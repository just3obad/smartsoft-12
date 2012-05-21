class AdminSettingsController < ApplicationController

    before_filter {admin_authenticated?} 
    $span_settings=false
    $story_settings=false
	#author: Bassem
  	def index
  		@admin = Admin.new
                @admin2 = current_admin
  	end

	def show
	end
	
	# author : Gasser
	# a method in the admin_settings controller passed from the show page of the settings page
	# this function just calls the static method in the model and passes the value entered in the 
	# form then it renders the show page again.
	def configure_flags_threshold
		if params[:auto] == "on"
			Admin_Settings.configure_flags_threshold params[:valuee] , false
		else 
			Admin_Settings.configure_flags_threshold params[:valuee] , true
		end 
		#if $flash_success = "true" 
		#	flash[:success] = "The settings are changed successfully"
		#end
		if $flash_error ==  "true"
			flash[:error] = "Please enter a valid string"
		end
		$story_settings=true
		$span_settings=false

		redirect_to '/admin_settings'		
	end
	
 	def new
	  @admin = Admin.new
	end
	 #this method for create new admin 
	 #if there is an attribute invalid user will be notified
	  def create
	    @admin = Admin.new(params[:admin])
	    if @admin.save
	      flash[:notice] = "Addition successful."
	      redirect_to('/admin_settings')
	    else
	      flash[:error] = "Enter Valid Arguments."
	      redirect_to('/admin_settings')
	    end
	  end

	  #Author : BASSEM
	  def statistics_time_span
	  	@response = Admin_Settings.set_statistics_span params[:days]
	  	if ( @response == 1 )
	  		flash[:success] = "Time span set successfully."
	 	 else
	 	 	flash[:error] = "Please enter a valid number."
	 	 end
	 	 	$span_settings=true
	 	 	$story_settings=false

	  	redirect_to :action => "index"
	  end
  def edit
    
  end
=begin
 this method for edit current admin 
 if there is an attribute invalid user will be notified
 Author Mouaz
 param(admin parameters)
=end 
  def update
    @admin = current_admin
    if @admin.update_attributes(params[:admin])
      flash[:notice] = "Successfully updated profile."
        redirect_to('/admin_settings')
    else
      flash[:error] = "Enter Valid Arguments."
      redirect_to('/admin_settings')
    end
  end
end
