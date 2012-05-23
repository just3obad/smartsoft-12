require 'test_helper'

class TwitterAccountTest < ActiveSupport::TestCase

  test "truth" do

  	 assert true
  end



test "one" do 
    user = users(:ben)
    f_account = FlickrAccount.new
    f_account.secret_key = 'asdfadsf'
    f_account.user = user 
    assert !f_account.save, 'Should not save without oauth token'
  end 


end