require 'test_helper'

class UserTest < ActiveSupport::TestCase
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
	u = User.new
	u.email = "a@abc.com"
	u.save
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

    
    #Author : Omar 
    #$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$
    	test "get all interests" do
			user = User.create(:email => "a@b.com")		
			int = Interest.create(:name => "interest")
			all_interests = Interest.all
			all = user.all_interests
			assert_equal( all_interests , all , "[not equal]" )
		  end


	test "get user interests" do 
		user = User.create(:email => "a@b.c")
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
    

	
end
