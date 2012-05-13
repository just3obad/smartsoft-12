class Settings < ActiveRecord::Base
  attr_accessible :key, :value
  # author: Gasser
  def configure_flags_threshold (flags_number)
  end
end
