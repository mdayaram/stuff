# encoding: UTF-8
# This file is auto-generated from the current state of the database. Instead
# of editing this file, please use the migrations feature of Active Record to
# incrementally modify your database, and then regenerate this schema definition.
#
# Note that this schema.rb definition is the authoritative source for your
# database schema. If you need to create the application database on another
# system, you should be using db:schema:load, not running all the migrations
# from scratch. The latter is a flawed and unsustainable approach (the more migrations
# you'll amass, the slower it'll run and the greater likelihood for issues).
#
# It's strongly recommended to check this file into your version control system.

ActiveRecord::Schema.define(:version => 20111211081851) do

  create_table "items", :force => true do |t|
    t.string   "title"
    t.text     "description",      :limit => 255
    t.integer  "submitted_by"
    t.integer  "approved_by"
    t.string   "status"
    t.datetime "date_submitted"
    t.datetime "date_received"
    t.datetime "date_approved"
    t.datetime "created_at"
    t.datetime "updated_at"
    t.datetime "date_purchased"
    t.integer  "purchased_by"
    t.decimal  "price"
    t.string   "img_file_name"
    t.string   "img_content_type"
    t.integer  "img_file_size"
    t.datetime "img_updated_at"
    t.string   "purchase_url"
  end

  create_table "users", :force => true do |t|
    t.string   "username"
    t.string   "full_name"
    t.string   "role"
    t.integer  "points"
    t.string   "password_hash"
    t.string   "password_salt"
    t.datetime "created_at"
    t.datetime "updated_at"
  end

end
