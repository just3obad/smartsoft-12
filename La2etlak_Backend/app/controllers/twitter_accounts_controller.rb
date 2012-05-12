require 'twitter'
class TwitterAccountsController < ApplicationController 

=begin
  This method removes a twitter account that corresponds to the user
  with the specific id u_id (pasesed in the params)
=end 
  # will be triggered by twitter/:u_id/delete
  # Author: Yahia
  def remove_twitter_account
    if User.remove_twitter_account(params[:u_id])
      render :layout => 'mobile_template', :text => "Removed twitter account"
    else 
      render :layout => 'mobile_template', :text => "Error while removing the record"
    end 
  end 

=begin
  This method checks the existence of a twitter acocunt for 
  the user with the specific id u_id (passed in the params)
=end
  # will be triggered by twitter/:u_id/exists
  # Author: Yahia
  def exists?
    if TwitterAccount.exists?(params[:u_id])
      render (:layout => 'mobile_template',
              :text => "Twitter account exsits for #{User.find(params[:id])}")
    else 
      render :layout => 'mobile_template', :text => "Error while checking the record"
    end 
    
  end 

end
