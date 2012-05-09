class StudentPostPagesController < ApplicationController
  # GET /student_post_pages
  # GET /student_post_pages.json
  def index
    @student_post_pages = StudentPostPage.all

    respond_to do |format|
      format.html # index.html.erb
      format.json { render json: @student_post_pages }
    end
  end

  # GET /student_post_pages/1
  # GET /student_post_pages/1.json
  def show
    @student_post_page = StudentPostPage.find(params[:id])

    respond_to do |format|
      format.html # show.html.erb
      format.json { render json: @student_post_page }
    end
  end

  # GET /student_post_pages/new
  # GET /student_post_pages/new.json
  def new
    @student_post_page = StudentPostPage.new

    respond_to do |format|
      format.html # new.html.erb
      format.json { render json: @student_post_page }
    end
  end

  # GET /student_post_pages/1/edit
  def edit
    @student_post_page = StudentPostPage.find(params[:id])
  end

  # POST /student_post_pages
  # POST /student_post_pages.json
  def create
    @student_post_page = StudentPostPage.new(params[:student_post_page])

    respond_to do |format|
      if @student_post_page.save
        format.html { redirect_to @student_post_page, notice: 'Student post page was successfully created.' }
        format.json { render json: @student_post_page, status: :created, location: @student_post_page }
      else
        format.html { render action: "new" }
        format.json { render json: @student_post_page.errors, status: :unprocessable_entity }
      end
    end
  end

  # PUT /student_post_pages/1
  # PUT /student_post_pages/1.json
  def update
    @student_post_page = StudentPostPage.find(params[:id])

    respond_to do |format|
      if @student_post_page.update_attributes(params[:student_post_page])
        format.html { redirect_to @student_post_page, notice: 'Student post page was successfully updated.' }
        format.json { head :no_content }
      else
        format.html { render action: "edit" }
        format.json { render json: @student_post_page.errors, status: :unprocessable_entity }
      end
    end
  end

  # DELETE /student_post_pages/1
  # DELETE /student_post_pages/1.json
  def destroy
    @student_post_page = StudentPostPage.find(params[:id])
    @student_post_page.destroy

    respond_to do |format|
      format.html { redirect_to student_post_pages_url }
      format.json { head :no_content }
    end
  end
end
