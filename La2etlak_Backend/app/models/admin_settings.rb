class Admin_Settings < ActiveRecord::Base
  attr_accessible :key, :value
  # author: Gasser
  # A static method that takes as an input the threshold of the flags 
  # if first loads the flags_thershold value from the admin_settings table 
  # then it checks if the input passed from the from was not empty then 
  # it parses the input to integer, and checks if the admin entered a number
  # that is less than the minimum threshold, it forces it to be 30 (Default 
  # Value) If the number was bigger than 30 then it updates the table with 
  # the new value and saves this update.
  # Then it loops on all the stories to update their status by checking the 
  # number of flags for each one if it is greater than the threshold it hides
  # this story else it un-hides it.This is done by setting the hidden flag in 
  # each story.All of that after checking the global variable auto_hiding which
  # the admin changes from the checkbox.
  def self.configure_flags_threshold (flags_number, disable)
      #$flash_error = "false"
      $auto_hiding = disable
  		if !$auto_hiding
        flags_threshold = Admin_Settings.first
  		  if !flags_number.nil?
	  		 	flags=Integer(flags_number)
  			if flags < 30
  				flags_threshold.value = 30
          #$flash_error = "true"
  			else
  				flags_threshold.value=flags
	  		end
  			flags_threshold.save
  			stories=Story.all
  			stories.each do |story|
  				if story.flags.count >= flags
  					story.hidden = true
  				elsif story.flags.count < flags
  					story.hidden = false
          end
  				eeeeeeend
  			end
  		end
    end
  end

  # author : Gasser
  # A static method that is called in the flag_story method in users_controller
  # to check after flagging any story if the number of flags exceeds the threshold
  # or not to hide this story or not.All of that after checking the global variable
  # auto_hiding which the admin changes from the checkbox.
  def self.update_story_if_flagged (story)
  	if !$auto_hiding
      if(story.flags.count >= Admin_Settings.first.value)
  		  story.hidden = true
  	  else
  		  story.hidden = false
  	  end
    end
  end
end
