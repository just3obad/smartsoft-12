class CreateGaheemAccounts < ActiveRecord::Migration
  def change
    create_table :gaheem_accounts do |t|
      t.string :email
      t.string :password
      t.integer :status

      t.timestamps
    end
  end
end
