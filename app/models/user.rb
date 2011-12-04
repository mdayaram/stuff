class User < ActiveRecord::Base
  attr_accessible :username, :full_name, :role, :points, :password, :password_confirmation
  attr_accessor :password
  before_save :set_defaults_and_password

  User_Roles = { :admin => 'chocolate', :standard => 'vanilla' }

  validates_uniqueness_of :username, :case_sensitive => false
  validates_length_of :username, :within => 3..256
  validates_length_of :full_name, :within => 3..256
  validates_presence_of :username, :full_name
  validates_inclusion_of :role, :in => User_Roles, :allow_blank => true
  validates_numericality_of :points, :on => :save
  validates_numericality_of :points, :on => :update

  validates_presence_of :password, :on => :create
  validates_confirmation_of :password
  validates_length_of :password, :within => 5..128

  def self.authenticate (username, password)
    user = User.first :conditions => { :username => username }
    if (user.blank?) then nil end
    phash = User.hash_pass(password, user.password_salt);
    phash == user.password_hash ? user : nil
  end

  private

  def set_defaults_and_password
    self.role ||= User_Roles[:standard]
    self.points ||= 0
    
    if self.password.present?
      self.password_salt = User.gen_salt()
      self.password_hash = User.hash_pass(self.password, self.password_salt)
    end
  end

  def self.hash_pass (password, salt)
    BCrypt::Engine.hash_secret(password, salt)
  end

  def self.gen_salt
    BCrypt::Engine.generate_salt
  end
end

