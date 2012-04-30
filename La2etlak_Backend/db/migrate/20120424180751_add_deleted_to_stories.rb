class AddDeletedToStories < ActiveRecord::Migration
  def change
    add_column :stories, :deleted, :boolean
  end
end
