class Lecturer < ActiveRecord::Base
  # attr_accessible :title, :body
  has_many :lecturer_post_pages , class_name: "LecturerPostPage"
  has_many :courses , class_name: "Course"
end
