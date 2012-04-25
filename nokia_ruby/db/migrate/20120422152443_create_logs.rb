class CreateLogs < ActiveRecord::Migration
  def change
    create_table :logs do |t|
      t.integer :loggingtype
      t.integer :user_id_1
      t.integer :user_id_2
      t.integer :admin_id
      t.integer :story_id
      t.integer :interest_id
      t.string :message

      t.timestamps
    end
  end
end
