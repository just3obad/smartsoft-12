require 'test_helper'

class UserProfilePageTest < ActionDispatch::IntegrationTest
  #this test is to make sure that the route is not lost 
  def setup
     @usr=User.create!(:email=>"exampleuserpage@gmail.com")
     get user_path(@usr)
  end

  test "UserProfilePageRoute" do
     assert_routing '/users/1', { :controller => "users", :action => "show", :id=> "1"}
  end
  
  test "UserProfilePage should return success response" do
     
     assert_response :success
  end
  
  test "UserProfilePage should contain all needed buttons RED" do
     
     assert_select "a[id=statistics_button]"
     assert_select "a[id=reset_password_button]"
     assert_select "a[id=deactivate_button]"
  end

  test "UserProfilePage should contain all the right main divs RED" do
    assert_select "div[id=friends]"
    assert_select "div[id=interests]"
    assert_select "div[id=recentActivity]"
  end

  test "UserProfilePage contains usr info RED" do
    assert_select "div[id=user-personal-info]" do
      assert_select "span[id=my-email]", @usr.email
      assert_select "span[id=is-active]", "Active"
    end
  end

  test "UserProfilePage get right no of friends RED" do
    assert_select "div[id=friends]" do
      assert_select "div[class=well-user-component]", @usr.get_friends.count
    end
  end 

  test "UserProfilePage get right no of interests of a user with no interests RED" do
    assert_select "div[id=interests]" do
      assert_select "div[class=well-interest-component]", 0
    end
  end 

  test "UserProfilePage get right no of interests of a user who added two interests RED" do
    int1=Interest.new(:name=> "InterestDummy1")
    assert int1.save
    int2=Interest.new(:name=> "InterestDummy2")
    assert int2.save
    @usr.added_interests << int1
    @usr.added_interests << int2
    assert_select "div[id=interests]" do
      assert_select "div[class=well-interest-component]", 2
    end
  end   

  test "UserProfilePage get right no of activities in the last 30 days RED" do
    count1=@usr.get_recent_activity(30.days.ago).count
    int1=Interest.new(:name=> "InterestDummy1")
    assert int1.save
    int2=Interest.new(:name=> "InterestDummy2")
    assert int2.save
    @usr.added_interests << int1
    @usr.added_interests << int2
    story=Story.new(:title=> "RandomTitle")
    story.interest = int1
    assert story.save
    @usr.shared_stories << story
    count2=@usr.get_recent_activity(30.days.ago).count
    assert_equal(count1+3,count2)
    assert_select "div[id=recentActivity]" do
      assert_select "div[class=well-feed-component-user-profile-page]", count2
    end
  end

end
