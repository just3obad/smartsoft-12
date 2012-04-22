class Story < ActiveRecord::Base
#definition of some attributes:-
# :rank==>hottness of a story, :category==> name of the related interest, :interest_id==>id of the related interest,
# :type==> 1 (Article) 2 (Image) 3 (video)
  attr_accessible :title, :date, :body, :rank,
                 :content, :deleted, :hidden , :interest_id
  belongs_to :interests

  #def initialize(title, date, body)
   # @title = title
    #@body = body
  #end

# get_story is a method that takes a specific story_id as an input  and searches the database for the stroy with this id and returns #this story to the caller

  def self.get_story(story_id)
      return Story.find(story_id)
  end


end
