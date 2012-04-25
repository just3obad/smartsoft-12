module StoriesHelper

#This method gets the number of shares of a certain story using its id in the last 30 days
def get_no_of_shares(story_id)

creationOfStory = Story.find(story_id).created_at.beginning_of_day
lastUpdated = Story.find(story_id).updated_at.end_of_day
hidden = Story.find(story_id).hidden_before_type_cast
#There are several cases concerning the date of creation, last update and hiding of the story that has to handeled:
#1) If the story was created and hidden within the last 30 days, then I only return the shares from the table between the creation date and the last update which is the hiding:
  if hidden && creationOfStory >= 30.days.ago.to_date && lastUpdated >= 30.days.ago.to_date
    shares_by_day = Share.where(:created_at => creationOfStory..lastUpdated, :story_id => story_id).group("date(created_at)").select("created_at, count(story_id) as noOfSharesPerDay")
    (creationOfStory.to_date..lastUpdated.to_date).map do |date|
      share = shares_by_day.detect { |share| share.created_at.to_date == date }
      share && share.noOfSharesPerDay.to_i || 0
    end.inspect
  #2) If the story was created before the last 30 days and hidden within the last 30 days, then I only return the shares from the table between the last 30 days and the last update which is the hiding:
    elsif hidden && creationOfStory < 30.days.ago.to_date && lastUpdated >= 30.days.ago.to_date
      shares_by_day = Share.where(:created_at => 30.days.ago.beginning_of_day..lastUpdated, :story_id => story_id).group("date(created_at)").select("created_at, count(story_id) as noOfSharesPerDay")
      (30.days.ago.to_date..lastUpdated.to_date).map do |date|
        share = shares_by_day.detect { |share| share.created_at.to_date == date }
        share && share.noOfSharesPerDay.to_i || 0
      end.inspect
  #3) If the story was created and hidden before the last 30 days, then I will return an empty array:
    elsif hidden && creationOfStory < 30.days.ago.to_date && lastUpdated < 30.days.ago.to_date
      shares_by_day = []
  #4) If the story wasn't hidden and it was created before the last 30 days, then I return the shares from the table between the last 30 days and the current date:
    elsif creationOfStory < 30.days.ago.to_date
      shares_by_day = Share.where(:created_at => 30.days.ago.beginning_of_day..Time.zone.now.end_of_day, :story_id => story_id).group("date(created_at)").select("created_at, count(story_id) as noOfSharesPerDay")
      (30.days.ago.to_date..Time.zone.now.to_date).map do |date|
        share = shares_by_day.detect { |share| share.created_at.to_date == date }
        share && share.noOfSharesPerDay.to_i || 0
     end.inspect
  #5) If the story wasn't hidden and it was created within the last 30 days, then I return the shares from the table between the creation date of the story and the current date:
    else
      shares_by_day = Share.where(:created_at => creationOfStory..Time.zone.now.end_of_day, :story_id => story_id).group("date(created_at)").select("created_at, count(story_id) as noOfSharesPerDay")
      (creationOfStory.to_date..Time.zone.now.to_date).map do |date|
        share = shares_by_day.detect { |share| share.created_at.to_date == date }
        share && share.noOfSharesPerDay.to_i || 0
      end.inspect
    end
end

#This method gets the number of likes of a certain story using its id in the last 30 days
def get_no_of_likes(story_id)

creationOfStory = Story.find(story_id).created_at.beginning_of_day
lastUpdated = Story.find(story_id).updated_at.end_of_day
hidden = Story.find(story_id).hidden_before_type_cast
#There are several cases concerning the date of creation, last update and hiding of the story that has to handeled:
#1) If the story was created and hidden within the last 30 days, then I only return the likes from the table between the creation date and the last update which is the hiding:
  if hidden && creationOfStory >= 30.days.ago.to_date && lastUpdated >= 30.days.ago.to_date
    likes_by_day = Likedislike.where(:created_at => creationOfStory..lastUpdated, :story_id => story_id, :action => 1).group("date(created_at)").select("created_at, count(story_id) as noOfLikesPerDay")
    (creationOfStory.to_date..lastUpdated.to_date).map do |date|
      like = likes_by_day.detect { |like| like.created_at.to_date == date }
      like && like.noOfLikesPerDay.to_i || 0
    end.inspect
  #2) If the story was created before the last 30 days and hidden within the last 30 days, then I only return the likes from the table between the last 30 days and the last update which is the hiding:
  elsif hidden && creationOfStory < 30.days.ago.to_date && lastUpdated >= 30.days.ago.to_date
    likes_by_day = Likedislike.where(:created_at => 30.days.ago.beginning_of_day..lastUpdated, :story_id => story_id, :action => 1).group("date(created_at)").select("created_at, count(story_id) as noOfLikesPerDay")
    (30.days.ago.to_date..lastUpdated.to_date).map do |date|
      like = likes_by_day.detect { |like| like.created_at.to_date == date }
      like && like.noOfLikesPerDay.to_i || 0
    end.inspect
  #3) If the story was created and hidden before the last 30 days, then I will return an empty array:
  elsif hidden && creationOfStory < 30.days.ago.to_date && lastUpdated < 30.days.ago.to_date
    likes_by_day = []
  #4) If the story wasn't hidden and it was created before the last 30 days, then I return the likes from the table between the last 30 days and the current date:
  elsif creationOfStory < 30.days.ago.to_date
    likes_by_day = Likedislike.where(:created_at => 30.days.ago.beginning_of_day..Time.zone.now.end_of_day, :story_id => story_id, :action => 1).group("date(created_at)").select("created_at, count(story_id) as noOfLikesPerDay")
    (30.days.ago.to_date..Time.zone.now.to_date).map do |date|
      like = likes_by_day.detect { |like| like.created_at.to_date == date }
      like && like.noOfLikesPerDay.to_i || 0
    end.inspect
  #5) If the story wasn't hidden and it was created within the last 30 days, then I return the likes from the table between the creation date of the story and the current date:
  else
    likes_by_day = Likedislike.where(:created_at => creationOfStory..Time.zone.now.end_of_day, :story_id => story_id, :action => 1).group("date(created_at)").select("created_at, count(story_id) as noOfLikesPerDay")
    (creationOfStory.to_date..Time.zone.now.to_date).map do |date|
      like = likes_by_day.detect { |like| like.created_at.to_date == date }
      like && like.noOfLikesPerDay.to_i || 0
    end.inspect
  end
end
  
#This method gets the number of dislikes of a certain story using its id in the last 30 days
def get_no_of_dislikes(story_id)

creationOfStory = Story.find(story_id).created_at.beginning_of_day
lastUpdated = Story.find(story_id).updated_at.end_of_day
hidden = Story.find(story_id).hidden_before_type_cast
#There are several cases concerning the date of creation, last update and deletion of the story that has to handeled:
#1) If the story was created and hiding within the last 30 days, then I only return the dislikes from the table between the creation date and the last update which is the deletion:
  if hidden && creationOfStory >= 30.days.ago.to_date && lastUpdated >= 30.days.ago.to_date
    dislikes_by_day = Likedislike.where(:created_at => creationOfStory..lastUpdated, :story_id => story_id, :action => -1).group("date(created_at)").select("created_at, count(story_id) as noOfDislikesPerDay")
    (creationOfStory.to_date..lastUpdated.to_date).map do |date|
       dislike = dislikes_by_day.detect { |dislike| dislike.created_at.to_date == date }
       dislike && like.noOfDislikesPerDay.to_i || 0
    end.inspect
  #2) If the story was created before the last 30 days and hidden within the last 30 days, then I only return the dislikes from the table between the last 30 days and the last update which is the hiding:
  elsif hidden && creationOfStory < 30.days.ago.to_date && lastUpdated >+ 30.days.ago.to_date
    dislikes_by_day = Likedislike.where(:created_at => 30.days.ago.beginning_of_day..lastUpdated, :story_id => story_id, :action => -1).group("date(created_at)").select("created_at, count(story_id) as noOfDislikesPerDay")
    (30.days.ago.to_date..lastUpdated.to_date).map do |date|
      dislike = dislikes_by_day.detect { |dislike| dislike.created_at.to_date == date }
      dislike && dislike.noOfDislikesPerDay.to_i || 0
    end.inspect
  #3) If the story was created and hidden before the last 30 days, then I will return an empty array:
  elsif hidden && creationOfStory < 30.days.ago.to_date && lastUpdated < 30.days.ago.to_date
    dislikes_by_day = []
  #4) If the story wasn't hidden and it was created before the last 30 days, then I return the dislikes from the table between the last 30 days and the current date:
  elsif creationOfStory < 30.days.ago.to_date
    dislikes_by_day = Likedislike.where(:created_at => 30.days.ago.beginning_of_day..Time.zone.now.end_of_day, :story_id => story_id, :action => -1).group("date(created_at)").select("created_at, count(story_id) as noOfDislikesPerDay")
    (30.days.ago.to_date..Time.zone.now.to_date).map do |date|
      dislike = dislikes_by_day.detect { |dislike| dislike.created_at.to_date == date }
      dislike && dislike.noOfDislikesPerDay.to_i || 0
    end.inspect
  #5) If the story wasn't hidden and it was created within the last 30 days, then I return the dislikes from the table between the creation date of the story and the current date:
  else
    dislikes_by_day = Likedislike.where(:created_at => creationOfStory..Time.zone.now.end_of_day, :story_id => story_id, :action => -1).group("date(created_at)").select("created_at, count(story_id) as noOfDislikesPerDay")
    (creationOfStory.to_date..Time.zone.now.to_date).map do |date|
      dislike = dislikes_by_day.detect { |dislike| dislike.created_at.to_date == date }
      dislike && dislike.noOfDislikesPerDay.to_i || 0
    end.inspect
  end
end
  
#This method gets the number of flags of a certain story using its id in the last 30 days
def get_no_of_flags(story_id)

creationOfStory = Story.find(story_id).created_at.beginning_of_day
lastUpdated = Story.find(story_id).updated_at.end_of_day
hidden = Story.find(story_id).hidden_before_type_cast
#There are several cases concerning the date of creation, last update and hiding of the story that has to handeled:
#1) If the story was created and hidden within the last 30 days, then I only return the flags from the table between the creation date and the last update which is the hiding:
  if hidden && creationOfStory >= 30.days.ago.to_date && lastUpdated >= 30.days.ago.to_date
    flags_by_day = Flag.where(:created_at => creationOfStory..lastUpdated, :story_id => story_id).group("date(created_at)").select("created_at, count(story_id) as noOfSpamsPerDay")
    (creationOfStory.to_date..lastUpdated.to_date).map do |date|
      flag = flags_by_day.detect { |flag| flag.created_at.to_date == date }
      flag && flag.noOfFlagsPerDay.to_i || 0
    end.inspect
  #2) If the story was created before the last 30 days and hidden within the last 30 days, then I only return the flags from the table between the last 30 days and the last update which is the hiding:
  elsif hidden && creationOfStory < 30.days.ago.to_date && lastUpdated >= 30.days.ago.to_date
    flags_by_day = Flag.where(:created_at => 30.days.ago.beginning_of_day..lastUpdated, :story_id => story_id).group("date(created_at)").select("created_at, count(story_id) as noOfSpamsPerDay")
    (30.days.ago.to_date..lastUpdated.to_date).map do |date|
      flag = flags_by_day.detect { |flag| flag.created_at.to_date == date }
      flag && flag.noOfFlagsPerDay.to_i || 0
    end.inspect
  #3) If the story was created and hidden before the last 30 days, then I will return an empty array:
  elsif hidden && creationOfStory < 30.days.ago.to_date && lastUpdated < 30.days.ago.to_date
    flags_by_day = []
  #4) If the story wasn't hidden and it was created before the last 30 days, then I return the flags from the table between the last 30 days and the current date:
  elsif creationOfStory < 30.days.ago.to_date
    flags_by_day = Flag.where(:created_at => 30.days.ago.beginning_of_day..Time.zone.now.end_of_day, :story_id => story_id).group("date(created_at)").select("created_at, count(story_id) as noOfSpamsPerDay")
    (30.days.ago.to_date..Time.zone.now.to_date).map do |date|
      flag = flags_by_day.detect { |flag| flag.created_at.to_date == date }
      flag && flag.noOfFlagsPerDay.to_i || 0
    end.inspect
  #5) If the story wasn't hidden and it was created within the last 30 days, then I return the flags from the table between the creation date of the story and the current date:
  else
    flags_by_day = Flag.where(:created_at => creationOfStory..Time.zone.now.end_of_day, :story_id => story_id).group("date(created_at)").select("created_at, count(story_id) as noOfSpamsPerDay")
    (creationOfStory.to_date..Time.zone.now.to_date).map do |date|
      flag = flags_by_day.detect { |flag| flag.created_at.to_date == date }
      flag && spam.noOfFlagsPerDay.to_i || 0
    end.inspect
  end
end

#This method when called will return the difference between today and the day the story was created in in days in all cases as mentioned in the above methods:
def get_story_start_date(story_id)

creationOfStory = Story.find(story_id).created_at.to_date
lastUpdated = Story.find(story_id).updated_at.to_date
hidden = Story.find(story_id).hidden_before_type_cast
if hidden && creationOfStory >= 30.days.ago.to_date && lastUpdated >= 30.days.ago.to_date
  date = Time.zone.now.to_date - creationOfStory
elsif hidden && creationOfStory < 30.days.ago.to_date && lastUpdated >= 30.days.ago.to_date
  date = Time.zone.now.to_date - 30.days.ago.to_date
elsif hidden && creationOfStory < 30.days.ago.to_date && lastUpdated < 30.days.ago.to_date
  date = -1
elsif creationOfStory < 30.days.ago.to_date
  date = Time.zone.now.to_date - 30.days.ago.to_date
else
  date = Time.zone.now.to_date - creationOfStory
end
end

#This method gets the number of total shares of a certain story given its id:
def get_total_number_of_shares(story_id)
  number = Share.where(:story_id => story_id).count
end

#This method gets the number of total likes of a certain story given its id:
def get_total_number_of_likes(story_id)
  number = Likedislike.where(:story_id => story_id, :action => 1).count
end

#This method gets the number of total dislikes of a certain story given its id:
def get_total_number_of_dislikes(story_id)
  number = Likedislike.where(:story_id => story_id, :action => -1).count
end

#This method gets the number of total flags of a certain story given its id:
def get_total_number_of_flags(story_id)
  number = Flag.where(:story_id => story_id).count
end

#This method gets the interest that the story belongs too:
def get_interest_of_story(story_id)
  interestID = Story.find(story_id).interest_id
  interest = Interest.find(interestID).name
end

#the method of fetching rss feeds

require 'rss/1.0'
require 'rss/2.0'
require 'open-uri'


def fetchRss(link)

#source = "http://feeds.abcnews.com/abcnews/topstories" # url or local file
source = link
content = "" # raw content of rss feed will be loaded here

#parsing the url
open(source) do |s| content = s.read end
rss = RSS::Parser.parse(content, false)


i = 0
num = rss.items.size
#creating the array of stories
listOfStories = Array.new()
puts listOfStories[num]


#creating the stories and put them in the array
while i < num do

stitle = rss.items[i].title
sdate = rss.items[i].date
sdescription =  rss.items[i].description

#check if the story already exists in the database
count_of_stories_with_same_title = Story.where(:title => stitle).count

#if it is a new story, it will enter automatically
if count_of_stories_with_same_title == 0
#getting the name of the interest 
sinterest = Interest.where(:feeds => source).select("interest_id")

listOfStories[i] = Story.create(:title => stitle, :date => sdate, :body => sdescription, :rank => 0, :deleted => false, :hidden => false, :interest_id => sinterest)
elsif
#if the story exists in the database it will enter the array without modifications
#listOfStories[i] = 

sid = Story.where(:title => stitle).select("story_id").first
listOfStories[i] = Story.find(sid)
end
i+=1
if i < num
#if the array is full, return it
return listOfStories
end
end

#handling the errors of the links are not valid
rescue NoMethodError
p ' enter valid rss link'
return
rescue Errno::ENOENT
p 'enter valid link'
 return
rescue RSS::NotWellFormedError
p 'enter valid rss feed link form'

end

#a method that takes list of interest_ids and returns list of stories related to those interests
  def get_stories(interests)
#create new array to return the stories in
  @stories=Array.new
#loop for each interest and queries the database to return all the stories which belongs to this specific interest and append them to the stories list.
  interests.each do |interest|
	 @stories += Story.find_all_by_interest_id(interest)
  end
#return the list after looping on all the entered interests
  return @stories
  end


end
