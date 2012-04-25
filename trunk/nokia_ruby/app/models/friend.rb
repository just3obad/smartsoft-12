class Friends < ActiveRecord::Base
  attr_accessible :receiver, :sender, :status
  
  belongs_to :user
  belongs_to :friend, :class_name => "User", :foreign_key => "friend_id"

  validates :receiver, presence: true
  validates :sender, presence: true
  validates :stat, presence: true
  
end
