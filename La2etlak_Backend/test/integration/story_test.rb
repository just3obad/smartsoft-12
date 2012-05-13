require 'test_helper'

class StoryTest < ActionDispatch::IntegrationTest
  # test "the truth" do
  #   assert true
  # end

#Author: khaled.elbhaey 
  test "should route to disliked_mobile_show RED" do
    assert_routing 'stories/1/disliked_mobile_show/1', { :controller => "stories", :action => "disliked_mobile_show", :id => "1", :id2 => "1" }
  end
#Author: khaled.elbhaey 
  test "should route to liked_mobile_show RED" do
    assert_routing 'stories/1/liked_mobile_show/1', { :controller => "stories", :action => "liked_mobile_show", :id => "1", :id2 => "1" }
  end
#Author: khaled.elbhaey 
  test "should route to recommend_story_mobile_show RED" do
    assert_routing 'stories/1/recommend_story_mobile_show', { :controller => "stories", :action => "recommend_story_mobile_show", :sid => "1"}
  end

#Author: khaled.elbhaey 
  test "the view of friends who liked RED" do
    new_user=User.create(:email=>"kd@abc.com")
    new_interest=Interest.create(:name=>"sport", :description=>"sport is good")
    new_story=Story.create(:title=>"messi", :content=>"won a lot", :interest_id=>1)

    list=new_story.view_friends_like(new_user)
     if !list.empty?
      get like_path(new_story, new_user)
      assert_select 'div[ id=liked]'
     else
      get like_path(new_story, new_user) 
      assert_select 'div[ id=error_explanation]'
     end
  end
#Author: khaled.elbhaey 
  test "the view of friends who disliked RED" do
   new_user=User.create(:email=>"kd@abc.com")
    new_interest=Interest.create(:name=>"sport", :description=>"sport is good")
    new_story=Story.create(:title=>"messi", :content=>"won a lot", :interest_id=>1)
    list=new_story.view_friends_dislike(new_user)
    if !list.empty?

     get dislike_path(new_story, new_user)
     assert_select 'div[ id=disliked]'
    else

     get dislike_path(new_story, new_user) 
     assert_select 'div[ id=error_explanation]'
    end
  end
#Author: khaled.elbhaey 
  test "the view of recommendation of story RED" do
   assert_select 'listbox[id=femails]'
   assert_select 'form[ id=recommendation]'
   assert_select 'text[ id=fmail]'
   assert_select 'div[ id=submit]'
   assert_select 'div[ id=back]'

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
	test "should route to readmore view RED" do
            assert_routing 'stories/1/get', { :controller => "stories", :action =>    		                   "get", :id => "1"}
  	end


end

