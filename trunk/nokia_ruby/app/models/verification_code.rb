class VerificationCode < ActiveRecord::Base
  attr_accessible :code, :user_id, :verified
  
  belongs_to :user
  
  validates :code, :presence => true,
  :length => { :is => 4}
  
  validates :user_id, :presence => true,
  :uniqueness => true
end
