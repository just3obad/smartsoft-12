class GaheemAccountsController < ApplicationController

  # GET /gaheem_accounts/1/edit
  def edit
    @gaheem_account = GaheemAccount.find(params[:id])
  end

  # PUT /gaheem_accounts/1
  # PUT /gaheem_accounts/1.json
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


end
