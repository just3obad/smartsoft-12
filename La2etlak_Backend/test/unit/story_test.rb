require 'test_helper'

class StoryTest < ActiveSupport::TestCase
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
