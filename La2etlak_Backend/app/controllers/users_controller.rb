class UsersController < ApplicationController
	respond_to :html,:json

	# Author: Kiro
  # Renders the registration remplate
 	def new
    @user = User.new
    render :layout => "mobile_template"
  end

  # Author: Kiro
  # Creates a new user and redirects him to the toggle if saved
  def create
    @user = User.new(params[:user])
    if @user.save
      @user.generateVerificationCode?
      Emailer.verification_instructions(@user).deliver
      flash[:notice] = "Thank you for joining La2etlak, you just recieved an E-mail containing the verification instructions."
			session = UserSession.new(@user)
			if session.save
     		redirect_to "/mob/toggle"
			else
				redirect_to "/dummyLogin"
			end
   else
    render :action => 'new', :layout => "mobile_template"
   end
  end
	
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
		render :layout => "mobile_template"
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




#this Action generates the User main feed
#if the User desn't have Interests the Top ranked stories on the System will appear else the Stories will appear to Him according to his Interests and in both cases the Stories shared by his Friends will be Included and Also stories From his Social Accounts

#Author Kareem###############
def feed
  #if(user.user_add_interests == [])
        #       @stories = Story.get_stories_ranking_last_30_days
        #else

 	user = current_user
 	int_name = params[:interest]
   	 if(int_name)
    	 @stories = user.get_feed(int_name)
	 else
	 @stories =  user.get_feed("null")
	end
 #@stories = @stories + user.get_friends_stories
	 respond_to do |format|
    	 format.json { render json: @stories }
   	 # Author : Mina Adel
  	 format.html { render :layout => "mobile_template"}
  	 #
 #end
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

# updates user interests according what the user selects  na dredirect to the same view toggle to update voew of interests
def int_toggle
user = current_user
id = params[:id]
user.toggle_interests(id)
 redirect_to "/mob/toggle"
end


#$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$

 #Author: Christine
 def show
    @user = User.find(params[:id])
    @user = User.find(params[:id])
    @logs = @user.get_recent_activity(30.days.ago)
    @friends = @user.friends
    @interests = @user.added_interests
    respond_to do |format|
      format.html #show.html.erb
      format.json { render json: @user }
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


#~~~~~~~~~~ 3OBAD ~~~~~~~~~~#
=begin
 ~ Method Name : edit
 ~ Function : Responsible for rendering the view in which the user can edit his information
=end

  def edit
    @user=current_user 
    render layout: "mobile_template", template: "users/edit" 
  end
=begin
 ~ Method : 
 ~ Function : Responsible for updating the information of the user and notiyf him.
   Case 1 : If the update was successful the user is notified that his information was updated successfully
   Case 2 : If the nickname is greater than 20 chars, he will be notified of this error
   Case 3 : If the firstname is greater than 20 chars, he will be notified of this error
   Case 4 : If the lastname is greater than 20 chars, he will be notified of this error
   Case 5 : If there is a password missmatch, he will be notified of this error
   Case 6 : If the pasword is less than 4 chars, he will be notified of this error
=end
  def update
   @user = current_user
   if @user.update_attributes(params[:user])
        flash[:notice] = "Your Info was saved successfully"
        redirect_to action:"edit"
   else
    if @user.name.length>20
         flash[:notice] = "The nickname is too long. Must be less than 20 chars"
         redirect_to action:"edit"
    else
      if @user.first_name.length>20
           flash[:notice] = "The firstname is too long. Must be less than 20 chars"
           redirect_to action:"edit"
      else
        if @user.last_name.length>20
             flash[:notice] = "The lastname is too long. Must be less than 20 chars"
             redirect_to action:"edit"
        else
          if @user.password != @user.password_confirmation
               flash[:notice] = "Password missmatch"
               redirect_to action:"edit"
          else
            if @user.password.length<4
                 flash[:notice] = "The password must be greater than 4 chars"
                 redirect_to action:"edit"
            end
          end
        end  
      end           
    end
   end
  end 

#~~~~~~~~~~ 3OBAD ~~~~~~~~~~#
  

end
