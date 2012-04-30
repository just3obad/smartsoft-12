class RemoveVerifiedFromUsers < ActiveRecord::Migration
  def up
    remove_column :users, :verified
      end

  def down
    add_column :users, :verified, :boolean
  end
end
