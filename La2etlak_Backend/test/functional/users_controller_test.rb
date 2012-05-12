require 'test_helper'

class UsersControllerTest < ActionController::TestCase

   #test "the truth" do
    # assert true
   #end

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
		   assert get(:addwithduplicates , {'interests' => interest} , {'user' => user} )
		   
		
		
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

end
