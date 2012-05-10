class CommentUpDown < ActiveRecord::Base
  attr_accessible :action
  belongs_to :user
  belongs_to :comment
  
  add_to_log(commenter)
     Log.create!(loggingtype: 2,user_id_1: self.user.id,user_id_2: commenter.id,admin_id: nil,story_id: nil,interest_id: nil,message: self.user.name +" thumbed UP the comment \"" +  self.comment.content +"\"" + " by " + commenter.name )
  end
end
