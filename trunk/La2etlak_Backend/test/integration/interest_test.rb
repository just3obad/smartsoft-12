require 'test_helper'

class InterestTest < ActionDispatch::IntegrationTest
  # test "the truth" do
  #   assert true
  # end

  ##########Author: Diab ############
  test "should route to all interests statistics" do
  assert_routing 'admins/statistics/all_interests', { :controller => "statistics", :action => "all_interests"}
    end

  ##########Author: Diab ############
  test "get all interests response" do
   get 'admins/statistics/all_interests'
   assert_response :success
    end

  ##########Author: Diab ############
  test "all interests page has div chart RED" do
    get 'admins/statistics/all_interests'
    assert_select 'div[id = ccc]'
   end

  ##########Author: Diab ############
  test "should route to interest statistics page" do
    assert_routing '/interests/1/statistics', { :controller => "statistics", :action => "interests" , :id => "1"}
   end 

  ##########Author: Diab ############
  test "get interest statistics response RED" do
   
   get '/interests/1/statistics'
   assert_response :success
   end 

  ##########Author: Diab ############
  test "interest statistics page has a list of users who added it RED" do
   get '/interests/1/statistics'
   assert_select "ul[id = adders]" do
    assert_select "li"
  end
 end
end
