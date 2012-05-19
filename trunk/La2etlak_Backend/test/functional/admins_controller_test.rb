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

end
