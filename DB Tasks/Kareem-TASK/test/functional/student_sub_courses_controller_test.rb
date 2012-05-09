require 'test_helper'

class StudentSubCoursesControllerTest < ActionController::TestCase
  setup do
    @student_sub_course = student_sub_courses(:one)
  end

  test "should get index" do
    get :index
    assert_response :success
    assert_not_nil assigns(:student_sub_courses)
  end

  test "should get new" do
    get :new
    assert_response :success
  end

  test "should create student_sub_course" do
    assert_difference('StudentSubCourse.count') do
      post :create, student_sub_course: {  }
    end

    assert_redirected_to student_sub_course_path(assigns(:student_sub_course))
  end

  test "should show student_sub_course" do
    get :show, id: @student_sub_course
    assert_response :success
  end

  test "should get edit" do
    get :edit, id: @student_sub_course
    assert_response :success
  end

  test "should update student_sub_course" do
    put :update, id: @student_sub_course, student_sub_course: {  }
    assert_redirected_to student_sub_course_path(assigns(:student_sub_course))
  end

  test "should destroy student_sub_course" do
    assert_difference('StudentSubCourse.count', -1) do
      delete :destroy, id: @student_sub_course
    end

    assert_redirected_to student_sub_courses_path
  end
end
