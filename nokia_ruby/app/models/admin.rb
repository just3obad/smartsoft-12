class Admin < ActiveRecord::Base
  # attr_accessible :title, :body

  $NAME = /([a-zA-Z]+)(.*)/
  $USERNAME = /(\w+)(.*)/
  $EMAIL = /((?:\w+\.)*\w+@(?:[a-z\d]+[.-])*[a-z\d]+\.[a-z\d]+)(.*)/


  def self.search(query)
    query_result = [].to_set

    email_query = query

    email_match = []
    while email_query =~ $EMAIL
      match = $EMAIL.match(email_query)
      email_match.push(match[1])
      email_query = match[2]
    end

    for email_query in email_match
      query_result += User.all.select {|user| not user.email.nil? and user.email =~ %r'#{email_query}'}
    end

    query_result += User.all.select {|user| not user.email.nil? and (query =~ %r'#{user.email}' or user.email =~ %r'#{query}')}

    # Matched $EMAIL
    # The next block is commented until the attributes of user are handled.
    #
    # name_query = query

    # name_match = []
    # while name_query =~ $NAME
    #   match = $NAME.match(name_query)
    #   name_match.push(match[1])
    #   name_query = match[2]
    # end

    # for name_query in name_match
    #   query_result += User.all.select {|user| not user.first_name.nil? and
    #                                   (user.first_name =~ %r'#{name_match}' or name_query.downcase =~ %r'#{user.first_name}')}
    # end
    # END OF BLOCK
    # Matched names
    username_query = query

    username_match = []
    while username_query =~ $USERNAME
      match = $USERNAME.match(username_query)
      username_match.push(match[1])
      username_query = match[2]
    end

    for username_query in username_match
      query_result += User.all.select {|user| not user.name.nil? and
                                      (user.name =~ %r'#{username_query}' or username_query.downcase =~ %r'#{user.name}')}
    end
    # Matched username

    return query_result.to_a
  end


  def self.search_by_id(id)
    return User.find(id)
  end

end
