class DropHaccount < ActiveRecord::Migration
  def up
    drop_table :HAccount
  end

  def down
  end
end
