class ApplicationController < ActionController::Base
  protect_from_forgery

	helper_method :current_user

	# This is a helper method that returns the User record
	# for the currently logged in user, this method is
  # added in the application controller since it is
	# needed to be accessed from anywhere inside the
	# application.
	# Author: Kiro
	private
	def current_user_session
 	 return @current_user_session if defined?(@current_user_session)
 	 @current_user_session = UserSession.find
	end

	def current_user
 	 @current_user = current_user_session && current_user_session.record
	end

end
