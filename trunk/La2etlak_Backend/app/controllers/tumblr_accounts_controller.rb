class TumblrAccountsController < ApplicationController
  require 'Tumblr'
  
=begin
  renders the login form login.html.erb
  input: none
  output: email and password
  Author: Essam Hafez
=end
  def login 
  
  end 
  
=begin
  creates the new tumblr account and relates it to the current user and saves the email and password the user entered
  then redirected back to social accounts page
  input: email and password
  output: none
  Author : Essam Hafez
=end
  def connect_tumblr
    email = params[:email]
    password = params[:password]
    tm = TumblrAccount.new
    tm.email = email
    tm.password = password
    tm.save
    current_user.tumblr_account = tm
    redirect_to action:'connect_social_accounts' , controller:'users'
  end
  
  
end

