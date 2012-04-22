class Admin < ActiveRecord::Base
  # attr_accessible :title, :body

  $NAME = /([a-zA-Z]+)(.*)/
  $USERNAME = /(\w+)(.*)/
  $EMAIL = /((?:\w+\.)*\w+@(?:[a-z\d]+[.-])*[a-z\d]+\.[a-z\d]+)(.*)/
end
