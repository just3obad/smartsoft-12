require 'twitter'
require 'oauth'
class TwitterRequestsController < ApplicationController
=begin 
  This is the controller that is repsonsible for everything that happens before
  a TwitterAccount gets created and connected to our user. 
=end 

  CONSUMER_TOKEN  = 'cuE56v2eK79gsxK1baasA'
  CONSUMER_SECRET = 'vIJmDpiqDNpiau2YaiHH19fW1zj5DRXjI1VUbuM7lk' 



  #Create
=begin
  This is the first phase of the OAuth phase that is required by twitter.
  In this phase, first a new Consumer gets created which is basically 
  a client that represents our app talking to twitter. 

  After that, the corresponding user (who is requesting this authorization) 
  is found from the database. As the request will be hooked to the user. 

  A small check is made, if the user has a twitter account already, this
  account will be deleted from the database. In order to preserver the 
  one-to-one relation between twitter accounts and Users.

  A new request is created. And twitter gives us the request_secret and
  the request_token which are saved in the table and hooked to the
  corresponding user.

  If the user had any pending old requests, those request are deleted. This
  table is supposed to be very small in size and contains ONLY pending data. 
  As the data gets old very soon in twitter and we the tokens will be 
  uneeded/unuseful after a couple of hours. 

  The new request is then saved and hooked to the user. 
  The mobile expects a response with the authentication url wihch is generated
  by the corresponding code


=end
  def generate_request_token
    oa = OAuth::Consumer.new(CONSUMER_TOKEN, CONSUMER_SECRET,
                             :site=> 'http://api.twitter.com',
                             :request_endpoint => 'http://api.twitter.com',
                             :sign_in => true)

    user = User.find(params[:u_id])
    t_account = TwitterAccount.find_by_user_id(params[:u_id])
    if (t_account)
      #return render text: "You already have an twitter account connected"
      #puts t_account.to_s
      #puts 't_account is destroyed '
      t_account.destroy
    end

    @t_request = TwitterRequest.new
    user = User.find(params[:u_id]) 

    req = oa.get_request_token
    @t_request.request_secret = req.secret
    @t_request.request_token = req.token


    old_req = TwitterRequest.find_by_user_id(user.id)
    #puts '###########' + old_req.to_s

    prefeix = ''
    if (old_req)
      prefix = 'deleted old one' 
      old_req.destroy
    else 
      prefix = "it's a new one"
    end 

    @t_request.save
    @t_request.user = user
    user.twitter_request = @t_request

    # FIXME remove everything but the url
    url = "http://api.twitter.com/oauth/authorize?oauth_token=#{ @t_request.request_token }"
    #render text: "#{ url } user with id #{params[:u_id]} made a twitter request #{@t_request.id} #{prefix} " 
    render text: url

  end 


  # AFter the user authenticates
=begin
  This is the second phase of authentication. In this phase, the user should have 
  authenticated our app through twitter. Then we use the request token and secret 
  token from that exact user to get our access tokens from twitter. Through the 
  access token, we can get the feeds or tweet on behalf of the user. 

  This is done by simply
  - Getting the user
  - Creating a new (at first, dummy) request
  - Changing the parameters of that request with the parameters from the request
    that corresponds to the user. (now it's not dummy ;))  
  - Get token for that request. 
  - Destory the request (as it will become an invalid request after requesting its)
    access keys
  - Make a new Twitter account with the access keys. 
  - Save the twitter account and hook it to the user. 
  - reply with user [ID] created a new twitter account. [won't be used by the mobile side]

=end 
  def generate_access_token

    user = User.find(params[:u_id])  
    t_request = TwitterRequest.find_by_user_id(params[:u_id])

    oa = OAuth::Consumer.new(CONSUMER_TOKEN, CONSUMER_SECRET,
                             :site=> 'http://api.twitter.com',
                             :request_endpoint => 'http://api.twitter.com',
                             :sign_in => true)
    req = oa.get_request_token
    req.token =  t_request.request_token
    req.secret = t_request.request_secret
    access = req.get_access_token

    #puts t_request.to_s + 'destoryed'
    t_request.destroy #not needed any more

    @t_account = TwitterAccount.new
    @t_account.auth_token = access.token
    @t_account.auth_secret = access.secret
    @t_account.user = user

    #puts 'access_secret is ' + @t_account.auth_secret
    @t_account.save
    render text: "User #{ params[:u_id] } created a new twitter account"
  end 


end
