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

  


end
