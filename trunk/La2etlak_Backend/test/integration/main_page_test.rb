require 'test_helper'

class MainPageTest < ActionDispatch::IntegrationTest
  #this test is to make sure that the route is not lost 
  test "AdminMainPageRoute" do
        assert_routing '/admins/index', { :controller => "admins", :action => "index"}
      end
  #this test is to make sure that the main page does exist and i get a 200OK response     
  test "AdminMainPage" do
        get "/admins/index"
        assert_response :success
     end
  #this test is for testing the existance of feed div
  test "AutoCompleteResultDiv" do
        get "/admins/index"
        assert_select "div[id=feeds]"
     end
  #test get_feed
  test "get feeds" do
        Interest.create!(:name => "Music", :description => "Music washes away from the soul the dust of everyday life", :image => "http://data.whicdn.com/images/15521117/hamster-music_165451585_thumb.jpg")
        @feeds = Admin.get_feed
        assert_equal(@feeds.length,1)
      end
end
