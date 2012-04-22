require 'twitter'
class TwitterAccountsController < ApplicationController 
  def config_twitter
    @t_account = TwitterAccount.find_by_user_id(params[:u_id]) 
    Twitter.configure do |config|
      config.consumer_key         = CONSUMER_TOKEN
      config.consumer_secret      = CONSUMER_SECRET
      config.oauth_token          = t_account.auth_token
      config.oauth_token_secret   = t_account.auth_secret
    end
  end 


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
end
