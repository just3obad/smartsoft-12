class AdminsController < ApplicationController

  def search
    @users = Admin.search(params[:query])

    respond_to do |format|
      format.html #index.html.erb
      format.json { render json: @users }
    end
  end

  def index
  end
end
