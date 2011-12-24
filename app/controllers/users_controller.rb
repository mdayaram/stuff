class UsersController < ApplicationController
  skip_before_filter :login_required, :only => [:create, :new, :index]
  before_filter :admin_required, :only => 
    [:destroy, :promote, :edit, :update]

  # GET /users
  def index
    @users = User.all

    respond_to do |format|
      format.html # index.html.erb
    end
  end

  # GET /users/1
  def show
    @user = User.find(params[:id])

    respond_to do |format|
      format.html # show.html.erb
    end
  end

  # GET /users/new
  def new
    @user = User.new

    respond_to do |format|
      format.html # new.html.erb
    end
  end

  # GET /users/1/edit
  def edit
    @user = User.find(params[:id])
  end

  # POST /users
  def create
    @user = User.new(params[:user])

    if @user.save
      set_session(@user)
      redirect_to root_path, notice: 'Successfully signed up!'
    else
      render 'new'
    end
  end

  # PUT /users/1
  def update
    @user = User.find(params[:id])

    respond_to do |format|
      if @user.update_attributes(params[:user])
        format.html { redirect_to @user, notice: 'User was successfully updated.' }
      else
        format.html { render action: "edit" }
      end
    end
  end

  # PUT /users/1/promote
  def promote
    @user = User.find(params[:id])
    
    respond_to do |format|
      if current_user.make_admin(@user)
        format.html { redirect_to @user, notice: 'User has been promoted to admin.' }
      else
        format.html { redirect_to @user, alert: 'Could not promote user to admin.' }
      end
    end
  end

  # GET /users/1/changepass
  def changepass
    @user = current_user.dup
    respond_to do |format|
      format.html # changepass.html.erb
    end
  end

  # PUT /users/1/updatepass
  def updatepass
    puser = params[:user]
    @user = User.authenticate(puser[:username], puser[:password])
    
    if @user.blank?
      @user = current_user.dup
      @user.errors[:base] << 'Original password was incorrect. Try again?'
      return respond_to do |format|
        format.html { render action: "changepass" }
      end
    end

    @user.password = puser[:new_password]
    @user.password_confirmation = puser[:new_password_confirmation]

    respond_to do |format|
      if @user.update_password
        format.html { redirect_to @user, notice: 'Password has been updated.'}
      else
        format.html { render action: "changepass" }
      end
    end
  end

  # DELETE /users/1
  def destroy
    @user = User.find(params[:id])
    @user.destroy

    respond_to do |format|
      format.html { redirect_to users_url }
    end
  end
end
