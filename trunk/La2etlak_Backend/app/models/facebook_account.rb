

class FacebookAccount < ActiveRecord::Base
  attr_accessible :auth_secret, :auth_token
  belongs_to :user
  validates_presence_of :user
  validates_presence_of :auth_token
  validates_presence_of :auth_secret


  # Method to get facebook feed of the user
  # if the token is expired a new one is generated
  # key attributes are selected from the facebook stream
  # according to the story type
	# Author: Menisy
  def get_feed
  	begin
      self.auth_secret = self.auth_secret.to_i+1
      p("\n\n\n>>>>>>>>>>>>>>>>>>>>>>>>>> \n\n\ngetting feed\n\n")
      new_token = Koala::Facebook::OAuth.new("http://localhost/fb/done/").exchange_access_token(self.auth_token)
      puts(">>>>>>>>>>>>>>>>>>>>>>>>>>\n\n\n"+new_token+"\n\n\n\n")
      self.auth_token = new_token.to_s
      self.auth_secret = self.auth_secret.to_i+1
      self.save!
      graph = Koala::Facebook::API.new(new_token)
      g = graph.get_connections("me","home")
      feed = Array.new
      pic = ""
      content = ""
      media = ""
      p g.nil?
      if g
        g.each do |s| 
          title = s["from"]["name"]+" shared:"
          if s["type"] == "photo"
            title = title+"\n"+s["message"] if s["message"]
            media = s["picture"]
            if s["caption"]
              content = content + s["caption"]
            end
          elsif s["type"] == "video"
            title = title+"\n"+s["name"] if s["name"]
            media_link = s["link"]
            content = s["message"] if s["message"]
            content = content + s["description"] if s["description"]
          else
            next
          end
          story = Story.new
          story.title = title
          story.media_link = media
          story.content = content
          story.deleted = false
          story.hidden = false
          feed << story
        end
        p "returning feed"
        p feed.to_s
        return feed
      end	
    rescue
      self.destroy
      return []
    end
  end
end
