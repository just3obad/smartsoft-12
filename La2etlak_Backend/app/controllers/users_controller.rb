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
				@user.generateVerificationCode?
				Emailer.verification_instructions(@user).deliver
	   	  format.json { render text: "true" }
	  	else
				errors = "errors:" + @user.errors.to_s
	  	  format.json { render text: errors }
	  	end
		end
	end

	def resetPassword
		@user = current_user
		newpass = @user.resetPassword
		Emailer.reset_password(@user,newpass).deliver
	end

	#Author: Kiro (This method is for testing purpose)
	def dummyLogin
	end

	def test
		user = User.find_by_id(params[:id])
		UserSession.create(user)
		@user = current_user
	end

	def test_2
		@user = current_user
	end
	#################################################

#new_record=User.new( :name =>"khaled", :email => "khaled@abc.com")
#new_record.save!
#this method Passes a list of Interests ids according to the user_id to get_Stories method which should return list of stories according to these Interests and it converts it to a json file.

#Author Kareem###############
def feed
 #$$$$$$$$ Mina Adel $$$$$$$$$$$$$
 user = current_user
 #$$$$$$$$ Mina Adel $$$$$$$$$$$$$
 int_name = params[:interest]
    if(int_name)
     @stories = user.get_feed(int_name)
	else
	@stories =  user.get_feed("null")
end
respond_to do |format|
   format.json { render json: @stories }
   # Author : Mina Adel
   format.html { render :layout => "mobile_template"}
   #
 end
end
########################


#$$$$$$$$$$$$$$$$$$$$$$$$$$$ Author Omar $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$
#this method return current user interests and all interests on the system and render to mobile_template (toggle view 
def toggle
@user = current_user
@user_interests = @user.user_interests
@all_interests = @user.all_interests
render :layout => "mobile_template"
end

#this method adds selected interests into UserAddInterest table in the database
def user_add_interests
@user = current_user
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

#$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$

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

 # Author: Yahia
  def connect_social_accounts
    # render :layout => "mobile_template"
    # FIXME  
    session[:user_id] = 1
    @user = User.find(session[:user_id])
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

  #author: Gasser
  def force_reset_password
  end

  # author: Gasser
  def send_resetted_password
  end

  # Author: Yahia
  def search_members
    # FIXME 
    # session[:user_id] = 1
    # string = params[:query]
    # @user = User.find(session[:user_id])
  end 
  #Author: Bassem
  def deactivate
    User.find_by_id(params[:id]).deactivated = true
    respond_with true
  end

  #method that calls the method in model to block story and renders the view
  #Author: Rana
  def block_story
    @user = current_user
    @story_id = params[:id]
    @story = Story.find_by_id(@story_id)
    @text = @user.block_story1(@story)
    render @text
  end

  #method that calls the method in model to block interest and renders the view
  #Author: Rana
  def block_interest
    @user = current_user
    @story_id = params[:id]
    @story = Story.find_by_id(@story_id)
    @text = @user.block_interest1(@story)
    render @text
  end

  #The method that calls the method in the model to block friend feed and renders the view
  #Author: Rana
  def block_friends_feed
      @user = current_user
      @friend_id = params[:id]
      @friend = User.find_by_id(@friend_id)
      @text = @user.block_friends_feed1(@friend) 
      render @text
  end

end
