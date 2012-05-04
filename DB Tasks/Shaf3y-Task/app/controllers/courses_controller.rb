class CoursesController < ApplicationController
def show
		@courses = Course.find(params[:id])

    respond_to do |format|
      format.html #show.html.erb
      format.json { render json: @courses }
    end
	end
end
