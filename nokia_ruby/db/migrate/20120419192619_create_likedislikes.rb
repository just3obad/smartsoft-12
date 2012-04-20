class CreateLikedislikes < ActiveRecord::Migration
  def change
    create_table :likedislikes do |t|
      t.integer :user_id
      t.integer :story_id
      t.integer :action

      t.timestamps
    end
  end
end
