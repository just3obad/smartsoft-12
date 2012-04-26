class HAccountsController < ApplicationController
  respond_to :html, :json
  # GET /h_accounts/1/edit
  def edit
    @h_account = Haccount.find(params[:id])
  end

  # PUT /h_accounts/1
  # PUT /h_accounts/1.json

  def create
    @h_account = Haccount.new(:email=>params[:email],:user_id=>params[:user_id],:password=>params[:password])
    if @h_account.save
      if @h_account.generateVerificationCode?
        Emailer.resend_code(@h_account).deliver
        format.json { render json: @h_account, status: :created }
      end
    else
      format.json { render json: @h_account.errors, status: :unprocessable_entity }
    end
  end
  
  def sign_in
    @h_account = Haccount.find_by_email(params[:email])
    if @h_account.password.eql? params[:password]
      @uLog = UserLogIn.create(params[@h_account.id])
      render json: @h_account
    else
      render json: @h_account.errors, status: :unprocessable_entity
    end
  end

  def forgot_password
    @h_account = Haccount.find_by_email(params[:email])
    if @h_account
      @pass = ([*('A'..'Z'),*('0'..'9')]-%w( 0 1 I O)).sample(6).join
      @h_account.password = @pass
      Emailer.password_reset(@h_account, @pass).deliver
    render json: true
    else
      render json: false
    end
  end
#this method takes user name email and password  updates the current info

  def update
    @h_account = Haccount.find(params[:id])

    respond_to do |format|
      if @h_account.update_attributes(:email=>params[:email],:password => params[:password])
        format.html { redirect_to @h_account, notice: 'HAccount was successfully updated.' }
        format.json { head :no_content }
      else
        format.html { render action: "edit" }
        format.json { render json: @h_account.errors, status: :unprocessable_entity }
      end
    end
  end

 def index
    @h_accounts = Haccount.all
    respond_with(@h_accounts)
 end
end
