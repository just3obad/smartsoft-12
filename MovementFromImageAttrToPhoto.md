#Photo instead of image attribute in user

# Introduction #

Changes from image to photo.


# Details #

Simply this attached attribute is treated  like "NOT SIMILAR TO "  any attribute of the Interest ( it knows that it belongs to this certain Interest) and can be updated too .


Here are some usages I have experienced so far   :

1- If you want to display this photo in your view use this :
```
  <%= @interest.photo.url(:small)  %>
```

ex :
```
  <img src= "<%= @interest.photo.url(:small)%>"
```



2-If you want to check whether this attribute is empty or not use :
```
  interest.photo.file? 
```

ex:
```
  <img src= "<%= @interest.photo.url(:small) if @interest.photo.file? %>"
```



3-If you want to use the Uploading ohoto option in any of your views ( in a form or something ) use this :
```
  <%= f.file_field :photo%>
```
(this will generate automatocally the field with the browse button beside it


4- For Resizing it's treated just like any imge src

ex :
```
   <img src= "<%= @interest.photo.url(:small) if @interest.photo.file? %>" width="320" style="height:320px"/>
```



That's all for now , if anybody has another usage for it just tell `  jailan.salah@gmail.com ` and we will know Inshaa'Allah how to use it it's simple as you see .