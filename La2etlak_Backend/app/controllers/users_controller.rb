class UsersController < ApplicationController
	respond_to :html,:json

	# Author: Kiro
  # Renders the registration remplate
 	def new
    @user = User.new
    render :layout => "mobile_template"
  end

  # Author: Kiro
  # Creates a new user
  # generates a verifcation code for the user and sends it by email
  # redirects the user to the toggle screen
  def create
    @user = User.new(params[:user])
		@user.email = params[:user][:email].downcase
    if @user.save
      @user.generateVerificationCode?
      Emailer.verification_instructions(@user).deliver
      flash[:notice] = "Thank you for joining La2etlak, you just recieved an E-mail containing the verification instructions green"
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
	
  # Author: Kiro
	# This method is used to register a new user and save him,
	# if the user was saved successfully it returns "true,
	# otherwise it returns the errors that prevented the saving of the record
  # MOBILE SIDE
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

 # Action to be called from the connet_to_social network view
  # which redirects to the facebook api using koala
  # Author: Menisy
  def authenticate_facebook_init
    path = Koala::Facebook::OAuth.new.url_for_oauth_code(:callback => "http://localhost:3000/fb/done/",:permissions => "read_stream")  
    redirect_to path
  end

  # Action to be called in the call back url after authentication take place
  # Author: Menisy
  def authenticate_facebook_done
    if !params[:code]
      flash[:notice] = "Facebook account was not added red"
      redirect_to :controller => "users", :action => "feed"
      return
    end
    token = Koala::Facebook::OAuth.new("http://localhost:3000/fb/done/").get_access_token(params[:code]) if params[:code]
    fb_account = FacebookAccount.find_or_create_by_user_id(current_user.id)
    fb_account.auth_token = token
    fb_account.auth_secret = 1
    graph =  Koala::Facebook::API.new(token)
    user = graph.get_object("me")
    fb_id = user["id"]
    fb_account.facebook_id = fb_id
    if fb_account.save
      fb_account.add_to_log
      flash[:notice] = "Facebook account was added successfully green"
      redirect_to :controller => "users", :action => "connect_social_accounts"
    else
      flash[:notice] = "Facebook account was not added red" + user.to_s
      redirect_to :controller => "users", :action => "connect_social_accounts"
    end
  end

  # Author: Kiro
  # resets the password of the user and sends it by email
	def resetPassword
		@user = User.find_by_email(params[:email].downcase)
    if @user.nil?
      flash[:notice] ="This email doesn't exist red"
      redirect_to :controller => 'users', :action => 'forgot_password'
    else
		  newpass = @user.resetPassword
		  Emailer.reset_password(@user,newpass).deliver
      flash[:notice] = "Your new password has been sent to your email green"
      redirect_to :controller => 'user_sessions' , :action => 'new'
    end
	end

  # Author: Kiro
  # renders the forgot_password screen
  def forgot_password
    @email
    render :layout => 'mobile_template'
  end

	#Author: Kiro (Those methods are for testing purpose)
	def dummyLogin
    if nokia_user?
      redirect_to :controller => 'user_sessions' , :action => 'new'
    end
		#render :layout => "mobile_template"
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
  user = current_user
	@lol = current_user
  int_name = params[:interest]
  if(user.user_add_interests == [] && !int_name)
               stories = user.get_unblocked_stories(Story.get_stories_ranking_last_30_days)
		stories = stories + user.get_friends_stories
               @stories=stories.paginate(:per_page => 10, :page=> params[:page])
     else
   	 if(int_name)
    	 stories = user.get_feed(int_name)
	 else
	 stories =  user.get_feed("null")
	 stories = stories + user.get_friends_stories
	 stories = stories + user.get_social_feed
	 end
  end
  # Author : Mina Adel
  @stories=stories.paginate(:per_page => 10, :page=> params[:page])
  render :layout => "mobile_template"
 	#
end
########################

#$$$$$$$$$$$$$$$$$$ Mina Adel $$$$$$$$$$$$$$$$$$
def settings
	@user = current_user
  render :layout => "mobile_template"
end

def facebook_feed
  @user = current_user
  stories = @user.filter_social_network(2)
  @stories=stories.paginate(:per_page => 10, :page=> params[:page])
  render :layout => "mobile_template", :template => "users/feed"
end

def twitter_feed
  @user = current_user
  stories = @user.filter_social_network(1)
  @stories=stories.paginate(:per_page => 10, :page=> params[:page])
  render :layout => "mobile_template", :template => "users/feed"
end
#$$$$$$$$$$$$$$$$$$ Mina Adel $$$$$$$$$$$$$$$$$$


#$$$$$$$$$$$$$$$$$$$$$$$$$$$ Author Omar $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$
#this method return current user interests and all interests on the system and render to mobile_template (toggle view 
def toggle
@user = current_user
@user_interests = @user.user_interests
@all_interests = @user.all_interests
 if @user.user_interests == []
  flash[:notice] = " You didn't select any interest ! "
 end
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

# author: Gasser
# This method is responsible for calling the resetPassword method in the users model 
# that generates a random password and updates his password then it is passed to the 
#emailer to send this mail with the format I made in send_forced_password in the views.
#Then a success flash appears to inform the admin that the password is sent successfully
# then it redirects again to the show page.
def force_reset_password     
  @user = User.find(params[:id])
  @logs = @user.get_recent_activity(Time.zone.now)
  @friends = @user.friends
  @interests = @user.added_interests
  pass = @user.resetPassword
  Emailer.send_forced_password(@user,pass).deliver
  flash[:success] = "The new password is sent to the user by e-mail"
  redirect_to '/users/'+ (params[:id])
end

 #Author: Christine
 # @user is the user that we will show
 # @logs contain recent activity of the user in the last 30 days
 # @friends contain the list of users who are friends with our user
 # @interests is the list of interests that the user is subscribed to
 def show
    @user = User.find(params[:id])
    @logs = @user.get_recent_activity(Time.zone.now)
    @friends = @user.friends
    @interests = @user.added_interests
    respond_to do |format|
      format.html #show.html.erb
      format.json { render json: @user }
    end
  end


  def index
    @users = User.paginate(:per_page=>5,:page=>params[:page])
  end

=begin
  This is the method responsible of redirecting  the user to
  the connect_social_account web page
  Author: Yahia
=end
  def connect_social_accounts
    @user = current_user
    render layout: "mobile_template", template: "users/connect_social_accounts"
    # redirect_to(:action => 'generate_request_token', :id => 5)
  end 
  

 #Author: Bassem
  #The method takes the user Id as a parameter, passes it to the model where the user is found
  #and his deactivated attribute is set to true
  def deactivate
    @user = User.find(params[:id])
    @user.deactivate_user(params[:id])
    redirect_to(:action => 'show', :id => @user.id)
  end
  #Author: Bassem
  #The method takes the user Id as a parameter, passes it to the model where the user is found
  #and his deactivated attribute is set to false
  def activate
    @user = User.find(params[:id])
    @user.activate_user(params[:id])
    redirect_to(:action => 'show', :id => @user.id)
  end

  #method that calls the method in model to block story and renders the view
  #Author: Rana
  def block_story
    @user = current_user
    @story_id = params[:id]
    @story = Story.find_by_id(@story_id)
    @text = @user.block_story1(@story)
    flash[:notice] = "#{@text}"
    redirect_to action: "feed"
  end

  #method that calls the method in model to block interest and renders the view
  #Author: Rana
  def block_interest
    @user = current_user
    @story_id = params[:id]
    @story = Story.find_by_id(@story_id)
    @text = @user.block_interest1(@story)
    flash[:notice] = "#{@text}"
    redirect_to action: "feed"
  end

  #The method that calls the method in the model to block friend feed and renders the view
  #Author: Rana
  def block_friends_feed
      @user = current_user
      @friend_id = params[:id]
      @friend = User.find_by_id(@friend_id)
      @text = @user.block_friends_feed1(@friend) 
      flash[:notice] = "#{@text}"
      redirect_to controller: 'friendships', action: "index"
  end

  #The method that gets the user's blocked friends and renders the view
  #Author: Rana
  def manage_blocked_friends
      @user = current_user
      @my_friends = @user.blocked
      render layout: "mobile_template"
  end

  #The method that calls method in the model to get the user's blocked stories and renders the view
  #Author: Rana
  def manage_blocked_stories
      @user = current_user
      @blocked_stories = @user.get_blocked_stories
      render layout: "mobile_template"
  end

  #The method that calls the method in the model to unblock a story and renders the view
  #Author: Rana
  def unblock_story
      @user = current_user
      @story_id = params[:id]
      @story = Story.find_by_id(@story_id)
      @text = @user.unblock_story1(@story) 
      flash[:notice] = "#{@text}"
      redirect_to action: "feed"
  end

  #The method that calls the method in the model to unblock friend feed and renders the view
  #Author: Rana
  def unblock_friends_feed
      @user = current_user
      @friend_id = params[:id]
      @friend = User.find_by_id(@friend_id)
      @text = @user.unblock_friends_feed1(@friend) 
      flash[:notice] = "#{@text}"
      redirect_to action: "friends_feed", id: @friend_id
  end

  #The method that calls method in the model to get friend stories and renders the view
  #Author: Rana
  def friends_feed
      @user = current_user
      @friend_id = params[:id]
      my_friend_stories = @user.get_one_friend_stories(@friend_id)
      @my_friend_stories = my_friend_stories.paginate(:per_page => 10, :page=> params[:page]) 
      render layout:"mobile_template", template: "users/friend_feed"
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
        flash[:notice] = "Your Info was saved successfully green"
        redirect_to action:"edit"
   else
    if @user.name.length>20
         flash[:notice] = "The nickname is too long. Must be less than 20 chars red"
         redirect_to action:"edit"
    else
      if @user.first_name.length>20
           flash[:notice] = "The firstname is too long. Must be less than 20 chars red"
           redirect_to action:"edit"
      else
        if @user.last_name.length>20
             flash[:notice] = "The lastname is too long. Must be less than 20 chars red"
             redirect_to action:"edit"
        else
          if @user.password != @user.password_confirmation
               flash[:notice] = "Password missmatch red"
               redirect_to action:"edit"
          else
            if @user.password.length<4
                 flash[:notice] = "The password must be greater than 4 chars red"
                 redirect_to action:"edit"
            else
                 flash[:notice] = "Error please try again red"
                 redirect_to action:"edit" 
            end
          end
        end  
      end           
    end
   end
  end   

#~~~~~~~~~~ 3OBAD ~~~~~~~~~~#

	# Author: Kiro
	def verifySettings
    render :layout => 'mobile_template'
  end

	# Author: Kiro
	def verifyAccount
		@code = params[:code].downcase
		@user = current_user
		if @user.verifyAccount?(@code)
			flash[:notice] = "Your account has been successfully verified green"
			redirect_to :controller => 'users', :action => 'feed'
		else
			flash[:notice] = "Incorrect Verification Code red"
			redirect_to :controller => 'users', :action => 'verifySettings'
		end
	end
  
	# Author: Kiro
	def resendCode
		@user = current_user
		if @user.resendCode?
			Emailer.verification_instructions(@user).deliver
			flash[:notice] = "The verification E-mail has been sent to your email green"
			redirect_to :controller => 'users', :action => 'verifySettings'
		else
			flash[:notice] = "You have already verified your account"
			redirect_to :controller => 'users', :action => 'feed'
		end
	end
end
