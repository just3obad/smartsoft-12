class CreateFriends < ActiveRecord::Migration
  def change
    create_table :friends do |t|
      t.integer :sender
      t.integer :receiver
      t.integer :status
      t.boolean :is_blocked

      t.timestamps
    end
  end
end
