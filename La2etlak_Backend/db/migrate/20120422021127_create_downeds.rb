class CreateDowneds < ActiveRecord::Migration
  def change
    create_table :downeds do |t|
      t.integer :user_id
      t.integer :comment_id

      t.timestamps
    end
    
    add_index :downeds, :user_id
    add_index :downeds, :comment_id
    add_index :downeds, [:user_id, :comment_id], unique: true
  end
end
