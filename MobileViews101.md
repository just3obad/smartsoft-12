## Mobile Views 101 ##
Here's a step by step guide on how to create mobile views from scratch.

First of all you need to configure a route that redirects you to the mobile view. So in your routes file add something like the following:

```
 match "/stories/:id/show_mob/:id2" => "stories#mobile_show"
```

this address will redirect us towards the stories controller and to mobile\_show action. It contains 2  id parameters, the first one **id** represents the **story** id, while **id2** represents the **user** id.

Now let's start implementing the action in the Stories controller. You need to define a new action so in your stories controller you should have something like:

```
  def mobile_show
    @story = Story.find(params[:id])
    @comment = Comment.new
    @user_id = params[:id2]
    render :layout => "mobile_template"	
  end
```

This basically says that we have an action called mobile\_show "which needs a view with the same name" **<sup>1</sup>** and in this action we initialize some instance variables so that we would be able to use them in our view template. The last line in the method is where the magic happens, instead of rendering the default application.html.erb template, we are asking rails to render another layout that will override the default one.

**1** - You can name your template to whatever name you like but if you do so make sure to render the correct template by hand using:
```
render :layout => "LAYOUT_NAME", :template => "TEMPLATE_PATH"
```

> Yahia suggested I add this to the wiki in order to be more clear about the rendering issue. In practice though it is better for your templates to have the same name as your action's name and let **Rails** do its **Magic** ;)

---

Now that our controller's action is ready, we need to start building our view that will be rendered on the phone.

As for the regular views that C3 and C4 have been using all along, it is obvious that our views consist of 2 main components, one that is static for all the pages "like the nav bar for example" and one that changes according to the page "regular content like story's content or comments content", to get rid of repeating code, rails gave us the choice of letting some parts of the page stay as is for all pages "which is done through the **application.html.erb**". I stated earlier that this is not the case for us, however after trying something, it worked perfectly so here I am editing the wiki to show you how you can do it.

First of all, notice that I've changed the action mobile\_show to render mobile\_template rather than mobile\_show so make sure you change it to:

```
render :layout => "mobile_template"
```

instead of

```
render :layout => "mobile_show"	
```

Please make sure you have it changed too.

Now go to **app/views/layouts** and create an empty file and name it "**mobile\_template.html.erb**", this will act the same as the application.html.erb which is the place holder for the static parts of the page allowing dynamic content to fit within it. Now open the mobile\_template in your editor and paste the following markup code:

```
<html>
  <head>
	<meta http-equiv="Content-type" content="text/html; charset=utf-8" />
	<meta name="viewport" content="width=240, initial-scale=0.5">
	<title>My Story Page!</title>
	<%= stylesheet_link_tag    "mobile-style.css"%>
  </head>

  <header class="navbar navbar-fixed-top" >
      <div class="navbar-inner">
        <div class="container" >
          <a href="#" id="logo" ><h1 class = "pull-left" style = "float:up;">LA2ETLAK</h1></a>
          <nav>
            <ul class="nav">
              <li><a href="#"><img src="http://c.dryicons.com/images/icon_sets/colorful_stickers_icons_set/png/256x256/home.png" id="il_fi" height="25" width="25"></a></li>
              <li><a href="#"><img src="http://www.getempower.com/apps/50/icons/icon_50x50.png" id="il_fi" height="25" width="25"></a></li>
            </ul>
          </nav>
        </div>
      </div>
  </header> 

  <body>
   	<%= yield %>
  </body>

</html>
```

Notice that the nav bar includes a logo and 2 images that are all active **"clickable"** using the < a > tags and which can re-direct to other pages by replacing the **#** in href to a meaningful address.
Notice in the body we have the statement **<%= yield %>** to indicate the place holder that will be available to you to include you content depending on your page, thank God we are using erb so life would be a lot easier when we try to implement a view that changes dynamically along with the data.

I will show you my story page content as an example of the dynamic data handling mechanisms to be used within our views, and now that we have our static template "mobile\_template" ready we only need to have the content of the body within our views since the nav-bar and other static parts of the page will be loaded automatically from the mobile\_template that we just added above. You need to make sure though that the view's name is the **same as** the action's name, so you need to navigate to **app/views/stories/** and create a new file with the name "**mobile\_show.html.erb**" and paste the following mark up code to it:
```

      <div style="padding-left:3px;">
        <h3> <%= @story.title %> </h3>
      </div>
           
      <div  class="story-component-box-mobile pre-scrollable">
        <%= @story.content %>
      </div>

      <div  style="float:right; margin:3px;">
        <h7 style="color:#3a87ad"><%= Share.find_all_by_story_id(@story.id).length%> shares </h7>.
        <h7 style="color:#468847"><%= Likedislike.find_all_by_story_id_and_action(@story.id,1).length%> Ups </h7>.
        <h7 style="color:#b94a48"><%= Likedislike.find_all_by_story_id_and_action(@story.id,2).length%>  Downs </h7> 
      </div>

      <div style="padding-left:3px;">
          <h4 >  Comments </h4>
      </div>
      <% comments = Comment.find_all_by_story_id(@story.id) %>
      <% comments.each do |comment| %>
        <div class="story-comment-box-mobile" style="float:left; width:99%; clear:both;">
          <h5 > <%= User.find(comment.user_id).name %> says:</h5>
          <div style="clear:both;">
            <a href="#" style"float:left;"  align="top"> <img src="http://placehold.it/30x30"></a>
            <%= comment.content %>
          </div>
        </div> 
      <% end %>
        <%= form_for(@comment) do |f| %>
          <div class="story-comment-box-mobile" style="padding-left:3px; clear:both;">
            <%= f.label :Add_Comment%>
            <%= f.text_area :content, :class => "story-textarea-mobile" %>
          </div>
    
         <%= f.hidden_field :user_id, :value => @user_id %>
         <%= f.hidden_field :story_id, :value => @story.id %>
    
        <div class="actions" style="padding:20px; float:right;">
        <%= f.submit "comment" ,:class => "btn btn-large" %>
       <% end %>

        </div>
```

For those who read the mobile\_template's mark-up well, you can notice that the **style\_sheet\_link\_tag** includes a style sheet called **mobile-style** which tells us that we must have a **.css.scss** file with the same name in our **app/assets/stylesheets** that will specify the styling attributes used through out the view. Since you do not have it yet, browse to **app/assets/stylesheets** and create w new file named **mobile-style.css.scss** and open it into your editor and Copy-Paste the following:
```
@import "bootstrap";


#logo {
  float: left;
  margin-right: 10px;
  font-size: 1.7em;
  color: #fff;
  text-transform: uppercase;
  letter-spacing: -1px;
  padding-top: 9px;
  font-weight: bold;
  line-height: 1;
}

#logo:hover {
  color: #fff;
  text-decoration: none;
}

body {
	padding-top: 60px;
	height:100%;
  	background-color:#eee;
}

textarea {
  resize: vertical;
}
.story-component-box-mobile{
  background-color:#ddd;
  margin:3px;
  padding:2px;
  border: 1px solid #999;
}
.pre-scrollable {
  max-height: 200px;
  overflow-y: scroll;
}
.story-comment-box-mobile{
   background-color:#e5e5e5;
  margin:3px;
  padding:2px;
  border: 1px solid #999;
}
.story-textarea-mobile{
  width:95%;
  height:50%;

}
```

This style sheet was created by Mina Essam and it defines different classes with different attributes that you can use along in your views to make it look Stylish! ;)

Now we are all set to test the new mobile view "Yaaay"! So start up your rails server, open your web browser and navigate to this link:
```
http://localhost:3000/stories/1/show_mob/1
```

Needless to say that you have to have at least 1 story and 1 user in your data base for this to work, if you do not have users yet, up to this step should work but the rest won't!

As you can see, a pretty styled page appears in front of you showing the story's content along with the comments "you might have none yet". Try entering a new comment and press the button **comment** in order for the comment to get posted.
Hold on, what happened?! why am I being re-directed to the comment's show page?!! Something must be wrong!
Well, this happened due to the fact that in the **comments controller**, the **create action** redirects us to the show page of the comment automatically once it has been saved correctly, to verify this take a look at your comments controller's create action it will look like:
```
  def create
    @comment = Comment.new(params[:comment])
    respond_to do |format|
      if @comment.save
        format.html { redirect_to @comment, notice: 'Comment was successfully created.' }
        format.json { render json: @comment, status: :created, location: @user }
      else
        format.html { render action: "new" }
        format.json { render json: @comment.errors, status: :unprocessable_entity }
      end
    end
  end
```

the **redirect\_to @comment** is the one responsible for redirecting us to the comment show page, so we need to change this to:
```
  def create
    @comment = Comment.new(params[:comment])
    if @comment.content.empty?
    then
      respond_to do |format|
        format.html { redirect_to :controller => 'stories', :action => 'mobile_show', :id2 => @comment.user_id,:id => @comment.story_id}
      end
    else
      respond_to do |format|
        if @comment.save
          format.html { redirect_to :controller => 'stories', :action => 'mobile_show', :id2 => @comment.user_id,:id => @comment.story_id}
          format.json { render json: @comment, status: :created, location: @user }
        else
          format.html { render action: "new" }
          format.json { render json: @comment.errors, status: :unprocessable_entity }
        end
      end
    end
  end
```

Now, after creating a new comment successfully, the action will re-direct us to the mobile\_show action in the story controller along with the 2 parameters needed **id and id2**. Moreover, an extra condition is also used above to prevent creating a comment with no content, this is done by re-directing without saving the comment in the database.

Try commenting again on a story and watch the page gets reloaded with your new comment posted ;) .

You must be wondering, what does all of this have to do with the mobile? How can I specify sizes that would fit the small phone's screen?
Wonder no more, nothing more needs to be added since bootstrap's **responsive** design allows the elements to be resized automatically and accordingly based on the browser's window dimensions. You can test this by re-sizing your browser and see how the template reacts.

To get a little more confident that the design works on the mobile and to get a feel of how it would look, we need to access the server through our good ol' Nokia SDK emulator and to be able to do this you need to do the following:

1- Open up your Netbeans and create a new mobile project.

2- In the **HelloMIDlet** class created by default, go to the **startMIDlet** method and copy paste the following in the **pre** part "right before the switchDisplayable":
```
public void startMIDlet() {   
        String url = "http://"+yourIP+":3000/stories/1/show_mob/1";                                   
        try {
              platformRequest(url);
                              
            } catch (ConnectionNotFoundException ex) {
              ex.printStackTrace();
            }
        if(true) return;
        switchDisplayable(null, getForm());                                        
        // write post-action user code here
    } 
```

**platformRequest** is responsible for asking the MIDlet to open the url in the phone's browser. When we come to deploy our app, the url should point to the root url of our application and the rest of the routing will be done through links and redirects from within the phone's browser without the need to go back to the MIDlet "just like normal browsers".

Of course you will need to change **yourIP** to match your own IP that you got from the VM's ifconfig command.
The " if(true) return; " ensures that the displayable does not get switched in case you're wondering.

Now run your project and watch the result on the simulator's screen, it is exactly the same as the one you get on the real phone. It might not look as pretty as on the desktop browser "no curves..etc" but believe me it is **far much better** than the web apps implemented using Nokia's web-app tools.

You can try all sort of things to check on how the phone re-acts but I would like to point out that most (**if not all**) java scripts won't work "like the ones used for collapsible and such" but you can still try if you want and let us know if you reach a breakthrough!

Hope this was helpful, if you face any trouble while following this wiki please let me know.

Regards,

Menisy

