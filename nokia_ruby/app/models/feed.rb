class Feed < ActiveRecord::Base
  attr_accessible  :link, :interest_id
  belongs_to :interest
  LINK_regex = /^(http|https):\/\/[a-z0-9]+([\-\.]{1}[a-z0-9]+)*\.[a-z]{2,6}(:[0-9]{1,5})?(\/.*)?$/ix

  validates :link,  :presence => true,
            :format   => { :with => LINK_regex },
            :uniqueness => { :case_sensitive => false }


  validates :interest_id, :presence => true


end
