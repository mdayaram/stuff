class StaticPagesController < ApplicationController
  skip_before_filter :login_required

  def about
  end
end
