class AddIsBlockedToFriends < ActiveRecord::Migration
  def change
    add_column :friends, :is_blocked, :boolean
  end
end
