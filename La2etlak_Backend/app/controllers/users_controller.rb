class UsersController < ApplicationController
	respond_to :html,:json

	# This method is used to register a new user and save him,
	# if the user was saved successfully it returns "true,
	# otherwise it returns the errors that prevented the saving of the record
	# Author: Kiro
	def register
 		@user = User.new(params[:user])
		respond_to do |format|
			if @user.save
	   	  format.json { render text: "true" }
	  	else
				errors = "errors:" + @user.errors
	  	  format.json { render text: errors }
	  	end
		end
	end

#new_record=User.new( :name =>"khaled", :email => "khaled@abc.com")
#new_record.save!
#this method Passes a list of Interests ids according to the user_id to get_Stories method which should return list of stories according to these Interests and it converts it to a json file.

#Author Kareem###############
def feed
@id=params[:id]
 user = User.find(@id)
 int_name = params[:interest]
    if(int_name)
     @stories = user.get_feed(int_name)
	else
	@stories =  user.get_feed("null")
end
respond_to do |format|
   format.json { render json: @stories }
 end
end
########################

#this method return current user interests and all interests on the system and render to mobile_template (toggle view 
def toggle
@user = User.first
@user_interests = @user.user_interests
@all_interests = @user.all_interests
render :layout => "mobile_template"
end

#this method adds selected interests into UserAddInterest table in the database
def user_add_interests
@user = User.first
@interests = params[:interests]
interestsss = UserAddInterest.find_all_by_user_id(@user.id)
 interestsss.each do |t|
 t.destroy
 end
 if @interests != nil 
@interests.each do |element|
  @intid = element
  UserAddInterest.create(user_id:@user.id , interest_id:@intid)
end
end
render :layout => "mobile_template"
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
  def edit_info
   @user = User.find(params[:id])
  respond_to do |format|
      if @user.update_attributes(params[:post])
        render :layout => "mobile_template", :template => "users/show"      
      else
        render :layout => "mobile_template", :template => "users/show"
      end
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


 # Author: Yahia
  def connect_social_accounts
    # render :layout => "mobile_template"
    # FIXME  
    params[:id] = 1
    @user = User.find(params[:id])
    render layout: "mobile_template", template: "users/connect_social_accounts"
    # redirect_to(:action => 'generate_request_token', :id => 5)
  end 
  

=begin
  This is the first phase of the OAuth phase that is required by twitter.
  In this phase, first a new Consumer gets created which is basically 
  a client that represents our app talking to twitter. 

  The client asks twitter for a request_token, from which a URL will 
  be generated. The callback url should be changed to the proper server 
  URL. 

  The browser will be redirected to the generated authorization URL. After
  that, twitter will redirect the user back to our app. 

  Author: Yahia
=end
  def generate_request_token
    #FIXME FOR THE SAKE OF TESTING
    session[:user_id] = 1

    #FIXME change IP 
    request_token = User.twitter_consumer.get_request_token(:oauth_callback => 
                "http://127.0.0.1:3000/users/twitter/generate_access_token")

    url = request_token.authorize_url
    #puts 'URL IS ' + url
    redirect_to(url)
  end 

=begin
  This is the second phase of authentication. In this phase, the user should have 
  authenticated our app through twitter. Then we use the request token and secret 
  token from that exact user to get our access tokens from twitter. Through the 
  access token, we can get the feeds or tweet on behalf of the user. 

  This is done by simply
  requesting the access token by the oauth_token and oauth_verifier which twitter
  put in the params array. Through this access token the twitter accoun can be made
  thorugh which the system fetches tweets.

  Author: Yahia
=end 
  def generate_access_token
    # FIXME FOR THE SAKE OF TESTING
    session[:user_id] = 1
    @user = User.find(session[:user_id])
    request_token = OAuth::RequestToken.new(User.twitter_consumer,
                    params["oauth_token"], params["oauth_verifier"])

    t_account = @user.create_twitter_account(request_token)

    unless t_account.new_record?
      render(:layout => 'mobile_template', 
              :text => "User #{ session[:user_id] }" + 
                      "created a new twitter account")
    else 
      render(:layout => 'mobile_template', 
              :text => 'Something wrong, couldn\'t save account')
    end 
  end 
end


