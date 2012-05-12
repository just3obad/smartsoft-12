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

end
