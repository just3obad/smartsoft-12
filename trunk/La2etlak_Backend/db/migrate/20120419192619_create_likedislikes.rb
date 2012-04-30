class CreateLikedislikes < ActiveRecord::Migration
  def change
    create_table :likedislikes do |t|
      t.integer :user_id
      t.integer :story_id
      t.integer :action

      t.timestamps
    end
    
    add_index :likedislikes, :user_id
    add_index :likedislikes, :story_id
    add_index :likedislikes, :action
    add_index :likedislikes, [:user_id, :story_id], unique: true
  end
end
