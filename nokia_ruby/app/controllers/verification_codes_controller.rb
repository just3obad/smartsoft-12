class VerificationCodesController < ApplicationController
                       respond_to :html,:json

# This method 'verify' takes the Haccount ID of the user and the code he entered to verify his
# account as parameters and tries to verify the Haccout of the user using the code he entered
# using the method verifyAccount?(verCode)
# If the Haccount was successfully verified the server sends the string "true" to the user,
# otherwise it sends "false"

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

# This method 'resend' uses the Haccount ID of the user that requested it and calls the method
# resendCode? to reset the verification code of the Haccount
# If the account wasn't verified yet (resentCode? succeeded) the user recieved an email containing
# his new verification code and the server send "true", otherwise only "false" is sent to the app

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
