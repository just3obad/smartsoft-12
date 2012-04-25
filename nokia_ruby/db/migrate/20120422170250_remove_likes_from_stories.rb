class RemoveLikesFromStories < ActiveRecord::Migration
  def up
    remove_column :stories, :likes
      end

  def down
    add_column :stories, :likes, :integer
  end
end
