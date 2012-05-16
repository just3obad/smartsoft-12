class AdminSessionsController < ApplicationController

 def new  
   @admin_session = AdminSession.new  
 end
 	# This method is used to check if the user entered a valid
	# email and password, if the AdminSession was successfully
	# created it replies with the user's perishable_token,
	# otherwise it replies with the errors that occured.
 def create  
  @admin_session = AdminSession.new(params[:admin_session])  
  if @admin_session.save  
    flash[:notice] = "Successfully logged in."  
    redirect_to('/admins/index')  
  else  
    render :action => 'new'  
  end  
 end  
 # This method is used to logout the currently logged in
 # admin by destroying his UserSession
 def destroy  
  @admin_session = AdminSession.find  
  @admin_session.destroy  
  flash[:notice] = "Successfully logged out."  
  redirect_to('/admin_login')  
 end  
 

end
