class Log < ActiveRecord::Base
  attr_accessible :admin_id, :interest_id, :loggingtype, :message, :story_id, :user_id_1, :user_id_2
end
