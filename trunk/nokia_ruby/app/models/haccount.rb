class Haccount < ActiveRecord::Base
  attr_accessible :email, :password, :user_id
  
  belongs_to :user, class_name: "User"

  validates :email, :presence => true,
  :format=> {:with => email_regex }
  :uniqueness => { :case_sensitive => false}
  validates :user_id, uniqueness =>true
end
