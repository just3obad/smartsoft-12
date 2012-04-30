class AddIndexToStories < ActiveRecord::Migration
  def self.up
    add_index :stories, :interest_id
  end

  def self.down
    remove_index :stories, :column => :interest_id
  end

end
