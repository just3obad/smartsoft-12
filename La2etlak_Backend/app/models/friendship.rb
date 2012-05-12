class Friendship < ActiveRecord::Base
  include Amistad::FriendshipModel
  Attr_accessible user_id, friend_id

end
