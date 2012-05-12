require 'test_helper'

class StoriesControllerTest < ActionController::TestCase
  # test "the truth" do
  #   assert true
  # end

   # Author: Menisy
  test "should up comment" do
  	assert_difference('CommentUpDown.find_all_by_action(1).count',1) do
  		get :up_comment, :comment_id => Comment.first.id, :user_id => User.first.id
  	end
  end
  
   # Author: Menisy
  test "should down comment" do
  	assert_difference('CommentUpDown.find_all_by_action(2).count',1) do
  		get :down_comment, :comment_id => Comment.first.id, :user_id => User.first.id
  	end
  end
  
  
  #Author : Omar
  test "create story view" do
	st = Story.new
	st.interest_id = 1
	st.title = "title"
	st.save	
	assert get(:get , {'id' => st.id })
  end
  

  
end
