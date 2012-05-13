require 'test_helper'

class InterestTest < ActiveSupport::TestCase






#Author:Jailan
 test "should save interest when updated GREEN" do
    interest = Interest.create!(name: "my Test", description: "blabla")
    

   

    @inty = Interest.my_update( interest.id ,{ :name => "fff" } )

    assert interest.save
 
end

#Author:Jailan
 test "shouldn't update interest when name is blank GREEN" do
    interest = Interest.create!(name: "my Test", description: "blabla")
    @inty = Interest.my_update( interest.id ,{ :name => "" } )

    assert interest.save ," Cannot save an Interest with a blank Name"
  end
#this test is checked after implementing the browse story (uploading image of Interest)
#Author:Jailan
 #test "shouldn't update interest when photo is blank" do
   # interest = Interest.create!(name: "my Test", description: "blabla")
    #interest.update(name:"desc" )

    #assert !interest.save
  #end




 test "should save interest when toggled RED" do
    interest = Interest.create!(name: "my Test", description: "blabla")
    interest.toggle

    assert interest.save
  end



end
