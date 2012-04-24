class FlagsController < ApplicationController

respond_to :html,:json

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
