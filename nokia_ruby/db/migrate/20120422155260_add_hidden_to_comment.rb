class AddHiddenToComment < ActiveRecord::Migration
  def change
    add_column :comments, :hidden, :bool
  end
end
