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
    @h_account.save
  end
  
  def sign_in
    @h_account = Haccount.find_by_email(params[:email])
    if @h_account.password == params[:password]
      render :json => @h_account
    else
      format.json { render json: @h_account.errors, status: :unprocessable_entity }
    end
  end

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
