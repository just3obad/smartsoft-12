module UsersHelper
  #This method when called will return the difference between today and the day the user registered in our app in days.
 def get_user_start_date(user_id)
 end
 #This method to get the number of users who registered per day
 def get_num_registered_day
 first_user_create_date = User.first.created_at_before_type_cast.to_date #to get the creation date of the first user
 #case 1 if the creation day was within last 30 days
 if first_user_create_date > 30.days.ago.to_date
 registered_per_day = User.where(:created_at => first_user_create_date..Time.zone.now.end_of_day).group("date(created_at)").select("created_at , count(user_id) as regs_day") #to get all the users who registered within first user creation until the current date and group by the date of creation
 (first_user_create_date.to_date..Date.today).map do |date|
  reg = registered_per_day.detect { |reg| reg.created_at.to_date == date}
  reg && reg.regs_day.to_i || 0
  #this was to get the count of the users who registered per day and 0 if no user did
 #case 2 if the creation date was before 30 days ago
  end.inspect
 else
 registered_per_day = User.where(:created_at => 30.days.ago.to_date..Time.zone.now.end_of_day).group("date(created_at)").select("created_at , count(user_id) as regs_day") #to get all the users who registered within 30 days ago until the current date and group by the date of creation
 (30.days.ago.to_date..Date.today).map do |date|
  reg = registered_per_day.detect { |reg| reg.created_at.to_date == date}
  reg && reg.regs_day.to_i || 0
 #this was to get the count of the users who registered per day and 0 if no user did
   end.inspect
  end
 end
 #This method to get the number of users who logged in per day
 def get_num_logged_in_day
 first_user_log_date = User_Log_In.first.created_at_before_type_cast.to_date #to get the creation date of the first user
 #case 1 if the first login was within last 30 days
 if first_user_log_date > 30.days.ago.to_date
 logged_per_day = User_Log_In.where(:created_at => first_user_log_date..Time.zone.now.end_of_day).group("date(created_at)").select("created_at , count(distinct(user_id)) as logs_day") #to get all the users who logged in within first user login until the current date and group by the date of creation
 (first_user_log_date.to_date..Date.today).map do |date|
  log = logged_per_day.detect { |log| log.created_at.to_date == date}
  log && log.logs_day.to_i || 0
  #this was to get the count of the users who registered per day and 0 if no user did
 #case 2 if the first user login was before 30 days ago
  end.inspect
 else
 logged_per_day = User_Log_In.where(:created_at => 30.days.ago.to_date..Time.zone.now.end_of_day).group("date(created_at)").select("created_at , count(distinct(user_id)) as logs_day") #to get all the users who logged in within 30 days ago until the current date and group by the date of creation
 (30.days.ago.to_date..Date.today).map do |date|
  log = logged_per_day.detect { |log| log.created_at.to_date == date}
  log && log.logs_day.to_i || 0
 #this was to get the count of the users who registered per day and 0 if no user did
   end.inspect
  end
 end
 #to get the start day of the statistics graph 
 def get_all_users_start_date
 first_user_create_date = User.first.created_at_before_type_cast.to_date #to get the creation date of the first user
 #case 1 if the creation day was within last 30 days
 if first_user_create_date > 30.days.ago.to_date
 date = first_user_create_date #to set the start date of the statistics to the creation of the first User
 #case 2 if the creation date was before 30 days ago
 else
 date = 30.days.ago.to_date
  end
  data=30
 end
 
 def all_user_registered
 num = User.all.count
 end
 
  def get_no_of_shares_user(user_id)

  user_reg_date = User.find(user_id).created_at_before_type_cast.to_date
  last_updated = User.find(user_id).updated_at_before_type_cast.to_date
  deactivated = User.find(user_id).deactivated_before_type_cast
  #1) If the user registered and was deactivated within the last 30 days
    if deactivated && user_reg_date > 30.days.ago.to_date
      shares_by_day = Share.where(:user_id => user_id, :created_at => user_reg_date..last_updated).group("date(created_at)").select("created_at, count(user_id) as noOfSharesPerDay")
      (user_reg_date.to_date..last_updated.to_date).map do |date|
      share = shares_by_day.detect { |share| share.created_at.to_date == date}
      share && share.noOfSharesPerDay.to_i || 0
    end.inspect
  #2) If the user registered before the last 30 days and was dactivated within the last 30 days
    elsif deactivated && user_reg_date <= 30.days.ago.to_date && last_updated > 30.days.ago.to_date
      shares_by_day = Share.where(:created_at => 30.days.ago..last_updated, :user_id => user_id).group("date(created_at)").select("created_at, count(user_id) as noOfSharesPerDay")
	  (30.days.ago.to_date..last_updated.to_date).map do |date|
      share = shares_by_day.detect { |share| share.created_at.to_date == date }
      share && share.noOfSharesPerDay.to_i || 0
      end.inspect
  #3) If the user registered and deactivated before the last 30 days
	 elsif deactivated && user_reg_date <= 30.days.ago.to_date && last_updated <= 30.days.ago.to_date
      shares_by_day = []
  #4) if the user registered before 30 days and wasn't deactivated
    elsif user_reg_date <= 30.days.ago.to_date
      shares_by_day = Share.where(:created_at => 30.days.ago..Time.zone.now.end_of_day, :user_id => user_id).group("date(created_at)").select("created_at, count(user_id) as noOfSharesPerDay")
      (30.days.ago.to_date..Time.zone.now.to_date).map do |date|
        share = shares_by_day.detect { |share| share.created_at.to_date == date }
        share && share.noOfSharesPerDay.to_i || 0
     end.inspect
  #5) if the user registered within 30 days and wasn't deactivated
    else
      shares_by_day = Share.where(:created_at => user_reg_date.beginning_of_day..Time.zone.now.end_of_day, :user_id => user_id).group("date(created_at)").select("created_at, count(user_id) as noOfSharesPerDay")
      (user_reg_date.to_date..Time.zone.now.to_date).map do |date|
        share = shares_by_day.detect { |share| share.created_at.to_date == date }
        share && share.noOfSharesPerDay.to_i || 0
      end.inspect
    end
	end
end
	  
#This method gets the number of Likes by this user in the last 30 days	  	  	
def get_no_of_likes_user(user_id)

user_reg_date = User.find(user_id).created_at_before_type_cast.to_date
last_updated = User.find(user_id).updated_at_before_type_cast.to_date
deactivated = User.find(user_id).deactivated_before_type_cast
#1) If the user registered and was deactivated within the last 30 days
  if deleted && user_reg_date > 30.days.ago.to_date && last_updated > 30.days.ago.to_date
    likes_by_day = Likedislike.where(:created_at => user_reg_date..last_updated, :user_id => user_id, :action => 1).group("date(created_at)").select("created_at, count(user_id) as noOfLikesPerDay")
    (user_reg_date.to_date..last_updated.to_date).map do |date|
      like = likes_by_day.detect { |like| like.created_at.to_date == date }
      like && like.noOfLikesPerDay.to_i || 0
    end.inspect
  #2) If the user registered before the last 30 days and was dactivated within the last 30 days
  elsif deleted && user_reg_date <= 30.days.ago.to_date && last_updated > 30.days.ago.to_date
    likes_by_day = Likedislike.where(:created_at => 30.days.ago..last_updated, :user_id => user_id, :action => 1).group("date(created_at)").select("created_at, count(user_id) as noOfLikesPerDay")
    (30.days.ago.to_date..last_updated.to_date).map do |date|
      like = likes_by_day.detect { |like| like.created_at.to_date == date }
      like && like.noOfLikesPerDay.to_i || 0
    end.inspect
  #3) If the user registered and deactivated before the last 30 days
  elsif deleted && user_reg_date <= 30.days.ago.to_date && last_updated <= 30.days.ago.to_date
    likes_by_day = []
  #4) if the user registered before 30 days and wasn't deactivated
  elsif user_reg_date <= 30.days.ago.to_date
    likes_by_day = Likedislike.where(:created_at => 30.days.ago..Time.zone.now.end_of_day, :user_id => user_id, :action => 1).group("date(created_at)").select("created_at, count(user_id) as noOfLikesPerDay")
    (30.days.ago.to_date..Time.zone.now.to_date).map do |date|
      like = likes_by_day.detect { |like| like.created_at.to_date == date }
      like && like.noOfLikesPerDay.to_i || 0
    end.inspect
  #5) if the user registered within 30 days and wasn't deactivated
  else
    likes_by_day = Likedislike.where(:created_at => user_reg_date.beginning_of_day..Time.zone.now.end_of_day, :user_id => user_id, :action => 1).group("date(created_at)").select("created_at, count(user_id) as noOfLikesPerDay")
    (user_reg_date.to_date..Time.zone.now.to_date).map do |date|
      like = likes_by_day.detect { |like| like.created_at.to_date == date }
      like && like.noOfLikesPerDay.to_i || 0
    end.inspect
  end
end
  
#This method gets the number of Dislikes by this user in the last 30 days
def get_no_of_dislikes_user(user_id)

user_reg_date = User.find(user_id).created_at_before_type_cast.to_date
last_updated = User.find(user_id).updated_at_before_type_cast.to_date
deactivated = User.find(user_id).deactivated_before_type_cast
#1) If the user registered and was deactivated within the last 30 days
  if deleted && user_reg_date > 30.days.ago.to_date && last_updated > 30.days.ago.to_date
    dislikes_by_day = Likedislike.where(:created_at => user_reg_date..last_updated, :user_id => user_id, :action => -1).group("date(created_at)").select("created_at, count(user_id) as noOfDislikesPerDay")
    (user_reg_date.to_date..last_updated.to_date).map do |date|
       dislike = dislikes_by_day.detect { |dislike| dislike.created_at.to_date == date }
       dislike && like.noOfDislikesPerDay.to_i || 0
    end.inspect
  #2) If the user registered before the last 30 days and was dactivated within the last 30 days
  elsif deleted && user_reg_date <= 30.days.ago.to_date && last_updated > 30.days.ago.to_date
    dislikes_by_day = Likedislike.where(:created_at => 30.days.ago..last_updated, :user_id => user_id, :action => -1).group("date(created_at)").select("created_at, count(user_id) as noOfDislikesPerDay")
    (30.days.ago.to_date..last_updated.to_date).map do |date|
      dislike = dislikes_by_day.detect { |dislike| dislike.created_at.to_date == date }
      dislike && dislike.noOfDislikesPerDay.to_i || 0
    end.inspect
  #3) If the user registered and deactivated before the last 30 days
  elsif deleted && user_reg_date <= 30.days.ago.to_date && last_updated <= 30.days.ago.to_date
    dislikes_by_day = []
  #4) if the user registered before 30 days and wasn't deactivated
  elsif user_reg_date <= 30.days.ago.to_date
    dislikes_by_day = Likedislike.where(:created_at => 30.days.ago..Time.zone.now.end_of_day, :user_id => user_id, :action => -1).group("date(created_at)").select("created_at, count(user_id) as noOfDislikesPerDay")
    (30.days.ago.to_date..Time.zone.now.to_date).map do |date|
      dislike = dislikes_by_day.detect { |dislike| dislike.created_at.to_date == date }
      dislike && dislike.noOfDislikesPerDay.to_i || 0
    end.inspect
  #5) if the user registered within 30 days and wasn't deactivated
  else
    dislikes_by_day = Likedislike.where(:created_at => user_reg_date.beginning_of_day..Time.zone.now.end_of_day, :user_id => user_id, :action => -1).group("date(created_at)").select("created_at, count(user_id) as noOfDislikesPerDay")
    (user_reg_date.to_date..Time.zone.now.to_date).map do |date|
      dislike = dislikes_by_day.detect { |dislike| dislike.created_at.to_date == date }
      dislike && dislike.noOfDislikesPerDay.to_i || 0
    end.inspect
  end
end

#This method gets the number of comments by this user in the last 30 days	  		
def get_no_of_comments_user(user_id)

user_reg_date = Story.find(user_id).created_at_before_type_cast.to_date
last_updated = Story.find(user_id).updated_at_before_type_cast.to_date
deactivated = User.find(user_id).deactivated_before_type_cast
#1) If the user registered and was deactivated within the last 30 days
  if deleted && user_reg_date > 30.days.ago.to_date && last_updated > 30.days.ago.to_date
    comments_by_day = Comment.where(:created_at => user_reg_date..last_updated, :user_id => user_id).group("date(created_at)").select("created_at, count(user_id) as noOfCommentsPerDay")
    (user_reg_date.to_date..last_updated.to_date).map do |date|
      comment = comments_by_day.detect { |comment| comment.created_at.to_date == date }
      comment && comment.noOfcommentsPerDay.to_i || 0
    end.inspect
  #2) If the user registered before the last 30 days and was dactivated within the last 30 days
  elsif deleted && user_reg_date <= 30.days.ago.to_date && last_updated > 30.days.ago.to_date
    comments_by_day = Comment.where(:created_at => 30.days.ago..last_updated, :user_id => user_id).group("date(created_at)").select("created_at, count(user_id) as noOfCommentsPerDay")
    (30.days.ago.to_date..last_updated.to_date).map do |date|
      comment = comments_by_day.detect { |comment| comment.created_at.to_date == date }
      comment && comment.noOfCommentsPerDay.to_i || 0
    end.inspect
  #3) If the user registered and deactivated before the last 30 days
  elsif deleted && user_reg_date <= 30.days.ago.to_date && last_updated <= 30.days.ago.to_date
    comments_by_day = []
  #4) if the user registered before 30 days and wasn't deactivated
  elsif user_reg_date <= 30.days.ago.to_date
    comments_by_day = Comment.where(:created_at => 30.days.ago..Time.zone.now.end_of_day, :user_id => user_id).group("date(created_at)").select("created_at, count(user_id) as noOfCommentsPerDay")
    (30.days.ago.to_date..Time.zone.now.to_date).map do |date|
      comment = comments_by_day.detect { |comment| comment.created_at.to_date == date }
      comment && comment.noOfCommentsPerDay.to_i || 0
    end.inspect
  #5) if the user registered within 30 days and wasn't deactivated
  else
    comments_by_day = Comment.where(:created_at => user_reg_date.beginning_of_day..Time.zone.now.end_of_day, :user_id => user_id).group("date(created_at)").select("created_at, count(user_id) as noOfCommentsPerDay")
    (user_reg_date.to_date..Time.zone.now.to_date).map do |date|
      comment = comments_by_day.detect { |comment| comment.created_at.to_date == date }
      comment && comment.noOfCommentsPerDay.to_i || 0
    end.inspect
  end
	
#This method gets the number of spams by this user in the last 30 days	  		
def get_no_of_spams_user(user_id)
user_reg_date = Story.find(user_id).created_at_before_type_cast.to_date
last_updated = Story.find(user_id).updated_at_before_type_cast.to_date
deactivated = User.find(user_id).deactivated_before_type_cast
#1) If the user registered and was deactivated within the last 30 days
  if deleted && user_reg_date > 30.days.ago.to_date && last_updated > 30.days.ago.to_date
    spams_by_day = Spam.where(:created_at => user_reg_date..last_updated, :user_id => user_id).group("date(created_at)").select("created_at, count(user_id) as noOfSpamsPerDay")
    (user_reg_date.to_date..last_updated.to_date).map do |date|
      spam = spams_by_day.detect { |spam| spam.created_at.to_date == date }
      spam && spam.noOfSpamsPerDay.to_i || 0
    end.inspect
  #2) If the user registered before the last 30 days and was dactivated within the last 30 days
  elsif deleted && user_reg_date <= 30.days.ago.to_date && last_updated > 30.days.ago.to_date
    spams_by_day = Spam.where(:created_at => 30.days.ago..last_updated, :user_id => user_id).group("date(created_at)").select("created_at, count(user_id) as noOfSpamsPerDay")
    (30.days.ago.to_date..last_updated.to_date).map do |date|
      spam = spams_by_day.detect { |spam| spam.created_at.to_date == date }
      spam && spam.noOfSpamsPerDay.to_i || 0
    end.inspect
  #3) If the user registered and deactivated before the last 30 days
  elsif deleted && user_reg_date <= 30.days.ago.to_date && last_updated <= 30.days.ago.to_date
    spams_by_day = []
  #4) if the user registered before 30 days and wasn't deactivated
  elsif user_reg_date <= 30.days.ago.to_date
    spams_by_day = Spam.where(:created_at => 30.days.ago..Time.zone.now.end_of_day, :user_id => user_id).group("date(created_at)").select("created_at, count(user_id) as noOfSpamsPerDay")
    (30.days.ago.to_date..Time.zone.now.to_date).map do |date|
      spam = spams_by_day.detect { |spam| spam.created_at.to_date == date }
      spam && spam.noOfSpamsPerDay.to_i || 0
    end.inspect
  #5) if the user registered within 30 days and wasn't deactivated
  else
    spams_by_day = Spam.where(:created_at => user_reg_date.beginning_of_day..Time.zone.now.end_of_day, :user_id => user_id).group("date(created_at)").select("created_at, count(user_id) as noOfSpamsPerDay")
    (user_reg_date.to_date..Time.zone.now.to_date).map do |date|
      spam = spams_by_day.detect { |spam| spam.created_at.to_date == date }
      spam && spam.noOfSpamsPerDay.to_i || 0
    end.inspect
  end
end
end
