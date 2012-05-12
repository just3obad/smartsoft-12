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
end
