class HAccountsController < ApplicationController
  respond_to :html, :json
  # GET /h_accounts/1/edit
  def edit
    @h_account = HAccount.find(params[:id])
  end

  # PUT /h_accounts/1
  # PUT /h_accounts/1.json

  def create
    @h_account = HAccount.new(params[:h_account])

    respond_to do |format|
      if @h_account.save
         if @h_account.generateVerificationCode?
           Emailer.deliver_registration_confirmation(@h_account)
         end
        format.json {render json: @h_account, status: :created, location: @h_account}
      else
        format.json {render json: @h_account.errors, status: :unprocessable_entity }
    end
  end
  end  
  def update
    @h_account = HAccount.find(params[:id])

    respond_to do |format|
      if @H_account.update_attributes(params[:h_account])
        format.html { redirect_to @h_account, notice: 'HAccount was successfully updated.' }
        format.json { head :no_content }
      else
        format.html { render action: "edit" }
        format.json { render json: @h_account.errors, status: :unprocessable_entity }
      end
    end
  end

 def index
    @h_accounts = HAccount.all
    respond_with(@h_accounts)
 end
end
