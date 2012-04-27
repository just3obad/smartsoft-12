module StatisticsHelper

include StoriesHelper
include InterestsHelper
include UsersHelper

 '''This is the method that should return the data of statistics of an interest
 with this format first element in the data arrays is ARRAY OF "No Of Users",
 second one is "No Of stories"'''
 def get_interest_stat(interest_id)
 sto = get_num_stories_in_interest_day(interest_id)
 usr = get_num_users_added_interest_day(interest_id)
 data ="[#{usr} , #{sto}]"
 end
  ''' 
  This is the method that should return the data of statistics of an story
  with this format first element in the data arrays is ARRAY OF "No Of Shares",
  second one is "No Of Likes"
  third one is "No of Dislikes"
  and forth one is "No of Flags"
  '''
  def get_story_stat
 
  shares = get_no_of_activity(@shares, @creation_date, @last_update, @hidden)
  likes = get_no_of_activity(@likes, @creation_date, @last_update, @hidden)
  dislikes = get_no_of_activity(@dislikes, @creation_date, @last_update, @hidden)
  flags = get_no_of_activity(@flags, @creation_date, @last_update, @hidden)
  data = "[#{shares},#{likes},#{dislikes},#{flags}]"
  end

  '''
  This is the method that should return the data of statistics of a user
  with this format first element in the data arrays is ARRAY OF "No Of Shares",
  second one is "No Of Likes"
  third one is "No of Dislikes"
  and forth one is "No of Flags"
  and fifth one is "No of Comments"
  '''
 def get_user_stat(user_id)
  s = get_no_of_shares_user(user_id)
 n = get_no_of_likes_user(user_id)
 m = get_no_of_dislikes_user(user_id)
 p = get_no_of_flags_user(user_id)
 c = get_no_of_comments_user(user_id)
 data = "[#{s},#{n},#{m},#{p},#{c}]"
 end
 
 #to get the number of registered users per day to use it in the graph
 def get_registered_stat
 r = get_num_registered_day
 data = "[#{r}]"
 end
#to get the number of logged in users per day to use it in the graph
 def get_logged_stat
 l = get_num_logged_in_day
 data = "[#{l}]"
 end
 #to get the number of registered and logged in users per day to use it in the graph
 def get_all_users_stat
 reg = get_num_registered_day
 log = get_num_logged_in_day
 if reg.empty?
 data = []
 else 
 data = "[#{reg},#{log}]"
 end
end
 
 
end
