class CreateShares < ActiveRecord::Migration
  def change
    create_table :shares do |t|
      t.integer :story_id
      t.integer :user_id

      t.timestamps
    end
    
    add_index :likedislikes, :user_id
    add_index :likedislikes, :story_id
  end
end
