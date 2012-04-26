class UserAddInterestsController < ApplicationController


#this method takes as input user_id and it get the Interests of this user
def getinterests
@id = params[:id]
@interests =  UserAddInterest.find(:all , :conditions => ["user_id = ?" , @id ] , :select => "interest_id").map {|interest| interest.interest_id}.map {|id| Interest.find(id).name}
   
 respond_to do |format|
      format.html # index.html.erb
      format.json { render json: @interests }

end
end
#end




def addwithduplicates
@userid = params[:id]
@interests = params[:interests]
@interests.each do |element|
  @intid = Interest.find_by_name(element).id
  UserAddInterest.create(user_id:@userid , interest_id:@intid)
end
respond_to do |format|
      format.html # index.html.erb
      format.json { render json: @userid }
end
end
end




def add #add interests user choosed into table
@userid = params[:id]
@interests = params[:interests]
 interestsss = UserAddInterest.find_all_by_user_id(@userid)
 interestsss.each do |t|
 t.destroy
 end
@interests.each do |element|
  @intid = Interest.find_by_name(element).id
  UserAddInterest.create(:user_id => @userid , :interest_id => @intid)
end
respond_to do |format|
      format.html # index.html.erb
      format.json { render json: @userid }
end
end
end




