class UserAddInterestsController < ApplicationController


#this method takes as input user_id and it get the Interests of this user
	def get_interests
		@id = params[:id]
		user = User.find(@id)
		@interests = user.get_interests
  		render :layout => "mobile_template"
 #respond_to do |format|
   #format.html # index.html.erb
      #format.json { render json: @interests }

	#end
	end

end
