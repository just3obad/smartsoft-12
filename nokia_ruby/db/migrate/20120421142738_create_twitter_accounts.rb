class CreateTwitterAccounts < ActiveRecord::Migration
  def change
    create_table :twitter_accounts do |t|
      t.string :auth_token
      t.string :auth_secret

      t.timestamps
    end
  end
end
