class User < ActiveRecord::Base
  # attr_accessible :title, :body
  attr_accessible :name, :email
  
  email_regex = /\A[\w+\-.]+@[a-z\d\-.]+\.[a-z]+\z/i
  validates :name, :presence => true,
  :length => { :maximum => 20 }
  validates :email, :presence => true,
  :format=> {:with => email_regex },
  :uniqueness => { :case_sensitive => false}
  
  def comment!(story_id,content)
     Comment.create(:content => content,:user_id => self.id , :story_id => story_id) 
  end
  
end
