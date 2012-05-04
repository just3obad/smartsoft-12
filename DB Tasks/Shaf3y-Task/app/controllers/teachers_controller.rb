class TeachersController < ApplicationController
	def show
		@teachers = Teacher.find(params[:id])

    respond_to do |format|
      format.html #show.html.erb
      format.json { render json: @teachers }
    end
	end
end
