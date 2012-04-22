require 'twitter'

class TwitterAccount < ActiveRecord::Base
  
  CONSUMER_TOKEN  = 'cuE56v2eK79gsxK1baasA'
  CONSUMER_SECRET = 'vIJmDpiqDNpiau2YaiHH19fW1zj5DRXjI1VUbuM7lk' 

  attr_accessible :auth_secret, :auth_token
  belongs_to :user, class_name: "User" 

  validates :auth_secret, presence: true
  validates :auth_token, presence: true
  validates :user_id, presence: true 
  validates :user_id, uniqueness: true


  def config_twitter
    Twitter.configure do |config|
      config.consumer_key         = CONSUMER_TOKEN
      config.consumer_secret      = CONSUMER_SECRET
      config.oauth_token          = self.auth_token
      config.oauth_token_secret   = self.auth_secret
    end
    puts 'twitter configured'
  end 

  def get_feed(page=1)
    self.config_twitter
    puts "getting the feed"
    feed = Twitter.home_timeline(:page => page)
    puts feed
    stories = Array.new
    feed.each do |tweet|
      temp = TwitterAccount.tweet_to_story(tweet) 
      stories.push(temp) 
    end 
    stories
  end 

  # This representation was asked by C1 lead. 
  def self.tweet_to_story(tweet)
    #story = [author: tweet['user']['name'], text: tweet['text']]
    story = Story.new
    story.instance_variables
    story.title = tweet['user']['name']
    story.body = tweet['text']
    #story.content = tweet['text']
    story.category = 'twitter'
    return story 
  end


#  def tweet(tweet)
#    client = Twitter::Client.new
#    client.update(tweet)
#  end 
#
end
