#colorize unit test output

# Introduction #

A tiny guide to colorize your unit test output when using default rails unit tests


# Details #

The steps are very simple, follow them and you will get nice greens/red/oranges all over the testing output with no scripting, hacking or whatsoever.

First we need to add a two small gems minitests and turn, add them in the test group of your GemFile (if you don't have a test group, make a new test group similar the following)

```
  group :test do 
    gem 'minitest' 
    gem 'turn' 
  end   
```

That's it !! try ` rake test ` to see the colorful output (green, red, orange for passed, failures, errors).