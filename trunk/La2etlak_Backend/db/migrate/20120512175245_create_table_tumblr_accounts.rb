class CreateTableTumblrAccounts < ActiveRecord::Migration
  def up
    create_table :tumblr_accounts do |t|
      t.integer :user_id
      t.string :consumer_key
      t.string :secret_key
          
      t.timestamps
    end
  end

  def down
   drop_table :tumblr_accounts
  end
end
