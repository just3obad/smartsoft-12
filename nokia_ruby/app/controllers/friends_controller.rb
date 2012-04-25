class FriendsController < ApplicationController

def index
    @gaheem_accounts = Friends.all

    respond_to do |format|
      format.html # index.html.erb
      format.json { render json: @gaheem_accounts }
    end
  end
  
# Method myreq : Its responsible for getting a list of the user that wish to be friends with the current user  
  def myreq
   @me=params[:id]
   #@id_list = Array.new()
   @friend_list = Array.new
   @id_list=Friend.where(:status=>1, :receiver=>@me).select("sender").uniq.map{|x|x.sender}
   0.upto(@id_list.length) do |i|
     @friend_list.append(User.find(@id_list[i]))
   end
 respond_to do |format|
      format.json { render json: @friend_list }
end
end
  
  
 # Method find : Its responsible for finding a list of user, when the current user enters a name its responsible for getting all matches for this name 
  def find
   @name=params[:name]
   @list = User.where(:name=>@name).select("name ,id , email")
   respond_to do |format|
      format.json { render json: @list }
      end
  end
  
# Method req : Its responsible for the friendship request action between two users (sender and receiver)
  def req
    @user=params[:id]
    @friend=params[:friend]
    @friendship1 = Friend.new(:sender=>@user, :receiver=>@friend, :status=>"0").save!
    @friendship2 = Friend.new(:sender=>@friend, :receiver=>@user, :status=>"0").save!
    if @friendship1 && @friendship2
        respond_to do |format|
      format.json { render json: @friendship1 }
      end
    end
  end
  
 #Method accept : Its responsible for friendship action accept, when a user wishes to accept an incoming friendship request from another user, what it does is that it changes the status of the friendship request from pending (status => 0 ) into accepted (friends status => 1)  
  def accept
    @user=params[:id]
    @friend=params[:friend]
    @friendship1 =Friend.where(:sender=>@user, :receiver=>@friend).first.update_attributes(:status => 1)
    @friendship2 =Friend.where(:sender=>@friend, :receiver=>@user).first.update_attributes(:status => 1)
    if @friendship1 && @friendship2
        respond_to do |format|
      format.json { render json: "succes" }
      end
    end
  end

# Method Reject : Its responsible for friendship action reject, when a user wishes to reject an incoming friendship request from another user, what it does is that it changes the status of the friendship request from pending (status => 0 ) into rejected (friends status => 2) 
  def reject
    @user=params[:id]
    @friend=params[:friend]
    @friendship1 =Friend.where(:sender=>@user, :receiver=>@friend).first.update_attributes(:status => 2)
    @friendship2 =Friend.where(:sender=>@friend, :receiver=>@user).first.update_attributes(:status => 2)
    if @friendship1 && @friendship2
        respond_to do |format|
      format.json { render json: "succes" }
      end
    end
  end


end
