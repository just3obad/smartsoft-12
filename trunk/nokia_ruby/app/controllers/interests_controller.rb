class InterestsController < ApplicationController
  
  def index
    @interests = Interest.all #passing a list of all interests to show them all in the view
  end

  def show
     
    @interests = Interest.all
    @interest = Interest.find(params[:id])#retrieving the interest from the database using the id
    @stories = Story.find_all_by_interest_id(params[:id]) # get
    @feeds = Feed.find_all_by_interest_id(params[:id])
    @feed = Feed.find_by_interest_id(params[:id])#retrieving the feeds for a certain interest in the database using the id of the interest

    if @feed == nil #if the list of RSS feeds if a certain interest is empty we will create a new one
       @feed = Feed.new
    end
    @title = @interest.name
    @image = @interest.image

  end


  def new
	$errors = false
  @interests = Interest.all 
  @feed = Feed.new # creaing a new interest and returning it in a variable @interest used in the form in new.html.erb 
  @interest = Interest.new
  @title = "Add interest"
  end
  #" CREATE " is a method to create a new interest using if-else statements, which allows us to handle the cases of failure and success separately based on the value of @interest.save, if saving succeeds ( according to validations ) then we redirect to the main page of the interest 
 # otherwise , we render once more  the "New" page 

  def create
  @interests = Interest.all 
   @interest = Interest.new(params[:interest])
   if @interest.save
     flash[:success] = "Your Interest was added Successfully"
 # here we check if flash returned a sucess then we display the success message

 Log.create!(loggingtype: 1,user_id_1: nil,user_id_2: nil,admin_id: nil,story_id: nil,interest_id: @interest.id,message: "Admin added an interest")
     redirect_to @interest


   else
  $errors = true
     @title = "Add Interest"     
render 'new'

#Render 'new' , evaluates the contents in the error_messages partial contents, and insert the results into the view.
    end
 end
end
