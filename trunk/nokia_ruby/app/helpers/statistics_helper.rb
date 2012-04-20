module StatisticsHelper

include StoriesHelper
include InterestsHelper
include UsersHelper

 def get_interest_stat(interest_id)
  dataa=[[1,2,3,4,5,6,9,7,8,14,1,2,3,4,5,6,9,7,8,14,1,2,3,4,5,6,9,7,8,14],[2,4,6,8,10,7,4,6,8,7,2,4,6,8,10,7,4,6,8,7,2,4,6,8,10,7,4,6,8,7],[2,10,5,15,4,1,3,5,7,9,2,10,5,15,4,1,3,5,7,9,2,10,5,15,4,1,3,5,7,9],[8,5,8,3,0,7,5,6,4,3,8,5,8,3,0,7,5,6,4,3,8,5,8,3,0,7,5,6,4,3]]
  
 end
 def get_story_stat(story_id)
 
 s = get_no_of_shares(story_id)
 n = get_no_of_likes(story_id)
 m = get_no_of_dislikes(story_id)
 p = get_no_of_spams(story_id)
 data = "[#{s},#{n},#{m},#{p}]"
 end

 
 def get_user_stat(user_id)
  dataa=[[1,2,3,4,5,6,9,7,8,14,1,2,3,4,5,6,9,7,8,14,1,2,3,4,5,6,9,7,8,14],[2,4,6,8,10,7,4,6,8,7,2,4,6,8,10,7,4,6,8,7,2,4,6,8,10,7,4,6,8,7],[2,10,5,15,4,1,3,5,7,9,2,10,5,15,4,1,3,5,7,9,2,10,5,15,4,1,3,5,7,9],[8,5,8,3,0,7,5,6,4,3,8,5,8,3,0,7,5,6,4,3,8,5,8,3,0,7,5,6,4,3]]
 end
 
end
