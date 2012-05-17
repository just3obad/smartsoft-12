require 'test_helper'

class SearchTest < ActionDispatch::IntegrationTest
  fixtures :all
  
  # test "the truth" do
  #   assert true
  # end
  
  #Author: Lydia
  test "route of search page exists RED" do
     assert_routing '/admins/search', { :controller => "admins", :action => "search"}
  end
  
  test "results of empty search query exists RED" do
      post 'admins/search', {:controller =>"admins" , :action => "search",:query=>"",:autocomplete=>{:query =>""}}
  end
  
  test "results of nonempty search query exists RED" do
      post 'admins/search', {:controller =>"admins" , :action => "search",:query=>"lydia", :autocomplete=>{:query =>"lydia"}}
  end
  
  test "empty search query RED" do
  
      users = Admin.search_user("")
      stories = Admin.search_story("")
      interests = Admin.search_interest("")
      uCount = users.count
      sCount = stories.count
      iCount = interests.count
      post 'admins/search', {:controller =>"admins" , :action => "search",:query=>"", :autocomplete=>{:query =>""}}
      assert_select "div[id=noSearchQuery]",'Please enter query into the search bar.' do
        assert_select "div[class=well-user-component]" , uCount
        assert_select "div[class=well-story-component]" , sCount
        assert_select "div[class=well-interest-component]" , iCount
      end
  end
  
  test "no results found RED" do
      users = Admin.search_user("blabla")
      stories = Admin.search_story("blabla")
      interests = Admin.search_interest("blabla")
      uCount = users.count
      sCount = stories.count
      iCount = interests.count
      post 'admins/search', {:controller =>"admins" , :action => "search",:query=>"blabla",:autocomplete=>{:query =>"blabla"}}
      assert_select "div[id=noSearchQuery]",'There are no matching results.' do
        assert_select "div[class=well-user-component]" , uCount
        assert_select "div[class=well-story-component]" , sCount
        assert_select "div[class=well-interest-component]" , iCount
      end
  end
  
  test "get results of nonempty search query RED" do
      users = Admin.search_user("lydia")
      stories = Admin.search_story("lydia")
      interests = Admin.search_interest("lydia")
      uCount = users.count
      sCount = stories.count
      iCount = interests.count
      post 'admins/search', {:controller =>"admins", :action =>"search", :query=>"lydia",:autocomplete=>{:query =>"lydia"}}
      assert_select "div[id=usersSearchResults]" do
        assert_select "div[class=well-user-component]" , uCount
      end
      assert_select "div[id=storiesSearchResults]" do
        assert_select "div[class=well-story-component]" , sCount
      end
      assert_select "div[id=interestsSearchResults]" do
        assert_select "div[class=well-interest-component]" , iCount
      end
  end
  
  test "button view all for users RED" do
      User.create!(name: "lydia",email: "loulou@mail.com", password: "1234", password_confirmation: "1234")
      users = Admin.search_user("lydia")
      post 'admins/search', {:controller =>"admins", :action =>"search", :query=>"lydia",:autocomplete=>{:query =>"lydia"}}
      assert_select "a[href=?]", '/admins/all_results?type=1' , 'View All Users Results' 
  end
  
  test "button view all for stories RED" do
      int = Interest.create!(name: "Test Interest", description: "Description of Test Interest")
      story = Story.new
      story.title = "lydia"
      story.interest = int
      story.content = "Test content"
      story.save
      stories = Admin.search_story("lydia")
      post 'admins/search', {:controller =>"admins", :action =>"search", :query=>"lydia",:autocomplete=>{:query =>"lydia"}}
      assert_select "a[href=?]", '/admins/all_results?type=2' , 'View All Stories Results'
  end
  
  test "button view all for interests RED" do
      Interest.create!(name: "lydia", description: "Description of Test Interest")
      interests = Admin.search_interest("lydia")
      post 'admins/search', {:controller =>"admins", :action =>"search", :query=>"lydia",:autocomplete=>{:query =>"lydia"}}
      assert_select "a[href=?]", '/admins/all_results?type=3' ,  'View All Interests Results' 
  end
  
  test "filter panel exists RED" do
      User.create!(name: "lydia",email: "loulou@mail.com", password: "1234", password_confirmation: "1234")
      int = Interest.create!(name: "lydia", description: "Description of Test Interest")
      story = Story.new
      story.title = "lydia"
      story.interest = int
      story.content = "Test content"
      story.save
      post 'admins/search', {:controller =>"admins", :action =>"search", :query=>"lydia",:autocomplete=>{:query =>"lydia"}}
      assert_select "div[id=filterPanel]" do
        assert_select "label[class=checkbox]" , 3
      end
  end
  
  test "viewing all users results RED" do
      User.create!(name: "lydia",email: "loulou@mail.com", password: "1234", password_confirmation: "1234")
      users = Admin.search_user("lydia")
      uCount = users.count
      post 'admins/search', {:controller =>"admins", :action =>"search", :query=>"lydia",:autocomplete=>{:query =>"lydia"}}
      post 'admins/all_results', {:controller =>"admins", :action =>"all_results", :type=>"1"}
      assert_select "div[id=searchResults]" do
        assert_select "div[class=well-user-component]" , uCount
      end
  end
  
  test "viewing all stories results RED" do
      int = Interest.create!(name: "lydia", description: "Description of Test Interest")
      story = Story.new
      story.title = "lydia"
      story.interest = int
      story.content = "Test content"
      story.save
      stories = Admin.search_story("lydia")
      sCount = stories.count
      post 'admins/search', {:controller =>"admins", :action =>"search", :query=>"lydia",:autocomplete=>{:query =>"lydia"}}
      post 'admins/all_results', {:controller =>"admins", :action =>"all_results", :type=>"2"}
      assert_select "div[id=searchResults]" do
        assert_select "div[class=well-story-component]" , sCount
      end
  end
  
  test "viewing all interests results RED" do
      Interest.create!(name: "lydia", description: "Description of Test Interest")
      interests = Admin.search_interest("lydia")
      iCount = stories.count
      post 'admins/search', {:controller =>"admins", :action =>"search", :query=>"lydia",:autocomplete=>{:query =>"lydia"}}
      post 'admins/all_results', {:controller =>"admins", :action =>"all_results", :type=>"3"}
      assert_select "div[id=searchResults]" do
        assert_select "div[class=well-interest-component]" , iCount
      end
  end
end
