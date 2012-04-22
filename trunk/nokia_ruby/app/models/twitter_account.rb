require 'twitter'

class TwitterAccount < ActiveRecord::Base
  
  CONSUMER_TOKEN  = 'cuE56v2eK79gsxK1baasA'
  CONSUMER_SECRET = 'vIJmDpiqDNpiau2YaiHH19fW1zj5DRXjI1VUbuM7lk' 

  attr_accessible :auth_secret, :auth_token
  belongs_to :user, class_name: "User" 

  validates :auth_secret, presence: true
  validates :auth_token, presence: true
  validates :user_id, presence: true

  def config_twitter
    Twitter.configure do |config|
      config.consumer_key         = CONSUMER_TOKEN
      config.consumer_secret      = CONSUMER_SECRET
      config.oauth_token          = access_token
      config.oauth_token_secret   = access_secret
    end
  end 


#  def tweet(tweet)
#    client = Twitter::Client.new
#    client.update(tweet)
#  end 
#
#
#  # returns the time of the last tweet received
#  def get_main_feed(count=10)
#    feed = Twitter.home_timeline(:count => count)
#  end 
end
