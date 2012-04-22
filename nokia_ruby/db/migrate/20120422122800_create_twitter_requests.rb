class CreateTwitterRequests < ActiveRecord::Migration
  def change
    create_table :twitter_requests do |t|
      t.integer "user_id" 
      t.string :request_token
      t.string :request_secret

      t.timestamps
    end
  end
end
