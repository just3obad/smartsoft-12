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
    UserSession.create u1
  	get :index
  	assert_response :success, "Get request should be successfull"
    assert_select "div[id=#{u2.id}]"
  		
  end 

  #Author: Yahia
  test 'should be able to create friendship RED' do  	
    u1 = users(:ben)
    u2 = users(:ahmed)
    u1.invite u2
  	assert_difference('Friendship.count') do
  		get :create, {user_id: 2}
      #, 'The count of Frienships should be changed'
  	end 
    assert_select "div["

  end 

  #Author: Yahia
  test 'should be able to approve friendship RED' do  	
    u1 = users(:ben)
    u2 = users(:ahmed)
    u1.invite u2
    assert Friendship.find_by_user_id(1).nil?, 'Friendship shouldn\'t be nil' 
    UserSession.create u2
    # puts Friendship.find_by_user_id(1).pending
  	assert_difference('Friendship.find_by_user_id(1).pending') do
  		get :approve, {user_id: 1}
  	end 
    assert_select 'div[id=notification][class=flash-red well]'

  end 

  #Author: Yahia
  test 'should be able to delete friendship RED' do  	
    u1 = users(:ben)
    u2 = users(:ahmed)
    u1.invite u2
    u2.approve u1    
    UserSession.create u2
  	assert_difference('Friendship.count') do
  		get :remove, {user_id: 2}
      # , 'The pending of a Frienships should be changed'
  	end 
    assert_select 'div[id=notification][class=flash-green well]'

  end 


end
