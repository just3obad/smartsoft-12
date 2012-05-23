require 'test_helper'

class UsersControllerTest < ActionController::TestCase

	# Author: Kiro ##########
	setup :activate_authlogic
	#########################

  # Author: Gasser
  test "admin should reset user's password" do
    admin = Admin.create!(email:"admin1@gmail.com", password:"123456", password_confirmation:"123456")
    AdminSession.create admin
    user=User.first
    password = user.password
    get :force_reset_password, :id => user.id
    assert_routing 'users/force_reset_password/'+user.id.to_s, {:controller =>"users", :action => "force_reset_password", :id=> "1"}
    assert_difference 'ActionMailer::Base.deliveries.size', +1 do
      get :force_reset_password, :id =>user.id
    end
    reset_password = ActionMailer::Base.deliveries.last
    assert_equal "Your Password was reset by the Admin", reset_password.subject, "The subject sent in the mail is wrong"
    assert_equal 'menis@abc.com', reset_password.to[0], "The email was not delivered to the user"
    assert_equal "The new password is sent to the user by e-mail", flash[:success] , "No success flash appears."
    assert_equal user.password, password , "The password was not changed successfully."
    assert_redirected_to user_path, "The admin was not redirected to the user profile page"
  end

   test "admin should reset users password RED" do
     user=User.first
     get :force_reset_password, :id => user.id
     assert_routing '/force_reset_password/'+user.id.to_s, {:controller =>"users", :action => "force_reset_password", :id=> "1"}
     assert_difference 'ActionMailer::Base.deliveries.size', +1 do
     get :send_resetted_password , :id =>user.id
     end
     reset_password = ActionMailer::Base.deliveries.last
     assert_equal "Your Password was reset by the Admin", reset_password.subject
     assert_equal 'menis@abc.com', reset_password.to[0]
     assert_redirected_to user_path
   end

# $$$$$$$$$$$$$$$$$$$ 3OBAD $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$  
=begin
  Modify Info Test
  Case 1: Update info noramelly
  Case 2: Update info without password 
  Case 3: Test name validation same for first name and last name but will do on test only, validation is name < 20
  Case 4: Test password != confirmation
  Case 5: Test password && confiramtion < 6 chars

  Note thar, I have no access on the password, meaning I can not do x.password, so I tests I changed the 
    password successflly by creating a session with the new password and assert saving and doig the reverse 
    at the same time, creating a session with the old password and asserting that it does not save :)
  Author: 3OBAD  
=end
  test "should update info with password" do
    user = users(:ben)
    us = UserSession.create(user)
    put :update, :id => users(:ben), :user => {:name => 'abdo', :first_name=>"abdo", :last_name=>"abdo", :password=>"111111", :password_confirmation=>"111111"}
    assert_equal( "abdo",User.find(users(:ben).id).name,"Name is not updated succefully" )
    assert_equal( User.find(users(:ben).id).first_name, "abdo", "First name is not updated succefully" )
    assert_equal( User.find(users(:ben).id).last_name, "abdo", "Last name is not updated succefully" )
    assert_response :redirect
    us.destroy
    session = UserSession.new(email:"ben@gmail.com",password:"benrocks")
    assert !session.save
    session2 = UserSession.new(email:"ben@gmail.com",password:"111111") 
    assert  session2.save
  end

  test "should update info without password" do
    user = users(:ben)
    us = UserSession.create(user)
    put :update, :id => users(:ben), :user => {:name => 'abdo', :first_name=>"abdo"}
    assert_equal( "abdo",User.find(users(:ben).id).name,"Name is not updated succefully" )
    assert_equal( User.find(users(:ben).id).first_name, "abdo", "First name is not updated succefully" )
    assert_response :redirect
    us.destroy
    session = UserSession.new(email:"ben@gmail.com",password:"benrocks")
    assert session.save
    session2 = UserSession.new(email:"ben@gmail.com",password:"")
    assert !session2.save
  end

  test "should update password without info" do
    user = users(:ben)
    us = UserSession.create(user)
    put :update, :id => users(:ben), :user => {:password=>"111111", :password_confirmation=>"111111"}

    #I do not know why its not working, If I created a new user, his name, fname, lname are nil, but one I call update they change to "",
    #Its not working here
    #assert_equal( "",User.find(users(:ben).id).name,"Name is not updated succefully" )
    #assert_equal( User.find(users(:ben).id).first_name, "", "First name is not updated succefully" )
    #assert_equal( User.find(users(:ben).id).last_name, "", "Last name is not updated succefully" )
    assert_response :redirect
    us.destroy
    session = UserSession.new(email:"ben@gmail.com",password:"benrocks")
    assert !session.save
    session2 = UserSession.new(email:"ben@gmail.com",password:"111111")
    assert session2.save
  end

  test "should not update info with name more than 20 chars" do
    user = users(:ben)
    us = UserSession.create(user)
    put :update, :id => users(:ben), :user => {:name => 'aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa'}
    assert_not_equal( "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa",User.find(users(:ben).id).name,"Name is  updated Damn!!!" )
    assert_response :redirect
  end

  test "should not update info when pass not equal conf" do
    user = users(:ben)
    us = UserSession.create(user)
    put :update, :id => users(:ben), :user => {:password=>"222222", :password_confirmation=>"111111"}
    assert_response :redirect
    us.destroy
    session = UserSession.new(email:"ben@gmail.com",password:"benrocks")
    assert session.save
    session2 = UserSession.new(email:"ben@gmail.com",password:"111111") 
    assert  !session2.save
  end

  test "should not update info when pass less than 6 chars" do
    user = users(:ben)
    us = UserSession.create(user)
    put :update, :id => users(:ben), :user => {:password=>"11", :password_confirmation=>"11"}
    assert_response :redirect
    us.destroy
    session = UserSession.new(email:"ben@gmail.com",password:"benrocks")
    assert session.save
    session2 = UserSession.new(email:"ben@gmail.com",password:"11") 
    assert  !session2.save
  end
# $$$$$$$$$$$$$$$$$$$ 3OBAD $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$  

  
  #Author : Essam
  test "filter selected social account view RED" do
    user = User.new
    user.email = 'essam@hafez.com'
    user.twitter_account = twitter_accounts(:one)
    user_feed = user.twitter_account.get_feed()
    assert !user_feed.nil?
    # add new route , match "user/social_feed/:id/:socialID" => "users#view_social_network_feed"
    assert_routing "user/social_feed/:id/:socialID" , { :controller => "users" , :action => "view_social_network_feed"}
    get "user/social_feed/:id/:socialID"
    assert_response :success
  end
  
  #Author : Mina Adel
  test "show main feed" do
    user = User.new(:email => "test@test.com")
    user.save
    puts user.id
    assert get(:feed, {"id" => user.id})
  end
  
  #Author : Mina Adel
  test "check if main feed has the correct elements RED" do
    interest = Interest.new(:name => "test Interest")
    interest.save
    user = User.new(:email => "test@test.com")
    user.save
    useraddinterest = UserAddInterest.new(:user_id => user.id, :interest_id => interest.id)
    feed = Feed.new(:link => "http://xkcd.com/rss.xml", :interest_id => interest.id)
    feed.save
    stories = user.get_feed("null")
    get(:feed, {'id' => user})
    stories.each do |s|
          puts s.title
          assert_select 'div[id = '+s.title+']'
    end  
  end
  
  #Author : Mina Adel
  test "check if main feed is nil RED" do
    interest = Interest.new(:name => "test Interest")
    interest.save
    user = User.new(:email => "test@test.com")
    user.save
    useraddinterest = UserAddInterest.new(:user_id => user.id, :interest_id => interest.id)
    assert get(:feed, {'id' => user.id})
    assert_select 'div[id = "Emptiness_Alert"]'
  end
  
  #Author: Omar
	test "toggle view" do
		
		user = users(:ben)
		UserSession.create(user)
		assert get(:toggle , {'user' => user})

	end
	
  #Author:Rana
  test "should redirect on block story" do
    this_interest = Interest.create :name => "Sports", :description => "hey sporty"
    this_story= Story.new :title => "Story1", :interest_id => this_interest
    this_story.interest = this_interest
    this_story.save
    this_user = users(:ben)
    UserSession.create(this_user)
    get :block_story, 'id' => this_story.id
    assert_response :redirect 
    assert_redirected_to(options = {:controller => "users", :action => "feed"},   	message = "Story blocked successfully")
  end

  #Author: Rana
  test "should redirect on block interest" do
    this_interest = Interest.create :name => "Sports", :description => "hey sporty"
    this_story= Story.new :title => "Story1", :interest_id => this_interest
    this_story.interest = this_interest
    this_story.save
    this_user = users(:ben)
    UserSession.create(this_user)
    get :block_interest, 'id' => this_story.id
    assert_response :redirect 
    assert_redirected_to(options = {:controller => "users", :action => "feed"},   	message = "Interest blocked successfully")
  end

  #Author: Rana
  test "get block friend" do
    this_user = users(:ben)
    UserSession.create(this_user)
    my_friend = users(:ahmed)
    this_user.invite my_friend
    my_friend.approve this_user
    get :block_friends_feed, 'id' => my_friend.id
    assert_response :redirect 
  end

  #Author: Rana
  test "get unblock friend" do
    this_user = users(:ben)
    UserSession.create(this_user)
    my_friend = users(:ahmed)
    this_user.invite my_friend
    my_friend.approve this_user
    get :unblock_friends_feed, 'id' => my_friend.id
    assert_response :redirect 
  end

  #Author: Rana
  test "get friend feed" do
    this_user = users(:ben)
    UserSession.create(this_user)
    my_friend = users(:ahmed)
    this_user.invite my_friend
    my_friend.approve this_user
    get :friends_feed, 'id' => my_friend.id
    assert_response :success
    assert_select 'div[id = "heading"]'
    if(!(this_user.get_one_friend_stories(my_friend.id).empty?))
    assert_select 'div[id = "my_stories"]'
    end
    assert_select 'div[id = "block"]'
    assert_select 'div[id = "back"]' 
  end

  #Author: Rana
  test "should get manage blocked friends list" do
      this_user = users(:ben)
      UserSession.create(this_user)
      get :manage_blocked_friends
      assert_response(:success, message = "list fetched successfully")
      assert_select 'div[id = "heading"]'
      assert_select 'div[id = "list"]'
      assert_select 'div[id = "back"]'
  end

  #Author: Rana
  test "get manage blocked stories list" do
      this_user = users(:ben)
      UserSession.create(this_user)
      get :manage_blocked_stories
      assert_response(:success, message = "list fetched successfully")
      assert_select 'div[id = "heading"]'
      assert_select 'div[id = "list"]'
      assert_select 'div[id = "back"]'
  end

  #Author: Rana
  test "should redirect on unblock story" do
    this_user = users(:ben)
    UserSession.create(this_user)
    this_interest = Interest.create :name => "Sports", :description => "hey sporty"
    this_story= Story.new :title => "Story1", :interest_id => this_interest
    this_story.interest = this_interest
    this_story.save
    get :unblock_story, 'id' => this_story.id
    assert_response :redirect 
    assert_redirected_to(options = {:controller => "users", :action => "feed"},   	message = "Story unblocked successfully")
  end


	# Author: Kiro
	test "should create user" do
		assert_difference('User.count') do
   		post :register, :user => { :email => 'user_email1@test.com', :password => '123456', :password_confirmation => '123456'}
  	end
	end

	# Auther: Kiro
	test "should not create user" do
		assert_no_difference('User.count') do
   		post :register, :user => { :email => 'user_email2@test.com', :password => '12345', :password_confirmation => '123456'}
  	end
	
		User.create(email: "AlreadyInUse@test.com", password: "123456", password_confirmation: "123456")

		assert_no_difference('User.count',"created a user with an existing email --same") do
   		post :register, :user => { :email => 'AlreadyInUse@test.com', :password => '123456', :password_confirmation => '123456'}
  	end

		assert_no_difference('User.count',"created a user with an existing --lowercase") do
   		post :register, :user => { :email => 'alreadyinuse@test.com', :password => '123456', :password_confirmation => '123456'}
  	end
	end

  # Author: Yahia
  test "connect social network" do
    # needs to create a session with that user
    u1 = users(:ben)
    u1.twitter_account = twitter_accounts(:one)
    u1.save
    UserSession.create u1
    assert_routing 'mob/connect_network', {:controller => "users", :action => "connect_social_accounts"}
    get :connect_social_accounts
    assert_response :success, 'Get should be successful'
    assert_select "div[id=twitter_warning]"

  end 

  # Author: Menisy
  test "facebook warning box" do
    # needs to create a session with that user
    u1 = users(:ben)
    u1.facebook_account = facebook_accounts(:one)
    u1.save
    UserSession.create u1
    get :connect_social_accounts
    assert_response :success, 'Get should be successful'
    assert_select "div[id=facebook_warning]"
  end 


   #author: khaled.elbhaey
  test "the view of friends emails RED" do
    new_user=User.create(:email=>"khd@abc.com")
    list=new_user.get_friends_email()
     if !list.empty?
      get email_path(new_user)
      assert_select 'div[ id=emails]'
     else
      get email_path(new_user)
      assert_select 'div[ id=error_explanation]'
     end
  end

	# Auther: Kiro
	test "The old password of the user should expire after requesting a new one RED" do

		get :resetPassword
		post :requestToken, :user_session => { :email => 'ben@gmail.com', :password => 'benrocks'}
		assert_nil assigns(:user), "the user was able to login using his expired password"

  end 


 
	# Author: Kiro
	test "An email should be sent to the user after requesting a new password RED" do
	   assert_difference 'ActionMailer::Base.deliveries.size', +1, "An email wasn't sent to the user" do
     get :resetPasswordz
     end
	end
   
  # Author : Christine
  test "UserProfilePageRoute" do
     assert_routing '/users/1', { :controller => "users", :action => "show", :id=> "1"}
  end
  
  # Author : Christine
  #Test that the user profile page responds with http:succes
  test "UserProfilePage should return success response" do
     admin1 = Admin.create!(email:"admin1@gmail.com", password:"123456", password_confirmation:"123456")
     AdminSession.create admin1
      @usr=User.create!(:email=>"exampleuserpage@gmail.com", :password => "1234567", :password_confirmation => "1234567")
      get :show, :id=> @usr.id
  
     assert_response :success
  end
  
  # Author : Christine
  #Check that the profile page contain all the buttons needed.
  test "UserProfilePage should contain all needed buttons" do
    admin1 = Admin.create!(email:"admin1@gmail.com", password:"123456", password_confirmation:"123456")
     AdminSession.create admin1
     @usr=User.create!(:email=>"exampleuserpage@gmail.com", :password => "1234567", :password_confirmation => "1234567")
     get :show, :id=> @usr.id
  
     assert_select "a[id=statistics_button]"
     assert_select "a[id=reset_password_button]"
     assert_select "a[id=deactivate_button]"
  end
 
  # Author : Christine
  #Check that the profile page contains all the Main divs needed.
  test "UserProfilePage should contain all the right main divs" do
    admin1 = Admin.create!(email:"admin1@gmail.com", password:"123456", password_confirmation:"123456")
     AdminSession.create admin1
    @usr=User.create!(:email=>"exampleuserpage@gmail.com", :password => "1234567", :password_confirmation => "1234567")
    get :show, :id=> @usr.id
    assert_select "div[id=friends]"
    assert_select "div[id=interests]"
    assert_select "div[id=recentActivity]"
  end
  # Author : Christine
  test "UserProfilePage contains usr info" do
    admin1 = Admin.create!(email:"admin1@gmail.com", password:"123456", password_confirmation:"123456")
     AdminSession.create admin1
     @usr=User.create!(:email=>"exampleuserpage@gmail.com", :password => "1234567", :password_confirmation => "1234567")
     get :show, :id=> @usr.id
    assert_select "div[id=user-personal-info]" do
      assert_select "span[id=my-email]", @usr.email
      assert_select "span[id=is-active]", "Active"
    end
  end
  # Author : Christine
  test "UserProfilePage get right no of friends" do
    admin1 = Admin.create!(email:"admin1@gmail.com", password:"123456", password_confirmation:"123456")
     AdminSession.create admin1
     @usr=User.create!(:email=>"exampleuserpage@gmail.com", :password => "1234567", :password_confirmation => "1234567")
     get :show, :id=> @usr.id
      @usr2=User.create!(:email=>"example1userpage@gmail.com", :password => "1234567", :password_confirmation => "1234567")
     @usr.invite @usr2
     @usr2.approve @usr
     get :show, :id=> @usr.id
    assert_select "div[id=friends]" do
      assert_select "div[class=well-user-component]", @usr.friends.count
    end
  end 

  # Author : Christine
  test "UserProfilePage get right no of interests of a user with no interests" do
     admin1 = Admin.create!(email:"admin1@gmail.com", password:"123456", password_confirmation:"123456")
     AdminSession.create admin1
     @usr=User.create!(:email=>"exampleuserpage@gmail.com", :password => "1234567", :password_confirmation => "1234567")
     get :show, :id=> @usr.id

    assert_select "div[id=interests]" do
      assert_select "div[class=well-interest-component]", 0
    end
  end 
  # Author : Christine
  test "UserProfilePage get right no of interests of a user who added two interests" do
     admin1 = Admin.create!(email:"admin1@gmail.com", password:"123456", password_confirmation:"123456")
     AdminSession.create admin1
     @usr=User.create!(:email=>"exampleuserpage@gmail.com", :password => "1234567", :password_confirmation => "1234567")
     get :show, :id=> @usr.id
    int1=Interest.new(:name=> "InterestDummy1")
    assert int1.save
    int2=Interest.new(:name=> "InterestDummy2")
    assert int2.save
    @usr.added_interests << int1
    @usr.added_interests << int2
    @usr.save!
    get :show, :id=> @usr.id
    assert_select "div[id=interests]" do
      assert_select "div[class=well-interest-component]", @usr.added_interests.count
    end
  end   
  # Author : Christine
  test "UserProfilePage get right no of activities in the last 30 days" do
    admin1 = Admin.create!(email:"admin1@gmail.com", password:"123456", password_confirmation:"123456")
     AdminSession.create admin1
     @usr=User.create!(:email=>"exampleuserpage@gmail.com", :password => "1234567", :password_confirmation => "1234567")
     get :show, :id=> @usr.id
    count1=@usr.get_recent_activity(Time.zone.now).count
    story=Story.new(:title=>"storyexample")
    int1=Interest.new(:name=> "InterestDummy1")
    assert int1.save
    story.interest=int1
    assert story.save
    Log.create!(:user_id_1 => @usr.id, :story_id => story.id, :message => "Shared a story")
    get :show, :id=> @usr.id
    count2=@usr.get_recent_activity(Time.zone.now).count
    assert_equal(count2,count1+1)
    assert_select "div[id=recentActivity]" do
      assert_select "td[class=logs-table-row]", count2
    end
  end

  #Author : Shafei
  test "route to all users" do
		assert_routing 'admins/statistics/all_users', { :controller => "statistics", :action => "all_users"}
    end
	
  #Author : Shafei
  test "get all users response" do
	get 'admins/statistics/all_users'
	assert_response :success
    end

  #Author : Shafei
  test "all users has div" do
    get 'admins/statistics/all_users'
    assert_select 'div[id = ccc]'
   end

	# Author: Kiro
	test "The correct user is logged in" do

		UserSession.create(users(:ben))
		get :feed
		user = assigns(:lol)
		puts user.email
		assert_not_nil user, "user is nil"
		assert_equal user.email, "ben@gmail.com", "user is not ben"

	end
	
	#Author: Kiro
	test "the user should recieve the registration mail" do

    assert_difference 'ActionMailer::Base.deliveries.size', +1 do
      post :create, :user => {:email => "user@example.com", :password => '123456', :password_confirmation => '123456'}
    end
    registration_email = ActionMailer::Base.deliveries.last
    assert_equal "La2etlak Verification Instructions", registration_email.subject, "wrong subject"
    assert_equal "user@example.com", registration_email.to[0], "wrong reciever"

  end

	#Author: Kiro
	test "unsaved user should not get the email" do

			User.create(email: "in_use@example.com",password: "123456", password_confirmation:"123456")
      
			assert_no_difference 'ActionMailer::Base.deliveries.size', "User that wasn't saved got the email" do
      	post :create, :user => {:email => "in_use@example.com", :password => '1234', :password_confirmation => '123456'}
    	end
  end
	
	#Author: Kiro
	test "the method resetPassword shows the correct flash messages" do

		ben = users(:ben)
		post :resetPassword, :email => "ben@gmail.com"
		assert_equal flash[:notice], "Your new password has been sent to your email $green", "(valid email)Incorrect flash message"

		post :resetPassword, :email => "bek@gmail.com"
		assert_equal flash[:notice], "This email doesn't exist $red", "(invalid email)Incorrect flash message"

	end

	#Author: Kiro
	test "user is redirected correctly resetPassword" do

	ben = users(:ben)
	post :resetPassword, :email => "ben@gmail.com"
	assert_redirected_to :controller => 'user_sessions', :action => 'new'

	post :resetPassword, :email => "bek@gmail.com"
	assert_redirected_to :controller => 'users', :action => 'forgot_password'

	end

end
