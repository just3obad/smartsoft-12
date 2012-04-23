class FriendsController < ApplicationController

  # GET /friends/new
  # GET /friends/new.json
# Add a friend request after checking the incoming name
  def add(user_id,receiver_name)
    
    @receiver_id = User.where(:name => receive_name).select("id")
     if (reciver != nil)
       Friends.create!(:sender => user_id, :receiver => receiver__id , :stat => 0)
     end
   end
       
  
  def new
    @friend = Friend.new

    respond_to do |format|
      format.html # new.html.erb
      format.json { render json: @friend }
    end
  end

  # GET /friends/1/edit
  def edit
    @friend = Friend.find(params[:id])
  end

  # POST /friends
  # POST /friends.json
#create the freind request and save it act as pending requests
  def create
    @friend = Friends.new(:sender=>params[user_id],:receiver=>params[receiver_id], :stat=>0)

    respond_to do |format|
      if @friend.save
        format.html { redirect_to @friend, notice: 'Friend was successfully created.' }
        format.json { render json: @friend, status: :created, location: @friend }
      else
        format.html { render action: "new" }
        format.json { render json: @friend.errors, status: :unprocessable_entity }
      end
    end
  end

  # PUT /friends/1
  # PUT /friends/1.json
  # update the friend request and change it stat to 1 , act as acceptance
  def update
    @friend = Friend.find(params[:id])

    respond_to do |format|
      if @friend.update_attributes(:sender=>params[user_id],:receiver=>params[receiver_id], :stat=>1)
        format.html { redirect_to @friend, notice: 'Friend was successfully updated.' }
        format.json { head :no_content }
      else
        format.html { render action: "edit" }
        format.json { render json: @friend.errors, status: :unprocessable_entity }
      end
    end
  end

  # DELETE /friends/1
  # DELETE /friends/1.json
#Drop the friend request act as rejecting
  def destroy
    @friend = Friend.find(:sender=>params[user_id],:receiver=>params[receiver_id])
    @friend.destroy

    respond_to do |format|
      format.html { redirect_to friends_url }
      format.json { head :no_content }
    end
  end

end
