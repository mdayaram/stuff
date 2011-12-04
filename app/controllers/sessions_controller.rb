class SessionsController < ApplicationController

  def new
  end

  def create
    @user = User.authenticate(params[:username], params[:password])
    if @user
      set_session (@user)
    end

    respond_to do |format|
      format.html # create.html.erb
      format.json { render json: @user }
    end
  end

  def destroy
    clear_session()
  end

end

