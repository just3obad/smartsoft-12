class FriendshipsController < ApplicationController

  # Author: Yahia 
  def index
    current_user = User.find(session[:user_id])
    @friends = current_user.friends
    @pending_invited_by = current_user.pending_invited_by
    @pending_invited = current_user.pending_invited
  end

  # Author: Yahia 
  def create
    current_user = User.find(session[:user_id])
    @friend = User.find(params[:user_id])
    @friendship_created = current_user.invite(@friend)
    if @friendship_created
      # flash.now[:notice] = "Friendship invitation created  #{@friend.email}"
      render layout: 'mobile_template', text: "Friendship invitation created  #{@friend.email}"
    end
  end

  # Author: Yahia 
  def approve
    current_user = User.find(session[:user_id])
    @friend = User.find(params[:user_id])
    @friendship_approved = current_user.approve(@friend)
    @friends = current_user.friends
    @pending_invited_by = current_user.pending_invited_by
    #flash.now[:notice] = "Friendship approved #{@friend.email} a t approuve"
    render layout: 'mobile_template', text: "Friendship approved  #{@friend.email}"
  end

  # Author: Yahia 
  def remove
    current_user = User.find(session[:user_id])
    @friend = User.find(params[:user_id])
    @friendship = current_user.send(:find_any_friendship_with, @friend)
    if @friendship
      @friendship.delete
      @removed = true
      #flash.now[:notice] = "Friendship removed #{@friend.email}"
      render layout: 'mobile_template', text: "Friendship removed  #{@friend.email}"
    end
  end
end 