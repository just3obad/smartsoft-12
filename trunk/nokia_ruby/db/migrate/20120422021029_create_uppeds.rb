class CreateUppeds < ActiveRecord::Migration
  def change
    create_table :uppeds do |t|
      t.integer :user_id
      t.integer :comment_id

      t.timestamps
    end
  end
end
