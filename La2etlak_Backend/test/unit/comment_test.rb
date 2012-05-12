require 'test_helper'

class CommentTest < ActiveSupport::TestCase
  test "should not save without content red" do
    comment = Comment.new
    assert !comment.save, "Should not save without content"
  end
  test "should save with content" do  
    comment = Comment.new
    comment.content = "I'm the content lol"
    assert comment.save, "Should save with content"
  end
  test "should not save without user red" do
  	comment = Comment.new
  	comment.content = "content again"
  	comment.story = Story.first
  	assert !comment.save , "Should not save without a user"
  end
    test "should not save without story red" do
  	comment = Comment.new
  	comment.content = "content again"
  	comment.user = User.first
  	assert !comment.save , "Should not save without a stpry"
  end
end
