class Log < ActiveRecord::Base
  attr_accessible :admin_id, :interest_id, :loggingtype, :message, :story_id, :user_id_1, :user_id_2
  #used for rendering xls
  def as_xls(options = {})
    {
        "DateTime" => created_at.to_s,
        "Message" => message
    }
  end

  def self.get_All_Logs
     $datefilter = false
      if @emptydate!=true
       $admins = "true"
        $users = "true"
        $stories = "true"
        $interests = "true"
        @emptydate = false
      end
      $logs = order("created_at DESC")
  end
  
  def self.add_New(log)
     @log = Log.new(log)
     @log.save
  end
  
  def self.get_log_for_user(id,time)
       out1 = Log.find_by_user_id_1(id)
       out2 = Log.find_by_user_id_2(id)
       if(out1 == nil or out2 == nil)
         if(out1 == nil)
           out = out2
         end
         if(out2 == nil)
           out = out1
         end
       else
         out = out1 + out2
       end
       if(out !=nil) 
         out.where("created_at > ?",time)
         out.sort_by { |obj| obj.created_at }.reverse
       end
       return out
    end
end
