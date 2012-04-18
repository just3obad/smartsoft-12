module SharesHelper
def get_no_of_shares(storyId)
#this method gets the number of shares of a certain story using its id in the last 4 weeks
  shares_by_day = Share.where(:created_at => 30.days.ago..Time.zone.now.end_of_day, :story_id => storyId).group("date(created_at)").select("created_at, count(story_id) as noOfSharesPerDay")
 (30.days.ago.to_date..Date.today).map do |date|
      share = shares_by_day.detect { |share| share.created_at.to_date == date }
      share && share.noOfSharesPerDay.to_i || 0
    end.inspect
end

def get_who_shared_story(storyId)
#this method gets the users who shared a certain story (of the given id) in the last 4 weeks
 ids = (Share.where(["created_at >= ? AND created_at <= ? AND story_id == ?",4.weeks.ago,Time.zone.now.end_of_day,storyId]))
   

#:created_at => 4.weeks.ago..Time.zone.now.end_of_day, :story_id => storyId).select("user_id")
 #users_who_shared=[]
 #ids.each do |id|
 # users_who_shared << User.find(id)
 #end
end
end
