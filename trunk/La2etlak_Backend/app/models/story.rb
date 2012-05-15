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
  has_many :likedislikers, :class_name => "User", :through => :likedislikes
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

  #Author: Lydia
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
  
  #Author: Lydia
   '''
   This method returns the difference between today and the day
  the story was created in in days so that the graph will know the day it should 
  start with.
  There are several cases:
  -case 1: if the story is hidden and it was created before the last 30 days
  but hidden within the last 30 days OR if the story was not hidden but created
  before the last 30 days
  -case 2: if the story was hidden and it was created before the last 30 days
  and hidden before the last 30 days
  -case 3: if the story was not hidden and it was created within the last 30 days
  '''
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
  
  #Author: Lydia
  '''
  This method returns a list of users who liked a certain story
  '''
  def liked
    likes = likedislikes.where(action: 1)
    likers = []
    likes.find_each do |like|
      likers << likedislikers.find_by_id(like.user_id)
    end
    return likers
  end
  
  
  #Author: Lydia
  '''
  This method returns a list of users who disliked a certain story
  '''
  def disliked
    dislikes = likedislikes.where(action: -1)
    dislikers = []
    dislikes.find_each do |dislike|
      dislikers << likedislikers.find_by_id(dislike.user_id)
    end
    return dislikers
  end
  
#Author : Shafei
  def get_story_rank_all_time
	comments_on_story 	= Comment.where(:story_id => self.id)
	shares_of_story 	= Share.where(:story_id => self.id)
	dislikes_of_story	= Likedislike.where(:story_id => self.id, :action => -1)
	likes_of_story 		= Likedislike.where(:story_id => self.id, :action => 1)
	flags_of_story 		= Flag.where(:story_id => self.id)
	sum 				= comments_on_story.length + (shares_of_story.length * 5) + (likes_of_story.length * 2) - 
						(dislikes_of_story.length * 2) - (flags_of_story.length * 5)
	return sum
  end
  
#Author : Shafei
  def get_story_rank_last_30_days
	comments_on_story 	= Comment.where(:created_at => 30.days.ago..Time.zone.now.end_of_day, :story_id => self.id)
	shares_of_story 	= Share.where(:created_at => 30.days.ago..Time.zone.now.end_of_day, :story_id => self.id)
	dislikes_of_story	= Likedislike.where(:created_at => 30.days.ago..Time.zone.now.end_of_day, :story_id => self.id, :action => -1)
	likes_of_story 		= Likedislike.where(:created_at => 30.days.ago..Time.zone.now.end_of_day, :story_id => self.id, :action => 1)
	flags_of_story 		= Flag.where(:created_at => 30.days.ago..Time.zone.now.end_of_day, :story_id => self.id)
	sum 				= comments_on_story.length + (shares_of_story.length * 5) + (likes_of_story.length * 2) - 
						(dislikes_of_story.length * 2) - (flags_of_story.length * 5)
	return sum
  end

#Author : Shafei
  def self.get_stories_ranking_all_time
		all_stories 		= Array.try_convert(Story.all)
		if all_stories.empty? == true
			return all_stories
		end
		rank 			= Array.new
		id_array		= Array.new
		max				= 0
		id				= 999999
		all_stories.each do |story|
			all_stories.each do |story|
				if story.get_story_rank_all_time >= max && (id_array.include?(story.id) == false)
					max = story.get_story_rank_all_time
					id = story.id
				end
			end
			max = 0
			id_array << id
			rank << all_stories[id-1]
		end
		return rank
  end
  
#Author : Shafei
  def self.get_stories_ranking_last_30_days
	all_stories 		= Array.try_convert(Story.all)
		if all_stories.empty? == true
			return all_stories
		end
		rank 			= Array.new
		id_array		= Array.new
		max				= 0
		id				= 999999
		all_stories.each do |story|
			all_stories.each do |story|
				if story.get_story_rank_last_30_days >= max && (id_array.include?(story.id) == false)
					max = story.get_story_rank_last_30_days
					id = story.id
				end
			end
			max = 0
			id_array << id
			rank << all_stories[id-1]
		end
		return rank
  end

#view_friends_like is a method that return a list of friends emails that liked the story
#Author: khaled.elbhaey
def view_friends_like(user)
  @user=user
  @flistliked=Array.new
  @listlike = self.liked() 
  @flistlike=@user.extract_friends(@listlike)

   (0..(@flistlike.length-1)).each do |i|
    @flistliked << (@flistlike[i].email)
      end  

   return @flistliked

   ''' @username = User.find(@userid).name
    @storytitle = Story.find(@storyid).title
    @interest_id = Story.find(@storyid).interest_id
    @interesttitle = Interest.find(@interest_id).name
    @message = "#{@username}recommend_story#{@storytitle}#{@interestitle}"

    Log.create!(loggingtype: 2,user_id_1: @userid,user_id_2: nil,admin_id: nil,story_id: @storyid,interest_id: @interest_id,message: @message )
'''
end


#view_friends_dislike is a method that return a list of friends emails that disliked the story
#Author: khaled.elbhaey
def view_friends_dislike(user)
  @user=user
  @flistdisliked=Array.new
  @listdislike = self.liked() 
  @flistdislike=@user.extract_friends(@listdislike)

   (0..(@flistdislike.length-1)).each do |i|
    @flistdisliked << (@flistdislike[i].email)
      end  

   return @flistdisliked

   ''' @username = User.find(@userid).name
    @storytitle = Story.find(@storyid).title
    @interest_id = Story.find(@storyid).interest_id
    @interesttitle = Interest.find(@interest_id).name
    @message = "#{@username}recommend_story#{@storytitle}#{@interestitle}"

    Log.create!(loggingtype: 2,user_id_1: @userid,user_id_2: nil,admin_id: nil,story_id: @storyid,interest_id: @interest_id,message: @message )
'''
end
  #Author : Kareem
 def check_like(user)
   action = Likedislike.find(:all , :conditions => ["story_id = ? AND user_id = ? AND action = ?", self.id, user.id , "1"])
 end
 #Author : Kareem
 def check_dislike(user)
    action = Likedislike.find(:all , :conditions => ["story_id = ? AND user_id = ? AND action = ?", self.id, user.id , "-1"])
 end
 
 #Author: BASSEM
 #This method checks the three booleans (hidden, flagged, checked) and queries the corresponding stories form the database
 #The hidden boolean gets all the hidden stories form the database
 #The active boolean gets all the stories that are not hidden nor deleted from the database
 #The Flagged boolean loops in the table "Flag" and adds whatever stories it finds by taking its id and fetching it from
 #table "Story"
 #All of these conditions append a sublist of stories to the array "stories" and then returns an array of unique stories.
 def self.filter_stories(hidden,flagged,active)
  stories = []
  stories3=[]
   if hidden
    stories1 =  Story.where("hidden = ? ", true)
    stories= stories + stories1
  end
    if active
    stories2 = Story.where("hidden = ? AND deleted = ?", false, false)
    stories= stories + stories2

  end
  if flagged
    Flag.find_each do |flag|
      stories3 << Story.where(:id => flag.story_id)
    end
    stories= stories + stories3

  end 
  return stories.uniq
 end

end
