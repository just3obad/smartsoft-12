class FeedsController < ApplicationController
def index
   
end
def show
   redirect_to :controller => 'interests', :action => 'show', :id => @interest_id
end
def update
end

def new 
  @feed = Feed.new
end

  def create
    #@id1 = Feed.find(params[:id]).interest_id
    $saved
    @feed= Feed.new(params[:feed]) #retrieving the feed from the database using the table feed parameters
    @interestid= params[:feed][:interest_id]
    if params.has_key?(:feed) and StoriesHelper.check_rss(params[:feed][:link])
    if @feed.save
    $saved=true

      redirect_to :controller => 'interests', :action => 'show', :id => @interestid
      flash[:success] = "Link added successfully."
      StoriesHelper.fetch_rss(params[:feed][:link])
    end
    else #if saving faild a flash message box will appear with the errors
     $saved=false
     flash[:error] = "Link is invalid. Please try again" 
      redirect_to :controller => 'interests', :action => 'show', :id => @feed.interest_id
    end
  end

  def destroy
        @id = Feed.find(params[:id]).interest_id
	Feed.find(params[:id]).destroy
	redirect_to :controller=>'interests', :action => 'show', :id => @id
  end


end

