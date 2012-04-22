require 'twitter'
require 'oauth'
class TwitterRequestsController < ApplicationController

  CONSUMER_TOKEN  = 'cuE56v2eK79gsxK1baasA'
  CONSUMER_SECRET = 'vIJmDpiqDNpiau2YaiHH19fW1zj5DRXjI1VUbuM7lk' 

  def generate_request_token
    oa = OAuth::Consumer.new(CONSUMER_TOKEN, CONSUMER_SECRET,
                             :site=> 'http://api.twitter.com',
                             :request_endpoint => 'http://api.twitter.com',
                             :sign_in => true)
    req = oa.get_request_token
    request_secret = req.secret
    request_token  = req.token

    url = "http://api.twitter.com/oauth/authorize?oauth_token=#{ request_token }"
    #puts "######## " + url
    session[:twitter_auth_req] = req
    #format.json { [twitter_auth_url: url] } 
    #render(:text => url)
    # I am rendering because this will only be called from the mobile side
    render text: url
  end 


  def generate_access_token()

    oa = OAuth::Consumer.new(self.consumer_token, $consumer_secret,
                             :site=> 'http://api.twitter.com',
                             :request_endpoint => 'http://api.twitter.com',
                             :sign_in => true)
    req = oa.get_request_token
    req.token = $req_token
    req.secret = $req_secret
    access = req.get_access_token
    $access_token = access.token
    puts 'access_token is ' + $access_token
    $access_secret= access.secret
    puts 'access_secret is ' + $access_secret
  end 



end
