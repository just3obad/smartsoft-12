class VerificationCodesController < ApplicationController
                       respond_to :html,:json

  def verify

  @code = params[:code]
  @account = GaheemAccount.find_by_id(params[:id])
    respond_to do |format|
      if @account.verifyAccount?(@code)
      format.json { render json: @account, notice: "account succesfully verified" }
      else
      format.json { render json: @account.errors, status: :unprocessable_entity }
      end
    end
  end

  def resend
  @account = GaheemAccount.find_by_id(params[:id])
    respond_to do |format|
      if @account.resendCode?
      Emailer.deliver_registration_confirmation(@account)
      format.json { render json: @account, notice: "Verification Code Resent" }
      else
      format.json { render json: @account.errors, status: :unprocessable_entity }
      end
    end
  end


end
