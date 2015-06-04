A guide for how to use the currently logged in user instead of passing the user\_id as a parameter in the URL.

# Introduction #

Our **authentication system** is now different, thanks to the **authlogic** gem.
So what this is all about is that we will change the way that we deal with **users** in our application.


# Details #

previously whenever we are calling a method in any controller for example the mainfeed we used to pass the id of the user as a parameter in the route like:

```
match "users/:id/main_feed" => "users#feed"
```

**which is totally wrong !**
### Why? ###

Because now we are using a **web app** and by using this route we are making the personal accounts for users visible for each other. For example a with id=1 can access the mainfeed of a user with id=2 by typing http://la2etlak.com/users/2/main_feed in the URL.

### How is this solved? ###

Now whenever a user logs in a new **User Session** is created for that user. This user session stores the information of **this currently logged in user**. By this way we don't need to pass the **user\_id** in the **url** to make the **controller** know that we need to access this **specific** user.

which means that our routes will look like this instead:

```
match "users/main_feed" => "users#feed"
```

but this is not enough to make the application understand that we need the **feed** of the **currently logged in user**.

what you need to do is that inside your methods you need to specify that the user that you are using is the **currently logged in user**.
This is easily done by calling the helper\_method **current\_user** inside your method like this :

```
@user = current_user
```

by this way you can access this user from anywhere inside your method of view for example:

```
name = @user.first_name

email = @user.email
```

so what we have to do right now is to adjust our routes and controllers to be working with this way.

Also now you cannot create a user like this anymore
```
user = User.create(email: "user@gmail.com")
or
user = User.create(email: "user@gmail.com", password:"123456")
```

instead you have to give it an email, password, and password\_confirmation
```
user = User.create(email: "user@gmail.com",password: "123456", password_confirmation: "123456")
```

### How to test your views? ###

This will help you test you methods and views:

First make sure that your svn is updated to the latest version

you will have to start by creating the users that you would like to make your test with:
rails console
```
$ User.create(email: "user1@gmail.com", password: "123456", password_confirmation: "123456")

$ User.create(email: "user2@gmail.com", password: "123456", password_confirmation: "123456")

$ User.create(email: "user3@gmail.com", password: "123456", password_confirmation: "123456")
```

You will need to remember the IDs of the users that you created because this is what we will use to login the user.

Now run your rails server

In you browser type http://localhost:3000/dummyLogin

now enter the id of the user you want to login with and press **login** button.

okay, now your user is logged in, you can continue using the application with this user, for example if you want to try the mainfeed you can go to http://localhost:3000/users/feed, if this is handled correctly as stated previously you will get the main feed of the user you logged in.

### How to apply this to your functional and unit tests? ###

Now it's time to include this in our functional tests, ofc everyone wants to hold his test while the user is logged in !

This will briefly explain how to login a user while holding your tests.

First you have to include this line in the **top** of the **test file** and remember the **test file** not the test itself !

```
setup :activate_authlogic
```

your test file should look like this:

```
class UserSessionsControllerTest < ActionController::TestCase

  setup :activate_authlogic

  test1
  test2
  test3
    .
    .
    .

end
```

Now after your test has the authlogic authentication enviroment ready, you may want to login a user in your **test**, but you must make sure that you have users ready in the fixtures (test/fixture/users.yml) and I've already added two users Ben and Ahmed :D

If you need to add your own user you should add him in this form in the user.yml file:

```
ben:
  id: 11
  email: ben@gmail.com
  password_salt: <%= salt = Authlogic::Random.hex_token %>
  crypted_password: <%= Authlogic::CryptoProviders::Sha512.encrypt("benrocks" + salt) %>
  persistence_token: <%= Authlogic::Random.hex_token %>
  single_access_token: <%= Authlogic::Random.friendly_token %>
  perishable_token: <%= Authlogic::Random.friendly_token %>
```

where his password is benrocks, in line 5 of this code.


now after our testing environment is all ready you can login a user during your test and this is simply by adding these lines of code to **your test**.

```
user1 = users(:ben)
UserSession.create(user1)
```

first we created the user **user1** which has the attributes of ben (the user we creatd in our users.yml file)

then we logged user1 in.



Thats all =)

If you have any questions feel free to ask me !

Kiro