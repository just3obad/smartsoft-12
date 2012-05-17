class TumblrAccount < ActiveRecord::Base
  attr_accessible :consumer_key, :secret_key
  belongs_to :user_id, class_name: "User" 
  
  #CONSUMER_KEY  = 'QQQxBUYP17iv7ByUdfP1jNfuAoSBwA3NxMoH7jlvo3ImjjVCNU '
  #SECRET_KEY = 'XX8QVI0fUhU1j5v2nypLq67aCIkWBhA06bB19dYh42ahd6akdo'
  
  #get_feed issue 90
  # gets user tumblr feeds
  # a gem is used called matenia-tumblr-api
  # the user enters his email and password in the login.html.view,
  # values are added to the tumbr account model and saved
  #when get_feed is called the authentication is done through the first line
  # then blog name is get
  # finally looping and converting blogs into stories
  
  def get_feed
    new_tumblr = Tumblr::User.new(self.email, self.password) #Authentication
    blog = new_tumblr.tumblr["tumblelog"]["name"]   #Get blog name through hashes
    Tumblr.blog = blog # set blog name
    stories = Array.new # create stories array to be returned
    posts = Tumblr::Post.all #Get user posts
    posts.each do |post| #loop and convert to story type
      s = TumblrAccount.convert_blog_to_story(post)
      stories.push(s)
    end
      stories
  end
  
  #gets a blog and grabs out the needed content from it to fit our application
  #a story needs a title, content and media link
  
    def self.convert_blog_to_story(post)
      story = Story.new #new Story
      story.instance_variables
      story.category = 'tumblr'
      story.title = post["slug"] # title encapsulated into "slug" hash
      s = post["type"] # get post type
      x = s <=> ("photo")  # checks if the story type is photo
      if(x==0)
        photo_type = post["photo_url"] 
        story.media_link = post["photo_url"][3]["__content__"] #gets photo @ position 3 with max width of 250px
        story.content = post["photo_caption"]
      else
        story.content = post["regular_body"]
      end
      return story
    end
end
