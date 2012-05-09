class Student < ActiveRecord::Base
  attr_accessible :name
has_many :student_post_pages , class_name: "StudentPostPage"
has_many :student_sub_pages , class_name: "StudentSubPage"
end
