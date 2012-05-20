require 'test_helper'

class AdminsControllerTest < ActionController::TestCase
  # test "the truth" do
  #   assert true
  # end
  setup :activate_authlogic
  test "AdminMainPage contains no of users who signed in today zero" do
  	admin1 = Admin.create!(email:"admin1@gmail.com", password:"123456", password_confirmation:"123456")
  	AdminSession.create admin1
  	get :index
  	assert_select "li[id=no_users_signed_in][class=label-warning]", "0 sign ins"
  end

  test "AdminMainPage contains no of users who signed in today not zero" do
    usr=User.new(:email=>"example@gmail.com", :password => "1234567", :password_confirmation => "1234567")
    assert usr.save
    log=UserLogIn.new
    log.user=usr
    assert log.save
    count2=User.get_no_of_users_signed_in_today
  	admin1 = Admin.create!(email:"admin1@gmail.com", password:"123456", password_confirmation:"123456")
  	AdminSession.create admin1
  	get :index
  	assert_select "li[id=users_signed_in][class=label-info]", "#{count2} sign ins"
  end
#Author MESAI
  #this test is to make sure that the route is not lost 
   test "AdminMainPageRoute" do
      assert_routing '/admins/index', { :controller => "admins", :action => "index"}
     end
   #this test is to make sure that the main page does exist and i get a 200OK response     
   test "AdminMainPage" do
      admin1 = Admin.create!(email:"admin1@gmail.com", password:"123456", password_confirmation:"123456")
   	  AdminSession.create admin1
   	  get :index
      assert_response :success ,message:"Go to main page"
     end
   #this test is to make sure that i have a field for search and autocomplete
   test "AutoCompleteFieldExistance" do
     admin1 = Admin.create!(email:"admin1@gmail.com", password:"123456", password_confirmation:"123456")
  	  AdminSession.create admin1
  	  get :index
     assert_select "input[id=autocomplete_query]",message:"Should find a TextField for search"
   end
   #this test is to make sure that that there is a hidden div for results of auto complete
   test "AutoCompleteResultDiv" do
      admin1 = Admin.create!(email:"admin1@gmail.com", password:"123456", password_confirmation:"123456")
   	  AdminSession.create admin1
   	  get :index
     assert_select "div[id=autocomplete_query_auto_complete]",message:"Should find a Div for results"
   end
    #this test is for testing the existance of feed div
    test "ExistanceOfFeedsDiv" do
       admin1 = Admin.create!(email:"admin1@gmail.com", password:"123456", password_confirmation:"123456")
    	  AdminSession.create admin1
    	  get :index
        assert_select "div[id=feeds]",message:"Should find a Div for feeds"
       end
    #test get_feed
    test "get feeds" do
          Interest.create!(:name => "Music", :description => "Music washes away from the soul the dust of everyday life")
          @feeds = Admin.get_feed
          assert(@feeds.length >= 1,"should find number of feeds greater than 1")
        end
end
