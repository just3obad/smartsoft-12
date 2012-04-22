class VerificationCode < ActiveRecord::Base
  attr_accessible :code, :user_id
  
  belongs_to :user
  
  validates :code, :presence => true,
  :length => { :equal => 4}
  
  validates :user_id, presence => true,
  :uniqueness => true

  def 

 # lets a user share a story given its id
    def addVEntry
      Verification_Code.create :code=>( (0..9).to_a + ('a'..'z').to_a).shuffle[0..3].join, :user_id=>self.id
    end 
  end

end
