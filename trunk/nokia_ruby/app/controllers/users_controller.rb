class UsersController < ApplicationController
	respond_to :html,:json


#this method Passes a list of Interests according to the user_id to getStories method which should return list of stories according to these Interests and it converts it to a json file.

def feed
 @id=params[:id]
 @interests = UserAddInterest.find(:all , :conditions => ["user_id = ?" , @id ] , :select => "interest_id").map {|interest| interest.interest_id}  
# @stories_list = getStories(@interests)
respond_to do |format|
    format.json { render json: @interests }
 end
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
    @user.haccount = Haccount.new(:email => @user.email, :password=>params[:password], :user_id => @user.id)
    respond_to do |format|
    #  if @user.save
        format.html { redirect_to @user, notice: 'User was successfully created.' }
    #    format.json { render json: @user, status: :created, location: @user }
    #  else
    #    format.html { render action: "new" }
    #    format.json { render json: @user.errors, status: :unprocessable_entity }
      end
    #end
  end

 # GET /users/1/edit
  def edit
    @user = User.find(params[:id])
  end

 # PUT /users/1
  # PUT /users/1.json
  def update
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
   
  def block_interest
 @user_interests = User.find(:first,:conditions => ["id = ?",params[:id]],:select => "interests")
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

  def block_friend_feed
    @id = params[:id]
    #@friend_stories = getFriendsStories()
    0.upto(@friend_stories.length) do |i|
       @friend_stories[i].is_blocked = true
    end
  end

#this method gets the stories of a friend through method getFriendsStories and converts them to a json file.

def friends_feed
 @user = User.find(params[:id])
#@friend_stories = getFriendsStories()
  respond_with(@friend_stories)
end
end


def friend_requests
   @me=params[:user_id]
   id_list = Array.new()
   @friend_list = Array.new()
   count = Friends.where(:stat=>0, :receiver => user_id ).select("sender").count
   puts id_list[count]
   puts friend_list[count]
   id_list=Friends.where(:stat=>0, :receiver => user_id ).select("sender")
   0.upto(id_list.length) do |i|
     friend_list[i]=User.where(@me=>id_list[i]).select("name")
   end
 respond_to do |format|
      format.json { render json: @friend_list }
end

end

