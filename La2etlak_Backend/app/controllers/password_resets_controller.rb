class PasswordResetsController < ApplicationController
before_filter :load_admin_using_perishable_token, :only => [:edit, :update]  
  before_filter :require_no_admin

def new  
 @admin_session = Admin.new
   render  
end  
  
def create  
 @admin = Admin.find_by_email(params[:email])  
 if @admin  
  @admin.deliver_password_reset_instructions!
  @admin_session = AdminSession.new  
  flash[:notice] = "Instructions to reset your password have been emailed to you. " +  
  "Please check your email."  
  redirect_to('/admin/login')  
 else  
  flash[:notice] = "No admin was found with that email address"  
  render :action => :new  
 end  
end
    

  
def edit  
 render  
end  
  
def update  
 @admin.password = params[:admin][:password]  
 @admin.password_confirmation = params[:admin][:password_confirmation]  
 if @admin.save  
  flash[:notice] = "Password successfully updated"  
  redirect_to('/admin/index')  
 else  
  render :action => :edit  
 end  
end  
  
private  
def load_admin_using_perishable_token  
 @admin = Admin.find_using_perishable_token(params[:id])  
 unless @admin  
  flash[:notice] = "We're sorry, but we could not locate your account. " +  
  "If you are having issues try copying and pasting the URL " +  
  "from your email into your browser or restarting the " +  
  "reset password process."  
  redirect_to('/admin/login')  
 end  
end
  
end
