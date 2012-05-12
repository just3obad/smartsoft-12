require 'test_helper'

class UserTest < ActiveSupport::TestCase
  # test "the truth" do
  #   assert true
  # end
  
  #Author : Essam
  test "filter social networks RED" do
    user = User.new
    user.email = 'essam@hafez.com'
    user.twitter_account = twitter_accounts(:one)
    assert !user.twitter_account.nil?
    user_feed = user.twitter_account.get_feed()
    assert !user_feed.nil?
  end

end