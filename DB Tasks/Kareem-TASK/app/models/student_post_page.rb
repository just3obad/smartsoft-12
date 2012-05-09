class StudentPostPage < ActiveRecord::Base
  # attr_accessible :title, :body
  belongs_to :student , :class_name => "Student"
  belongs_to :page , :class_name => "Page"
end
