class Story < ActiveRecord::Base
#definition of some attributes:-
# :rank==>hottness of a story, :interest_id==>id of the related interest,
# :type==> 1 (Article) 2 (Image) 3 (video)
  attr_accessible :interest_id, :title, :date, :rank, :description, 
  		:media_link, :is_blocked, :category, :content, :deleted, :hidden
  belongs_to :interests
  has_many :comments
  #def initialize(title, date, body)
   # @title = title
    #@body = body
  #end

# get_story is a method that takes a specific story_id as an input  and searches the database for the stroy with this id and returns #this story to the caller

  def self.get_story(story_id)
      return Story.find(story_id)
  end

  
end
