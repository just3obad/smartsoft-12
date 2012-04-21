class User < ActiveRecord::Base
  # attr_accessible :title, :body
  attr_accessible :name, :email, :deactivated, :first_name, :last_name, :date_of_birth
  
  has_one :gaheem_account

  email_regex = /\A[\w+\-.]+@[a-z\d\-.]+\.[a-z]+\z/i
  validates :name, :presence => true,
  :length => { :maximum => 20 }
  validates :email, :presence => true,
  :format=> {:with => email_regex },
  :uniqueness => { :case_sensitive => false}
  validates :first_name, :presence => true,
  :length => { :maximum => 20 }
  validates :last_name, :presence => true,
  :length => { :maximum => 20 }
  validates :date_of_birth, :presence => true,
  
  
  
  
  
end
