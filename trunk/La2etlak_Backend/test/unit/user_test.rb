require 'test_helper'

class UserTest < ActiveSupport::TestCase
  ##########Author: christinesed@gmail.com ############
  test "get no of users who signed in today one more user signed in today RED" do
    count=User.get_no_of_users_signed_in_today
    usr=User.new(:email=>"example@gmail.com")
    assert usr.save
    log=UserLogIn.new
    log.user=usr
    assert log.save
    count2=User.get_no_of_users_signed_in_today
    assert_equal(count+1,count2)
  end

   ##########Author: christinesed@gmail.com ############
  test "get no of users who signed in today shouldn't add RED" do
    count=User.get_no_of_users_signed_in_today
    usr=User.new(:email=>"example@gmail.com")
    assert usr.save
    log=UserLogIn.new
    log.user=usr
    log.created_at=2.days.ago
    assert log.save
    count2=User.get_no_of_users_signed_in_today
    assert_equal(count,count2)
  end

   ##########Author: christinesed@gmail.com ############
  test "get no of users who signed in today three more users signed in today RED" do
    count=User.get_no_of_users_signed_in_today
    usr=User.new(:email=>"example@gmail.com")
    usr1=User.new(:email=>"example1@gmail.com")
    usr2=User.new(:email=>"example2@gmail.com")
    assert usr.save
    assert usr1.save
    assert usr2.save
    log=UserLogIn.new
    log.user=usr
    assert log.save
    log=UserLogIn.new
    log.user=usr1
    assert log.save
    log=UserLogIn.new
    log.user=usr2
    assert log.save
    count2=User.get_no_of_users_signed_in_today
    assert_equal(count+3,count2)
  end

   ##########Author: christinesed@gmail.com ############
   test "get no of users who signed in today a user signed in more than once RED" do
    count=User.get_no_of_users_signed_in_today
    usr=User.new(:email=>"example@gmail.com")
    assert usr.save
    log=UserLogIn.new
    log.user=usr
    assert log.save
    log=UserLogIn.new
    log.user=usr
    assert log.save
    count2=User.get_no_of_users_signed_in_today
    assert_equal(count+1,count2)
  end
  
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