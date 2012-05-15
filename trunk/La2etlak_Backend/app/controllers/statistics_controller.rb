class StatisticsController < ApplicationController
 respond_to :js, :html
 def index
   @interests= Interest.all
 end
 def interests
   @id=params[:id]
   @interest=Interest.find(@id)
 end
 
 def stories
   @id=params[:id]
   @story= Story.find(@id)
 end
 
 def users
   @id=params[:id]
 end
 def all_users
  # @allUsers=User
 end
 def all_stories
 end
 def all_interests
 end
 
#Author : Shafei
 def top_users
	respond_with(@users = User.get_users_ranking)
 end
 
 #Author : Shafei
 def top_stories_30_days
	respond_with(@stories = Story.get_stories_ranking_last_30_days)
 end
 
 #Author : Shafei
 def top_users_all_time
	respond_with(@stories = User.get_stories_ranking_all_time)
 end
end
