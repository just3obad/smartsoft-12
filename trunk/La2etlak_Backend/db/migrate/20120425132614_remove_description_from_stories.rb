class RemoveDescriptionFromStories < ActiveRecord::Migration
  def up
    remove_column :stories, :description
      end

  def down
    add_column :stories, :description, :string
  end
end
