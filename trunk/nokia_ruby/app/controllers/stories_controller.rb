class StoriesController < ApplicationController
respond_to :html,:json
 def show
   @comments = Comment.find_all_by_story_id(params[:id]) # get comments of this story
   @story = Story.find(params[:id])
 end
#<<<<<<< .mine

def index
    respond_with(@stories = Story.all)
 end

#=======
 
#>>>>>>> .r169
end
