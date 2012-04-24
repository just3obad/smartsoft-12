class VerificationCodesController < ApplicationController
                       respond_to :html,:json

  def verify

  @code = params[:code]
  @account = Haccount.find_by_id(params[:id])
    respond_to do |format|
      if @account.verifyAccount?(@code)
      @result = true
      format.json { render json: @result }
      else
      @result = false
      format.json { render json: @result }
      end
    end
  end

  def resend
  @account = Haccount.find_by_id(params[:id])
    respond_to do |format|
      if @account.resendCode?
      @result = true
      Emailer.resend_code(@account).deliver
      format.json { render json: @result }
      else
      @result = false
      format.json { render json: @result }
      end
    end
  end


end
