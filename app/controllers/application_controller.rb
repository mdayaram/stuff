class ApplicationController < ActionController::Base
  protect_from_forgery
  before_filter :login_required  
  helper_method :current_user

  private

  def current_user
    @current_user ||= User.find(session[:user_id]) if session[:user_id]
  end

  def set_session (user)
    session[:user_id] = user.id if user.present?
    @current_user = user
  end

  def clear_session
    session[:user_id] = nil
    @current_user = nil
  end

  def login_required
    if session[:user_id].present? then return true end
    flash.now[:alert] = 'You gotta log in for that, buddy...'
    session[:return_to] = request.url
    redirect_to login_path
    return false
  end

  def redirect_to_stored
    if return_to = session[:return_to]
      session[:return_to] = nil
      redirect_to return_to
    else
      redirect_to root_path
    end
  end
end

