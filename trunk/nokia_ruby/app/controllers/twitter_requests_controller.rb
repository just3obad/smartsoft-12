require 'twitter'
require 'oauth'
class TwitterRequestsController < ApplicationController

  CONSUMER_TOKEN  = 'cuE56v2eK79gsxK1baasA'
  CONSUMER_SECRET = 'vIJmDpiqDNpiau2YaiHH19fW1zj5DRXjI1VUbuM7lk' 



  #Create
  def generate_request_token
    oa = OAuth::Consumer.new(CONSUMER_TOKEN, CONSUMER_SECRET,
                             :site=> 'http://api.twitter.com',
                             :request_endpoint => 'http://api.twitter.com',
                             :sign_in => true)

    @t_request = TwitterRequest.new
    user = User.find(params[:u_id]) #FIXME according to mina adel 

    req = oa.get_request_token
    @t_request.request_secret = req.secret
    @t_request.request_token = req.token
    @t_request.user = user

    url = "http://api.twitter.com/oauth/authorize?oauth_token=#{ @t_request.request_token }"
    if @t_request.save
      render text: "#{ url } user with id #{params[:u_id]} made a twitter request #{@t_request.id}  " 
    else 
      render text: "# user is has already a request"
      user.twitter_request.destroy
      @t_request.save
    end 

    #puts "######## " + url
    #format.json { [twitter_auth_url: url] } 
    #render(:text => url)
    # I am rendering because this will only be called from the mobile side
    #render text: "#{ url } user with id #{params[:u_id]} made a twitter request  " 
  end 


  # AFter the user authenticates
  def generate_access_token()
    t_request = TwitterRequest.find_by_user_id(params[:u_id])

    oa = OAuth::Consumer.new(self.consumer_token, $consumer_secret,
                             :site=> 'http://api.twitter.com',
                             :request_endpoint => 'http://api.twitter.com',
                             :sign_in => true)
    req = oa.get_request_token
    req.token =  t_request.request_token
    req.secret = t_request.request_secret
    access = req.get_access_token

    @t_account = TwitterAccoung.new
    @t_account.auth_token = access.token
    @t_account.auth_secret = access.secret

    puts 'access_secret is ' + $access_secret
    t_account.save
    render text: "User #{ param[:u_id] } created a new twitter account"
  end 



end
