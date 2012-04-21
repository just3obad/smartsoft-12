class FlagsController < ApplicationController

respond_to :html,:json

def flag(story_id,user_id)
  thumped = Flag.where(:stroy_id => story_id, :user_id => user_id)
     if thumped.nil?
        Flag.create!(:user_id => user_id, :story_id => story_id)
       @story = Story.find(story_id)
       @story.flags = @story.flags+1
       @story.save   
     end
end
