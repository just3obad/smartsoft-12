class TumblrAccountsController < ApplicationController
  require 'Tumblr'
  def login 
  
  end 
  
  def connect_tumblr
    email = params[:email]
    password = params[:password]
    tm = TumblrAccount.new
    tm.email = email
    tm.password = password
    tm.save
    current_user.tumblr_account = tm
    #user = User.first
    #user.tumblr_account = tm
    redirect_to action:'connect_social_accounts' , controller:'users'
  end
  
  
end

