class GaheemAccount < ActiveRecord::Base
  attr_accessible :email, :password, :status
end
