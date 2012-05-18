class User < ActiveRecord::Base

	# This is added to use the authlogic gem
	# Author: Kiro
	acts_as_authentic	

=begin

   This is added to use the amistad friendship Gem
   Author: Yahia
=end
  include Amistad::FriendModel
  include UsersHelper

  # attr_accessible :title, :body
  attr_accessible :name, :first_name, :last_name, :date_of_birth, :email, :deactivated, :twitter_account, :twitter_request, :image, :password, :password_confirmation
  has_many :comments
  has_many :comment_up_downs
  # stat 0 pending
  # stat 1 requested
  # stat 2 accepted
  has_one :haccount
  has_one :facebook_account
  has_one :twitter_account
	has_one :verification_code
  has_one :tumblr_account
  has_one :flickr_account
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
  has_many :user_log_ins
  has_and_belongs_to_many :user_add_interests
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
    return shared_stories.flatten
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
#the get_friends is a method that return list of all friends email of the user
#Author: khaled.elbhaey
  def get_friends_email()

  @flistemail=Array.new
  @flist = self.friends

      (0..(@flist.length-1)).each do |i|
      @flistemail << (@flist[i].email)
      end  


      return @flistemail

 ''' @username = User.find(@userid).name
  @message = "#{@username}get_friends_email"

   Log.create!(loggingtype: 1,user_id_1: @userid,user_id_2: nil,admin_id:   nil,story_id: nil,interest_id: nil,message: @message )
  '''
  end
   

#has_account checks if the user with the (mail) is in our database or not
#Author: khaled.elbhaey
  def has_account(mail)
  
  @email=mail
  if User.find_by_email(@email).nil?
      return false
  else
      return true
  end    

  end

=begin 
  This method takes a list of users and return a list users that are friends
  with the 'self' 
  Author: Yahia
=end
  def extract_friends(users)
    return self.friends & users
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

 #this Action takes as parametars a story and action and checks if the user had thumbed this story or not.
 # if the user didn't thumb a new record will be added in the Likedislike table with story_id , user_id and action
 # if thumbed before and tries to make the same action again .. nothing will happen
 # if thumbed before and tries to make a diffrent action .. the old record will be destroyed and a new record with the new action will be added
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

#this Action takes as a parametar a story and it checks if the current User flagged it before or not .. if not a new Record will be added in the Flags table with current user_id , current_story id else if he already flagged it nothing will happen.
#Author :Kareem
def flag_story(story)
  thumped = Flag.where(:story_id => story.id, :user_id => self.id)
     if thumped.empty?
        Flag.create!(:user_id =>  self.id, :story_id => story.id)
	#Admin_Settings.update_story_if_flagged
  return true
     end
  return false
end


#this Action returns a list of stories accordind to the current user interests .. and it also checks if theses stories are blocked According to an Interest or its a Blocked story or not before it returns the List of stories.
#the method also takes as input Interest name if it's not "null" the method will return the stories of this interest only.
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

#Author : Kareem
#this Method Takes a list of stories and returns the List filtered from the Blocked one's
  def  get_unblocked_stories(stories)
	unblocked_stories = Array.new
  	stories = stories.sort_by &:created_at
  	stories_ids = stories.map {|story| story.id}
  	blocked_stories_ids = BlockStory.select { |entry| self.id==entry.user_id }.map  { |entry| entry.story_id }
	blocked_interests =  BlockInterest.select {|entry| self.id==entry.user_id }.map{|entry| entry.interest_id }
  	unblocked_stories_ids = stories_ids - blocked_stories_ids
  	unblocked_stories_ids.each do |unblocked_story_id|
	story = Story.find(unblocked_story_id)
	if !(blocked_interests.include?(story.interest_id))
        unblocked_stories.append(story)
	end
  end
 return unblocked_stories
end
	
#Author : Shafei
# This action returns the rank of one user
	def get_user_rank
		rank = (self.shares.count * 3) + self.comments.count + self.likedislikes.where(action: 1).count + self.flags.count + self.likedislikes.where(action: -1).count + self.added_interests.count + self.friends.count + self.user_log_ins.count
		return rank
	end

#Author : Shafei
# This action returns a list of all users in the database sorted according to their rank
	def self.get_users_ranking
		all_users = Array.new
		top_users = Array.new
		final_users = Array.new
		User.all.each do |user|
	  	all_users << {:rank => user.get_user_rank, :theuser => user}
		end
		(all_users.sort_by {|element| element[:rank]}).each do |hsh|
		  final_users << hsh[:theuser]
		end
		top_users =  final_users.reverse
		if(top_users.empty? == true)
			return []
		else
		return top_users
		end
	end


  
 #Author : Omar 
 # check if user toggle new interests the methods adds it to his interests if toggled old interest it deletes it from his interests
 def toggle_interests(id)

	 if self.name.nil?
	      username = self.email.split('@')[0]
          else
      		username = self.name
         end
   		interest_name = Interest.find(id).name
  
   if(UserAddInterest.find_by_interest_id(id) == nil)
 	UserAddInterest.create(:user_id => self.id , :interest_id => id)
 		if(BlockInterest.find_by_interest_id(id) != nil)
 			BlockInterest.find_by_interest_id(id).destroy
 		end
 		message = "#{username} added interest : #{interest_name}"
   		Log.create!(loggingtype: 3,user_id_1: self.id,user_id_2: nil, admin_id: nil, story_id: nil, 			interest_id: id, message: message)
 	else 
 		UserAddInterest.find_by_interest_id(id).destroy
 		message = "#{username} deleted interest : #{interest_name}"
   		Log.create!(loggingtype: 3,user_id_1: self.id,user_id_2: nil, admin_id: nil, story_id: nil, 			interest_id: id, message: message)
 	end
 end
 
  # Author: Omar
  # select user existing interests in our database
  def user_interests
    user_interests =  UserAddInterest.find(:all , :conditions => ["user_id = ?" , self.id ] , :select => "interest_id").map {|interest| interest.interest_id}
  end

# Author: Omar
#check if user has this interest as blocked or not if blocked return 1 else return 0
 def is_blocked(interest)
 	if(BlockInterest.find(:all , :conditions => ["user_id = ? AND interest_id = ? ", self.id, interest]).length > 0)
 	return 1
 	  else 
 	    return 2
 	 end
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
   story_interest = this_story.interest
   check_interest =  BlockInterest.find_by_user_id_and_interest_id(this_user,story_interest)
   check_user_add_interest = UserAddInterest.find_by_user_id_and_interest_id(this_user,story_interest)
   if (!(check_user_add_interest.nil?)) #checks if the interest belongs to the user
      check_user_add_interest.delete
   end
   if check_interest.nil?
      blocked_interest = BlockInterest.new(:user_id => this_user, :interest_id => story_interest)
      blocked_interest.user = this_user
      blocked_interest.interest = story_interest 
      blocked_interest.save
      @text = "Interest blocked successfully"
   #for log file in case of success of block
   if self.name.nil?
      @username = self.email.split('@')[0]
   else
      @username = self.name
   end
   @interesttitle = story_interest.name
   @message = "#{@username} blocks interest with name: #{@interesttitle}"
   Log.create!(loggingtype: 3,user_id_1: self.id,user_id_2: nil, admin_id: nil, story_id: nil, interest_id: story_interest.id, message: @message)
   else 
      @text = "Interest already blocked"    
   end
   
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
   #for log file in case of success of block
   if self.name.nil?
      @username = self.email.split('@')[0]
   else
      @username = self.name
   end
   @storytitle = this_story.title
   @message = "#{@username} blocks story with title: #{@storytitle}" 
   Log.create!(loggingtype: 2,user_id_1: self.id,user_id_2: nil, admin_id: nil, story_id: this_story.id, interest_id: nil, message: @message)

   else 
      @text = "Story already blocked"
   end

   return @text #return the message in variable text
  end

  #This method takes a story id as input and unblocks its story by deleting its row in BlockStory table. 
  #Author : Rana

  def unblock_story1(this_story)
   this_user = self
   check_story = BlockStory.find_by_user_id_and_story_id(this_user,this_story)
   if !(check_story.nil?)
      check_story.delete
      @text = "Story unblocked successfully."
   #for log file in case of success of unblock
   if self.name.nil?
      @username = self.email.split('@')[0]
   else
      @username = self.name
   end
   @storytitle = this_story.title
   @message = "#{@username} unblocks story with title: #{@storytitle}" 
   Log.create!(loggingtype: 2,user_id_1: self.id,user_id_2: nil, admin_id: nil, story_id: check_story.id, interest_id: nil, message: @message)
   end
   return @text #return the message in variable text
  end

  #This method gets the unblocked stories of the user from the BlockStory table. 
  #Author : Rana

  def get_blocked_stories
   this_user = self
   blocked_stories = Array.new
   blocked_stories_id = this_user.block_stories
   blocked_stories_id.each do |blocked_story|
      blocked_stories << Story.find_by_id(blocked_story.story_id)
    end
   return blocked_stories
  end

  #This method takes a friend as input and blocks him/her using the block method provided by the gem amistad.
  #It first checks if the friend is already blocked or not.
  #Author: Rana
  def block_friends_feed1(my_friend)
    @this_user = self
    if(!(@this_user.blocked? my_friend))
        @this_user.block (my_friend)
        @text = "#{my_friend.email} blocked successfully."
    #for log file in case of success of block
     if self.name.nil?
      @username = self.email.split('@')[0]
     else
      @username = self.name
     end
     if my_friend.name.nil?
      @frname = my_friend.email.split('@')[0]
     else
      @frname = my_friend.name
     end
   @message = "#{@username} blocks friend named: #{@frname}" 
   Log.create!(loggingtype: 0,user_id_1: self.id,user_id_2: my_friend.id , admin_id: nil, story_id: nil, interest_id: nil, message: @message)
    else
        @text = "Friend already blocked."
    end

   return @text #return the message in variable text
  end

  # This method takes a friend as input and unblocks him/her using the unblock method provided by the gem amistad.
  # Author: Rana
  def unblock_friends_feed1(my_friend)
    self.unblock my_friend
    @text = "#{my_friend.email} unblocked successfully."
    #for log file
     if self.name.nil?
      @username = self.email.split('@')[0]
     else
      @username = self.name
     end
     if my_friend.name.nil?
      @frname = my_friend.email.split('@')[0]
     else
      @frname = my_friend.name
     end
   @message = "#{@username} unblocks friend named: #{@frname}" 
   Log.create!(loggingtype: 0,user_id_1: self.id,user_id_2: my_friend.id , admin_id: nil, story_id: nil, interest_id: nil, message: @message)

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
       social_stories = social_stories + (user.twitter_account.get_feed)
     end
     if(!user.tumblr_account.nil?)
       social_stories = social_stories + (user.tumblr_account.get_feed)
     end
     if(!user.facebook_account.nil?)
       social_stories = social_stories + (user.facebook_account.get_feed)
     end
     if(!user.flickr_account.nil?)
       social_stories = social_stories + (user.flickr_account.get_feed)
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
		self.update_attributes(password: newpass, password_confirmation: newpass)
		self.save
		return newpass
	
	end

  #get recent Activity of user from the passed start date
  #Author: Christine
  def get_recent_activity(start_date)
    activities=Log.get_log_for_user(self.id,start_date.beginning_of_day)
  end

  def deactivate_user(id)
    @user =User.find(id)
    @user.deactivated = true
    @user.save
  end
  def activate_user(id)
    @user =User.find(id)
    @user.deactivated = false
    @user.save
  end

end
