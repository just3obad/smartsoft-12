require 'test_helper'

class VerificationCodesControllerTest < ActionController::TestCase


test "should update intetrest" do
  put :update, :id => interests(:one), :interest => {:name => 'MyString2'}
  assert_equal "MyString2", Interest.find(collections(:one).id).name
end

