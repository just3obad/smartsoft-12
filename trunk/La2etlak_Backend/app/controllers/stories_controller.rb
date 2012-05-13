class StoriesController < ApplicationController
  respond_to :html,:json
  require 'net/smtp'

def show
   @comments = Comment.find_all_by_story_id(params[:id]) # get comments of this story
   @story = Story.find(params[:id])

  end
  
  # Author: Omar $$ this method taks params story id and get number of likes dislikes done bu user and renders mobile_template view  likes => method in the model gets the user who thumbed up story  dislikes=> method in model gets user who thumbed down the story
   def get
	id = params[:id]
	@story = Story.find(id)
	@likes = @story.liked
	@dislikes = @story.disliked
	@action1 = @story.check_like
	@action2 = @story.check_dislike
	render :layout => "mobile_template"
  end
  
# renders the mobile view and initialize
  # the instance variables needed for the 
  # story page
  # Author: Menisy
  def mobile_show
    @story = Story.find(params[:id])
    @comment = Comment.new
    @user = User.find(params[:id2])
    render :layout => "mobile_template" 
  end
  
  
  # shows comments of a certain story
  # Should be deprecated
  # Author: Menisy
  def show_comments
    @story = Story.find(params[:id])
    @user = User.find(1)
    @comment = Comment.new
    @comments = Comment.find_all_by_story_id(params[:id]).reverse
  end

  # action for thumbing up a comment with params passed in POST HTTP request
  # Author: Menisy
  def up_comment
    comment = Comment.find(params[:comment_id])
    user = User.find(params[:user_id])
    upped = comment.up_comment(user)
    if upped 
      redirect_to :action => "mobile_show" ,:id2 => params[:user_id],:id => params[:id]
    else
      render json: "temporary error occured"
    end
  end
  
  # action for thumbing down a comment with params passed in POST HTTP request
  # Author: Menisy
  def down_comment
    comment = Comment.find(params[:comment_id])
    user = User.find(params[:user_id])
    downed = comment.down_comment(user)
    if downed 
      redirect_to :action => "mobile_show" ,:id2 => params[:user_id],:id => params[:id]
    else
      render json: "temporary error occured"
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
   

#recommend_story is a method to recommend specific story to another friend by clicking the button of recommend story in the story it depend on the method get_friend_list which return alist of friends of the user that the user will select one of them to recommend the story to if the user has no friends he could be directed to add friends page or the user could write an email and the recommendation go to that email if the email isnot in the database an invitation shall be sent to him
#Author=> khaled.elbhaey
def recommend_story_mobile_show()
  @storyid=params[:sid]
  @userid=params[:uid]
    @friend=params[:friend]
    @email=params[:email]
    @message=params[:message]


   user=User.find_by_id(@userid)
   @useremail=user.email
   @story=Story.find_by_id(@storyid)
   @storytit=@story.title
   @storybod=@story.content

  if @friend=="null"

  if User.find_by_email(@email).nil?
    Emailer.invite_to_app(@useremail, @email, @message).deliver
   else
    Emailer.recommend_story(@useremail, @email, @message, @storytit, @storybod).deliver
  end

 else
   Emailer.recommend_story(@useremail, @friend, @message, @storytit, @storybod).deliver
 end
'''
@username = User.find(@userid).name
    @storytitle = Story.find(@storyid).title
    @interest_id = Story.find(@storyid).interest_id
   @interesttitle = Interest.find(@interest_id).name
  @message = "#{@username}recommend_story#{@storytitle}#{@interestitle}"

Log.create!(loggingtype: 2,user_id_1: @userid,user_id_2: nil,admin_id: nil,story_id: @storyid,interest_id: @interest_id,message: @message )
  '''
  end



#this method returns friend emails and ids when takes the user id.
def get_friend_id()
 @useremail=params[:email] 
 @id = User.find_by_email(@useremail).id
 render text: @id.to_s
end

#Author=> khaled.elbhaey
  def liked_mobile_show
    @story = Story.find(params[:id])
    @user = User.find(params[:id2])
    @friends=@story.view_friends_like(@user)
    flash[:error] = "sorry, you have no friends you can go to find friend pade to add more"
    render :layout => "mobile_template" 
  end
#Author=> khaled.elbhaey
  def disliked_mobile_show
    @story = Story.find(params[:id])
    @user = User.find(params[:id2])
    @friends=@story.view_friends_dislike(@user)
    flash[:error] = "sorry, you have no friends you can go to find friend pade to add more"
 render :layout => "mobile_template" 
  end


end
