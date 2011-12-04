class ApplicationController < ActionController::Base
  protect_from_forgery
  
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
    session[:user_id].present?
  end

end
