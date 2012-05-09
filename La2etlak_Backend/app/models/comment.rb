class Comment < ActiveRecord::Base
  attr_accessible :content
  belongs_to :user
  belongs_to :story
  has_many :comment_up_downs
  
  # This method adds the details of this comment to the log file.
  # It will be called after a successful creation of the comment
  def add_to_log
    Log.create!(loggingtype: 2,user_id_1: self.user.id ,user_id_2: nil,admin_id: nil,story_id: self.story.id ,interest_id: nil,message: (self.user.name+" commented on \"" + self.story.title + "\" with \"" + self.content + "\"").to_s )
  end
end
