class FeedsController < ApplicationController

def index
end  

def new 
@feed = Feed.new
end

  def create
    
    @feed= Feed.new(params[:feed]) #retrieving the feed from the database using the table feed parameters
    
    if @feed.save # if we succeded to add a new RSS feed then a flash message in a green box will appear with a message indicating that , we will redirect to the interests page again .

      redirect_to :controller => 'interests', :action => 'show', :id => @feed.interest_id
      flash[:success] = "Link added successfully" 
    else #if saving faild a flash message box will appear with the errors
     flash[:error] = "link not added. Please try again" 
      redirect_to :controller => 'interests', :action => 'show', :id => @feed.interest_id

end
  end

def destroy
end

  def delete # a method that deletes RSS feeds from the database.
      Feed.find(params[:id]).destroy
      render 'feeds/delete_page', :interest_id => @interest.id
  end
  
  def delete_page #method to redirect to a page where feeds will be deleted.
   @feeds = Feed.find_all_by_interest_id(params[:interest_id])
  end

end

