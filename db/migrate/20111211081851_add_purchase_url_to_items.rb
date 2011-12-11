class AddPurchaseUrlToItems < ActiveRecord::Migration
  def change
    add_column :items, :purchase_url, :string
  end
end
