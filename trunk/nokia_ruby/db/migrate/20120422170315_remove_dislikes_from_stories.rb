class RemoveDislikesFromStories < ActiveRecord::Migration
  def up
    remove_column :stories, :dislikes
      end

  def down
    add_column :stories, :dislikes, :integer
  end
end
