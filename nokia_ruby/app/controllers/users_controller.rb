class UsersController < ApplicationController
	respond_to :html,:json

new_record=User.new( :name =>"khaled", :email => "khaled@abc.com")
new_record.save!
#this method Passes a list of Interests ids according to the user_id to get_Stories method which should return list of stories according to these Interests and it converts it to a json file.

def feed
 @id=params[:id]
 @interests = UserAddInterest.find(:all , :conditions => ["user_id = ?" , @id ] , :select => "interest_id").map {|interest| interest.interest_id}  
 @stories_list = StoriesHelper.get_stories(@interests)
respond_to do |format|
    format.json { render json: @stories_list }
 end
 end

#this method takes id as param and return user intersts and all interests 
def toggle
@id=params[:id]
@user_interests =  UserAddInterest.find(:all , :conditions => ["user_id = ?" , @id ] , :select => "interest_id").map {|interest| interest.interest_id}.map {|id| Interest.find(id).name}
@all_interests =  Interest.all()

respond_to do |format|
      format.html # index.html.erb
      format.json { render json: (@user_interests + @all_interests)  }

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
    @uLog = UserLogIn.create(:user_id => @user.id)
    respond_to do |format|
      if @user.save
        @uLog = UserLogIn.create(:user_id => @user.id)
    #    format.html { redirect_to @user, notice: 'User was successfully created.' }
        format.json { render json: @user, status: :created }
      else
    #    format.html { render action: "new" }
        format.json { render json: @user.errors, status: :unprocessable_entity }
      end
    end
  end

 
#Method profile : Responsible for updating the profile information about a certain user (First name , Last name , Date of Birth , Username), It updates the user`s Haccount as well (if the user wished to change his password). 
 # def profile
  #  @user_id =(params[:id])
    #@user=User.find(@user_id)
    @user=User.find(@user_id).update_attributes(:name=>params[:name],:first_name=>params[:first_name],:last_name=>params[:last_name],:date_of_birth=>params[:date_of_birth] )
    @huser=Haccount.find(@user_id).update_attributes(:password => params[:password])
    if @user&@huser
    #format.json { render json: "updated" }
    #respond_with("updated")
    #end
 # end

  def index
    respond_with(@users = User.all)
  end
 #This method blocks interests from the user's feed by changing the is_blocked attribute to true. The method depends on another method that gets the list of stories under these interests. The inputs to this method are the user_id and story_id
   
  def block_interest
  @user_id = params[:uid]
  @story_id = params[:id]
  @interest_id = Story.find(:first, :conditions => { :id => @story_id},:select => "interest_id")
  @interest_list = Array.new
  @interest_list << @interest_id 
  @story_list = User.find(:first,:conditions => {:id => @user_id}).get_stories(@interest_list) 
       if (@story_list!=nil)
         0.upto(@story_list.length) do |i|
         @id = @story_list[i].id
         @title = @story_list[i].title
         @date = @story_list[i].date
         @rank = @story_list[i].rank
         @media_link = @story_list[i].media_link
         @category = @story_list[i].category
         @content = @story_list[i].content
         @hidden = @story_list[i].hidden
         @deleted = @story_list[i].deleted
         @story_list[i].destroy
         Story.create!(:id => @id, :interest_id => @interest_id , :title => @title, :date => @date, :rank => @rank, :media_link => @media_link, :is_blocked => true, :category => @category, :content => @content, :hidden => @hidden, :deleted => @deleted )
         end
       end
 @username = User.find(:first, :conditions => { :id => @user_id},:select => "name")
 @storytitle = Story.find(:first, :conditions => { :id => @story_id},:select => "title")
  @interesttitle = Interest.find(:first, :conditions => { :id => @interest_id},:select => "name")
  @message = "#{@username}block_story#{@storytitle}+#{@interesttitle}"
Log.create!(loggingtype: 3,user_id_1: @user_id,user_id_2: nil, admin_id: nil, story_id: @story_id, interest_id: @interest_id, message: @message)

  end

  #This method takes a story id as input and blocks its story by setting the is_blocked attribute to true. If the story is not found, it responds with a message that the story is not found.

  
  def block_story
   @user_id = params[:uid]
   @story_id = params[:id]
   @story = Story.find(:first, :conditions => { :id => @story_id})
    if (@story != nil)
    @story.is_blocked = true
    respond_with("story is blocked successfully")
    else 
    respond_with("story is not found")
    end
    @username = User.find(:first, :conditions => { :id => @user_id},:select => "name")
    @storytitle = Story.find(:first, :conditions => { :id => @story_id},:select => "title")
    @interest_id = Story.find(:first, :conditions => { :id => @story_id},:select => "interest_id")
    @interesttitle = Interest.find(:first, :conditions => { :id => @interest_id},:select => "name")
  @message = "#{@username}block_story#{@storytitle}+#{@interesttitle}" 
Log.create!(loggingtype: 2,user_id_1: @user_id,user_id_2: nil, admin_id: nil, story_id: @story_id, interest_id: @interest_id, message: @message)
  end

#This method gets the stories of a friend through method get_friends_stories() and converts them to a json file. It takes as input the user_id.

def friends_feed
  @user_id = params[:id]
  @friend_stories = User.find(:first, :conditions => { :id => @user_id}).get_friends_stories()
  respond_with(@friend_stories)
  @username = User.find(:first, :conditions => { :id => @user_id},:select => "name")
  @message = "#{@username}friends_feed" 
Log.create!(loggingtype: 2,user_id_1: @user_id,user_id_2: nil, admin_id: nil, story_id: nil, interest_id: nil, message: @message)
end

  #This method blocks feeds from this friend by setting is_blocked attribute to true. The method depends on another method that gets the stories belonging to a friend. It takes as an input the user_id.

  def block_friends_feed
    @user_id = params[:id]
    @friend_stories = User.find(:first, :conditions => { :id => @user_id}).get_friends_stories()
    if (@friend_stories!= nil)
     @friend_stories.each {|x| x.is_blocked =true}
     # 0.upto(@friend_stories.length) do |i|
      #   @friend_stories[i].is_blocked = true
      #end
      @username = User.find(:first, :conditions => { :id => @user_id},:select => "name")
      @message = "#{@username}block_friends_feed" 
Log.create!(loggingtype: 2,user_id_1: @user_id,user_id_2: nil, admin_id: nil, story_id: nil, interest_id: nil, message: @message)
    end
  end

end
end
