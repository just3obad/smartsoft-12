require 'oauth'
require 'twitter'

module AuthenticationHelper
  # Applicatoin specific
  $consumer_token  = 'cuE56v2eK79gsxK1baasA'
  $consumer_secret = 'vIJmDpiqDNpiau2YaiHH19fW1zj5DRXjI1VUbuM7lk' 

  # User specific in this case @testapi9 it's password is testapi! 
  $access_token    = '558012292-Sz82kMwDBxlt8Yo3wK4NA0m72VfirgE7TJBzOG1j'
  $access_secret   = '1MuiXTaGQUrHiAKowJn6YAVHe9EONajEUBaTaB1tos'

=begin 
TODO handle excpetion such as,
    Twitter is not over capacity (ServiceUnavailable),
    You are not authozorized,
    Duplicate status, 

=end
  def generate_request_token
    oa = OAuth::Consumer.new($consumer_token, $consumer_secret,
                             :site=> 'http://api.twitter.com',
                             :request_endpoint => 'http://api.twitter.com',
                             :sign_in => true)
    req = oa.get_request_token
    request_secret = req.secret
    request_token  = req.token

    url = "http://api.twitter.com/oauth/authorize?oauth_token=#{ request_token }"
    puts url
    req
  end 


  def generate_access_token(req)
    access = req.get_access_token
    $access_token = access.token
    puts 'access_token is ' + $access_token
    $access_secret= access.secret
    puts 'access_secret is ' + $access_secret
  end 


  def config_twitter
    Twitter.configure do |config|
      config.consumer_key         = $consumer_token
      config.consumer_secret      = $consumer_secret
      config.oauth_token          = $access_token
      config.oauth_token_secret   = $access_secret
    end
  end 


  def tweet(tweet)
    client = Twitter::Client.new
    client.update(tweet)
  end 


  # returns the time of the last tweet received
  def get_main_feed(count=10)
    feed = Twitter.home_timeline(:count => count)
  end 
end 


