class Emailer < ActionMailer::Base
  default from: "info.2allak@gmail.com"

  def resend_code(account)
  @code = VerificationCode.find_by_user_id(account.id).code
  mail(:to => account.email, :subject => "2allak Verification Code")
  end

  def password_reset(h_account, pass)
    @passw = pass
    mail(:to => h_account.email, :subject => "2allak New Password")
  end
end
