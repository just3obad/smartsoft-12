require 'twitter'
class TwitterAccountsController < ApplicationController 

=begin
  This method removes a twitter account that corresponds to the user
  with the specific id u_id (pasesed in the session)
  
  will be triggered by twitter/delete
  Author: Yahia  
=end 
  def remove_twitter_account
    if User.find(session[:user_id]).remove_twitter_account
      render :layout => 'mobile_template', :text => "Removed twitter account"
    else 
      render :layout => 'mobile_template', :text => "Error while removing the record"
    end 
  end 

=begin
  This controls uses a method in the model  to checks the existence of 
  a twitter acocunt for the user with the specific id u_id (passed in 
  the session)

  will be triggered by twitter/exists
  Author: Yahia
=end
  def exists?
    user = User.find(session[:user_id])
    if user.has_twitter_account
      render :layout => 'mobile_template', :text => "Twitter account exsits for #{User.find(params[:id])}"
    else 
      render :layout => 'mobile_template', :text => "Error while checking the record"
    end 
    
  end 

end
