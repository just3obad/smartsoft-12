require 'test_helper'

class UserTest < ActiveSupport::TestCase
  # test "the truth" do
  #   assert true
  # end

  test "A user should be able to seach for member" do 
  	u1 = users(:one)
  	u2 = users(:two)
	list = u1.search_member(u2.first_name)
	assert !list.nil? 
	assert_equal list.first.class.name, 'User', 'A search result should return a list of users'
	assert_equal list.first.first_name, u2.first_name, 'A list should include the user which has the first name query string'
	list = u1.search_member(u2.last_name)
	assert_equal list.first.last_name, u2.email, 'A list should include the user which has the last name query string'
	list = u1.search_member(u2.email)
	assert_equal list.first.email, u2.email, 'A list should include the user which has the email query string'
  end 

#Author : Shafei
  test "user get rank RED" do
	user = User.new
	user.save
	story = Story.new
	story.save
	comment = Comment.new
	comment.user = user
	comment.story = story
	assert_equal(user.get_user_rank(),2,"Action returns wrong number")
  end
  
    #Author : Shafei
  test "users get rank Red" do
	top_users = Array.new#
	comments = Array.new#
	story = Story.new
	story.save
	i = 1
	top_users.each do |user|
		user = User.new
		user.save
		top_users >> user
		for j in 1...i
			comment = Comment.new
			comment.user = user
			comment.story = story
			comment.save
			comments >> comment
		end
		i = i + 1
	end
	user = User.new
	users = user.get_users_ranking
	assert_nil(users.first,"")
	users.each do |user|
		assert_equal(user.id, top_users[i].id, "Ranking not correct")
		i = i - 1
	end
  end

  ##########Author: christinesed@gmail.com ############
  test "get no of users who signed in today one more user signed in today RED" do
    count=User.get_no_of_users_signed_in_today
    usr=User.new(:email=>"example@gmail.com")
    assert usr.save
    log=UserLogIn.new
    log.user=usr
    assert log.save
    count2=User.get_no_of_users_signed_in_today
    assert_equal(count+1,count2)
  end

   ##########Author: christinesed@gmail.com ############
  test "get no of users who signed in today shouldn't add RED" do
    count=User.get_no_of_users_signed_in_today
    usr=User.new(:email=>"example@gmail.com")
    assert usr.save
    log=UserLogIn.new
    log.user=usr
    log.created_at=2.days.ago
    assert log.save
    count2=User.get_no_of_users_signed_in_today
    assert_equal(count,count2)
  end

   ##########Author: christinesed@gmail.com ############
  test "get no of users who signed in today three more users signed in today RED" do
    count=User.get_no_of_users_signed_in_today
    usr=User.new(:email=>"example@gmail.com")
    usr1=User.new(:email=>"example1@gmail.com")
    usr2=User.new(:email=>"example2@gmail.com")
    assert usr.save
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
   test "get no of users who signed in today a user signed in more than once RED" do
    count=User.get_no_of_users_signed_in_today
    usr=User.new(:email=>"example@gmail.com")
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
    user = User.new
    user.email = 'essam@hafez.com'
    user.twitter_account = twitter_accounts(:one)
    assert !user.twitter_account.nil?
    user_feed = user.twitter_account.get_feed()
    assert !user_feed.nil?
  end

  #Author : Essam
  test "tumblr account RED" do
    user = User.new
    user.email = 'essam@hafez.com'
    user.tumblr_account = tumblr_accounts(:one)
    assert !user.tumblr_account.nil?
    tumblr_feed = user.tumblr_account.get_feed()
    assert !tumblr_feed.nil?
  end

	#Author : Kareem
	test "create new flag" do
  	i = Interest.create(:name => "Smart")
	u = User.create(:email=> "7amo2a@guc.com")
 	s = Story.create(:title => "lol" , :interest_id => i.id)
  	assert_difference('Flag.count') do
	u.flag_story(s) 	
	end
	end
	
	#Author : Kareem
	test "try flagged user" do
	i = Interest.create(:name => "Smart1")
	u1 = User.create(:name => "7amada" , :email => "b@abc.com")
	s = Story.create(:title => "hashas" , :interest_id => i.id)
 	Flag.create(:user_id => u1.id , :story_id =>s.id)
	assert_no_difference('Flag.count') do
	u1.flag_story(s)
	end
     end 

       

 

	#Author : Kareem 			
	test "new thumb story" do
	user = User.create(:name => "Apolo" , :email => "a@a.com")
	i = Interest.create(:name => "rare")
	s = Story.create(:title => "ahaaaa" , :interest_id => i.id)
	assert_difference('Likedislike.count') do
	user.thumb_story(s,1)
	end
	end
	
	#Author : Kareem
	test "thumb story again" do
	user = User.create(:name => "Apolo" , :email => "b@a.com")
	i = Interest.create(:name => "rares")
	s = Story.create(:title => "ahaaaa" , :interest_id => i.id)
	Likedislike.create(:user_id => user.id , :story_id => s.id ,:action => 1)
	assert_no_difference('Likedislike.count') do
	user.thumb_story(s,1)
	end
	end

	#Author : Kareem
	test "should return stories" do
	user = User.create(:name => "7amdy" , :email => "7amdy@a.com")
	i= Interest.create(:name => "Art")
	s= Story.create(:title => "hahsd" , :interest_id => i.id)
	UserAddInterest.create(:user_id => user.id , :interest_id => i.id)
	stories = user.get_feed("null")
	assert_not_nil stories
	assert_equal stories[0].class.name , "Story"
	end


        #Author : Kareem
	test "should return user interests" do
	user = User.create(:name => "lol" , :email => "lol@a.com")
	i = Interest.create(:name => "Ball")
     	i1 =  user.get_interests
	i2 = UserAddInterest.find(:all , :conditions => ["user_id = ?" , user.id ] , :select => "interest_id").map {|interest| interest.interest_id}.map {|id| 		   		Interest.find(id).name} 
 	assert_equal i1 , i2	
	end

    
    #Author : Omar 
    #$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$
    	test "get all interests" do
			user = User.new
			user.email = "abc@abc.com"
			user.password = "123456"
			user.password_confirmation = "123456"
			user.save	
			int = Interest.create(:name => "interest")
			all_interests = Interest.all
			all = user.all_interests
			assert_equal( all_interests , all , "[not equal]" )
		  end


	test "get user interests" do 
		user = User.new
		user.email = "abc@abc.com"
		user.password = "123456"
		user.password_confirmation = "123456"
		user.save
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
      this_user = User.create :name => "amr", :email => "amr@abc.com", :password => "123456", :password_confirmation => "123456"
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
      this_user = User.create :name => "amr", :email => "amr@abc.com", :password =>"123456", :password_confirmation => "123456"
      assert_difference('BlockStory.count',1) do
         this_user.block_story1(this_story)
      end
      assert_difference('BlockStory.count', 0) do
         this_user.block_story1(this_story)
      end
    end

    #Author: Rana
   test "user wants to block friend feed RED" do
      this_user = User.create :name => "amr", :email => "amr@abc.com", :password =>"123456", :password_confirmation => "123456"
      my_friend = User.create :name => "ahmed", :email => "ahmed@abc.com", :password =>"123498", :password_confirmation => "123498"
      this_user.invite my_friend
      my_friend.approve this_user
      assert_equal('Friend blocked successfully.', this_user.block_friends_feed1(my_friend))
      assert_equal('Friend already blocked.', this_user.block_friends_feed1(my_friend))
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
	test "should not save a user without any parameters RED" do
		user = User.new
		assert !user.save
	end

	#Auther: Kiro
	test "should not save a user without a password RED" do
		user = User.new
		user.email = "email_1@test.com"
		assert !user.save, "saved a user without a password"
	end
	
	#Auther: Kiro
	test "shouldn't save a user with mismatching password confirmation" do
		user = User.new(email: "email_2@test.com",password: "123456",password_confirmation:"abcdet")
		assert !user.save
	end

	#Auther: Kiro
	test "password should be 6 characters or more RED" do

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
	test "should not save a user without a password confirmation RED" do
		user = User.new
		user.email = "email_1@test.com"
		user.password = "123456"
		assert !user.save, "saved a user without password confirmation"
	end

	#Author: Kiro
	test "Should not save a user with an existing email RED" do
	
		user = User.new(email: "only_one@test.com", password:"123456", password_confirmation:"123456")
		assert user.save, "didn't save the first user"
	
		user = User.new(email: "only_one@test.com", password:"123456", password_confirmation:"123456")
		assert !user.save, "saved a user with an existing email"

	end

	#Author: Kiro
	test "should not accept an email in a wrong format RED" do
	
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
   

end
