class CommentUpDown < ActiveRecord::Base

=begin
    
Author: Menisy


=end

  attr_accessible :action
  belongs_to :user
  belongs_to :comment
  
  def add_to_log(commenter,before=1)
    msg = nil
    if self.action == 1 then
      if before == 1
        msg = "upped"
      else
        msg = "un-upped"
      end
    else
      if before == 1
        msg = "downed"
      else
        msg = "un-downed"
      end
    end
      user_name = self.user.name  ||  self.user.email.split('@')[0]
      commenter_name = commenter.name  ||  commenter.email.split('@')[0]
      Log.create!(loggingtype: 2,user_id_1: self.user.id,user_id_2: commenter.id,admin_id: nil,story_id: nil,interest_id: nil,message: user_name + " " + msg + " the comment \"" +  self.comment.content +  "\"" + " by " + commenter_name )
  end
end
