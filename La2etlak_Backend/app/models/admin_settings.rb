class Admin_Settings < ActiveRecord::Base
  attr_accessible :key, :value

=begin
  Method Descritpion: A static method that takes as an input the threshold 
  of the flags if first loads the flags_thershold value from the admin_settings
  table then it checks if the input passed from the from was not empty then 
  it parses the input to integer, and checks if the admin entered a number
  that is less than the minimum threshold, it forces it to be 30 (Default 
  Value) If the number was bigger than 30 then it updates the table with 
  the new value and saves this update. Then it loops on all the stories to 
  update their status by checking the number of flags for each one if it is
  greater than the threshold it hides this story else it un-hides it.This is
  done by setting the hidden flag in each story.All of that after checking 
  the global variable auto_hiding which the admin changes from the checkbox.
  Parameters: (flags_number, auto_hiding flag)
  Author: Gasser
=end
  def self.configure_flags_threshold (flags_number, disable)
      @flash_success = "false"
      flags_threshold = Admin_Settings.find_by_key("flags_threshold")
      auto_hiding = Admin_Settings.find_by_key("auto_hiding")
      flags = flags_number.to_i
  		if disable == true
        auto_hiding.value = 1
  			flags_threshold.value=flags
  		  flags_threshold.save
        @flash_success = "true"
  		  stories=Story.all
			  stories.each do |story|
 				  if story.flags.count >= flags
   					 story.hidden = true
				  elsif story.flags.count < flags
  					 story.hidden = false
          end
            story.save
        end
      else
        auto_hiding.value = 0
      end
      auto_hiding.save
      $current_auto_hiding = Admin_Settings.find_by_key("auto_hiding").value
      $current_flags_threshold = Admin_Settings.find_by_key("flags_threshold").value
  end
=begin 
  Method Description: A static method that is called in the flag_story method 
  in users_controller to check after flagging any story if the number of flags
  exceeds the threshold or not to hide this story or not.All of that after 
  checking the global variable auto_hiding which the admin changes from the checkbox.
=end
  def self.update_story_if_flagged (story)
  	if Admin_Settings.find_by_key("auto_hiding").value == 1
      if(story.flags.count >= Admin_Settings.first.value)
  		  story.hidden = true
  	  else
  		  story.hidden = false
  	  end
      story.save
    end
end

=begin
This method takes the number of days as an input, checks if its not equal to zero and sets the value of the time span
to this number which is to be checked lately whenever statistics are shown.
Inputs: no of days to be set
Outputs: non
NB: "statistics_time_span" key is to be put initially = 30. But now for the sake of testing, please type this command
in your terminal:
$ Admin_Settings.create!(key:"statistics_time_span", value:30)
Author: Bassem
=end
  def self.set_statistics_span (days)
    days_value = days.to_i
    span= Admin_Settings.find_by_key("statistics_time_span")
    
    if (days_value <= 0)
     return 0
    else
      span.value = days_value
      span.save
      return 1
    end
  end
end
