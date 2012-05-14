class FlagsController < ApplicationController

respond_to :html,:json

#this method is called when a  user tries to Flag a story on the mobile it checks if the user has flagged the story before ,, if not a new record is added to the flags table with the flag the user did to this story. 

   def flag

	story_id = params[:sid]
  	story = Story.find(story_id)
  	user = current_user
  	user.flag_story(story)

   end

end
