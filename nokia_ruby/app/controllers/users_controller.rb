class UsersController < ApplicationController
	respond_to :html,:json

 def show
    respond_with(@user = User.find(params[:id]))
  end  

 
     def new
    @user = User.new

    respond_to do |format|
      format.html # new.html.erb
      format.json { render json: @user }
   end
  end
def create
    @user = User.new(params[:user])

    respond_to do |format|
      if @user.save
        format.html { redirect_to @user, notice: 'User was successfully created.' }
        format.json { render json: @user, status: :created, location: @user }
      else
        format.html { render action: "new" }
        format.json { render json: @user.errors, status: :unprocessable_entity }
      end
    end
  end

  def index
    respond_with(@users = User.all)
  end
 #this method takes an array of interests and blocks these interests   from the user's feed by changing the is_blocked attribute to true. The method depends on another method that gets the list of stories under these interests.
  
  #@story_list = getStories(self.interests) 
  def block_interest(@story_list)
    0.upto(@story_list.length) do |i|
       @story_list[i].is_blocked = true
    end
  end

  #this method takes a story as input and blocks it by setting the is_blocked attribute to true.

  @story = Story.find(params[:id])
  def block_story(@story)
    @story.is_blocked = true
  end
end
