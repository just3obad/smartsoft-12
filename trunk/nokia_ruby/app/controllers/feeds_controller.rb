class FeedsController < ApplicationController
def index
   
end
def show
   redirect_to :controller => 'interests', :action => 'show', :id => @interest_id
end
def new 
  @feed = Feed.new
end

  def create
    if StoriesHelper.check_rss(params[:feed][:link]) 
    @feed= Feed.new(params[:feed]) #retrieving the feed from the database using the table feed parameters
    if @feed.save
      redirect_to :controller => 'interests', :action => 'show', :id => @feed.interest_id
      flash[:success] = "Link added successfully"
      StoriesHelper.fetch_rss(params[:feed][:link])
    end
    else #if saving faild a flash message box will appear with the errors
     flash[:error] = "link not added. Please try again" 
      redirect_to :controller => 'interests', :action => 'show', :id => @feed.interest_id
    end
  end

  def destroy
        @id = Feed.find(params[:id]).interest_id
	Feed.find(params[:id]).destroy
	redirect_to :controller=>'interests', :action => 'show', :id => @id
  end


end

