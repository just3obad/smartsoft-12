class UserAddInterestsController < ApplicationController


#this method takes as input user_id and it get the Interests of this user
def getinterests
@id = params[:id]
user = User.find(@id)
@interests = user.get_interests
  
 respond_to do |format|
      format.html # index.html.erb
      format.json { render json: @interests }

end
end

end
