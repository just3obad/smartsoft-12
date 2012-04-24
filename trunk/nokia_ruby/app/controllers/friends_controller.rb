class FriendsController < ApplicationController

def req
    @user = params[:user_id]
     @fr = params[:friend_name]
    @friend = User.where(:name => @fr).select("id")
    unless @friend.nil?
      if Friends.request(@user, @friend)
        flash[:notice] = "Friendship with #{@friend.full_name} requested"
         respond_to do |format|
    format.json { render json: "1" }
 end
      else
        flash[:notice] = "Friendship with #{@friend.full_name} cannot be requested"
      end
    end
    respond_to do |format|
    format.json { render json: "0" }
 end
  end
  
  
  def accept
   @user = params[:user_id]
    @fr = params[:friend_name]
    @friend = User.where(:name => @fr).select("id")
    unless @friend.nil?
      if Friends.accept(@user, @friend)
        flash[:notice] = "Friendship with #{@friend.full_name} accepted"
         respond_to do |format|
    format.json { render json: "1" }
 end
      else
        flash[:notice] = "Friendship with #{@friend.full_name} cannot be accepted"
      end
    end
    respond_to do |format|
    format.json { render json: "0" }
 end
  end

  def reject
   @user = params[:user_id]
   @fr = params[:friend_name]
   @friend = User.where(:name => @fr).select("id")
    unless @friend.nil?
      if Friends.reject(@user, @friend)
        flash[:notice] = "Friendship with #{@friend.full_name} rejected"
        respond_to do |format|
    format.json { render json: "1" }
 end
      else
        flash[:notice] = "Friendship with #{@friend.full_name} cannot be rejected"
        respond_to do |format|
    format.json { render json: "0" }
 end
      end
    end
  end

end
