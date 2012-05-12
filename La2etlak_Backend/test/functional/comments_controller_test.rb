require 'test_helper'

class CommentsControllerTest < ActionController::TestCase
   # Author: Menisy
  test "should create comment RED" do
    assert_difference('Comment.count') do
      post :create, :comment => {:content => "lololo" , :story_id => Story.first.id , :user_id => User.first.id}
    end
    assert_redirected_to :controller => "stories" , :action => "mobile_show", :id2 => User.first.id, :id => Story.first.id
  end
end
