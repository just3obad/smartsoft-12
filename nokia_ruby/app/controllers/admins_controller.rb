class AdminsController < ApplicationController

  def index
    @user = Admin.search(params[:search])

    puts @user
    respond_to do |format|
      format.html #index.html.erb
      format.json { render json: @user }
    end
  end
end
