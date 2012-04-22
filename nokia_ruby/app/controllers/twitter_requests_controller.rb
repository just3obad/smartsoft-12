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
    user = User.find(params[:u_id]) 

    req = oa.get_request_token
    @t_request.request_secret = req.secret
    @t_request.request_token = req.token
    @t_request.user = user


    old_req = TwitterRequest.find_by_user_id(user.id)
    puts '###########' + old_req.to_s

    prefeix = ''
    if (old_req)
      prefix = 'deleted old one' 
      old_req.destroy
    else 
      prefix = "it's a new one"
    end 

    @t_request.save

    # FIXME remove everything but the url
    url = "http://api.twitter.com/oauth/authorize?oauth_token=#{ @t_request.request_token }"
    render text: "#{ url } user with id #{params[:u_id]} made a twitter request #{@t_request.id} #{prefix} " 

  end 


  # AFter the user authenticates
  def generate_access_token

    user = User.find(params[:u_id])  
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
    @t_account.user = user

    puts 'access_secret is ' + @t_account.auth_secret
    @t_account.save
    render text: "User #{ param[:u_id] } created a new twitter account"
  end 


end
