require 'test_helper'

class UserTest < ActionDispatch::IntegrationTest
  # test "the truth" do
  #   assert true
  # end
  
  setup :activate_authlogic


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
    test "route to unblock friend" do
      assert_routing 'mob/unblock_friends_feed/1', {:controller => "users", :action => "unblock_friends_feed", :id => "1" }
    end

    #Author: Rana   
    test "route to view friends feed" do
      assert_routing 'mob/friends_feed/1', {:controller => "users", :action => "friends_feed", :id => "1"}
    end

    #Author: Rana    
    test "route to manage blocked friends" do
      assert_routing 'mob/manage_blocked_friends', {:controller => "users", :action => "manage_blocked_friends"}
    end

    #Author: Rana   
    test "route to unblock story" do
      assert_routing 'mob/unblock_story/1', {:controller => "users", :action => "unblock_story", :id => "1" }
    end  

    #Author: Rana    
    test "route to manage blocked stories" do
      assert_routing 'mob/manage_blocked_stories', {:controller => "users", :action => "manage_blocked_stories"}
    end 
     
    #Author : Omar
     test "route to toggle interests" do
  	user = users(:ben)
	UserSession.create(user)      
        assert_routing '/mob/toggle', {:controller => "users", :action => "toggle"}
    end
       
  #Author: Yahia
    test "route to connect social account RED" do
      assert_routing 'users/mob/connect_network', {:controller => "users", :action => "connect_social_accounts"}
    end
    
    
end
