require 'twitter'
class TwitterAccountsController < ApplicationController 


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

  def exists?
    t_accounts = TwitterAccount.find_by_user_id(params[:u_id])
    puts '###########' + t_accounts.class.to_s
    if t_accounts
      render text: 'true'
    else
      render text: 'false'
    end 
  end 

end
