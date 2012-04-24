class LogsController < ApplicationController

 
 #index
 
  def index
        $datefilter = false
        if @emptydate!=true
         $admins = "true"
          $users = "true"
          $stories = "true"
          $interests = "true"
          @emptydate = false
        end
        $logs = Log.order("created_at DESC")
        respond_to do |format|
           format.html { render :template => 'logs/Logs' }
           format.xls { send_data $logs.to_xls, content_type: 'application/vnd.ms-excel', filename: 'LogFile.xls' }
         end
      end


 #insertion
  
  def create
      @log = Log.new(params[:log])
      if @log.save
          render :nothing => true 
          else
          render :nothing => true 
      end
  end
  
 #Filter
 
  def filter
    $admins = params[:admins]
    $users = params[:users]
    $stories = params[:stories]
    $interests = params[:interests]
    $typefilter = true
     render :template => 'logs/Logs'
  end
  
 #Period selection 
 
  def filter_by_date
     if(params[:from]==''&&params[:to]=='')
        render :template => 'logs/Logs'
        @emptydata = true
        return
      end
    if(params[:from]==''||params[:to]=='')
       flash[:error] = "Missing an input !"
      redirect_to logs_path
      return
    end
    if(!params[:from].match('(19|20)[0-9]{2}[- \/.](0[1-9]|1[012])[- \/.](0[1-9]|[12][0-9]|3[01])'))
      flash[:error] = "Wrong Format !"
       redirect_to logs_path
       return
     end
      begin
     if( Date.parse(params[:from])> Date.parse(params[:to]))
       flash[:error] = "The limits of  your dates are wrong !"
       redirect_to logs_path
       return
     end
       $typefilter = true
       $from_date = Date.parse(params[:from])
       $to_date = Date.parse(params[:to])
       #$logs =  Log.find(:all,:conditions=>['date(created_at) >= ? AND date(created_at) <= ?', $from_date,$to_date], :order => "created_at DESC")
        $datefilter = true
        render :template => 'logs/Logs'
     rescue 
        flash[:error] = "Invalid input !"
        redirect_to(:action => 'index')
     end  
  end
 
 
 end
