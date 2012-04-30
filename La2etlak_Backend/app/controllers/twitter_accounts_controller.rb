require 'twitter'
class TwitterAccountsController < ApplicationController 

=begin
  This method removes a twitter account that corresponds to the user
  with the specific id u_id (pasesed in the params)
=end 
  # will be triggered by twitter/:u_id/delete
  def remove_twitter_account
    user = User.find(params[:u_id])
    t_account = TwitterAccount.find_by_user_id(user.id)
    if (t_account)
      t_account.destroy
    else 
      return render text: 'You have no accounts'
    end 
    render text: "You twitter account was deleted"
  end 

=begin
  This method checks the existence of a twitter acocunt for 
  the user with the specific id u_id (passed in the params)
=end
  # will be triggered by twitter/:u_id/exists
  def exists?
    t_accounts = TwitterAccount.find_by_user_id(params[:u_id])
    #puts '###########' + t_accounts.class.to_s
    if t_accounts
      render text: 'true'
    else
      render text: 'false'
    end 
  end 

end
