class StatisticsController < ApplicationController
 layout 'statisticsPage'
 def index
   @interests= Interest.all
 end
 def interests
   @id=params[:id]
 end
 def stories
   @id=params[:id]
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
