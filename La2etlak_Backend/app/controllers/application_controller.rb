class ApplicationController < ActionController::Base
  protect_from_forgery

	helper_method :current_user
        helper_method :current_admin
    helper_method :nokia_user?

    def nokia_user?

    	agent = request.env["HTTP_USER_AGENT"].downcase
    	if agent.index('nokia'.downcase)
    		return true
    	else
    		return false
    	end
    end

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

	private
	def current_admin_session
 	 return @current_admin_session if defined?(@current_admin_session)
 	 @current_admin_session = AdminSession.find
	end

	def current_admin
 	 @current_admin = current_admin_session && current_admin_session.record
	end

end
