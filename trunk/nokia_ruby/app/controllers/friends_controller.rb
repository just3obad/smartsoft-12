class FriendsController < ApplicationController

def index
    @gaheem_accounts = Friends.all

    respond_to do |format|
      format.html # index.html.erb
      format.json { render json: @gaheem_accounts }
    end
  end
  
  
  def myreq
   @me=params[:id]
   #@id_list = Array.new()
   @friend_list = Array.new
   #@ids = Array.new
   #@name = Array.new
   #@mail = Array.new
   #count = Friend.where(:stat=>1, :receiver => @me ).select("sender").count
   #puts @id_list[count]
   #puts @friend_list[count]
   @id_list=Friend.where(:stat=>1, :receiver=>@me).select("sender").uniq.map{|x|x.sender}

   #@id_list=Friend.where(:stat=>1, :receiver => @me ).select("sender").uniq
   0.upto(@id_list.length) do |i|
     @friend_list.append(User.find(@id_list[i]))
   end
 respond_to do |format|
      format.json { render json: @friend_list }
end
end
  
  def find
   @name=params[:name]
   @list = User.where(:name=>@name).select("name ,id , email")
   respond_to do |format|
      format.json { render json: @list }
      end
  end
  

  def req
    @user=params[:id]
    @friend=params[:friend]
    @friendship1 = Friend.new(:sender=>@user, :receiver=>@friend, :stat=>"0").save!
    @friendship2 = Friend.new(:sender=>@friend, :receiver=>@user, :stat=>"0").save!
    if @friendship1 && @friendship2
        respond_to do |format|
      format.json { render json: @friendship1 }
      end
    end
  end
  
  def accept
    @user=params[:id]
    @friend=params[:friend]
    @friendship1 =Friend.where(:sender=>@user, :receiver=>@friend).first.update_attributes(:stat => 1)
    @friendship2 =Friend.where(:sender=>@friend, :receiver=>@user).first.update_attributes(:stat => 1)
    if @friendship1 && @friendship2
        respond_to do |format|
      format.json { render json: "succes" }
      end
    end
  end

  def reject
    @user=params[:id]
    @friend=params[:friend]
    @friendship1 =Friend.where(:sender=>@user, :receiver=>@friend).first.update_attributes(:stat => 2)
    @friendship2 =Friend.where(:sender=>@friend, :receiver=>@user).first.update_attributes(:stat => 2)
    if @friendship1 && @friendship2
        respond_to do |format|
      format.json { render json: "succes" }
      end
    end
  end


end
