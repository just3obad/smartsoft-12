require 'test_helper'

class CommentsControllerTest < ActionController::TestCase
   # Author: Menisy
  test "should create comment" do
    assert_difference('Comment.count') do
      post :create, :comment => {:content => "lololo" , :story_id => Story.first.id , :user_id => User.first.id}
    end
    assert_redirected_to :controller => "stories" , :action => "get", :id => Story.first.id
  end
end
