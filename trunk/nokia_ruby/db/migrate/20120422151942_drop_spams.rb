class DropSpams < ActiveRecord::Migration
  def up
  drop_table :spams
  end

  def down
  end
end
