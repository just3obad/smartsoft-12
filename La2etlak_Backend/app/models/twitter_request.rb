class TwitterRequest < ActiveRecord::Base
  # This class has request_token and request_secret. Those 
  # tokens are the important parameters in the authentication 
  # request. They are needed to be able to get the access keys 
  # for a twitter account for a certain user (specified by user_
  # id) in phase 2 in the authentication process. For more info 
  # about authentication process see twitter_requests_controller.rb
  # attr_accessible :title, :body
  attr_accessible :request_token, :request_secret, :user_id
  belongs_to :user 

  validates :request_secret, presence: true
  validates :request_token, presence: true
  validates :user_id, presence: true
  validates :user_id, uniqueness: true
end
