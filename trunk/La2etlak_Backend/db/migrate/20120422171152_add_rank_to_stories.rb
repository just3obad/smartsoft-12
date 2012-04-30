class AddRankToStories < ActiveRecord::Migration
  def change
    add_column :stories, :rank, :integer
  end
end
