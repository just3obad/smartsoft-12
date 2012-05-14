class AdminSettingsController < ApplicationController
	def show
	end

	def configure_flags_threshold
		
		Admin_Settings.configure_flags_threshold (params[:value])
		render :template=>'admin_settings/show'		
	end
	def create
	end
end
