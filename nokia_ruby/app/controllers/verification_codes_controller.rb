class VerificationCodesController < ApplicationController
                       respond_to :html,:json

  def verify

  @code = params[:code]
  @user = Users.find_by_id(params[:id])
    respond_to do |format|
      if @user.verifyAccount(@code)
      format.json { render json: @user, notice: "account succesfully verified" }
      else
      format.json { render json: @user.errors, status: :unprocessable_entity }
      end
    end
  end


end
