class Settings < ActiveRecord::Base
  attr_accessible :key, :value
  # author: Gasser
  def configure_flags_threshold (int flags_number)
  end
end
