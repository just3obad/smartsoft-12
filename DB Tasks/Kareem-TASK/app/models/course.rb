class Course < ActiveRecord::Base
  attr_accessible :name
  has_one :page , class_name: "Page"
  belongs_to :lecturer , class_name: "Lecturer"
  has_many :student_sub_courses , class_name: "StudentSubCourse"
end
