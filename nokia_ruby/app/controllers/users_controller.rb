class UsersController < ApplicationController
	respond_to :html,:json

#new_record=User.new( :name =>"khaled", :email => "khaled@abc.com")
#new_record.save!
#this method Passes a list of Interests ids according to the user_id to get_Stories method which should return list of stories according to these Interests and it converts it to a json file.

def feed
 @id=params[:id]
 @interests = UserAddInterest.find(:all , :conditions => ["user_id = ?" , @id ] , :select => "interest_id").map {|interest| interest.interest_id}  
 @stories_list = get_stories(@interests).map {|story| story.attributes.merge(:interest => Interest.find(story.interest_id).name)}
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
      # format.html  index.html.erb
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
    @user = User.new(params[:user.downcase])
    respond_to do |format|
      if @user.save
        @user.haccount = Haccount.new(:email => @user.email.downcase,
                                      :password=>params[:password.downcase], :user_id => @user.id)
        if @user.haccount.generateVerificationCode?
          Emailer.resend_code(@user.haccount).deliver
          @uLog = UserLogIn.create(:user_id => @user.id)
          format.json { render json: @user, status: :created }
        end
      else
        format.json { render json: @user.errors, status: :unprocessable_entity }
      end
    end
  end

 
#Method profile : Responsible for updating the profile information about a certain user (First name , Last name , Date of Birth , Username), It updates the user`s Haccount as well (if the user wished to change his password). 
  def profile
    @user_id =(params[:id])
    #@user=User.find(@user_id)
    @user=User.find(@user_id).update_attributes(:name=>params[:name],:first_name=>params[:first_name],:last_name=>params[:last_name],:date_of_birth=>params[:date_of_birth] )
    @huser=Haccount.find(@user_id).update_attributes(:password => params[:password])
   if @user&@huser
    #format.json { render json: "updated" }
    respond_with("updated")
   end
  end

  def index
    respond_with(@users = User.all)
  end

#This method blocks interests from the user's feed by changing the is_blocked attribute to true. The method depends on another method that gets the list of stories under these interests. The inputs to this method are the user_id and story_id
   
  def block_interest
  @user_id = params[:uid]
  @story_id = params[:id]
  @interest_id = Story.find_by_id(@story_id).interest_id
  @interest_list = Array.new
   interest =  BlockInterest.find_by_user_id_and_interest_id(@user_id,@interest_id)
   if interest.nil?
      BlockInterest.create!(:user_id => @user_id, :interest_id => @interest_id) 
      render text: "interest blocked successfully"
   else 
      render text: "interest already blocked"    
   end
   #log file
  @username = User.find_by_id(@user_id).name
  @storytitle = Story.find_by_id(@story_id).title
  @interesttitle = Interest.find_by_id(@interest_id).name
  @message = "#{@username}blocks interest#{@interesttitle}"
  Log.create!(loggingtype: 3,user_id_1: @user_id,user_id_2: nil, admin_id: nil, story_id: nil, interest_id: @interest_id, message: @message)
  end

  #This method takes a story id as input and blocks its story by setting the is_blocked attribute to true. If the story is not found, it responds with a message that the story is not found.

  def block_story
   @user_id = params[:uid]
   @story_id = params[:id]
   story = BlockStory.find_by_user_id_and_story_id(@user_id,@story_id)
   if story.nil?
      BlockStory.create!(:user_id => @user_id, :story_id => @story_id)     
      render text: "Story blocked successfully"
   else 
      render text: "Story already blocked"
   end
   #for log file
   @username = User.find_by_id(@user_id).name
   @storytitle = Story.find_by_id(@story_id).title
   @message = "#{@username}blocks story with title:#{@storytitle}" 
   Log.create!(loggingtype: 2,user_id_1: @user_id,user_id_2: nil, admin_id: nil, story_id: @story_id, interest_id: @interest_id, message: @message)
  end


#This method gets the stories of a friend through method get_friends_stories() and converts them to a json file. It takes as input the user_id.

def friends_feed
  @user_id = params[:id]
  @friend_id = params[:fid]
  @friend_stories = User.find_by_id(@user_id).get_one_friend_stories(@friend_id)
  respond_with(@friend_stories)
end


  #This method blocks feeds from this friend by setting is_blocked attribute to true. The method depends on another method that gets the stories belonging to a friend. It takes as an input the user_id.

  def block_friends_feed
    @user_id = params[:id]
    @friend_id = params[:fid]
    sender = Friend.find_by_sender_and_receiver_and_status(@user_id, @friend_id,1)
    if !sender.nil?
      sender.status = 3
      sender.save
      render text: "friend is blocked successfully sender"
    else 
      receiver = Friend.find_by_sender_and_receiver_and_status(@friend_id,@user_id,1)
      if !receiver.nil?
         receiver.status = 4
         receiver.save
         render text: "friend is blocked successfully receiver"
      else 
         render text: "error while blocking"
      end
    end
    #for log file    
    @username = User.find_by_id(@user_id).name
    @user2 = User.find_by_id(@friend_id).name
    @message = "#{@username}blocks the feed of#{@user2}" 
    Log.create!(loggingtype: 0,user_id_1: @user_id,user_id_2: @friend_id, admin_id: nil, story_id: nil, interest_id: nil, message: @message)
  end
end


