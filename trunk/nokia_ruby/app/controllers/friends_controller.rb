class FriendsController < ApplicationController

  # GET /friends/new
  # GET /friends/new.json

  def add(sender_id,receiver_id)
    @receiver = User.where(:id => receiver_id)
     if (reciver != nil)
       Friends.create!(:sender => sender_id, :receiver => receiver__id , :stat => 0)
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
  def create
    @friend = Friend.new(params[:friend])

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
  def update
    @friend = Friend.find(params[:id])

    respond_to do |format|
      if @friend.update_attributes(params[:friend])
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
  def destroy
    @friend = Friend.find(params[:id])
    @friend.destroy

    respond_to do |format|
      format.html { redirect_to friends_url }
      format.json { head :no_content }
    end
  end

end
