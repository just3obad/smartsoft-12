class Likedislike < ActiveRecord::Base
  attr_accessible :action, :story_id, :user_id
  
end
