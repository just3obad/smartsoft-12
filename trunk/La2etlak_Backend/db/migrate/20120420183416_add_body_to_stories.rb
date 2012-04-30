class AddBodyToStories < ActiveRecord::Migration
  def change
	add_column :stories, :body, :string
  end
end
