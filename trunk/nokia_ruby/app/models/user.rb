class User < ActiveRecord::Base
  # attr_accessible :title, :body
  attr_accessible :name, :first_name, :last_name, :date_of_birth, :email, :deactivated, 
  		:twitter_account, :twitter_request, :image
has_many :friends, :through => :friends, :conditions => "status = '2'"
has_many :requested_friends, :through => :friends, :source => :friend, :conditions => "stat = '1'"
has_many :pending_friends, :through => :friends, :source => :friend, :conditions => "stat = '0'"
has_many :friendships, :dependent => :destroy
  # stat 0 pending
  # stat 1 requested
  # stat 2 accepted
  has_one :haccount
  has_one :twitter_account
  has_one :twitter_request #If he requested another one, the old will be deleted

  email_regex = /\A(?:\w+\.)*\w+@(?:[a-z\d]+[.-])*[a-z\d]+\.[a-z\d]+\z/i
  validates :name, :length => { :maximum => 20 }
  validates :email, :presence => true,
  :format=> {:with => email_regex },

  :uniqueness => { :case_sensitive => false}
  validates :first_name, :length => { :maximum => 20 }
  validates :last_name,  :length => { :maximum => 20 }
  

 # gets the shared stories of one friend given his/her id
  def get_one_friend_stories(friend_id)
    shares = Share.find_all_by_user_id(friend_id)
    stories = Array.new
    shares.each do |share|
      stories.append(Story.find(share.story_id))
    end
    stories.uniq
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
  @user1=User.new( :name =>"khaled", :email => "khaled@abc.com")
  @user2=User.new( :name =>"rana", :email => "rana@abc.com")
  @user3=User.new( :name =>"essam", :email => "essam@abc.com")
  @user4=User.new( :name =>"omar", :email => "omar@abc.com")
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

end
