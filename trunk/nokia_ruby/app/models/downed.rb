class Downed < ActiveRecord::Base
  attr_accessible :comment_id, :user_id
  
  belongs_to :comment, class_name: "Comment"
  belongs_to :user, class_name: "User"
end
