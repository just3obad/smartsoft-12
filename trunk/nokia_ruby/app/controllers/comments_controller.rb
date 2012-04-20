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
    @comment = Comment.new(params[:comment])

    respond_to do |format|
      if @comment.save
        format.html { redirect_to @comment, notice: 'User was successfully created.' }
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
