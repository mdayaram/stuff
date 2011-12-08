class UpdateTypesOfItemColumns < ActiveRecord::Migration
  def up
    change_column :items, :date_submitted, :datetime
    change_column :items, :date_approved, :datetime
    change_column :items, :date_received, :datetime
    add_column :items, :date_purchased, :datetime
    add_column :items, :purchased_by, :integer
    change_column :items, :description, :text
  end

  def down
    remove_column :items, :date_purchased
    remove_column :items, :purchased_by
    change_column :items, :date_submitted, :date
    change_column :items, :date_approved, :date
    change_column :items, :date_received, :date
    change_column :items, :description, :string
  end
end
