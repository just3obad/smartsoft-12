require 'test_helper'

class StatisticsControllerTest < ActionController::TestCase
  # test "the truth" do
  #   assert true
  # end
 setup :activate_authlogic
  
   ##########Author: Diab ############
  test "user statistics page has a list of users who added it" do
    admin1 = Admin.create!(email:"admin1@gmail.com", password:"123456", password_confirmation:"123456")
    AdminSession.create admin1
   get :users, :id=>1
   assert_select 'div[id = shared_stories]'
 end

 ##########Author: Diab ############
  test "get user statistics response" do
    admin1 = Admin.create!(email:"admin1@gmail.com", password:"123456", password_confirmation:"123456")
    AdminSession.create admin1
   get :users , :id => 1
   assert_response :success
   end 

 ##########Author: Diab ############
  test "all interests page has div chart" do
    admin1 = Admin.create!(email:"admin1@gmail.com", password:"123456", password_confirmation:"123456")
    AdminSession.create admin1
   get :all_interests
    assert_select 'div[id = ccc]'
   end
   
    ##########Author: Diab ############
  test "get all interests response" do
    admin1 = Admin.create!(email:"admin1@gmail.com", password:"123456", password_confirmation:"123456")
    AdminSession.create admin1
    get :all_interests
    assert_response :success
   end

   ##########Author: Diab ############
  test "interest statistics page has a list of users who added it" do
    admin1 = Admin.create!(email:"admin1@gmail.com", password:"123456", password_confirmation:"123456")
    AdminSession.create admin1
   get :interests, :id=>1
   assert_select 'div[id = adders]'
 end
 
  
   ##########Author: Diab ############
  test "get interest statistics response" do
   admin1 = Admin.create!(email:"admin1@gmail.com", password:"123456", password_confirmation:"123456")
    AdminSession.create admin1
   get :interests, :id=>1
   assert_response :success
   end 
end
