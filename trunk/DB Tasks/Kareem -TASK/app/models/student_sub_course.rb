class StudentSubCourse < ActiveRecord::Base
  # attr_accessible :title, :body
	 belongs_to :student , :class_name => "Student"
  belongs_to :course , :class_name => "Course"
end
