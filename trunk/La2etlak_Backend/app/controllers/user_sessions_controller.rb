class UserSessionsController < ApplicationController

	before_filter :user_authenticated?, :only => [:destroy]

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

	# Author: Kiro
	# renders the login screen
	def new
  		@user_session = UserSession.new
  		render :layout =>"mobile_template"
	end

	# Author: Kiro
	# creates a user session for the user
	# redirects the user to his main feed
	def create
 		@user_session = UserSession.new(params[:user_session])
  		if @user_session.save
				@user = current_user
				if @user.deactivated
					@user_session.destroy
					flash[:notice] = "Sorry, your account has been deactivated red"
   				redirect_to :action => 'new', :layout =>"mobile_template"
				else
					UserLogIn.create!(:user_id => @user.id)
   				flash[:notice] = "Successfully logged in green"
    			redirect_to "/mob/main_feed"
				end
  		else
  		  flash[:notice] = @user_session.errors.full_messages[0].to_s+"red"
   			redirect_to :action => 'new', :layout =>"mobile_template"
 	 	end
	end





	# This method is used to logout the currently logged in
	# user by destroying his UserSession
	# Author: Kiro
	def destroy
  	@user_session = UserSession.find
  	@user_session.destroy
  	flash[:notice] = "Successfully logged out."
		redirect_to new_user_session_path
	end

	# This method is used to login the user by creating
	# a UserSession using his perishable_token
	# Author: Kiro
	def login_with_token
		user = User.find_by_perishable_token(params[:token])
 		UserSession.create(user)
	end



end

