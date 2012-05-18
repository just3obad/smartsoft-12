class CommentUpDown < ActiveRecord::Base
  attr_accessible :action
  belongs_to :user
  belongs_to :comment
  
  def add_to_log(commenter)
    msg = nil
    if self.action == 1 then
      msg = "UP"
    else
      msg = "DOWN"
    end
      Log.create!(loggingtype: 2,user_id_1: self.user.id,user_id_2: commenter.id,admin_id: nil,story_id: nil,interest_id: nil,message: self.user.name + " thumbed " + msg + " the comment \"" +  self.comment.content +  "\"" + " by " + commenter.name )
  end
end
