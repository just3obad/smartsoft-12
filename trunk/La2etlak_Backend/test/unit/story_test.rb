require 'test_helper'

class StoryTest < ActiveSupport::TestCase

  #test "the truth" do
  #  assert true
  #end
  
  #Author: Lydia
  test "no likes should return an empty list RED" do
    interest = Interest.create!(name: "Test Interest", description: "Description
    of Test Interest")
    story = Story.create!(interest_id: 1, title: "Test Story", 
    content: "Test content")
    list = story.liked
    assert_equal(list.count, 0)
  end
  
   test "no dislikes should return an empty list RED" do
    interest = Interest.create!(name: "Test Interest", description: "Description
    of Test Interest")
    story = Story.create!(interest_id: 1, title: "Test Story", 
    content: "Test content")
    list = story.disliked
    assert_equal(list.count, 0)
  end
  
  test "some likes should return 3 RED" do
    interest = Interest.create!(name: "Test Interest", description: "Description
    of Test Interest")
    story = Story.create!(interest_id: 1, title: "Test Story", 
    content: "Test content")
    user1 = User.create!(name: "Test user1",email: "test1@user.com")
    user2 = User.create!(name: "Test user2",email: "test2@user.com")
    user3 = User.create!(name: "Test user3",email: "test3@user.com")
    like1 = Likedislike.new
    like1.likedisliker = user1
    like1.action = 1
    like1.likedisliked_story = story
    like1.save
    like2 = Likedislike.new
    like2.likedisliker = user2
    like2.action = 1
    like2.likedisliked_story = story
    like2.save
    like3 = Likedislike.new
    like3.likedisliker = user3
    like3.action = 1
    like3.likedisliked_story = story
    like3.save
    list = story.liked
    assert_equal(list.count, 3)
  end
  
  test "some dislikes should return 3 RED" do
    interest = Interest.create!(name: "Test Interest", description: "Description
    of Test Interest")
    story = Story.create!(interest_id: 1, title: "Test Story", 
    content: "Test content")
    user1 = User.create!(name: "Test user1",email: "test1@user.com")
    user2 = User.create!(name: "Test user2",email: "test2@user.com")
    user3 = User.create!(name: "Test user3",email: "test3@user.com")
    dislike1 = Likedislike.new
    dislike1.likedisliker = user1
    dislike1.action = -1
    dislike1.likedisliked_story = story
    dislike1.save
    dislike2 = Likedislike.new
    dislike2.likedisliker = user2
    dislike2.action = -1
    dislike2.likedisliked_story = story
    dislike2.save
    dislike3 = Likedislike.new
    dislike3.likedisliker = user3
    dislike3.action = -1
    dislike3.likedisliked_story = story
    dislike3.save
    list = story.disliked
    assert_equal(list.count, 3)
  end

#Author : Shafei
    test "story get rank all time RED" do
		user = User.new
		user.save
		story = Story.new
		story.save
		comment = Comment.new
		comment.user = user
		comment.story = story
		assert_equal(story.get_story_rank_all_time(),1,"Action returns wrong number")
	end
	
#Author : Shafei
    test "story get rank last 30 days RED" do
		user = User.new
		user.save
		story = Story.new
		story.save
		comment = Comment.new
		comment.created_at = 40.days.ago
		comment.user = user
		comment.story = story
		comment2 = Comment.new
		comment2.user = user
		comment2.story = story
		assert_equal(story.get_story_rank_all_time(),1,"Action returns wrong number")
	end
	
#Author : Shafei
	test "stories get rank all time RED" do
		top_stories = Array.new#
		comments = Array.new#
		user = User.new
		user.save
		i = 1
		top_stories.each do |story|
			story = Story.new
			story.save
			top_stories >> story
			for j in 1...i
				comment = Comment.new
				comment.user = user
				comment.story = story
				comment.save
				comments >> comment
			end
			i = i + 1
		end
		story = Story.new
		stories = story.get_stories_ranking_all_time
		assert_nil(stories.first,"")
		stories.each do |story|
			assert_equal(story.id, top_stories[i].id, "Ranking not correct")
			i = i - 1
		end
	end
	
test "stories get rank last 30 days RED" do
		top_stories = Array.new#
		comments = Array.new#
		user = User.new
		user.save
		i = 1
		top_stories.each do |story|
			story = Story.new
			story.save
			top_stories >> story
			for j in 1...i
				comment = Comment.new
				comment.user = user
				comment.story = story
				comment.save
				comments >> comment
				comments >> comment2
			end
				for j in (i+5)...1
				comment = Comment.new
				comment.created_at = 50.days.ago
				comment.user = user
				comment.story = story
				comment.save
				comments >> comment
			end
			i = i + 1
		end
		story = Story.new
		stories = story.get_stories_ranking_all_time
		assert_nil(stories.first,"")
		stories.each do |story|
			assert_equal(story.id, top_stories[i].id, "Ranking not correct")
			i = i - 1
		end
	end
	
end
