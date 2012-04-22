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


  def get_feed(count=10)
    Twitter.home_timeline(:count => count)
    stories = Array.new
    feed.each do |tweet|
      temp = tweet_to_story(tweet) 
      stories.push(temp) 
    end 
  end 
  

  # This representation was asked by C1 lead. 
  def self.tweet_to_story (tweet)
    #story = [author: tweet['user']['name'], text: tweet['text']]
    story = Story.new
    story.title = tweet['user']['name']
    story.body = tweet['text']
    story.content = tweet['text']
    story.category = 'twitter'
  end

  

#  def tweet(tweet)
#    client = Twitter::Client.new
#    client.update(tweet)
#  end 
#

end
