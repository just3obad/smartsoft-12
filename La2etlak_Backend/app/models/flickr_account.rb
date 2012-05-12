class FlickrAccount < ActiveRecord::Base
  attr_accessible :consumer_key, :secret_key, :user_id
end
