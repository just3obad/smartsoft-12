class Haccount < ActiveRecord::Base
  attr_accessible :email, :password, :user_id
  
  belongs_to :user, class_name: "User"
end
