require 'test_helper'

class UsersControllerTest < ActionController::TestCase
  # test "the truth" do
  #   assert true
  # end
  
  #Author : Essam
  test "filter selected social account view RED" do
    user = User.new
    user.email = 'essam@hafez.com'
    user.twitter_account = twitter_accounts(:one)
    user_feed = user.twitter_account.get_feed()
    assert !user_feed.nil?
    # add new route , match "user/social_feed/:id/:socialID" => "users#view_social_network_feed"
    assert_routing "user/social_feed/:id/:socialID" , { :controller => "users" , :action => "view_social_network_feed"}
    get "user/social_feed/:id/:socialID"
    assert_response :success
  end
  
end
