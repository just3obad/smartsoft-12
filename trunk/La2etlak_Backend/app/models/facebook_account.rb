

class FacebookAccount < ActiveRecord::Base
  attr_accessible :auth_secret, :auth_token
  CONSUMER_TOKEN  = ''
  CONSUMER_SECRET = ''
  belongs_to :user
  validates_presence_of :user
  validates_presence_of :auth_token
  validates_presence_of :auth_secret


end
