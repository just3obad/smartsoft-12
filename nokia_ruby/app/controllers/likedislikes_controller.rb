class LikedislikesController < ApplicationController

respond_to :html,:json

#this method is called when a user presses Like on the mobile App
def thumb
  
   @user_id = params[:uid]
    @story_id = params[:sid]
    @action = params[:act] 
   thumped = Likedislike.where(:story_id => @story_id, :user_id => @user_id)
     if thumped.empty?
        Likedislike.create!(:user_id => @user_id, :story_id => @story_id , :action => @action)
       #@story = Story.find(@story_id)
       #@story.likes = @story.likes+1
       #@story.save   
     end
  
end

#def dislike(user_id,story_id)
 #   thumped = Likedislike.where(:stroy_id => story_id, :user_id => user_id)
  #   if thumped.empty?
   #    Likedislike.create!(:user_id => user_id, :story_id => story_id , :action => -1 )
    #   @story = Story.find(story_id)
     #  @story.likes = @story.dislikes+1
      # @story.save
     #end
#end


end
