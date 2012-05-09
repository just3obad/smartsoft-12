class CreateStudentSubCourses < ActiveRecord::Migration
  def change
    create_table :student_sub_courses do |t|
    t.references :student 
	t.references :course
      t.timestamps
    end
  end
end
