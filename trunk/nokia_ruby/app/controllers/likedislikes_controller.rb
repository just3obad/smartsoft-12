class LikedislikesController < ApplicationController

respond_to :html,:json

#this method is called when a user presses Like on the mobile App
def like
  
   @user_id = params[:uid]
    @story_id = params[:sid]

  
   thumped = Likedislike.where(:story_id => @story_id, :user_id => @user_id)
     if thumped.empty?
        Likedislike.create!(:user_id => @user_id, :story_id => @story_id , :action => 1)
  


    else
     
     Likedislike.find(:first , :conditions => ["user_id = ? AND story_id = ?" ,@user_id , @story_id]).destroy
      Likedislike.create!(:user_id => @user_id, :story_id => @user_id , :action => 1 )
     

  
 end

  end


def dislike
     @user_id = params[:uid]
    @story_id = params[:sid]

    thumped = Likedislike.where(:story_id => @story_id, :user_id => @user_id)
     if thumped.empty?
       Likedislike.create!(:user_id => @user_id, :story_id => @story_id , :action => -1 )
 
    else
      Likedislike.find(:first , :conditions => ["user_id = ? AND story_id = ?" ,@user_id , @story_id]).destroy
      Likedislike.create!(:user_id => @user_id, :story_id => @user_id , :action => -1 )
  
     end

end      
end 



