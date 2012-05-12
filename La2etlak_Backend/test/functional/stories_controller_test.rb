require 'test_helper'

class StoriesControllerTest < ActionController::TestCase
  # test "the truth" do
  #   assert true
  # end
  test "should create comment" do
  	p Story.first.id
  	p User.first.id
    assert_difference('Comment.count') do
      post :create_comment, :content => "lololo" , :id => Story.first.id , :user_id => User.first.id
    end
    assert_redirected_to comment_path(assigns(:comment))
  end
end
