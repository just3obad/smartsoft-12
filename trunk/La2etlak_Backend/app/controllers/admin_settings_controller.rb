class AdminSettingsController < ApplicationController

    before_filter {admin_authenticated?} 
	#author: Bassem
  	def index
  		@admin = Admin.new
      @admin2 = current_admin
  	end	
=begin	
	Method Description: A method in the admin_settings controller passed 
	from the show page of the settings page this function just calls the 
	static method in the model and passes the value entered in the form 
	then it redirects to the settings page again and it handles if the 
	admin enters characters not numbers in the text field and throws error
	and success flashes.
	Author : Gasser
=end
	def configure_flags_threshold
		if params[:auto] == "on"
			if params[:valuee].to_i == 0
			#	flash[:not_entering_numbers] = "Please enter a valid number. $red"
				Admin_Settings.configure_flags_threshold $current_flags_threshold , false
			end
		else
			if params[:valuee].to_i == 0
				flash[:not_entering_numbers] = "Please enter a valid number. $red"
				Admin_Settings.configure_flags_threshold $current_flags_threshold , true
			else
				flash[:success] = "Settings changed successfully. $green"
				Admin_Settings.configure_flags_threshold params[:valuee] , true
			end
		end
		#if $current_auto_hiding == 0
		#	flash[:changed_settings] = "Settings changed successfully. $green"	
		#end
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
   			
	      render :action => 'index'
	      flash[:error] = "Enter Valid Arguments."
	      redirect_to('/admin_settings')
	    end
	  end

	  #Author : BASSEM
	  def statistics_time_span
	  	@response = Admin_Settings.set_statistics_span params[:days]
	  	if ( @response == 1 )
	  		flash[:span_set] = "Time span set successfully. $green"
	 	 else
	 	 	flash[:span_not_set] = "Please enter a valid number. $red"
	 	 end
	 	 	
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
