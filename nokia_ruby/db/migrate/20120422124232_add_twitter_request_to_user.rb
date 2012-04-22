class AddTwitterRequestToUser < ActiveRecord::Migration
  def up
    add_column('users', 'twitter_request_id', :string) 
  end

  def down
    remove_column('users', 'twitter_request_id') 
  end
end
