class RenameAccountId < ActiveRecord::Migration
  def up
    rename_column :Verification_Codes, :account_id, :user_id
  end

  def down
    rename_column :Verification_Codes, :user_id, :account_id
  end
end
