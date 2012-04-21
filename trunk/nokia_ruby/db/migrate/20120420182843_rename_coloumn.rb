class RenameColoumn < ActiveRecord::Migration
  def up
	rename_column :stories, :description, :title
  end

  def down
  end
end
