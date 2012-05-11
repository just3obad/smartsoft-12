class Story < ActiveRecord::Base

include StoriesHelper
#definition of some attributes:-
# :rank==>hottness of a story, :interest_id==>id of the related interest,
# :type==> 1 (Article) 2 (Image) 3 (video)
  attr_accessible :interest_id, :title, :date, :rank, 
		  :media_link, :category, :content, :deleted, :hidden
  belongs_to :interest
  has_many   :comments
  
  
  has_many :shares
  has_many :sharers, :class_name => "User", :through => :shares
  has_many :likedislikes
  has_many :likers, :class_name => "User", :through => :likedislikes, :conditions => "action = '1'"
  has_many :dislikers, :class_name => "User",:through => :likedislikes, :conditions => "action = '-1'"
  has_many :flags
  has_many :flagers, :class_name => "User", :through => :flags
  has_many :block_stories
  has_many :blockers, :class_name => "User", :through => :block_stories 
  
  #def initialize(title, date, body)
   # @title = title
    #@body = body
  #end

  URL_regex = /^(?:(?:http|https):\/\/[a-z0-9]+(?:[\-\.]{1}[a-z0-9]+)*\.[a-z]{2,6}(?::[0-9]{1,5})?(\/.*)?)|(?:^$)$/ix
# putting some validations on the title and interest_id that they are present
  validates :title , :presence=>true
  validates :interest_id, :presence=>true
# checking that the media_link is a valid URL according to the regex defined above.
  validates :media_link, :format=> {:with => URL_regex}
			 
# get_story is a method that takes a specific story_id as an input  and searches the database for the stroy with this id and returns #this story to the caller

  def get_story(story_id)
      return Story.find(story_id)
  end

  ''' 
  This is the method that should return the data of statistics of a story
  with this format first element in the data arrays is ARRAY OF "No Of Shares",
  second one is "No Of Likes",
  third one is "No of Dislikes",
  forth one is "No of Flags",
  and fifth one is "No of Comments"
  '''
  def get_story_stat
    creation_date = created_at.beginning_of_day
    last_update = updated_at.end_of_day
   
    share = get_no_of_activity(shares, creation_date, last_update, hidden)
    like = get_no_of_activity(likedislikes.where(:action => 1) , creation_date, last_update, hidden)
    dislike = get_no_of_activity(likedislikes.where(:action => -1), creation_date, last_update, hidden)
    flag = get_no_of_activity(flags, creation_date, last_update, hidden)
    comment = get_no_of_activity(comments, creation_date, last_update, hidden)
    data = "[#{share},#{like},#{dislike},#{flag},#{comment}]"
  end
  
  def get_story_start_date
    creation_date = created_at.beginning_of_day
    last_update = updated_at.end_of_day  
    
    if (hidden && creation_date.to_date < 30.days.ago.to_date && 
          last_update.to_date >= 30.days.ago.to_date) ||
          (creation_date.to_date < 30.days.ago.to_date)
            date = Time.zone.now.to_date - 30.days.ago.to_date
    
    elsif hidden && creation_date.to_date < 30.days.ago.to_date && 
          last_update.to_date < 30.days.ago.to_date
            date = -1
            
    else
            date = Time.zone.now.to_date - creation_date.to_date
    end
  end
  
end
