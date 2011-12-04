class SessionsController < ApplicationController

  def new
  end

  def create
    @user = User.authenticate(params[:username], params[:password])
    if @user
      set_session (@user)
      flash[:message] = 'Logged in!'
      redirect_to_stored
    else
      flash[:alert] = 'Nope, wrong.  Try again...'
      render 'new'
    end
  end

  def destroy
    clear_session()
    flash[:message] = 'Successfully logged out.'
    redirect_to root_path
  end

end

