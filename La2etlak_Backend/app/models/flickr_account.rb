class FlickrAccount < ActiveRecord::Base
  attr_accessible :consumer_key, :secret_key, :user_id
  belongs_to :user, class_name: "User" 
end
