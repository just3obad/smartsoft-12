class FriendsController < ApplicationController

def req
    @user = params[:user_id]
    @friend = User.where(:name => friend_name).select("id")
    unless @friend.nil?
      if Friendship.request(@user, @friend)
        flash[:notice] = "Friendship with #{@friend.full_name} requested"
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
    @friend = User.where(:name => friend_name).select("id")
    unless @friend.nil?
      if Friendship.accept(@user, @friend)
        flash[:notice] = "Friendship with #{@friend.full_name} accepted"
      else
        flash[:notice] = "Friendship with #{@friend.full_name} cannot be accepted"
      end
    end
    redirect_to :controller => :user, :action => :index
  end

  def reject
   @user = params[:user_id]
    @friend = User.where(:name => friend_name).select("id")
    unless @friend.nil?
      if Friendship.reject(@user, @friend)
        flash[:notice] = "Friendship with #{@friend.full_name} rejected"
      else
        flash[:notice] = "Friendship with #{@friend.full_name} cannot be rejected"
      end
    end
    redirect_to :controller => :user, :action => :index
  end

end
