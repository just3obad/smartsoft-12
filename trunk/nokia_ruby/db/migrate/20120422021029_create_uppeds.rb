class CreateUppeds < ActiveRecord::Migration
  def change
    create_table :uppeds do |t|
      t.integer :user_id
      t.integer :comment_id

      t.timestamps
    end
    
    add_index :uppeds, :user_id
    add_index :uppeds, :comment_id
    add_index :uppeds, [:user_id, :comment_id], unique: true
  end
end
