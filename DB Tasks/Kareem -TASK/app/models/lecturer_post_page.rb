class LecturerPostPage < ActiveRecord::Base
  # attr_accessible :title, :body
   belongs_to :lecturer , :class_name => "Lecturer"
  belongs_to :page , :class_name => "Page"
end
