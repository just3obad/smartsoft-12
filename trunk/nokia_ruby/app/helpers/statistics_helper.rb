module StatisticsHelper

include StoriesHelper
include InterestsHelper
include UsersHelper

#This is the method that should return the data of statistics of an interest
#with this format first element in the data arrays is ARRAY OF "No Of Users",
#second one is "No Of Articles"
#third one is "No of videos"
#and forth one is "No of images"
 def get_interest_stat(interest_id)
  #dataa=[[1,2,3,4,5,6,9,7,8,14,1,2,3,4,5,6,9,7,8,14,1,2,3,4,5,6,9,7,8,14],[2,4,6,8,10,7,4,6,8,7,2,4,6,8,10,7,4,6,8,7,2,4,6,8,10,7,4,6,8,7],[2,10,5,15,4,1,3,5,7,9,2,10,5,15,4,1,3,5,7,9,2,10,5,15,4,1,3,5,7,9],[8,5,8,3,0,7,5,6,4,3,8,5,8,3,0,7,5,6,4,3,8,5,8,3,0,7,5,6,4,3]]
  
 end
 

#This is the method that should return the data of statistics of an story
#with this format first element in the data arrays is ARRAY OF "No Of Shares",
#second one is "No Of Likes"
#third one is "No of Dislikes"
#and forth one is "No of Spams"
 def get_story_stat(story_id)
 
 s = get_no_of_shares(story_id)
 n = get_no_of_likes(story_id)
 m = get_no_of_dislikes(story_id)
 p = get_no_of_spams(story_id)
 data = "[#{s},#{n},#{m},#{p}]"
 end


 #This is the method that should return the data of statistics of an story
#with this format first element in the data arrays is ARRAY OF "No Of Shares",
#second one is "No Of Likes"
#third one is "No of Dislikes"
#and forth one is "No of Spams"
#and fifth one is "No of Comments"
 def get_user_stat(user_id)
  #dataa=[[1,2,3,4,5,6,9,7,8,14,1,2,3,4,5,6,9,7,8,14,1,2,3,4,5,6,9,7,8,14],[2,4,6,8,10,7,4,6,8,7,2,4,6,8,10,7,4,6,8,7,2,4,6,8,10,7,4,6,8,7],[2,10,5,15,4,1,3,5,7,9,2,10,5,15,4,1,3,5,7,9,2,10,5,15,4,1,3,5,7,9],[8,5,8,3,0,7,5,6,4,3,8,5,8,3,0,7,5,6,4,3,8,5,8,3,0,7,5,6,4,3]]
 end
 
end
