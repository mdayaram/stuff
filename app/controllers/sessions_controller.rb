class SessionsController < ApplicationController
  skip_before_filter :login_required, :only => [:create, :new]

  def new
    @session = Session.new
  end

  def create
    user = params[:session][:username]
    pass = params[:session][:password]
    @user = User.authenticate(user, pass)
    if @user
      set_session (@user)
      flash[:message] = 'Logged in!'
      redirect_to_stored
    else
      @session = Session.new
      @session.username = user
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

