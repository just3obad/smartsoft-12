require 'test_helper'

class FriendshipsControllerTest < ActionController::TestCase
  # test "the truth" do
  #   assert true
  # end

  # Notice on (13th May), I haven't settled on how the 
  # views will be desigined, therefore, I selected text wit
  # any division. Will enhance the tests after setteling on the design

  #Author: Yahia
  test 'should be able to index friendships request RED' do
    u1 = users(:one)
    u2 = users(:two)
    u1.invite u2
    u2.approve u1    

  	get :index, {}, {user_id: 1}
  	assert_response :success, "Get request should be successfull"
    assert_select '*', "#{u2.first_name}"
    assert_select '*', "#{u2.last_name}"
    assert_select '*', "#{u2.email}"
  		
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
    assert_select '*', "Your request has been sent to #{u2.email}"

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
    assert_select '*', "Your request has been sent to #{u2.email}"

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
    assert_select '*', "You hae deleted sent to #{u2.email}"

  end 


end