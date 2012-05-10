class Interest < ActiveRecord::Base
  
#attributes  that can be modified automatically by outside users
  attr_accessible :description, :name, :image, :deleted
  
  has_many :stories
  has_many :feeds, :dependent => :destroy

 # RSS feed link has to be of the form "http://www.abc.com"
LINK_regex = /^(?:(?:http|https):\/\/[a-z0-9]+(?:[\-\.]{1}[a-z0-9]+)*\.[a-z]{2,6}(?::[0-9]{1,5})?(\/.*)?)|(?:^$)$/ix
 
  # name cannot be duplicated and has to be there .

validates :name, :presence => true,
                 :uniqueness => {:case_sensitive => false},
                 :length   => { :maximum => 20 }

                 



 


#description can never exceed 240 characters .
  validates :description,  :length   => { :maximum => 100 }


# the image will be entered using a URL link whci should also be of the form  "http://www. xxxx.jpg"
  validates :image,  :format   => { :with => LINK_regex }

  '''This method when called will return the difference between today and the day
  the interest was created in days.

  first to get when the interest was created
  then to get when the interest was updated last time
  then check if the interest is deleted or not
  
  the cases are
  case 1 if the interest is deleted and it is created within the last 30 day
  but its last update was within the last 30 days
  case 2 if the interest is deleted and its created before the last 30 days
  case 3 if the interest is deleted and its created before the last 30 days 
  and its last update was before the last 30 days
  case 4 if the interest is not deleted and its created before the last 30 days
  case 5 if the interest is not deleted and its created within the last 30 days'''
  
  def self.get_interest_start_date(interest_id)

  interest_create_date = Interest.find(interest_id).created_at.to_date   
  interest_last_update_date = Interest.find(interest_id).updated_at.to_date   
  deleted = Interest.find(interest_id).deleted_before_type_cast 
  
  
  
  if deleted && interest_create_date >= 30.days.ago.to_date &&
     interest_create_date >= 30.days.ago.to_date

      date = Time.zone.now.to_date - interest_create_date
   
  
  elsif deleted && interest_create_date < 30.days.ago.to_date && 
        interest_create_date >= 30.days.ago.to_date

      date = Time.zone.now.to_date - 30.days.ago.to_date
  
  elsif deleted && interest_create_date < 30.days.ago.to_date && 
        interest_create_date < 30.days.ago.to_date

      date = -1
  
  elsif interest_create_date < 30.days.ago.to_date

      date = Time.zone.now.to_date - 30.days.ago.to_date
  
  else

      date = Time.zone.now.to_date - interest_create_date

   end
  end
  


  '''this method when called will get the number of stories in an interest for 
     each day.
  first to get when the interest was created
  then to get when the interest was updated last time
  then check if the interest is deleted or not
  
  the cases are
  
  case 1 if the interest is deleted and it is created within the last 30 day
  but its last update was within the last 30 days:
  get all the stories within the creation and deletion of the interest and 
  group by the date of creation then get the count of the stories added to the 
  interest per day and 0 if no stories were added
  
  case 2 if the interest is deleted and its created before the last 30 days:
  first get all the stories within the last 30 days and the last update of the 
  interest and group by the date of creation.
  then get the count of the stories added to the interest per day and 0 if
  no stories were added

  case 3 if the interest is deleted and its created before the last 30 days
  and its last update was before the last 30 days:
  return 0 as there are no stories added within the last 30 days

  case 4 if the interest is not deleted and its created before the last 30 days:
  get all the stories within the creation of the interest until the current date
  and group by the date of creation.
  then get the count of the stories added to the interest per day and 0 
  if no stories were added

  case 5 if the interest is not deleted and its created within the last 30 days:
  get all the stories within interest creation date until the current date and
  group by the date of creation.
  then get the count of the stories added to the interest per day and 0 if 
  no stories were added
  '''
  def self.get_num_stories_in_interest_day(interest_id)

  interest_create_date = Interest.find(interest_id).created_at
  interest_last_update_date = Interest.find(interest_id).updated_at 
  deleted = Interest.find(interest_id).deleted_before_type_cast 

  

  if deleted && interest_create_date >= 30.days.ago.to_date && 
     interest_last_update_date >= 30.days.ago.to_date

      stories_per_day = Story.where(:created_at => interest_create_date.beginning_of_day..
      interest_last_update.end_of_day , :interest_id => interest_id)
      .group("date(created_at)").select("created_at , count(id) as strys_day")

      (interest_create_date.to_date..interest_last_update.to_date).map do |date|
      story = stories_per_day.detect { |story| story.created_at.to_date == date}
      story && story.strys_day.to_i || 0
          end.inspect
  

  elsif deleted && interest_create_date < 30.days.ago.to_date && 
        interest_last_update_date >= 30.days.ago.to_date

         stories_per_day = Story.where(:created_at => 30.days.ago.beginning_of_day..
         interest_last_update.end_of_day , :interest_id => interest_id)
         .group("date(created_at)").select("created_at , count(id) as strys_day") 
  
         (30.days.ago.to_date..interest_last_update.to_date).map do |date|
         story = stories_per_day.detect { |story| story.created_at.to_date == date}
         story && story.strys_day.to_i || 0  
           end.inspect


  elsif deleted && interest_create_date < 30.days.ago.to_date && 
        interest_create_date < 30.days.ago.to_date
  
          stories_per_day = []  
  

  elsif interest_create_date < 30.days.ago.to_date

         stories_per_day = Story.where(:created_at => 30.days.ago.beginning_of_day..
         Time.zone.now.end_of_day , :interest_id => interest_id)
         .group("date(created_at)").select("created_at , count(id) as strys_day") 
  
         (30.days.ago.to_date..Time.zone.now.to_date).map do |date|
         story = stories_per_day.detect { |story| story.created_at.to_date == date}
         story && story.strys_day.to_i || 0
     end.inspect

  else

    stories_per_day = Story.where(:created_at => interest_create_date.beginning_of_day..
    Time.zone.now.end_of_day , :interest_id => interest_id)
    .group("date(created_at)").select("created_at , count(id) as strys_day") 
    
    (interest_create_date.to_date..Time.zone.now.to_date).map do |date|
    story = stories_per_day.detect { |story| story.created_at.to_date == date}
    story && story.strys_day.to_i || 0
     end.inspect

  end
 end 

  '''this method when called will get the number of users who added an interest
  for each day.
  first to get when the interest was created
  then to get when the interest was updated last time
  then check if the interest is deleted or not
  
  the cases are
  
  case 1 if the interest is deleted and it is created within the last 30 day:
  but its last update was within the last 30 days
  get all the users within the creation and deletion of the interest and 
  group by the date of creation then get the count of the users added the 
  interest per day and 0 if no users added it
  
  case 2 if the interest is deleted and its created before the last 30 days:
  first get all the users within the last 30 days and the last update of the 
  interest and group by the date of creation.
  then get the count of the users added the interest per day and 0 if
  no users added it

  case 3 if the interest is deleted and its created before the last 30 days
  and its last update was before the last 30 days:
  return 0 as there are no users added the interest within the last 30 days

  case 4 if the interest is not deleted and its created before the last 30 days:
  get all the users within the creation of the interest until the current date
  and group by the date of creation.
  then get the count of the users added the interest per day and 0 
  if no user added it

  case 5 if the interest is not deleted and its created within the last 30 days:
  get all the users within interest creation date until the current date and
  group by the date of creation.
  then get the count of the users added the interest per day and 0 if 
  no user added it
  '''
  def self.get_num_users_added_interest_day(interest_id)

    interest_create_date = Interest.find(interest_id).created_at 
    interest_last_update_date = Interest.find(interest_id).updated_at
    deleted = Interest.find(interest_id).deleted_before_type_cast 


  if deleted && interest_create_date >= 30.days.ago.to_date && 
     interest_last_update_date >= 30.days.ago.to_date
  
     users_per_day = UserAddInterest.where(:created_at => interest_create_date
     .beginning_of_day..interest_last_update.end_of_day , :interest_id => interest_id)
     .group("date(created_at)").select("created_at , count(user_id) as usrs_day")
 
     (interest_create_date.to_date..interest_last_update.to_date).map do |date|
     user = users_per_day.detect { |user| user.created_at.to_date == date}
     user && user.usrs_day.to_i || 0
       end.inspect
  

  elsif deleted && interest_create_date < 30.days.ago.to_date && 
        interest_last_update_date >= 30.days.ago.to_date
  
        users_per_day = UserAddInterest.where(:created_at => 30.days.ago.
        beginning_of_day..interest_last_update.end_of_day , :interest_id => interest_id)
        .group("date(created_at)").select("created_at , count(user_id) as usrs_day") 
        
        (30.days.ago.to_date..interest_last_update.to_date).map do |date|
        user = users_per_day.detect { |user| user.created_at.to_date == date}
        user && user.usrs_day.to_i || 0  
          end.inspect


  elsif deleted && interest_create_date < 30.days.ago.to_date && 
        interest_last_update_date < 30.days.ago.to_date
  
       users_per_day = []  
  

  elsif interest_create_date < 30.days.ago.to_date
  
      users_per_day = UserAddInterest.where(:created_at => 30.days.ago.beginning_of_day..
      Time.zone.now.end_of_day , :interest_id => interest_id)
      .group("date(created_at)").select("created_at , count(user_id) as usrs_day") 
  
      (30.days.ago.to_date..Time.zone.now.to_date).map do |date|
      user = users_per_day.detect { |user| user.created_at.to_date == date}
      user && user.usrs_day.to_i || 0
        end.inspect

  else
 
      users_per_day = UserAddInterest.where(:created_at => interest_create_date.
      beginning_of_day..Time.zone.now.end_of_day , :interest_id => interest_id)
      .group("date(created_at)").select("created_at , count(user_id) as usrs_day") 
  
      (interest_create_date.to_date..Time.zone.now.to_date).map do |date|
      user = users_per_day.detect { |user| user.created_at.to_date == date}
      user && user.usrs_day.to_i || 0
        end.inspect
  end
 end

 '''these methods are to get all the general info regarding the statistics of 
    the interest from the database , given its id as a parameter

 #to get the count of the stories inside the given interest'''
 def self.get_interest_num_stories(interestId) 

 num_stories_in_interest = Story.where(:interest_id => interestId).count
 
 end
 
 '''to get the count of the users who added this interest'''
 def self.get_total_num_user_added_interest(interestId)

 num_users_added_interest = UserAddInterest.where(:interest_id => interestId).count 
 
 end

'''This is the method that should return the data of statistics of an interest
 with this format first element in the data arrays is ARRAY OF "No Of Users",
 second one is "No Of stories"'''
 def self.get_interest_stat(interest_id)
 sto = get_num_stories_in_interest_day(interest_id)
 usr = get_num_users_added_interest_day(interest_id)
 data ="[#{usr} , #{sto}]"
 end

end
