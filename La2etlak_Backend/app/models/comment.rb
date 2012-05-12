class Comment < ActiveRecord::Base
  attr_accessible :content
  belongs_to :user
  belongs_to :story
  has_many :comment_up_downs
  validates_presence_of :content
  validates_presence_of :user
  validates_presence_of :story
  
  # This method adds the details of this comment to the log file.
  # It will be called after a successful creation of the comment
  def add_to_log
   # Log.create!(loggingtype: 2,user_id_1: self.user.id ,user_id_2: nil,admin_id: nil,story_id: self.story.id ,interest_id: nil,message: (self.user.name+" commented on \"" + self.story.title + "\" with \"" + self.content + "\"").to_s )
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
  
  def up_comment(user)
    upped_before = self.upped_by_user?(user)
    downed_before = self.downed_by_user?(user)
    if !upped_before && !downed_before then #if user never upped or downed the comment then up it
      up = CommentUpDown.new(:action => 1)
      up.comment = self
      up.user = user
      up.save
      up.add_to_log(self.user)
      return true
   #  self.comment_up_downs << Comment_up_down.create()
    elsif downed_before then
      self.comment_up_downs.find_by_user_id_and_action(user.id,2).destroy #if user disliked it, now make him like it!
      up = CommentUpDown.new(:action => 1)
      up.comment = self
      up.user = user
      up.save
      up.add_to_log(self.user)
      return true
    elsif upped_before
      self.comment_up_downs.find_by_user_id_and_action(user.id,1).destroy
      return true
    else
      return false
    end     
  end
  
  def down_comment(user)
    upped_before = self.upped_by_user?(user)
    downed_before = self.downed_by_user?(user)
    if !upped_before && !downed_before then #if user never upped or downed the comment then up it
      down = CommentUpDown.new(:action => 2)
      down.comment = self
      down.user = user
      down.save
      down.add_to_log(self.user)
      return true
   #  self.comment_up_downs << Comment_up_down.create()
    elsif upped_before then
      self.comment_up_downs.find_by_user_id_and_action(user.id,1).destroy #if user disliked it, now make him like it!
      down = CommentUpDown.new(:action => 2)
      down.comment = self
      down.user = user
      down.save
      down.add_to_log(self.user)
      return true
   elsif downed_before
      self.comment_up_downs.find_by_user_id_and_action(user.id,2).destroy
      return true
    else
      return false
    end
   end 
  
end
