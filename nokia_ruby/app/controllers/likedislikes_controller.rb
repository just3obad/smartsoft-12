class LikedislikesController < ApplicationController

respond_to :html,:json

#this method is called when a user presses Like on the mobile App
def like(user_id,story_id)  
   thumped = Likedislike.where(:stroy_id => story_id, :user_id => user_id)
     if thumped.nil?
        Likedislike.create!(:user_id => user_id, :story_id => story_id , :action => 1)
       @story = Story.find(story_id)
       @story.likes = @story.likes+1
       @story.save   
     end
  
end

def dislike(user_id,story_id)
    thumped = Likedislike.where(:stroy_id => story_id, :user_id => user_id)
     if thumped.nil?
       Likedislike.create!(:user_id => user_id, :story_id => story_id , :action => -1 )
       @story = Story.find(story_id)
       @story.likes = @story.dislikes+1
       @story.save
     end
end


end
