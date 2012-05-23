class SharesController < ApplicationController
  before_filter {user_authenticated?}
  #$$$$$$$$$$$$$$$$ Mina Adel $$$$$$$$$$$$$$$$
  #Here I create a new entry in the Share table, create a flash the states that the share was successful (or not) and pass the flash on to
  #the stories/:id page (which makes the flash show up in that page)
  #No view is assigned to this method
  def user_share_story
    @user = current_user
    @story_id = params[:id]
    usershare = Share.new(:user_id => @user.id, :story_id => @story_id)
    if(usershare.save)
      flash.now[:story_successfully_shared] = "You have successfully shared this story, it will now appear on your friends' feeds $green"
      redirect_to("/stories/"+@story_id+"/get", :flash => flash)
    else 
      flash.now[:story_not_shared] = "Story not successfully saved, please try again later $red"
      redirect_to("/stories/"+@story_id+"/get", :flash => flash)
  end

  #render :layout => "mobile_template"
  end
  #$$$$$$$$$$$$$$$$ Mina Adel $$$$$$$$$$$$$$$$
end
