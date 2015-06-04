#This page should show us how to show only certain number of data in our page for example see
localhost:3000/users.
Having more than 6 users will show this.

**To have a page like this all u need to do is the following....**

**1-in the controller you are working on .. on your action do the following
> instead of getting your query only get**

## yourquery.paginate(:per\_page => 5, :page=> params[:page]) ##

{where per\_page is the number per page you want
for example in the users\_controller we want in the index page to show only 5 users per page so all we do is

def index
> @users= User.paginate(:per\_page => 5,:page => params[:page])
end


**2- The last thing to do is the following at the end of your view page
insert those lines of code..**
<div>
<blockquote><%= will_paginate @users, :container => false %><br>
</blockquote><blockquote></div></blockquote>

and replace @users with the variable you instantiated in the controller..

And You will get a very decent view page...

Good Luck...
Please ask me if you can't understand a thing of that and if it doesn't work..
