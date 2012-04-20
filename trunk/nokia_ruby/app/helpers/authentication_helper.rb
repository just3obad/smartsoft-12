require 'oauth'
require 'twitter'

$consumer_token  = 'cuE56v2eK79gsxK1baasA'
$consumer_secret = 'vIJmDpiqDNpiau2YaiHH19fW1zj5DRXjI1VUbuM7lk' 

def generate_request_token
  oa = OAuth::Consumer.new($consumer_token, $consumer_secret,
                           :site=> 'http://api.twitter.com',
                           :request_endpoint => 'http://api.twitter.com',
                           :sign_in => true)
  req = oa.get_request_token
  request_secret = req.secret
  request_token  = req.token

  url = "http://api.twitter.com/oauth/authorize?oauth_token=#{ rtoken }"
  {'url' => url, 'request' => req}
end


def generate_access_token(req)
  access = req.get_access_token
  access_token = access.token
  acces_secret = access.secret 
  returh Hash['access_token', access_token, 'access_secret', acces_secret]
end
  

def configure_twitter(access_token, access_secret)
  Twitter.configure do |config|
    config.consumer_key     = $consumer_token
    config.consumer_secret  = $consumer_secret
    config.access_token     = access_token
    config.access_secret    = access_secret
  end
end


def tweet(tweet, access_token, access_secret)
  configure_twitter($consumer_secret, access_token, access_secret) 
  client = Twitter::Client.new
  client.update(tweet)
end


def get_main_feed($consumer_token,
                  $consumer_secret,
                  access_token,
                  access_secret)
  ['coming', 'soon']
end
                   
  
