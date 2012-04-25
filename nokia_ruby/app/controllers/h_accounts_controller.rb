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
      format.json { render json: @h_account, status: :created }
    else
      format.json { render json: @h_account.errors, status: :unprocessable_entity }
    end
  end
  
  def sign_in
    @h_account = Haccount.find_by_email(params[:email])
    if @h_account.password.eql? params[:password]
      render json: @h_account
    else
      render json: @h_account.errors, status: :unprocessable_entity
    end
  end

  def forgot_password
    @h_account = Haccount.find_by_email(params[:email])
    Emailer.password_reset(@h_account).deliver
    render json: true
  end
#this method takes user name email and password  updates the current info

  def profile
#    @h_account = Haccount.find(params[:id])
    @h_account=Haccount.find(params[:id]).update_attributes(:password => params[:password])
    if @h_account
    #format.json { render json: "updated" }
    respond_with("updated")
    end
  end

 def reset_password
   @account = Haccount.find_by_email(params[:email])
   Emailer.deliver_password_reset(@account)
   format.json {render json: @account, notice: "Password Reset" }
 end

 def index
    @h_accounts = Haccount.all
    respond_with(@h_accounts)
 end
end
