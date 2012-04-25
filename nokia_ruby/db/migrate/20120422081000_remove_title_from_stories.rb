class RemoveTitleFromStories < ActiveRecord::Migration
  def up
    remove_column :stories, :title
      end

  def down
    add_column :stories, :title, :string
  end
end
