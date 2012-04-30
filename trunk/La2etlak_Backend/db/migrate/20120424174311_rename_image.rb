class RenameImage < ActiveRecord::Migration
  def self.up
    rename_column :stories, :image, :media_link
  end

  def self.down
    rename_column :stories, :media_link, :image
  end
end
