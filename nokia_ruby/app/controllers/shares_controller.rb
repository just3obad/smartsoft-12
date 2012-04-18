class SharesController < ApplicationController
 def index
  @shares=Share.all
 end
end
