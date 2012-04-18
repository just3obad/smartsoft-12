class User < ActiveRecord::Base
  # attr_accessible :title, :body

  has_many :shares, dependent: :destroy
  has_many :stories, through: :shares

   def share!(storyid)
     shares.create!(story_id: storyid)
  end
end
