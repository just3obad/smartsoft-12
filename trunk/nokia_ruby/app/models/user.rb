class User < ActiveRecord::Base
  # attr_accessible :title, :body
  attr_accessible :name, :email
  has_many :shares, dependent: :destroy
  has_many :stories, through: :shares
  email_regex = /\A[\w+\-.]+@[a-z\d\-.]+\.[a-z]+\z/i
  validates :name, :presence => true,
  :length => { :maximum => 20 }
  validates :email, :presence => true,
  :format=> {:with => email_regex },
  :uniqueness => { :case_sensitive => false}

   def share!(storyid)
     shares.create!(story_id: storyid)
  end
end
