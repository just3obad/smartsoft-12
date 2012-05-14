class LikedislikesController < ApplicationController

respond_to :html,:json

#this method is called when a user presses thump/up action the mobile App , it adds a new record to Likedislike table with the User_id  and story_id ,, if the user had thumped this story b4 and tried to thump it again the old record is Deleted and a new Record is added.
def thumb
	action = params[:action]  
	story_id = params[:sid]
	user = current_user
	story = Story.find(story_id)
	user.thumb_story(story,action)

  end
end 



