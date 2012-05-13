require 'test_helper'

class SettingsTest < ActiveSupport::TestCase
  # author : Gasser
  test "admin should configure flags threshold" do
    settings = Settings(:one)
    old_value = settings.value
    settings.configure_flags_threshold (50)
    assert_equal settings.value, 50
  end


  # author : Gasser
  test "admin should not set the threshold with less than 20" do
    settings = Settings(:one)
    old_value = settings.value
    settings.configure_flags_threshold (10)
    assert_equals settings.value, 30 
  end


  # author : Gasser
  test "stories should be hidden after setting the threshold" do
    settings = Settings(:one)
    stories = Story.all
    settings.configure_flags_threshold (10)
    stories.each do |story|
      assert_boolean !story.hidden 
    end
  end 

end
