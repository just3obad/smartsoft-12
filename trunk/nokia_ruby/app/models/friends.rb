class Friends < ActiveRecord::Base
  attr_accessible :receiver, :sender, :stat
  
  belongs_to:user, class_name:"User"

  validates :receiver, presence: true
  validates :sender, presence: true
  validates :stat, presence: true
end
