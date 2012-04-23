class StoriesController < ApplicationController
  respond_to :html,:json
  require 'net/smtp'

  def show
   @comments = Comment.find_all_by_story_id(params[:id]) # get comments of this story
   @story = Story.find(params[:id])

  end
    # show comments of a certain story
    def show_comments
    respond_with (@comments = Comment.find_all_by_story_id(params[:id]))
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
  # action for thumbing up a comment with params passed in POST HTTP request
  def up_comment
    @liked = Upped.find_by_user_id_and_comment_id(params[:user_id],params[:comment_id])
    @disliked = Downed.find_by_user_id_and_comment_id(params[:user_id],params[:comment_id])
    if @liked.nil? && @disliked.nil? then #if user never liked or disliked comment then like it
      Upped.create(:user_id=>params[:user_id],:comment_id=>params[:comment_id]) 
      return true
    else if !@disliked.nil? then #if user disliked it, now make him like it! 
      
Downed.find_by_user_id_and_comment_id(params[:user_id],params[:comment_id]).destroy
      Upped.create(:user_id=>params[:user_id],:comment_id=>params[:comment_id])
      return true
    # extra condition if he liked it before is not needed since nothing will be done in this case
       return false
    end
  end 
end

  # action for thumbing down a comment with params passed in POST HTTP request
  def down_comment
    @liked = Upped.find_by_user_id_and_comment_id(params[:user_id],params[:comment_id])
    @disliked = Downed.find_by_user_id_and_comment_id(params[:user_id],params[:comment_id])
    if @liked.nil? && @disliked.nil? then #if user never liked or disliked comment then like it
      Downed.create(:user_id=>params[:user_id],:comment_id=>params[:comment_id]) 
      return true
    else if !@liked.nil?  then #if user liked it, now make him dislike it! 
      
Upped.find_by_user_id_and_comment_id(params[:user_id],params[:comment_id]).destroy
      Downed.create(:user_id=>params[:user_id],:comment_id=>params[:comment_id])
      return true
    # extra condition if he liked it before is not needed since nothing will be done in this case
       return false
    end
  end 
end

  def index
    respond_with(@stories = Story.all)
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
  flag=share_Story self.id, user.id 

  respond_to do |format|
    if flag
      format.json { message :story_published_successfuly }
    else
     format.json { message  :an_error_happend_plz_try_again_later }
    end
  end
 end
   

#recommend_story is a method to recommend specific story to another friend by clicking the button of recommend story in the story it depend on the method get_friend_list which return alist of friends of the user that the user will select one of them to recommend the story to if the user has no friends he could be directed to add friends page 

def recommend_story
  @flist=Array.new
  @flist=user.get_Friend_List() 

   

  respond_to do |mess|
    if @flist.Empty
      mess.json { message :sorry_you_dont_have_any_friends }
    else
        list.json { render json: @flist}
  end
 end

  respond_from do |format|
    parsed_json = ActiveSupport::JSON.decode(format)
    email=parsed_json[email]
    message=parsed_json[message]
   end

 userlist=Array.new
 userlist=User.where(:user_mail => email)

  if userlist.Empty
    Net::SMTP.start( smtp.gmail.com, 25) do |smtp|
    smtp.send_message "invitation to gaheem application", user.email, [email]
     end

   else
    self.show email, message
  end

end


#view_friends_like_dislike is a method to view the friends of the user who liked or disliked a certain story, there will be button in the options tab of the story called view liks/dislikes that will open another page with the names of friends in it

def view_friends_like_dislike()

  @flistlike=Array.new
  @flistdislike=Array.new
  
   @flistlike=extractFriends( liked self.id )
   @flistdislike=extractFriends( disliked self.id )

  respond_to do |listlike|
    listlike.json { render json: @flistlike, status: :liked}
   end
  
  respond_to do |listdislike|
    listdislike.json { render json: @flistdislike, status: :dislike}
   end
  
end

end
