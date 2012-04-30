class RemoveStoryTypeFromStories < ActiveRecord::Migration
  def up
    remove_column :stories, :story_type
      end

  def down
    add_column :stories, :story_type, :integer
  end
end
