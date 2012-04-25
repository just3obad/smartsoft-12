class UsersController < ApplicationController
	respond_to :html,:json


#this method Passes a list of Interests according to the user_id to getStories method which should return list of stories according to these Interests and it converts it to a json file.

def feed
 @id=params[:id]
 @interests = UserAddInterest.find(:all , :conditions => ["user_id = ?" , @id ] , :select => "interest_id").map {|interest| interest.interest_id}  
 @stories_list = get_stories(@interests)
respond_to do |format|
    format.json { render json: @stories_list }
 end
 end


#a method that takes list of interest_ids and returns list of stories related to those interests
  def get_stories(interests)
#create new array to return the stories in
  @stories=Array.new
#loop for each interest and queries the database to return all the stories which belongs to this specific interest and append them to the stories list.
  interests.each do |interest|
	 @stories += Story.find_all_by_interest_id(interest)
  end
#return the list after looping on all the entered interests
  return @stories
  end



 def show
    @user = User.find(params[:id])

    respond_to do |format|
      format.html #show.html.erb
      format.json { render json: @user }
    end
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
    @user.haccount = Haccount.new(:email => @user.email, 
                                  :password=>params[:password], :user_id => @user.id)
    respond_to do |format|
      if @user.save
    #    format.html { redirect_to @user, notice: 'User was successfully created.' }
        format.json { render json: @user, status: :created }
      else
    #    format.html { render action: "new" }
        format.json { render json: @user.errors, status: :unprocessable_entity }
      end
    end
  end

 # GET /users/1/edit
  def edit
    @user = User.find(params[:id])
  end

 # PUT /users/1
  # PUT /users/1.json
#this method takes user name email and password and his basic info and updates the current info
  def profile
    @user = User.find(params[:id])

    respond_to do |format|
      if @user.update_attributes(params[:name=>params[:name],:email=>params[:email],
                    :first_name=>params[:first_name], :last_name=>params[:last_name],
                    :date_of_birth=>params[:date_of_birth]])
        redirect_to :controller => "h_accounts", :action => 'update', :email=>params[:email],
        :password=>params[:password]
        format.html { redirect_to @user, notice: 'User was successfully updated.' }
        format.json { head :no_content }
      else
        format.html { render action: "edit" }
        format.json { render json: @user.errors, status: :unprocessable_entity }
      end
    end
  end

  def index
    respond_with(@users = User.all)
  end
 #this method blocks interests from the user's feed by changing the is_blocked attribute to true. The method depends on another method that gets the list of stories under these interests.
   
  def block_interest(story_id)
  @interest_id = Story.find(:first, :condtions => { :id => story_id},:select => "interest_id")
  @interests = Interest.find(:first, :conditions => {:id => @interest_id})
    #@story_list = getStories(@user_interests)
    0.upto(@story_list.length) do |i|
       @story_list[i].is_blocked = true
    end
  end

  #this method takes a story id as input and blocks its story by setting the is_blocked attribute to true.

  
  def block_story(story_id)
   @story = Story.find(:first, :conditions => { :id => story_id})
    if (@story != nil)
    @story.is_blocked = true
    respond_with("story is blocked successfully")
    else 
    respond_with("story is not found")
    end
  end

  #this method blocks feeds from this friend by setting is_blocked attribute to true. The method depends on another method that gets the stories belonging to a friend.

  def block_friend_feed(user_id)
    user_id = params[:id]
    #@friend_stories = getFriendsStories()
    0.upto(@friend_stories.length) do |i|
       @friend_stories[i].is_blocked = true
    end
  end

#this method gets the stories of a friend through method getFriendsStories and converts them to a json file.

def friends_feed(user_id)
 user_id = params[:id]
#@friend_stories = getFriendsStories()
  respond_with(@friend_stories)
end
end

end

