require 'test_helper'

class InterestsControllerTest < ActionController::TestCase
#Author:Jailan
test "should update interest" do
  put :update, :id => interests(:one), :interest => {:name => 'hello', :description => 'desc2'}
  assert_equal "hello", interests(:one).name 
  assert_equal "desc2", interests(:one).description

end




#Author:Jailan
  test "should block interest" do
  put :toggle, :id => interests(:two), :interest => {:deleted => true}
  assert_equal true, assigns(:interest).deleted
end

#Author:Jailan
test "should restore interest RED" do
  put :toggle, :id => interests(:one), :interest => {:deleted => false}
  assert_equal false, assigns(:interest).deleted
end



#Author:Jailan

 test "should have restore button when blocked RED" do
 put :toggle, :id => interests(:two), :interest => {:deleted => true}
 assert_select 'submit[class=btn btn-success]',1

end
#Author:Jailan
test "should have block button when not blocked RED" do
 put :toggle, :id => interests(:one), :interest => {:deleted => false}
 assert_tag :tag =>"a" , :content =>"Block"

end

#Author:Jailan
test "should have savechanges button RED" do
 put :toggle, :id => interests(:two), :interest => {:deleted => true}
 assert_tag :tag =>"a" , :content =>"Restore"

end
end
