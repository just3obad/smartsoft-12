class CreateUserAddInterests < ActiveRecord::Migration
  def change
    create_table :user_add_interests do |t|
      t.integer :user_id
      t.integer :interest_id

      t.timestamps
    end
    
    add_index :user_add_interests, :user_id
    add_index :user_add_interests, :interest_id
    add_index :user_add_interests, [:user_id, :interest_id], unique: true
  end
end
