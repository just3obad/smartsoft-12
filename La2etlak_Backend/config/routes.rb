NokiaRuby::Application.routes.draw do


  get "admin_settings/index"


 # ~~~~~~~~~~~~~~~~ 3OBAD ~~~~~~~~~~~~~~~~~~~~~~~~

  match "users/edit" => "users#edit"
  post "users/info/update" => "users#update"
  match "users/flickr/authenticate" => "flickr_accounts#auth"
  match "users/flickr/callback" => "flickr_accounts#callback"

 # ~~~~~~~~~~~~~~~~ 3OBAD ~~~~~~~~~~~~~~~~~~~~~~~~


	# $$$$$$$$$$$$$$ KIRO $$$$$$$$$$$$$$$$$$$$$$$$$
	match "requestToken" => "user_sessions#requestToken"
	match "login" => "user_sessions#login_with_token"
	match "register" => "users#register"
	match "resetPassword" => "users#resetPassword"
	match "dummyLogin" => "users#dummyLogin"
	match "mob/test" => "users#test"
	match "mob/test_2" => "users#test_2"
	resources :users
	resources :user_sessions
	# $$$$$$$$$$$$$$ KIRO $$$$$$$$$$$$$$$$$$$$$$$$$

# $$$$$$$$$$$$$$  OMAR $$$$$$$$$$$$$$$$$$$$$$$
  match "/stories/:id/get" => "stories#get"
  match "mob/toggle" => "users#toggle"   
  match "mob/int_toggle" => "users#int_toggle"
# $$$$$$$$$$$$$$  OMAR $$$$$$$$$$$$$$$$$$$$$$$ 



  #match "users/mobile_show/edit" => "users#edit"
  #post  "users/mobile_show/update" => "users#update"
  match "h_accounts/:id/profile" => "h_accounts#profile"
  match "users/:id/stories" => "users#feed"
  post  "h_accounts/sign_in"
  post  "h_accounts/password" => "h_accounts#forgot_password"
  match 'stories/get_friend_id' => 'stories#get_friend_id'

        # $$$$$$$$$$$$$$ RANA $$$$$$$$$$$$$$$$$$$$$$$$$ 
  match 'block_story/:id' => 'users#block_story'
  match 'block_interest/:id' => 'users#block_interest'
  match 'friends_list' => 'users#friends_list'
  match 'block_friends_feed/:id' => 'users#block_friends_feed'
  match 'friends_feed/:id' => 'users#friends_feed'
        # $$$$$$$$$$$$$$ RANA $$$$$$$$$$$$$$$$$$$$$$$$$
        
  #$$$$$$$$$$$$$$$$ MINA $$$$$$$$$$$$$$$$$$$$$$$
  match "main_feed" => "users#feed"
  match "shares/:id" => "shares#user_share_story"
  #$$$$$$$$$$$$$$$$ MINA $$$$$$$$$$$$$$$$$$$$$$$
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
  
  #Author: Lydia############
  match "/admins/search" => "admins#search"
  match "/admins/all_results" => "admins#all_results"
  match "/admins/filter" => "admins#filter"
  ##########################

  match "/interests/list"  => "interests#new", :as => :new

 match "interests/:id/toggle" => "interests#toggle"

#--------------------Kareem------------------------
  match "user_add_interests/interests" => "user_add_interests#get_interests" 
  match "likedislikes/thumb" => "likedislikes#thumb"
  match "users/:id/stories" => "users#feed"
  match "flags/flag" => "flags#flag"
#-------------------END---------------------------- 




  # $$$$$$$$$$$$$$  YAHIA $$$$$$$$$$$$$$$$$$$$$$$
  match "users/twitter/generate_request_token" => "twitter_accounts#generate_request_token"
  match "users/twitter/generate_access_token" => "twitter_accounts#generate_access_token"
  match "users/mob/connect_network" => "users#connect_social_accounts"
  match "friendship/search/" => "friendships#search"
  match "friendship/search/:query" => "friendships#search"
  match "friendship/create/:friend_id" => "friendships#create"
  match "friendship/reject/:friend_id" => "friendships#remove"
  match "friendship/block/:friend_id" => "friendships#block"
  match "friendship/pending" => "friendships#pending"
  match "friendship/accept/:friend_id" => "friendships#accept"
  match "friendship/index" => "friendships#index"
  # $$$$$$$$$$$$$$  YAHIA  END $$$$$$$$$$$$$$$$$$$
  
  # $$$$$$$$$$$$$$  MENISY $$$$$$$$$$$$$$$$$$$$$$$
  match "stories/:id/comments/new" => "stories#create_comment"
  match "stories/:id/comments/upc" => "stories#up_comment"
  match "stories/:id/comments/downc" => "stories#down_comment"
  match "/stories/:id/show_mob/" => "stories#mobile_show"
  match "/fb/done" => "users#authenticate_facebook_done"
  match "users/facebook/auth_init" => "users#authenticate_facebook_init"
  # $$$$$$$$$$$$ MENISY END $$$$$$$$$$$$$$$$$$$$$$

    # $$$$$$$$$$$$$$ Khaled $$$$$$$$$$$$$$$$$$$$$$$$$
  match "stories/recommend_story_mobile_show/:sid" => "stories#recommend_story_mobile_show"
  match "stories/recommend_success_mobile_show/:sid" => "stories#recommend_success_mobile_show"
  match "/stories/liked_mobile_show/:id" => "stories#liked_mobile_show", :as => :like
  match "/stories/disliked_mobile_show/:id" => "stories#disliked_mobile_show", :as => :dislike
  match "/users/get_friends_email_mobile_show" => "users#get_friends_email_mobile_show", :as => :email
   # $$$$$$$$$$$$$$ Khaled $$$$$$$$$$$$$$$$$$$$$$$$$

   #$$$$$$$$$$$$$$$$$$$$$ BASSEM !! $$$$$$$$$$$$$$$$$$
   match "users/deactivate/:id" => "users#deactivate"
   match "users/activate/:id" => "users#activate"
   match "stories/filter" => "stories#filter"
   match "/admin/settings" => "admin_settings#index"
   #$$$$$$$$$$$$$$$$$$$$$ BASSEM !! $$$$$$$$$$$$$$$$$$


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
 
 
  
  


  root :to => 'admins#index'
  
#  resources :users
#  resources :h_accounts

  resources :admins
  resources :stories
  resources :interests
  resources :shares 
  resources :comments
  resources :admin_sessions



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
  match '/users/force_reset_password/:id', to: 'users#force_reset_password'
  get '/admin_settings', to: 'admin_settings#show'
  post '/admin_settings', to: 'admin_settings#configure_flags_threshold'
  # $$$$$$$$$$$$$$ GASSER $$$$$$$$$$$$$$$$$$$$$$$
  #Mouaz
  match '/admin_login', to: 'admin_sessions#new'
  match '/admin_logout', to: 'admin_sessions#destroy'
  match '/admins_reset_pass', to: 'emailer#reset_admin_password'
#  match 'forgot_password' => 'admin_sessions#forgot_password', :as => :forgot_password, :via => :get
 # match 'forgot_password' => 'admin_sessions#forgot_password_lookup_email', :as => :forgot_password, :via => :post

 # put 'reset_password/:reset_password_code' => 'admins#reset_password_submit', :as => :reset_password, :via => :put
  #get 'reset_password/:reset_password_code' => 'admins#reset_password', :as => :reset_password, :via => :get 
 
  # $$$$$$$$$$$$$$ Shafei $$$$$$$$$$$$$$$$$$$$$$$$$
   match 'statistics/top_users', to: 'statistics#top_users'
   get 'statistics/top_users', to: 'statistics#top_users'
   
   
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
