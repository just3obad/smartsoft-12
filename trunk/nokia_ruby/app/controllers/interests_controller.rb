class InterestsController < ApplicationController

  def show
    @interest = Interest.find(params[:id])
    @feeds = @interest.feeds
    @title = @interest.name
    @image = @interest.image

  end



  def new
   @feed = Feed.new
  @interest = Interest.new
  @title = "Add interest"
  end

  def create
   @interest = Interest.new(params[:interest])
   if @interest.save
     flash[:success] = "Your Interest was added Successfully"
     redirect_to @interest

   else
     @title = "Add Interest"
     render 'new'
    end
 end
end
