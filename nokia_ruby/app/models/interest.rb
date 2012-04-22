class Interest < ActiveRecord::Base
  
#attributes  that can be modified automatically by outside users
  attr_accessible :RSS, :description, :name, :image
  
  has_many :stories
  has_many :feeds, :dependent => :destroy

 # RSS feed link has to be of the form "http://www.abc.com"
RSS_regex = /^(http|https):\/\/[a-z0-9]+([\-\.]{1}[a-z0-9]+)*\.[a-z]{2,6}(:[0-9]{1,5})?(\/.*)?$/ix
 
  # name cannot be duplicated and has to be there .

validates :name, :presence => true,
                   :uniqueness => true


# we cannot create an Interest without an rss .. 
  validates :RSS,  :presence => true,
            :format   => { :with => RSS_regex },
 


#description can never exceed 240 characters .
  validates :description,  :length   => { :maximum => 240 }


# the image will be entered using a URL link whci should also be of the form  "http://www. xxxx.jpg"
  validates :image,  :format   => { :with => RSS_regex }
end
