class CreateUsers < ActiveRecord::Migration
  def change
    create_table :users do |t|
      t.integer :id
      t.string :username
      t.string :full_name
      t.string :role
      t.integer :points
      t.string :password_hash
      t.string :password_salt

      t.timestamps
    end
  end
end
