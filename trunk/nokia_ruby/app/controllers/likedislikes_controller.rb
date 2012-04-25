class LikedislikesController < ApplicationController

respond_to :html,:json

#this method is called when a user presses thump/up action the mobile App , it adds a new record to Likedislike table with the User_id  and story_id ,, if the user had thumped this story b4 and tried to thump it again the old record is Deleted and a new Record is added.
def like
  
   @user_id = params[:uid]
    @story_id = params[:sid]

  
   thumped = Likedislike.where(:story_id => @story_id, :user_id => @user_id)
     if thumped.empty?
        Likedislike.create!(:user_id => @user_id, :story_id => @story_id , :action => 1)
  respond_to do |format|
    format.json { render json: "Liked Success"  }
 end


    else
     
     Likedislike.find(:first , :conditions => ["user_id = ? AND story_id = ?" ,@user_id , @story_id]).destroy
      Likedislike.create!(:user_id => @user_id, :story_id => @user_id , :action => 1 )
     
respond_to do |format|
    format.json { render json: "Removed and  Liked Success"  }
 end
  
 end

  end
#this method is called when a user presses thump/down action the mobile App , it adds a new record to Likedislike table with the User_id  and story_id ,, if the user had thumped this story b4 and tried to thump it again the old record is Deleted and a new Record is added.

def dislike
     @user_id = params[:uid]
    @story_id = params[:sid]

    thumped = Likedislike.where(:story_id => @story_id, :user_id => @user_id)
     if thumped.empty?
       Likedislike.create!(:user_id => @user_id, :story_id => @story_id , :action => -1 )
       respond_to do |format|
    format.json { render json: "DisLiked Success"  }
 end
    else
      Likedislike.find(:first , :conditions => ["user_id = ? AND story_id = ?" ,@user_id , @story_id]).destroy
      Likedislike.create!(:user_id => @user_id, :story_id => @user_id , :action => -1 )
            respond_to do |format|
    format.json { render json: "Removed And DisLiked Success"  }
 end


     end

end      
end 



