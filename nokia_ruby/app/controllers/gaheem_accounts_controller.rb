class GaheemAccountsController < ApplicationController
  respond_to :html, :json
  # GET /gaheem_accounts/1/edit
  def edit
    @gaheem_account = GaheemAccount.find(params[:id])
  end

  # PUT /gaheem_accounts/1
  # PUT /gaheem_accounts/1.json

  def create
    @gaheem_account = GaheemAccount.new(params[:gaheem_account])

    respond_to do |format|
      if @gaheem_account.save
        format.json {render json: @gaheem_account, status: :created, location: @gaheem_account}
      else
        format.json {render json: @gaheem_account.errors, status: :unprocessable_entity }
    end
  end
  end  
  def update
    @gaheem_account = GaheemAccount.find(params[:id])

    respond_to do |format|
      if @gaheem_account.update_attributes(params[:gaheem_account])
        format.html { redirect_to @gaheem_account, notice: 'Gaheem account was successfully updated.' }
        format.json { head :no_content }
      else
        format.html { render action: "edit" }
        format.json { render json: @gaheem_account.errors, status: :unprocessable_entity }
      end
    end
  end

 def index
    @gaheem_accounts = GaheemAccount.all
    respond_with(@gaheem_accounts)
 end
end
