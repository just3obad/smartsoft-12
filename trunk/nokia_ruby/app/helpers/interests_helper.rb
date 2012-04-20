module InterestsHelper
  #This method when called will return an array of ActiveRecords having all interests in the database.
  def get_all_interests
    interests=Interest.where(:deleted => false)
  end
  
  #This method when called will return the difference between today and the day the interest was created in days.
  def get_interest_start_date(interest_id)
 
  end
end
