class AddStoryAttributesToStories < ActiveRecord::Migration
  def change

	add_column :stories, :rank, :integer
	add_column :stories, :image, :string
	add_column :stories, :category, :string
  end
end
