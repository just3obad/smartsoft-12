class StoriesController < ApplicationController
respond_to :html,:json
  def show
   @comments = Comment.find_all_by_story_id(params[:id]) # get comments of this story
   @story = Story.find(params[:id])

  end
    # show comments of a certain story
    def show_comments
    @comments = Comment.find_all_by_story_id(params[:id])
    end

    # create a new comment for a certain story
    # parameters are stored in POST HTTP request body
    # that was sent from the mobile client
    def create_comment
     @comment = Comment.new({:content=>params[:content],:user_id=>params[:user_id],:story_id=>params[:id]})

    respond_to do |format|
      if @comment.save
        format.html { redirect_to @comment, notice: 'Comment success' }
        format.json { render json: @comment, status: :created, location: @comment }
      else
        format.html { render action: "new" }
        format.json { render json: @comment.errors, status: :unprocessable_entity }
      end
    end
  end

  def index
    respond_with(@stories = Story.all)
  end

  def new

  end

  def create

  end

  def destroy
 
  end

# get_story is a method that takes a specific story_id as an input  and searches the database for the stroy with this id and returns #this story to the caller

  def get_story(story_id)
  return Story.find(params[story_id])
  end

#share_story_social_network is a method to share specific story to  asocial network by clicking the button of share story in the story 
#it depend on the method share_story which take the story id and the account id and return true or false if it returns true then a pop up message will display thet the story published successfully
#if it returned false a pop up message will disply thet an error happend and to try again
 def share_story_social_network
  flag=share_Story self.id, user.id 

  respond_to do |format|
    if flag
      format.json { message :story_published_successfuly }
    else
     format.json { message  :an_error_happend_plz_try_again_later }
    end
  end
 end
   

#recommend_story is a method to recommend specific story to another friend by clicking the button of recommend story in the story 
#it depend on the method get_friend_list which return alist of friends of the user that the user will select one of them to recommend the story to 
#if the user has no friends he could be directed to add friends page
def recommend_story
  flist=Array.new
  flist=user.get_Friend_List() 

  respond_to do |list|
    list.json { render json: flist}
   end

  respond_to do |mess|
    if flist.Empty
  mess.json { message :sorry_you_dont_have_any_friends }
  end
 end

  respond_from do |format|
    parsed_json = ActiveSupport::JSON.decode(format)
    email=parsed_json[email]
    message=parsed_json[message]
   end
  
  self.show email, message
end

#view_friends_like_dislike is a method to view the friends of the user who liked or disliked a certain
#story, there will be button in the options tab of the story called view liks/dislikes that will open another 
#page with the names of friends in it
def view_friends_like_dislike()

  flistlike=Array.new
  flistdislike=Array.new
  
   flistlike=extractFriends( liked self.id )
   flistdislike=extractFriends( disliked self.id )

  respond_to do |listlike|
    listlike.json { render json: flistlike}
   end
  
  respond_to do |listdislike|
    listdislike.json { render json: flistdislike}
   end
  
end

end
