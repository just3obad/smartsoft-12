class Emailer < ActionMailer::Base
  default from: "info.2allak@gmail.com"

  def resend_code(account)
  #Emailer.resend_code(@account).deliver
  @code = VerificationCode.find_by_user_id(account.id).code
  mail(:to => account.email, :subject => "2allak Verification Code")
  end

  #def password_reset(account)
  #recipients account.email
  #from       "mina.adel.fahmy@gmail.com"
  #subject    "Your password reset email"
  #body       (:account => account,:password ==>Haccount.new_pass(user.id))
  #end

end
