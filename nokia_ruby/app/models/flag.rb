class Flag < ActiveRecord::Base
  attr_accessible :story_id, :user_id
  
  belongs_to :story, class_name: "Story"
  belongs_to :user, class_name: "User"
end
