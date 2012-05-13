require 'test_helper'

class AdminTest < ActiveSupport::TestCase
  # test "the truth" do
  #   assert true
  # end
  def setup
    @interest1 = Interest.create(:name => "The bored people", :description => "This interest is made specifically for the more bored people")
    @interest2 = Interest.create(:name => "Swarly Stinson", :description => "Please don't call me Swarely any more")
    @story1 = Story.new(:title => "AWESOME pplz", :content => "Once upon a time I was a hero")
    @story1.interest=@interest1
    @story1.save
    @story2 = Story.new(:title => "Call me Barney", :content => "No more Swarrllleey, Swarl-ly, swarlz, NO MORE")
    @story2.interest=@interest2
    @story2.save
  end

  test "search title 1 RED" do
    search_result = Admin.search_story("AwESomE!")
    assert(search_result.include? @story1)
  end

  test "search title 2 RED" do
    search_result = Admin.search_story("pplz")
    assert(search_result.include? @story1)
  end

  test "search title 3 RED" do
    search_result = Admin.search_story("Barney")
    assert(search_result.include? @story2)
  end

  test "search title 4 RED" do
    search_result = Admin.search_story("Poland tomorrow")
    assert(search_result.empty?)
  end

  test "search content 1 RED" do
    search_result = Admin.search_story("Swarlz")
    assert(search_result.include? @story2)
  end

  test "search content 2 RED" do
    search_result = Admin.search_story("more")
    assert(((search_result.include? @story1) and (search_result.include? @story2)))
  end

  test "search interest name 1 RED" do
    search_result = Admin.search_interest("StiNsoN")
    assert(search_result.include? @interest2)
  end

  test "search interest name 2 RED" do
    search_result = Admin.search_interest("bored")
    assert(search_result.include? @interest1)
  end

  test "search interest description 1 RED" do
    search_result = Admin.search_interest("swarely")
    assert(search_result.include? @interest2)
  end

  test "search interest description 2 RED" do
    search_result = Admin.search_interest("elnesr")
    assert(search_result.empty?)
  end

  test "search interest description 3 RED" do
    search_result = Admin.search_interest("more")
    assert(((search_result.include? @interest1) and (search_result.include? @interest2)))
  end
  
  test "search interest description 4 RED" do
    search_result = Admin.search_interest("speciFIcally")
    assert(search_result.include? @interest1)
  end
end
