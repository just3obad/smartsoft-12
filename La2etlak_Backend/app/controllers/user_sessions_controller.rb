class UserSessionsController < ApplicationController

	# This method is used to check if the user entered a valid
	# email and password, if the UserSession was successfully
	# created it replies with the user's perishable_token,
	# otherwise it replies with the errors that occured.
	# Author: Kiro
	def requestToken
  	@user_session = UserSession.new(params[:user_session])
		respond_to do |format|
 		  if @user_session.save
   			@user = current_user
				format.json { render json: @user.perishable_token }
				@user_session.destroy
 	 		else
  	  	errors = "errors:" + @user_session.errors.to_s
				format.json { render text: errors }
 	 		end
		end
	end

	def new
  		@user_session = UserSession.new
  		render :layout =>"mobile_template"
	end

	def create
 		@user_session = UserSession.new(params[:user_session])
  		if @user_session.save
   			flash[:notice] = "Successfully logged in."
    		redirect_to "/main_feed"
  		else
   			render :action => 'new', :layout =>"mobile_template"
 	 	end
	end





	# This method is used to logout the currently logged in
	# user by destroying his UserSession
	# Author: Kiro
	def destroy
  	@user_session = UserSession.find
  	@user_session.destroy
  	flash[:notice] = "Successfully logged out."
	end

	# This method is used to login the user by creating
	# a UserSession using his perishable_token
	# Author: Kiro
	def login_with_token
		user = User.find_by_perishable_token(params[:token])
 		UserSession.create(user)
	end



end

