require 'test_helper'

class StudentPostPagesControllerTest < ActionController::TestCase
  setup do
    @student_post_page = student_post_pages(:one)
  end

  test "should get index" do
    get :index
    assert_response :success
    assert_not_nil assigns(:student_post_pages)
  end

  test "should get new" do
    get :new
    assert_response :success
  end

  test "should create student_post_page" do
    assert_difference('StudentPostPage.count') do
      post :create, student_post_page: {  }
    end

    assert_redirected_to student_post_page_path(assigns(:student_post_page))
  end

  test "should show student_post_page" do
    get :show, id: @student_post_page
    assert_response :success
  end

  test "should get edit" do
    get :edit, id: @student_post_page
    assert_response :success
  end

  test "should update student_post_page" do
    put :update, id: @student_post_page, student_post_page: {  }
    assert_redirected_to student_post_page_path(assigns(:student_post_page))
  end

  test "should destroy student_post_page" do
    assert_difference('StudentPostPage.count', -1) do
      delete :destroy, id: @student_post_page
    end

    assert_redirected_to student_post_pages_path
  end
end
