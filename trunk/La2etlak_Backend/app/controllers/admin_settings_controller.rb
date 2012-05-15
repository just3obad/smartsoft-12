class AdminSettingsController < ApplicationController

	#author: Bassem
  	def index

  	end

	def show
	end
	
	# author : Gasser
	# a method in the admin_settings controller passed from the show page of the settings page
	# this function just calls the static method in the model and passes the value entered in the 
	# form then it renders the show page again.
	def configure_flags_threshold
		if params[:auto] == "on"
			Admin_Settings.configure_flags_threshold params[:value] , "true"
		else 
			Admin_Settings.configure_flags_threshold params[:value] , "false"
		end 
		if $flash_error ==  "true"
			#flash[:error] = "You can not enter values less than 30, It is now changed to the default value 30."
		end
		render :template=>'admin_settings/index'		
	end
	
	def create
	end
end
