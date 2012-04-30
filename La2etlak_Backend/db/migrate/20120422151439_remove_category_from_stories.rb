class RemoveCategoryFromStories < ActiveRecord::Migration
  def up
    remove_column :stories, :category
      end

  def down
    add_column :stories, :category, :string
  end
end
