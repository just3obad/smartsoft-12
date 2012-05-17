require 'flickraw'
class FlickrAccountsController < ApplicationController

  

  API_KEY='443b0e6f88e26df854bdf0d7f7a8c1d5'
  SHARED_SECRET='ff3e3ebe8f31e039'
  


  FlickRaw.api_key=API_KEY
  FlickRaw.shared_secret=SHARED_SECRET
  

  def auth

    @user = current_user

    puts @user.email
    Rails.cache.write("user_id", @user.id)

    flickr = FlickRaw::Flickr.new
    token = flickr.get_request_token(:oauth_callback => (
    'http://127.0.0.1:3000/users/flickr/callback'))

    Rails.cache.write("oauth_token_secret",token['oauth_token_secret'])

    
    auth_url = flickr.get_authorize_url(token['oauth_token'], :perms => 'read')
    redirect_to(auth_url)

  end

  def callback 

    user_id = Rails.cache.read("user_id")
    oauth_token_secret = Rails.cache.read("oauth_token_secret")


    flickr = FlickRaw::Flickr.new
    oauth_token = params[:oauth_token]
    oauth_verifier = params[:oauth_verifier]
    raw_token = flickr.get_access_token(params[:oauth_token], oauth_token_secret, params[:oauth_verifier])


    oauth_token = raw_token["oauth_token"]
    oauth_token_secret = raw_token["oauth_token_secret"]


    flickr.access_token = oauth_token
    flickr.access_secret =oauth_token_secret

    if User.find(user_id).flickr_account
       User.find(user_id).flickr_account.delete
    end

    flickr_account = FlickrAccount.create(consumer_key: oauth_token , secret_key: oauth_token_secret)
    User.find(user_id).flickr_account = flickr_account

    unless flickr_account.new_record?
      flash[:notice] = 'Flickr account created green'
    else 
      flash[:notice] = 'Flickr account couldn\'t be created red'
    end 

    redirect_to controller: 'users', action: 'connect_social_accounts'

    end


end
