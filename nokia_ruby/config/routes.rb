NokiaRuby::Application.routes.draw do

  match "user_add_interests" => "user_add_interests#getinterests"
  match "flags" => "flags#flag"
  match "likedislikes" => "likedislikes#thumb"
  match "/interests/:id/statistics" => "statistics#interests"
  match "/stories/:id/statistics" => "statistics#stories"
  match "/users/:id/statistics" => "statistics#users"
  match "/admins/index" => "admins#index"
  match "admins/statistics/all_users" => "statistics#all_users"
  match "admins/statistics/all_interests" => "statistics#all_interests"
  match "admins/statistics/all_stories" => "statistics#all_stories"
  match "users/:id/update" => "users#update"
  match "h_accounts/:id/update" => "h_accounts#update"
  match "h_accounts/create" => "h_accounts#create", :as => :create
  match "authenticate/:u_id/get_twitter_url" => "twitter_requests#generate_request_token"
  match "authenticate/:u_id/new_twitter_account" =>  "twitter_requests#generate_access_token"
  match "Twitter/:u_id/delete" => "twitter_accounts#remove_twitter_account"
  match "stories/:id/comments"  => "stories#show_comments"
  match "stories/:id/comments/new" => "stories#create_comment"
  match "friends/:id" => "friends#add"
  match "friends/:id/accept" => "friends#update"
  match "friends/:id/reject" => "friends#delete"
  match "h_accounts/:id/verify" => "verification_codes#verify"
  match "h_accounts" => "h_accounts#index"
  match "/users/:id" => "users#feed"
  match "admins?search=:id" => "admins#search"
  match "/users/new" => "users#create"
  match '/add',  :to => 'interests#new'
  match "stories/:id/comments/upc" => "stories#up_comment"
  match "stories/:id/comments/downc" => "stories#down_comment"
  
  match '/pages/home' => 'pages#home'

  root :to => 'pages#home'
  
  resources :users
  resources :admins
  resources :stories
  resources :interests
  resources :shares 
  resources :comments

  resources :feeds, :only => [:create, :destroy, :index, :new]

  resources :gaheem_accounts
  resources :twitter_accounts
  resources :friends
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
