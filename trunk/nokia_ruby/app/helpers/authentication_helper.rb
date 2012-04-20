require 'oauth'
require 'twitter'

$consumer_token  = 'cuE56v2eK79gsxK1baasA'
$consumer_secret = 'vIJmDpiqDNpiau2YaiHH19fW1zj5DRXjI1VUbuM7lk' 
$access_token
$access_secret

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
  $access_secret= access.secret
end 


def tweet(tweet)
  Twitter.configure do |config|
    config.consumer_key         = $consumer_token
    config.consumer_secret      = $consumer_secret
    config.oauth_token          = $access_token
    config.oauth_token_secret   = $access_secret
  end
  client = Twitter::Client.new
  client.update(tweet)
end 

req = generate_request_token
puts 'Please visit the above url'

puts "Press enter when authenticated"
gets
generate_access_token(req) 


puts 'Please enter the tweet'
tweet("#{ gets.chomp }")

