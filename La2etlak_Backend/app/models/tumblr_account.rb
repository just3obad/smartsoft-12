class TumblrAccount < ActiveRecord::Base
  attr_accessible :consumer_key, :secret_key
  belongs_to :user_id, class_name: "User" 
end
