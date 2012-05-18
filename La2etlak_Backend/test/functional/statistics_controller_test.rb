require 'test_helper'

class StatisticsControllerTest < ActionController::TestCase
  # test "the truth" do
  #   assert true
  # end
   ##########Author: Diab ############
  test "user statistics page has a list of users who added it" do
   get :users, :id=>1
   assert_select 'div[id = shared_stories]'
 end

 ##########Author: Diab ############
  test "all interests page has div chart" do
   get :all_interests, :id=>1
    assert_select 'div[id = ccc]'
   end
   ##########Author: Diab ############
  test "interest statistics page has a list of users who added it" do
   get :interests, :id=>1
   assert_select 'div[id = adders]'
 end
end
