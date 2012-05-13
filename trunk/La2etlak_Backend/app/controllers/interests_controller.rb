class InterestsController < ApplicationController
  
  def index
    redirect_to "/interests/list"
    @interests = Interest.all #passing a list of all interests to show them all in the view
  end

  def show
     
    @interests = Interest.get_all_interests
    @interest = Interest.get_interest(params[:id])
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
  if @interests.empty?
       flash[:error] = "There are currently no interests." 
  end
  @feed = Feed.new # creaing a new interest and returning it in a variable @interest used in the form in new.html.erb 
  @interest = Interest.new
  @title = "Add interest"
  end
  
  #" CREATE " is a method to create a new interest using if-else statements, which allows us to handle the cases of 
  #failure and success separately based on the value of @interest.save, if saving succeeds ( according to validations )
  # then we redirect to the main page of the interest 
  # otherwise , we render once more  the "New" page 

#Author : Jailan
#" CREATE " is a method to create a new interest using if-else statements, which allows us to handle the cases of failure and success separately based on the value of @interest.save, if saving succeeds ( according to validations ) then we redirect to the main page of the interest 
 # otherwise , we render once more  the "New" page 

#this methods retrieves and adjust changes to the adtabase using "my_create" method in the Model

  def create 
   $saved = nil
   $savedinterest
   @interests = Interest.all 
   @interest = Interest.new(params[:interest])
   if @interest.save
 $savedinterest = true
#global variable $saved & $savedinterest are used to handle the flash message in "Show.html.erb"
     flash[:success] = "Your Interest was added Successfully"
 # here we check if flash returned a sucess then we display the success message

 Log.create!(loggingtype: 1,user_id_1: nil,user_id_2: nil,admin_id: nil,story_id: nil,interest_id: @interest.id,message: "Admin added an interest")
     redirect_to @interest


   else
 $savedinterest = false
  $errors = true
     @title = "Add Interest"     
render 'new'

#Render 'new' , evaluates the contents in the error_messages partial contents, and insert the results into the view.
    end
 end

#Author : Jailan
#Update Method uses the form in the view to update the attributes of the interest
#all databese changes are done in the "my_update" method in the Model
  def update

 @interests = Interest.get_all_interests
#here, we call the method in model
    @interest= Interest.my_update(params[:id],params[:interest])

@deleted = Interest.is_deleted(params[:id])
#we check on the interest deleted or not because an admin is not allowed to adjust any changes/edit an interest unless it's ACTIVE
    if @interest && (@deleted == false || @deleted.nil?)
 $savedinterest = true
#global variable $savedinterest used to handle the flash message in "Show.html.erb"
      flash[:success] = "Interest updated successfully "

 
    @myinterest= Interest.get_interest(params[:id])
  redirect_to @myinterest
    else
 $savedinterest = false
  $errors = true
#global variable $errors used to handle the flash message in "Show.html.erb"
if @deleted == false || @deleted.nil?
# a flash appears when we enter an invalid info (balnk name )
          flash[:error] = " (Invalid inputs) Interest Update failed  , PLease Try again !"
else
#a flash appears banning the admin from updating the interest as long as it's blocked
flash[:error] = " This interest is now blocked , Please Restore it if you want Update"
end

    @myinterest= Interest.get_interest(params[:id])
  redirect_to @myinterest
end

#after updating we redirect to the interest main page but after editing

  end

#Author: jailan
# toggle method used to block/unblock the interest , when we block an interest no feeds appear for the users only the admin can view it 
# if the interest is deleted then he admin has the right to restore it once more .
  def toggle
# first we call the my_toggle method from the model to adjust changes in the database
    @interest= Interest.my_toggle(params[:id])
   @interests = Interest.get_all_interests
   @deleted = Interest.is_deleted(params[:id])

if !@deleted 

 $savedinterest = true
# if the interest was deleted and the admin restored it successfully a flash appears endicating that
flash[:success] = " Interest Restored Successfuly "
else

$savedinterest = true
# if the interest wasn't deleted and the admin blocked it successfully a flash appears endicating that
flash[:success] = " Interest Blocked Successfuly "
end
# finally , we redirect to the main interest's page after adjusting the changes
 redirect_to @interest
end
end
