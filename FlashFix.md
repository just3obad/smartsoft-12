Fixing the Flash message

Add your content here.

The flash is now fixed
in your controller:

Call flash[:notice] for blue

Call flash[:error] for red

Call flash[:success] for green
+ the message


and you must include once in your view at the top:

<%= render 'shared/all\_flash' %>