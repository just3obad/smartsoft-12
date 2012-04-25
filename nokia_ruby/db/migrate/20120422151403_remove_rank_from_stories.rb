class RemoveRankFromStories < ActiveRecord::Migration
  def up
    remove_column :stories, :rank
      end

  def down
    add_column :stories, :rank, :integer
  end
end
