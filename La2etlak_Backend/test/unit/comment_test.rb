require 'test_helper'

class CommentTest < ActiveSupport::TestCase
  test "should not save without content red" do
    comment = Comment.new
    assert !comment.save, "Should not save without content"
  end
  test "should save with content" do  
    comment = Comment.new
    comment.content = "I'm the content"
    assert comment.save, "Should save with content"
  end
end
