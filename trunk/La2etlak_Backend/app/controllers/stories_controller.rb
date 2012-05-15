class StoriesController < ApplicationController
  respond_to :html,:json
  require 'net/smtp'

def show
   @comments = Comment.find_all_by_story_id(params[:id]) # get comments of this story
   @story = Story.find(params[:id])

  end
  
  # Author: Omar $$ this method taks params story id and get number of likes dislikes done bu user and 
  #renders mobile_template view  likes => method in the model gets the user who thumbed up story 
  # dislikes=> method in model gets user who thumbed down the story
   def get
	id = params[:id]
	user = current_user
	@story = Story.find(id)
	@likes = @story.liked
	@dislikes = @story.disliked
	@action1 = @story.check_like(user)
	@action2 = @story.check_dislike(user)
	render :layout => "mobile_template"
  #render :temple => "show_comments"
  end
  
# renders the mobile view and initialize
  # the instance variables needed for the 
  # story page
  # Author: Menisy
  def mobile_show
    @story = Story.find(params[:id])
    @comment = Comment.new
    @user = current_user
    render :layout => "mobile_template" 
  end

  # action for thumbing up a comment with params passed in POST HTTP request
  # Author: Menisy
  def up_comment
    comment = Comment.find(params[:comment_id])
    user = current_user
    upped = comment.up_comment(user)
    if upped 
      redirect_to :action => "mobile_show" ,:id => params[:id]
    else
      render json: "temporary error occured"
    end
  end
  
  # action for thumbing down a comment with params passed in POST HTTP request
  # Author: Menisy
  def down_comment
    comment = Comment.find(params[:comment_id])
    user = current_user
    downed = comment.down_comment(user)
    if downed 
      redirect_to :action => "mobile_show" ,:id => params[:id]
    else
      render json: "temporary error occured"
    end
  end


  def index
    @interests = Interest.all   
    @stories = Story.filter_stories($hidden,$flagged,$active) # passing a list of all stories to the view .
    if @stories.nil?
      @stories = []
    end
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
   

#recommend_story is a method to recommend specific story to another friend by clicking the button of 
#recommend story in the story it depend on the method get_friend_list which return alist of friends of the
# user that the user will select one of them to recommend the story to if the user has no friends he could be 
#directed to add friends page or the user could write an email and the recommendation go to that email if the email
# isnot in the database an invitation shall be sent to him
#Author=> khaled.elbhaey
  def recommend_story_mobile_show()
    @storyid=params[:sid]
    @user=current_user
    @flistemail=@user.get_friends_email()
'''
@username = User.find(@userid).name
    @storytitle = Story.find(@storyid).title
    @interest_id = Story.find(@storyid).interest_id
   @interesttitle = Interest.find(@interest_id).name
  @message = "#{@username}recommend_story#{@storytitle}#{@interestitle}"

Log.create!(loggingtype: 2,user_id_1: @userid,user_id_2: nil,admin_id: nil,story_id: @storyid,interest_id: @interest_id,message: @message )
  '''
   
	render :layout => "mobile_template"
  end

#Author=> khaled.elbhaey
  def recommend_success_mobile_show()
    @storyid=params[:sid]
    @user=current_user
    @listemail=params[:lemail]
    @friendmail=params[:email]
    @message=params[:message]
    @useremail=@user.email
    @story=Story.find_by_id(@storyid)
    @storytit=@story.title
    @storybod=@story.content
    @successflag=true
    regex = Regexp.new('^(\s*[a-zA-Z0-9\._%-]+@[a-zA-Z0-9\.-]+\.[a-zA-Z]{2,4}\s*([,]{1}[\s]*[a-zA-Z0-9\._%-]+@[a-zA-Z0-9\.]+\.[azA-Z]{2,4}\s*)*)$')
  if @friendmail==""

      Emailer.recommend_story(@useremail, @listemail, @message, @storytit, @storybod).deliver

  else
    if(!@friendmail.match(regex))
       @successflag=false
    else
  
      if !@user.has_account(@friendmail)
        Emailer.invite_to_app(@useremail, @friendmail, @message).deliver
      else
        Emailer.recommend_story(@useremail, @friendmail, @message, @storytit, @storybod).deliver
      end
       end
  end
   render :layout => "mobile_template"
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
    @user = current_user
    @friends=@story.view_friends_like(@user)
    flash[:error] = "sorry, you have no friends you can go to find friend pade to add more"
    render :layout => "mobile_template" 
  end
#Author=> khaled.elbhaey
  def disliked_mobile_show
    @story = Story.find(params[:id])
    @user = current_user
    @friends=@story.view_friends_dislike(@user)
    flash[:error] = "sorry, you have no friends you can go to find friend pade to add more"
    render :layout => "mobile_template" 
  end
  #Author: Bassem !!
  def filter
    $hidden = params[:hidden]
    $flagged = params[:flagged]
    $active = params[:active]
    redirect_to "/stories"
    end


end