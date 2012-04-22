class UserAddInterest < ActiveRecord::Base
  attr_accessible :interest_id, :user_id
  
  belongs_to :interest, class_name: "Interest"
  belongs_to :user, class_name: "User"
  
  validates :interest_id, presence: true
  validates :user_id, presence: true
end
