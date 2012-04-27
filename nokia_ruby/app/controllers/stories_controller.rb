class StoriesController < ApplicationController
  respond_to :html,:json
  require 'net/smtp'

  def show
   @comments = Comment.find_all_by_story_id(params[:id]) # get comments of this story
   @story = Story.find(params[:id])

  end
    # show comments of a certain story
    def show_comments
    @comments = Comment.find_all_by_story_id(params[:id]).reverse
    end

    # create a new comment for a certain story
    # parameters are stored in POST HTTP request body
    # that was sent from the mobile client
    def create_comment
     @comment = Comment.new({:content=>params[:content],:user_id=>params[:user_id],:story_id=>params[:id]})

      if @comment.save then

	Log.create!(loggingtype: 2,user_id_1: params[:user_id],user_id_2: nil,admin_id: nil,story_id: params[:id],interest_id: nil,message: (User.find(params[:user_id]).name+" commented on \"" + Story.find(params[:id]).title + "\" with \"" + params[:content]+"\"").to_s ) # adding data to log
        render json: "ok"
      else
        render json: "no"
    end
  end

  # action for thumbing up a comment with params passed in POST HTTP request
  def up_comment
    liked = Upped.find_by_user_id_and_comment_id(params[:user_id],params[:comment_id])
    disliked = Downed.find_by_user_id_and_comment_id(params[:user_id],params[:comment_id])
    if liked.nil? && disliked.nil? then #if user never liked or disliked comment then like it
      Upped.create(:user_id=>params[:user_id],:comment_id=>params[:comment_id]) 

      # adding data to log
      commenterID = Comment.find(params[:comment_id]).user_id  # ID of the commenter
      Log.create!(loggingtype: 2,user_id_1: params[:user_id],user_id_2: commenterID,admin_id: nil,story_id: params[:id],interest_id: nil,message: User.find(params[:user_id]).name+" thumbed UP the comment \"" + Comment.find(params[:comment_id]).content+"\"" + " by " + User.find(commenterID).name )
	render json: "ok" and return true
     # return true
    else if !disliked.nil? then #if user disliked it, now make him like it! 
      Downed.find_by_user_id_and_comment_id(params[:user_id],params[:comment_id]).destroy
      Upped.create(:user_id=>params[:user_id],:comment_id=>params[:comment_id])

# adding data to log
      commenterID = Comment.find(params[:comment_id]).user_id  # ID of the commenter
      Log.create!(loggingtype: 2,user_id_1: params[:user_id],user_id_2: commenterID,admin_id: nil,story_id: params[:id],interest_id: nil,message: User.find(params[:user_id]).name+" thumbed UP the comment \"" + Comment.find(params[:comment_id]).content+"\"" + " by " + User.find(commenterID).name )

     render json: "ok" and return true
    # no extra conditions are needed to be checked for
    else
       render json: "no" and return false
    end
  end 
end

  # action for thumbing down a comment with params passed in POST HTTP request
  def down_comment
    liked = Upped.find_by_user_id_and_comment_id(params[:user_id],params[:comment_id])
    disliked = Downed.find_by_user_id_and_comment_id(params[:user_id],params[:comment_id])
    if liked.nil? && disliked.nil? then #if user never liked or disliked comment then like it
      Downed.create(:user_id=>params[:user_id],:comment_id=>params[:comment_id]) 

# adding data to log
      commenterID = Comment.find(params[:comment_id]).user_id  # ID of the commenter
      Log.create!(loggingtype: 2,user_id_1: params[:user_id],user_id_2: commenterID,admin_id: nil,story_id: params[:id],interest_id: nil,message: User.find(params[:user_id]).name+" thumbed DOWN the comment \"" + Comment.find(params[:comment_id]).content+"\"" + " by " + User.find(commenterID).name )

    render json: "ok" and  return true
    else if !liked.nil?  then #if user liked it, now make him dislike it! 
      Upped.find_by_user_id_and_comment_id(params[:user_id],params[:comment_id]).destroy
      Downed.create(:user_id=>params[:user_id],:comment_id=>params[:comment_id])

# adding data to log
      commenterID = Comment.find(params[:comment_id]).user_id  # ID of the commenter
      Log.create!(loggingtype: 2,user_id_1: params[:user_id],user_id_2: commenterID,admin_id: nil,story_id: params[:id],interest_id: nil,message: User.find(params[:user_id]).name+" thumbed DOWN the comment \"" + Comment.find(params[:comment_id]).content+"\"" + " by " + User.find(commenterID).name )
	render json: "ok" and return true
  
    # no extra conditions are needed to be checked for
    else
	render json: "no" and return false
    
    end
  end 
end

  def index
    respond_with(@stories = Story.all) # passing a list of all stories to the view .
  end

  def new
	@story = Story.new
  end

  def create
   @story = Story.new(params[:title], params[:date], params[:body], params[:rank], params[:deleted], params[:hidden], params[:likes], params[:dislikes], params[:flags], params[:interest])

  end

  def destroy
 
  end


#share_story_social_network is a method to share specific story to  asocial network by clicking the button of share story in the story 
#it depend on the method share_story which take the story id and the account id and return true or false if it returns true then a pop up message will display thet the story published successfully
#if it returned false a pop up message will disply thet an error happend and to try again
 def share_story_social_network
  @storyid=params[:sid]
  @userid=params[:uid]
  flag=User.find_by_id(@userid).share?(@storyid) 

    if flag
   respond_to do |format|
      format.json{render json: "true" }
        end  
    else
      respond_to do |format|
     format.json{render json:"false"}
    end
   end 
#@username = User.find(@userid).name
 #   @storytitle = Story.find(@storyid).title
  #  @interest_id = Story.find(@storyid).interest_id
   # @interesttitle = Interest.find(@interest_id).name
 # @message = "#{@username}recommend_story#{@storytitle}#{@interestitle}"

#Log.create!(loggingtype: 2,user_id_1: @userid,user_id_2:nil,admin_id: nil,story_id: @storyid,interest_id: @interest_id,message: @message )
 end
   

#recommend_story is a method to recommend specific story to another friend by clicking the button of recommend story in the story it depend on the method get_friend_list which return alist of friends of the user that the user will select one of them to recommend the story to if the user has no friends he could be directed to add friends page or the user could write an email and the recommendation go to that email if the email isnot in the database an invitation shall be sent to him3

def recommend_story()
  @storyid=params[:sid]
  @userid=params[:uid]
    @friend=params[:friend]
    @email=params[:email]
    @message=params[:message]


   user2=User.find_by_id(@userid)
   @user2email=user2.email
   @story=Story.find_by_id(@storyid)
   @storytit=@story.title
   @storybod=@story.content

  if @friend=="null"

  if User.find_by_email(@email).nil?
    Emailer.invite_to_app(@user2email, @email, @message).deliver
   else
    Emailer.recommend_story(@user2email, @email, @message, @storytit, @storybod).deliver
  end

 else
   Emailer.recommend_story(@user2email, @friend, @message, @storytit, @storybod).deliver
 end
@username = User.find(@userid).name
    @storytitle = Story.find(@storyid).title
    @interest_id = Story.find(@storyid).interest_id
   @interesttitle = Interest.find(@interest_id).name
  @message = "#{@username}recommend_story#{@storytitle}#{@interestitle}"

Log.create!(loggingtype: 2,user_id_1: @userid,user_id_2: nil,admin_id: nil,story_id: @storyid,interest_id: @interest_id,message: @message )
end

#i seperated get_friends method from the recommend_story method so that no conflict happen when recieving and sending the json file and it return list of friends of the user
def get_friends_email()
 @userid=params[:uid] 

  @flist=Array.new
  @flistemail=Array.new
  @flist = User.find_by_id(@userid).get_Friend_List()

    (0..(@flist.length-1)).each do |i|
    @flistemail << (@flist[i].email)
      end  

    if @flist.empty?
       respond_to do |format|
     format.json{render json:"sorry_you_dont_have_any_friends" }
      end

    else
        respond_to do |format|
     format.json{render json:@flistemail}
      end

 end
@username = User.find(@userid).name
  @message = "#{@username}get_friends_email"

Log.create!(loggingtype: 1,user_id_1: @userid,user_id_2: nil,admin_id: nil,story_id: nil,interest_id: nil,message: @message )
end

#this method returns friend emails and ids when takes the user id.
def get_friends_email_and_id()
 @userid=params[:uid] 

  @flist=Array.new
  @flistemailid=Array.new
  @flist = User.find_by_id(@userid).get_Friend_List()

    (0..(@flist.length-1)).each do |i|
    @flistemailid << (@flist[i].id)
    @flistemailid << (@flist[i].email)
      end  
    if @flist.empty?
     render text: "sorry_you_dont_have_any_friends"
    else
        render text: @flistemailid.to_s
      end

 end



#view_friends_like_dislike is a method to view the friends of the user who liked or disliked a certain story, there will be button in the options tab of the story called view liks/dislikes that will open another page with the names of friends in it

def view_friends_like
  @storyid=params[:sid]
  @userid=params[:uid]
  
  @flistlike=Array.new
  @flistliked=Array.new
  @flistlike = User.find_by_id(@userid).liked() 

   (0..(@flistlike.length-1)).each do |i|
    @flistliked << (@flistlike[i].name)
      end  

   respond_to do |format|
     format.json{render json:@flistliked}
     end
@username = User.find(@userid).name
    @storytitle = Story.find(@storyid).title
    @interest_id = Story.find(@storyid).interest_id
    @interesttitle = Interest.find(@interest_id).name
  @message = "#{@username}recommend_story#{@storytitle}#{@interestitle}"

Log.create!(loggingtype: 2,user_id_1: @userid,user_id_2: nil,admin_id: nil,story_id: @storyid,interest_id: @interest_id,message: @message )
end

def view_friends_dislike
    @storyid=params[:sid]
    @userid=params[:uid]

  @flistdislike=Array.new
  @flistdisliked=Array.new
  @flistdislike = User.find_by_id(@userid).disliked()

   (0..(@flistdislike.length-1)).each do |i|
    @flistdisliked << (@flistdislike[i].name)
      end  

   respond_to do |format|
     format.json{render json:@flistdisliked}
    end
@username = User.find(@userid).name
    @storytitle = Story.find(@storyid).title
    @interest_id = Story.find(@storyid).interest_id
    @interesttitle = Interest.find(@interest_id).name
  @message = "#{@username}recommend_story#{@storytitle}#{@interestitle}"

Log.create!(loggingtype: 2,user_id_1: @userid,user_id_2: nil,admin_id: nil,story_id: @storyid,interest_id: @interest_id,message: @message )
end


end
