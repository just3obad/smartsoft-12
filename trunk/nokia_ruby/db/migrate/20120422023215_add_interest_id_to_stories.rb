class AddInterestIdToStories < ActiveRecord::Migration
  def change
    add_column :stories, :interest_id, :int
  end
end
