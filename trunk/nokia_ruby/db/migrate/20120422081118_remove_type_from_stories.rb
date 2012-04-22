class RemoveTypeFromStories < ActiveRecord::Migration
  def up
    remove_column :stories, :type
      end

  def down
    add_column :stories, :type, :int
  end
end
