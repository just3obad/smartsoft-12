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
  test "all interests page has div chart" do
    get 'admins/statistics/all_interests'
    assert_select 'div[id = ccc]'
   end

  ##########Author: Diab ############
  test "should route to interest statistics page" do
    assert_routing '/interests/statistics/1', { :controller => "statistics", :action => "interests" , :id => "1"}
   end 

  ##########Author: Diab ############
  test "get interest statistics response" do
   
   get '/interests/statistics/1'
   assert_response :success
   end 

  ##########Author: Diab ############
  test "interest statistics page has a list of users who added it" do
   get '/interests/statistics/1'
   assert_select 'div[id = adders]'
 end
end
