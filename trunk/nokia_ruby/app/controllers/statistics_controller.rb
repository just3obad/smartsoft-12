class StatisticsController < ApplicationController
 def index
   @interests= Interest.all
 end
 def interests
   @id=params[:id]
 end
 def stories
   @id=params[:id]
   @story= Story.find(@id)
   #The following query is done because the associations between the tables 
   #are missing right now
   @interest_id= @story.interest_id 
   @interest = Interest.find(@interest_id)
   @interest_name = @interest.name
   @shares = Share.where(:story_id => @id)
   @likes = Likedislike.where(:story_id => @id, :action => 1)
   @dislikes = Likedislike.where(:story_id => @id, :action => -1)
   @flags = Flag.where(:story_id => @id)
   @creation_date = @story.created_at.beginning_of_day
   @last_update = @story.updated_at.end_of_day
   @hidden = @story.hidden_before_type_cast
   @total_shares = @shares.count
   @total_likes = @likes.count
   @total_dislikes = @dislikes.count
   @total_flags = @flags.count
 end
 def users
   @id=params[:id]
 end
 def all_users
 end
 def all_stories
 end
 def all_interests
 end
end
