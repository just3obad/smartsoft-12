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
  
  #this method when called will get the number of videos in an interest for each day
  def get_num_videos_in_interest(interest_id)
  interest_create_date = Interest.find(interest_id).created_at_before_type_cast.to_date #to get when the interest was created
  interest_last_update_date = Interest.find(interest_id).updated_at_before_type_cast.to_date #to get when the interest was updated last time
  deleted = Interest.find(interest_id).select("deleted") #to check if the interest is deleted or not
  
  #case 1 if the interest is deleted and it's created within the last 30 days
  if deleted && interest_create_date > 30.days.ago.to_date && interest_create_date > 30.days.ago.to_date
  videos_per_day = Story.where(:created_at => interest_create_date.to_date..interest_last_update.to_date , :interest_id = interest_id , :type = "video" , :deleted = false).group("date(created_at)").select("created_at , count(story_id) as vids_day") #to get all the videos within the creation and deletion of the interest and group by the date of creation
  (interest_create_date.to_date..interest_last_update.to_date).map do |date|
  video = videos_per_day.detect { |video| video.created_at.to_date == date}
  video && video.vids_day.to_i || 0
  #this was to get the count of the videos added to the interest per day and 0 if no videos were added  
  end.inspect
  
  #case 2 if the interest is deleted and it's created before the last 30 days but its last update was within the last 30 days
  elsif deleted && interest_create_date <= 30.days.ago.to_date && interest_create_date > 30.days.ago.to_date
  videos_per_day = Story.where(:created_at => 30.days.ago.to_date..interest_last_update.to_date , :interest_id = interest_id , :type = "video" , :deleted = false).group("date(created_at)").select("created_at , count(story_id) as vids_day") #to get all the videos within the last 30 days and the last update of the interest and group by the date of creation
  (30.days.ago.to_date..interest_last_update.to_date).map do |date|
  video = videos_per_day.detect { |video| video.created_at.to_date == date}
  video && video.vids_day.to_i || 0  
  #this was to get the count of the videos added to the interest per day and 0 if no videos were added
  end.inspect

  #case 3 if the interest is deleted and it's created before the last 30 days and its last update was before the last 30 days
  elsif deleted && interest_create_date <= 30.days.ago.to_date && interest_create_date <= 30.days.ago.to_date
  videos_per_day = [0]  #to return 0 as there are no videos added within the last 30 days
  end.inspect

  #case 4 if the interest is not deleted and it's created before the last 30 days
  elsif interest_create_date <= 30.days.ago.to_date
  videos_per_day = Story.where(:created_at => 30.days.ago.to_date..Time.zone.now.end_of_day , :interest_id = interest_id , :type = "video" , :deleted = false).group("date(created_at)").select("created_at , count(story_id) as vids_day") #to get all the videos within the creation of the interest until the current date and group by the date of creation
  (30.days.ago.to_date..Date.today).map do |date|
  video = videos_per_day.detect { |video| video.created_at.to_date == date}
  video && video.vids_day.to_i || 0
  #this was to get the count of the videos added to the interest per day and 0 if no videos were added
  end.inspect

  #case 5 if the interest is not deleted and it's created within the last 30 days
  else
  videos_per_day = Story.where(:created_at => interest_create_date..Time.zone.now.end_of_day , :interest_id = interest_id , :type = "video" , :deleted = false).group("date(created_at)").select("created_at , count(story_id) as vids_day") #to get all the videos within interest creation date until the current date and group by the date of creation
  (interest_create_date.to_date..Date.today).map do |date|
  video = videos_per_day.detect { |video| video.created_at.to_date == date}
  video && video.vids_day.to_i || 0
  #this was to get the count of the videos added to the interest per day and 0 if no videos were added
   end.inspect
  end
 end


  #this method when called will get the number of images in an interest for each day
  def get_num_images_in_interest(interest_id)
  interest_create_date = Interest.find(interest_id).created_at_before_type_cast.to_date #to get when the interest was created
  interest_last_update_date = Interest.find(interest_id).updated_at_before_type_cast.to_date #to get when the interest was updated last time
  deleted = Interest.find(interest_id).select("deleted") #to check if the interest is deleted or not
  
  #case 1 if the interest is deleted and it's created within the last 30 days
  if deleted && interest_create_date > 30.days.ago.to_date && interest_create_date > 30.days.ago.to_date
  images_per_day = Story.where(:created_at => interest_create_date.to_date..interest_last_update.to_date , :interest_id = interest_id , :type = "image" , :deleted = false).group("date(created_at)").select("created_at , count(story_id) as imgs_day") #to get all the images within the creation and deletion of the interest and group by the date of creation
  (interest_create_date.to_date..interest_last_update.to_date).map do |date|
  image = images_per_day.detect { |image| image.created_at.to_date == date}
  image && image.imgs_day.to_i || 0
  #this was to get the count of the images added to the interest per day and 0 if no images were added  
  end.inspect
  
  #case 2 if the interest is deleted and it's created before the last 30 days but its last update was within the last 30 days
  elsif deleted && interest_create_date <= 30.days.ago.to_date && interest_create_date > 30.days.ago.to_date
  images_per_day = Story.where(:created_at => 30.days.ago.to_date..interest_last_update.to_date , :interest_id = interest_id , :type = "image" , :deleted = false).group("date(created_at)").select("created_at , count(story_id) as imgs_day") #to get all the images within the last 30 days and the last update of the interest and group by the date of creation
  (30.days.ago.to_date..interest_last_update.to_date).map do |date|
  image = images_per_day.detect { |image| image.created_at.to_date == date}
  image && image.imgs_day.to_i || 0  
  #this was to get the count of the images added to the interest per day and 0 if no images were added
  end.inspect

  #case 3 if the interest is deleted and it's created before the last 30 days and its last update was before the last 30 days
  elsif deleted && interest_create_date <= 30.days.ago.to_date && interest_create_date <= 30.days.ago.to_date
  images_per_day = [0]  #to return 0 as there are no images added within the last 30 days
  end.inspect

  #case 4 if the interest is not deleted and it's created before the last 30 days
  elsif interest_create_date <= 30.days.ago.to_date
  images_per_day = Story.where(:created_at => 30.days.ago.to_date..Time.zone.now.end_of_day , :interest_id = interest_id , :type = "image" , :deleted = false).group("date(created_at)").select("created_at , count(story_id) as imgs_day") #to get all the images within 30 day ago until the current date and group by the date of creation
  (30.days.ago.to_date..Date.today).map do |date|
  image = images_per_day.detect { |image| image.created_at.to_date == date}
  image && image.imgs_day.to_i || 0
  #this was to get the count of the images added to the interest per day and 0 if no images were added
  end.inspect

  #case 5 if the interest is not deleted and it's created within the last 30 days
  else
  images_per_day = Story.where(:created_at => interest_create_date..Time.zone.now.end_of_day , :interest_id = interest_id , :type = "image" , :deleted = false).group("date(created_at)").select("created_at , count(story_id) as imgs_day") #to get all the images within interest creation until the current date and group by the date of creation
  (interest_create_date.to_date..Date.today).map do |date|
  image = images_per_day.detect { |image| image.created_at.to_date == date}
  image && image.imgs_day.to_i || 0
  #this was to get the count of the images added to the interest per day and 0 if no images were added
   end.inspect
  end
 end


  #this method when called will get the number of articles in an interest for each day
  def get_num_articles_in_interest(interest_id)
  interest_create_date = Interest.find(interest_id).created_at_before_type_cast.to_date #to get when the interest was created
  interest_last_update_date = Interest.find(interest_id).updated_at_before_type_cast.to_date #to get when the interest was updated last time
  deleted = Interest.find(interest_id).select("deleted") #to check if the interest is deleted or not
  
  #case 1 if the interest is deleted and it's created within the last 30 days
  if deleted && interest_create_date > 30.days.ago.to_date && interest_create_date > 30.days.ago.to_date
  articles_per_day = Story.where(:created_at => interest_create_date.to_date..interest_last_update.to_date , :interest_id = interest_id , :type = "article" , :deleted = false).group("date(created_at)").select("created_at , count(story_id) as artcs_day") #to get all the articles within the creation and deletion of the interest and group by the date of creation
  (interest_create_date.to_date..interest_last_update.to_date).map do |date|
  article = articles_per_day.detect { |article| article.created_at.to_date == date}
  article && article.artcs_day.to_i || 0
  #this was to get the count of the articles added to the interest per day and 0 if no articles were added  
  end.inspect
  
  #case 2 if the interest is deleted and it's created before the last 30 days but its last update was within the last 30 days
  elsif deleted && interest_create_date <= 30.days.ago.to_date && interest_create_date > 30.days.ago.to_date
  articles_per_day = Story.where(:created_at => 30.days.ago.to_date..interest_last_update.to_date , :interest_id = interest_id , :type = "article" , :deleted = false).group("date(created_at)").select("created_at , count(story_id) as artcs_day") #to get all the articles within the last 30 days and the last update of the interest and group by the date of creation
  (30.days.ago.to_date..interest_last_update.to_date).map do |date|
  article = articles_per_day.detect { |article| article.created_at.to_date == date}
  article && article.artcs_day.to_i || 0  
  #this was to get the count of the articles added to the interest per day and 0 if no articles were added
  end.inspect

  #case 3 if the interest is deleted and it's created before the last 30 days and its last update was before the last 30 days
  elsif deleted && interest_create_date <= 30.days.ago.to_date && interest_create_date <= 30.days.ago.to_date
  articles_per_day = [0]  #to return 0 as there are no articles added within the last 30 days
  end.inspect

  #case 4 if the interest is not deleted and it's created before the last 30 days
  elsif interest_create_date <= 30.days.ago.to_date
  articles_per_day = Story.where(:created_at => 30.days.ago.to_date..Time.zone.now.end_of_day , :interest_id = interest_id , :type = "article" , :deleted = false).group("date(created_at)").select("created_at , count(story_id) as artcs_day") #to get all the articles within 30 days ago until the current date and group by the date of creation
  (30.days.ago.to_date..Date.today).map do |date|
  article = articles_per_day.detect { |article| article.created_at.to_date == date}
  article && article.artcs_day.to_i || 0
  #this was to get the count of the articles added to the interest per day and 0 if no articles were added
  end.inspect

  #case 5 if the interest is not deleted and it's created within the last 30 days
  else
  articles_per_day = Story.where(:created_at => interest_create_date..Time.zone.now.end_of_day , :interest_id = interest_id , :type = "article" , :deleted = false).group("date(created_at)").select("created_at , count(story_id) as artcs_day") #to get all the articles within interest creation until the current date and group by the date of creation
  (interest_create_date.to_date..Date.today).map do |date|
  article = articles_per_day.detect { |article| article.created_at.to_date == date}
  article && article.artcs_day.to_i || 0
  #this was to get the count of the articles added to the interest per day and 0 if no articles were added
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
  users_per_day = User_Add_Interest.where(:created_at => interest_create_date.to_date..interest_last_update.to_date , :interest_id = interest_id).group("date(created_at)").select("created_at , count(user_id) as usrs_day") #to get all the users who added the interest within the creation and deletion of the interest and group by the date of creation
  (interest_create_date.to_date..interest_last_update.to_date).map do |date|
  user = users_per_day.detect { |user| user.created_at.to_date == date}
  user && user.usrs_day.to_i || 0
  #this was to get the count of the users who added the interest per day and 0 if no user did  
  end.inspect
  
  #case 2 if the interest is deleted and it's created before the last 30 days but its last update was within the last 30 days
  elsif deleted && interest_create_date <= 30.days.ago.to_date && interest_create_date > 30.days.ago.to_date
  users_per_day = User_Add_Interest.where(:created_at => 30.days.ago.to_date..interest_last_update.to_date , :interest_id = interest_id).group("date(created_at)").select("created_at , count(user_id) as usrs_day") #to get all the users who added the interest within the last 30 days and the last update of the interest and group by the date of creation
  (30.days.ago.to_date..interest_last_update.to_date).map do |date|
  user = users_per_day.detect { |user| user.created_at.to_date == date}
  user && user.usrs_day.to_i || 0  
  #this was to get the count of the users who added the interest per day and 0 if no users added it
  end.inspect

  #case 3 if the interest is deleted and it's created before the last 30 days and its last update was before the last 30 days
  elsif deleted && interest_create_date <= 30.days.ago.to_date && interest_create_date <= 30.days.ago.to_date
  users_per_day = [0]  #to return 0 as there are no users added the interest within the last 30 days
  end.inspect

  #case 4 if the interest is not deleted and it's created before the last 30 days
  elsif interest_create_date <= 30.days.ago.to_date
  users_per_day = User_Add_Interest.where(:created_at => 30.days.ago.to_date..Time.zone.now.end_of_day , :interest_id = interest_id).group("date(created_at)").select("created_at , count(user_id) as usrs_day") #to get all the users who added the interest within 30 days ago until the current date and group by the date of creation
  (30.days.ago.to_date..Date.today).map do |date|
  user = users_per_day.detect { |user| user.created_at.to_date == date}
  user && user.usrs_day.to_i || 0
  #this was to get the count of the users who added the interest per day and 0 if no users added it
  end.inspect

  #case 5 if the interest is not deleted and it's created within the last 30 days
  else
  users_per_day = User_Add_Interest.where(:created_at => interest_create_date..Time.zone.now.end_of_day , :interest_id = interest_id).group("date(created_at)").select("created_at , count(user_id) as usrs_day") #to get all the users who added the interest within interest creation until the current date and group by the date of creation
  (interest_create_date.to_date..Date.today).map do |date|
  user = users_per_day.detect { |user| user.created_at.to_date == date}
  user && user.usrs_day.to_i || 0
  #this was to get the count of the users who added the interest per day and 0 if no users added it
   end.inspect
  end
 end

 #this method is to get all the general info regarding the statistics of the interest from the database , given its id as a parameter
 def get_interest_general_statistics(interestId) 
 interest_title = Interest.find(interestId).title #storing the title of the interest in a variable
 num_videos_in_interest = Story.count.where(":interest = ? AND :type = ?",interestId,'Video') #to get the count of the videos inside the given interest
 num_images_in_interest = Story.count.where(":interest = ? AND :type = ?",interestId,'Image') #to get the count of the images inside the given interest
 num_articles_in_interest = Story.count.where(":interest = ? AND :type = ?",interestId,'Article') #to get the count of the articles inside the given interest
 num_users_added_interest = Users_Add_Interests.count.where(":interest = ?",interestId) #to get the count of the users who added this interest
 end

end
