class CreateShares < ActiveRecord::Migration
  def change
    create_table :shares do |t|
      t.integer :story_id
      t.integer :user_id

      t.timestamps
    end
    add_index :shares, :story_id
    add_index :shares, :user_id
  end
end
