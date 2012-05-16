class FriendshipsController < ApplicationController

=begin
  This is the controller responsible of indexing frineds
  Author: Yahia 
=end
  def index
    @user = current_user
    @friends = @user.friends
    @pending_invited_by = @user.pending_invited_by
    @pending_invited = @user.pending_invited
    render layout: 'mobile_template'
  end

=begin
  This is the controller resposnsible of cerating a new friendship
  (requesting)
  The query_forward  variable is got from the search from and will
  help us not to have our search done again each time we request
  a friendship
  Author: Yahia 
=end
  def create
    @user = current_user
    @friend = User.find(params[:friend_id])
    @friendship_created = @user.invite(@friend)
    if @friendship_created
      # flash.now[:notice] = "Friendship invitation created  #{@friend.email}"
      flash[:notice] = 'Frindship request has succesffully been sent green'
    else 
      flash[:notice] = 'Frindship request was not sent red'
    end
    redirect_to action: "search", query: params[:query_forward]
  end

=begin
  This is the controller responsible of accepting frinedship
  Author: Yahia 
=end
  def accept
    @user = current_user
    @friend = User.find(params[:friend_id])
    @friendship_approved = @user.approve(@friend)
    @friends = @user.friends
    @pending_invited_by = @user.pending_invited_by
    #flash.now[:notice] = "Friendship approved #{@friend.email} a t approuve"
    #render layout: 'mobile_template', text: "Friendship approved  #{@friend.email}"
    redirect_to action: 'pending'
  end

=begin
  This is the controller responsible of ignoring frinedship
  Author: Yahia 
=end
  def remove
    @user = current_user
    @friend = User.find(params[:friend_id])
    @friendship = @user.send(:find_any_friendship_with, @friend)
    if @friendship
      @friendship.delete
      @removed = true
      flash[:notice] = "Friendship removed #{@friend.email} green"
    else 
      flash[:notice] = "Friendship was not #{@friend.email} red"
    end
  redirect_to action: "search", query: params[:query_forward] 

  end

=begin
  This is the controller responsible of blocking a user (not receiving
  frineship requests from him in the first place)
  Author: Yahia 
=end
  def block
    @user = current_user
    @friend = User.find(params[:friend_id])
    @user.block @friend
    
    render layout: 'mobile_template', text: "Member blocked  #{@friend.email}"
  end


=begin
  This is the controller responsible for the search of users 
  (to find friends)
  Author: Yahia 
=end
  def search
    @user = current_user
    @query = params[:query]
    @resulted_users = Admin.search_user(@query)
    @resulted_users.delete @user
    render layout: 'mobile_template'
  end 

=begin
  This is the controller responsible of accepting frinedship
  Author: Yahia 
=end
  def pending 
    @user = current_user
    @inviters = @user.pending_invited_by
    if @inviters.empty?
      flash[:notice] = "You have no pending requests red"   
    end 
    render layout: 'mobile_template'
  end 
end 
