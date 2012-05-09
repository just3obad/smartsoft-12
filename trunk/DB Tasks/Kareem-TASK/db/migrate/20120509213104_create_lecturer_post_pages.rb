class CreateLecturerPostPages < ActiveRecord::Migration
  def change
    create_table :lecturer_post_pages do |t|
	   t.references :lecturer 
	t.references :page
      t.timestamps
    end
  end
end
