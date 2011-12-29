class Item < ActiveRecord::Base
  belongs_to :submitter, :class_name => 'User', :foreign_key => :submitted_by
  belongs_to :approver, :class_name => 'User', :foreign_key => :approved_by
  belongs_to :purchaser, :class_name => 'User', :foreign_key => :purchased_by

  before_validation :set_defaults
  
  Item_Statuses = { 
    :available => 'Available', 
    :purchased => 'Purchased', 
    :received => 'Received' 
  }

  has_attached_file :img, :styles => { :small => "150x150>", :large => "400x400>" }
  validates_attachment_presence :img
  validates_attachment_size :img, :less_than => 20.megabytes
  validates_attachment_content_type :img, 
    :content_type => ['image/png', 'image/jpeg', 'image/gif', 'image/tiff']

  validates_presence_of :title, :description, :submitted_by, :price
  validates_presence_of :status, :date_submitted, :purchase_url
  validates_inclusion_of :status, :in => Item_Statuses.values
  validates_numericality_of :price, :greater_than_or_equal_to => 0
  validates_format_of :purchase_url, :with => URI::regexp(%w(http https))

  def self.available
    where(:status => Item_Statuses[:available])
  end

  def self.purchased
    where(:status => Item_Statuses[:purchased])
  end

  def set_defaults()
    self.status = Item_Statuses[:available]
    self.date_submitted = Time.now
  end

  def purchase(user)
    if user.blank? then return false end
    User.transaction do
      count = Item.update_all(
        { :purchased_by => user.id, :date_purchased => Time.now, :status => Item_Statuses[:purchased] }, 
        { :id => self.id, :status => Item_Statuses[:available] })
      if count == 1
        user.update_attribute(:points, user.points + (self.price * 100))
      else return false end
    end
  end
end

