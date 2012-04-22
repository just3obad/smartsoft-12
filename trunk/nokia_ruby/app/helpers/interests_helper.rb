module InterestsHelper
  #This method when called will return an array of ActiveRecords having all interests in the database.
  def get_all_interests
    interests=Interest.where(:deleted => false)
  end
  
  #This method when called will return the difference between today and the day the interest was created in days.
  def get_interest_start_date(interest_id)
  interest_create_date = Interest.find(interest_id).created_at_before_type_cast.to_date #to get when the interest was created
  interest_last_update_date = Interest.find(interest_id).updated_at_before_type_cast.to_date #to get when the interest was updated last time
  deleted = Interest.find(interest_id).select("deleted") #to check if the interest is deleted or not
  
  #case 1 if the interest is deleted and it's created within the last 30 days
  if deleted && interest_create_date > 30.days.ago.to_date && interest_create_date > 30.days.ago.to_date
  date = interest_last_update - interest_create_date
  #case 2 if the interest is deleted and it's created before the last 30 days but its last update was within the last 30 days
  elsif deleted && interest_create_date <= 30.days.ago.to_date && interest_create_date > 30.days.ago.to_date
  date = interest_last_update - 30.days.ago.to_date
  #case 3 if the interest is deleted and it's created before the last 30 days and its last update was before the last 30 days
  elsif deleted && interest_create_date <= 30.days.ago.to_date && interest_create_date <= 30.days.ago.to_date
  date = 0
  #case 4 if the interest is not deleted and it's created before the last 30 days
  elsif interest_create_date <= 30.days.ago.to_date
  date = (Date.today) - 30.days.ago.to_date
  #case 5 if the interest is not deleted and it's created within the last 30 days
  else
  date = (Date.today) - interest_create_date
   end
  end
  
   #this method when called will get the number of stories in an interest for each day
  def get_num_stories_in_interest_day(interest_id)
  interest_create_date = Interest.find(interest_id).created_at_before_type_cast.to_date #to get when the interest was created
  interest_last_update_date = Interest.find(interest_id).updated_at_before_type_cast.to_date #to get when the interest was updated last time
  deleted = Interest.find(interest_id).select("deleted") #to check if the interest is deleted or not
  
  #case 1 if the interest is deleted and it's created within the last 30 days
  if deleted && interest_create_date > 30.days.ago.to_date && interest_create_date > 30.days.ago.to_date
  stories_per_day = Story.where(:created_at => interest_create_date.to_date..interest_last_update.to_date , :interest_id => interest_id , :deleted => false).group("date(created_at)").select("created_at , count(story_id) as strys_day") #to get all the stories within the creation and deletion of the interest and group by the date of creation
  (interest_create_date.to_date..interest_last_update.to_date).map do |date|
  story = stories_per_day.detect { |story| story.created_at.to_date == date}
  story && story.strys_day.to_i || 0
  #this was to get the count of the stories added to the interest per day and 0 if no stories were added  
  end.inspect
  
  #case 2 if the interest is deleted and it's created before the last 30 days but its last update was within the last 30 days
  elsif deleted && interest_create_date <= 30.days.ago.to_date && interest_create_date > 30.days.ago.to_date
  stories_per_day = Story.where(:created_at => 30.days.ago.to_date..interest_last_update.to_date , :interest_id => interest_id , :deleted => false).group("date(created_at)").select("created_at , count(story_id) as strys_day") #to get all the stories within the last 30 days and the last update of the interest and group by the date of creation
  (30.days.ago.to_date..interest_last_update.to_date).map do |date|
  story = stories_per_day.detect { |story| story.created_at.to_date == date}
  story && story.strys_day.to_i || 0  
  #this was to get the count of the stories added to the interest per day and 0 if no stories were added
  end.inspect

  #case 3 if the interest is deleted and it's created before the last 30 days and its last update was before the last 30 days
  elsif deleted && interest_create_date <= 30.days.ago.to_date && interest_create_date <= 30.days.ago.to_date
  stories_per_day = [0]  #to return 0 as there are no stories added within the last 30 days
  

  #case 4 if the interest is not deleted and it's created before the last 30 days
  elsif interest_create_date <= 30.days.ago.to_date
  stories_per_day = Story.where(:created_at => 30.days.ago.to_date..Time.zone.now.end_of_day , :interest_id => interest_id , :deleted => false).group("date(created_at)").select("created_at , count(story_id) as strys_day") #to get all the stories within the creation of the interest until the current date and group by the date of creation
  (30.days.ago.to_date..Date.today).map do |date|
  story = stories_per_day.detect { |story| story.created_at.to_date == date}
  story && story.strys_day.to_i || 0
  #this was to get the count of the stories added to the interest per day and 0 if no stories were added
  end.inspect

  #case 5 if the interest is not deleted and it's created within the last 30 days
  else
  stories_per_day = Story.where(:created_at => interest_create_date..Time.zone.now.end_of_day , :interest_id => interest_id , :deleted => false).group("date(created_at)").select("created_at , count(story_id) as strys_day") #to get all the stories within interest creation date until the current date and group by the date of creation
  (interest_create_date.to_date..Date.today).map do |date|
  story = stories_per_day.detect { |story| story.created_at.to_date == date}
  story && story.strys_day.to_i || 0
  #this was to get the count of the stories added to the interest per day and 0 if no stories were added
   end.inspect
  end
 end 

  #this method when called will get the number of users in an interest for each day
  def get_num_users_added_interest(interest_id)
  interest_create_date = Interest.find(interest_id).created_at_before_type_cast.to_date #to get when the interest was created
  interest_last_update_date = Interest.find(interest_id).updated_at_before_type_cast.to_date #to get when the interest was updated last time
  deleted = Interest.find(interest_id).select("deleted") #to check if the interest is deleted or not
  
  #case 1 if the interest is deleted and it's created within the last 30 days
  if deleted && interest_create_date > 30.days.ago.to_date && interest_create_date > 30.days.ago.to_date
  users_per_day = UserAddInterest.where(:created_at => interest_create_date.to_date..interest_last_update.to_date , :interest_id => interest_id).group("date(created_at)").select("created_at , count(user_id) as usrs_day") #to get all the users who added the interest within the creation and deletion of the interest and group by the date of creation
  (interest_create_date.to_date..interest_last_update.to_date).map do |date|
  user = users_per_day.detect { |user| user.created_at.to_date == date}
  user && user.usrs_day.to_i || 0
  #this was to get the count of the users who added the interest per day and 0 if no user did  
  end.inspect
  
  #case 2 if the interest is deleted and it's created before the last 30 days but its last update was within the last 30 days
  elsif deleted && interest_create_date <= 30.days.ago.to_date && interest_create_date > 30.days.ago.to_date
  users_per_day = UserAddInterest.where(:created_at => 30.days.ago.to_date..interest_last_update.to_date , :interest_id => interest_id).group("date(created_at)").select("created_at , count(user_id) as usrs_day") #to get all the users who added the interest within the last 30 days and the last update of the interest and group by the date of creation
  (30.days.ago.to_date..interest_last_update.to_date).map do |date|
  user = users_per_day.detect { |user| user.created_at.to_date == date}
  user && user.usrs_day.to_i || 0  
  #this was to get the count of the users who added the interest per day and 0 if no users added it
  end.inspect

  #case 3 if the interest is deleted and it's created before the last 30 days and its last update was before the last 30 days
  elsif deleted && interest_create_date <= 30.days.ago.to_date && interest_create_date <= 30.days.ago.to_date
  users_per_day = [0]  #to return 0 as there are no users added the interest within the last 30 days
  

  #case 4 if the interest is not deleted and it's created before the last 30 days
  elsif interest_create_date <= 30.days.ago.to_date
  users_per_day = UserAddInterest.where(:created_at => 30.days.ago.to_date..Time.zone.now.end_of_day , :interest_id => interest_id).group("date(created_at)").select("created_at , count(user_id) as usrs_day") #to get all the users who added the interest within 30 days ago until the current date and group by the date of creation
  (30.days.ago.to_date..Date.today).map do |date|
  user = users_per_day.detect { |user| user.created_at.to_date == date}
  user && user.usrs_day.to_i || 0
  #this was to get the count of the users who added the interest per day and 0 if no users added it
  end.inspect

  #case 5 if the interest is not deleted and it's created within the last 30 days
  else
  users_per_day = UserAddInterest.where(:created_at => interest_create_date..Time.zone.now.end_of_day , :interest_id => interest_id).group("date(created_at)").select("created_at , count(user_id) as usrs_day") #to get all the users who added the interest within interest creation until the current date and group by the date of creation
  (interest_create_date.to_date..Date.today).map do |date|
  user = users_per_day.detect { |user| user.created_at.to_date == date}
  user && user.usrs_day.to_i || 0
  #this was to get the count of the users who added the interest per day and 0 if no users added it
   end.inspect
  end
 end

 #these methods are to get all the general info regarding the statistics of the interest from the database , given its id as a parameter
 #num stories
 def get_interest_num_stories(interestId) 
 num_videos_in_interest = Story.count.where(":interest = ?",interestId) #to get the count of the stories inside the given interest
 end
 
 #num users who added interest
 def get_num_user_added_interest(interestId)
 num_users_added_interest = UserAddInterest.count.where(":interest = ?",interestId) #to get the count of the users who added this interest
 end

end
