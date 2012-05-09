class CreateStudentPostPages < ActiveRecord::Migration
  def change
    create_table :student_post_pages do |t|
	t.references :student 
	t.references :page	
     	 t.timestamps
    end
  end
end
