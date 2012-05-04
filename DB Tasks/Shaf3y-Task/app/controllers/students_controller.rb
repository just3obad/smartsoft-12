class StudentsController < ApplicationController
def show
		@students = Student.find(params[:id])

    respond_to do |format|
      format.html #show.html.erb
      format.json { render json: @students }
    end
	end
end
