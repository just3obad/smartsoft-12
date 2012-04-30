class CreateComments < ActiveRecord::Migration
  def change
    create_table :comments do |t|
      t.string :content
      t.integer :story_id
      t.integer :user_id
      t.boolean :hidden

      t.timestamps
    end
    
    add_index :comments, :story_id
    add_index :comments, :user_id
    add_index :comments, :hidden
    add_index :comments, :content
  end
end
