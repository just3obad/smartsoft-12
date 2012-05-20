# encoding: utf-8
class StoriesController < ApplicationController
  respond_to :html,:json
  require 'net/smtp'

def show
   @comments = Comment.find_all_by_story_id(params[:id]) # get comments of this story
   @story = Story.find(params[:id])

  end
  
  
  
=begin 
Authot : Omar
this method takes a story object as a parameter form social feed and renders its view 
=end

  def get_story_feed
   @ser = params[:serStor]
   @story = Marshal::load(@ser)
   render :layout => "mobile_template"   
  end
  
  # Author: Omar $$ this method taks params story id and get number of likes dislikes done bu user and 
  #renders mobile_template view  likes => method in the model gets the user who thumbed up story 
  # dislikes=> method in model gets user who thumbed down the story
  
  
   def get
	id = params[:id]
	@user = current_user
	@story = Story.find(id)
	@likes = @story.liked
	@dislikes = @story.disliked
	@action1 = @story.check_like(@user)
	@action2 = @story.check_dislike(@user)
  # instance variables for comments
  # Author: Menisy
  @story = Story.find(params[:id])
  @comment = Comment.new
  @user = current_user
  ### end of comment variables ###
	render :layout => "mobile_template"
  #render :temple => "show_comments"
  end
  
# renders the mobile view and initialize
  # the instance variables needed for the 
  # story page, this was used for seperate
  # testing.
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
    upped = comment.up_comment(current_user)
    if upped 
      redirect_to :action => "get" ,:id => params[:id]
    else
      flash[:notice] = "Temporary error has occured red"
      redirect_to :action => "get" ,:id => params[:id]
    end
  end
  
  # action for thumbing down a comment with params passed in POST HTTP request
  # Author: Menisy
  def down_comment
    comment = Comment.find(params[:comment_id])
    downed = comment.down_comment(current_user)
    if downed 
      redirect_to :action => "get" ,:id => params[:id]
    else
      flash[:notice] = "Temporary error has occured red"
      redirect_to :action => "get" ,:id => params[:id]
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
   

##################Begin############
#description: recommend_story_mobile_show is a method that calls the view of the recommendation page with a list of friends email for the user to select one of them or to write down an email on his own and then click the button send which will call the recommend_success_mobile_show method to send the recommendation
#input: story_id
#output: view for a form and a list for the user to select a friend from and write down the message or the email
#Author=> khaled.elbhaey
  def recommend_story_mobile_show()
    @storyid=params[:sid]
    @user=current_user
    @flistemail=@user.get_friends_email()
    render :layout => "mobile_template"
  end
################End##################

##################Begin############
#description: recommend_success_mobile_show is a method to recommend specific story to another friend via email so an email and the recommendation go to that email if the email isnot in the database an invitation shall be sent to him
#input: story_id, inputs of the form(email and message)
#output: a message that tell the user weather the recommendation was successful or not
#Author=> khaled.elbhaey
  def recommend_success_mobile_show()
    @storyid=params[:sid]
    @user=current_user
    @listemail=params[:lemail]
    @friendmail=params[:email]
    @message=params[:message]
    @useremail=@user.email
    @story=Story.get_story(@storyid)
    @storytit=@story.title
    @storybod=@story.content
    @successflag=true
    @username=@user.email.split("@").first
    @interest_id = @story.interest_id
    @interesttit = Interest.get_interest(@interest_id).name
    @logmessage = "the user: " 
    + @username.to_s + " has recommended a story with atitle: " + @storytit.to_s 
    + " in the interest: "+ @interesttit.to_s
    regex = Regexp.new('^(\s*[a-zA-Z0-9\._%-]+@[a-zA-Z0-9\.-]+\.[a-zA-Z]{2,4}\s*([,]{1}[\s]*[a-zA-Z0-9\._%-]+@[a-zA-Z0-9\.]+\.[azA-Z]{2,4}\s*)*)$')


		if @friendmail==""
			if @listemail.nil?
			   @successflag=false
			else
			 Emailer.recommend_story(@useremail, @listemail, @message, @storytit, 				  @storybod).deliver
			 Log.create!(loggingtype: 2,user_id_1: @user.id,user_id_2: nil,admin_id: nil, 
				story_id: @storyid,interest_id: @interest_id,message: @logmessage )

			end
		else
			if(!@friendmail.match(regex))
				   @successflag=false

			else
			  if !@user.has_account(@friendmail)
				    Emailer.invite_to_app(@useremail, @friendmail, @message).deliver
			  else
				    Emailer.recommend_story(@useremail, @friendmail, @message, @storytit,
				    @storybod).deliver
			  end
			Log.create!(loggingtype: 2,user_id_1: @user.id,user_id_2: nil,admin_id: nil, 
				story_id: @storyid,interest_id: @interest_id,message: @logmessage )

			end
		end

    render :layout => "mobile_template"
  end
################End##################

#this method returns friend emails and ids when takes the user id.
def get_friend_id()
 @useremail=params[:email] 
 @id = User.find_by_email(@useremail).id
 render text: @id.to_s
end


##################Begin############
#description: liked_mobile_show is a method to view to the user a list of friends who liked specific story (calls the view of the list)
#input: story_id
#output: a list of names of friends who liked this story
#Author=> khaled.elbhaey
  def liked_mobile_show
    @storyid=params[:id]
    @story=Story.get_story(@storyid)
    @user=current_user
    @friends=@story.view_friends_like(@user)

    flash[:error] = "sorry, you have no friends that liked this story"
    render :layout => "mobile_template" 
  end
################End##################


##################Begin############
#description: disliked_mobile_show is a method to view to the user a list of friends who disliked specific story (calls the view of the list)
#input: story_id
#output: a list of names of friends who disliked this story
#Author=> khaled.elbhaey
  def disliked_mobile_show
    @storyid = params[:id]
    @story=Story.get_story(@storyid)
    @user = current_user
    @friends=@story.view_friends_dislike(@user)

    flash[:error] = "sorry, you have no friends that disliked this story"
    render :layout => "mobile_template" 
  end
################End##################

  #Author: Bassem !!
  def filter
    $hidden = params[:hidden]
    $flagged = params[:flagged]
    $active = params[:active]
    redirect_to "/stories"
    end


end
