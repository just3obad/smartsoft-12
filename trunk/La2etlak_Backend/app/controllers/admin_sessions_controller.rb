class AdminSessionsController < ApplicationController

 def new  
   @admin_session = AdminSession.new  
 end
=begin
  This method is used to check if the user entered a valid
 email and password, if the AdminSession was successfully
 created it replies with the user's perishable_token,
 otherwise it replies with the errors that occured.
 Author : mouaz
 params(admin_session parameters)
=end
 def create  
  @admin_session = AdminSession.new(params[:admin_session])  
  if @admin_session.save  
    flash[:notice] = "Successfully logged in."  
    redirect_to('/admins/index')  
  else  
    render :action => 'new'  
  end  
 end  
=begin
 This method is used to logout the currently logged in
 admin by destroying his AdminSession
 otherwise it replies with the errors that occured.
 Author : mouaz
 params(admin_session parameters)
=end

 def destroy  
    @admin_session = AdminSession.find
  if @admin_session == nil
    redirect_to('/admin/login')
  else  
    @admin_session.destroy  
    flash[:notice] = "Successfully logged out."  
    redirect_to('/admin/login')
  end  
 end 
 

end
