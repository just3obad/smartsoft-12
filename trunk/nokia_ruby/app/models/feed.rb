class Feed < ActiveRecord::Base
  attr_accessible :interest_id, :link
  
  belongs_to :interest, class_name: "Interest"
end
