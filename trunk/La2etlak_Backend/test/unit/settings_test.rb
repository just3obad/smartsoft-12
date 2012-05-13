require 'test_helper'

class SettingsTest < ActiveSupport::TestCase
  # author : Gasser
  test "admin should configure flags threshold RED" do
    settings = Settings.create!(:key=>"flags_threshold", :value=>20)
    old_value = settings.value
    settings.configure_flags_threshold (50)
    assert_equal settings.value, 50
  end


  # author : Gasser
  test "admin should not set the threshold with less than 20 RED" do
    settings = Settings.create!(:key=>"flags_threshold", :value=>20)
    old_value = settings.value
    settings.configure_flags_threshold (10)
    assert_equal settings.value, 30 
  end


  # author : Gasser
  test "stories should be hidden after setting the threshold RED" do
    settings = Settings.create!(:key=>"flags_threshold", :value=>20)
    stories = Story.all
    settings.configure_flags_threshold (10)
    stories.each do |story|
      assert !story.hidden 
    end
  end 
  # author : Gasser
  test "admin should disable auto hiding RED" do 
    settings = Settings.create!(:key=>"flags_threshold", :value=>20)
    old_value = settings.value
    settings.disable_auto_hiding
    settings.configure_flags_threshold (10)
    assert_equal settings.value, 20
  end
     
end