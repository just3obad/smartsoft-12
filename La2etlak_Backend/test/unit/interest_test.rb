require 'test_helper'

class InterestTest < ActiveSupport::TestCase
  # test "the truth" do
  #   assert true
  # end

  ##########Author: Diab ############
  def setup
    Interest.destroy_all
  end

  ##########Author: Diab ############
  test "is rank updated after user added interest RED" do
     interest = Interest.new(:name=>"whatever")
     interest.save
     user = User.new(:email=>"email@mail.com")
     user.save
     rank = interest.get_interest_rank
     user.added_interests << interest
     assert_difference('rank' , 5) do
     rank = interest.get_interest_rank
     end
    end

  ##########Author: Diab ############
  test "is rank updated after story added to interest RED" do
     interest = Interest.new(:name=>"whatever")
     interest.save
     rank = interest.get_interest_rank
     story = Story.new(:title=>"Story")
     story.interest = interest
     story.save
     assert_difference('rank' , 2) do
     rank = interest.get_interest_rank
     end
    end
  
  
  ##########Author: Diab ############
  test "top interests RED" do
     interest1 = Interest.new(:name=>"whatever1")
     interest1.save
     interest2 = Interest.new(:name=>"whatever2")
     interest2.save
     interest3 = Interest.new(:name=>"whatever3")
     interest3.save
     user = User.new(:email=>"email@mail.com")
     user.save
     story1 = Story.new(:title=>"Story")
     story1.interest = interest2
     story1.save
     story2 = Story.new(:title=>"Story")
     story2.interest = interest3
     story2.save
     user.added_interests << interest1
     user.added_interests << interest3
     top_interests_names = Interest.get_top_interests_names
     top_interests_ranks = Interest.get_top_interests_ranks
     assert_equal top_interests_names , ["whatever3","whatever1","whatever2"]
     assert_equal top_interests_ranks , [7,5,2]
    end

  ##########Author: Diab ############
  test "are all users added interest returned RED" do
     interest = Interest.new(:name=>"whatever")
     interest.save
     user1 = User.new(:email=>"email1@mail.com")
     user1.save
     user1.added_interests << interest
     user2 = User.new(:email=>"email2@mail.com")
     user2.save
     user2.added_interests << interest
     allUsers = interest.get_users_added_interest
     assert_equal allUsers , interest.adding_users
    end

require 'test_helper'

class InterestTest < ActiveSupport::TestCase
  # test "the truth" do
  #   assert true
  # end

  ##########Author: Diab ############
  def setup
    Interest.destroy_all
  end

  ##########Author: Diab ############
  test "is rank updated after user added interest RED" do
     interest = Interest.new(:name=>"whatever")
     interest.save
     user = User.new(:email=>"email@mail.com")
     user.save
     rank = interest.get_interest_rank
     user.added_interests << interest
     assert_difference('rank' , 5) do
     rank = interest.get_interest_rank
     end
    end

  ##########Author: Diab ############
  test "is rank updated after story added to interest RED" do
     interest = Interest.new(:name=>"whatever")
     interest.save
     rank = interest.get_interest_rank
     story = Story.new(:title=>"Story")
     story.interest = interest
     story.save
     assert_difference('rank' , 2) do
     rank = interest.get_interest_rank
     end
    end
  
  
  ##########Author: Diab ############
  test "top interests RED" do
     interest1 = Interest.new(:name=>"whatever1")
     interest1.save
     interest2 = Interest.new(:name=>"whatever2")
     interest2.save
     interest3 = Interest.new(:name=>"whatever3")
     interest3.save
     user = User.new(:email=>"email@mail.com")
     user.save
     story1 = Story.new(:title=>"Story")
     story1.interest = interest2
     story1.save
     story2 = Story.new(:title=>"Story")
     story2.interest = interest3
     story2.save
     user.added_interests << interest1
     user.added_interests << interest3
     top_interests_names = Interest.get_top_interests_names
     top_interests_ranks = Interest.get_top_interests_ranks
     assert_equal top_interests_names , ["whatever3","whatever1","whatever2"]
     assert_equal top_interests_ranks , [7,5,2]
    end

  ##########Author: Diab ############
  test "are all users added interest returned RED" do
     interest = Interest.new(:name=>"whatever")
     interest.save
     user1 = User.new(:email=>"email1@mail.com")
     user1.save
     user1.added_interests << interest
     user2 = User.new(:email=>"email2@mail.com")
     user2.save
     user2.added_interests << interest
     allUsers = interest.get_users_added_interest
     assert_equal allUsers , interest.adding_users
    end

#Author:Jailan
test "should not save interest without name RED" do
  interest = Interest.my_new
  assert !interest.save, "Saved the post without a title"
end



#Author:Jailan
 test "should save interest when updated RED" do
    interest = Interest.create!(name: "my Test", description: "blabla")
    interest.update(name:"my Test2"  )

    assert interest.save
  end

#Author:Jailan
 test "shouldn't update interest when name is blank RED" do
    interest = Interest.create!(name: "my Test", description: "blabla")
    interest.update(description:"desc" )

    assert !interest.save
  end
#this test is checked after implementing the browse story (uploading image of Interest)
#Author:Jailan
 #test "shouldn't update interest when photo is blank" do
   # interest = Interest.create!(name: "my Test", description: "blabla")
    #interest.update(name:"desc" )

    #assert !interest.save
  #end


end

#Author:Jailan
 test "should save interest when toggled RED" do
    interest = Interest.create!(name: "my Test", description: "blabla")
    interest.toggle

    assert interest.save
  end


end
