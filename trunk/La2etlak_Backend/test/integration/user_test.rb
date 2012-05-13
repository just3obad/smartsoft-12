require 'test_helper'

class UserTest < ActionDispatch::IntegrationTest
  # test "the truth" do
  #   assert true
  # end

  ##########Author: Diab ############
  test "should route to user statistics page" do
    assert_routing '/users/1/statistics', { :controller => "statistics", :action => "users" , :id => "1"}
   end 
  
  ##########Author: Diab ############
  test "get user statistics response RED" do
   get '/users/1/statistics'
   assert_response :success
   end 
  
  ##########Author: Diab ############
  test "user statistics page has a list of users who added it RED" do
   get '/users/1/statistics'
   assert_select "ul[id = shared_stories]" do
    assert_select "li"
  end
 end
#Author: khaled.elbhaey 
  test "should route to get_friends_email_mobile_show RED" do
    assert_routing 'users/get_friends_email_mobile_show/1', { :controller => "users", :action => "get_friends_email_mobile_show", :uid => "1" }
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
    #Author : Mina Adel
    test "route to main feed" do
      assert_routing 'users/1/main_feed', {:controller => "users", :action => "feed", :id => "1"}
    end
    
    #Author: Rana
    test "route to block story RED" do
      assert_routing 'users/block_story/1/1', {:controller => "users", :action => "block_story", :uid => "1", :id => "1" }
    end

    #Author: Rana   
    test "route to block interest RED" do
      assert_routing 'users/block_interest/1/1', {:controller => "users", :action => "block_interest", :uid => "1", :id => "1" }
    end

    #Author: Rana   
    test "route to block friend RED" do
      assert_routing 'users/block_friends_feed/1/rana', {:controller => "users", :action => "block_friends_feed", :id => "1", :fname => "rana" }
    end

    #Author: Rana   
    test "route to view friends feed RED" do
      assert_routing 'users/friends_feed/1/rana', {:controller => "users", :action => "friends_feed", :id => "1", :fname => "rana" }
    end

    #Author: Rana    
    test "route to view friends list RED" do
      assert_routing 'users/friends_list/1', {:controller => "users", :action => "friends_list", :id => "1"}
    end   

    #Author: Rana
    test "friends list view page RED" do
      this_user = User.create :name => "mohamed", :email => "m@abc.com"
      get friends_list_path(this_user)
      assert_select 'div[id = "heading"]'
      assert_select 'div[id = "list"]'
      assert_select 'div[id = "fbutton"]'
      assert_select 'div[id = "bbutton"]'
      assert_select "form[action='friends_feed']"
      assert_select "form[action='block_friends_feed']"
    end

    #Author: Rana
    test "form for button friend list in main feed RED" do
      this_user = User.create :name => "mohamed", :email => "m@abc.com"
      get main_feed_path(this_user)
      assert_select "form[action = 'friends_list']"
      assert_select 'div[id="fbutton"]'
    end
    
    
    #Author : Omar
     test "route to toggle interests RED" do
      assert_routing '/users/1/toggle', {:controller => "users", :action => "toggle", :id => "1" }
    end
    
    
    
    
    
    #Author : Omar
    test "all interests checkboxes RED" do
      	user = User.new
	user.email = "abc@abc.com"
	user.save
	assert get(:toggle , {'user' => user})
          interests.each do |t|
             put t.name
             assert_select 'div[id = '+t.name+']'
	  end


    end
    
    
    #Author : Omar
     test "route to confirmation of interests RED" do
      assert_routing '/users/1/user_add_interests', {:controller => "users", :action => "user_add_interests", :id => "1"}
    end
    
  #Author: Yahia
    test "route to connect social account RED" do
      assert_routing 'users/mob/connect_network', {:controller => "users", :action => "connect_social_accounts"}
    end
    
    
end
