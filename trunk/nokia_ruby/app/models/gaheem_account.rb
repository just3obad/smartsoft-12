class GaheemAccount < ActiveRecord::Base
  attr_accessible :email, :password,

  email_regex = /\A[\w+\-.]+@[a-z\d\-.]+\.[a-z]+\z/i
#  validates :email, :presence => true,
#                    :format=> {:with => email_regex },
#                    :uniqueness => { :case_sensitive => false}


end
