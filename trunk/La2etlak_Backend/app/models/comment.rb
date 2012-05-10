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
  
  # This method checks if a user thumbed up this comment before
  def upped_by_user?(user)
    up = self.comment_up_downs.find_by_user_id_and_action(user.id,1) # get the up that a user gave to this comment before "if exists"
    !up.nil? # if up is nil then return false if not then return true
  end
  
  # This method checks if a user thumbed up this comment before
  def downed_by_user?(user)
    down = self.comment_up_downs.find_by_user_id_and_action(user.id,2) # get the down that a user gave to this comment before "if exists"
    !down.nil? # if down is nil then return false if not then return true
  end
  
  def up_comment?(user)
    upped_before = self.upped_by_user?(user)
    downed_before = self.downed_by_user?(user)
    if !upped_before && !downed_before  #if user never upped or downed the comment then up it
      up = Comment_up_down.create(:action => 1)
      user.comment_up_downs << up
      comment.comment_up_downs << up
      up.add_to_log(self.user)
      render json: "yes" and return true
   #  self.comment_up_downs << Comment_up_down.create()
    else if downed_before
      self.comment_up_downs.find_by_user_id_and_action(user.id,2).destroy #if user disliked it, now make him like it!
      up = Comment_up_down.create(:action => 1)
      user.comment_up_downs << up
      comment.comment_up_downs << up
      up.add_to_log(self.user)
      render json: "yes" and return true
    else
      render json: "no" and return false
    end
      
  end
  
  def down_comment?(user)
    upped_before = self.upped_by_user?(user)
    downed_before = self.downed_by_user?(user)
    if !upped_before && !downed_before  #if user never upped or downed the comment then up it
      down = Comment_up_down.create(:action => 2)
      user.comment_up_downs << down
      comment.comment_up_downs << down
      down.add_to_log(self.user)
      render json: "yes" and return true
   #  self.comment_up_downs << Comment_up_down.create()
    else if upped_before
      self.comment_up_downs.find_by_user_id_and_action(user.id,1).destroy #if user disliked it, now make him like it!
      down = Comment_up_down.create(:action => 1)
      user.comment_up_downs << down
      comment.comment_up_downs << down
      down.add_to_log(self.user)
      render json: "yes" and return true
    else
      render json: "no" and return false
    end
      
  end
    
  
end
