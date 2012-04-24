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

ActiveRecord::Schema.define(:version => 20120424180751) do

  create_table "Verification_Codes", :force => true do |t|
    t.string   "code"
    t.integer  "account_id"
    t.datetime "created_at", :null => false
    t.datetime "updated_at", :null => false
    t.boolean  "verified"
  end

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

  add_index "comments", ["content"], :name => "index_comments_on_content"
  add_index "comments", ["hidden"], :name => "index_comments_on_hidden"
  add_index "comments", ["story_id"], :name => "index_comments_on_story_id"
  add_index "comments", ["user_id"], :name => "index_comments_on_user_id"

  create_table "downeds", :force => true do |t|
    t.integer  "user_id"
    t.integer  "comment_id"
    t.datetime "created_at", :null => false
    t.datetime "updated_at", :null => false
  end

  add_index "downeds", ["comment_id"], :name => "index_downeds_on_comment_id"
  add_index "downeds", ["user_id", "comment_id"], :name => "index_downeds_on_user_id_and_comment_id", :unique => true
  add_index "downeds", ["user_id"], :name => "index_downeds_on_user_id"

  create_table "feeds", :force => true do |t|
    t.string   "link"
    t.integer  "interest_id"
    t.datetime "created_at",  :null => false
    t.datetime "updated_at",  :null => false
  end

  add_index "feeds", ["interest_id"], :name => "index_feeds_on_interest_id"
  add_index "feeds", ["link"], :name => "index_feeds_on_link"

  create_table "flags", :force => true do |t|
    t.integer  "user_id"
    t.integer  "story_id"
    t.datetime "created_at", :null => false
    t.datetime "updated_at", :null => false
  end

  add_index "flags", ["story_id"], :name => "index_flags_on_story_id"
  add_index "flags", ["user_id", "story_id"], :name => "index_flags_on_user_id_and_story_id", :unique => true
  add_index "flags", ["user_id"], :name => "index_flags_on_user_id"

  create_table "friends", :force => true do |t|
    t.integer  "sender"
    t.integer  "receiver"
    t.integer  "stat"
    t.datetime "created_at", :null => false
    t.datetime "updated_at", :null => false
    t.boolean  "is_blocked"
  end

  add_index "friends", ["receiver"], :name => "index_friends_on_receiver"
  add_index "friends", ["sender", "receiver"], :name => "index_friends_on_sender_and_receiver", :unique => true
  add_index "friends", ["sender"], :name => "index_friends_on_sender"

  create_table "haccounts", :force => true do |t|
    t.integer  "user_id"
    t.string   "email"
    t.string   "password"
    t.datetime "created_at", :null => false
    t.datetime "updated_at", :null => false
  end

  add_index "haccounts", ["email"], :name => "index_haccounts_on_email"
  add_index "haccounts", ["password"], :name => "index_haccounts_on_password"
  add_index "haccounts", ["user_id"], :name => "index_haccounts_on_user_id"

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

  add_index "likedislikes", ["action"], :name => "index_likedislikes_on_action"
  add_index "likedislikes", ["story_id"], :name => "index_likedislikes_on_story_id"
  add_index "likedislikes", ["user_id", "story_id"], :name => "index_likedislikes_on_user_id_and_story_id", :unique => true
  add_index "likedislikes", ["user_id"], :name => "index_likedislikes_on_user_id"

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
    t.string   "description"
    t.integer  "interest_id"
    t.string   "title"
    t.date     "date"
    t.integer  "rank"
    t.string   "media_link"
    t.boolean  "is_blocked"
    t.string   "category"
    t.text     "content"
    t.boolean  "hidden"
    t.boolean  "deleted"
  end

  add_index "stories", ["interest_id"], :name => "index_stories_on_interest_id"

  create_table "twitter_accounts", :force => true do |t|
    t.integer  "user_id"
    t.string   "auth_token"
    t.string   "auth_secret"
    t.datetime "created_at",  :null => false
    t.datetime "updated_at",  :null => false
  end

  add_index "twitter_accounts", ["user_id"], :name => "index_twitter_accounts_on_user_id", :unique => true

  create_table "twitter_requests", :force => true do |t|
    t.integer  "user_id"
    t.string   "request_token"
    t.string   "request_secret"
    t.datetime "created_at",     :null => false
    t.datetime "updated_at",     :null => false
  end

  add_index "twitter_requests", ["user_id"], :name => "index_twitter_requests_on_user_id", :unique => true

  create_table "uppeds", :force => true do |t|
    t.integer  "user_id"
    t.integer  "comment_id"
    t.datetime "created_at", :null => false
    t.datetime "updated_at", :null => false
  end

  add_index "uppeds", ["comment_id"], :name => "index_uppeds_on_comment_id"
  add_index "uppeds", ["user_id", "comment_id"], :name => "index_uppeds_on_user_id_and_comment_id", :unique => true
  add_index "uppeds", ["user_id"], :name => "index_uppeds_on_user_id"

  create_table "user_add_interests", :force => true do |t|
    t.integer  "user_id"
    t.integer  "interest_id"
    t.datetime "created_at",  :null => false
    t.datetime "updated_at",  :null => false
  end

  add_index "user_add_interests", ["interest_id"], :name => "index_user_add_interests_on_interest_id"
  add_index "user_add_interests", ["user_id", "interest_id"], :name => "index_user_add_interests_on_user_id_and_interest_id", :unique => true
  add_index "user_add_interests", ["user_id"], :name => "index_user_add_interests_on_user_id"

  create_table "user_log_ins", :force => true do |t|
    t.integer  "user_id"
    t.datetime "created_at", :null => false
    t.datetime "updated_at", :null => false
  end

  add_index "user_log_ins", ["user_id"], :name => "index_user_log_ins_on_user_id"

  create_table "users", :force => true do |t|
    t.datetime "created_at",                       :null => false
    t.datetime "updated_at",                       :null => false
    t.string   "name"
    t.string   "email"
    t.boolean  "deactivated",   :default => false
    t.string   "first_name"
    t.string   "last_name"
    t.date     "date_of_birth"
  end

end
