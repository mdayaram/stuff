class CreateItems < ActiveRecord::Migration
  def change
    create_table :items do |t|
      t.integer :id
      t.string :img_url
      t.string :title
      t.string :description
      t.integer :submitted_by
      t.integer :approved_by
      t.decimal :cost
      t.string :status
      t.date :date_submitted
      t.date :date_received
      t.date :date_approved

      t.timestamps
    end
  end
end
