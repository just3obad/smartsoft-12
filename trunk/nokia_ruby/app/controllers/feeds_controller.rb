class FeedsController < ApplicationController

def index
end  

def new 
@feed = Feed.new
end

  def create
    
    @feed= Feed.new(params[:feed]) #retrieving the feed from the database using the table feed parameters
    
    if @feed.save # if we succeded to add a new RSS feed then a flash message in a green box will appear with a message indicating that , we will redirect to the interests page again .
      flash[:success] = "RSS added Successfully !"
      redirect_to :controller => 'interests', :action => 'show', :id => @feed.interest_id
    else#if saving faild a flash message box will appear with the errors
     @title = "Add feed"
     render 'new'
    end
  end



  def destroy
  end
  
  def delete_page #method to redirect to a page where feeds will be deleted.
   @feeds = Feed.find(params[:interest_id])
  end

  def delete # a method that deletes RSS feeds from the database.
    
  end
end

