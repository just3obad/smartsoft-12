class TwitterAccountsController < ApplicationController
=begin
  This is the first phase of the OAuth phase that is required by twitter.
  In this phase, first a new Consumer gets created which is basically 
  a client that represents our app talking to twitter. 

  The client asks twitter for a request_token, from which a URL will 
  be generated. The callback url should be changed to the proper server 
  URL. 

  The browser will be redirected to the generated authorization URL. After
  that, twitter will redirect the user back to our app. 

  Author: Yahia
=end
  def generate_request_token
    #FIXME FOR THE SAKE OF TESTING
    session[:user_id] = 1

    #FIXME change IP 
    request_token = User.twitter_consumer.get_request_token(:oauth_callback => 
                "http://127.0.0.1:3000/users/twitter/generate_access_token")

    url = request_token.authorize_url
    #puts 'URL IS ' + url
    redirect_to(url)
  end 

=begin
  This is the second phase of authentication. In this phase, the user should have 
  authenticated our app through twitter. Then we use the request token and secret 
  token from that exact user to get our access tokens from twitter. Through the 
  access token, we can get the feeds or tweet on behalf of the user. 

  This is done by simply
  requesting the access token by the oauth_token and oauth_verifier which twitter
  put in the params array. Through this access token the twitter accoun can be made
  thorugh which the system fetches tweets.

  Author: Yahia
=end 
  def generate_access_token
    # FIXME FOR THE SAKE OF TESTING
    session[:user_id] = 1
    @user = User.find(session[:user_id])
    request_token = OAuth::RequestToken.new(User.twitter_consumer,
                    params["oauth_token"], params["oauth_verifier"])

    t_account = @user.create_twitter_account(request_token)

    unless t_account.new_record?
      render(:layout => 'mobile_template', 
              :text => "User #{ session[:user_id] }" + 
                      "created a new twitter account")
    else 
      render(:layout => 'mobile_template', 
              :text => 'Something wrong, couldn\'t save account')
    end 
  end 


end
