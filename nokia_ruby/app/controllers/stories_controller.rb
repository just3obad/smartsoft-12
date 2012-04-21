class StoriesController < ApplicationController
respond_to :html,:json
  def show
   @comments = Comment.find_all_by_story_id(params[:id]) # get comments of this story
   @story = Story.find(params[:id])
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

#share_story_social_network is a method to share specific story to  asocial network by clicking the button of share story in the story 
#it depend on the method share_story which take the story id and the account id and return true or false if it returns true then a pop up message will display thet the story published successfully
#if it returned false a pop up message will disply thet an error happend and to try again
 def share_story_social_network
  @flag=share_Story self.id, user.id 

  respond_to do |format|
    if @flag
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
  @flist=Array.new
  @flist=user.get_Friend_List() 

  respond_to do |list|
    list.json { render json: @flist}
   end

  respond_to do |mess|
    if @flist.Empty
  mess.json { message :sorry_you_dont_have_any_friends }
  end

  respond_from do |format|
    parsed_json = ActiveSupport::JSON.decode(format)
    @email=parsed_json[email]
    @message=parsed_json[message]
   end
  
  self.show @email, @message
end


end
end
