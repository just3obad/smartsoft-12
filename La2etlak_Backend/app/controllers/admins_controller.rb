class AdminsController < ApplicationController

  def search
    $usersSearch = "true"
    $storiesSearch = "true"
    $interestsSearch = "true"
    if params[:autocomplete][:query].length > 0
      $users = Admin.search(params[:autocomplete][:query])
      if $users.length == 0
        $users = "There are no matching results."
      end
    else
      $users = "Please enter query into the search bar."
    end
    
    #these attributes are added this way temporarilly until Mostafa comitts his
    #work so that I can work on the view of the search
    $interests = Interest.all
    $stories = Story.all

    respond_to do |format|
      format.html #index.html.erb
      format.json { render json: $users }
    end
  end
  
  #Author: Lydia
  def filter
    $usersSearch = params[:users]
    $storiesSearch = params[:stories]
    $interestsSearch = params[:interests]
     render :template => 'admins/search'
  end
  
  #Author: Lydia
  def all_results
    if params[:type] == "1"
      @results = $users
    end
    if params[:type] == "2"
      @results = $stories
    end
    if params[:type] == "3"
      @results = $interests
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