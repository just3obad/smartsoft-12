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

ActiveRecord::Schema.define(:version => 20120424141431) do

  create_table "admins", :force => true do |t|
    t.datetime "created_at", :null => false
    t.datetime "updated_at", :null => false
  end

  create_table "comments", :force => true do |t|
    t.string   "content"
    t.integer  "story_id"
    t.integer  "user_id"
    t.boolean  "hidden"
    t.datetime "created_at", :null => false
    t.datetime "updated_at", :null => false
  end

  create_table "downeds", :force => true do |t|
    t.integer  "user_id"
    t.integer  "comment_id"
    t.datetime "created_at", :null => false
    t.datetime "updated_at", :null => false
  end

  create_table "feeds", :force => true do |t|
    t.string   "link"
    t.integer  "interest_id"
    t.datetime "created_at",  :null => false
    t.datetime "updated_at",  :null => false
  end

  create_table "flags", :force => true do |t|
    t.integer  "user_id"
    t.integer  "story_id"
    t.datetime "created_at", :null => false
    t.datetime "updated_at", :null => false
  end

  create_table "friends", :force => true do |t|
    t.integer  "sender"
    t.integer  "receiver"
    t.integer  "stat"
    t.datetime "created_at", :null => false
    t.datetime "updated_at", :null => false
  end

  create_table "haccounts", :force => true do |t|
    t.integer  "user_id"
    t.string   "email"
    t.string   "password"
    t.datetime "created_at", :null => false
    t.datetime "updated_at", :null => false
  end

  create_table "interests", :force => true do |t|
    t.datetime "created_at",  :null => false
    t.datetime "updated_at",  :null => false
    t.string   "name"
    t.string   "image"
    t.string   "description"
    t.boolean  "deleted"
  end

  create_table "likedislikes", :force => true do |t|
    t.integer  "user_id"
    t.integer  "story_id"
    t.integer  "action"
    t.datetime "created_at", :null => false
    t.datetime "updated_at", :null => false
  end

  create_table "logs", :force => true do |t|
    t.integer  "loggingtype"
    t.integer  "user_id_1"
    t.integer  "user_id_2"
    t.integer  "admin_id"
    t.integer  "story_id"
    t.integer  "interest_id"
    t.string   "message"
    t.datetime "created_at",  :null => false
    t.datetime "updated_at",  :null => false
  end

  create_table "shares", :force => true do |t|
    t.integer  "story_id"
    t.integer  "user_id"
    t.datetime "created_at", :null => false
    t.datetime "updated_at", :null => false
  end

  add_index "shares", ["story_id"], :name => "index_shares_on_story_id"
  add_index "shares", ["user_id"], :name => "index_shares_on_user_id"

  create_table "stories", :force => true do |t|
    t.datetime "created_at",  :null => false
    t.datetime "updated_at",  :null => false
    t.string   "body"
    t.text     "content"
    t.boolean  "deleted"
    t.boolean  "hidden"
    t.integer  "interest_id"
    t.string   "title"
    t.date     "date"
    t.integer  "rank"
    t.string   "image"
  end

  create_table "twitter_accounts", :force => true do |t|
    t.string   "auth_token"
    t.string   "auth_secret"
    t.datetime "created_at",  :null => false
    t.datetime "updated_at",  :null => false
  end

  create_table "twitter_requests", :force => true do |t|
    t.integer  "user_id"
    t.string   "request_token"
    t.string   "request_secret"
    t.datetime "created_at",     :null => false
    t.datetime "updated_at",     :null => false
  end

  create_table "uppeds", :force => true do |t|
    t.integer  "user_id"
    t.integer  "comment_id"
    t.datetime "created_at", :null => false
    t.datetime "updated_at", :null => false
  end

  create_table "user_add_interests", :force => true do |t|
    t.integer  "user_id"
    t.integer  "interest_id"
    t.datetime "created_at",  :null => false
    t.datetime "updated_at",  :null => false
  end

  create_table "user_log_ins", :force => true do |t|
    t.integer  "user_id"
    t.datetime "created_at", :null => false
    t.datetime "updated_at", :null => false
  end

  create_table "users", :force => true do |t|
    t.datetime "created_at",         :null => false
    t.datetime "updated_at",         :null => false
    t.string   "name"
    t.string   "email"
    t.boolean  "deactivated"
    t.string   "twitter_account_id"
    t.string   "twitter_request_id"
    t.string   "first_name"
    t.string   "last_name"
    t.date     "date_of_birth"
  end

  create_table "verification_codes", :force => true do |t|
    t.string   "code"
    t.integer  "user_id"
    t.datetime "created_at", :null => false
    t.datetime "updated_at", :null => false
    t.boolean  "verified"
  end

end
