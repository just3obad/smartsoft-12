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
 
end
