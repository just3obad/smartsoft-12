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

  
end
