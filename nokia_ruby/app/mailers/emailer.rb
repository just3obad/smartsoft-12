class Emailer < ActionMailer::Base
  default from: "kerolos.bebawy@gmail.com"

  def registration_confirmation(account)
  recipients  account.email
  from        "kerolos.bebawy@gmail.com"
  subject     "Thank you for Registering, Please Verify your account"
  body        (:account => account,:code ==>VerificationCode.find_by_user_id(user.id).code)
  end

  def password_reset(account)
  recipients account.email
  from       "mina.adel.fahmy@gmail.com"
  subject    "Your password reset email"
  body       (:account => account,:password ==>Haccount.new_pass(user.id))
  end

end
