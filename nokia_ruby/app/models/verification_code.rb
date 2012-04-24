class VerificationCode < ActiveRecord::Base
  attr_accessible :code, :account_id, :verified, :user_id
  
  belongs_to :h_account, class_name: "Haccount"
  
  validates :code, :presence => true,
  :length => { :is => 4}
  
  validates :user_id, :presence => true,
  :uniqueness => true

end
