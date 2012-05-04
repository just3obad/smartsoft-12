class StudentsCourses < ActiveRecord::Migration
  def up
		create_table :students_courses, :id => false do |t|
			t.integer "student_id"
			t.integer "course_id"
  end
	add_index :students_courses, ["student_id", "course_id"]
end
  def down
	drop_table :students_courses
  end
end
