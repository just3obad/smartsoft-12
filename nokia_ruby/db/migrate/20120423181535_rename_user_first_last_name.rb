class RenameUserFirstLastName < ActiveRecord::Migration
  def up
    rename_column :users, :firstname, :first_name
    rename_column :users, :lastname, :last_name
  end

  def down
    rename_column :users, :first_name, :firstname
    rename_column :users, :last_name, :lasttname
  end
end
