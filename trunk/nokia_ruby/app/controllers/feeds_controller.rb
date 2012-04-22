class FeedsController < ApplicationController

def index
end  

def new 
@feed = Feed.new
end

  def create
    
    @feed= Feed.new(params[:feed])
    
    if @feed.save
      flash[:success] = "RSS added Successfully !"
      redirect_to :controller => 'interests', :action => 'show', :id => @feed.interest_id
    else
     puts @feed 
    end
  end



  def destroy
  end
end

