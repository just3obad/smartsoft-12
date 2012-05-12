require 'test_helper'

class CommentsControllerTest < ActionController::TestCase
  test "should create comment" do
    assert_difference('Comment.count') do
      post :create, :content => "lololo"
    end
    assert_redirected_to comment_path(assigns(:comment))
    assert_equal 'comment success', flash[:notice]
  end

  test "should get index" do
    get :index
    assert_response :success
  end

end
