class FriendsController < ApplicationController

def index
    @gaheem_accounts = Friends.all

    respond_to do |format|
      format.html # index.html.erb
      format.json { render json: @gaheem_accounts }
    end
  end
def req
    @user = params[:id]
    @fr = params[:friend_name]
    @friend = User.where(:name => @fr).select("id")
    unless @friend.nil?
      if Friends.request(@user, @friend)
        flash[:notice] = "Friendship with #{@friend.full_name} requested"
    format.json { render json: "1" }
 end
      else
        flash[:notice] = "Friendship with #{@friend.full_name} cannot be requested"
        respond_to do |format|
    format.json { render json: "0" }
 end
      end
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
