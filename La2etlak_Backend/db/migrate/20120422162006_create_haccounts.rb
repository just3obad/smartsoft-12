class CreateHaccounts < ActiveRecord::Migration
  def change
    create_table :haccounts do |t|
      t.integer :user_id
      t.string :email
      t.string :password

      t.timestamps
    end
    
    add_index :haccounts, :user_id
    add_index :haccounts, :email
    add_index :haccounts, :password
  end
end
