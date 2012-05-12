require 'twitter'

class TwitterAccount < ActiveRecord::Base


  # Those are our Consumer Token and Consumer secret that twitter
  # provided us. This correspons our entity to twitter. The Consumer
  # Secret should be saved in a safe place. 
  CONSUMER_TOKEN  = 'A8Fh0r4H5DJl3dCYLGbXyQ'
  CONSUMER_SECRET = '614KLHBIR3jyAyULABnxeJ7jUWz5jDG2rs7K1zY20Q' 

  # auth_secret and auth_token are the access keys to the twitter 
  # accounts. Twitter provides them to us in phase to in the 
  # authentication. see twitter_requests_controller.rb for more info 
  attr_accessible :auth_secret, :auth_token
  belongs_to :user, class_name: "User" 

  validates :auth_secret, presence: true
  validates :auth_token, presence: true
  validates :user_id, presence: true 
  validates :user_id, uniqueness: true


  # This method configure twitters Gem paramters. This configuratin 
  # should be done whenever a feed will be request or a tweet will be
  # posted
  def config_twitter
    Twitter.configure do |config|
      config.consumer_key         = CONSUMER_TOKEN
      config.consumer_secret      = CONSUMER_SECRET
      config.oauth_token          = self.auth_token
      config.oauth_token_secret   = self.auth_secret
    end
    #puts 'twitter configured'
  end 
  
  # This methods is a simple method to get the feed from a twitter account.
  # The heart of this method is the much-richer Twitter::Client#home_timeline
  # method. Which has many featurs and options. But for the sake of simplicity 
  # I have pruned the option to one optional option which is the pages.  a page
  # contains 20 tweets. Adding a new options will be as simple as putting them 
  # into a hash and providing them to the home_timeline method. 
  def get_feed(page=1)
    self.config_twitter
    #puts "getting the feed"
    feed = Twitter.home_timeline(:page => page)
    #puts feed
    stories = Array.new
    feed.each do |tweet|
      temp = TwitterAccount.tweet_to_story(tweet) 
      stories.push(temp) 
    end 
    #puts stories 
    stories
  end 
  
  # This method converts a Twitter::Status to Story class. This representation
  # was asked by C1 
  def self.tweet_to_story(tweet)
    #story = [author: tweet['user']['name'], text: tweet['text']]
    story = Story.new
    story.instance_variables
    story.title = tweet['user']['name']
    story.content = tweet['text']
    #story.content = tweet['text']
    story.category = 'twitter'
    return story 
  end


#  def tweet(tweet)
#    configure_twitter
#    client = Twitter::Client.new
#    client.update(tweet)
#  end 

end
