class FlagsController < ApplicationController

respond_to :html,:json

#this Action is called when a User Hits the Flag button in the Story view it calls the flag_story with the current story as parametar.
#story => currently viewed story

#Author : Kareem

   def flag

	story_id = params[:sid]
  	story = Story.find(story_id)
  	user = current_user
  	result = user.flag_story(story)
  	if(result == true)
	  flash[:notice] = "Story Flagged Succesfully"
	  redirect_to :controller => "stories", :action => "get" , :id => story_id
	   else
	  flash[:notice] = "You Already Flagged this Story"
	  redirect_to :controller => "stories", :action => "get" , :id => story_id 
	end

   end

end
