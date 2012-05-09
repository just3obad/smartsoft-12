require 'test_helper'

class LecturerPostPagesControllerTest < ActionController::TestCase
  setup do
    @lecturer_post_page = lecturer_post_pages(:one)
  end

  test "should get index" do
    get :index
    assert_response :success
    assert_not_nil assigns(:lecturer_post_pages)
  end

  test "should get new" do
    get :new
    assert_response :success
  end

  test "should create lecturer_post_page" do
    assert_difference('LecturerPostPage.count') do
      post :create, lecturer_post_page: {  }
    end

    assert_redirected_to lecturer_post_page_path(assigns(:lecturer_post_page))
  end

  test "should show lecturer_post_page" do
    get :show, id: @lecturer_post_page
    assert_response :success
  end

  test "should get edit" do
    get :edit, id: @lecturer_post_page
    assert_response :success
  end

  test "should update lecturer_post_page" do
    put :update, id: @lecturer_post_page, lecturer_post_page: {  }
    assert_redirected_to lecturer_post_page_path(assigns(:lecturer_post_page))
  end

  test "should destroy lecturer_post_page" do
    assert_difference('LecturerPostPage.count', -1) do
      delete :destroy, id: @lecturer_post_page
    end

    assert_redirected_to lecturer_post_pages_path
  end
end
