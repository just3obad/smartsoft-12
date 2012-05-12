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
      post 'admins/search', {:controller =>"admins" , :action => "search",:query=>""}
  end
  
  test "results of nonempty search query exists RED" do
      post 'admins/search', {:controller =>"admins" , :action => "search",:query=>"lydia"}
  end
  
  test "empty search query RED" do
      post 'admins/search', {:controller =>"admins" , :action => "search",:query=>""}
      assert_select "div[id=noSearchQuery]",'Please enter query into the search bar.' do
        assert_select "div[class=well-user-component]" , 0
      end
  end
  
  test "no results found RED" do
      post 'admins/search', {:controller =>"admins" , :action => "search",:query=>"blabla"}
      assert_select "div[id=noSearchQuery]",'There are no matching results.' do
        assert_select "div[class=well-user-component]" , 0
      end
  end
  
  test "get user results of nonempty search query RED" do
      User.create!(name: "lydia", email: "l1@gmail.com")
      User.create!(name: "lydia", email: "l2@gmail.com")
      post 'admins/search', {:controller =>"admins", :action =>"search", :query=>"lydia"}
      assert_select "div[id=usersSearchResults]" do
        assert_select "div[class=well-user-component]" , 2
      end
  end
  
  test "button view all for users RED" do
      User.create!(name: "lydia", email: "l1@gmail.com")
      User.create!(name: "lydia", email: "l2@gmail.com")
      post 'admins/search', {:controller =>"admins", :action =>"search", :query=>"lydia"}
      assert_select "a[href=?]", '/admins/all_results?type=1' , 'View All Users Results' 
  end
  
  test "get story results of nonempty search query RED" do
      User.create!(name: "lydia", email: "l1@gmail.com")
      User.create!(name: "lydia", email: "l2@gmail.com")
      post 'admins/search', {:controller =>"admins", :action =>"search", :query=>"lydia"}
      assert_select "div[id=storiesSearchResults]" do
        assert_select "div[class=well-story-component]" , 0
      end
  end
  
  test "button view all for stories RED" do
      User.create!(name: "lydia", email: "l1@gmail.com")
      User.create!(name: "lydia", email: "l2@gmail.com")
      post 'admins/search', {:controller =>"admins", :action =>"search", :query=>"lydia"}
      assert_select "a[href=?]", '/admins/all_results?type=2' , 'View All Stories Results'
  end
  
  test "get interest results of nonempty search query RED" do
      User.create!(name: "lydia", email: "l1@gmail.com")
      User.create!(name: "lydia", email: "l2@gmail.com")
      post 'admins/search', {:controller =>"admins", :action =>"search", :query=>"lydia"}
      assert_select "div[id=interestsSearchResults]" do
        assert_select "div[class=well-interest-component]" , 0
      end
  end
  
  test "button view all for interests RED" do
      User.create!(name: "lydia", email: "l1@gmail.com")
      User.create!(name: "lydia", email: "l2@gmail.com")
      post 'admins/search', {:controller =>"admins", :action =>"search", :query=>"lydia"}
      assert_select "a[href=?]", '/admins/all_results?type=3' ,  'View All Interests Results' 
  end
  
  test "filter panel exists RED" do
      User.create!(name: "lydia", email: "l1@gmail.com")
      User.create!(name: "lydia", email: "l2@gmail.com")
      post 'admins/search', {:controller =>"admins", :action =>"search", :query=>"lydia"}
      assert_select "div[id=filterPanel]" do
        assert_select "label[class=checkbox]" , 3
      end
  end
  
  test "viewing all users results RED" do
      User.create!(name: "lydia", email: "l1@gmail.com")
      User.create!(name: "lydia", email: "l2@gmail.com")
      post 'admins/search', {:controller =>"admins", :action =>"search", :query=>"lydia"}
      post 'admins/all_results', {:controller =>"admins", :action =>"all_results", :type=>"1"}
      assert_select "div[id=searchResults]" do
        assert_select "div[class=well-user-component]" , 2
      end
  end
  
  test "viewing all stories results RED" do
      User.create!(name: "lydia", email: "l1@gmail.com")
      User.create!(name: "lydia", email: "l2@gmail.com")
      post 'admins/search', {:controller =>"admins", :action =>"search", :query=>"lydia"}
      post 'admins/all_results', {:controller =>"admins", :action =>"all_results", :type=>"2"}
      assert_select "div[id=searchResults]" do
        assert_select "div[class=well-story-component]" , 0
      end
  end
  
  test "viewing all interests results RED" do
      User.create!(name: "lydia", email: "l1@gmail.com")
      User.create!(name: "lydia", email: "l2@gmail.com")
      post 'admins/search', {:controller =>"admins", :action =>"search", :query=>"lydia"}
      post 'admins/all_results', {:controller =>"admins", :action =>"all_results", :type=>"3"}
      assert_select "div[id=searchResults]" do
        assert_select "div[class=well-interest-component]" , 0
      end
  end
end
