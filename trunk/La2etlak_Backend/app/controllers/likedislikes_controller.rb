class LikedislikesController < ApplicationController

respond_to :html,:json

#this Action is called when the User Hits the Thumb up/down button in the Story View  and calls the thumb_story action with the Story or the Action user made.
#user => current logged in user
#story => the Story which the user View

def thumb
	action = params[:act]  
	story_id = params[:sid]
	user = current_user
	story = Story.find(story_id)
	user.thumb_story(story,action)
        redirect_to :controller => "stories", :action => "get" , :id => story_id 

  end
end 



