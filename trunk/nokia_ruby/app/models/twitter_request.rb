class TwitterRequest < ActiveRecord::Base
  # attr_accessible :title, :body
  attr_accessible :request_token, :request_secret, :user_id
  belongs_to :user 

  validates :request_secret, presence: true
  validates :request_token, presence: true
  validates :user_id, presence: true
  validates :user_id, uniqueness: true
end
