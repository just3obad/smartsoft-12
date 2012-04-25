class AddIsBlockedToStories < ActiveRecord::Migration
  def change
    add_column :stories, :is_blocked, :boolean
  end
end
