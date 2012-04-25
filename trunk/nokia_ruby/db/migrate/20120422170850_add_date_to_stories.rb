class AddDateToStories < ActiveRecord::Migration
  def change
    add_column :stories, :date, :date
  end
end
