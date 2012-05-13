require 'test_helper'

class UserTest < ActiveSupport::TestCase
  # test "the truth" do
  #   assert true
  # end

  test "A user should be able to seach for member" do 
  	u1 = users(:one)
  	u2 = users(:two)
	list = u1.search_member(u2.first_name)
	assert !list.nil? 
	assert_equal list.first.class.name, 'User', 'A search result should return a list of users'
	assert_equal list.first.first_name, u2.first_name, 'A list should include the user which has the first name query string'
	list = u1.search_member(u2.last_name)
	assert_equal list.first.last_name, u2.email, 'A list should include the user which has the last name query string'
	list = u1.search_member(u2.email)
	assert_equal list.first.email, u2.email, 'A list should include the user which has the email query string'
  end 
end
