require 'test_helper'

class UsersControllerTest < ActionController::TestCase
  # test "the truth" do
  #   assert true
  # end
  
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

  
  
end
