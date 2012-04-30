class CreateUserLogIns < ActiveRecord::Migration
  def change
    create_table :user_log_ins do |t|
      t.integer :user_id

      t.timestamps
    end
    
    add_index :user_log_ins, :user_id
  end
end
