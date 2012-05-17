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
    #work so that I can work on the view of the search(LYDIA)
    $interests = Interest.all
    $stories = Story.all

    respond_to do |format|
      format.html #index.html.erb
      format.json { render json: $users }
    end
  end
  
  #Author: Lydia
  '''
  This method checks if the Users/Stories/Interests checkboxes are 
  checked or not and returns the values in the global variables:
  $usersSearch
  $storiesSearch
  $interestsSearch 
  Then it renders the search view again after the filteration.
  '''
  def filter
    $usersSearch = params[:users]
    $storiesSearch = params[:stories]
    $interestsSearch = params[:interests]
     render :template => 'admins/search'
  end
  
  #Author: Lydia
  '''
  This method checks the params of type passed from the search page so that
  it sets the @results variable to be either all results of the users or all 
  results of the stories or all results of the interests and it also applies
  the pagination method to @results.
  '''
  def all_results
    if params[:type] == "1"
      @results = $users.paginate(:page=>params[:page], :per_page=> 2)
    end
    if params[:type] == "2"
      @results = $stories.paginate(:page=>params[:page], :per_page=> 2)
    end
    if params[:type] == "3"
      @results = $interests.paginate(:page=>params[:page], :per_page=> 2)
    end
  end
 #Author : Mouaz
 
  def new
  @admin = Admin.new
  end
 #this method for create new admin 
 #if there is an attribute invalid user will be notified
  def create
    @admin = Admin.new(params[:admin])
    if @admin.save
      flash[:notice] = "Registration successful."
      redirect_to('/admins/index')
    else
      render :action => 'new'
    end
  end
  def index
    @admin_session = AdminSession.find
    if @admin_session == nil
     redirect_to('/admin/login')
    else
      Admin.get_feed
    end
  end
 #this method for reseting password
  def resetPassword
    @admin = Admin.find_by_email(params[:email])
    if @admin.nil?
      flash[:notice] ="This email doesn't exist red"
      redirect_to('/admin/forgot_password')
    else
		  newpass = @admin.resetPassword
		  Emailer.reset_admin_password(@admin,newpass).deliver
      flash[:notice] = "Your new password has been sent to your email green"
      redirect_to('/admin/reset_pass')
    end
  end
  def forgot_password
  @email
  redirect_to('admins/forgot_password.html.erb'
  end
end
