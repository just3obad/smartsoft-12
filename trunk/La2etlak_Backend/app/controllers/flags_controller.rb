class FlagsController < ApplicationController

respond_to :html,:json

#this Action is called when a User Hits the Flag button in the Story view it calls the flag_story with the current story as parametar.
#story => currently viewed story

#Author : Kareem

   def flag

	story_id = params[:sid]
  	story = Story.find(story_id)
  	user = current_user
  	user.flag_story(story)

   end

end
