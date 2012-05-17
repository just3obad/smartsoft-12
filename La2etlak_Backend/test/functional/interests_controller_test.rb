require 'test_helper'

class InterestsControllerTest < ActionController::TestCase


  #Author Jailan
  #checking route is not lost 
  test "Interest main page routes GREEN" do
     interest = Interest.first
     get :show, :id => interest.id
     assert_routing '/interests/'+interest.id.to_s, {:controller =>"interests", :action => "show", :id=> "1"}
    end

  #Author Jailan
  #this test is to make sure that the main page does exist and i get a 200OK response     
  test "Interest Main Page GREEN" do
       get :show, :id => 1
      assert_response :success
    end

 

#Author: Jailan
test "should update Interest GREEN" do
  put :update, :id => interests(:two), :interest => {:name => 'MyString2'}
 assert_equal "MyString2", Interest.find(interests(:two).id).name
end

#Author: Jailan
test "shouldn't update Interest with a blank name GREEN" do
  put :update, :id => interests(:one), :interest => {:description => 'lala'}
 assert_not_equal "lala", Interest.find(interests(:one).id).name
end

#Author: Jailan
test "shouldn't update Interest when it's blocked GREEN" do
  put :toggle, :id => interests(:two), :interest => {:deleted => true}
  assert_equal true, assigns(:interest).deleted
  put :update, :id => interests(:two), :interest => {:name => 'lala'}

 assert_not_equal "lala", Interest.find(interests(:two).id).name
end




#Author:Jailan
  test "should block interest GREEN" do
  put :toggle, :id => interests(:two), :interest => {:deleted => true}
  assert_equal true, assigns(:interest).deleted
end

#Author:Jailan
test "should restore interest GREEN" do
  put :toggle, :id => interests(:one), :interest => {:deleted => false}
  assert_equal false, assigns(:interest).deleted
end



#Author:Jailan

 test "should have restore button when blocked GREEN" do
 put :toggle, :id => 2, :interest => {:deleted => false}
 get :show, :id => 2
assert_select 'div[id = "restore"]'
end
#Author:Jailan
test "should have block button when not blocked GREEN" do
 put :toggle, :id => 1, :interest => {:deleted => true}
 get :show, :id => 1
assert_select 'div[id = "block"]'

end

#Author:Jailan
  test "should have an update form to fill with save changes button GREEN" do

     get :show, :id => 1
    assert_select 'div[id = "update"]'

  end

#Author:Jailan
  test "should have a status form showing the Interests Status GREEN" do
     get :show, :id => 1
    assert_select 'div[id = "status"]'

  end


#Author:Jailan "Testing the browse plugin"
  test "should have an image saved  after creating GREEN" do
     get :show, :id => 1
    assert_select 'div[id = "image"]'

  end


end
