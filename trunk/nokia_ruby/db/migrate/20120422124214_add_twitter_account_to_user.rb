class AddTwitterAccountToUser < ActiveRecord::Migration
  def up
    add_column('users', 'twitter_account_id', :string) 
  end

  def down
    remove_column('users', 'twitter_account_id') 
  end
end
