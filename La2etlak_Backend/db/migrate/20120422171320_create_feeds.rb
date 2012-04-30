class CreateFeeds < ActiveRecord::Migration
  def change
    create_table :feeds do |t|
      t.string :link
      t.integer :interest_id

      t.timestamps
    end
    
    add_index :feeds, :interest_id
    add_index :feeds, :link
  end
end
