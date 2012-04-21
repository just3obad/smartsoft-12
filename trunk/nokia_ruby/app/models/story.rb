class Story < ActiveRecord::Base
  attr_accessible :title, :body, :rank, :image, :category, 
                  :content, :deleted, :hidden

end
