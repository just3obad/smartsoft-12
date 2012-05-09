class StudentSubCoursesController < ApplicationController
  # GET /student_sub_courses
  # GET /student_sub_courses.json
  def index
    @student_sub_courses = StudentSubCourse.all

    respond_to do |format|
      format.html # index.html.erb
      format.json { render json: @student_sub_courses }
    end
  end

  # GET /student_sub_courses/1
  # GET /student_sub_courses/1.json
  def show
    @student_sub_course = StudentSubCourse.find(params[:id])

    respond_to do |format|
      format.html # show.html.erb
      format.json { render json: @student_sub_course }
    end
  end

  # GET /student_sub_courses/new
  # GET /student_sub_courses/new.json
  def new
    @student_sub_course = StudentSubCourse.new

    respond_to do |format|
      format.html # new.html.erb
      format.json { render json: @student_sub_course }
    end
  end

  # GET /student_sub_courses/1/edit
  def edit
    @student_sub_course = StudentSubCourse.find(params[:id])
  end

  # POST /student_sub_courses
  # POST /student_sub_courses.json
  def create
    @student_sub_course = StudentSubCourse.new(params[:student_sub_course])

    respond_to do |format|
      if @student_sub_course.save
        format.html { redirect_to @student_sub_course, notice: 'Student sub course was successfully created.' }
        format.json { render json: @student_sub_course, status: :created, location: @student_sub_course }
      else
        format.html { render action: "new" }
        format.json { render json: @student_sub_course.errors, status: :unprocessable_entity }
      end
    end
  end

  # PUT /student_sub_courses/1
  # PUT /student_sub_courses/1.json
  def update
    @student_sub_course = StudentSubCourse.find(params[:id])

    respond_to do |format|
      if @student_sub_course.update_attributes(params[:student_sub_course])
        format.html { redirect_to @student_sub_course, notice: 'Student sub course was successfully updated.' }
        format.json { head :no_content }
      else
        format.html { render action: "edit" }
        format.json { render json: @student_sub_course.errors, status: :unprocessable_entity }
      end
    end
  end

  # DELETE /student_sub_courses/1
  # DELETE /student_sub_courses/1.json
  def destroy
    @student_sub_course = StudentSubCourse.find(params[:id])
    @student_sub_course.destroy

    respond_to do |format|
      format.html { redirect_to student_sub_courses_url }
      format.json { head :no_content }
    end
  end
end
