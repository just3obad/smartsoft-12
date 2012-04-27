/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hello;
import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;
import org.json.me.JSONArray;
import org.json.me.JSONException;
import org.json.me.JSONObject;
 
/**
 *
 * @author essam
 */
public class createStories {
    Form MainFeed;
    String content;
    Displayable display;
    HelloMIDlet Hello;
     
    public createStories(String content, Form MainFeed,Displayable display,HelloMIDlet Hello){
     this.MainFeed = MainFeed;
     this.content = content;
     this.display = display;
     this.Hello = Hello; 
     helpMainFeed(); // calls helper mehtod
     
     
    }
     public String removeExtras(String x){
        String returned="";
        for(int i=0;i<x.length();i++){
            if(x.charAt(i)=='"' || x.charAt(i)=='{' || x.charAt(i)=='}' || x.charAt(i) == ':'){
             
            }else{
                returned+=x.charAt(i);
            }
        }
        return(returned);
    }
     public String removeSpaces(String x){
        String returned="";
        for(int i=0;i<x.length();i++){
            if(x.charAt(i) == ' '){
             
            }else{
                returned+=x.charAt(i);
            }
        }
        return(returned);
    }
      public String removeImageExtras(String x){
        String returned="";
        for(int i=0;i<x.length();i++){
            if(x.charAt(i) != 'h'){
                if(x.charAt(i)=='"' || x.charAt(i)=='{' || x.charAt(i)=='}' || x.charAt(i) == ':'){
             
                }else{
                    returned+=x.charAt(i);
                }
            }else{
                while(i<x.length()){
                    returned+=x.charAt(i);
                    i++;
                }
                break;
            }
        }
        return(returned);
    }
    
      // methdo that works on a given url to get out the contents required to create the story item
      // a json file formatted on server back end that returns the specifies string to be parsed
    public void parseJsonStory(){
        System.out.println(content);
        String temp = content;
        String id,title,body,rank,image,category;
        try{
        if(temp.length() !=0){ 
            while(temp.indexOf("id")>=0) { //loop till no more id written
                int idOccur = temp.indexOf("id");    //get first occurance of it
                int titleOccur = temp.indexOf("title"); 
                int bodyOccur = temp.indexOf("content");
                int rankOccur = temp.indexOf("rank");
                int imageOccur = temp.indexOf("media_link");
                int categoryOccur = temp.indexOf("category");
                idOccur+=2;
                id = temp.substring(idOccur,titleOccur-1);
                titleOccur +=5;     // add 5 to title to get the rest as the title
                title = temp.substring(titleOccur, bodyOccur-1); //title = after title to index of body-1
                bodyOccur+=4;
                body = temp.substring(bodyOccur, rankOccur-1);
                rankOccur+=4;
                rank = temp.substring(rankOccur, imageOccur-1);
                imageOccur+=5;
                image = temp.substring(imageOccur, categoryOccur-1);
                categoryOccur +=8;
                if(temp.indexOf("id",categoryOccur)!=-1){ // checks if no more stories exist ( eg. ids )
                    category = temp.substring(categoryOccur,temp.indexOf("id", categoryOccur));
                    String x = temp.substring(temp.indexOf("id",categoryOccur));
                    temp = x;
                }else{
                    category = temp.substring(categoryOccur);
                    String x = temp.substring(categoryOccur);
                    temp = x;
                }
                id = removeExtras(id); // helper methods that removes the extra conent sent from the server through Json
                id = removeSpaces(id);
                title = removeExtras(title);
                body = removeExtras(body);
                rank = removeExtras(rank);
                rank = removeSpaces(rank);
                image = removeImageExtras(image);
                image = image.trim();
                category = removeExtras(category);
                storyItem a = new storyItem(Integer.parseInt(id),image,title,body,Integer.parseInt(rank),category,display,Hello); // creates the story
                MainFeed.append(a); // append it to the end of the list
            }
        }
        }catch(Exception e){
            System.out.println("Exception happened here");
            e.printStackTrace();
        }
    }
 public void fromJson(String js) {
        try {
            JSONObject json = new JSONObject(js);
            int id = json.getInt("id");
            String title = json.getString("title");
            String content = (json.getString("content"));
            String category = json.getString("category");
            int rank = json.getInt("rank");
            String media = json.getString("media_link");
            storyItem a = new storyItem(id,media,title,content,rank,category,display,this.Hello);
             MainFeed.append(a);
        } catch (JSONException ex) {
            ex.printStackTrace();
        }

    }
    
    public void parseNew() throws JSONException{
         JSONObject json = null;  
        try {
            System.out.println(content);
            json = new JSONObject(content); // create json object from string
        
            
            JSONArray jsonArray = json.getJSONArray("storyItem");  // get array
            int total = jsonArray.length();
       
            for (int i = 0; i < total; i++) {
                     String x = jsonArray.getString(i);
                     System.out.println("\n here is x\n\n" + x + "\n\n");
                     x = "{ storyItem :" + x + "}";
                     JSONObject json2 = new JSONObject(x);
                     JSONArray jsonArray2 = json2.getJSONArray("storyItem");
                     
                     for (int j=0;j<jsonArray2.length();j++){
                        String commJson = jsonArray2.getString(j); // get json representation of comment
                        System.out.println("\n" + commJson);
                        
                        fromJson(commJson);
                     }
                
               
                
              // storyItem a = new storyItem(commJson, this); // create a commentItem using it
                //CommentsMany.append(comments[i]); // append commentItem tp form
            }
            } catch (JSONException ex) {
            ex.printStackTrace();
        }
    }
     public void parseNewArray() throws JSONException{
         JSONObject json = null;  
            System.out.println(content);
            json = new JSONObject(content); // create json object from string

            
            JSONArray jsonArray = json.getJSONArray("storyItem");  // get array
            int total = jsonArray.length();

            for (int i = 0; i < total; i++) {
                String commJson = jsonArray.getString(i); // get json representation of comment
                System.out.println(commJson);
                fromJson(commJson);
                
              // storyItem a = new storyItem(commJson, this); // create a commentItem using it
                //CommentsMany.append(comments[i]); // append commentItem tp form
            }
    }
 
    
    public void helpMainFeed() {
        try {
            parseNewArray();
            System.out.println("back from parse without error");
            //parseJsonStory(); // calls method that actually helps
        } catch (JSONException ex) {
            try {
                parseNew();
            } catch (JSONException ex1) {
                ex1.printStackTrace();
            }
            ex.printStackTrace();
        }
    }

}
