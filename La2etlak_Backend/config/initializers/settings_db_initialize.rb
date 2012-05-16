Admin_Settings.destroy_all
Admin_Settings.create!(:key=>"flags_thershold", :value=>30)

# 0 false
# 1 true
Admin_Settings.create!(:key=>"auto_hiding_stories", :value=>0)