class StatisticsController < ApplicationController
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
end
