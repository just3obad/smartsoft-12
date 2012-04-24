class Friends < ActiveRecord::Base
  attr_accessible :receiver, :sender, :stat
  
  belongs_to :user
  belongs_to :friend, :class_name => "User", :foreign_key => "friend_id"

  validates :receiver, presence: true
  validates :sender, presence: true
  validates :stat, presence: true
  
 
  
  def new
   @friendship1 = Friends.new
   @friendship2 = Friends.new
  end
  
  
  def create
   @user = User.find(params[:id])
   @friend = User.find(params[:friend_id])
    params[:friendship1] = {:user_id => @user.id, :friend_id => @friend.id, :stat => '1'}
    params[:friendship2] = {:user_id => @friend.id, :friend_id => @user.id, :stat => '0'}
   @friendship1 = Friends.create(params[:friendship1])
   @friendship2 = Friends.create(params[:friendship2])
   if @friendship1.save && @friendship2.save
   return true
   else
   return false
   end
   end
   
   
def update
@user = User.find(params[:id])
@friend = User.find(params[:friend_id])
params[:friendship1] = {:user_id => @user.id, :friend_id => @friend.id, :stat => '2'}
params[:friendship2] = {:user_id => @friend.id, :friend_id => @user.id, :stat => '2'}
@friendship1 = Friends.find_by_user_id_and_friend_id(@user.id, @friend.id)
@friendship2 = Friends.find_by_user_id_and_friend_id(@friend.id, @user.id)
if @friendship1.update_attributes(params[:friendship1]) && @friendship2.update_attributes(params[:friendship2])
flash[:notice] = 'Friend sucessfully accepted!'
return true
else
return true
end
end



def destroy
@user = User.find(params[:id])
@friend = User.find(params[:friend_id])
@friendship1 = @user.friendships.find_by_friend_id(params[:id]).destroy
@friendship2 = @friend.friendships.find_by_user_id(params[:friend_id]).destroy
end
  
end
