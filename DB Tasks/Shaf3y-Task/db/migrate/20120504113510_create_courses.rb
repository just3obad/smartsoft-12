class CreateCourses < ActiveRecord::Migration
  def up
    create_table :courses do |t|
		t.integer "teacher_id"
		t.string "name"
      t.timestamps
    end
	add_index: courses, ["teacher_id"]
  end
  def down
    drop_table :courses
  end
end
