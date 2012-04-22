class CreateFriends < ActiveRecord::Migration
  def change
    create_table :friends do |t|
      t.integer :sender
      t.integer :receiver
      t.integer :stat

      t.timestamps
    end
    
    add_index :friends, :sender
    add_index :friends, :receiver
    add_index :friends, [:sender, :receiver], unique: true
  end
end
