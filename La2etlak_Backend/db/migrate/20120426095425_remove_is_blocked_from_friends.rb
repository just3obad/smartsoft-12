class RemoveIsBlockedFromFriends < ActiveRecord::Migration
  def up
    remove_column :friends, :is_blocked
      end

  def down
    add_column :friends, :is_blocked, :boolean
  end
end
