class Haccount < ActiveRecord::Base
  attr_accessible :email, :password, :user_id

  belongs_to :user, class_name: "User"
  has_one :verification_code

  validates :email, :presence => true,
  :format=> {:with => email_regex },
  :uniqueness => { :case_sensitive => false}
  validates :user_id, uniqueness =>true

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

 
  #This methods resets the verification code
  def resendCode?
    @verEntry = VerificationCode.find_by_account_id(self.id)
    if @varEntry.verified
       return false
    else
    @verEntry.update_attributes(code: ( (0..9).to_a + ('a'..'z').to_a).shuffle[0..3].join)
       return true
    end
end
