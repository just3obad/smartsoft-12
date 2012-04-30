class CreateTwitterAccounts < ActiveRecord::Migration
  def change
    create_table :twitter_accounts do |t|
      t.integer "user_id"
      t.string :auth_token
      t.string :auth_secret

      t.timestamps
    end
    add_index("twitter_accounts", "user_id", :unique => true)
  end
end
