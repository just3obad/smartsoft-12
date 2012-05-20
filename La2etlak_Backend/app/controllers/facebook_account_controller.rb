class FacebookAccountController < ApplicationController

  before_filter {user_authenticated?}
	 # Action to be called from the connet_to_social network view
  # which redirects to the facebook api using koala
  # Author: Menisy
  def authenticate_facebook_init
    path = Koala::Facebook::OAuth.new.url_for_oauth_code(:callback => "http://127.0.0.1:3000/fb/done/",:permissions => "read_stream")  
    redirect_to path
  end

  # Action to be called in the call back url after authentication take place
  # Author: Menisy
  def authenticate_facebook_done
    if !params[:code]
      flash[:notice] = "Facebook account was not added red"
      redirect_to :controller => "users", :action => "feed"
      return
    end
    token = Koala::Facebook::OAuth.new("http://127.0.0.1:3000/fb/done/").get_access_token(params[:code]) if params[:code]
    fb_account = FacebookAccount.find_or_create_by_user_id(current_user.id)
    fb_account.auth_token = token
    fb_account.auth_secret = 1
    graph =  Koala::Facebook::API.new(token)
    user = graph.get_object("me")
    fb_id = user["id"]
    fb_account.facebook_id = fb_id
    if fb_account.save
      fb_account.add_to_log
      flash[:notice] = "Facebook account was added successfully green"
      redirect_to :controller => "users", :action => "connect_social_accounts"
    else
      flash[:notice] = "Facebook account was not added red" + user.to_s
      redirect_to :controller => "users", :action => "connect_social_accounts"
    end
  end
end


