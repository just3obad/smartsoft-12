module InterestsHelper
  #This method when called will return an array of ActiveRecords having all interests in the database.
  def get_all_interests
    interests=Interest.all
  end
end
