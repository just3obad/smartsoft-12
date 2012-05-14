class AdminsController < ApplicationController

  def search
    if params[:autocomplete][:query].length > 0
      $users = Admin.search(params[:autocomplete][:query])
      if $users.length == 0
        $users = "There are no matching results."
      end
    else
      $users = "Please enter query into the search bar."
    end

    respond_to do |format|
      format.html #index.html.erb
      format.json { render json: $users }
    end
  end
  
  def new
  @admin = Admin.new
  end

  def create
    @admin = Admin.new(params[:admin])
    if @admin.save
      flash[:notice] = "Registration successful."
      redirect_to(admins/login)
    else
      render :action => 'new'
    end
  end
  def index
    Admin.get_feed
  end
end
