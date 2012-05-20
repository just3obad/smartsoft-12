require 'test_helper'

class UserTest < ActiveSupport::TestCase
  # test "the truth" do
  #   assert true
  # end

  setup :activate_authlogic

#Author : Shafei
  test "user get rank" do
	user = users(:one)
	story = stories(:one)
	comment = comments(:one)
	comment.user = user
	comment.story = story
	comment.save
	assert_equal(user.get_user_rank,7,"Action returns wrong number")
  end
  
    #Author : Shafei
  test "users get rank RED" do
  
	top_users = Array.new#
	top_users << users(:five)
	top_users << users(:four)
	top_users << users(:three)
	top_users << users(:two)
	top_users << users(:one)
	
	for i in 0...5
		assert_equal(top_users[i].id, User.get_users_ranking[i].id, "Ranking not correct")
	end
	
  end

  ##########Author: christinesed@gmail.com ############
  test "get no of users who signed in today one more user signed in today" do
    count=User.get_no_of_users_signed_in_today
    usr=User.new(:email=>"example@gmail.com", :password => "1234567", :password_confirmation => "1234567")
    assert usr.save
    log=UserLogIn.new
    log.user=usr
    assert log.save
    count2=User.get_no_of_users_signed_in_today
    assert_equal(count+1,count2)
  end

   ##########Author: christinesed@gmail.com ############
  test "get no of users who signed in today shouldn't add" do
    count=User.get_no_of_users_signed_in_today
     usr=User.new(:email=>"example@gmail.com", :password => "1234567", :password_confirmation => "1234567")
    assert usr.save
    log=UserLogIn.new
    log.user=usr
    log.created_at=2.days.ago
    assert log.save
    count2=User.get_no_of_users_signed_in_today
    assert_equal(count,count2)
  end

   ##########Author: christinesed@gmail.com ############
  test "get no of users who signed in today three more users signed in today" do
    count=User.get_no_of_users_signed_in_today
    usr=User.new(:email=>"example@gmail.com", :password => "1234567", :password_confirmation => "1234567")
    usr1=User.new(:email=>"example1@gmail.com", :password => "1234567", :password_confirmation => "1234567")
    usr2=User.new(:email=>"example2@gmail.com", :password => "1234567", :password_confirmation => "1234567")
    assert usr.save, "User can not be saved"
    assert usr1.save
    assert usr2.save
    log=UserLogIn.new
    log.user=usr
    assert log.save
    log=UserLogIn.new
    log.user=usr1
    assert log.save
    log=UserLogIn.new
    log.user=usr2
    assert log.save
    count2=User.get_no_of_users_signed_in_today
    assert_equal(count+3,count2)
  end

   ##########Author: christinesed@gmail.com ############
   test "get no of users who signed in today a user signed in more than once" do
   	count = User.get_no_of_users_signed_in_today
    usr=User.new(:email=>"example@gmail.com", :password => "1234567", :password_confirmation => "1234567")
    assert usr.save
    log=UserLogIn.new
    log.user=usr
    assert log.save
    log=UserLogIn.new
    log.user=usr
    assert log.save
    count2=User.get_no_of_users_signed_in_today
    assert_equal(count+1,count2)
  end
  

 #Author : Essam
  test "filter social networks RED" do
    user = User.new(:email=>"essamahmedhafez@gmail.com", :password => "12345678", :password_confirmation => "12345678")
    user.save
    user.twitter_account = twitter_accounts(:one)
    assert !user.twitter_account.nil?
    user_feed = user.twitter_account.get_feed()
    assert !user_feed.nil?
    user.facebook_account = facebook_accounts(:one)
    assert !user.facebook_account.nil?
    facebook_feed = user.facebook_account.get_feed
    assert !facebook_feed.nil?
  end

  #Author : Essam
  test "tumblr account RED" do
    new_tumblr = Tumblr::User.new('essamahmedhafez@gmail.com', '12345678') #Authentication
    blog = new_tumblr.tumblr["tumblelog"]["name"]
    tumblr = TumblrAccount.new
    Tumblr.blog = blog
    posts = Tumblr::Post.all #Get user posts
    assert !posts.nil?
  end

	#Author : Kareem
	test "create new flag" do
  	i = Interest.create(:name => "Smart")
	u = User.create(:email => "bwqer@a.com" , :password => "123456" , :password_confirmation => "123456")
 	s = Story.create(:title => "lol" , :interest_id => i.id)
  	assert_difference('Flag.count') do
	u.flag_story(s) 	
	end
	end
	
	#Author : Kareem
	test "try flagged user" do
	i = Interest.create(:name => "Smart1")
	u1 = User.create(:email => "bwqwer@a.com" , :password => "123456" , :password_confirmation => "123456")
	s = Story.create(:title => "hashas" , :interest_id => i.id)
 	Flag.create(:user_id => u1.id , :story_id =>s.id)
	assert_no_difference('Flag.count') do
	u1.flag_story(s)
	end
     end 

       

 

	#Author : Kareem 			
	test "new thumb story" do
	user = User.create(:email => "a@a.com" , :password => "123456" , :password_confirmation => "123456")
	i = Interest.create(:name => "rare")
	s = Story.create(:title => "ahaaaa" , :interest_id => i.id)
	assert_difference('Likedislike.count') do
	user.thumb_story(s,1)
	end
	end
	
	#Author : Kareem
	test "thumb story again" do
	user = User.create(:email => "b@a.com" , :password => "123456" , :password_confirmation => "123456")
	i = Interest.create(:name => "rares")
	s = Story.create(:title => "ahaaaa" , :interest_id => i.id)
	Likedislike.create(:user_id => user.id , :story_id => s.id ,:action => 1)
	assert_no_difference('Likedislike.count') do
	user.thumb_story(s,1)
	end
	end

	#Author : Kareem
	test "should return stories" do
	user = User.create(:email => "baa@a.com" , :password => "123456" , :password_confirmation => "123456")
	i= Interest.create(:name => "Art")
	s= Story.create(:title => "hahsd" , :interest_id => i.id)
	UserAddInterest.create(:user_id => user.id , :interest_id => i.id)
	stories = user.get_feed("null")
	assert_not_nil stories
	assert_equal stories[0].class.name , "Story" , "Return must be of class story"
	end


        #Author : Kareem
	test "should return user interests" do
	user = User.create(:email => "basa@a.com" , :password => "123456" , :password_confirmation => "123456")
	i = Interest.create(:name => "Ball")
     	i1 =  user.get_interests
	i2 = UserAddInterest.find(:all , :conditions => ["user_id = ?" , user.id ] , :select => "interest_id").map {|interest| interest.interest_id}.map {|id| 		   		Interest.find(id).name} 
 	assert_equal i1 , i2	, "User Interests must be returned"
	end

    
    #Author : Omar 
    #$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$

	test "add delete interest" do
	
		user = users(:ben)
		UserSession.create(user)
		int1 = Interest.create(:name => "interest 1")
		user.toggle_interests(int1.id)
		assert_equal( user.user_interests , [int1.id]  , "interest is not added")
		user.toggle_interests(int1.id)
		assert_equal( user.user_interests , []  , "interest is not deleted")
	
	end
	
	
	test "check if blocked" do
		user = users(:ben)
		UserSession.create(user)
		int1 = Interest.create(:name => "interest 1")
		int2 = Interest.create(:name => "interest 2")
		BlockInterest.create(:user_id => user.id , :interest_id => int1.id)
		assert_equal( user.is_blocked(int1.id) , 1 , "not returning 1 when interest is block")
		assert_equal( user.is_blocked(int2.id) , 2 , "not returning 2 when interest is not blocked")
	end



    	test "get all interests" do
			user = users(:ben)
			UserSession.create(user)
			int = Interest.create(:name => "interest")
			all_interests = Interest.all
			all = user.all_interests
			assert_equal( all_interests , all , "[not equal]" )
		  end


	test "get user interests" do 
		user = users(:ben)
		UserSession.create(user)
		int1 = Interest.create(:name => "interest 1")
		int2 = Interest.create(:name => "interest 2")
		int3 = Interest.create(:name => "interest 3")		
		UserAddInterest.create(:interest_id => int1.id , :user_id => user.id)
		UserAddInterest.create(:interest_id => int2.id , :user_id => user.id)
		uinterests = user.user_interests		
		uint =UserAddInterest.find(:all , :conditions => ["user_id = ?" , user.id ] , :select => "interest_id").map {|interest|interest.interest_id} 
		assert_equal( uint , uinterests , "[not equal]" )
	    end

   #$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$ 
    

	

    #Author: Rana
    test "user wants to block interest" do
      this_interest = Interest.create :name => "Sports", :description => "hey sporty"
      this_story= Story.new :title => "Story1", :interest_id => this_interest
      this_story.interest = this_interest
      this_story.save
      this_user = users(:ben)
      UserSession.create(this_user)
      assert_difference('BlockInterest.count',1) do
         this_user.block_interest1(this_story)
      end
      assert_difference('BlockInterest.count', 0) do
         this_user.block_interest1(this_story)
      end
    end

    #Author: Rana
   test "user wants to block story" do
      this_interest = Interest.create :name => "Sports", :description => "hey sporty"
      this_story= Story.new :title => "Story1", :interest_id => this_interest
      this_story.interest = this_interest
      this_story.save
      this_user = users(:ben)
      UserSession.create(this_user)
      assert_difference('BlockStory.count',1) do
         this_user.block_story1(this_story)
      end
      assert_difference('BlockStory.count', 0) do
         this_user.block_story1(this_story)
      end
    end

    #Author: Rana
   test "user wants to block friend feed" do
      this_user = users(:ben)
      UserSession.create(this_user)
      my_friend = users(:ahmed)
      this_user.invite my_friend
      my_friend.approve this_user
      assert_equal("#{my_friend.email} blocked successfully.", this_user.block_friends_feed1(my_friend))
      assert_equal('Friend already blocked.', this_user.block_friends_feed1(my_friend))
    end

    #Author: Rana
   test "user wants to unblock friend feed" do
      this_user = users(:ben)
      UserSession.create(this_user)
      my_friend = users(:ahmed)
      this_user.invite my_friend
      my_friend.approve this_user
      assert_equal("#{my_friend.email} unblocked successfully.", this_user.unblock_friends_feed1(my_friend))
    end

   #Author: Rana
   test "user wants to unblock story" do
      this_user = users(:ben)
      UserSession.create(this_user)
      this_interest = Interest.create :name => "Sports", :description => "hey sporty"
      this_story= Story.new :title => "Story1", :interest_id => this_interest
      this_story.interest = this_interest
      this_story.save
      this_user.block_story1(this_story)
      assert_equal("Story unblocked successfully.", this_user.unblock_story1(this_story))
    end

   #Author: Rana
   test "user wants to get blocked stories list" do
      this_user = users(:ben)
      UserSession.create(this_user)
      this_interest = Interest.create :name => "Sports", :description => "hey sporty"
      this_story= Story.new :title => "Story1", :interest_id => this_interest
      this_story.interest = this_interest
      this_story.save
      BlockStory.create!(:user_id => this_user, :story_id => this_story)
      blocked_stories = this_user.block_stories 
      assert_equal(blocked_stories, this_user.get_blocked_stories)
    end

  #Author : 3OBAD
  test "flickr account RED" do
    user = User.new
    user.email = '3obad@prince.com'
    user.flickr_account = flickr_account(:one)
    assert !user.flickr_account.nil?
    flickr_feed = user.flickr_account.get_feed()
    assert !flickr_feed.nil?
  end

	#Author: Kiro
	test "should not save a user without any parameters" do
		user = User.new
		assert !user.save, "saved a user without any parameters"
	end

	#Auther: Kiro
	test "should not save a user without a password" do
		user = User.new
		user.email = "email_1@test.com"
		assert !user.save, "saved a user without a password"
	end
	
	#Auther: Kiro
	test "shouldn't save a user with mismatching password confirmation" do
		user = User.new(email: "email_2@test.com",password: "123456",password_confirmation:"abcdet")
		assert !user.save, "saved a user with mismatching password confirmation"
	end

	#Auther: Kiro
	test "password should be 6 characters or more" do

		user = User.new(email: "pass0@test.com",password: "",password_confirmation:"")
		assert !user.save, "saved a user with passowrd of length 0"

		user = User.new(email: "pass1@test.com",password: "1",password_confirmation:"1")
		assert !user.save, "saved a user with passowrd of length 1"
		
		user = User.new(email: "pass5@test.com",password: "12345",password_confirmation:"12345")
		assert !user.save, "saved a user with passowrd of length 5"

		user = User.new(email: "pass6@test.com",password: "123456",password_confirmation:"123456")
		assert user.save, "didn't save a user with password length 6"

		user = User.new(email: "pass7@test.com",password: "1234567",password_confirmation:"1234567")
		assert user.save, "didn't save a user with password length 7"	
		
	end

	#Auther: Kiro
	test "should not save a user without a password confirmation" do
		user = User.new
		user.email = "email_1@test.com"
		user.password = "123456"
		assert !user.save, "saved a user without password confirmation"
	end

	#Author: Kiro
	test "Should not save a user with an existing email" do
	
		user = User.new(email: "only_one@test.com", password:"123456", password_confirmation:"123456")
		assert user.save, "didn't save the first user"
	
		user = User.new(email: "only_one@test.com", password:"123456", password_confirmation:"123456")
		assert !user.save, "saved a user with an existing email"

	end

	#Author: Kiro
	test "should not accept an email in a wrong format" do
	
		user = User.new(email: "correct_format@test.com", password:"123456", password_confirmation:"123456")
		assert user.save, "didn't accept an email in a correct format"

		user.email = ""
		assert !user.save, "saved a user with a wrong email --> empty string "

		user.email = "fail_format1"
		assert !user.save, "saved a user with a wrong email --> fail_format1"

		user.email = "fail_format2.com"
		assert !user.save, "saved a user with a wrong email --> fail_format2.com"

		user.email = "fail_format3@com"
		assert !user.save, "saved a user with a wrong email --> fail_format3@com"

		user.email = "@failformat4"
		assert !user.save, "saved a user with a wrong email --> @failformat4"

		user.email = "fail/format5@test.com"
		assert !user.save, "saved a user with a wrong email --> fail/format5@test.com"

		user.email = "+@failformat6.com"
		assert !user.save, "saved a user with a wrong email --> +@failformat6.com"

		user.email = "fail_format7@fail_format7.com"
		assert !user.save, "saved a user with a wrong email --> fail_format7@fail_format7.com"

		user.email = "correct_format@correctformat.com"
		assert user.save, "didn't save an email in the correct format"
		
	end

	test "extract friends" do 
		u1 = users(:one)
		u2 = users(:two)
		u1.invite u2
		u2.approve u1
		users = [users(:one), users(:two), users(:three), users(:four)]
		result = u1.extract_friends(users)
		assert result.include?(u2), 'User 2 should be in the result'
		assert !result.include?( users(:three)), 'User three should not be in the result'
	end 
  
	  #Author: Bassem
    test "deactivating users" do
    @usr=User.create!(:email=>"exampleuserpage@gmail.com", :password => "1234567", :password_confirmation => "1234567")
    @usr.deactivate_user
  	assert @usr.deactivated
end
 #Author: Bassem
    test "activating users" do
    @usr=User.create!(:email=>"exampleuserpage@gmail.com", :password => "1234567", :password_confirmation => "1234567")
    @usr.activate_user
  	assert !@usr.deactivated
end
end
