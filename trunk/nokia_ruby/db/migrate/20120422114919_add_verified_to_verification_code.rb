class AddVerifiedToVerificationCode < ActiveRecord::Migration
  def change
    add_column :verification_codes, :verified, :boolean
  end
end
