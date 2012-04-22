
class InterestsController < ApplicationController
  
  def index
    @interests = Interest.all
  end

  def show
 # creating a new interest and returning it in a variable @interest used in the form in new.html.erb 
    @interest = Interest.find(params[:id])

    @feed = Feed.find_by_interest_id(params[:id])

    if @feed == nil 
        @feed = Feed.new
    end
    @title = @interest.name
    @image = @interest.image

  end


  def new
  @feed = Feed.new
  @interest = Interest.new
  @title = "Add interest"
  end
  #" CREATE " is a method to create a new interest using if-else statements, which allows us to handle the cases of failure and success separately based on the value of @interest.save, if saving succeeds ( according to validations ) then we redirect to the main page of the interest 
 # otherwise , we render once more  the "New" page  
  def create
   @interest = Interest.new(params[:interest])
   if @interest.save
     flash[:success] = "Your Interest was added Successfully"
# here we check if flash returned a sucess then we display the success message 

     redirect_to @interest

   else
     @title = "Add Interest"
     render 'new'
#Render 'new' , evaluates the contents in the error_messages partial contents, and insert the results into the view.


    end
 end
end
