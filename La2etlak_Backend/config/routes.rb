NokiaRuby::Application.routes.draw do

	# $$$$$$$$$$$$$$ KIRO $$$$$$$$$$$$$$$$$$$$$$$$$
	match "requestToken" => "user_sessions#requestToken"
	match "login" => "user_sessions#login_with_token"
	match "register" => "users#register"
	match "dummyLogin" => "users#dummyLogin"
	match "users/test" => "users#test"
	match "users/test_2" => "users#test_2"
	resources :user_sessions
	# $$$$$$$$$$$$$$ KIRO $$$$$$$$$$$$$$$$$$$$$$$$$

  match "users/:id/profile" => "users#profile"
  match "h_accounts/:id/profile" => "h_accounts#profile"
  match "users/:id/stories" => "users#feed"
  post  "h_accounts/sign_in"
  post  "h_accounts/password" => "h_accounts#forgot_password"
  match 'stories/get_friend_id' => 'stories#get_friend_id'
        # $$$$$$$$$$$$$$ RANA $$$$$$$$$$$$$$$$$$$$$$$$$ 
  match 'users/block_story/:id' => 'users#block_story'
  match 'users/block_interest/:id' => 'users#block_interest'
  match 'users/friends_list/' => 'users#friends_list'
  match 'users/block_friends_feed/:fname' => 'users#block_friends_feed'
  match 'users/friends_feed/:fname' => 'users#friends_feed'
        # $$$$$$$$$$$$$$ RANA $$$$$$$$$$$$$$$$$$$$$$$$$
  match "users/index" => "users#index"
  match "/users/new" => "users#create", :as => :create
  match "users/:id" => "users#show"
  match "/h_accounts/create" => "h_accounts#create", :as => :create
 
  

  match "/interests/:id/statistics" => "statistics#interests"
  match "/stories/:id/statistics" => "statistics#stories"
  match "/users/:id/statistics" => "statistics#users"
   post "/autocomplete/auto_complete_for_autocomplete_query" => "autocomplete#auto_complete_for_autocomplete_query"
  match "/admins/index" => "admins#index"
  match "admins/statistics/all_users" => "statistics#all_users"
  match "admins/statistics/all_interests" => "statistics#all_interests"
  match "admins/statistics/all_stories" => "statistics#all_stories"
  match "h_accounts/verify" => "verification_codes#verify"
  match "h_accounts/:id/resend" => "verification_codes#resend"
  match "h_accounts" => "h_accounts#index"
  match "/users/:id" => "users#feed"
  match "/admins/search" => "admins#search"

  match "/interests/list"  => "interests#new", :as => :new

 match "interests/:id/toggle" => "interests#toggle"

#--------------------Kareem------------------------
  match "user_add_interests/interests" => "user_add_interests#get_interests" 
  match "likedislikes/thumb" => "likedislikes#thumb"
  match "users/:id/stories" => "users#feed"
  match "flags/flag" => "flags#flag"
#-------------------END---------------------------- 




  # $$$$$$$$$$$$$$  YAHIA $$$$$$$$$$$$$$$$$$$$$$$
  match "users/twitter/generate_request_token" => "users#generate_request_token"
  match "users/twitter/generate_access_token" => "users#generate_access_token"
  match "twitter_accounts/delete" => "twitter_accounts#remove_twitter_account"
  match "twitter_accounts/exists" => "twitter_accounts#exists?"
  match "users/mob/connect_network" => "users#connect_social_accounts"
  # $$$$$$$$$$$$$$  YAHIA $$$$$$$$$$$$$$$$$$$$$$$
  
  # $$$$$$$$$$$$$$  MENISY $$$$$$$$$$$$$$$$$$$$$$$
  match "stories/:id/comments/new" => "stories#create_comment"
  match "stories/:id/comments/upc" => "stories#up_comment"
  match "stories/:id/comments/downc" => "stories#down_comment"
  match "/stories/:id/show_mob/" => "stories#mobile_show"
  # $$$$$$$$$$$$ MENISY END $$$$$$$$$$$$$$$$$$$$$$

    # $$$$$$$$$$$$$$ Khaled $$$$$$$$$$$$$$$$$$$$$$$$$
  match "stories/:sid/recommend_story_mobile_show" => "stories#recommend_story_mobile_show"
  match "/stories/:id/liked_mobile_show/:id2" => "stories#liked_mobile_show", :as => :like
  match "/stories/:id/disliked_mobile_show/:id2" => "stories#disliked_mobile_show", :as => :dislike
  match "/users/get_friends_email_mobile_show/:uid" => "users#get_friends_email_mobile_show", :as => :email
   # $$$$$$$$$$$$$$ Khaled $$$$$$$$$$$$$$$$$$$$$$$$$


  #match "users/" => "users#index"
  match "friends/"=>"friends#index"
  match "friends/:id/req" => "friends#req"
  match "friends/:id/accept" => "friends#accept"
  match "friends/:id/reject" => "friends#reject"
  match "friends/index"=>"friends#index"
  match "friends/find"=>"friends#find"
  match "friends/:id/myreq"=>"friends#myreq"
  match "users/" => "users#index"
  match '/pages/home' => 'pages#home'

  match 'interests/feeds/create' => 'feeds#create'
  resources :feeds


  match "check" => "verification_codes#check"

  #match "/feeds/:id" => "feeds#create"
  #match "/feeds/delete/:id" => "feeds#destroy"
  
  #$$$$$$$$$$$$$$$$ MINA $$$$$$$$$$$$$$$$$$$$$$$
  match "users/:id/main_feed" => "users#feed"
  #$$$$$$$$$$$$$$$$ MINA $$$$$$$$$$$$$$$$$$$$$$$
  
 # $$$$$$$$$$$$$$  OMAR $$$$$$$$$$$$$$$$$$$$$$$
  match "/stories/:id/get" => "stories#get"
  match "users/:id/toggle" => "users#toggle"   
  match "users/:id/user_add_interests" => "users#user_add_interests"
 # $$$$$$$$$$$$$$  OMAR $$$$$$$$$$$$$$$$$$$$$$$ 
 
  match "user/:id/friendship/invite" => "user_friend#invite"
  match "user/:id/friendship/approve" => "user_friend#approve"
  match "user/:id/friendship/pending" => "user_friend#pending_invited_by"
  match "user/:id/friendship/friends" => "user_friend#friends"
  match "user/:id/friendship/checkFriends" => "user_friend#friends_with?"
  match "user/:id/friendship/removeFriend" => "user_friend#remove_friendship"
  match "user/:id/friendship/block" => "user_friend#block"
  match "user/:id/friendship/unblock" => "user_friend#unblock"
  match "user/:id/friendship/totalfriends" => "user_friend#total_friends"
  
  


  root :to => 'admins#index'
  
#  resources :users
#  resources :h_accounts

  resources :admins
  resources :stories
  resources :interests
  resources :shares 
  resources :comments



  resources :gaheem_accounts
  #resources :friends
#LogFiles
  resources :logs, :except => [:delete,:show]  
  match '/logs/filter/',    to: 'logs#filter'
  match '/logs/search/',    to: 'logs#search'
  match '/logs/filterbydate/',    to: 'logs#filter_by_date'
  match '/logs/interest/:id',    to: 'logs#get_specific_interest'
  match '/logs/stories/:id',    to: 'logs#get_specific_story'
  match '/logs/users/:id',    to: 'logs#get_specific_user'
  match '/logs/admins/:id',    to: 'logs#get_specific_admin'
#LogFiles
  
  # $$$$$$$$$$$$$$ GASSER $$$$$$$$$$$$$$$$$$$$$$$
  match '/force_reset_password/:id', to: 'users#force_reset_password'
  # $$$$$$$$$$$$$$ GASSER $$$$$$$$$$$$$$$$$$$$$$$

  # The priority is based upon order of creation:
  # first created -> highest priority.

  # Sample of regular route:
  #   match 'products/:id' => 'catalog#view'
  # Keep in mind you can assign values other than :controller and :action

  # Sample of named route:
  #   match 'products/:id/purchase' => 'catalog#purchase', :as => :purchase
  # This route can be invoked with purchase_url(:id => product.id)

  # Sample resource route (maps HTTP verbs to controller actions automatically):
  #   resources :products

  # Sample resource route with options:
  #   resources :products do
  #     member do
  #       get 'short'
  #       post 'toggle'
  #     end
  #
  #     collection do
  #       get 'sold'
  #     end
  #   end

  # Sample resource route with sub-resources:
  #   resources :products do
  #     resources :comments, :sales
  #     resource :seller
  #   end

  # Sample resource route with more complex sub-resources
  #   resources :products do
  #     resources :comments
  #     resources :sales do
  #       get 'recent', :on => :collection
  #     end
  #   end

  # Sample resource route within a namespace:
  #   namespace :admin do
  #     # Directs /admin/products/* to Admin::ProductsController
  #     # (app/controllers/admin/products_controller.rb)
  #     resources :products
  #   end

  # You can have the root of your site routed with "root"
  # just remember to delete public/index.html.
  # root :to => 'welcome#index'

  # See how all your routes lay out with "rake routes"

  # This is a legacy wild controller route that's not recommended for RESTful applications.
  # Note: This route will make all actions in every controller accessible via GET requests.
  # match ':controller(/:action(/:id))(.:format)'
end
