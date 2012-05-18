require 'test_helper'

class UsersControllerTest < ActionController::TestCase

	# Author: Kiro ##########
	setup :activate_authlogic
	#########################


   test "admin should reset user's password RED" do
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

  test "should update" do
    put :edit_info, id: 1, user: { name:"sdgssf", first_name:"uhsdgsf", last_name:"dshsfghsgd" }
    #assert_redirected_to post_path(assigns(:post))
    assert_template(expected = "show")
  end

  test "valid max" do
    put :edit_info, id: 1, user: { name:"sdgssfaaaaaaaaaaaaaaaaa", first_name:"uhsdgsfaaaaaaaaaaaaaaaaaa", last_name:"dshsfghsgd" }
    #assert_redirected_to post_path(assigns(:post))
    assert_template(expected = "show")
  end


  
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
  test "get block story" do
    this_interest = Interest.create :name => "Sports", :description => "hey sporty"
    this_story= Story.new :title => "Story1", :interest_id => this_interest
    this_story.interest = this_interest
    this_story.save
    this_user = users(:ben)
    UserSession.create(this_user)
    get :block_story, 'id' => this_story.id
    assert_response :redirect 
  end

  #Author: Rana
  test "get block interest" do
    this_interest = Interest.create :name => "Sports", :description => "hey sporty"
    this_story= Story.new :title => "Story1", :interest_id => this_interest
    this_story.interest = this_interest
    this_story.save
    this_user = users(:ben)
    UserSession.create(this_user)
    get :block_interest, 'id' => this_story.id
    assert_response :redirect 
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
  test "get manage blocked friends list" do
      this_user = users(:ben)
      UserSession.create(this_user)
      get :manage_blocked_friends
      assert_response :success
      assert_select 'div[id = "heading"]'
      assert_select 'div[id = "list"]'
      assert_select 'div[id = "back"]'
  end

  #Author: Rana
  test "get manage blocked stories list" do
      this_user = users(:ben)
      UserSession.create(this_user)
      get :manage_blocked_stories
      assert_response :success
      assert_select 'div[id = "heading"]'
      assert_select 'div[id = "list"]'
      assert_select 'div[id = "back"]'
  end

  #Author: Rana
  test "get unblock story" do
    this_user = users(:ben)
    UserSession.create(this_user)
    this_interest = Interest.create :name => "Sports", :description => "hey sporty"
    this_story= Story.new :title => "Story1", :interest_id => this_interest
    this_story.interest = this_interest
    this_story.save
    get :unblock_story, 'id' => this_story.id
    assert_response :redirect 
  end

  # Author: Yahia
  test "connect social accounts" do
    get :connect_social_accounts
    assert_response :success, "Get request should be successfull"

  end 

	# Author: Kiro
	test "should create user RED" do
		assert_difference('User.count') do
   		post :register, :user => { :email => 'user_email1@test.com', :password => '123456', :password_confirmation => '123456'}
  	end
	end

	# Auther: Kiro
	test "should not create user RED" do
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
  test "A user should see a warning if he is connected to twitter in the connect_social_accounts page" do
    # needs to create a session with that user
    u1 = users(:ben)
    u1.twitter_account = twitter_accounts(:one)
    u1.save
    UserSession.create u1
    get :connect_social_accounts
    assert_response :success, 'Get should be successful'
    assert_select "div[id=twitter_warning]"

  end 

  # Author: Menisy
  test "facebook_warning_box" do
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

  #Author: Bassem
  test "deactivating users" do
    new_user = User.create
    get :deactivate, { :id => new_user.id} 
    assert new_user.deactivated
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
      @usr=User.create!(:email=>"exampleuserpage@gmail.com", :password => "1234567", :password_confirmation => "1234567")
      get :show, :id=> @usr.id
  
     assert_response :success
  end
  
  # Author : Christine
  #Check that the profile page contain all the buttons needed.
  test "UserProfilePage should contain all needed buttons" do
     @usr=User.create!(:email=>"exampleuserpage@gmail.com", :password => "1234567", :password_confirmation => "1234567")
     get :show, :id=> @usr.id
  
     assert_select "a[id=statistics_button]"
     assert_select "a[id=reset_password_button]"
     assert_select "a[id=deactivate_button]"
  end
 
  # Author : Christine
  #Check that the profile page contains all the Main divs needed.
  test "UserProfilePage should contain all the right main divs" do
    @usr=User.create!(:email=>"exampleuserpage@gmail.com", :password => "1234567", :password_confirmation => "1234567")
    get :show, :id=> @usr.id
    assert_select "div[id=friends]"
    assert_select "div[id=interests]"
    assert_select "div[id=recentActivity]"
  end
  # Author : Christine
  test "UserProfilePage contains usr info" do
     @usr=User.create!(:email=>"exampleuserpage@gmail.com", :password => "1234567", :password_confirmation => "1234567")
     get :show, :id=> @usr.id
    assert_select "div[id=user-personal-info]" do
      assert_select "span[id=my-email]", @usr.email
      assert_select "span[class='label label-info']", "Active"
    end
  end
  # Author : Christine
  test "UserProfilePage get right no of friends" do
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
     @usr=User.create!(:email=>"exampleuserpage@gmail.com", :password => "1234567", :password_confirmation => "1234567")
     get :show, :id=> @usr.id

    assert_select "div[id=interests]" do
      assert_select "div[class=well-interest-component]", 0
    end
  end 
  # Author : Christine
  test "UserProfilePage get right no of interests of a user who added two interests" do
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
	test "test login" do

		UserSession.create(users(:ben))
		get :feed
		user = assigns(:lol)
		puts user.email
		assert_not_nil user, "user is nil"
		assert_equal user.email, "ben@gmail.com", "user is not ben"

	end

end
