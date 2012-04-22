class UsersController < ApplicationController
	respond_to :html,:json

 def show
    respond_with(@user = User.find(params[:id]))
  end

  def search
    respond_with(@user = Admin.search(params[:id]))
  end

   def new
    @user = User.new

    respond_to do |format|
      format.html # new.html.erb
      format.json { render json: @user }
    end
    end

  def create
    @user = User.new(params[:name=>params[:name],:email=>params[:email],
                    :first_name=>params[:first_name], :last_name=>params[:last_name],
                    :date_of_birth=>params[:date_of_birth]])
#    redirect_to :controller => GaheemAccount, :action => 'create', :email=>params[:email],
#        :password=>params[:password]
    respond_to do |format|
      if @user.save
        format.html { redirect_to @user, notice: 'User was successfully created.' }
 #       format.json { render json: @user, status: :created, location: @user }
      else
        format.html { render action: "new" }
 #       format.json { render json: @user.errors, status: :unprocessable_entity }
      end
    end
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
      if @user.update_attributes(params[:user])
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
 #this method takes an array of interests and blocks these interests   from the user's feed by changing the is_blocked attribute to true. The method depends on another method that gets the list of stories under these interests.
  
  #@story_list = getStories(self.interests) 
  def block_interest(story_list)
    0.upto(story_list.length) do |i|
       story_list[i].is_blocked = true
    end
  end

  #this method takes a story as input and blocks it by setting the is_blocked attribute to true.

  #story = Story.find(params[:id])
  def block_story(story)
    story.is_blocked = true
  end

  #this method takes list of stories belonging to a friend as input and blocks feeds from this friend by setting is_blocked attribute to true. The method depends on another method that gets the stories belonging to a friend.

  #@friend_stories = getFriendsStories(self.friends)
  def block_friend_feed(friend_stories)
    0.upto(friend_stories.length) do |i|
       friend_stories[i].is_blocked = true
    end
  end

#this method Passes a list of Interests according to the user_id to getStories method which should return list of stories according to these Interests and it converts it to a json file.

def feed
 @id=params[:id]
# @interests = User.find(:first,:conditions => ["id = ?",@id],:select => "interests")
# @stories_list = getStories(@interests)
respond_to do |format|
      format.json { render json: @stories_list }
end

def friend_requests(user_id)
   id_list = Array.new()
   @friend_list = Array.new()
   count = Friends.where(:stat=>0, :receiver => user_id ).select("sender").count
   puts id_list[count]
   puts friend_list[count]
   id_list=Friends.where(:stat=>0, :receiver => user_id ).select("sender")
   0.upto(id_list.length) do |i|
     friend_list[i]=User.where(:id=>id_list[i]).select("name")
   end
 respond_to do |format|
      format.json { render json: @friend_list }
end  


def friends(user_id)
   id_list = Array.new()
   @friends = Array.new()
   count = Friends.where(:stat=>1, :receiver => user_id ).select("sender").count
   puts id_list[count]
   puts friend[count]
   id_list=Friends.where(:stat=>1, :receiver => user_id ).select("sender")
   0.upto(id_list.length) do |i|
     friends[i]=User.where(:id=>id_list[i]).select("name")
   end
 respond_to do |format|
      format.json { render json: @friends }
end  
  

end
end
end
