class Page < ActiveRecord::Base
  attr_accessible :name
 has_many :student_post_pages , class_name: "StudentPostPage"
  belongs_to :course ,class_name: "Course"
  has_many :lecturer_post_pages , class_name: "LecturerPostPage"
end
