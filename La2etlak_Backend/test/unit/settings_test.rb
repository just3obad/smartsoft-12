require 'test_helper'

class SettingsTest < ActiveSupport::TestCase
  # author : Gasser
  test "admin should configure flags threshold" do
    settings = Settings.first
    assert_difference ('settings.value',30) do
      settings.configure_flags_threshold (50)
    end
  end
  # author : Gasser
  test "admin should not set the threshold with less than 20"
  settings = Settings.first
    assert_difference ('settings.value',20, "You can't put the threshold with less than 20") do
      settings.configure_flags_threshold (10)
    end
  end
  # author : Gasser
  test "stories should be hidden after setting the threshold"
    settings = Settings.first
    stories = stories.all
    settings.configure_flags_threshold (10)
    stories.each do |story|
    assert_boolean !story.hidden 
    end
  end
end
