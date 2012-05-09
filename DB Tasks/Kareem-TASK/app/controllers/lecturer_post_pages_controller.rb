class LecturerPostPagesController < ApplicationController
  # GET /lecturer_post_pages
  # GET /lecturer_post_pages.json
  def index
    @lecturer_post_pages = LecturerPostPage.all

    respond_to do |format|
      format.html # index.html.erb
      format.json { render json: @lecturer_post_pages }
    end
  end

  # GET /lecturer_post_pages/1
  # GET /lecturer_post_pages/1.json
  def show
    @lecturer_post_page = LecturerPostPage.find(params[:id])

    respond_to do |format|
      format.html # show.html.erb
      format.json { render json: @lecturer_post_page }
    end
  end

  # GET /lecturer_post_pages/new
  # GET /lecturer_post_pages/new.json
  def new
    @lecturer_post_page = LecturerPostPage.new

    respond_to do |format|
      format.html # new.html.erb
      format.json { render json: @lecturer_post_page }
    end
  end

  # GET /lecturer_post_pages/1/edit
  def edit
    @lecturer_post_page = LecturerPostPage.find(params[:id])
  end

  # POST /lecturer_post_pages
  # POST /lecturer_post_pages.json
  def create
    @lecturer_post_page = LecturerPostPage.new(params[:lecturer_post_page])

    respond_to do |format|
      if @lecturer_post_page.save
        format.html { redirect_to @lecturer_post_page, notice: 'Lecturer post page was successfully created.' }
        format.json { render json: @lecturer_post_page, status: :created, location: @lecturer_post_page }
      else
        format.html { render action: "new" }
        format.json { render json: @lecturer_post_page.errors, status: :unprocessable_entity }
      end
    end
  end

  # PUT /lecturer_post_pages/1
  # PUT /lecturer_post_pages/1.json
  def update
    @lecturer_post_page = LecturerPostPage.find(params[:id])

    respond_to do |format|
      if @lecturer_post_page.update_attributes(params[:lecturer_post_page])
        format.html { redirect_to @lecturer_post_page, notice: 'Lecturer post page was successfully updated.' }
        format.json { head :no_content }
      else
        format.html { render action: "edit" }
        format.json { render json: @lecturer_post_page.errors, status: :unprocessable_entity }
      end
    end
  end

  # DELETE /lecturer_post_pages/1
  # DELETE /lecturer_post_pages/1.json
  def destroy
    @lecturer_post_page = LecturerPostPage.find(params[:id])
    @lecturer_post_page.destroy

    respond_to do |format|
      format.html { redirect_to lecturer_post_pages_url }
      format.json { head :no_content }
    end
  end
end
