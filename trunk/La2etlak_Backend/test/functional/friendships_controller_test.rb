require 'test_helper'

class FriendshipsControllerTest < ActionController::TestCase
  # test "the truth" do
  #   assert true
  # end

  #Author: Yahia
  test 'should be able to index friendships request RED' do
    u1 = users(:one)
    u2 = users(:two)
    u1.invite u2
    u2.approve u1    

  	get :index, {}, {user_id: 1}
  	assert_response :success, "Get request should be successfull"
  		
  end 

  #Author: Yahia
  test 'should be able to create friendship RED' do  	
    u1 = users(:one)
    u2 = users(:two)
    u1.invite u2
  	assert_difference('Friendship.count') do
  		get :create, {user_id: 2}, {user_id: 1}
      #, 'The count of Frienships should be changed'
  	end 
  end 

  #Author: Yahia
  test 'should be able to approve friendship RED' do  	
    u1 = users(:one)
    u2 = users(:two)
    u1.invite u2
    assert Friendship.find_by_user_id(1).nil?, 'Friendship shouldn\'t be nil' 
    
    puts Friendship.find_by_user_id(1).pending
  	assert_difference('Friendship.find_by_user_id(1).pending') do
  		get :approve, {user_id: 1}, {user_id: 2}
      # , 'The pending of a Frienships should be changed'

  	end 
  end 

  #Author: Yahia
  test 'should be able to delete friendship RED' do  	
    u1 = users(:one)
    u2 = users(:two)
    u1.invite u2
    u2.approve u1    
  	assert_difference('Friendship.count') do
  		get :remove, {user_id: 2}, {user_id: 1}
      # , 'The pending of a Frienships should be changed'

  	end 
  end 


end
