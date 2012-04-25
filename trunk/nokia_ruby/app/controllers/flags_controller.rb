class FlagsController < ApplicationController

respond_to :html,:json

#this method is called when a  user tries to Flag a story on the mobile it checks if the user has flagged the story before ,, if not a new record is added to the flags table with the flag the user did to this story. 

def flag
@story_id = params[:sid]
@user_id = params[:uid]
   f = false
   thumped = Flag.where(:story_id => @story_id, :user_id => @user_id)
     if thumped.empty?
        Flag.create!(:user_id =>  @user_id, :story_id => @story_id)
      # @story = Story.find(story_id)
      # @story.flags = @story.flags+1
      # @story.save
       f = true
     end
respond_to do |format|
    format.json { render json: f   }
 end


end

end
