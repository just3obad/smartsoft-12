class BlockStory < ActiveRecord::Base
  attr_accessible :user_id, :story_id
  
  belongs_to :story, class_name: "Story"
  belongs_to :user, class_name: "User"
  
  validates :story_id, presence: true
  validates :user_id, presence: true
end