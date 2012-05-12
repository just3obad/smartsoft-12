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


end

