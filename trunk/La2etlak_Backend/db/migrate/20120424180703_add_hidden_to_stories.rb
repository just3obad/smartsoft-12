class AddHiddenToStories < ActiveRecord::Migration
  def change
    add_column :stories, :hidden, :boolean
  end
end
