if you see this error or something like it:

unknown-10-40-f3-ab-c2-66:testing MESAI$ rails s
=> Booting Thin
=> Rails 3.2.3 application starting in development on http://0.0.0.0:3000
=> Call with -d to detach
=> Ctrl-C to shutdown server
DEPRECATION WARNING: You have Rails 2.3-style plugins in vendor/plugins! Support for these plugins will be removed in Rails 4.0. Move them out and bundle them in your Gemfile, or fold them in to your app as lib/myplugin/ and config/initializers/myplugin.rb. See the release notes for more on this: http://weblog.rubyonrails.org/2012/1/4/rails-3-2-0-rc2-has-been-released. (called from <top (required)> at /Users/MESAI/Testing/config/environment.rb:7)
DEPRECATION WARNING: You have Rails 2.3-style plugins in vendor/plugins! Support for these plugins will be removed in Rails 4.0. Move them out and bundle them in your Gemfile, or fold them in to your app as lib/myplugin/ and config/initializers/myplugin.rb. See the release notes for more on this: http://weblog.rubyonrails.org/2012/1/4/rails-3-2-0-rc2-has-been-released. (called from <top (required)> at /Users/MESAI/Testing/config/environment.rb:7)
>> Thin web server (v1.3.1 codename Triple Espresso)
>> Maximum connections set to 1024
>> Listening on 0.0.0.0:3000, CTRL+C to stop
Exiting
<b>
/Users/MESAI/.rvm/gems/ruby-1.9.2-p318@rails3tutorial/gems/eventmachine-1.0.0.beta.4/lib/eventmachine.rb:516:in `start_tcp_server': no acceptor (port is in use or requires root privileges) (RuntimeError)</b>
> from /Users/MESAI/.rvm/gems/ruby-1.9.2-p318@rails3tutorial/gems/eventmachine-1.0.0.beta.4/lib/eventmachine.rb:516:in `start\_server'
> from /Users/MESAI/.rvm/gems/ruby-1.9.2-p318@rails3tutorial/gems/thin-1.3.1/lib/thin/backends/tcp\_server.rb:16:in `connect'
> from /Users/MESAI/.rvm/gems/ruby-1.9.2-p318@rails3tutorial/gems/thin-1.3.1/lib/thin/backends/base.rb:53:in `block in start'
> from /Users/MESAI/.rvm/gems/ruby-1.9.2-p318@rails3tutorial/gems/eventmachine-1.0.0.beta.4/lib/eventmachine.rb:179:in `call'
> from /Users/MESAI/.rvm/gems/ruby-1.9.2-p318@rails3tutorial/gems/eventmachine-1.0.0.beta.4/lib/eventmachine.rb:179:in `run\_machine'
> from /Users/MESAI/.rvm/gems/ruby-1.9.2-p318@rails3tutorial/gems/eventmachine-1.0.0.beta.4/lib/eventmachine.rb:179:in `run'
> from /Users/MESAI/.rvm/gems/ruby-1.9.2-p318@rails3tutorial/gems/thin-1.3.1/lib/thin/backends/base.rb:61:in `start'
> from /Users/MESAI/.rvm/gems/ruby-1.9.2-p318@rails3tutorial/gems/thin-1.3.1/lib/thin/server.rb:159:in `start'
> from /Users/MESAI/.rvm/gems/ruby-1.9.2-p318@rails3tutorial/gems/rack-1.4.1/lib/rack/handler/thin.rb:13:in `run'
> from /Users/MESAI/.rvm/gems/ruby-1.9.2-p318@rails3tutorial/gems/rack-1.4.1/lib/rack/server.rb:265:in `start'
> from /Users/MESAI/.rvm/gems/ruby-1.9.2-p318@rails3tutorial/gems/railties-3.2.3/lib/rails/commands/server.rb:70:in `start'
> from /Users/MESAI/.rvm/gems/ruby-1.9.2-p318@rails3tutorial/gems/railties-3.2.3/lib/rails/commands.rb:55:in `block in <top (required)>'
> from /Users/MESAI/.rvm/gems/ruby-1.9.2-p318@rails3tutorial/gems/railties-3.2.3/lib/rails/commands.rb:50:in `tap'
> from /Users/MESAI/.rvm/gems/ruby-1.9.2-p318@rails3tutorial/gems/railties-3.2.3/lib/rails/commands.rb:50:in `<top (required)>'
> from script/rails:6:in `require'
> from script/rails:6:in `

&lt;main&gt;

'




The problem was that you forgot to close the server last time and it is still working in the background
you need to go to your "task manager" or "activity monitor" or "what is equivalent in linux" and kill the process under the name "ruby" then rerun.