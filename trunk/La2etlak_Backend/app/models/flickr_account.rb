require 'flickraw'

class FlickrAccount < ActiveRecord::Base

API_KEY='443b0e6f88e26df854bdf0d7f7a8c1d5'
SHARED_SECRET='ff3e3ebe8f31e039'
  

=begin  
  consumer_key and secret_key are the access keys to the flickr 
  accounts. Flickr provides them to us in phase to in the 
  authentication. see flickr_accounts_controller.rb for more info 
=end

  attr_accessible :consumer_key, :secret_key, :user_id
  belongs_to :user, class_name: "User" 

  validates :consumer_key, presence: true
  validates :secret_key, presence: true


def config_flickr
  FlickRaw.api_key=API_KEY
  FlickRaw.shared_secret=SHARED_SECRET
  flickr.access_token=self.consumer_key
  flickr.access_secret=self.secret_key
end


=begin
  This is the method responsible for getting the recent photos of the user`s 
  friends. login = flickr.test.login is used to chech the if the token has expired 
  or not by making a test login. photo_list is a list of the recent friends` photos
  then we change them into stories to be used in the main feed.
  Author: 3OBAD
=end


  def get_feed

begin
     self.config_flickr
     login = flickr.test.login
     puts "#{login} + login thats good"
     user = flickr.people.findByUsername( :username => login.username )
     photo_list = flickr.photos.getContactsPhotos( :count=>"20",:just_friends => "1")
    rescue Exception => ex
      puts ex
      puts "False"
      return []
    end
 
     stories = Array.new
     puts "Created the array"
     photo_list.each do |photo|
      puts "will convert"
     phtot = FlickrAccount.convert_photo_to_story(photo) 
     puts "pushing"
     stories.push(photo) 
    end 
    #puts stories 
    puts "True"
    return stories

  end


=begin
  This is the method that change the photo onto a story by getting 
  the story url and its title
  Author: 3OBAD

=end 

  def self.convert_photo_to_story(photo)
    #story = [author: tweet['user']['name'], text: tweet['text']]
    puts "getting info" 
    info = flickr.photos.getInfo :photo_id => photo.id, :secret => photo.secret
    puts "getting url"
    original_url = FlickRaw.url(info)
    puts "new story"
    story = Story.new
    story.instance_variables
    story.title = "Photo of #{photo.username}"
    puts story.title
    story.category = 'flickr'
    puts story.category
    story.media_link = original_url
    puts story.media_link
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