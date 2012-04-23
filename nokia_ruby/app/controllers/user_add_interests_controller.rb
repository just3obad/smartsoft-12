class UserAddInterestsController < ApplicationController

def getinterests
@id = params[:id]
@interests =  UserAddInterest.find(:all , :conditions => ["user_id = ?" , @id ] , :select => "interest_id").map {|interest| interest.interest_id}.map {|id| Interest.find(id).name}
   
 respond_to do |format|
      format.html # index.html.erb
      format.json { render json: @interests }

end
end
end
