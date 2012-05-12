class User < ActiveRecord::Base

  # This is added to use the amistad friendship Gem
  # Author: Yahia
  include Amistad::FriendModel

  # Those are our Consumer Token and Consumer secret that twitter
  # provided us. This correspons our entity to twitter. The Consumer
  # Secret should be saved in a safe place. 
  # Author: Yahia
  CONSUMER_TOKEN  = 'A8Fh0r4H5DJl3dCYLGbXyQ'
  CONSUMER_SECRET = '614KLHBIR3jyAyULABnxeJ7jUWz5jDG2rs7K1zY20Q' 

  # attr_accessible :title, :body
  attr_accessible :name, :first_name, :last_name, :date_of_birth, :email, :deactivated, 
  		:twitter_account, :twitter_request, :image
  has_many :friends, :through => :friends, :conditions => "status = '2'"
  has_many :requested_friends, :through => :friends, :source => :friend, :conditions => "stat = '1'"
  has_many :pending_friends, :through => :friends, :source => :friend, :conditions => "stat = '0'"
  has_many :friendships, :dependent => :destroy
  has_many :comments
  has_many :comment_up_downs
  # stat 0 pending
  # stat 1 requested
  # stat 2 accepted
  has_one :haccount
  has_one :twitter_account

  has_many :shares
  has_many :shared_stories, :class_name => "Story", :through => :shares
  has_many :likedislikes
  has_many :likedisliked_stories, :class_name => "Story", :through => :likedislikes
  has_many :flags
  has_many :flaged_stories, :class_name => "Story", :through => :flags
  has_many :block_interests
  has_many :blocked_interests, :class_name => "Interest", :through => :block_interests
  has_many :block_stories
  has_many :blocked_stories, :class_name => "Story", :through => :block_stories 


  email_regex = /\A(?:\w+\.)*\w+@(?:[a-z\d]+[.-])*[a-z\d]+\.[a-z\d]+\z/i
  validates :name, :length => { :maximum => 20 }
  validates :email, :presence => true,
  :format=> {:with => email_regex },

  :uniqueness => { :case_sensitive => false}
  validates :first_name, :length => { :maximum => 20 }
  validates :last_name,  :length => { :maximum => 20 }
  
 # gets the shared stories of one friend given his/her id
 # to get your own shared stories supply your own ID
 # as the param friend id
  def get_one_friend_stories(friend_id) 
    shares = Share.find_all_by_user_id(friend_id) # get all his/her shared stories
    stories = Array.new
    shares.each do |share|
      stories.append(Story.find(share.story_id)) # get the stories of these shares and append to stories
    end
    stories.uniq # remove tuplicates, if stories is equal nil this will return []
  end
 # gets the shared stories of friends of a user
  def get_friends_stories()
    friendsSent = Friend.find_all_by_sender_and_status(self.id,1) #find all friends who approved my request
    friendsRec = Friend.find_all_by_receiver_and_status(self.id,1) #find all friends whom I approved
    allFriends = friendsSent + friendsRec  # get all my friends by appending lists
    shares = Array.new # init shares array
    allFriends.each do |friend| # for all my friends
      shares+=Share.find_all_by_user_id(friend.id) # get their shares and append them to shares array
    end
    stories = Array.new # init stories array
    shares.each do |share| # for all my friends' shares
      stories.append Story.find(share.story_id) # get their stories and append them to stories array 
    end
    stories.uniq # remove duplicates, if stories is equal nil this will return []
  end
  
  #dummy data to be returned until it created in sprint 2

  def get_Friend_List()   

  @list=Array.new
  @user1=User.new( :name =>"khaled", :email => "khaled@abc.com")
  @user2=User.new( :name =>"rana", :email => "rana@abc.com")
  @user3=User.new( :name =>"essam", :email => "essam@abc.com")
  @user4=User.new( :name =>"omar", :email => "omar@abc.com")
  @list <<@user1 <<@user2 <<@user3 <<@user4

  return @list
    
 end


 #dummy data to be returned until it created in sprint 2

 def liked()  
  @list=Array.new
  @user1=User.new( :name =>"khaled", :email => "khaled@abc.com")
  @user2=User.new( :name =>"rana", :email => "rana@abc.com")
  @user3=User.new( :name =>"essam", :email => "essam@abc.com")
  @user4=User.new( :name =>"omar", :email => "omar@abc.com")
  @list <<@user1 <<@user2 <<@user3 <<@user4

  return @list

    
 end

 #dummy data to be returned until it created in sprint 2

  def disliked()  
  @list=Array.new
  @user1=User.new( :name =>"mina", :email => "mina@abc.com")
  @user2=User.new( :name =>"kareem", :email => "kareem@abc.com")
  @user3=User.new( :name =>"7mada", :email => "7mada@abc.com")
  @user4=User.new( :name =>"yahia", :email => "yahia@abc.com")
  @list <<@user1 <<@user2 <<@user3 <<@user4

  return @list
 end



# lets a user share a story given its id
  def share?(story_id)
    share = Share.find_by_user_id_and_story_id(self.id,story_id)
    if share.nil? then	# if he/she didn't share this story before then make him/her share it
      Share.create :user_id=>self.id,:story_id=>story_id
      return true		# shared successfully, return true
    else 			# else, dont allow to share it
      return false		# and return false
    end
  end 
 
 # this methods generates a verification code for the user and adds an entry to Verification_Code
  def generateVerificationCode?()
  @verification_code = VerificationCode.find_by_user_id(self.id)
      if @verification_code.nil? then
      VerificationCode.create :code=>( (0..9).to_a + ('a'..'z').to_a).shuffle[0..3].join,:user_id=>self.id, :verified=>false
      return true		
    else 			
      return false		
    end
  end 

  def verifyAccount?(verCode)
    @verEntry = VerificationCode.find_by_user_id(self.id)
    if @verEntry.code == verCode then
      @verEntry.update_attributes(verified: true)
      return true
    else 
      return false
    end
  end

 '''This method to get the number of users who registered per day
 first we check if there are users in the DB else we return an empty array
 
 case 1 if the creation day was within last 30 days:
 get all the users who registered within first user creation until the current 
 date and group by the date of creation
 then was to get the count of the users who registered per day and 0 if no user did

 case 2 if the creation date was before 30 days ago:
 get all the users who registered within 30 days ago until the current date and 
 group by the date of creation
 then get the count of the users who registered per day and 0 if no user did'''

 def self.get_num_registered_day
 
 first_user = User.first

 if first_user.nil?
 
  registered_per_day = []

 else

  first_user_create_date = User.first.created_at
 
  if first_user_create_date >= 30.days.ago.to_date

   registered_per_day = User.where(:created_at => first_user_create_date.
   beginning_of_day..Time.zone.now.end_of_day).group("date(created_at)").
   select("created_at , count(id) as regs_day") 

   (first_user_create_date.to_date..Time.zone.now.to_date).map do |date|
   reg = registered_per_day.detect { |reg| reg.created_at.to_date == date}
   reg && reg.regs_day.to_i || 0
     end.inspect

  else
 
   registered_per_day = User.where(:created_at => 30.days.ago.beginning_of_day..
   Time.zone.now.end_of_day).group("date(created_at)")
   .select("created_at , count(id) as regs_day") 

   (30.days.ago.to_date..Time.zone.now.to_date).map do |date|
   reg = registered_per_day.detect { |reg| reg.created_at.to_date == date}
   reg && reg.regs_day.to_i || 0
    end.inspect
 
   end
  end
 end


 '''This method to get the number of users who registered per day
 first we check if there are users in the DB else we return an empty array
 
 case 1 if the first login was within last 30 days
 get all the users who logged in within first user login until the current date 
 and group by the date of creation
 then get the count of the users who registered per day and 0 if no user did

 case 2 if the first user login was before 30 days ago
 get all the users who logged in within 30 days ago until the current date and 
 group by the date of creation
 then get the count of the users who registered per day and 0 if no user did
 '''
 def self.get_num_logged_in_day

 first_user = UserLogIn.first

 if first_user.nil?

  logged_per_day = []

 else

 first_user_log_date = User.first.created_at 
 
 if first_user_log_date >= 30.days.ago.to_date

  logged_per_day = UserLogIn.where(:created_at => first_user_log_date
  .beginning_of_day..Time.zone.now.end_of_day).group("date(created_at)")
  .select("created_at , count(distinct(user_id)) as logs_day") 

  (first_user_log_date.to_date..Time.zone.now.to_date).map do |date|
  log = logged_per_day.detect { |log| log.created_at.to_date == date}
  log && log.logs_day.to_i || 0
   end.inspect

 else

  logged_per_day = UserLogIn.where(:created_at => 30.days.ago.beginning_of_date..
  Time.zone.now.end_of_day).group("date(created_at)")
  .select("created_at , count(distinct(user_id)) as logs_day")
  
  (30.days.ago.to_date..Time.zone.now.to_date).map do |date|
  log = logged_per_day.detect { |log| log.created_at.to_date == date}
  log && log.logs_day.to_i || 0
     end.inspect

   end
 end
end

 '''to get the start day of the statistics graph

 case 1 if the creation day was within last 30 days:
 set the start date of the statistics to the creation of the first User
 
 case 2 if the creation date was before 30 days ago:
 set it to 30 days ago
 ''' 
 def self.get_all_users_start_date

  first_user = User.first

 if first_user.nil?

   date=-1
 else

 first_user_create_date = User.first.created_at.to_date 
 
 if first_user_create_date >= 30.days.ago.to_date

 date = Time.zone.now.to_date - first_user_create_date 
 
 else

 date = Time.zone.now.to_date - 30.days.ago.to_date
 
   end
  end
 end
 
 #to get num of users registered in the system
 def self.all_user_registered

 num = User.all.count
 
 end
#to get the number of registered users per day to use it in the graph
 def self.get_registered_stat
 r = get_num_registered_day
 data = "[#{r}]"
 end
#to get the number of logged in users per day to use it in the graph
 def self.get_logged_stat
 l = get_num_logged_in_day_h
 data = "[#{l}]"
 end
 #to get the number of registered and logged in users per day to use it in the graph
 def self.get_all_users_stat
 reg = get_num_registered_day
 log = get_num_logged_in_day
 if reg.empty?
 data = []
 else 
 data = "[#{reg},#{log}]"
 end
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

 def get_user_start_date(user_id)
   user_reg_date = User.find(user_id).created_at_before_type_cast.to_date
   last_updated = User.find(user_id).updated_at_before_type_cast.to_date
   deactivated = User.find(user_id).deactivated_before_type_cast
   if deactivated && user_reg_date > 30.days.ago.to_date && last_updated > 30.days.ago.to_date
     date = Time.zone.now.to_date - user_reg_date
   elsif deactivated && user_reg_date <= 30.days.ago.to_date && last_updated > 30.days.ago.to_date
     date = Time.zone.now.to_date - 30.days.ago.to_date
   elsif deactivated && user_reg_date <= 30.days.ago.to_date && last_updated <= 30.days.ago.to_date
     date = -1
   elsif user_reg_date <= 30.days.ago.to_date
     date = Time.zone.now.to_date - 30.days.ago.to_date
   else
     date = Time.zone.now.to_date - user_reg_date
   end
 end

  # This return the consumer for twitter authentication
  # Author: Yahia
  def self.twitter_consumer
  # The readkey and readsecret below are the values you get during registration
    OAuth::Consumer.new(CONSUMER_TOKEN, CONSUMER_SECRET,
                      { :site=>"http://twitter.com" })
  end

  # Author: Yahia
  def create_twitter_account(request_token)
    access_token = request_token.get_access_token
    old_account = self.twitter_account(true)
    if (old_account)
      old_account.destroy
    end
    self.twitter_account(true) #Reload cache     
    t_account = TwitterAccount.new
    t_account.auth_token = access_token.token
    t_account.auth_secret = access_token.secret
    t_account.user = self
    t_account.save
    return t_account
  end 

 #Author Kareem
 	def thumb_story(story,act)
	f = "nil"
	 thumped = Likedislike.where(:story_id => story.id, :user_id => self.id)
        if thumped.empty? 
        Likedislike.create!(:user_id => self.id, :story_id => story.id , :action => act)
	 f = "thumbed"
		
  	elsif (thumped[0].action == act) 
      	   f="Already"
	
	elsif (thumped[0].action != act) 
		Likedislike.find(:first , :conditions => ["user_id = ? AND story_id = ?" ,self.id , story.id]).destroy
		 Likedislike.create!(:user_id => self.id, :story_id => story.id , :action => act )	
		f = "Removed _thumbed"
	end
return f
end
#Author :Kareem
def flag_story(story)
	thumped = Flag.where(:story_id => story.id, :user_id => self.id)
     if thumped.empty?
        Flag.create!(:user_id =>  self.id, :story_id => story.id)
	return true
     end
	return false
end
#Author : Kareem
	def get_feed(int_name)
	  user_interests = UserAddInterest.find(:all , :conditions => ["user_id =?" , self.id ] , :select => "interest_id").map {|interest|interest.interest_id}
 	  blocked_interests =  BlockInterest.select {|entry| self.id==entry.user_id }.map{|entry| entry.interest_id }
	  unblocked_stories = Array.new
 	  unblocked_interests = user_interests - blocked_interests
	  stories_list = Array.new
 	  unblocked_interests.each do |unblocked_interest|
	  stories_list +=  Interest.find(unblocked_interest).get_stories(10)
	 end
 	
 	stories_list = stories_list.sort_by &:created_at
 	stories_ids = stories_list.map {|story| story.id}
 	blocked_stories_ids = BlockStory.select { |entry| self.id==entry.user_id }.map  { |entry| entry.story_id }
 	unblocked_stories_ids = stories_ids - blocked_stories_ids
 	unblocked_stories_ids.each do |unblocked_story_id|
       	unblocked_stories.append(Story.find(unblocked_story_id))
 	end
	done_stories =   unblocked_stories.map {|story|story.attributes.merge(:interest =>Interest.find(story.interest_id).name)}
	if(int_name != "null")
		filtered_stories = Array.new
        	filtered_stories_ids = Array.new
		unblocked_stories_ids.each do |unblocked_story_id|
		interest_name = Interest.find(Story.find(unblocked_story_id).interest_id).name
		if(interest_name == int_name)
			filtered_stories_ids.append(unblocked_story_id)
			end
		end
		filtered_stories = Array.new
		filtered_stories_ids.each do |filtered_story_id|
       		filtered_stories.append(Story.find(filtered_story_id))
		end
           done_stories = filtered_stories
           done_stories =   filtered_stories.map {|story|story.attributes.merge(:interest =>Interest.find(story.interest_id).name)}
		end
	return done_stories

	end

 #-------------------------#
end
