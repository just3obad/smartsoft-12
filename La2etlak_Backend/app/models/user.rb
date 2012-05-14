class User < ActiveRecord::Base

	# This is added to use the authlogic gem
	# Author: Kiro
	acts_as_authentic	

  # This is added to use the amistad friendship Gem
  # Author: Yahia
  include Amistad::FriendModel
  include UsersHelper
  # Those are our Consumer Token and Consumer secret that twitter
  # provided us. This correspons our entity to twitter. The Consumer
  # Secret should be saved in a safe place. 
  # Author: Yahia
  CONSUMER_TOKEN  = 'A8Fh0r4H5DJl3dCYLGbXyQ'
  CONSUMER_SECRET = '614KLHBIR3jyAyULABnxeJ7jUWz5jDG2rs7K1zY20Q' 

  # attr_accessible :title, :body
  attr_accessible :name, :first_name, :last_name, :date_of_birth, :email, :deactivated, :twitter_account, :twitter_request, :image, :password, :password_confirmation
  has_many :comments
  has_many :comment_up_downs
  # stat 0 pending
  # stat 1 requested
  # stat 2 accepted
  has_one :haccount
  has_one :twitter_account
	has_one :verification_code
  has_one :tumblr_account
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
  
  has_many :user_add_interests
  has_many :added_interests, :class_name => "Interest", :through => :user_add_interests

  has_many :user_add_interests
  has_many :added_interests, :class_name =>"Interest", :through => :user_add_interests 


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
 # Author: Menisy
  def get_one_friend_stories(friend_id) 
    user = User.find(friend_id)
    user.shared_stories
  end

  # gets the users shared stories
  # Author: Menisy
  def get_shared_stories
    self.shared_stories
  end
  
 # gets the shared stories of friends of a user
 # Author: Menisy
  def get_friends_stories()
    friends = self.friends
    shared_stories = Array.new
    friends.each do |friend|
      shared_stories << friend.shared_stories
    end
  end
# gets the users pending notifications
# only pending friendship requests for now
# Author: Menisy
def get_notifications
  if self.pending_invited_by.empty?
    nil
  else
    self.pending_invited_by
  end
end
#i seperated get_friends method from the recommend_story method so that no conflict happen when recieving and sending the json file and it return list of friends of the user
#Author: khaled.elbhaey
  def get_friends_email()

  @flistemail=Array.new
  @flist = self.get_Friend_List()

      (0..(@flist.length-1)).each do |i|
      @flistemail << (@flist[i].email)
      end  


      return @flistemail

 ''' @username = User.find(@userid).name
  @message = "#{@username}get_friends_email"

   Log.create!(loggingtype: 1,user_id_1: @userid,user_id_2: nil,admin_id:   nil,story_id: nil,interest_id: nil,message: @message )
  '''
  end
    
#Author: khaled.elbhaey
  def get_Friend_List()   

  @list=Array.new
  @user1=User.new( :name =>"khaled", :email => "khaledelbhaey@yahoo.com")
  @user2=User.new( :name =>"rana", :email => "rana@abc.com")
  @user3=User.new( :name =>"essam", :email => "essam@abc.com")
  @user4=User.new( :name =>"omar", :email => "omar@abc.com")
  @list <<@user1 <<@user2 <<@user3 <<@user4

  return @list
    
 end

#Author: khaled.elbhaey

  def has_account(mail)
  
  @email=mail
  if User.find_by_email(@email).nil?
      return false
  else
      return true
  end    

  end
  #dummy data to be returned until it created in sprint 2
#Author: khaled.elbhaey
  def extract_friends(listlike)

  @list=Array.new
  @user1=User.new( :name =>"khaled", :email => "khaledelbhaey@yahoo.com")
  @user2=User.new( :name =>"rana", :email => "rana@abc.com")
  @user3=User.new( :name =>"essam", :email => "essam@abc.com")
  @user4=User.new( :name =>"omar", :email => "omar@abc.com")
  @list <<@user1 <<@user2 <<@user3 <<@user4

  return @list

  end



# lets a user share a story given its id
  def share?(story_id)
    share = Share.find_by_user_id_and_story_id(self.id,story_id)
    if share.nil? then  # if he/she didn't share this story before then make him/her share it
      Share.create :user_id=>self.id,:story_id=>story_id
      return true   # shared successfully, return true
    else      # else, dont allow to share it
      return false    # and return false
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
  
   
  
  #This method returns the number of users who signed in today.
  ########## Author : christinesed@gmail.com #########
  def self.get_no_of_users_signed_in_today
    no_sing_ins=UserLogIn.where(:created_at=>
    Time.zone.now.beginning_of_day..Time.zone.now.end_of_day).
    uniq.pluck(:user_id).count
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
 ##########Author: Diab ############
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
##########Author: Diab ############
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
 ##########Author: Diab ############
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
 ##########Author: Diab ############
 def self.all_user_registered

 num = User.all.count
 
 end
 
#to get the number of registered users per day to use it in the graph
##########Author: Diab ############
 def self.get_registered_stat
 r = get_num_registered_day
 data = "[#{r}]"
 end

#to get the number of logged in users per day to use it in the graph
##########Author: Diab ############
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

=begin
 This return the consumer for twitter authentication
 
 Author: Yahia  
=end
  def self.twitter_consumer
  # The readkey and readsecret below are the values you get during registration
    OAuth::Consumer.new(CONSUMER_TOKEN, CONSUMER_SECRET,
                      { :site=>"http://twitter.com" })
  end

=begin
  This method adds a twitter account to the user. The twitter
  account corresponds to the request_token received from twitter
  in the authentication phase

  Author: Yahia
=end
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

=begin
  This method removes the twitter account of 
  returns true if the removal was successfull 
  Author: Yahia
=end   
  def remove_twitter_account
    if (self.twitter_account)
      self.twitter_account.destroy
    else 
      false 
    end 
  end 

=begin 
  Checks if the user has a twitter account
=end 
  def has_twitter_account?
    if self.twitter_account.nil?
      return false
    else
      return true
    end 
  end


 #Author Kareem
  def thumb_story(story,act)
   thumped = Likedislike.where(:story_id => story.id, :user_id => self.id)
        if thumped.empty? 
        Likedislike.create!(:user_id => self.id, :story_id => story.id , :action => act)
   puts "Thump"
    
    elsif (thumped[0].action == act) 
           puts "Already"
  
  elsif (thumped[0].action != act) 
    Likedislike.find(:first , :conditions => ["user_id = ? AND story_id = ?" ,self.id , story.id]).destroy
     Likedislike.create!(:user_id => self.id, :story_id => story.id , :action => act )  
    puts "Removed _thumbed"
  end

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
        unblocked_stories.append(Story. find(unblocked_story_id))
  end
  done_stories =   unblocked_stories #.map {|story|story.attributes.merge(:interest =>Interest.find(story.interest_id).name)}
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
          # done_stories =   filtered_stories.map {|story|story.attributes.merge(:interest =>Interest.find(story.interest_id).name)}
    end
  return done_stories

  end

#Author : Shafei
	def get_user_rank
		comments_by_user 	= Comment.where(:user_id => self.id)
		shares_by_user 		= Share.where(:user_id => self.id)
		dislikes_by_user	= Likedislike.where(:user_id => self.id, :action => -1)
		likes_by_user 		= Likedislike.where(:user_id => self.id, :action => 1)
		flags_by_user 		= Flag.where(:user_id => self.id)
		friends_of_user		= Friendship.where(:user_id => self.id)
		interests_of_user	= UserAddInterest.where(:user_id => self.id)
		sign_ins_of_user	= UserLogIn.where(:user_id => self.id)
		stories_blocked		= BlockStory.where(:user_id => self.id)
		sum 				= (comments_by_user.length * 2) + (shares_by_user.length * 3) + likes_by_user.length + 
							dislikes_by_user.length + flags_by_user.length + friends_of_user.length + interests_of_user.length
							+ (sign_ins_of_user.length * 2) - (stories_blocked.length * 10)
		return sum
	end

#Author : Shafei
	def get_users_ranking
	'''	all_users 		= User.*
		rank 			= Array.new
		max = 0
		id
		for i in 0...(all_users.length - 1)
			for j in 0...(all_users.length - 1)
				if all_users[j].get_user_rank >= max
					max = all_users[j].get_user_rank
					id = j+1
				end
			end
			all_users.delete_if{|k| == max}
			rank << all_users[id]
		end '''
	end

  # author = Gasser
  def reset_password
  end

 
  # Author: Omar
  # select user existing interests in our database
  def user_interests
    user_interests =  UserAddInterest.find(:all , :conditions => ["user_id = ?" , self.id ] , :select => "interest_id").map {|interest| interest.interest_id}
  end

  # Author: Omar
  #select all interests in the system
  def all_interests
    all_interests =  Interest.all()
  end
 
  #Author : Kareem
  def get_interests
 	  interests = UserAddInterest.find(:all , :conditions => ["user_id = ?" , self.id ] , :select => "interest_id").map {|interest| interest.interest_id}.map {|id| 		Interest.find(id).name}
	 return interests 
	end 

  #Author: Yahia
  def search_members(string)
  end

# This method generateVerificationCode? generates a verification code for the user
# and adds an entry to the verification codes table in the database.
# The verification code is 4 characters from (0-->9) and (a-->z).
# Returns true if it succeeded to create the entry, otherwise it returns false.
# Author: Kiro
  def generateVerificationCode?()
  		@verification_code = self.verification_code
      if @verification_code.nil? then
			entry = VerificationCode.new :code=>( (0..9).to_a + ('a'..'z').to_a).shuffle[0..3].join, :verified=>false
      self.verification_code = entry
      return true               
    else                        
      return false              
    end
  end 

# This method verifyAccount?(verCode) takes the verCode entered by the user to verify his account
# and it tries to match this code with the account's code.
# returns true and sets verified to true if the user entered the correct code,
# otherwise it returns false.
# Author: Kiro
  def verifyAccount?(verCode)
    @verEntry = self.verification_code
    if @verEntry.code == verCode then
      @verEntry.update_attributes(verified: true)
      return true
    else 
      return false
    end
  end

 
# This method resendCode? resets the verification Code of a specific account and updates the
# entry in the database.
# This method returns true if the accout wasnt verified yet, otherwise it returns false.
# Author: Kiro
  def resendCode?
    @varEntry = self.verification_code
    if @varEntry.verified
       return false
    else
    @varEntry.update_attributes(code: ( (0..9).to_a + ('a'..'z').to_a).shuffle[0..3].join)
       return true
    end
  end

#This method takes a story id as input and blocks the interest belonging to the story by inserting a row in BlockInterest table.
  #It also removes the row belonging to the interest and user from UserAddInterest table. 
  #If the interest is already blocked, it responds with a message that the interest is already blocked.
  #Author : Rana
   
  def block_interest1(this_story)
   this_user = self
   #this_story = Story.find_by_id(story_id)
   story_interest = this_story.interest
   check_interest =  BlockInterest.find_by_user_id_and_interest_id(this_user,story_interest)
   check_user_add_interest = UserAddInterest.find_by_user_id_and_interest_id(this_user,story_interest)
   if !check_user_add_interest.nil? #checks if the interest belongs to the user
      check_user_add_interest.delete
   end
   if check_interest.nil?
      blocked_interest = BlockInterest.new(:user_id => this_user, :interest_id => story_interest)
      blocked_interest.user = this_user
      blocked_interest.interest = story_interest 
      blocked_interest.save
      @text = "Interest blocked successfully"
   else 
      @text = "Interest already blocked"    
   end
   #for log file
   if self.name.nil?
      @username = self.email
   else
      @username = self.name
   end
   @interesttitle = story_interest.name
   @message = "#{@username} blocks interest with name: #{@interesttitle}"
   Log.create!(loggingtype: 3,user_id_1: self.id,user_id_2: nil, admin_id: nil, story_id: nil, interest_id: story_interest.id, message: @message)
  
   return @text #return the message in variable text
  end

  #This method takes a story id as input and blocks its story by inserting a row in BlockStory table. 
  #If the story is already blocked, it responds with a message that the story is already blocked.
  #Author : Rana

  def block_story1(this_story)
   this_user = self
   check_story = BlockStory.find_by_user_id_and_story_id(this_user,this_story)
   if check_story.nil?
      blocked_story = BlockStory.new(:user_id => this_user, :story_id => this_story)     
      blocked_story.story = this_story
      blocked_story.user = this_user
      blocked_story.save
      @text = "Story blocked successfully"
   else 
      @text = "Story already blocked"
   end
   #for log file
   if self.name.nil?
      @username = self.email
   else
      @username = self.name
   end
   #for log file
   @storytitle = this_story.title
   @message = "#{@username} blocks story with title: #{@storytitle}" 
   Log.create!(loggingtype: 2,user_id_1: self.id,user_id_2: nil, admin_id: nil, story_id: this_story.id, message: @message)

   return @text #return the message in variable text
  end

  #This method takes a friend as input and blocks him/her using the block method provided by the gem amistad.
  #It first checks if the friend is already blocked or not.
  #Author: Rana
  def block_friends_feed1(my_friend) 
    @this_user = self
    if(!(@this_user.blocked? my_friend))
        @this_user.block (my_friend)
        @text = "Friend blocked successfully."
    else
        @text = "Friend already blocked."
    end
    #for log file
     if self.name.nil?
      @username = self.email
     else
      @username = self.name
     end
     if my_friend.name.nil?
      @frname = my_friend.email
     else
      @frname = my_friend.name
     end
   @message = "#{@username} blocks friend named: #{@frname}" 
   Log.create!(loggingtype: 0,user_id_1: self.id,user_id_2: my_friend.id , admin_id: nil, story_id: nil, message: @message)

   return @text #return the message in variable text
  end

  # Author : Essam
   # issue 89
   # A method called to get stories from social accounts conected to the current user
   # returns a list of stories shuffled 
   # checks the four social networks we have in our system and sees whether 
   # the user connected to them or not, then
   # calls the get_feed method in each network
   def get_social_feed()
     user = User.find(self.id)
     social_stories = Array.new
     if(!user.twitter_account.nil?)
       social_stories.append(user.twitter_account.get_feed)
     end
     if(!user.tumblr_account.nil?)
       social_stories.append(user.tumblr_account.get_feed)
     end
     if(!user.facebook_account.nil?)
       social_stories.append(user.facebook_account.get_feed)
     end
     if(!user.flickr_account.nil?)
       social_stories.append(user.flickr_account.get_feed)
     end
     return social_stories.shuffle
   end
   
   
   
    # Author : Essam
    # issue 88
    # a method called when the user wants to see a specific feed
    # if the user wants to filter the view of any social account he is connected to 
    # he calls this method with the id of the social network then a list of stories of this account is returned
    # twitter => id = 1
    # facebook => id = 2
    # flickr => id = 3
    # tumblr => id = 4
  def filter_social_network (socialID)
    user = User.find(self.id)
    if(socialID == 1)
      return user.twitter_account.get_feed
    elsif (socialID == 2)
      return user.facebook_account.get_feed
    elsif (socialID == 3)
      return user.flickr_account.get_feed
    elsif (socialID == 4)
      return user.tumblr_account.get_feed
    else
      return []
    end
  end

	def resetPassword
	
		newpass = ((0..9).to_a + ('a'..'z').to_a + ('A'..'Z').to_a ).shuffle[0..5].join
		self.password = newpass
		self.reset_password!
		return newpass
	
	end

  #get recent Activity of user from the passed start date
  #Author: Christine
  def get_recent_activity(start_date)
    activities=Log.get_log_for_user(self.id,start_date.beginning_of_day)
  end

end
