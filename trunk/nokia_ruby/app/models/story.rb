class Story < ActiveRecord::Base
  # attr_accessible :title, :body

  has_many :shares, dependent: :destroy
  has_many :users, through: :shares
end
