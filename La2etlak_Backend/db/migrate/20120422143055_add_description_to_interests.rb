class AddDescriptionToInterests < ActiveRecord::Migration
  def change
    add_column :interests, :description, :string
  end
end
