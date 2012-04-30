class AddTypeToStories < ActiveRecord::Migration
  def change
    add_column :stories, :type, :int
  end
end
