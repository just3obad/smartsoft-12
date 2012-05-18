require 'test_helper'

class StoryTest < ActionDispatch::IntegrationTest
  # test "the truth" do
  #   assert true
  # end

#Author: khaled.elbhaey 
  test "should route to disliked_mobile_show " do
    assert_routing 'mob/stories/disliked_mobile_show/1', { :controller => "stories", :action => "disliked_mobile_show", :id => "1"}
  end
#Author: khaled.elbhaey 
  test "should route to liked_mobile_show " do
    assert_routing 'mob/stories/liked_mobile_show/1', { :controller => "stories", :action => "liked_mobile_show", :id => "1"}
  end
#Author: khaled.elbhaey 
  test "should route to recommend_story_mobile_show " do
    assert_routing 'mob/stories/recommend_story_mobile_show/1', { :controller => "stories", :action => "recommend_story_mobile_show", :sid => "1"}
  end

#Author: khaled.elbhaey 
  test "should route to recommend_success_mobile_show " do
    assert_routing 'mob/stories/recommend_success_mobile_show/1', { :controller => "stories", :action => "recommend_success_mobile_show", :sid => "1"}
  end



    #Author: Rana
    test "form for button block interest in read more RED" do
      this_interest = Interest.create :name => "Sports", :description => "hey sporty"
      this_story= Story.new :title => "Story1", :interest_id => this_interest
      this_story.interest = this_interest
      this_story.save
      this_user = User.create :name => "amr", :email => "amr@abc.com"
      get read_more_path(this_story)
      assert_select "form[action = 'this_user.block_interest']"
      assert_select 'div[id="bbutton"]'
    end

    #Author: Rana 
    test "form for button block story in read more RED" do
      this_interest = Interest.create :name => "Sports", :description => "hey sporty"
      this_story= Story.new :title => "Story1", :interest_id => this_interest
      this_story.interest = this_interest
      this_story.save
      this_user = User.create :name => "amr", :email => "amr@abc.com"
      get read_more_path(this_story)
      assert_select "form[action = 'this_user.block_story']"
      assert_select 'div[id="bsbutton"]'
    end

# Author : Omar
	test "should route to readmore view" do
            assert_routing 'stories/1/get', { :controller => "stories", :action =>    		                   "get", :id => "1"}
  	end


end

