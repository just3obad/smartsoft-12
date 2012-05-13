class Emailer < ActionMailer::Base
  default from: "info.2allak@gmail.com"

	# Author: Kiro
	def verification_instructions(user)
		@code = user.verification_code.code
		mail(:to => user.email, :subject => "La2etlak Verification Instructions")
	end

	# Author: Kiro
  def resend_code(user)
  	@code = user.verification_code.code
  	mail(:to => user.email, :subject => "La2etlak Verification Code")
  end

	# Author: Kiro
  def reset_password(user,pass)
  	@pass = pass
  	mail(:to => user.email, :subject => "La2etlak New Password")
  end

  def password_reset(h_account, pass)
    @passw = pass
    mail(:to => h_account.email, :subject => "2allak New Password")
  end
  # author: Gasser
  def send_forced_password(user, pass)
    @passw = pass
    mail(:to => user.mail, :subject => "Your Password was reset by the Admin")
  end

 def recommend_story(sender, reciever, message, stitle, sbody)

   @user=sender
   @friend=reciever
   @mess=message
   @storytit=stitle
   @storybod=sbody

  mail(:to => @friend, :subject => "recommendation in La2etlak app")

 end

def invite_to_app(sender, reciever, message)

   @user=sender
   @friend=reciever
   @mess=message
  
  mail(:to => @friend, :subject => "invitation to La2etlak app")

 end

end
