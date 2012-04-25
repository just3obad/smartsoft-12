class AddIndexToFriends < ActiveRecord::Migration
  def self.up
    add_index :friends, :receiver
    add_index :friends, [:sender, :receiver]
    add_index :friends, :sender
  end

  def self.down
    remove_index :friends, :column => :receiver
    remove_index :friends, [:sender, :receiver]
    remove_index :friends, :column => :sender
  end
end
