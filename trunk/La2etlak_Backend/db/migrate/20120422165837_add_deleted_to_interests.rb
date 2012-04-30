class AddDeletedToInterests < ActiveRecord::Migration
  def change
    add_column :interests, :deleted, :boolean
  end
end
