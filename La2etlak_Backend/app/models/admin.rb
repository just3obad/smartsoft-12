class Admin < ActiveRecord::Base
        #this is added for authlogic gem
    acts_as_authentic


  attr_accessible :first_name, :last_name,:email, :password, :password_confirmation
 
 def send_forgot_password!
  deactivate!
  reset_perishable_token!
  Notifier.forgot_password(self).deliver
 end
  
require "net/http"
  $NAME = /([a-zA-Z]+)(.*)/
  $USERNAME = /(\w+)(.*)/
  $EMAIL = /((?:\w+\.)*\w+@(?:[a-z\d]+[.-])*[a-z\d]+\.[a-z\d]+)(.*)/


  def self.search(query)
    return search_user(query)
  end

  def self.search_user(query)
=begin
    Given a query string, the method analyze the type of the field is being searched for, and 
    returns all the users matching the query.

    Args : query : string of the given query
    Returns : A list of User objects.

    The method work by trying to match the fields first_name, name, email
    a regular expression is used for each of the fields

    For any field, the same is done ::
      if the regular expression of a field is R, then I use the regular expression '(R)(.*)'
      to get the first matching to R, and then taking the rest of the string, and extract 
      from it all that match with R; then a query is done on all users that matches the expressions

    The users from each query are put in a set, to avoid repeated users, then the result returned
    is then converted to an array.
=end
    query_result = [].to_set
    
    if query.instance_of? String
      query = query.downcase
    else
      return query_result.to_a
    end

    email_query = query

    email_match = []
    while email_query =~ $EMAIL
      match = $EMAIL.match(email_query)
      email_match.push(match[1])
      email_query = match[2]
    end

    for email_query in email_match
      query_result += User.all.select {|user| not user.email.nil? and not user.email.empty? and user.email =~ %r'#{email_query}'}
    end

    query_result += User.all.select {|user| not user.email.nil? and not user.email.empty? and (query =~ %r'#{user.email}' or user.email =~ %r'#{query}')}

    # Matched $EMAIL

    name_query = query

    name_match = []
    while name_query =~ $NAME
      match = $NAME.match(name_query)
      name_match.push(match[1])
      name_query = match[2]
    end

    for name_query in name_match
      query_result += User.all.select {|user| not user.first_name.nil? and not user.first_name.empty? and
                                       (user.first_name.downcase =~ %r'#{name_query}' or name_query.downcase =~ %r'#{user.first_name}')}
    end
    # Matched names
    username_query = query

    username_match = []
    while username_query =~ $USERNAME
      match = $USERNAME.match(username_query)
      username_match.push(match[1])
      username_query = match[2]
    end

    for username_query in username_match
      query_result += User.all.select {|user| not user.name.nil? and not user.name.empty? and
                                       (user.name.downcase =~ %r'#{username_query.downcase}' or 
                                        username_query.downcase =~ %r'#{user.name.downcase}')}
    end
    # Matched username

    return query_result.to_a
  end
  #Author MESAI
  #this method is used to get the main feed
  def self.get_feed
   item1 = Interest.order("created_at DESC").where("created_at < ?",30.days.from_now)
   item2 = Story.order("created_at DESC").where("created_at < ?",30.days.from_now)
   item = item1+item2
   $newsfeed = item.sort_by { |obj| obj.created_at }.reverse
   puts $newsfeed
  end
  
  #Author MESAI
  #this method is used to update the main feed on spot.
  def self.push_notifications (channel,data)
    begin
       message = {:channel => channel, :data => data}
       uri = URI.parse("http://localhost:9292/faye")
       Net::HTTP.post_form(uri, :message => message.to_json)
     rescue
     end
  end

  def self.search_story(query)
    return []
  end
  
  def self.search_interest(query)
    return []
  end

	def resetPassword
	
		newpass = ((0..9).to_a + ('a'..'z').to_a + ('A'..'Z').to_a ).shuffle[0..5].join
		self.password = newpass
		self.reset_password!
		return newpass
	
	end
end
