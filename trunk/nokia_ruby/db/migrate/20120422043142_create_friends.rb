class CreateFriends < ActiveRecord::Migration
  def change
    create_table :friends do |t|
      t.integer :sender
      t.integer :receiver
      t.integer :stat

      t.timestamps
    end
  end
end
