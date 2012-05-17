require 'flickraw'
class FlickrAccountsController < ApplicationController

  

  API_KEY='443b0e6f88e26df854bdf0d7f7a8c1d5'
  SHARED_SECRET='ff3e3ebe8f31e039'
  


  FlickRaw.api_key=API_KEY
  FlickRaw.shared_secret=SHARED_SECRET
  

  def auth
@user = current_user
    puts @user.name

    puts "~~~~~~~~~~~"

    #@user = User.find(1)
    flickr = FlickRaw::Flickr.new
    token = flickr.get_request_token(:oauth_callback => (
    'http://127.0.0.1:3000/users/flickr/callback'))
    @user.name = token['oauth_token_secret']
    @user.save


    auth_url = flickr.get_authorize_url(token['oauth_token'], :perms => 'read')
    redirect_to(auth_url)

  end

  def callback 
    #@user = User.find(1)
@user = current_user
     puts @user.name

    puts "~~~~~~~~~~~"

    flickr = FlickRaw::Flickr.new
    oauth_token = params[:oauth_token]
    oauth_verifier = params[:oauth_verifier]
    raw_token = flickr.get_access_token(params[:oauth_token], User.find(1).name, params[:oauth_verifier])

  # raw_token is a hash like this {"user_nsid"=>"92023420%40N00", "oauth_token_secret"=>"XXXXXX", "username"=>"boncey", "fullname"=>"Darren%20Greaves", "oauth_token"=>"XXXXXX"}
  # Use URI.unescape on the nsid and name parameters

    oauth_token = raw_token["oauth_token"]
    oauth_token_secret = raw_token["oauth_token_secret"]


    flickr.access_token = oauth_token
    flickr.access_secret =oauth_token_secret

  # Store the oauth_token and oauth_token_secret in session or database
  #   and attach to a Flickraw instance before calling any methods requiring authentication

  # Attach the tokens to your flickr instance - you can now make authenticated calls with the flickr object
    

    if @user.flickr_account
      @user.flickr_account.delete
    end

  

    flickr_account = FlickrAccount.create(consumer_key: oauth_token , secret_key: oauth_token_secret)
    @user.flickr_account = flickr_account

    unless flickr_account.new_record?
      flash[:notice] = 'Flickr account created green'
    else 
      flash[:notice] = 'Flickr account couldn\'t be created red'
    end 

    redirect_to controller: 'users', action: 'connect_social_accounts'

 

    end


end
