require 'test_helper'

class UserTest < ActionDispatch::IntegrationTest
  # test "the truth" do
  #   assert true
  # end

  ##########Author: Diab ############
  test "should route to user statistics page" do
    assert_routing '/users/statistics/1', { :controller => "statistics", :action => "users" , :id => "1"}
   end 
  
  ##########Author: Diab ############
  test "get user statistics response" do
   get '/users/statistics/1'
   assert_response :success
   end 
  
 

    #Author : Mina Adel
    test "route to main feed" do
      assert_routing 'users/1/main_feed', {:controller => "users", :action => "feed", :id => "1"}
    end
    
    #Author: Rana
    test "route to block story" do
      assert_routing 'mob/block_story/1', {:controller => "users", :action => "block_story", :id => "1" }
    end

    #Author: Rana   
    test "route to block interest" do
      assert_routing 'mob/block_interest/1', {:controller => "users", :action => "block_interest", :id => "1" }
    end

    #Author: Rana   
    test "route to block friend" do
      assert_routing 'mob/block_friends_feed/1', {:controller => "users", :action => "block_friends_feed", :id => "1" }
    end

    #Author: Rana   
    test "route to view friends feed" do
      assert_routing 'mob/friends_feed/1', {:controller => "users", :action => "friends_feed", :id => "1"}
    end

    #Author: Rana    
    test "route to view friends list" do
      assert_routing 'mob/friends_list', {:controller => "users", :action => "friends_list"}
    end   
     
    #Author : Omar
     test "route to toggle interests RED" do
        user = User.new
      	user.email = "abc@abc.com"
	user.password = "123456"
	user.password_confirmation = "123456"
	user.save
        assert_routing '/users/toggle', {:controller => "users", :action => "toggle", :id => user.id }
    end
    
    
    
    
    
    #Author : Omar
    test "all interests checkboxes RED" do
      	user = User.new
	user.email = "abc@abc.com"
	user.password = "123456"
	user.password_confirmation = "123456"
	user.save
	assert get(:toggle , {'user' => user})
          interests.each do |t|
             put t.name
             assert_select 'div[id = '+t.name+']'
	  end


    end
    
    
    #Author : Omar
     test "route to confirmation of interests RED" do
      assert_routing '/users/user_add_interests', {:controller => "users", :action => "user_add_interests"}
    end
    
  #Author: Yahia
    test "route to connect social account RED" do
      assert_routing 'users/mob/connect_network', {:controller => "users", :action => "connect_social_accounts"}
    end
    
    
end
