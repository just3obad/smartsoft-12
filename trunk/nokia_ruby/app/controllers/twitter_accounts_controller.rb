require 'twitter'
class TwitterAccountsController < ApplicationController 
  def config_twitter
    Twitter.configure do |config|
      config.consumer_key         = CONSUMER_TOKEN
      config.consumer_secret      = CONSUMER_SECRET

      # FIXME
      #config.oauth_token          = access_token
      #config.oauth_token_secret   = access_secret
    end
  end 
end
