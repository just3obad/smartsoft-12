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
  validates :date_of_birth, :presence => true
  

  def 

 # lets a user share a story given its id
  def share?(story_id)
    @share = Share.find_by_user_id_and_story_id(self.id,story_id)
    if @share.nil? then		# if he/she didn't share this story before then make him/her share it
      Share.create :user_id=>self.id,:story_id=>story_id
      return true		# shared successfully, return true
    else 			# else, dont allow to share
      return false		# sharing failed, return false
    end
  end 
end
