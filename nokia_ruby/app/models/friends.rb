class Friends < ActiveRecord::Base
  attr_accessible :receiver, :sender, :stat
  
  belongs_to :user
  belongs_to :friend, :class_name => "User", :foreign_key => "friend_id"

  validates :receiver, presence: true
  validates :sender, presence: true
  validates :stat, presence: true
  
  
  def self.request(user, friend)
   
    return false if user == friend
    f1 = new(:sender => user, :receiver => friend, :stat => "0")
    f2 = new(:sender => friend, :rceiver => user, :stat => "0")
    transaction do
      f1.save
      f2.save
    end
    return true
  end


def self.accept(user, friend)
    f1 = find_by_user_id_and_friend_id(user, friend)
    f2 = find_by_user_id_and_friend_id(friend, user)
    if f1.nil? or f2.nil?
      return false
    else
      transaction do
        f1.update_attributes(:stat => "1")
        f2.update_attributes(:stat => "1")
      end
    end
    return true
  end
  
  def self.reject(user, friend)
    f1 = find_by_user_id_and_friend_id(user, friend)
    f2 = find_by_user_id_and_friend_id(friend, user)
    if f1.nil? or f2.nil?
      return false
    else
      transaction do
        f1.destroy
        f2.destroy
        return true
      end
    end
  end
  
end
