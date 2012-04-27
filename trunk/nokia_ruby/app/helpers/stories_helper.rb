module StoriesHelper
  '''
  This method gets the number of a certain activity (shares, likes, dislikes, 
  flags) of a certain story using its id in each day in the last 30 days.
  There are several cases, concerning the date of creation, last update and
  hiding of the story, that has to handeled:
  1) If the story was created and hidden within the last 30 days, then I only 
  return the activity from the table between the creation date and the last 
  update which is the hiding.
  2) If the story was created before the last 30 days and hidden within the last
  30 days, then I only return the activity from the table between the last 30 
  days and the last update which is the hiding.
  3) If the story was created and hidden before the last 30 days, then I will 
  return an empty array.
  4) If the story was not hidden and it was created before the last 30 days, 
  then I return the activity from the table between the last 30 days and the 
  current date.
  5) If the story was not hidden and it was created within the last 30 days, 
  then I return the activity from the table between the creation date of the 
  story and the current date.
  '''
  def get_no_of_activity(needed_graph, creation_date, last_update, hidden)

    if hidden && creation_date >= 30.days.ago.to_date && 
       last_update >= 30.days.ago.to_date
      activities_by_day = needed_graph.where(:created_at => 
      creation_date..last_update).group("date(created_at)").select("created_at, 
      count(story_id) as noPerDay")
      (creation_date.to_date..last_update.to_date).map do |date|
        activity = activities_by_day.detect { |activity| 
        activity.created_at.to_date == date }
        activity && activity.noPerDay.to_i || 0
      end.inspect
      
    elsif hidden && creation_date < 30.days.ago.to_date && 
          last_update >= 30.days.ago.to_date
      activities_by_day = needed_graph.where(:created_at => 
      30.days.ago.beginning_of_day..last_update).group(
      "date(created_at)").select("created_at, count(story_id) as noPerDay")
      (30.days.ago.to_date..last_update.to_date).map do |date|
        activity = activities_by_day.detect { |activity| 
        activity.created_at.to_date == date }
        activity && activity.noPerDay.to_i || 0
      end.inspect
      
    elsif hidden && creation_date < 30.days.ago.to_date && 
          last_update < 30.days.ago.to_date
      activities_by_day = []
      
    elsif creation_date < 30.days.ago.to_date
      activities_by_day = needed_graph.where(:created_at => 30.days.ago.beginning_of_day..Time.zone.now.end_of_day).group(
      "date(created_at)").select("created_at, count(story_id) as noPerDay")
      (30.days.ago.to_date..Time.zone.now.to_date).map do |date|
        activity = activities_by_day.detect { |activity| 
        activity.created_at.to_date == date }
        activity && activity.noPerDay.to_i || 0
     end.inspect
    else
    
      activities_by_day = needed_graph.where(
      :created_at => creation_date..Time.zone.now.end_of_day).group(
      "date(created_at)").select("created_at, count(story_id) as noPerDay")
      (creation_date.to_date..Time.zone.now.to_date).map do |date|
        activity = activities_by_day.detect { |activity| 
        activity.created_at.to_date == date }
        activity && activity.noPerDay.to_i || 0
      end.inspect
    end
  end

  '''
  This method when called will return the difference between today and the day 
  the story was created in in days in all cases as mentioned in the above 
  methods:
  '''
  def get_story_start_date(creation_date, last_update, hidden)
    
    if (hidden && creation_date.to_date < 30.days.ago.to_date && 
          last_update.to_date >= 30.days.ago.to_date) ||
          (creation_date.to_date < 30.days.ago.to_date)
            date = Time.zone.now.to_date - 30.days.ago.to_date
    
    elsif hidden && creation_date.to_date < 30.days.ago.to_date && 
          last_update.to_date < 30.days.ago.to_date
            date = -1
            
    else
            date = Time.zone.now.to_date - creation_date.to_date
    end
  end

  #This method gets the number of total shares of a certain story given its id:
  def get_total_number_of_shares
    number = @total_shares
  end

  #This method gets the number of total likes of a certain story given its id:
  def get_total_number_of_likes
    number = @total_likes
  end

  #This method gets the number of total dislikes of a certain story given its id:
  def get_total_number_of_dislikes
    number = @total_dislikes
  end

  #This method gets the number of total flags of a certain story given its id:
  def get_total_number_of_flags
    number = @total_flags
  end
  
  def get_number_of_shares(user_id)
    number = @shares.where(:user_id => user_id).count
  end

  #This method gets the interest that the story belongs too:
  def get_interest_of_story(story_id)
    interestID = Story.find(story_id).interest_id
    interest = Interest.find(interestID).name
  end

  '''
  This method returns the number of distinct users who shared the story. 
  This means that if a user shares a story two times, this method will count 
  him once.
  '''
  def get_distinct_number_of_users_who_shared
    number = @shares.select("distinct(user_id)").count
  end
  
  '''
  This method returns the percentage of the likes to the total number 
  of likes and dislikes
  '''
  def get_width
    likes = get_total_number_of_likes
    dislikes =  get_total_number_of_dislikes
    total = likes + dislikes
    if total == 0
      width = 0
    else 
      width = likes*100/total
    end
  end

#the method of fetching rss feeds

require 'rss/1.0'
require 'rss/2.0'
require 'open-uri'


def fetch_rss(link)

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

  scategory = rss.channel.link

  #creating the stories and put them in the array
  while i < num do

    stitle = rss.items[i].title
    sdate = rss.items[i].date
    sdescription =  rss.items[i].description

    #check if the story already exists in the database
    count_of_stories_with_same_title = Story.where(:title => stitle).select("title").count


    #if it is a new story, it will enter automatically
    if count_of_stories_with_same_title == 0
      #getting the id of the interest 
      sinterest = Feed.where(:link => source).select("interest_id")

      storynow = Story.new(:title => stitle, :rank => 0, :category => "RSS",:media_link => "", :deleted => 		false, :hidden => false, :interest_id => sinterest)



      storynow.content = sdescription
      storynow.save

      sid = Story.find_by_title(stitle).id
      
      Log.create(loggingtype: 2,story_id: sid,message: "new story")

      listOfStories.append(storynow)
    else
      #if the story exists in the database it will enter the array without modifications

      storynow = Story.find_by_title(stitle)
      listOfStories.append(storynow)
    end
      i+=1

    if i == num || i ==50
      #if the array is full, return it
      return listOfStories
    end
end

#handling the errors of the links are not valid
rescue OpenURI::HTTPError
	return false
rescue SocketError
	return false
rescue RuntimeError
	return false
rescue NoMethodError
	p 'enter valid link not a website'
	return false
rescue Errno::ENOENT
	p 'enter valid link'
	 return
rescue RSS::NotWellFormedError
	p 'enter valid rss feed link form'
	return false
end

def check_rss(link)

  source = link
  content = "" # raw content of rss feed will be loaded here

  #parsing the url
  open(source) do |s| content = s.read end
  rss = RSS::Parser.parse(content, false)
  scategory = rss.channel.link
  return true

  rescue OpenURI::HTTPError
  return false
  rescue SocketError
  return false
  rescue RuntimeError
  return false
  rescue NoMethodError
  p 'enter valid link not a website'
  return false
  rescue Errno::ENOENT
  p 'enter valid link'
   return false
  rescue RSS::NotWellFormedError
  p 'enter valid rss feed link form'
  return false


  return false
end

module_function :fetch_rss
module_function :check_rss
end
