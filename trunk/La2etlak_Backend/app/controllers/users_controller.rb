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
     		redirect_to "/toggle"
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

  # Action to be called from the connet_to_social network view
  # which redirects to the facebook api using koala
  # Author: Menisy
  def authenticate_facebook_init
    path = Koala::Facebook::OAuth.new.url_for_oauth_code(:callback => "http://localhost:3000/fb/done/")  
    redirect_to path
  end

  # Action to be called in the call back url after authentication take place
  # Author: Menisy
  def authenticate_facebook_done
    token = Koala::Facebook::OAuth.new("http://localhost:3000/fb/done/").get_access_token(params[:code]) if params[:code]
    fb_account = FacebookAccount.new(:auth_token => token,:auth_secret => "1")
    fb_account.user = current_user
    graph =  Koala::Facebook::API.new(token)
    user = graph.get_object("me")
    if fb_account.save
      flash[:notice] = "Facebook account added successfully green" +user.to_s
      redirect_to :controller => "stories", :action => "mobile_show", :id => 1
    else
      flash[:notice] = "Facebook account was not added red" + user.to_s
      redirect_to :controller => "stories", :action => "mobile_show", :id => 1
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

 	user = current_user
	int_name = params[:interest]
	#if(user.user_add_interests == [])
	#	@stories = Story.get_stories_ranking_last_30_days
	#else

 	
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


#$$$$$$$$$$$$$$$$$$$$$$$$$$$ Author Omar  $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$
#this method return current user interests and all interests on the system and render to mobile_template (toggle view )
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
 redirect_to "/users/toggle"
end

#$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$

 #Author: Christine
 # @user is the user that we will show
 # @logs contain recent activity of the user in the last 30 days
 # @friends contain the list of users who are friends with our user
 # @interests is the list of interests that the user is subscribed to
 def show
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

=begin
  This is the method responsible of redirecting  the user to
  the connect_social_account web page
  Author: Yahia
=end
  def connect_social_accounts
    # render :layout => "mobile_template"
    # FIXME  
    session[:user_id] = 1
    @user = User.find(session[:user_id])
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
      redirect_to action: "feed"
  end

  #The method that calls method in the model to get friend stories and renders the view
  #Author: Rana
  def friends_feed
      @user = current_user
      @friend_id = params[:id]
      @my_friend_stories = @user.get_one_friend_stories(@friend_id)
      render layout:"mobile_template", template: "users/friend_feed"
  end

  #The method that calls method in the model to get the user's friends' email and renders the view
  #Author: Rana
  def friends_list
      @user = current_user
      @my_friends = @user.get_friends_email
      render layout: "mobile_template", template: "users/friends_list"
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
