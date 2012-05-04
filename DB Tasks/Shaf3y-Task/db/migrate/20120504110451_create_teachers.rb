class CreateTeachers < ActiveRecord::Migration
  def change
    create_table :teachers do |t|
		t.string "first_name", :limit=>25
		t.string "last_name", :limit=>25
		t.string "email", :default=>"", :null=>false
      t.timestamps
    end
  end
end
