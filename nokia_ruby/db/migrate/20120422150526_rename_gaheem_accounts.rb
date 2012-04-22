class RenameGaheemAccounts < ActiveRecord::Migration
  def up
    rename_table :gaheem_accounts, :HAccount
  end

  def down
  end
end
