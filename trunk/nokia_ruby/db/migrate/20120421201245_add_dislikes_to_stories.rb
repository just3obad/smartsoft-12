class AddDislikesToStories < ActiveRecord::Migration
  def change
    add_column :stories, :dislikes, :integer
  end
end
