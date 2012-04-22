class Emailer < ActionMailer::Base
  default from: "kerolos.bebawy@gmail.com"

  def registration_confirmation(account)
  @recipients  account.email
  @from        "kerolos.bebawy@gmail.com"
  @subject     "Thank you for Registering, Please Verify your account"
  @body        (:account => account,:code ==>VerificationCode.find_by_user_id(user.id).code)
  end


end
