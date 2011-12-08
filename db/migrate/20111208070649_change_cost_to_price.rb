class ChangeCostToPrice < ActiveRecord::Migration
  def up
    remove_column :items, :cost
    add_column :items, :price, :decimal, :precision => 16, :scale => 2
  end

  def down
    add_column :items, :cost, :decimal
    remove_column :items, :price
  end
end
