class AddFlagsToStories < ActiveRecord::Migration
  def change
    add_column :stories, :flags, :integer
  end
end
