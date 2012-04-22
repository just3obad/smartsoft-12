class TwitterRequest < ActiveRecord::Base
  # attr_accessible :title, :body
  attr_accessible :request_token, :request_secret, :user_id
  belongs_to :user 
end
