require 'test_helper'

class UserSessionsControllerTest < ActionController::TestCase
	# Author: Kiro
	setup :activate_authlogic
	
	# Author: Kiro
	test "should be able to create the session when requesting token" do

   		post :requestToken, :user_session => { :email => 'ben@gmail.com', :password => 'benrocks'}
			assert_not_nil assigns(:user_session)
			assert_not_nil assigns(:user)
			assert_equal(assigns(:user).email, 'ben@gmail.com', "the current's users email is not right")
			assert_not_nil assigns(:user).perishable_token, "couldn't find a token"

	end

	# Author: Kiro
	test "session should not be created when requesting token" do

		post :requestToken, :user_session => { :email => 'nouser@gmail.com', :password => 'ahmedrocks'}
		assert_nil assigns(:user), "created a session for a user that doesnt exist"

		post :requestToken, :user_session => { :email => 'ahmed@gmail.com', :password => 'ahmedrocks'}
		assert_nil assigns(:user), "accepted a wrong password --lowercase a"

		post :requestToken, :user_session => { :email => 'ahmed@gmail.com', :password => ''}
		assert_nil assigns(:user), "created a session with an empty password"		

		post :requestToken, :user_session => { :email => 'ahmed@gmail.com'}
		assert_nil assigns(:user), "created a session without a password"
	end
	
	# Author: Kiro
	test "should login the user with token" do

		assert_nil controller.session["user_credentials"]
   	post :login_with_token, :token => users(:ben).perishable_token
		assert_equal controller.session["user_credentials"], users(:ben).persistence_token
			
	end

end
