class Session < FormtasticFauxModel
  attr_accessor :username, :password

  validates_presence_of :username, :password
  
  self.types = { :username => :string, :password => :string }
end

