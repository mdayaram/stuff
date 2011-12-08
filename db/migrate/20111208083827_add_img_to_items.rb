class AddImgToItems < ActiveRecord::Migration
  def change
    add_column :items, :img_file_name, :string
    add_column :items, :img_content_type, :string
    add_column :items, :img_file_size, :integer
    add_column :items, :img_updated_at, :datetime
    remove_column :items, :img_url
  end
end
