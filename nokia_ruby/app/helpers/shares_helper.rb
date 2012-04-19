module SharesHelper
  def get_no_of_shares(storyId)
#this method gets the number of shares of a certain story using its id in the last 30 days
creationOfStory = Story.find(storyId).created_at_before_type_cast.to_date
lastUpdated = Story.find(storyId).updated_at_before_type_cast.to_date
deleted = false
#Story.where(:id => story_id).select("deleted")
  if deleted && creationOfStory > 30.days.ago.to_date && lastUpdated > 30.days.ago.to_date
    shares_by_day = Share.where(:created_at => creationOfStory..lastUpdated, :story_id => storyId).group("date(created_at)").select("created_at, count(story_id) as noOfSharesPerDay")
    (creationOfStory.to_date..lastUpdated.to_date).map do |date|
      share = shares_by_day.detect { |share| share.created_at.to_date == date }
      share && share.noOfSharesPerDay.to_i || 0
    end.inspect
  elsif deleted && creationOfStory <= 30.days.ago.to_date && lastUpdated > 30.days.ago.to_date
    shares_by_day = Share.where(:created_at => 30.days.ago..lastUpdated, :story_id => storyId).group("date(created_at)").select("created_at, count(story_id) as noOfSharesPerDay")
    (30.days.ago.to_date..lastUpdated.to_date).map do |date|
      share = shares_by_day.detect { |share| share.created_at.to_date == date }
      share && share.noOfSharesPerDay.to_i || 0
    end.inspect
  elsif deleted && creationOfStory <= 30.days.ago.to_date && lastUpdated <= 30.days.ago.to_date
    shares_by_day = [0]
  elsif creationOfStory <= 30.days.ago.to_date
    shares_by_day = Share.where(:created_at => 30.days.ago..Time.zone.now.end_of_day, :story_id => storyId).group("date(created_at)").select("created_at, count(story_id) as noOfSharesPerDay")
    (30.days.ago.to_date..Date.today).map do |date|
      share = shares_by_day.detect { |share| share.created_at.to_date == date }
      share && share.noOfSharesPerDay.to_i || 0
    end.inspect
  else
    shares_by_day = Share.where(:created_at => creationOfStory.beginning_of_day..Time.zone.now.end_of_day, :story_id => storyId).group("date(created_at)").select("created_at, count(story_id) as noOfSharesPerDay")
    (creationOfStory.to_date..Date.today).map do |date|
      share = shares_by_day.detect { |share| share.created_at.to_date == date }
      share && share.noOfSharesPerDay.to_i || 0
    end.inspect
  end
  end

  def get_who_shared_story(storyId)
#this method gets the users who shared a certain story (of the given id) in the last 30 days
 ids = (Share.where(["created_at >= ? AND created_at <= ? AND story_id == ?",30.days.ago,Time.zone.now.end_of_day,storyId]))
  end
end
