class RemoveFlagsFromStories < ActiveRecord::Migration
  def up
    remove_column :stories, :flags
      end

  def down
    add_column :stories, :flags, :integer
  end
end
