class BlockInterest < ActiveRecord::Base
  attr_accessible :user_id, :interest_id
  
  belongs_to :interest, class_name: "Interest"
  belongs_to :user, class_name: "User"
  
  validates :interest_id, presence: true
  validates :user_id, presence: true
end
