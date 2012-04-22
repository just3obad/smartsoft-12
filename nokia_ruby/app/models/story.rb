class Story < ActiveRecord::Base
#definition of some attributes:-
# :rank==>hottness of a story, :category==> name of the related interest, :interest_id==>id of the related interest,
# :type==> 1 (Article) 2 (Image) 3 (video)
  attr_accessible :title, :date, :body, :rank, :image, :category, 
                  :content, :deleted, :hidden , :likes , :dislikes ,
		  :flags, :type, :interest, :is_blocked
  belongs_to :interests

  #def initialize(title, date, body)
   # @title = title
    #@body = body
  #end

end
