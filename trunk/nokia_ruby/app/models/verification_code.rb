class VerificationCode < ActiveRecord::Base
  attr_accessible :code, :account_id, :verified
  
  belongs_to :h_account
  
  validates :code, :presence => true,
  :length => { :is => 4}
  
  validates :account_id, :presence => true,
  :uniqueness => true

end
