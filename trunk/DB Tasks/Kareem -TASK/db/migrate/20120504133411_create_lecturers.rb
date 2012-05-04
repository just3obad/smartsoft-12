class CreateLecturers < ActiveRecord::Migration
  def change
    create_table :lecturers do |t|

      t.timestamps
    end
  end
end
