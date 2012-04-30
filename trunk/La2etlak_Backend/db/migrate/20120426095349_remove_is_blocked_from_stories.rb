class RemoveIsBlockedFromStories < ActiveRecord::Migration
  def up
    remove_column :stories, :is_blocked
      end

  def down
    add_column :stories, :is_blocked, :boolean
  end
end
