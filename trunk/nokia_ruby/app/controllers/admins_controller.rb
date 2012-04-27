class AdminsController < ApplicationController

  def search
    if params[:query].length > 0
      @users = Admin.search(params[:query])
      if @users.length == 0
        @users = "There are no matching results."
      end
    else
      @users = "Please enter query into the search bar."
    end

    respond_to do |format|
      format.html #index.html.erb
      format.json { render json: @users }
    end
  end

  def index
  end
end
