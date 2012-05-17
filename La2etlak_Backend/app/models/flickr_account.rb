require 'flickraw'

class FlickrAccount < ActiveRecord::Base



=begin  
  consumer_key and secret_key are the access keys to the flickr 
  accounts. Flickr provides them to us in phase to in the 
  authentication. see flickr_accounts_controller.rb for more info 
=end

  attr_accessible :consumer_key, :secret_key, :user_id
  belongs_to :user, class_name: "User" 

  validates :consumer_key, presence: true
  validates :secret_key, presence: true





=begin
  This is the method responsible for getting the recent photos of the user`s 
  friends. login = flickr.test.login is used to chech the if the token has expired 
  or not by making a test login. photo_list is a list of the recent friends` photos
  then we change them into stories to be used in the main feed.
  Author: 3OBAD
=end


  def get_feed

	begin
     login = flickr.test.login
    rescue
     redirect_to controller: 'flickr_accounts', action: 'auth'
    end

    begin
     user = flickr.people.findByUsername( :username => login.username )
     photo_list = flickr.photos.getContactsPublicPhotos( :user_id => user.nsid, :count => 10 ,:just_friends => 1)
    rescue Exception => ex
     puts ex
      return []
    end
 
     stories = Array.new
     photo_list.each do |image|
     temp = TwitterAccount.convert_tweet_to_story(image) 
     stories.push(temp) 
    end 
    #puts stories 
    return stories

  end


=begin
  This is the method that change the photo onto a story by getting 
  the story url and its title
  Author: 3OBAD

=end 

  def self.convert_image_to_story(image)
    #story = [author: tweet['user']['name'], text: tweet['text']]
    info = flickr.photos.getInfo :photo_id => image.id, :secret => image.secret
    original_url = FlickRaw.url(info)
    story = Story.new
    story.instance_variables
    story.title = image.title
    story.category = 'flickr'
    story.media_link = original_url
    return story 
  end


# def get_photos( username, count = 9 )
#   begin
#     user = flickr.people.findByUsername( :username => username )
#     photo_list = flickr.photos.getContactsPublicPhotos( :user_id => user.nsid, :count => 9 )
#   rescue Exception => ex
#     puts ex
#     return []
#   end
 
#   return photo_list.slice(0, 9)
# end





end





#  puts "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
#   login = flickr.test.login
#   puts "You are now authenticated as #{login.username}" 
#   flickr_name = login.username


#   puts"aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"

#     puts "tttttttttttttttttttttttttttttttttttttttttt"

    

#     #redirect_to("http://farm#{farm}.static.flickr.com/#{server}/#{id}_#{secret}.jpg")


# puts "zzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzz"

#      # info = flickr.photos.getInfo :photo_id => id, :secret => secret
#      # puts info 
#      # puts info.title           # => "PICT986"
#      # puts info.dates.taken     # => "2006-07-06 15:16:18"
    

#     find_user = flickr.people.findByUsername( :username => flickr_name)
#     #find_user = getContactsPhotos(:count => '10',:just_friends => '1',:single_photo => 'single_photo',:include_self => '1',:extras => 'extras')
#     photo_list = flickr.photos.getContactsPublicPhotos( :user_id => find_user.nsid, :count => 10 , :just_friends => 1)
#     puts photo_list[0].id
#     info = flickr.photos.getInfo :photo_id => photo_list[0].id, :secret => photo_list[0].secret

#     puts "!!!!!!"
#      puts info.title 


#      puts "ggggggggggggggggg"
    

#   #square_url = FlickRaw.url_s(info)
#   original_url = FlickRaw.url(info)


#     #redirect_to("http://farm#{photo_list[0].farm}.static.flickr.com/#{photo_list[0].server}/#{photo_list[0].id}_#{photo_list[0].secret}.jpg")
#     redirect_to (original_url)

#      puts"zzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzz"








   
#      #render :text => "HAAHAHAHAH"

#  