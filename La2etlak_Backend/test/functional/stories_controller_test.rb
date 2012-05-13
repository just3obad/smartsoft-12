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
  	assert_redirected_to :action => "mobile_show", :id2 => User.first.id
  end

  # Author: Menisy
  test "should down comment" do
  	assert_difference('CommentUpDown.find_all_by_action(2).count',1) do
  		get :down_comment, :comment_id => Comment.first.id, :user_id => User.first.id
  	end
  	assert_redirected_to :action => "mobile_show", :id2 => User.first.id
  end

  # Author: Menisy
  test "mobile show view" do
  	story = Story.first
  	comment = Comment.new
  	comment.content = "lol"
  	comment.user = User.first
  	comment.story = story
  	comment.save
  	get :mobile_show, :id => Story.first.id , :id2 => User.first.id
  	assert_select 'ul.nav' do
  		assert_select 'li', 6				# 2 for the navbar + 4 four the 1st comment
		end
		assert_select 'div[class=story-component-box-mobile]',1
		assert_select 'input[class=btn btn-large]',1
		assert_select 'div[class=story-comment-box-mobile]',true
		assert_select 'textarea[class=story-textarea-mobile]',1
		assert_select 'title', {:count => 1, :text => "My Story Page!"},
    "Wrong title or more than one title element"
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
