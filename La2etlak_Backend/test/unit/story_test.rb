require 'test_helper'

class StoryTest < ActiveSupport::TestCase

  #test "the truth" do
  #  assert true
  #end
  
   #Author: Lydia
  test "no likes should return an empty list" do
    int = Interest.create!(name: "Test Interest", description: "Description
    of Test Interest")
    story = Story.new
    story.title = "Test Story"
    story.interest = int
    story.content = "Test content"
    story.save
    list = story.liked
    assert_equal(list.count, 0)
  end
  
  #Author: Lydia
  test "no dislikes should return an empty list" do
    int = Interest.create!(name: "Test Interest", description: "Description
    of Test Interest")
    story = Story.new
    story.title = "Test Story"
    story.interest = int
    story.content = "Test content"
    story.save
    list = story.disliked
    assert_equal(list.count, 0)
  end
  
  #Author: Lydia
  test "some likes should return 3" do
    int = Interest.create!(name: "Test Interest", description: "Description
    of Test Interest")
    story = Story.new
    story.title = "Test Story"
    story.interest = int
    story.content = "Test content"
    story.save
    user1 = User.create!(name: "Test user1",email: "test1@user.com",password: "123456",password_confirmation: "123456")
    user2 = User.create!(name: "Test user2",email: "test2@user.com",password: "123456",password_confirmation: "123456")
    user3 = User.create!(name: "Test user3",email: "test3@user.com",password: "123456",password_confirmation: "123456")
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
  
  #Author: Lydia
  test "some dislikes should return 3" do
    int = Interest.create!(name: "Test Interest", description: "Description
    of Test Interest")
    story = Story.new
    story.title = "Test Story"
    story.interest = int
    story.content = "Test content"
    story.save
    user1 = User.create!(name: "Test user1",email: "test1@user.com",password: "123456",password_confirmation: "123456")
    user2 = User.create!(name: "Test user2",email: "test2@user.com",password: "123456",password_confirmation: "123456")
    user3 = User.create!(name: "Test user3",email: "test3@user.com",password: "123456",password_confirmation: "123456")
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
    test "story get rank all time" do
		user = users(:one)
		story = stories(:one)
		comment = comments(:one)
		comment.user = user
		comment.story = story
		comment.save
		assert_equal(story.get_story_rank_all_time(),31,"Action returns wrong number")
	end
	
#Author : Shafei
    test "story get rank last 30 days" do
		user = users(:one)
		story = stories(:one)
		comment = comments(:two)
		comment.created_at = 40.days.ago
		comment.user = user
		comment.story = story
		comment.save
		comment2 = comments(:one)
		comment2.user = user
		comment2.story = story
		comment2.save
		assert_equal(story.get_story_rank_all_time(),32,"Action returns wrong number")
	end
	
#Author : Shafei
	test "stories get rank all time" do
		top_stories = Array.new#
		top_stories << stories(:two)
		top_stories << stories(:one)
		top_stories << stories(:five)
		top_stories << stories(:four)
		top_stories << stories(:three)
	
		for i in 0...5
			assert_equal(top_stories[i].id, Story.get_stories_ranking_all_time[i].id, "Ranking not correct")
		end
	end
	
#Author : Shafei
	test "stories get rank last 30 days" do
		top_stories = Array.new#
		top_stories << stories(:two)
		top_stories << stories(:one)
		top_stories << stories(:five)
		top_stories << stories(:four)
		top_stories << stories(:three)
		for i in 0...5
			assert_equal(top_stories[i].id, Story.get_stories_ranking_last_30_days[i].id, "Ranking not correct")
		end
	end
end
