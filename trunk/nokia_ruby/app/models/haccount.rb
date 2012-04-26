class Haccount < ActiveRecord::Base
  attr_accessible :email, :password, :user_id

  belongs_to :user, class_name: "User"
  has_one :verification_code


  email_regex = /\A[\w+\-.]+@[a-z\d\-.]+\.[a-z]+\z/i
  validates :email, :presence => true,
  :format=> {:with => email_regex },
  :uniqueness => { :case_sensitive => false}
  validates :user_id, :uniqueness =>true

# This method generateVerificationCode? generates a verification code for the 'Haccount'
# and adds an entry to the verification codes table in the database.
# The verification code is 4 characters from (0-->9) and (a-->z).
# Returns true if it succeeded to create the entry, otherwise it returns false.

  def generateVerificationCode?()
  @verification_code = VerificationCode.find_by_user_id(self.id)
      if @verification_code.nil? then
      VerificationCode.create :code=>( (0..9).to_a + ('a'..'z').to_a).shuffle[0..3].join,:user_id=>self.id, :verified=>false
      return true               
    else                        
      return false              
    end
  end 

# This method verifyAccount?(verCode) takes the verCode entered by the user to verify his Haccount
# and it tries to match this code with the Haccount's code.
# returns true and sets verified to true if the user entered the correct code,
# otherwise it returns false.
  def verifyAccount?(verCode)
    @verEntry = VerificationCode.find_by_user_id(self.id)
    if @verEntry.code == verCode then
      @verEntry.update_attributes(verified: true)
      return true
    else 
      return false
    end
  end

 
# This method resendCode? resets the verification Code of a specific Haccount and updates the
# entry in the database.
# This method returns true if the accout wasnt verified yet, otherwise it returns false.
  def resendCode?
    @varEntry = VerificationCode.find_by_user_id(self.id)
    if @varEntry.verified
       return false
    else
    @varEntry.update_attributes(code: ( (0..9).to_a + ('a'..'z').to_a).shuffle[0..3].join)
       return true
    end
  end
end
