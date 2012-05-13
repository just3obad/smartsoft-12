require 'test_helper'

class UsersControllerTest < ActionController::TestCase

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
		
		user = User.new
		user.email = "abc@abc.com"
		user.save
		assert get(:toggle , {'user' => user})

	end

  # Author: Omar
  test "add selected interest in database" do
     user = User.create(:email => "a@abc.com")	
     interest = Array.new
     interest1 = Interest.create(:name => "interest 1")
     interest2 = Interest.create(:name => "interest 2")
     interest = [interest1.id , interest2.id]
     assert get(:user_add_interests , {'interests' => interest} , {'user' => user} )
     


  end
    
  #Author:Rana
  test "get block story RED" do
    this_interest = Interest.create :name => "Sports", :description => "hey sporty"
    this_story= Story.new :title => "Story1", :interest_id => this_interest
    this_story.interest = this_interest
    this_story.save
    this_user = User.create :name => "amr", :email => "amr@abc.com"
    get :block_story, 'uid' => this_user.id, 'id' => this_story.id
    assert_response :success 
  end

  #Author: Rana
  test "get block interest RED" do
    this_interest = Interest.create :name => "Sports", :description => "hey sporty"
    this_story= Story.new :title => "Story1", :interest_id => this_interest
    this_story.interest = this_interest
    this_story.save
    this_user = User.create :name => "amr", :email => "amr@abc.com"
    get :block_interest, 'uid' => this_user.id, 'id' => this_story.id
    assert_response :success 
  end

  #Author: Rana
  test "get block friend RED" do
    this_user = User.create :name => "amr", :email => "amr@abc.com"
    my_friend = User.create :name => "ahmed", :email => "ahmed@abc.com"
    get :block_friends_feed, 'id' => this_user.id, 'fname' => my_friend.name
    assert_response :success 
  end

  #Author: Rana
  test "get friend feed RED" do
    this_user = User.create :name => "amr", :email => "amr@abc.com"
    my_friend = User.create :name => "ahmed", :email => "ahmed@abc.com"
    get :friends_feed, 'id' => this_user.id, 'fname' => my_friend.name
    assert_response :success 
  end

  #Author: Rana
  test "get friend list RED" do
      this_user = User.create :name => "amr", :email => "amr@abc.com"
      get :friends_list, 'id' => this_user.id
      assert_response :success
  end

  # Author: Yahia
  test "connect social accounts RED" do
    get :connect_social_accounts, {}, {:user_id => 1}
    assert_response :success, "Get request should be successfull"

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
  test "A user should see a warning if he is connected to twitter in the connect_social_accounts page" do
    u1 = user(:one)
    u1.twitter_account = twitter_accounts(:one)
    u1.save
    get :connect_social_accounts, {}, {:user_id => u1.id}
    assert_select 'div[class=warning-component-box-mobile]',1
    assert_select 'You are connected to twitter'
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

end
