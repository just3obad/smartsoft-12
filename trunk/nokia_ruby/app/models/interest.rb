class Interest < ActiveRecord::Base

  attr_accessible :RSS, :description, :name, :image
  
 
  has_many :feeds, :dependent => :destroy

  RSS_regex = /^(http|https):\/\/[a-z0-9]+([\-\.]{1}[a-z0-9]+)*\.[a-z]{2,6}(:[0-9]{1,5})?(\/.*)?$/ix
 
  validates :name, :presence => true,
                   :uniqueness => true

  validates :RSS,  :presence => true,
            :format   => { :with => RSS_regex },
            :uniqueness => { :case_sensitive => false }

  validates :description,  :length   => { :maximum => 240 }

  validates :image,  :format   => { :with => RSS_regex }
end
