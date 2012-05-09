class Comment < ActiveRecord::Base
  attr_accessible :content
  belongs_to :user
  belongs_to :story
  has_many :comment_up_downs
end
