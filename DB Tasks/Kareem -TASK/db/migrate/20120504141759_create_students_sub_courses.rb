class CreateStudentsSubCourses < ActiveRecord::Migration

  def up
 	create_table :student_post_course , :id => false do |t|
	t.integer "student_id"
	t.integer "course_id"
      end
   add_index :student_post_course , ["student_id" , "course_id"]
  end

  def down
     drop_table :student_post_course
  end
end
