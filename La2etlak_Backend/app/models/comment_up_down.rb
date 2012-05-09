class CommentUpDown < ActiveRecord::Base
  attr_accessible :action
  belongs_to :user
  belongs_to :comment
end
