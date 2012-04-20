module StoriesHelper

#This method gets the number of shares of a certain story using its id in the last 30 days
def get_no_of_shares(storyId)

creationOfStory = Story.find(storyId).created_at_before_type_cast.to_date
lastUpdated = Story.find(storyId).updated_at_before_type_cast.to_date
deleted = false
#Story.where(:id => story_id).select("deleted")
#There are several cases concerning the date of creation, last update and deletion of the story that has to handeled:
#1) If the story was created and deleted within the last 30 days, then I only return the shares from the table between the creation date and the last update which is the deletion:
  if deleted && creationOfStory > 30.days.ago.to_date && lastUpdated > 30.days.ago.to_date
    shares_by_day = Share.where(:created_at => creationOfStory..lastUpdated, :story_id => storyId).group("date(created_at)").select("created_at, count(story_id) as noOfSharesPerDay")
    (creationOfStory.to_date..lastUpdated.to_date).map do |date|
      share = shares_by_day.detect { |share| share.created_at.to_date == date }
      share && share.noOfSharesPerDay.to_i || 0
    end.inspect
#2) If the story was created before the last 30 days and deleted within the last 30 days, then I only return the shares from the table between the last 30 days and the last update which is the deletion:
  elsif deleted && creationOfStory <= 30.days.ago.to_date && lastUpdated > 30.days.ago.to_date
    shares_by_day = Share.where(:created_at => 30.days.ago..lastUpdated, :story_id => storyId).group("date(created_at)").select("created_at, count(story_id) as noOfSharesPerDay")
    (30.days.ago.to_date..lastUpdated.to_date).map do |date|
      share = shares_by_day.detect { |share| share.created_at.to_date == date }
      share && share.noOfSharesPerDay.to_i || 0
    end.inspect
#3) If the story was created and deleted before the last 30 days, then I will return an empty array:
  elsif deleted && creationOfStory <= 30.days.ago.to_date && lastUpdated <= 30.days.ago.to_date
    shares_by_day = []
#4) If the story wasn't deleted and it was created before the last 30 days, then I return the shares from the table between the last 30 days and the current date:
  elsif creationOfStory <= 30.days.ago.to_date
    shares_by_day = Share.where(:created_at => 30.days.ago..Time.zone.now.end_of_day, :story_id => storyId).group("date(created_at)").select("created_at, count(story_id) as noOfSharesPerDay")
    (30.days.ago.to_date..Date.today).map do |date|
      share = shares_by_day.detect { |share| share.created_at.to_date == date }
      share && share.noOfSharesPerDay.to_i || 0
    end.inspect
#5) If the story wasn't deleted and it was created within the last 30 days, then I return the shares from the table between the creation date of the story and the current date:
  else
    shares_by_day = Share.where(:created_at => creationOfStory.beginning_of_day..Time.zone.now.end_of_day, :story_id => storyId).group("date(created_at)").select("created_at, count(story_id) as noOfSharesPerDay")
    (creationOfStory.to_date..Date.today).map do |date|
      share = shares_by_day.detect { |share| share.created_at.to_date == date }
      share && share.noOfSharesPerDay.to_i || 0
    end.inspect
  end
  end

#This method gets the number of likes of a certain story using its id in the last 30 days
def get_no_of_likes(storyId)

creationOfStory = Story.find(storyId).created_at_before_type_cast.to_date
lastUpdated = Story.find(storyId).updated_at_before_type_cast.to_date
deleted = false
#Story.where(:id => story_id).select("deleted")
#There are several cases concerning the date of creation, last update and deletion of the story that has to handeled:
#1) If the story was created and deleted within the last 30 days, then I only return the likes from the table between the creation date and the last update which is the deletion:
  if deleted && creationOfStory > 30.days.ago.to_date && lastUpdated > 30.days.ago.to_date
    likes_by_day = Likedislike.where(:created_at => creationOfStory..lastUpdated, :story_id => storyId, :action => 1).group("date(created_at)").select("created_at, count(story_id) as noOfLikesPerDay")
    (creationOfStory.to_date..lastUpdated.to_date).map do |date|
      like = likes_by_day.detect { |like| like.created_at.to_date == date }
      like && like.noOfLikesPerDay.to_i || 0
    end.inspect
#2) If the story was created before the last 30 days and deleted within the last 30 days, then I only return the likes from the table between the last 30 days and the last update which is the deletion:
  elsif deleted && creationOfStory <= 30.days.ago.to_date && lastUpdated > 30.days.ago.to_date
    likes_by_day = Likedislike.where(:created_at => 30.days.ago..lastUpdated, :story_id => storyId, :action => 1).group("date(created_at)").select("created_at, count(story_id) as noOfLikesPerDay")
    (30.days.ago.to_date..lastUpdated.to_date).map do |date|
      like = likes_by_day.detect { |like| like.created_at.to_date == date }
      like && like.noOfLikesPerDay.to_i || 0
    end.inspect
#3) If the story was created and deleted before the last 30 days, then I will return an empty array:
  elsif deleted && creationOfStory <= 30.days.ago.to_date && lastUpdated <= 30.days.ago.to_date
    likes_by_day = []
#4) If the story wasn't deleted and it was created before the last 30 days, then I return the likes from the table between the last 30 days and the current date:
  elsif creationOfStory <= 30.days.ago.to_date
    likes_by_day = Likedislike.where(:created_at => 30.days.ago..Time.zone.now.end_of_day, :story_id => storyId, :action => 1).group("date(created_at)").select("created_at, count(story_id) as noOfLikesPerDay")
    (30.days.ago.to_date..Date.today).map do |date|
      like = likes_by_day.detect { |like| like.created_at.to_date == date }
      like && like.noOfLikesPerDay.to_i || 0
    end.inspect
#5) If the story wasn't deleted and it was created within the last 30 days, then I return the likes from the table between the creation date of the story and the current date:
  else
    likes_by_day = Likedislike.where(:created_at => creationOfStory.beginning_of_day..Time.zone.now.end_of_day, :story_id => storyId, :action => 1).group("date(created_at)").select("created_at, count(story_id) as noOfLikesPerDay")
    (creationOfStory.to_date..Date.today).map do |date|
      like = likes_by_day.detect { |like| like.created_at.to_date == date }
      like && like.noOfLikesPerDay.to_i || 0
    end.inspect
  end
  end
  
#This method gets the number of dislikes of a certain story using its id in the last 30 days
def get_no_of_dislikes(storyId)

creationOfStory = Story.find(storyId).created_at_before_type_cast.to_date
lastUpdated = Story.find(storyId).updated_at_before_type_cast.to_date
deleted = false
#Story.where(:id => story_id).select("deleted")
#There are several cases concerning the date of creation, last update and deletion of the story that has to handeled:
#1) If the story was created and deleted within the last 30 days, then I only return the dislikes from the table between the creation date and the last update which is the deletion:
  if deleted && creationOfStory > 30.days.ago.to_date && lastUpdated > 30.days.ago.to_date
    dislikes_by_day = Likedislike.where(:created_at => creationOfStory..lastUpdated, :story_id => storyId, :action => -1).group("date(created_at)").select("created_at, count(story_id) as noOfDislikesPerDay")
    (creationOfStory.to_date..lastUpdated.to_date).map do |date|
      dislike = dislikes_by_day.detect { |dislike| dislike.created_at.to_date == date }
      dislike && like.noOfDislikesPerDay.to_i || 0
    end.inspect
#2) If the story was created before the last 30 days and deleted within the last 30 days, then I only return the dislikes from the table between the last 30 days and the last update which is the deletion:
  elsif deleted && creationOfStory <= 30.days.ago.to_date && lastUpdated > 30.days.ago.to_date
    dislikes_by_day = Likedislike.where(:created_at => 30.days.ago..lastUpdated, :story_id => storyId, :action => -1).group("date(created_at)").select("created_at, count(story_id) as noOfDislikesPerDay")
    (30.days.ago.to_date..lastUpdated.to_date).map do |date|
      dislike = dislikes_by_day.detect { |dislike| dislike.created_at.to_date == date }
      dislike && dislike.noOfDislikesPerDay.to_i || 0
    end.inspect
#3) If the story was created and deleted before the last 30 days, then I will return an empty array:
  elsif deleted && creationOfStory <= 30.days.ago.to_date && lastUpdated <= 30.days.ago.to_date
    dislikes_by_day = []
#4) If the story wasn't deleted and it was created before the last 30 days, then I return the dislikes from the table between the last 30 days and the current date:
  elsif creationOfStory <= 30.days.ago.to_date
    dislikes_by_day = Likedislike.where(:created_at => 30.days.ago..Time.zone.now.end_of_day, :story_id => storyId, :action => -1).group("date(created_at)").select("created_at, count(story_id) as noOfDislikesPerDay")
    (30.days.ago.to_date..Date.today).map do |date|
      dislike = dislikes_by_day.detect { |dislike| dislike.created_at.to_date == date }
      dislike && dislike.noOfDislikesPerDay.to_i || 0
    end.inspect
#5) If the story wasn't deleted and it was created within the last 30 days, then I return the dislikes from the table between the creation date of the story and the current date:
  else
    dislikes_by_day = Likedislike.where(:created_at => creationOfStory.beginning_of_day..Time.zone.now.end_of_day, :story_id => storyId, :action => -1).group("date(created_at)").select("created_at, count(story_id) as noOfDislikesPerDay")
    (creationOfStory.to_date..Date.today).map do |date|
      dislike = dislikes_by_day.detect { |dislike| dislike.created_at.to_date == date }
      dislike && dislike.noOfDislikesPerDay.to_i || 0
    end.inspect
  end
  end
  
#This method gets the number of spams of a certain story using its id in the last 30 days
def get_no_of_spams(storyId)

creationOfStory = Story.find(storyId).created_at_before_type_cast.to_date
lastUpdated = Story.find(storyId).updated_at_before_type_cast.to_date
deleted = false
#Story.where(:id => story_id).select("deleted")
#There are several cases concerning the date of creation, last update and deletion of the story that has to handeled:
#1) If the story was created and deleted within the last 30 days, then I only return the spams from the table between the creation date and the last update which is the deletion:
  if deleted && creationOfStory > 30.days.ago.to_date && lastUpdated > 30.days.ago.to_date
    spams_by_day = Spam.where(:created_at => creationOfStory..lastUpdated, :story_id => storyId).group("date(created_at)").select("created_at, count(story_id) as noOfSpamsPerDay")
    (creationOfStory.to_date..lastUpdated.to_date).map do |date|
      spam = spams_by_day.detect { |spam| spam.created_at.to_date == date }
      spam && spam.noOfSpamsPerDay.to_i || 0
    end.inspect
#2) If the story was created before the last 30 days and deleted within the last 30 days, then I only return the spams from the table between the last 30 days and the last update which is the deletion:
  elsif deleted && creationOfStory <= 30.days.ago.to_date && lastUpdated > 30.days.ago.to_date
    spams_by_day = Spam.where(:created_at => 30.days.ago..lastUpdated, :story_id => storyId).group("date(created_at)").select("created_at, count(story_id) as noOfSpamsPerDay")
    (30.days.ago.to_date..lastUpdated.to_date).map do |date|
      spam = spams_by_day.detect { |spam| spam.created_at.to_date == date }
      spam && spam.noOfSpamsPerDay.to_i || 0
    end.inspect
#3) If the story was created and deleted before the last 30 days, then I will return an empty array:
  elsif deleted && creationOfStory <= 30.days.ago.to_date && lastUpdated <= 30.days.ago.to_date
    spams_by_day = []
#4) If the story wasn't deleted and it was created before the last 30 days, then I return the spams from the table between the last 30 days and the current date:
  elsif creationOfStory <= 30.days.ago.to_date
    spams_by_day = Spam.where(:created_at => 30.days.ago..Time.zone.now.end_of_day, :story_id => storyId).group("date(created_at)").select("created_at, count(story_id) as noOfSpamsPerDay")
    (30.days.ago.to_date..Date.today).map do |date|
      spam = spams_by_day.detect { |spam| spam.created_at.to_date == date }
      spam && spam.noOfSpamsPerDay.to_i || 0
    end.inspect
#5) If the story wasn't deleted and it was created within the last 30 days, then I return the spams from the table between the creation date of the story and the current date:
  else
    spams_by_day = Spam.where(:created_at => creationOfStory.beginning_of_day..Time.zone.now.end_of_day, :story_id => storyId).group("date(created_at)").select("created_at, count(story_id) as noOfSpamsPerDay")
    (creationOfStory.to_date..Date.today).map do |date|
      spam = spams_by_day.detect { |spam| spam.created_at.to_date == date }
      spam && spam.noOfSpamsPerDay.to_i || 0
    end.inspect
  end
  end

#This method when called will return the difference between today and the day the story was created in in days.
def get_story_start_date(story_id)

creationOfStory = Story.find(story_id).created_at_before_type_cast.to_date
lastUpdated = Story.find(story_id).updated_at_before_type_cast.to_date
deleted = false
#Story.where(:id => story_id).select("deleted")
if deleted && creationOfStory > 30.days.ago.to_date && lastUpdated > 30.days.ago.to_date
  date = lastUpdated - creationOfStory
elsif deleted && creationOfStory <= 30.days.ago.to_date && lastUpdated > 30.days.ago.to_date
  date = lastUpdated - 30.days.ago.to_date
elsif deleted && creationOfStory <= 30.days.ago.to_date && lastUpdated <= 30.days.ago.to_date
  date = 0
elsif creationOfStory <= 30.days.ago.to_date
  date = (Date.today) - 30.days.ago.to_date
else
  date = (Date.today) - creationOfStory
end
end

end
