class CommentsController < ApplicationController
  respond_to :html,:json
  
  def new
    @comment = Comment.new

    respond_to do |format|
      format.html # new.html.erb
      format.json { render json: @comment }
      end
    end

  def show
    respond_with(@comment = Comment.find(params[:id]))
  end  
  def create
    @comment = Comment.new# Comment.new(params[:comment])
    @comment.story = Story.find(params[:comment][:story_id])
    @comment.content = params[:comment][:content]
    @comment.user = User.find(params[:comment][:user_id])
    if @comment.save
      redirect_to :controller => "stories", :action => "mobile_show" , :id => @comment.story.id
    else
      flash[:notice] = "Please enter something to comment red"
      redirect_to :controller => "stories", :action => "get" , :id => params[:comment][:story_id]
    end
  end

  def index
    respond_with(@comments = Comment.all)
  end
end
