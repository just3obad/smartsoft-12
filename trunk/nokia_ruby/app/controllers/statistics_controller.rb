class StatisticsController < ApplicationController
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
end
