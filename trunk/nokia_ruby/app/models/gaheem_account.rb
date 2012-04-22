class GaheemAccount < ActiveRecord::Base
  attr_accessible :email, :password,

  has_one :verification_code

  email_regex = /\A[\w+\-.]+@[a-z\d\-.]+\.[a-z]+\z/i
  validates :email, :presence => true,
                    :format=> {:with => email_regex },
                    :uniqueness => { :case_sensitive => false}


  
  # this methods generates a verification code for the user and adds an entry to Verification_Code
  def generateVerificationCode?()
  @verification_code = VerificationCode.find_by_account_id(self.id)
      if @verification_code.nil? then
      VerificationCode.create :code=>( (0..9).to_a + ('a'..'z').to_a).shuffle[0..3].join,:account_id=>self.id, :verified=>false
      return true		
    else 			
      return false		
    end
  end 
  
  #This methods verfies the account
  def verifyAccount?(verCode)
    @verEntry = VerificationCode.find_by_account_id(self.id)
    if @verEntry.code == verCode then
      @verEntry.update_attributes(verified: true)
      return true
    else 
      return false
    end
  end


end
