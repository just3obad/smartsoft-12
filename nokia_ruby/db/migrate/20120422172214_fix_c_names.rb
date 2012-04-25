class FixCNames < ActiveRecord::Migration
  def up
  rename_column :Verification_Codes, :user_id, :account_id
  end

  def down
  end
end
