require 'test_helper'

class AutoCompleteTest < ActionDispatch::IntegrationTest
  #this test is to make sure that the route is not lost 
  test "AdminMainPageRoute" do
     assert_routing '/admins/index', { :controller => "admins", :action => "index"}
    end
  #this test is to make sure that the main page does exist and i get a 200OK response     
  test "AdminMainPage" do
      get "/admins/index"
      assert_response :success
    end
  #this test is to make sure that the route of auto complete is not lost 
  test "AutoCompleteRoute" do
     post 'autocomplete/auto_complete_for_autocomplete_query', {:controller =>"autocomplete" , :action => "auto_complete_for_autocomplete_query",:autocomplete=>{:query =>""}}
  end
  #this test is to make sure that i have a field for search and autocomplete
  test "AutoCompleteFieldExistance" do
     get "/admins/index"
    assert_select "input[id=autocomplete_query]"
  end
  #this test is to make sure that that there is a hidden div for results of auto complete
  test "AutoCompleteResultDiv" do
     get "/admins/index"
    assert_select "div[id=autocomplete_query_auto_complete]"
  end
  #this test is to make sure that the auto completion really works
  test "AutoCompleteTest" do
     get "/admins/index"
     User.create!(name: "dummy0", email: "dummy0@gmail.com")
     User.create!(name: "dummy1", email: "dummy1@gmail.com")
     post 'autocomplete/auto_complete_for_autocomplete_query', {:controller =>"autocomplete" , :action => "auto_complete_for_autocomplete_query",:autocomplete=>{:query =>"d"}}
     @response.content_type = 'text/html'
     assert_tag :tag =>"ul" , :child => {:tag => "li", :content =>"dummy0"}
   end
end
