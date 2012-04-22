class AddImageToInterests < ActiveRecord::Migration
  def change
    add_column :interests, :image, :string
  end
end
