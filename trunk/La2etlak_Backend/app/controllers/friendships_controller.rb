class FriendshipsController < ApplicationController

  before_filter {user_authenticated?}

=begin
  This is the controller responsible of indexing frineds
  Input: Nothing
  Output: Nothing  
  Author: Yahia 
=end
  def index
    @user = current_user
    @friends = @user.friends
    @pending_invited_by = @user.pending_invited_by
    @pending_invited = @user.pending_invited
    @friends = @friends + @pending_invited
    @friends.map{|a| if a.name.nil? then a.name = a.email.split('@')[0] end}
    @friends.sort! {|a,b| a.email <=> b.email}
    @friends = @friends.paginate(:per_page => 5, :page=> params[:page])
    flash[:notice] = 'You have no approved friendships $red'
    render layout: 'mobile_template'
  end

=begin
  This is the controller resposnsible of cerating a new friendship
  (requesting)
  The query_forward  variable is got from the search from and will
  help us not to have our search done again each time we request
  a friendship
  Input: params[:friend_id]
  Output: Nothing
  Author: Yahia 
=end
  def create
    @user = current_user
    @friend = User.find(params[:friend_id])
    @friendship_created = @user.invite(@friend)
    if @friendship_created
      flash[:notice] = 'Frindship request has succesffully been sent green'
      # for the log file 
      l = Log.new
      l.user_id_1 = @user.id
      l.user_id_2 = @friend.id
      name_1 = if @user.name.nil? then @user.email.split('@')[0] else @user.name end
      name_2 = if @friend.name.nil? then @friend.email.split('@')[0] else @friend.name end
      l.message = "#{name_1} requested friendship of #{name_2}"
      l.save
    else 
      flash[:notice] = 'Frindship request was not sent $red'
    end  

    redirect_to action: "index"
  end

=begin
  This is the controller responsible of accepting frinedship
  when this controller is active, the current user will accept
  the friendship request sent by the user with the id passed
  in the params
  Input: params[:friend_id]
  Output: Nothing
  Author: Yahia 
=end
  def accept
    @user = current_user
    @friend = User.find(params[:friend_id])
    @friendship_approved = @user.approve(@friend)
    @friends = @user.friends
    @pending_invited_by = @user.pending_invited_by
    l = Log.new
    l.user_id_1 = @user.id
    l.user_id_2 = @friend.id
    name_1 = if @user.name.nil? then @user.email.split('@')[0] else @user.name end
    name_2 = if @friend.name.nil? then @friend.email.split('@')[0] else @friend.name end
    l.message = "#{name_1} accepted friendship of #{name_2}"
    l.save
    flash[:notice] = "You and #{name_2} are now friends $green"
    redirect_to action: 'pending'
  end

=begin
  This is the controller responsible of ignoring frinedship
  Input: parmas[:friend_id]
  Output: Nothing
  Author: Yahia 
=end
  def remove
    @user = current_user
    @friend = User.find(params[:friend_id])
    @friendship = @user.send(:find_any_friendship_with, @friend)
    if @friendship
      @friendship.delete
      @removed = true
      flash[:notice] = "Friendship removed #{@friend.email} $green"
    else 
      flash[:notice] = "Friendship was not #{@friend.email} $red"
    end
    l = Log.new
    l.user_id_1 = @user.id
    l.user_id_2 = @friend.id
    name_1 = if @user.name.nil? then @user.email.split('@')[0] else @user.name end
    name_2 = if @friend.name.nil? then @friend.email.split('@')[0] else @friend.name end
    l.message = "#{name_1} removed friendship of #{name_2}"
    l.save

    redirect_to action: "index"
  end

=begin
  This is the controller responsible of blocking a user (not receiving
  frineship requests from him in the first place)
  Input: params[:friend_id]
  Output: Nothing  
  Author: Yahia 
=end
  def block
    @user = current_user
    @friend = User.find(params[:friend_id])
    @user.block @friend
    @friendship = @user.send(:find_any_friendship_with, @friend)
    if @friendship
      @friendship.delete
    end 

    l = Log.new
    l.user_id_1 = @user.id
    l.user_id_2 = @friend.id
    name_1 = if @user.name.nil? then @user.email.split('@')[0] else @user.name end
    name_2 = if @friend.name.nil? then @friend.email.split('@')[0] else @friend.name end
    l.message = "#{name_1} blocked #{name_2}"
    l.save
    flash[:notice] = "#{name_2} was blocked successfully $green"
    redirect_to action: 'index'
  end


=begin
  This is the controller responsible for the search of users 
  (to find friends)
  Input: params[:query]
  Output: Nothing  
  Author: Yahia 
=end
  def search
    @user = current_user
    @query = params[:query]
    @resulted_users = Admin.search_user(@query)
    @resulted_users.delete @user
    @resulted_users.sort! {|a,b| a.email <=> b.email}
    @resulted_users = @resulted_users.paginate(:per_page => 5, :page=> params[:page])
    render layout: 'mobile_template'
  end 

=begin
  This is the controller responsible of viewing pending frinedship
  requests. 
  Input: Nothing
  Output: Nothing  
  Author: Yahia 
=end
  def pending 
    @user = current_user
    @inviters = @user.pending_invited_by
    if @inviters.empty?
      flash[:notice] = "You have no pending requests $red"   
    end 
    render layout: 'mobile_template'
  end 
end 
