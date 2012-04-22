class CreateFlags < ActiveRecord::Migration
  def change
    create_table :flags do |t|
      t.integer :user_id
      t.integer :story_id

      t.timestamps
    end
    
    add_index :flags, :user_id
    add-index :flags, :story_id
    add_index :downed, [:user_id, :story_id], unique: true
  end
end
