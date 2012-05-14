class FriendshipsController < ApplicationController

  # Author: Yahia 
  def index
    @user = current_user
    @friends = @user.friends
    @pending_invited_by = @user.pending_invited_by
    @pending_invited = @user.pending_invited
  end

  # Author: Yahia 
  def create
    @user = current_user
    @friend = User.find(params[:friend_id])
    @friendship_created = @user.invite(@friend)
    if @friendship_created
      # flash.now[:notice] = "Friendship invitation created  #{@friend.email}"
      render layout: 'mobile_template', text: "Friendship invitation created  #{@friend.email}"
    end
  end

  # Author: Yahia 
  def approve
    @user = current_user
    @friend = User.find(params[:friend_id])
    @friendship_approved = @user.approve(@friend)
    @friends = @user.friends
    @pending_invited_by = @user.pending_invited_by
    #flash.now[:notice] = "Friendship approved #{@friend.email} a t approuve"
    render layout: 'mobile_template', text: "Friendship approved  #{@friend.email}"
  end

  # Author: Yahia 
  def remove
    @user = current_user
    @friend = User.find(params[:friend_id])
    @friendship = @user.send(:find_any_friendship_with, @friend)
    if @friendship
      @friendship.delete
      @removed = true
      #flash.now[:notice] = "Friendship removed #{@friend.email}"
      render layout: 'mobile_template', text: "Friendship removed  #{@friend.email}"
    end
  end
end 