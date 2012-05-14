class AdminSettingsController < ApplicationController
	
	def show
	end
	
	# author : Gasser
	# a method in the admin_settings controller passed from the show page of the settings page
	# this function just calls the static method in the model and passes the value entered in the 
	# form then it renders the show page again.
	def configure_flags_threshold
		
		Admin_Settings.configure_flags_threshold (params[:value])
		render :template=>'admin_settings/show'		
	end
	
	def create
	end
end
