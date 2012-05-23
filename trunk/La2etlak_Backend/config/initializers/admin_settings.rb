def initialize_admin_settings
	if Admin_Settings.find_by_key("flags_threshold").nil?
		Admin_Settings.create!(:key=>"flags_threshold", :value=>10)
		Admin_Settings.create!(:key=>"auto_hiding", :value=>1)
		Admin_Settings.create!(:key=>"statistics_time_span", :value=>30)
	end
end