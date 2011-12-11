class User < ActiveRecord::Base
  has_many :approved_items, :class_name => 'Item', :foreign_key => 'approved_by'
  has_many :submitted_items, :class_name => 'Item', :foreign_key => 'submitted_by'
  has_many :purchased_items, :class_name => 'Item', :foreign_key => 'purchased_by'

  attr_accessible :username, :full_name, :password, :password_confirmation
  attr_accessor :password
  before_save :set_defaults_and_password

  User_Roles = { :admin => 'choco', :standard => 'vanilla' }

  validates_uniqueness_of :username, :case_sensitive => false
  validates_length_of :username, :within => 3..256
  validates_length_of :full_name, :within => 3..256
  validates_presence_of :username, :full_name
  validates_inclusion_of :role, :in => User_Roles.values, :allow_blank => true
  validates_numericality_of :points, :on => :save, :greater_than_or_equal_to => 0
  validates_numericality_of :points, :on => :update, :greater_than_or_equal_to => 0

  validates_presence_of :password, :on => :create
  validates_confirmation_of :password
  validates_length_of :password, :within => 5..128

  def self.authenticate (username, password)
    user = User.first :conditions => { :username => username }
    if (user.blank?) then return nil end
    phash = User.hash_pass(password, user.password_salt);
    phash == user.password_hash ? user : nil
  end

  def is_admin
    self.role == User_Roles[:admin] || self.id <= 1
  end

  def make_admin (user)
    if self.role != User_Roles[:admin] and self.id > 1 or user.blank? then return false end
    user.update_attribute(:role, User_Roles[:admin])
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

