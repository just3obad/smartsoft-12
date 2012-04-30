class StatisticsController < ApplicationController
 def index
   @interests= Interest.all
 end
 def interests
   @id=params[:id]
 end
 
 '''
 Here I am setting instanct variables that are needed in the view of the stories
 statistics page:
 -The id of the current story
 -The stoty itself
 -The interest that is related to this story
 -The shares that belongs to the story
 -The likes that belongs to the story
 -The dislikes that belongs to the story
 -The flags that belongs to the story
 -The creation date of the story
 -The date of the last update that happend to the story which I use to get the
  date of the hiding of the story if it was hidden
 -Using @hidden to check if the story was hidden
 -The total number of shares that belong to this story
 -The total number of likes that belong to this story
 -The total number of dislikes that belong to this story
 -The total number of flags that belong to this story
 '''
 def stories
   @id=params[:id]
   @story= Story.find(@id)
   @interest = Interest.find(@story.interest_id)
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
