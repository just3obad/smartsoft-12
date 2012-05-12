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
    respond_to do |format|
      if @comment.save
        format.html { redirect_to @comment, notice: 'comment success' }
        format.json { render json: @comment, status: :created, location: @user }
      else
        format.html { render action: "new" }
        format.json { render json: @comment.errors, status: :unprocessable_entity }
      end
    end
  end

  def index
    respond_with(@comments = Comment.all)
  end
end
