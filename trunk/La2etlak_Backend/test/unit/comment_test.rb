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
  	assert !comment.save , "Should not save without a story"
  end
  test "should up a comment" do
    assert_difference('CommentUpDown.find_all_by_action(1).count',1) do
      up_comment
    end
  end
  test "should down a comment" do
    assert_difference('CommentUpDown.find_all_by_action(2).count',1) do
      down_comment
    end
  end
  test "should un-up when downed" do
    down_comment
    assert_difference('CommentUpDown.find_all_by_action(2).count',-1) do
      up_comment
    end
  end
  test "should un-down when upped" do
    up_comment
    assert_difference('CommentUpDown.find_all_by_action(1).count',-1) do
      down_comment
    end
  end
  test "should un-up when re-upped red" do
    up_comment
    assert_difference('CommentUpDown.find_all_by_action(1).count',-1) do
      up_comment
    end
  end
  test "should un-down when re-downed red" do
    down_comment
    assert_difference('CommentUpDown.find_all_by_action(2).count',-1) do
      down_comment
    end
  end

  def up_comment
    comment = Comment.first
    comment.content = "content"
    user = User.first
    comment.user = user
    comment.story = Story.first
    comment.save
    comment.up_comment(user)
  end
  def down_comment
    comment = Comment.first
    comment.content = "content"
    user = User.first
    comment.user = user
    comment.story = Story.first
    comment.save
    comment.down_comment(user)
  end  
end
