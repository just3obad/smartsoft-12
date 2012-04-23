/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hello;

import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;
import  com.nokia.mid.ui.LCDUIUtil;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import javax.microedition.io.Connector;
import javax.microedition.io.HttpConnection;
import org.json.me.*;

/**
 * @author Essam Hafez
 */

public class HelloMIDlet extends MIDlet implements CommandListener{
    String url;
   
    int countInsertion = 0;
    private boolean midletPaused = false;
     Displayable displayable;
    String json;
    int user_id; // The user id of logged in 
//<editor-fold defaultstate="collapsed" desc=" Generated Fields ">//GEN-BEGIN:|fields|0|
private Command exitCommand;
private Command viewComments;
private Command Comment1;
private Command backCommand;
private Command backCommand1;
private Command okCommand;
private Command AddComment;
private Command backToStory;
private Command cancelCommand;
private Command ok;
private Command back;
private Command options;
private Command okCommand1;
private Command Back1;
private Command Find;
private Command Add;
private Command choosefriend;
private Command Add1;
private Command okCommand2;
private Command backCommand3;
private Command backCommand4;
private Command backCommand5;
private Command okCommand4;
private Command okCommand3;
private Command okCommand5;
private Command backCommand7;
private Command backCommand8;
private Command backCommand6;
private Command exitCommand1;
private Command Resend;
private Command okCommand6;
private Command Verify;
private Command backV;
private Command okCommand7;
private Command backCommand9;
private Command thumbdown;
private Command thumbup;
private Command blockstory;
private Command signout;
private Command recommend1;
private Command blockinterest;
private Command flag;
private Command share;
private Command Dislike;
private Command backToComments;
private Command Like;
private Form form;
private StringItem stringItem;
private Form Story;
private Form MainFeed;
private Form CommentsMany;
private TextField textField;
private TextBox textBox;
private Form profile;
private TextField firstName;
private TextField userName;
private TextField confPas;
private TextField pas;
private DateField dob;
private TextField lastName;
private Alert CommentFail;
private Gauge indicator;
private Alert CommentSucc;
private List connectAccount;
private Form findFriend;
private TextField search;
private Alert Found;
private Alert NotFound;
private Alert Saved;
private Alert Error;
private Form recommend;
private TextField textField3;
private TextField textField2;
private Alert ComingSoon;
private Alert EnterUserNameEmail;
private List choosefriend1;
private List likeddisliked;
private Alert storynotpublished;
private Alert storeypublished;
private Form LoginScreen;
private TextField textField5;
private TextField textField4;
private Form form1;
private TextField textField9;
private Form RegisterScreen;
private TextField textField6;
private TextField textField7;
private TextField textField8;
private Alert IncorrectCode;
private Gauge indicator1;
private Alert ResentAlert;
private Gauge indicator3;
private Alert VerifiedAlert;
private Gauge indicator2;
private Alert InvalidCode;
private Gauge indicator4;
private Form Verification;
private TextField vTF;
private StringItem vSI;
private Form readMore;
private ImageItem imageItem;
private Form FriendsStories;
private List list;
private Form CommentOne;
private Image image1;
//</editor-fold>//GEN-END:|fields|0|
    private HttpConnection httpConn;
    private OutputStream os;

  
    /**
     * The HelloMIDlet constructor.
     */
    public HelloMIDlet() {
    }

    public Image getImage1(String s) {
        if (image1 == null) {
            // write pre-init user code here
            try {
                image1 = Image.createImage(s);
            } catch (java.io.IOException e) {
                e.printStackTrace();
            }
            // write post-init user code here
        }
        return image1;
    }
    
    public void jsonReadMoree() {
        String url = "id: 1 title:\"FaceBook \" body To launch the high quality TV channel TNT in Belgium we placed a big red push button on an average Flemish square of an average Flemish town. A sign with the...http://www.3run.co.uk/ - Home of the 3RUN Famly World Wide http://www.3runshop.com/ - Free Running Trainers, Clothing, DVD's, Bags, Accessories. 3RUN  : rank 5 image : /x.png category : arts";
        String s = url;
        
        if(s.length() == 0) {
            readMore.append("sorry , the selected story was removed");
            readMore.removeCommand(thumbup);
            readMore.removeCommand(thumbdown);
            readMore.removeCommand(flag);
            readMore.removeCommand(recommend1);
            readMore.removeCommand(share);
            readMore.removeCommand(signout);
            readMore.removeCommand(blockinterest);
            readMore.removeCommand(blockstory); 
        }
                else {
                int idOccur = s.indexOf("id");   
                int t = s.indexOf("title");
                int b = s.indexOf("body");
                int r = s.indexOf("rank");
                int i = s.indexOf("image");
                int c = s.indexOf("category");
                t=t+7;
                String title = s.substring(t, b-2); 
                b+=4;
                String body = s.substring(b, r-1);
                r+=4;
                String rank = s.substring(r, i-1);
                i+=5;
                String image = s.substring(i+3, c-1);
                c +=8;
                String category = s.substring(c, s.length()-1);
          
             Image addedImage = getImage1(image);
               
           try {
                addedImage = Image.createImage(image);
            } catch (IOException ex) {
                ex.printStackTrace();
            }   
            
                
                //readMore.append("\n");
                readMore.append( title.toUpperCase());
                 if(addedImage != null) readMore.append(addedImage);
                readMore.append("\n");
                readMore.append("with ranking:   " + rank);
                readMore.append("\n");
                //readMore.append(image);
                readMore.append("\n");
                readMore.append(": " + body);

        }
    }
  
    public void rm() {
        jsonReadMoree();
    }   
    

 
    
    
     public void sendData(String ip, String data){
        
        
         try {
        //Change IP accordingly
        httpConn = (HttpConnection) Connector.open(ip);
        //Request method has to be POST
        httpConn.setRequestMethod(HttpConnection.POST);
        httpConn.setRequestProperty("User-Agent",
                "Profile/MIDP-1.0 Confirguration/CLDC-1.0");
        httpConn.setRequestProperty("Accept_Language", "en-US");
        //Content-Type is must to pass parameters in POST Request must be application/json
        httpConn.setRequestProperty("Content-Type", "application/json");
        // JSON String that you will send containing the attributes needed for sign up. 
        //String dataToBeSend = "{\"created_at\":\"nil\",\"email\":\"menisy@abfcge.com\",\"name\":\"menisy\",\"updated_at\":\"nil\"}";
        String dataToBeSend = data;
        httpConn.setRequestProperty("Content-Length",
                "" + dataToBeSend.length());
        os = httpConn.openOutputStream();
        os.write(dataToBeSend.getBytes());

        os.flush();//data written will be flushed to server.
        System.out.println(httpConn.getResponseCode());
        System.out.println(dataToBeSend);
    
    } catch (Throwable t) {
        System.out.println("Exception occured " + t.toString());
    } //Since only limited number of network objects can be in open state
    //it is necessary to clean them up as soon as we are done with them.
    finally {
        try {
            if (os != null) {
                os.close();
            }
        } catch (Throwable t) {
            System.out.println("Exception occured " + t.toString());
        }
        try {
            if (httpConn != null) {
                httpConn.close();
            }
        } catch (Throwable t) {
            System.out.println("Exception occured " + t.toString());
        }
    }
        
    }
     
      public String getData(String ip){
         String ret = "";
          HttpConnection httpConn = null;
      String url = ip+".json" ;  

    InputStream is = null;
    OutputStream os = null;

    try {
      // Open an HTTP Connection object
      httpConn = (HttpConnection)Connector.open(url);

      // Setup HTTP Request
      httpConn.setRequestMethod(HttpConnection.GET);
      httpConn.setRequestProperty("User-Agent",
        "Profile/MIDP-1.0 Confirguration/CLDC-1.0");


      int respCode = httpConn.getResponseCode();
      if (respCode == httpConn.HTTP_OK) {
        StringBuffer sb = new StringBuffer();
        os = httpConn.openOutputStream();
        is = httpConn.openDataInputStream();
        int chr;
        while ((chr = is.read()) != -1)
          sb.append((char) chr);

    
        System.out.println( sb.toString());
        ret = sb.toString();
      }
      else {
        System.out.println("Error in opening HTTP Connection. Error#" + respCode);
      }}catch(Exception e){
          
      

      } finally {
        if(is!= null)
            try {
                is.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
          if(os != null)
            try {
                os.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
      if(httpConn != null)
            try {
                httpConn.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
     
    }
         return ret;
     }
      public void friendsConnection()
    {
          
         HttpConnection httpConn = null;
      String url = "http://192.168.26.136:3000/users/friends_feed?id="+ user_id;
      
    InputStream is = null;
    OutputStream os = null;

    try {
      // Open an HTTP Connection object
      httpConn = (HttpConnection)Connector.open(url);

      // Setup HTTP Request
      httpConn.setRequestMethod(HttpConnection.GET);
      httpConn.setRequestProperty("User-Agent",
        "Profile/MIDP-1.0 Confirguration/CLDC-1.0");
      int respCode = httpConn.getResponseCode();
      if (respCode == httpConn.HTTP_OK) {
        StringBuffer sb = new StringBuffer();
        os = httpConn.openOutputStream();
        is = httpConn.openDataInputStream();
        int chr;
        while ((chr = is.read()) != -1)
          sb.append((char) chr);
        
        sb.toString();
        FriendsStories.append(sb.toString());
      }
      else {
        System.out.println("Error in opening HTTP Connection. Error#" + respCode);
      }

      } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if(is!= null)
                   is.close();
                  if(os != null)
                    os.close();
              if(httpConn != null)
                    httpConn.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
    }

    }
    
//<editor-fold defaultstate="collapsed" desc=" Generated Methods ">//GEN-BEGIN:|methods|0|
//</editor-fold>//GEN-END:|methods|0|
//<editor-fold defaultstate="collapsed" desc=" Generated Method: initialize ">//GEN-BEGIN:|0-initialize|0|0-preInitialize
/**
 * Initializes the application.
 * It is called only once when the MIDlet is started. The method is called before the <code>startMIDlet</code> method.
 */
private void initialize () {//GEN-END:|0-initialize|0|0-preInitialize
        // write pre-initialize user code here
//GEN-LINE:|0-initialize|1|0-postInitialize
        // write post-initialize user code here
}//GEN-BEGIN:|0-initialize|2|
//</editor-fold>//GEN-END:|0-initialize|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Method: startMIDlet ">//GEN-BEGIN:|3-startMIDlet|0|3-preAction
/**
 * Performs an action assigned to the Mobile Device - MIDlet Started point.
 */
public void startMIDlet () {//GEN-END:|3-startMIDlet|0|3-preAction
        // write pre-action user code here
switchDisplayable (null, getCommentsMany ());//GEN-LINE:|3-startMIDlet|1|3-postAction
        // write post-action user code here
}//GEN-BEGIN:|3-startMIDlet|2|
//</editor-fold>//GEN-END:|3-startMIDlet|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Method: resumeMIDlet ">//GEN-BEGIN:|4-resumeMIDlet|0|4-preAction
/**
 * Performs an action assigned to the Mobile Device - MIDlet Resumed point.
 */
public void resumeMIDlet () {//GEN-END:|4-resumeMIDlet|0|4-preAction
        // write pre-action user code here
//GEN-LINE:|4-resumeMIDlet|1|4-postAction
        // write post-action user code here
}//GEN-BEGIN:|4-resumeMIDlet|2|
//</editor-fold>//GEN-END:|4-resumeMIDlet|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Method: switchDisplayable ">//GEN-BEGIN:|5-switchDisplayable|0|5-preSwitch
/**
 * Switches a current displayable in a display. The <code>display</code> instance is taken from <code>getDisplay</code> method. This method is used by all actions in the design for switching displayable.
 * @param alert the Alert which is temporarily set to the display; if <code>null</code>, then <code>nextDisplayable</code> is set immediately
 * @param nextDisplayable the Displayable to be set
 */
public void switchDisplayable (Alert alert, Displayable nextDisplayable) {//GEN-END:|5-switchDisplayable|0|5-preSwitch
        // write pre-switch user code here
Display display = getDisplay ();//GEN-BEGIN:|5-switchDisplayable|1|5-postSwitch
if (alert == null) {
display.setCurrent (nextDisplayable);
} else {
display.setCurrent (alert, nextDisplayable);
}//GEN-END:|5-switchDisplayable|1|5-postSwitch
        // write post-switch user code here
}//GEN-BEGIN:|5-switchDisplayable|2|
//</editor-fold>//GEN-END:|5-switchDisplayable|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Method: commandAction for Displayables ">//GEN-BEGIN:|7-commandAction|0|7-preCommandAction
/**
 * Called by a system to indicated that a command has been invoked on a particular displayable.
 * @param command the Command that was invoked
 * @param displayable the Displayable where the command was invoked
 */
public void commandAction (Command command, Displayable displayable) {//GEN-END:|7-commandAction|0|7-preCommandAction
        // write pre-action user code here
         this.displayable = displayable;
if (displayable == CommentOne) {//GEN-BEGIN:|7-commandAction|1|213-preAction
if (command == Dislike) {//GEN-END:|7-commandAction|1|213-preAction
 // write pre-action user code here
//GEN-LINE:|7-commandAction|2|213-postAction
 // write post-action user code here
} else if (command == Like) {//GEN-LINE:|7-commandAction|3|211-preAction
 // write pre-action user code here
//GEN-LINE:|7-commandAction|4|211-postAction
 // write post-action user code here
} else if (command == backToComments) {//GEN-LINE:|7-commandAction|5|209-preAction
 // write pre-action user code here
//GEN-LINE:|7-commandAction|6|209-postAction
 // write post-action user code here
}//GEN-BEGIN:|7-commandAction|7|40-preAction
} else if (displayable == CommentsMany) {
if (command == AddComment) {//GEN-END:|7-commandAction|7|40-preAction
                // write pre-action user code here
//GEN-LINE:|7-commandAction|8|40-postAction
                // write post-action user code here
    String s = getTextField().getString();
    if(s.equals(null) || s.equals("")){
        switchDisplayable(CommentFail, displayable);  //show failure alert
    }else{
      //  addComment(s);
      //  getTextField().delete(0, s.length());
       // getTextField();
     //   switchDisplayable(null, getStory());   //re-initializing comments list component
     //   Comment = null;
     //   getComment();
      //  addComment(s);
     //   getTextField().n
     //   switchDisplayable(CommentSucc, getComment());  //show success alert
      //  getDisplay().setCurrent(displayable);
    }
} else if (command == backToStory) {//GEN-LINE:|7-commandAction|9|38-preAction
                // write pre-action user code here
//GEN-LINE:|7-commandAction|10|38-postAction
                // write post-action user code here
}//GEN-BEGIN:|7-commandAction|11|137-preAction
} else if (displayable == LoginScreen) {
if (command == backCommand5) {//GEN-END:|7-commandAction|11|137-preAction
                // write pre-action user code here
//GEN-LINE:|7-commandAction|12|137-postAction
                // write post-action user code here
} else if (command == okCommand3) {//GEN-LINE:|7-commandAction|13|135-preAction
                // write pre-action user code here
//GEN-LINE:|7-commandAction|14|135-postAction
                // write post-action user code here
}//GEN-BEGIN:|7-commandAction|15|63-preAction
} else if (displayable == MainFeed) {
if (command == options) {//GEN-END:|7-commandAction|15|63-preAction
                // write pre-action user code here
//GEN-LINE:|7-commandAction|16|63-postAction
                // write post-action user code here
}//GEN-BEGIN:|7-commandAction|17|155-preAction
} else if (displayable == RegisterScreen) {
if (command == backCommand7) {//GEN-END:|7-commandAction|17|155-preAction
                // write pre-action user code here
//GEN-LINE:|7-commandAction|18|155-postAction
                // write post-action user code here
} else if (command == okCommand5) {//GEN-LINE:|7-commandAction|19|153-preAction
                // write pre-action user code here
//GEN-LINE:|7-commandAction|20|153-postAction
                // write post-action user code here
}//GEN-BEGIN:|7-commandAction|21|29-preAction
} else if (displayable == Story) {
if (command == Comment1) {//GEN-END:|7-commandAction|21|29-preAction
                // write pre-action user code here
//GEN-LINE:|7-commandAction|22|29-postAction
                // write post-action user code here
} else if (command == backCommand) {//GEN-LINE:|7-commandAction|23|31-preAction
                // write pre-action user code here
//GEN-LINE:|7-commandAction|24|31-postAction
                // write post-action user code here
} else if (command == okCommand1) {//GEN-LINE:|7-commandAction|25|79-preAction
 // write pre-action user code here
//GEN-LINE:|7-commandAction|26|79-postAction
 // write post-action user code here
}//GEN-BEGIN:|7-commandAction|27|170-preAction
} else if (displayable == Verification) {
if (command == Resend) {//GEN-END:|7-commandAction|27|170-preAction
                // write pre-action user code here
//GEN-LINE:|7-commandAction|28|170-postAction
                // write post-action user code here
} else if (command == Verify) {//GEN-LINE:|7-commandAction|29|168-preAction
                // write pre-action user code here
//GEN-LINE:|7-commandAction|30|168-postAction
                // write post-action user code here
} else if (command == backV) {//GEN-LINE:|7-commandAction|31|166-preAction
                // write pre-action user code here
//GEN-LINE:|7-commandAction|32|166-postAction
                // write post-action user code here
}//GEN-BEGIN:|7-commandAction|33|123-preAction
} else if (displayable == choosefriend1) {
if (command == Find) {//GEN-END:|7-commandAction|33|123-preAction
                // write pre-action user code here
//GEN-LINE:|7-commandAction|34|123-postAction
                // write post-action user code here
} else if (command == List.SELECT_COMMAND) {//GEN-LINE:|7-commandAction|35|117-preAction
                // write pre-action user code here
choosefriend1Action ();//GEN-LINE:|7-commandAction|36|117-postAction
                // write post-action user code here
} else if (command == backCommand4) {//GEN-LINE:|7-commandAction|37|122-preAction
                // write pre-action user code here
//GEN-LINE:|7-commandAction|38|122-postAction
                // write post-action user code here
} else if (command == okCommand2) {//GEN-LINE:|7-commandAction|39|120-preAction
                // write pre-action user code here
//GEN-LINE:|7-commandAction|40|120-postAction
                // write post-action user code here
}//GEN-BEGIN:|7-commandAction|41|81-preAction
} else if (displayable == connectAccount) {
if (command == List.SELECT_COMMAND) {//GEN-END:|7-commandAction|41|81-preAction
                // write pre-action user code here
connectAccountAction ();//GEN-LINE:|7-commandAction|42|81-postAction
                // write post-action user code here
}//GEN-BEGIN:|7-commandAction|43|106-preAction
} else if (displayable == findFriend) {
if (command == Add1) {//GEN-END:|7-commandAction|43|106-preAction
                // write pre-action user code here
//GEN-LINE:|7-commandAction|44|106-postAction

                // write post-action user code here
                if(this.search.getString().length()!=0){
                    //" \"date_of_birth\":"+this.dob.getDate().toString()
                   String s =" \"receiver\":"+ this.search.getString()+" \"sender_id\":"+this.user_id;
                   this.sendData("friends/add/ip", s);
                }
} else if (command == Back1) {//GEN-LINE:|7-commandAction|45|94-preAction
                // write pre-action user code here
//GEN-LINE:|7-commandAction|46|94-postAction
                // write post-action user code here
}//GEN-BEGIN:|7-commandAction|47|19-preAction
} else if (displayable == form) {
if (command == exitCommand) {//GEN-END:|7-commandAction|47|19-preAction
                // write pre-action user code here
//GEN-LINE:|7-commandAction|48|19-postAction
                // write post-action user code here
}//GEN-BEGIN:|7-commandAction|49|159-preAction
} else if (displayable == form1) {
if (command == backCommand8) {//GEN-END:|7-commandAction|49|159-preAction
                // write pre-action user code here
//GEN-LINE:|7-commandAction|50|159-postAction
                // write post-action user code here
} else if (command == okCommand6) {//GEN-LINE:|7-commandAction|51|161-preAction
                // write pre-action user code here
//GEN-LINE:|7-commandAction|52|161-postAction
                // write post-action user code here
}//GEN-BEGIN:|7-commandAction|53|128-preAction
} else if (displayable == likeddisliked) {
if (command == List.SELECT_COMMAND) {//GEN-END:|7-commandAction|53|128-preAction
                // write pre-action user code here
likeddislikedAction ();//GEN-LINE:|7-commandAction|54|128-postAction
                // write post-action user code here
} else if (command == backCommand) {//GEN-LINE:|7-commandAction|55|130-preAction
                // write pre-action user code here
//GEN-LINE:|7-commandAction|56|130-postAction
                // write post-action user code here
}//GEN-BEGIN:|7-commandAction|57|182-preAction
} else if (displayable == list) {
if (command == List.SELECT_COMMAND) {//GEN-END:|7-commandAction|57|182-preAction
                // write pre-action user code here
listAction ();//GEN-LINE:|7-commandAction|58|182-postAction
                // write post-action user code here
}//GEN-BEGIN:|7-commandAction|59|53-preAction
} else if (displayable == profile) {
if (command == back) {//GEN-END:|7-commandAction|59|53-preAction
                // write pre-action user code here
//GEN-LINE:|7-commandAction|60|53-postAction
                // write post-action user code here
} else if (command == ok) {//GEN-LINE:|7-commandAction|61|49-preAction
                // write pre-action user code here
               //String dataToBeSend = "{\"created_at\":\"nil\",\"email\":\"menisy@abfcge.com\",\"name\":\"menisy\",\"updated_at\":\"nil\"}"
               String s ="";
               int anyInfo = 0;
               int passInt = 0;
               
               if(this.userName.getString().length()!=0){
                   s +=" \"name\":"+this.userName.getString();
                    anyInfo = 1;
               }
               if(this.pas.getString().length()!=0 || this.confPas.getString().length()!=0){
                   if(this.pas.getString() == this.confPas.getString()){
                        s += " \"password\":"+this.userName.getString()+" \"user_id\":"+this.user_id;
                         passInt = 1;
                        
                    }
                     else{
                      //Alert pass and confpass mismatch
                       }
                   }
               if(this.firstName.getString().length()!=0){
                   s+=" \"firstName\":"+this.firstName.getString();
                    anyInfo = 1;
               }
               if(this.lastName.getString().length()!=0){
                   s+=" \"lastName\":"+this.lastName.getString();
                    anyInfo = 1;
               }
               if(this.dob.getDate().toString().length()!=0){
                   s+=" \"date_of_birth\":"+this.dob.getDate().toString();
                    anyInfo = 1;
               }
               
               if(passInt==1&&anyInfo==1){
                   this.sendData("haccount/modify/IP", s);
                   this.sendData("user/modify/ip", s);
               }else{
                   if(passInt==1&&anyInfo==0){
                       this.sendData("haccount/modify/IP", s);
                   }
                   else{
                       if(passInt==0&&anyInfo==0){
                           this.sendData("user/modify/IP", s);
                       }
                   }
               }
//GEN-LINE:|7-commandAction|62|49-postAction
                // write post-action user code here
}//GEN-BEGIN:|7-commandAction|63|186-preAction
} else if (displayable == readMore) {
if (command == backCommand9) {//GEN-END:|7-commandAction|63|186-preAction
                 // write pre-action user code here
//GEN-LINE:|7-commandAction|64|186-postAction
                 // write post-action user code here
} else if (command == blockinterest) {//GEN-LINE:|7-commandAction|65|200-preAction
                 // write pre-action user code here
//GEN-LINE:|7-commandAction|66|200-postAction
                 // write post-action user code here
} else if (command == blockstory) {//GEN-LINE:|7-commandAction|67|202-preAction
                 // write pre-action user code here
//GEN-LINE:|7-commandAction|68|202-postAction
                 // write post-action user code here
} else if (command == flag) {//GEN-LINE:|7-commandAction|69|194-preAction
                 // write pre-action user code here
//GEN-LINE:|7-commandAction|70|194-postAction
                 // write post-action user code here
} else if (command == okCommand7) {//GEN-LINE:|7-commandAction|71|188-preAction
                 // write pre-action user code here
//GEN-LINE:|7-commandAction|72|188-postAction
                 // write post-action user code here
} else if (command == recommend1) {//GEN-LINE:|7-commandAction|73|198-preAction
                 // write pre-action user code here
//GEN-LINE:|7-commandAction|74|198-postAction
                 // write post-action user code here
} else if (command == share) {//GEN-LINE:|7-commandAction|75|196-preAction
                 // write pre-action user code here
//GEN-LINE:|7-commandAction|76|196-postAction
                 // write post-action user code here
} else if (command == signout) {//GEN-LINE:|7-commandAction|77|204-preAction
                 // write pre-action user code here
//GEN-LINE:|7-commandAction|78|204-postAction
                 // write post-action user code here
} else if (command == thumbdown) {//GEN-LINE:|7-commandAction|79|192-preAction
                 // write pre-action user code here
//GEN-LINE:|7-commandAction|80|192-postAction
                 // write post-action user code here
} else if (command == thumbup) {//GEN-LINE:|7-commandAction|81|190-preAction
                 // write pre-action user code here
//GEN-LINE:|7-commandAction|82|190-postAction
                 // write post-action user code here
}//GEN-BEGIN:|7-commandAction|83|115-preAction
} else if (displayable == recommend) {
if (command == backCommand3) {//GEN-END:|7-commandAction|83|115-preAction
                // write pre-action user code here
//GEN-LINE:|7-commandAction|84|115-postAction
                // write post-action user code here
} else if (command == choosefriend) {//GEN-LINE:|7-commandAction|85|124-preAction
                // write pre-action user code here
//GEN-LINE:|7-commandAction|86|124-postAction
                // write post-action user code here
} else if (command == okCommand) {//GEN-LINE:|7-commandAction|87|113-preAction
                // write pre-action user code here
//GEN-LINE:|7-commandAction|88|113-postAction
                // write post-action user code here
}//GEN-BEGIN:|7-commandAction|89|35-preAction
} else if (displayable == textBox) {
if (command == backCommand1) {//GEN-END:|7-commandAction|89|35-preAction
                // write pre-action user code here
//GEN-LINE:|7-commandAction|90|35-postAction
                // write post-action user code here
} else if (command == okCommand) {//GEN-LINE:|7-commandAction|91|33-preAction
                // write pre-action user code here
//GEN-LINE:|7-commandAction|92|33-postAction
                // write post-action user code here
}//GEN-BEGIN:|7-commandAction|93|7-postCommandAction
}//GEN-END:|7-commandAction|93|7-postCommandAction
        // write post-action user code here
}//GEN-BEGIN:|7-commandAction|94|
//</editor-fold>//GEN-END:|7-commandAction|94|





//<editor-fold defaultstate="collapsed" desc=" Generated Getter: exitCommand ">//GEN-BEGIN:|18-getter|0|18-preInit
/**
 * Returns an initiliazed instance of exitCommand component.
 * @return the initialized component instance
 */
public Command getExitCommand () {
if (exitCommand == null) {//GEN-END:|18-getter|0|18-preInit
            // write pre-init user code here
exitCommand = new Command ("Exit", Command.EXIT, 0);//GEN-LINE:|18-getter|1|18-postInit
            // write post-init user code here
}//GEN-BEGIN:|18-getter|2|
return exitCommand;
}
//</editor-fold>//GEN-END:|18-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: form ">//GEN-BEGIN:|14-getter|0|14-preInit
/**
 * Returns an initiliazed instance of form component.
 * @return the initialized component instance
 */
public Form getForm () {
if (form == null) {//GEN-END:|14-getter|0|14-preInit
            // write pre-init user code here
form = new Form ("Welcome", new Item[] { getStringItem () });//GEN-BEGIN:|14-getter|1|14-postInit
form.addCommand (getExitCommand ());
form.setCommandListener (this);//GEN-END:|14-getter|1|14-postInit
            // write post-init user code here
}//GEN-BEGIN:|14-getter|2|
return form;
}
//</editor-fold>//GEN-END:|14-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: stringItem ">//GEN-BEGIN:|16-getter|0|16-preInit
/**
 * Returns an initiliazed instance of stringItem component.
 * @return the initialized component instance
 */
public StringItem getStringItem () {
if (stringItem == null) {//GEN-END:|16-getter|0|16-preInit
            // write pre-init user code here
stringItem = new StringItem ("Hello", "Hello, World!");//GEN-LINE:|16-getter|1|16-postInit
            // write post-init user code here
}//GEN-BEGIN:|16-getter|2|
return stringItem;
}
//</editor-fold>//GEN-END:|16-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: MainFeed ">//GEN-BEGIN:|22-getter|0|22-preInit
/**
 * Returns an initiliazed instance of MainFeed component.
 * @return the initialized component instance
 */
public Form getMainFeed () {
if (MainFeed == null) {//GEN-END:|22-getter|0|22-preInit
            // write pre-init user code here
MainFeed = new Form ("form1");//GEN-BEGIN:|22-getter|1|22-postInit
MainFeed.addCommand (getOptions ());
MainFeed.setCommandListener (this);//GEN-END:|22-getter|1|22-postInit
            // write post-init user code here
            new createStories(url,MainFeed,displayable,this);
}//GEN-BEGIN:|22-getter|2|
return MainFeed;
}
//</editor-fold>//GEN-END:|22-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: viewComments ">//GEN-BEGIN:|24-getter|0|24-preInit
/**
 * Returns an initiliazed instance of viewComments component.
 * @return the initialized component instance
 */
public Command getViewComments () {
if (viewComments == null) {//GEN-END:|24-getter|0|24-preInit
            // write pre-init user code here
viewComments = new Command ("Ok", Command.OK, 0);//GEN-LINE:|24-getter|1|24-postInit
            // write post-init user code here
}//GEN-BEGIN:|24-getter|2|
return viewComments;
}
//</editor-fold>//GEN-END:|24-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: Story ">//GEN-BEGIN:|23-getter|0|23-preInit
/**
 * Returns an initiliazed instance of Story component.
 * @return the initialized component instance
 */
public Form getStory () {
if (Story == null) {//GEN-END:|23-getter|0|23-preInit
            // write pre-init user code here
Story = new Form ("form1");//GEN-BEGIN:|23-getter|1|23-postInit
Story.addCommand (getOkCommand1 ());
Story.addCommand (getComment1 ());
Story.addCommand (getBackCommand ());
Story.setCommandListener (this);//GEN-END:|23-getter|1|23-postInit
            // write post-init user code here
}//GEN-BEGIN:|23-getter|2|
return Story;
}
//</editor-fold>//GEN-END:|23-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: textBox ">//GEN-BEGIN:|26-getter|0|26-preInit
/**
 * Returns an initiliazed instance of textBox component.
 * @return the initialized component instance
 */
public TextBox getTextBox () {
if (textBox == null) {//GEN-END:|26-getter|0|26-preInit
            // write pre-init user code here
textBox = new TextBox ("textBox", null, 100, TextField.ANY);//GEN-BEGIN:|26-getter|1|26-postInit
textBox.addCommand (getOkCommand ());
textBox.addCommand (getBackCommand1 ());
textBox.setCommandListener (this);//GEN-END:|26-getter|1|26-postInit
            // write post-init user code here
}//GEN-BEGIN:|26-getter|2|
return textBox;
}
//</editor-fold>//GEN-END:|26-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: CommentsMany ">//GEN-BEGIN:|27-getter|0|27-preInit
/**
 * Returns an initiliazed instance of CommentsMany component.
 * @return the initialized component instance
 */
public Form getCommentsMany () {
if (CommentsMany == null) {//GEN-END:|27-getter|0|27-preInit
            // write pre-init user code here
    
CommentsMany = new Form ("Comments", new Item[] { getTextField () });//GEN-BEGIN:|27-getter|1|27-postInit
CommentsMany.addCommand (getBackToStory ());
CommentsMany.addCommand (getAddComment ());
CommentsMany.setCommandListener (this);//GEN-END:|27-getter|1|27-postInit
        parseComments("8");

               
}//GEN-BEGIN:|27-getter|2|
return CommentsMany;
}
//</editor-fold>//GEN-END:|27-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: Comment1 ">//GEN-BEGIN:|28-getter|0|28-preInit
/**
 * Returns an initiliazed instance of Comment1 component.
 * @return the initialized component instance
 */
public Command getComment1 () {
if (Comment1 == null) {//GEN-END:|28-getter|0|28-preInit
            // write pre-init user code here
Comment1 = new Command ("Comments", Command.OK, 0);//GEN-LINE:|28-getter|1|28-postInit
            // write post-init user code here
}//GEN-BEGIN:|28-getter|2|
return Comment1;
}
//</editor-fold>//GEN-END:|28-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: backCommand ">//GEN-BEGIN:|30-getter|0|30-preInit
/**
 * Returns an initiliazed instance of backCommand component.
 * @return the initialized component instance
 */
public Command getBackCommand () {
if (backCommand == null) {//GEN-END:|30-getter|0|30-preInit
            // write pre-init user code here
backCommand = new Command ("Back", Command.BACK, 0);//GEN-LINE:|30-getter|1|30-postInit
            // write post-init user code here
}//GEN-BEGIN:|30-getter|2|
return backCommand;
}
//</editor-fold>//GEN-END:|30-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: okCommand ">//GEN-BEGIN:|32-getter|0|32-preInit
/**
 * Returns an initiliazed instance of okCommand component.
 * @return the initialized component instance
 */
public Command getOkCommand () {
if (okCommand == null) {//GEN-END:|32-getter|0|32-preInit
            // write pre-init user code here
okCommand = new Command ("send", Command.OK, 0);//GEN-LINE:|32-getter|1|32-postInit
            // write post-init user code here
}//GEN-BEGIN:|32-getter|2|
return okCommand;
}
//</editor-fold>//GEN-END:|32-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: backCommand1 ">//GEN-BEGIN:|34-getter|0|34-preInit
/**
 * Returns an initiliazed instance of backCommand1 component.
 * @return the initialized component instance
 */
public Command getBackCommand1 () {
if (backCommand1 == null) {//GEN-END:|34-getter|0|34-preInit
            // write pre-init user code here
backCommand1 = new Command ("Back", Command.BACK, 0);//GEN-LINE:|34-getter|1|34-postInit
            // write post-init user code here
}//GEN-BEGIN:|34-getter|2|
return backCommand1;
}
//</editor-fold>//GEN-END:|34-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: backToStory ">//GEN-BEGIN:|37-getter|0|37-preInit
/**
 * Returns an initiliazed instance of backToStory component.
 * @return the initialized component instance
 */
public Command getBackToStory () {
if (backToStory == null) {//GEN-END:|37-getter|0|37-preInit
            // write pre-init user code here
backToStory = new Command ("Back", Command.BACK, 0);//GEN-LINE:|37-getter|1|37-postInit
            // write post-init user code here
}//GEN-BEGIN:|37-getter|2|
return backToStory;
}
//</editor-fold>//GEN-END:|37-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: AddComment ">//GEN-BEGIN:|39-getter|0|39-preInit
/**
 * Returns an initiliazed instance of AddComment component.
 * @return the initialized component instance
 */
public Command getAddComment () {
if (AddComment == null) {//GEN-END:|39-getter|0|39-preInit
            // write pre-init user code here
AddComment = new Command ("Add", Command.OK, 0);//GEN-LINE:|39-getter|1|39-postInit
            // write post-init user code here
}//GEN-BEGIN:|39-getter|2|
return AddComment;
}
//</editor-fold>//GEN-END:|39-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: profile ">//GEN-BEGIN:|47-getter|0|47-preInit
/**
 * Returns an initiliazed instance of profile component.
 * @return the initialized component instance
 */
public Form getProfile () {
if (profile == null) {//GEN-END:|47-getter|0|47-preInit
            // write pre-init user code here
profile = new Form ("form1", new Item[] { getUserName (), getFirstName (), getLastName (), getDob (), getPas (), getConfPas () });//GEN-BEGIN:|47-getter|1|47-postInit
profile.addCommand (getOk ());
profile.addCommand (getBack ());
profile.setCommandListener (this);//GEN-END:|47-getter|1|47-postInit
            // write post-init user code here
}//GEN-BEGIN:|47-getter|2|
return profile;
}
//</editor-fold>//GEN-END:|47-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: ok ">//GEN-BEGIN:|48-getter|0|48-preInit
/**
 * Returns an initiliazed instance of ok component.
 * @return the initialized component instance
 */
public Command getOk () {
if (ok == null) {//GEN-END:|48-getter|0|48-preInit
            // write pre-init user code here
ok = new Command ("Ok", Command.OK, 0);//GEN-LINE:|48-getter|1|48-postInit
            // write post-init user code here
}//GEN-BEGIN:|48-getter|2|
return ok;
}
//</editor-fold>//GEN-END:|48-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: cancelCommand ">//GEN-BEGIN:|50-getter|0|50-preInit
/**
 * Returns an initiliazed instance of cancelCommand component.
 * @return the initialized component instance
 */
public Command getCancelCommand () {
if (cancelCommand == null) {//GEN-END:|50-getter|0|50-preInit
            // write pre-init user code here
cancelCommand = new Command ("Cancel", Command.CANCEL, 0);//GEN-LINE:|50-getter|1|50-postInit
            // write post-init user code here
}//GEN-BEGIN:|50-getter|2|
return cancelCommand;
}
//</editor-fold>//GEN-END:|50-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: back ">//GEN-BEGIN:|52-getter|0|52-preInit
/**
 * Returns an initiliazed instance of back component.
 * @return the initialized component instance
 */
public Command getBack () {
if (back == null) {//GEN-END:|52-getter|0|52-preInit
            // write pre-init user code here
back = new Command ("Back", Command.BACK, 0);//GEN-LINE:|52-getter|1|52-postInit
            // write post-init user code here
}//GEN-BEGIN:|52-getter|2|
return back;
}
//</editor-fold>//GEN-END:|52-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: userName ">//GEN-BEGIN:|54-getter|0|54-preInit
/**
 * Returns an initiliazed instance of userName component.
 * @return the initialized component instance
 */
public TextField getUserName () {
if (userName == null) {//GEN-END:|54-getter|0|54-preInit
            // write pre-init user code here
userName = new TextField ("User Name", null, 32, TextField.ANY);//GEN-LINE:|54-getter|1|54-postInit
            // write post-init user code here
}//GEN-BEGIN:|54-getter|2|
return userName;
}
//</editor-fold>//GEN-END:|54-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: firstName ">//GEN-BEGIN:|55-getter|0|55-preInit
/**
 * Returns an initiliazed instance of firstName component.
 * @return the initialized component instance
 */
public TextField getFirstName () {
if (firstName == null) {//GEN-END:|55-getter|0|55-preInit
            // write pre-init user code here
firstName = new TextField ("firstName", null, 32, TextField.ANY);//GEN-LINE:|55-getter|1|55-postInit
            // write post-init user code here
}//GEN-BEGIN:|55-getter|2|
return firstName;
}
//</editor-fold>//GEN-END:|55-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: lastName ">//GEN-BEGIN:|56-getter|0|56-preInit
/**
 * Returns an initiliazed instance of lastName component.
 * @return the initialized component instance
 */
public TextField getLastName () {
if (lastName == null) {//GEN-END:|56-getter|0|56-preInit
            // write pre-init user code here
lastName = new TextField ("Last Name", null, 32, TextField.ANY);//GEN-LINE:|56-getter|1|56-postInit
            // write post-init user code here
}//GEN-BEGIN:|56-getter|2|
return lastName;
}
//</editor-fold>//GEN-END:|56-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: dob ">//GEN-BEGIN:|57-getter|0|57-preInit
/**
 * Returns an initiliazed instance of dob component.
 * @return the initialized component instance
 */
public DateField getDob () {
if (dob == null) {//GEN-END:|57-getter|0|57-preInit
            // write pre-init user code here
dob = new DateField ("Date of Birth", DateField.DATE);//GEN-BEGIN:|57-getter|1|57-postInit
dob.setDate (new java.util.Date (System.currentTimeMillis ()));//GEN-END:|57-getter|1|57-postInit
            // write post-init user code here
}//GEN-BEGIN:|57-getter|2|
return dob;
}
//</editor-fold>//GEN-END:|57-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: pas ">//GEN-BEGIN:|58-getter|0|58-preInit
/**
 * Returns an initiliazed instance of pas component.
 * @return the initialized component instance
 */
public TextField getPas () {
if (pas == null) {//GEN-END:|58-getter|0|58-preInit
            // write pre-init user code here
pas = new TextField ("New Password", null, 32, TextField.ANY | TextField.PASSWORD | TextField.SENSITIVE | TextField.NON_PREDICTIVE);//GEN-LINE:|58-getter|1|58-postInit
            // write post-init user code here
}//GEN-BEGIN:|58-getter|2|
return pas;
}
//</editor-fold>//GEN-END:|58-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: confPas ">//GEN-BEGIN:|59-getter|0|59-preInit
/**
 * Returns an initiliazed instance of confPas component.
 * @return the initialized component instance
 */
public TextField getConfPas () {
if (confPas == null) {//GEN-END:|59-getter|0|59-preInit
            // write pre-init user code here
confPas = new TextField ("Confirm  New Password", null, 32, TextField.ANY | TextField.PASSWORD | TextField.SENSITIVE | TextField.NON_PREDICTIVE);//GEN-LINE:|59-getter|1|59-postInit
            // write post-init user code here
}//GEN-BEGIN:|59-getter|2|
return confPas;
}
//</editor-fold>//GEN-END:|59-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: options ">//GEN-BEGIN:|62-getter|0|62-preInit
/**
 * Returns an initiliazed instance of options component.
 * @return the initialized component instance
 */
public Command getOptions () {
if (options == null) {//GEN-END:|62-getter|0|62-preInit
            // write pre-init user code here
options = new Command ("Options", Command.SCREEN, 0);//GEN-LINE:|62-getter|1|62-postInit
            // write post-init user code here
}//GEN-BEGIN:|62-getter|2|
return options;
}
//</editor-fold>//GEN-END:|62-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: textField ">//GEN-BEGIN:|67-getter|0|67-preInit
/**
 * Returns an initiliazed instance of textField component.
 * @return the initialized component instance
 */
public TextField getTextField () {
if (textField == null) {//GEN-END:|67-getter|0|67-preInit
 // write pre-init user code here
textField = new TextField ("Enter Comment", null, 200, TextField.ANY);//GEN-BEGIN:|67-getter|1|67-postInit
textField.setLayout (ImageItem.LAYOUT_DEFAULT);//GEN-END:|67-getter|1|67-postInit
 // write post-init user code here
}//GEN-BEGIN:|67-getter|2|
return textField;
}
//</editor-fold>//GEN-END:|67-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: CommentFail ">//GEN-BEGIN:|68-getter|0|68-preInit
/**
 * Returns an initiliazed instance of CommentFail component.
 * @return the initialized component instance
 */
public Alert getCommentFail () {
if (CommentFail == null) {//GEN-END:|68-getter|0|68-preInit
 // write pre-init user code here
CommentFail = new Alert ("alert", "Please enter something!", null, AlertType.WARNING);//GEN-BEGIN:|68-getter|1|68-postInit
CommentFail.setIndicator (getIndicator ());
CommentFail.setTimeout (Alert.FOREVER);//GEN-END:|68-getter|1|68-postInit
 // write post-init user code here
}//GEN-BEGIN:|68-getter|2|
return CommentFail;
}
//</editor-fold>//GEN-END:|68-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: indicator ">//GEN-BEGIN:|69-getter|0|69-preInit
/**
 * Returns an initiliazed instance of indicator component.
 * @return the initialized component instance
 */
public Gauge getIndicator () {
if (indicator == null) {//GEN-END:|69-getter|0|69-preInit
 // write pre-init user code here
indicator = new Gauge (null, false, 100, 0);//GEN-LINE:|69-getter|1|69-postInit
 // write post-init user code here
}//GEN-BEGIN:|69-getter|2|
return indicator;
}
//</editor-fold>//GEN-END:|69-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: CommentSucc ">//GEN-BEGIN:|71-getter|0|71-preInit
/**
 * Returns an initiliazed instance of CommentSucc component.
 * @return the initialized component instance
 */
public Alert getCommentSucc () {
if (CommentSucc == null) {//GEN-END:|71-getter|0|71-preInit
 // write pre-init user code here
CommentSucc = new Alert ("", "Comment added Successfully!", null, AlertType.CONFIRMATION);//GEN-BEGIN:|71-getter|1|71-postInit
CommentSucc.setTimeout (Alert.FOREVER);//GEN-END:|71-getter|1|71-postInit
 // write post-init user code here
}//GEN-BEGIN:|71-getter|2|
return CommentSucc;
}
//</editor-fold>//GEN-END:|71-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: okCommand1 ">//GEN-BEGIN:|78-getter|0|78-preInit
/**
 * Returns an initiliazed instance of okCommand1 component.
 * @return the initialized component instance
 */
public Command getOkCommand1 () {
if (okCommand1 == null) {//GEN-END:|78-getter|0|78-preInit
 // write pre-init user code here
okCommand1 = new Command (" ", Command.OK, 0);//GEN-LINE:|78-getter|1|78-postInit
 // write post-init user code here
}//GEN-BEGIN:|78-getter|2|
return okCommand1;
}
//</editor-fold>//GEN-END:|78-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: connectAccount ">//GEN-BEGIN:|80-getter|0|80-preInit
/**
 * Returns an initiliazed instance of connectAccount component.
 * @return the initialized component instance
 */
public List getConnectAccount () {
if (connectAccount == null) {//GEN-END:|80-getter|0|80-preInit
        // write pre-init user code here
connectAccount = new List ("Network", Choice.IMPLICIT);//GEN-BEGIN:|80-getter|1|80-postInit
connectAccount.append ("Twitter", null);
connectAccount.append ("Facebook", null);
connectAccount.append ("Flickr", null);
connectAccount.append ("Foursquare", null);
connectAccount.append ("Tumblr", null);
connectAccount.append ("Youtube", null);
connectAccount.setCommandListener (this);
connectAccount.setSelectedFlags (new boolean[] { false, false, false, false, false, false });//GEN-END:|80-getter|1|80-postInit
        // write post-init user code here
}//GEN-BEGIN:|80-getter|2|
return connectAccount;
}
//</editor-fold>//GEN-END:|80-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Method: connectAccountAction ">//GEN-BEGIN:|80-action|0|80-preAction
/**
 * Performs an action assigned to the selected list element in the connectAccount component.
 */
public void connectAccountAction () {//GEN-END:|80-action|0|80-preAction
    // enter pre-action user code here
String __selectedString = getConnectAccount ().getString (getConnectAccount ().getSelectedIndex ());//GEN-BEGIN:|80-action|1|83-preAction
if (__selectedString != null) {
if (__selectedString.equals ("Twitter")) {//GEN-END:|80-action|1|83-preAction
            // write pre-action user code here
//GEN-LINE:|80-action|2|83-postAction
            // write post-action user code here
} else if (__selectedString.equals ("Facebook")) {//GEN-LINE:|80-action|3|84-preAction
            // write pre-action user code here
//GEN-LINE:|80-action|4|84-postAction
            // write post-action user code here
} else if (__selectedString.equals ("Flickr")) {//GEN-LINE:|80-action|5|85-preAction
            // write pre-action user code here
//GEN-LINE:|80-action|6|85-postAction
            // write post-action user code here
} else if (__selectedString.equals ("Foursquare")) {//GEN-LINE:|80-action|7|86-preAction
            // write pre-action user code here
//GEN-LINE:|80-action|8|86-postAction
            // write post-action user code here
} else if (__selectedString.equals ("Tumblr")) {//GEN-LINE:|80-action|9|87-preAction
            // write pre-action user code here
//GEN-LINE:|80-action|10|87-postAction
            // write post-action user code here
} else if (__selectedString.equals ("Youtube")) {//GEN-LINE:|80-action|11|88-preAction
            // write pre-action user code here
//GEN-LINE:|80-action|12|88-postAction
            // write post-action user code here
}//GEN-BEGIN:|80-action|13|80-postAction
}//GEN-END:|80-action|13|80-postAction
    // enter post-action user code here
}//GEN-BEGIN:|80-action|14|
//</editor-fold>//GEN-END:|80-action|14|



//<editor-fold defaultstate="collapsed" desc=" Generated Getter: search ">//GEN-BEGIN:|97-getter|0|97-preInit
/**
 * Returns an initiliazed instance of search component.
 * @return the initialized component instance
 */
public TextField getSearch () {
if (search == null) {//GEN-END:|97-getter|0|97-preInit
        // write pre-init user code here
search = new TextField ("Enter A User Name or An Email", null, 32, TextField.ANY);//GEN-LINE:|97-getter|1|97-postInit
        // write post-init user code here
}//GEN-BEGIN:|97-getter|2|
return search;
}
//</editor-fold>//GEN-END:|97-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: Add ">//GEN-BEGIN:|102-getter|0|102-preInit
/**
 * Returns an initiliazed instance of Add component.
 * @return the initialized component instance
 */
public Command getAdd () {
if (Add == null) {//GEN-END:|102-getter|0|102-preInit
        // write pre-init user code here
Add = new Command ("Screen", Command.SCREEN, 0);//GEN-LINE:|102-getter|1|102-postInit
        // write post-init user code here
}//GEN-BEGIN:|102-getter|2|
return Add;
}
//</editor-fold>//GEN-END:|102-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: findFriend ">//GEN-BEGIN:|92-getter|0|92-preInit
/**
 * Returns an initiliazed instance of findFriend component.
 * @return the initialized component instance
 */
public Form getFindFriend () {
if (findFriend == null) {//GEN-END:|92-getter|0|92-preInit
        // write pre-init user code here
findFriend = new Form ("form1", new Item[] { getSearch () });//GEN-BEGIN:|92-getter|1|92-postInit
findFriend.addCommand (getBack1 ());
findFriend.addCommand (getAdd1 ());
findFriend.setCommandListener (this);//GEN-END:|92-getter|1|92-postInit
        // write post-init user code here
}//GEN-BEGIN:|92-getter|2|
return findFriend;
}
//</editor-fold>//GEN-END:|92-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: Back1 ">//GEN-BEGIN:|93-getter|0|93-preInit
/**
 * Returns an initiliazed instance of Back1 component.
 * @return the initialized component instance
 */
public Command getBack1 () {
if (Back1 == null) {//GEN-END:|93-getter|0|93-preInit
        // write pre-init user code here
Back1 = new Command ("Back", Command.BACK, 0);//GEN-LINE:|93-getter|1|93-postInit
        // write post-init user code here
}//GEN-BEGIN:|93-getter|2|
return Back1;
}
//</editor-fold>//GEN-END:|93-getter|2|



//<editor-fold defaultstate="collapsed" desc=" Generated Getter: Error ">//GEN-BEGIN:|99-getter|0|99-preInit
/**
 * Returns an initiliazed instance of Error component.
 * @return the initialized component instance
 */
public Alert getError () {
if (Error == null) {//GEN-END:|99-getter|0|99-preInit
        // write pre-init user code here
Error = new Alert ("alert1", "Error while saving Info ", null, AlertType.ERROR);//GEN-BEGIN:|99-getter|1|99-postInit
Error.setTimeout (Alert.FOREVER);//GEN-END:|99-getter|1|99-postInit
        // write post-init user code here
}//GEN-BEGIN:|99-getter|2|
return Error;
}
//</editor-fold>//GEN-END:|99-getter|2|



//<editor-fold defaultstate="collapsed" desc=" Generated Getter: Find ">//GEN-BEGIN:|95-getter|0|95-preInit
/**
 * Returns an initiliazed instance of Find component.
 * @return the initialized component instance
 */
public Command getFind () {
if (Find == null) {//GEN-END:|95-getter|0|95-preInit
            // write pre-init user code here
Find = new Command ("findfriend", Command.OK, 0);//GEN-LINE:|95-getter|1|95-postInit
            // write post-init user code here
}//GEN-BEGIN:|95-getter|2|
return Find;
}
//</editor-fold>//GEN-END:|95-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: Add1 ">//GEN-BEGIN:|105-getter|0|105-preInit
/**
 * Returns an initiliazed instance of Add1 component.
 * @return the initialized component instance
 */
public Command getAdd1 () {
if (Add1 == null) {//GEN-END:|105-getter|0|105-preInit
            // write pre-init user code here
Add1 = new Command ("Ok", Command.OK, 0);//GEN-LINE:|105-getter|1|105-postInit
            // write post-init user code here
}//GEN-BEGIN:|105-getter|2|
return Add1;
}
//</editor-fold>//GEN-END:|105-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: Saved ">//GEN-BEGIN:|98-getter|0|98-preInit
/**
 * Returns an initiliazed instance of Saved component.
 * @return the initialized component instance
 */
public Alert getSaved () {
if (Saved == null) {//GEN-END:|98-getter|0|98-preInit
            // write pre-init user code here
Saved = new Alert ("alert", "Your Info was saved ", null, AlertType.CONFIRMATION);//GEN-BEGIN:|98-getter|1|98-postInit
Saved.setTimeout (Alert.FOREVER);//GEN-END:|98-getter|1|98-postInit
            // write post-init user code here
}//GEN-BEGIN:|98-getter|2|
return Saved;
}
//</editor-fold>//GEN-END:|98-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: Found ">//GEN-BEGIN:|100-getter|0|100-preInit
/**
 * Returns an initiliazed instance of Found component.
 * @return the initialized component instance
 */
public Alert getFound () {
if (Found == null) {//GEN-END:|100-getter|0|100-preInit
            // write pre-init user code here
Found = new Alert ("alert", "Friend Found\n", null, AlertType.CONFIRMATION);//GEN-BEGIN:|100-getter|1|100-postInit
Found.setTimeout (Alert.FOREVER);//GEN-END:|100-getter|1|100-postInit
            // write post-init user code here
}//GEN-BEGIN:|100-getter|2|
return Found;
}
//</editor-fold>//GEN-END:|100-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: NotFound ">//GEN-BEGIN:|101-getter|0|101-preInit
/**
 * Returns an initiliazed instance of NotFound component.
 * @return the initialized component instance
 */
public Alert getNotFound () {
if (NotFound == null) {//GEN-END:|101-getter|0|101-preInit
            // write pre-init user code here
NotFound = new Alert ("alert1", "Check User Name/Email", null, AlertType.ERROR);//GEN-BEGIN:|101-getter|1|101-postInit
NotFound.setTimeout (Alert.FOREVER);//GEN-END:|101-getter|1|101-postInit
            // write post-init user code here
}//GEN-BEGIN:|101-getter|2|
return NotFound;
}
//</editor-fold>//GEN-END:|101-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: EnterUserNameEmail ">//GEN-BEGIN:|104-getter|0|104-preInit
/**
 * Returns an initiliazed instance of EnterUserNameEmail component.
 * @return the initialized component instance
 */
public Alert getEnterUserNameEmail () {
if (EnterUserNameEmail == null) {//GEN-END:|104-getter|0|104-preInit
            // write pre-init user code here
EnterUserNameEmail = new Alert ("alert", "Enter A User Name/EMail", null, AlertType.ERROR);//GEN-BEGIN:|104-getter|1|104-postInit
EnterUserNameEmail.setTimeout (Alert.FOREVER);//GEN-END:|104-getter|1|104-postInit
            // write post-init user code here
}//GEN-BEGIN:|104-getter|2|
return EnterUserNameEmail;
}
//</editor-fold>//GEN-END:|104-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: ComingSoon ">//GEN-BEGIN:|107-getter|0|107-preInit
/**
 * Returns an initiliazed instance of ComingSoon component.
 * @return the initialized component instance
 */
public Alert getComingSoon () {
if (ComingSoon == null) {//GEN-END:|107-getter|0|107-preInit
            // write pre-init user code here
ComingSoon = new Alert ("Coming Soon", "This network is coming soon", null, null);//GEN-BEGIN:|107-getter|1|107-postInit
ComingSoon.setTimeout (Alert.FOREVER);//GEN-END:|107-getter|1|107-postInit
            // write post-init user code here
}//GEN-BEGIN:|107-getter|2|
return ComingSoon;
}
//</editor-fold>//GEN-END:|107-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: choosefriend ">//GEN-BEGIN:|111-getter|0|111-preInit
/**
 * Returns an initiliazed instance of choosefriend component.
 * @return the initialized component instance
 */
public Command getChoosefriend () {
if (choosefriend == null) {//GEN-END:|111-getter|0|111-preInit
            // write pre-init user code here
choosefriend = new Command ("choosefriend", Command.SCREEN, 0);//GEN-LINE:|111-getter|1|111-postInit
            // write post-init user code here
}//GEN-BEGIN:|111-getter|2|
return choosefriend;
}
//</editor-fold>//GEN-END:|111-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: backCommand3 ">//GEN-BEGIN:|114-getter|0|114-preInit
/**
 * Returns an initiliazed instance of backCommand3 component.
 * @return the initialized component instance
 */
public Command getBackCommand3 () {
if (backCommand3 == null) {//GEN-END:|114-getter|0|114-preInit
            // write pre-init user code here
backCommand3 = new Command ("Back", Command.BACK, 0);//GEN-LINE:|114-getter|1|114-postInit
            // write post-init user code here
}//GEN-BEGIN:|114-getter|2|
return backCommand3;
}
//</editor-fold>//GEN-END:|114-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: okCommand2 ">//GEN-BEGIN:|119-getter|0|119-preInit
/**
 * Returns an initiliazed instance of okCommand2 component.
 * @return the initialized component instance
 */
public Command getOkCommand2 () {
if (okCommand2 == null) {//GEN-END:|119-getter|0|119-preInit
            // write pre-init user code here
okCommand2 = new Command ("ok", Command.OK, 0);//GEN-LINE:|119-getter|1|119-postInit
            // write post-init user code here
}//GEN-BEGIN:|119-getter|2|
return okCommand2;
}
//</editor-fold>//GEN-END:|119-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: backCommand4 ">//GEN-BEGIN:|121-getter|0|121-preInit
/**
 * Returns an initiliazed instance of backCommand4 component.
 * @return the initialized component instance
 */
public Command getBackCommand4 () {
if (backCommand4 == null) {//GEN-END:|121-getter|0|121-preInit
            // write pre-init user code here
backCommand4 = new Command ("Back", Command.BACK, 0);//GEN-LINE:|121-getter|1|121-postInit
            // write post-init user code here
}//GEN-BEGIN:|121-getter|2|
return backCommand4;
}
//</editor-fold>//GEN-END:|121-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: recommend ">//GEN-BEGIN:|108-getter|0|108-preInit
/**
 * Returns an initiliazed instance of recommend component.
 * @return the initialized component instance
 */
public Form getRecommend () {
if (recommend == null) {//GEN-END:|108-getter|0|108-preInit
            // write pre-init user code here
recommend = new Form ("form1", new Item[] { getTextField2 (), getTextField3 () });//GEN-BEGIN:|108-getter|1|108-postInit
recommend.addCommand (getOkCommand ());
recommend.addCommand (getBackCommand3 ());
recommend.addCommand (getChoosefriend ());
recommend.setCommandListener (this);//GEN-END:|108-getter|1|108-postInit
            // write post-init user code here
}//GEN-BEGIN:|108-getter|2|
return recommend;
}
//</editor-fold>//GEN-END:|108-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: textField2 ">//GEN-BEGIN:|109-getter|0|109-preInit
/**
 * Returns an initiliazed instance of textField2 component.
 * @return the initialized component instance
 */
public TextField getTextField2 () {
if (textField2 == null) {//GEN-END:|109-getter|0|109-preInit
            // write pre-init user code here
textField2 = new TextField ("Email", null, 32, TextField.ANY);//GEN-LINE:|109-getter|1|109-postInit
            // write post-init user code here
}//GEN-BEGIN:|109-getter|2|
return textField2;
}
//</editor-fold>//GEN-END:|109-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: textField3 ">//GEN-BEGIN:|110-getter|0|110-preInit
/**
 * Returns an initiliazed instance of textField3 component.
 * @return the initialized component instance
 */
public TextField getTextField3 () {
if (textField3 == null) {//GEN-END:|110-getter|0|110-preInit
            // write pre-init user code here
textField3 = new TextField ("message", null, 32, TextField.ANY);//GEN-LINE:|110-getter|1|110-postInit
            // write post-init user code here
}//GEN-BEGIN:|110-getter|2|
return textField3;
}
//</editor-fold>//GEN-END:|110-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: choosefriend1 ">//GEN-BEGIN:|116-getter|0|116-preInit
/**
 * Returns an initiliazed instance of choosefriend1 component.
 * @return the initialized component instance
 */
public List getChoosefriend1 () {
if (choosefriend1 == null) {//GEN-END:|116-getter|0|116-preInit
            // write pre-init user code here
choosefriend1 = new List ("choosefriend", Choice.IMPLICIT);//GEN-BEGIN:|116-getter|1|116-postInit
choosefriend1.addCommand (getOkCommand2 ());
choosefriend1.addCommand (getBackCommand4 ());
choosefriend1.addCommand (getFind ());
choosefriend1.setCommandListener (this);//GEN-END:|116-getter|1|116-postInit
            // write post-init user code here
}//GEN-BEGIN:|116-getter|2|
return choosefriend1;
}
//</editor-fold>//GEN-END:|116-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Method: choosefriend1Action ">//GEN-BEGIN:|116-action|0|116-preAction
/**
 * Performs an action assigned to the selected list element in the choosefriend1 component.
 */
public void choosefriend1Action () {//GEN-END:|116-action|0|116-preAction
        // enter pre-action user code here
String __selectedString = getChoosefriend1 ().getString (getChoosefriend1 ().getSelectedIndex ());//GEN-LINE:|116-action|1|116-postAction
        // enter post-action user code here
}//GEN-BEGIN:|116-action|2|
//</editor-fold>//GEN-END:|116-action|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: storeypublished ">//GEN-BEGIN:|125-getter|0|125-preInit
/**
 * Returns an initiliazed instance of storeypublished component.
 * @return the initialized component instance
 */
public Alert getStoreypublished () {
if (storeypublished == null) {//GEN-END:|125-getter|0|125-preInit
            // write pre-init user code here
storeypublished = new Alert ("", "story published successfully", null, AlertType.CONFIRMATION);//GEN-BEGIN:|125-getter|1|125-postInit
storeypublished.setTimeout (Alert.FOREVER);//GEN-END:|125-getter|1|125-postInit
            // write post-init user code here
}//GEN-BEGIN:|125-getter|2|
return storeypublished;
}
//</editor-fold>//GEN-END:|125-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: storynotpublished ">//GEN-BEGIN:|126-getter|0|126-preInit
/**
 * Returns an initiliazed instance of storynotpublished component.
 * @return the initialized component instance
 */
public Alert getStorynotpublished () {
if (storynotpublished == null) {//GEN-END:|126-getter|0|126-preInit
            // write pre-init user code here
storynotpublished = new Alert ("", "story hasn\'t published , please try again later", null, AlertType.ALARM);//GEN-BEGIN:|126-getter|1|126-postInit
storynotpublished.setTimeout (Alert.FOREVER);//GEN-END:|126-getter|1|126-postInit
            // write post-init user code here
}//GEN-BEGIN:|126-getter|2|
return storynotpublished;
}
//</editor-fold>//GEN-END:|126-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: likeddisliked ">//GEN-BEGIN:|127-getter|0|127-preInit
/**
 * Returns an initiliazed instance of likeddisliked component.
 * @return the initialized component instance
 */
public List getLikeddisliked () {
if (likeddisliked == null) {//GEN-END:|127-getter|0|127-preInit
            // write pre-init user code here
likeddisliked = new List ("liked/disliked", Choice.IMPLICIT);//GEN-BEGIN:|127-getter|1|127-postInit
likeddisliked.addCommand (getBackCommand ());
likeddisliked.setCommandListener (this);//GEN-END:|127-getter|1|127-postInit
            // write post-init user code here
}//GEN-BEGIN:|127-getter|2|
return likeddisliked;
}
//</editor-fold>//GEN-END:|127-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Method: likeddislikedAction ">//GEN-BEGIN:|127-action|0|127-preAction
/**
 * Performs an action assigned to the selected list element in the likeddisliked component.
 */
public void likeddislikedAction () {//GEN-END:|127-action|0|127-preAction
        // enter pre-action user code here
String __selectedString = getLikeddisliked ().getString (getLikeddisliked ().getSelectedIndex ());//GEN-LINE:|127-action|1|127-postAction
        // enter post-action user code here
}//GEN-BEGIN:|127-action|2|
//</editor-fold>//GEN-END:|127-action|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: okCommand3 ">//GEN-BEGIN:|134-getter|0|134-preInit
/**
 * Returns an initiliazed instance of okCommand3 component.
 * @return the initialized component instance
 */
public Command getOkCommand3 () {
if (okCommand3 == null) {//GEN-END:|134-getter|0|134-preInit
            // write pre-init user code here
okCommand3 = new Command ("Ok", Command.OK, 0);//GEN-LINE:|134-getter|1|134-postInit
            // write post-init user code here
}//GEN-BEGIN:|134-getter|2|
return okCommand3;
}
//</editor-fold>//GEN-END:|134-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: backCommand5 ">//GEN-BEGIN:|136-getter|0|136-preInit
/**
 * Returns an initiliazed instance of backCommand5 component.
 * @return the initialized component instance
 */
public Command getBackCommand5 () {
if (backCommand5 == null) {//GEN-END:|136-getter|0|136-preInit
            // write pre-init user code here
backCommand5 = new Command ("Back", Command.BACK, 0);//GEN-LINE:|136-getter|1|136-postInit
            // write post-init user code here
}//GEN-BEGIN:|136-getter|2|
return backCommand5;
}
//</editor-fold>//GEN-END:|136-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: okCommand4 ">//GEN-BEGIN:|142-getter|0|142-preInit
/**
 * Returns an initiliazed instance of okCommand4 component.
 * @return the initialized component instance
 */
public Command getOkCommand4 () {
if (okCommand4 == null) {//GEN-END:|142-getter|0|142-preInit
            // write pre-init user code here
okCommand4 = new Command ("Ok", Command.OK, 0);//GEN-LINE:|142-getter|1|142-postInit
            // write post-init user code here
}//GEN-BEGIN:|142-getter|2|
return okCommand4;
}
//</editor-fold>//GEN-END:|142-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: backCommand6 ">//GEN-BEGIN:|144-getter|0|144-preInit
/**
 * Returns an initiliazed instance of backCommand6 component.
 * @return the initialized component instance
 */
public Command getBackCommand6 () {
if (backCommand6 == null) {//GEN-END:|144-getter|0|144-preInit
            // write pre-init user code here
backCommand6 = new Command ("Back", Command.BACK, 0);//GEN-LINE:|144-getter|1|144-postInit
            // write post-init user code here
}//GEN-BEGIN:|144-getter|2|
return backCommand6;
}
//</editor-fold>//GEN-END:|144-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: exitCommand1 ">//GEN-BEGIN:|146-getter|0|146-preInit
/**
 * Returns an initiliazed instance of exitCommand1 component.
 * @return the initialized component instance
 */
public Command getExitCommand1 () {
if (exitCommand1 == null) {//GEN-END:|146-getter|0|146-preInit
            // write pre-init user code here
exitCommand1 = new Command ("Exit", Command.EXIT, 0);//GEN-LINE:|146-getter|1|146-postInit
            // write post-init user code here
}//GEN-BEGIN:|146-getter|2|
return exitCommand1;
}
//</editor-fold>//GEN-END:|146-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: LoginScreen ">//GEN-BEGIN:|131-getter|0|131-preInit
/**
 * Returns an initiliazed instance of LoginScreen component.
 * @return the initialized component instance
 */
public Form getLoginScreen () {
if (LoginScreen == null) {//GEN-END:|131-getter|0|131-preInit
            // write pre-init user code here
LoginScreen = new Form ("LoginScreen", new Item[] { getTextField4 (), getTextField5 () });//GEN-BEGIN:|131-getter|1|131-postInit
LoginScreen.addCommand (getOkCommand3 ());
LoginScreen.addCommand (getBackCommand5 ());
LoginScreen.setCommandListener (this);//GEN-END:|131-getter|1|131-postInit
            // write post-init user code here
}//GEN-BEGIN:|131-getter|2|
return LoginScreen;
}
//</editor-fold>//GEN-END:|131-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: textField4 ">//GEN-BEGIN:|132-getter|0|132-preInit
/**
 * Returns an initiliazed instance of textField4 component.
 * @return the initialized component instance
 */
public TextField getTextField4 () {
if (textField4 == null) {//GEN-END:|132-getter|0|132-preInit
            // write pre-init user code here
textField4 = new TextField ("Email", null, 32, TextField.ANY);//GEN-LINE:|132-getter|1|132-postInit
            // write post-init user code here
}//GEN-BEGIN:|132-getter|2|
return textField4;
}
//</editor-fold>//GEN-END:|132-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: textField5 ">//GEN-BEGIN:|133-getter|0|133-preInit
/**
 * Returns an initiliazed instance of textField5 component.
 * @return the initialized component instance
 */
public TextField getTextField5 () {
if (textField5 == null) {//GEN-END:|133-getter|0|133-preInit
            // write pre-init user code here
textField5 = new TextField ("Password", null, 32, TextField.ANY);//GEN-LINE:|133-getter|1|133-postInit
            // write post-init user code here
}//GEN-BEGIN:|133-getter|2|
return textField5;
}
//</editor-fold>//GEN-END:|133-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: okCommand5 ">//GEN-BEGIN:|152-getter|0|152-preInit
/**
 * Returns an initiliazed instance of okCommand5 component.
 * @return the initialized component instance
 */
public Command getOkCommand5 () {
if (okCommand5 == null) {//GEN-END:|152-getter|0|152-preInit
            // write pre-init user code here
okCommand5 = new Command ("Ok", Command.OK, 0);//GEN-LINE:|152-getter|1|152-postInit
            // write post-init user code here
}//GEN-BEGIN:|152-getter|2|
return okCommand5;
}
//</editor-fold>//GEN-END:|152-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: backCommand7 ">//GEN-BEGIN:|154-getter|0|154-preInit
/**
 * Returns an initiliazed instance of backCommand7 component.
 * @return the initialized component instance
 */
public Command getBackCommand7 () {
if (backCommand7 == null) {//GEN-END:|154-getter|0|154-preInit
            // write pre-init user code here
backCommand7 = new Command ("Back", Command.BACK, 0);//GEN-LINE:|154-getter|1|154-postInit
            // write post-init user code here
}//GEN-BEGIN:|154-getter|2|
return backCommand7;
}
//</editor-fold>//GEN-END:|154-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: RegisterScreen ">//GEN-BEGIN:|148-getter|0|148-preInit
/**
 * Returns an initiliazed instance of RegisterScreen component.
 * @return the initialized component instance
 */
public Form getRegisterScreen () {
if (RegisterScreen == null) {//GEN-END:|148-getter|0|148-preInit
            // write pre-init user code here
RegisterScreen = new Form ("RegisterScreen", new Item[] { getTextField6 (), getTextField7 (), getTextField8 () });//GEN-BEGIN:|148-getter|1|148-postInit
RegisterScreen.addCommand (getOkCommand5 ());
RegisterScreen.addCommand (getBackCommand7 ());
RegisterScreen.setCommandListener (this);//GEN-END:|148-getter|1|148-postInit
            // write post-init user code here
}//GEN-BEGIN:|148-getter|2|
return RegisterScreen;
}
//</editor-fold>//GEN-END:|148-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: textField6 ">//GEN-BEGIN:|149-getter|0|149-preInit
/**
 * Returns an initiliazed instance of textField6 component.
 * @return the initialized component instance
 */
public TextField getTextField6 () {
if (textField6 == null) {//GEN-END:|149-getter|0|149-preInit
            // write pre-init user code here
textField6 = new TextField ("Email", null, 32, TextField.ANY);//GEN-LINE:|149-getter|1|149-postInit
            // write post-init user code here
}//GEN-BEGIN:|149-getter|2|
return textField6;
}
//</editor-fold>//GEN-END:|149-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: textField7 ">//GEN-BEGIN:|150-getter|0|150-preInit
/**
 * Returns an initiliazed instance of textField7 component.
 * @return the initialized component instance
 */
public TextField getTextField7 () {
if (textField7 == null) {//GEN-END:|150-getter|0|150-preInit
            // write pre-init user code here
textField7 = new TextField ("Password", null, 32, TextField.ANY);//GEN-LINE:|150-getter|1|150-postInit
            // write post-init user code here
}//GEN-BEGIN:|150-getter|2|
return textField7;
}
//</editor-fold>//GEN-END:|150-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: textField8 ">//GEN-BEGIN:|151-getter|0|151-preInit
/**
 * Returns an initiliazed instance of textField8 component.
 * @return the initialized component instance
 */
public TextField getTextField8 () {
if (textField8 == null) {//GEN-END:|151-getter|0|151-preInit
            // write pre-init user code here
textField8 = new TextField ("Confirm Password", null, 32, TextField.ANY);//GEN-LINE:|151-getter|1|151-postInit
            // write post-init user code here
}//GEN-BEGIN:|151-getter|2|
return textField8;
}
//</editor-fold>//GEN-END:|151-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: backCommand8 ">//GEN-BEGIN:|158-getter|0|158-preInit
/**
 * Returns an initiliazed instance of backCommand8 component.
 * @return the initialized component instance
 */
public Command getBackCommand8 () {
if (backCommand8 == null) {//GEN-END:|158-getter|0|158-preInit
            // write pre-init user code here
backCommand8 = new Command ("Back", Command.BACK, 0);//GEN-LINE:|158-getter|1|158-postInit
            // write post-init user code here
}//GEN-BEGIN:|158-getter|2|
return backCommand8;
}
//</editor-fold>//GEN-END:|158-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: okCommand6 ">//GEN-BEGIN:|160-getter|0|160-preInit
/**
 * Returns an initiliazed instance of okCommand6 component.
 * @return the initialized component instance
 */
public Command getOkCommand6 () {
if (okCommand6 == null) {//GEN-END:|160-getter|0|160-preInit
            // write pre-init user code here
okCommand6 = new Command ("Ok", Command.OK, 0);//GEN-LINE:|160-getter|1|160-postInit
            // write post-init user code here
}//GEN-BEGIN:|160-getter|2|
return okCommand6;
}
//</editor-fold>//GEN-END:|160-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: form1 ">//GEN-BEGIN:|156-getter|0|156-preInit
/**
 * Returns an initiliazed instance of form1 component.
 * @return the initialized component instance
 */
public Form getForm1 () {
if (form1 == null) {//GEN-END:|156-getter|0|156-preInit
            // write pre-init user code here
form1 = new Form ("ForgotPasswordScreen", new Item[] { getTextField9 () });//GEN-BEGIN:|156-getter|1|156-postInit
form1.addCommand (getBackCommand8 ());
form1.addCommand (getOkCommand6 ());
form1.setCommandListener (this);//GEN-END:|156-getter|1|156-postInit
            // write post-init user code here
}//GEN-BEGIN:|156-getter|2|
return form1;
}
//</editor-fold>//GEN-END:|156-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: textField9 ">//GEN-BEGIN:|157-getter|0|157-preInit
/**
 * Returns an initiliazed instance of textField9 component.
 * @return the initialized component instance
 */
public TextField getTextField9 () {
if (textField9 == null) {//GEN-END:|157-getter|0|157-preInit
            // write pre-init user code here
textField9 = new TextField ("Email", null, 32, TextField.ANY);//GEN-LINE:|157-getter|1|157-postInit
            // write post-init user code here
}//GEN-BEGIN:|157-getter|2|
return textField9;
}
//</editor-fold>//GEN-END:|157-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: Resend ">//GEN-BEGIN:|169-getter|0|169-preInit
/**
 * Returns an initiliazed instance of Resend component.
 * @return the initialized component instance
 */
public Command getResend () {
if (Resend == null) {//GEN-END:|169-getter|0|169-preInit
            // write pre-init user code here
Resend = new Command ("Resend", Command.SCREEN, 1);//GEN-LINE:|169-getter|1|169-postInit
            // write post-init user code here
}//GEN-BEGIN:|169-getter|2|
return Resend;
}
//</editor-fold>//GEN-END:|169-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: Verify ">//GEN-BEGIN:|167-getter|0|167-preInit
/**
 * Returns an initiliazed instance of Verify component.
 * @return the initialized component instance
 */
public Command getVerify () {
if (Verify == null) {//GEN-END:|167-getter|0|167-preInit
            // write pre-init user code here
Verify = new Command ("Verify", Command.SCREEN, 1);//GEN-LINE:|167-getter|1|167-postInit
            // write post-init user code here
}//GEN-BEGIN:|167-getter|2|
return Verify;
}
//</editor-fold>//GEN-END:|167-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: backV ">//GEN-BEGIN:|165-getter|0|165-preInit
/**
 * Returns an initiliazed instance of backV component.
 * @return the initialized component instance
 */
public Command getBackV () {
if (backV == null) {//GEN-END:|165-getter|0|165-preInit
            // write pre-init user code here
backV = new Command ("Back", Command.BACK, 1);//GEN-LINE:|165-getter|1|165-postInit
            // write post-init user code here
}//GEN-BEGIN:|165-getter|2|
return backV;
}
//</editor-fold>//GEN-END:|165-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: IncorrectCode ">//GEN-BEGIN:|171-getter|0|171-preInit
/**
 * Returns an initiliazed instance of IncorrectCode component.
 * @return the initialized component instance
 */
public Alert getIncorrectCode () {
if (IncorrectCode == null) {//GEN-END:|171-getter|0|171-preInit
            // write pre-init user code here
IncorrectCode = new Alert ("Incorrect Verification", "Incorrect verification code", null, AlertType.ERROR);//GEN-BEGIN:|171-getter|1|171-postInit
IncorrectCode.setIndicator (getIndicator1 ());
IncorrectCode.setTimeout (Alert.FOREVER);//GEN-END:|171-getter|1|171-postInit
            // write post-init user code here
}//GEN-BEGIN:|171-getter|2|
return IncorrectCode;
}
//</editor-fold>//GEN-END:|171-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: indicator1 ">//GEN-BEGIN:|175-getter|0|175-preInit
/**
 * Returns an initiliazed instance of indicator1 component.
 * @return the initialized component instance
 */
public Gauge getIndicator1 () {
if (indicator1 == null) {//GEN-END:|175-getter|0|175-preInit
            // write pre-init user code here
indicator1 = new Gauge (null, false, 100, 50);//GEN-LINE:|175-getter|1|175-postInit
            // write post-init user code here
}//GEN-BEGIN:|175-getter|2|
return indicator1;
}
//</editor-fold>//GEN-END:|175-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: ResentAlert ">//GEN-BEGIN:|174-getter|0|174-preInit
/**
 * Returns an initiliazed instance of ResentAlert component.
 * @return the initialized component instance
 */
public Alert getResentAlert () {
if (ResentAlert == null) {//GEN-END:|174-getter|0|174-preInit
            // write pre-init user code here
ResentAlert = new Alert ("Code Resent", "The verification code has been sent to your email", null, AlertType.CONFIRMATION);//GEN-BEGIN:|174-getter|1|174-postInit
ResentAlert.setIndicator (getIndicator3 ());
ResentAlert.setTimeout (Alert.FOREVER);//GEN-END:|174-getter|1|174-postInit
            // write post-init user code here
}//GEN-BEGIN:|174-getter|2|
return ResentAlert;
}
//</editor-fold>//GEN-END:|174-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: indicator3 ">//GEN-BEGIN:|178-getter|0|178-preInit
/**
 * Returns an initiliazed instance of indicator3 component.
 * @return the initialized component instance
 */
public Gauge getIndicator3 () {
if (indicator3 == null) {//GEN-END:|178-getter|0|178-preInit
            // write pre-init user code here
indicator3 = new Gauge (null, false, 100, 50);//GEN-LINE:|178-getter|1|178-postInit
            // write post-init user code here
}//GEN-BEGIN:|178-getter|2|
return indicator3;
}
//</editor-fold>//GEN-END:|178-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: VerifiedAlert ">//GEN-BEGIN:|173-getter|0|173-preInit
/**
 * Returns an initiliazed instance of VerifiedAlert component.
 * @return the initialized component instance
 */
public Alert getVerifiedAlert () {
if (VerifiedAlert == null) {//GEN-END:|173-getter|0|173-preInit
            // write pre-init user code here
VerifiedAlert = new Alert ("Account Verified", "your account has been successfully verified", null, AlertType.CONFIRMATION);//GEN-BEGIN:|173-getter|1|173-postInit
VerifiedAlert.setIndicator (getIndicator2 ());
VerifiedAlert.setTimeout (Alert.FOREVER);//GEN-END:|173-getter|1|173-postInit
            // write post-init user code here
}//GEN-BEGIN:|173-getter|2|
return VerifiedAlert;
}
//</editor-fold>//GEN-END:|173-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: indicator2 ">//GEN-BEGIN:|177-getter|0|177-preInit
/**
 * Returns an initiliazed instance of indicator2 component.
 * @return the initialized component instance
 */
public Gauge getIndicator2 () {
if (indicator2 == null) {//GEN-END:|177-getter|0|177-preInit
            // write pre-init user code here
indicator2 = new Gauge (null, false, 100, 50);//GEN-LINE:|177-getter|1|177-postInit
            // write post-init user code here
}//GEN-BEGIN:|177-getter|2|
return indicator2;
}
//</editor-fold>//GEN-END:|177-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: InvalidCode ">//GEN-BEGIN:|172-getter|0|172-preInit
/**
 * Returns an initiliazed instance of InvalidCode component.
 * @return the initialized component instance
 */
public Alert getInvalidCode () {
if (InvalidCode == null) {//GEN-END:|172-getter|0|172-preInit
            // write pre-init user code here
InvalidCode = new Alert ("Invalid Verification", "The verification code can\'t be less than 4 characters", null, AlertType.ERROR);//GEN-BEGIN:|172-getter|1|172-postInit
InvalidCode.setIndicator (getIndicator4 ());
InvalidCode.setTimeout (Alert.FOREVER);//GEN-END:|172-getter|1|172-postInit
            // write post-init user code here
}//GEN-BEGIN:|172-getter|2|
return InvalidCode;
}
//</editor-fold>//GEN-END:|172-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: indicator4 ">//GEN-BEGIN:|179-getter|0|179-preInit
/**
 * Returns an initiliazed instance of indicator4 component.
 * @return the initialized component instance
 */
public Gauge getIndicator4 () {
if (indicator4 == null) {//GEN-END:|179-getter|0|179-preInit
            // write pre-init user code here
indicator4 = new Gauge (null, false, 100, 50);//GEN-LINE:|179-getter|1|179-postInit
            // write post-init user code here
}//GEN-BEGIN:|179-getter|2|
return indicator4;
}
//</editor-fold>//GEN-END:|179-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: Verification ">//GEN-BEGIN:|162-getter|0|162-preInit
/**
 * Returns an initiliazed instance of Verification component.
 * @return the initialized component instance
 */
public Form getVerification () {
if (Verification == null) {//GEN-END:|162-getter|0|162-preInit
            // write pre-init user code here
Verification = new Form ("form2", new Item[] { getVTF (), getVSI () });//GEN-BEGIN:|162-getter|1|162-postInit
Verification.addCommand (getBackV ());
Verification.addCommand (getVerify ());
Verification.addCommand (getResend ());
Verification.setCommandListener (this);//GEN-END:|162-getter|1|162-postInit
            // write post-init user code here
}//GEN-BEGIN:|162-getter|2|
return Verification;
}
//</editor-fold>//GEN-END:|162-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: vTF ">//GEN-BEGIN:|163-getter|0|163-preInit
/**
 * Returns an initiliazed instance of vTF component.
 * @return the initialized component instance
 */
public TextField getVTF () {
if (vTF == null) {//GEN-END:|163-getter|0|163-preInit
            // write pre-init user code here
vTF = new TextField ("Enter verification code:", "", 4, TextField.ANY);//GEN-LINE:|163-getter|1|163-postInit
            // write post-init user code here
}//GEN-BEGIN:|163-getter|2|
return vTF;
}
//</editor-fold>//GEN-END:|163-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: vSI ">//GEN-BEGIN:|164-getter|0|164-preInit
/**
 * Returns an initiliazed instance of vSI component.
 * @return the initialized component instance
 */
public StringItem getVSI () {
if (vSI == null) {//GEN-END:|164-getter|0|164-preInit
            // write pre-init user code here
vSI = new StringItem ("", "Press the \'Resend\' button to resend the verification code to your registered   e-mail adrress.");//GEN-LINE:|164-getter|1|164-postInit
            // write post-init user code here
}//GEN-BEGIN:|164-getter|2|
return vSI;
}
//</editor-fold>//GEN-END:|164-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: FriendsStories ">//GEN-BEGIN:|180-getter|0|180-preInit
/**
 * Returns an initiliazed instance of FriendsStories component.
 * @return the initialized component instance
 */
public Form getFriendsStories () {
if (FriendsStories == null) {//GEN-END:|180-getter|0|180-preInit
            // write pre-init user code here
FriendsStories = new Form ("FriendsStories");//GEN-LINE:|180-getter|1|180-postInit
            // write post-init user code here
}//GEN-BEGIN:|180-getter|2|
return FriendsStories;
}
//</editor-fold>//GEN-END:|180-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: list ">//GEN-BEGIN:|181-getter|0|181-preInit
/**
 * Returns an initiliazed instance of list component.
 * @return the initialized component instance
 */
public List getList () {
if (list == null) {//GEN-END:|181-getter|0|181-preInit
            // write pre-init user code here
list = new List ("list", Choice.IMPLICIT);//GEN-BEGIN:|181-getter|1|181-postInit
list.setCommandListener (this);//GEN-END:|181-getter|1|181-postInit
            // write post-init user code here
}//GEN-BEGIN:|181-getter|2|
return list;
}
//</editor-fold>//GEN-END:|181-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Method: listAction ">//GEN-BEGIN:|181-action|0|181-preAction
/**
 * Performs an action assigned to the selected list element in the list component.
 */
public void listAction () {//GEN-END:|181-action|0|181-preAction
        // enter pre-action user code here
String __selectedString = getList ().getString (getList ().getSelectedIndex ());//GEN-LINE:|181-action|1|181-postAction
        // enter post-action user code here
}//GEN-BEGIN:|181-action|2|
//</editor-fold>//GEN-END:|181-action|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: backCommand9 ">//GEN-BEGIN:|185-getter|0|185-preInit
/**
 * Returns an initiliazed instance of backCommand9 component.
 * @return the initialized component instance
 */
public Command getBackCommand9 () {
if (backCommand9 == null) {//GEN-END:|185-getter|0|185-preInit
            // write pre-init user code here
backCommand9 = new Command ("Back", Command.BACK, 0);//GEN-LINE:|185-getter|1|185-postInit
            // write post-init user code here
}//GEN-BEGIN:|185-getter|2|
return backCommand9;
}
//</editor-fold>//GEN-END:|185-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: okCommand7 ">//GEN-BEGIN:|187-getter|0|187-preInit
/**
 * Returns an initiliazed instance of okCommand7 component.
 * @return the initialized component instance
 */
public Command getOkCommand7 () {
if (okCommand7 == null) {//GEN-END:|187-getter|0|187-preInit
            // write pre-init user code here
okCommand7 = new Command ("  ", Command.OK, 0);//GEN-LINE:|187-getter|1|187-postInit
            // write post-init user code here
}//GEN-BEGIN:|187-getter|2|
return okCommand7;
}
//</editor-fold>//GEN-END:|187-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: thumbup ">//GEN-BEGIN:|189-getter|0|189-preInit
/**
 * Returns an initiliazed instance of thumbup component.
 * @return the initialized component instance
 */
public Command getThumbup () {
if (thumbup == null) {//GEN-END:|189-getter|0|189-preInit
            // write pre-init user code here
thumbup = new Command ("Thumb Up", Command.OK, 0);//GEN-LINE:|189-getter|1|189-postInit
            // write post-init user code here
}//GEN-BEGIN:|189-getter|2|
return thumbup;
}
//</editor-fold>//GEN-END:|189-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: thumbdown ">//GEN-BEGIN:|191-getter|0|191-preInit
/**
 * Returns an initiliazed instance of thumbdown component.
 * @return the initialized component instance
 */
public Command getThumbdown () {
if (thumbdown == null) {//GEN-END:|191-getter|0|191-preInit
            // write pre-init user code here
thumbdown = new Command ("Thumb Down", Command.OK, 0);//GEN-LINE:|191-getter|1|191-postInit
            // write post-init user code here
}//GEN-BEGIN:|191-getter|2|
return thumbdown;
}
//</editor-fold>//GEN-END:|191-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: flag ">//GEN-BEGIN:|193-getter|0|193-preInit
/**
 * Returns an initiliazed instance of flag component.
 * @return the initialized component instance
 */
public Command getFlag () {
if (flag == null) {//GEN-END:|193-getter|0|193-preInit
            // write pre-init user code here
flag = new Command ("Flag", Command.OK, 0);//GEN-LINE:|193-getter|1|193-postInit
            // write post-init user code here
}//GEN-BEGIN:|193-getter|2|
return flag;
}
//</editor-fold>//GEN-END:|193-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: share ">//GEN-BEGIN:|195-getter|0|195-preInit
/**
 * Returns an initiliazed instance of share component.
 * @return the initialized component instance
 */
public Command getShare () {
if (share == null) {//GEN-END:|195-getter|0|195-preInit
            // write pre-init user code here
share = new Command ("Share", Command.OK, 0);//GEN-LINE:|195-getter|1|195-postInit
            // write post-init user code here
}//GEN-BEGIN:|195-getter|2|
return share;
}
//</editor-fold>//GEN-END:|195-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: recommend1 ">//GEN-BEGIN:|197-getter|0|197-preInit
/**
 * Returns an initiliazed instance of recommend1 component.
 * @return the initialized component instance
 */
public Command getRecommend1 () {
if (recommend1 == null) {//GEN-END:|197-getter|0|197-preInit
            // write pre-init user code here
recommend1 = new Command ("Recommend", Command.OK, 0);//GEN-LINE:|197-getter|1|197-postInit
            // write post-init user code here
}//GEN-BEGIN:|197-getter|2|
return recommend1;
}
//</editor-fold>//GEN-END:|197-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: blockinterest ">//GEN-BEGIN:|199-getter|0|199-preInit
/**
 * Returns an initiliazed instance of blockinterest component.
 * @return the initialized component instance
 */
public Command getBlockinterest () {
if (blockinterest == null) {//GEN-END:|199-getter|0|199-preInit
            // write pre-init user code here
blockinterest = new Command ("Block Interest", Command.OK, 0);//GEN-LINE:|199-getter|1|199-postInit
            // write post-init user code here
}//GEN-BEGIN:|199-getter|2|
return blockinterest;
}
//</editor-fold>//GEN-END:|199-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: blockstory ">//GEN-BEGIN:|201-getter|0|201-preInit
/**
 * Returns an initiliazed instance of blockstory component.
 * @return the initialized component instance
 */
public Command getBlockstory () {
if (blockstory == null) {//GEN-END:|201-getter|0|201-preInit
            // write pre-init user code here
blockstory = new Command ("Block Story", Command.OK, 0);//GEN-LINE:|201-getter|1|201-postInit
            // write post-init user code here
}//GEN-BEGIN:|201-getter|2|
return blockstory;
}
//</editor-fold>//GEN-END:|201-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: signout ">//GEN-BEGIN:|203-getter|0|203-preInit
/**
 * Returns an initiliazed instance of signout component.
 * @return the initialized component instance
 */
public Command getSignout () {
if (signout == null) {//GEN-END:|203-getter|0|203-preInit
            // write pre-init user code here
signout = new Command ("Sign out", Command.OK, 0);//GEN-LINE:|203-getter|1|203-postInit
            // write post-init user code here
}//GEN-BEGIN:|203-getter|2|
return signout;
}
//</editor-fold>//GEN-END:|203-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: readMore ">//GEN-BEGIN:|184-getter|0|184-preInit
/**
 * Returns an initiliazed instance of readMore component.
 * @return the initialized component instance
 */
public Form getReadMore () {
if (readMore == null) {//GEN-END:|184-getter|0|184-preInit
            // write pre-init user code here
readMore = new Form ("form2", new Item[] { getImageItem () });//GEN-BEGIN:|184-getter|1|184-postInit
readMore.addCommand (getBackCommand9 ());
readMore.addCommand (getOkCommand7 ());
readMore.addCommand (getThumbup ());
readMore.addCommand (getThumbdown ());
readMore.addCommand (getFlag ());
readMore.addCommand (getShare ());
readMore.addCommand (getRecommend1 ());
readMore.addCommand (getBlockinterest ());
readMore.addCommand (getBlockstory ());
readMore.addCommand (getSignout ());
readMore.setCommandListener (this);//GEN-END:|184-getter|1|184-postInit
            // write post-init user code here
            rm();
}//GEN-BEGIN:|184-getter|2|
return readMore;
}
//</editor-fold>//GEN-END:|184-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: imageItem ">//GEN-BEGIN:|205-getter|0|205-preInit
/**
 * Returns an initiliazed instance of imageItem component.
 * @return the initialized component instance
 */
public ImageItem getImageItem () {
if (imageItem == null) {//GEN-END:|205-getter|0|205-preInit
            // write pre-init user code here
imageItem = new ImageItem ("", getImage1 (), ImageItem.LAYOUT_DEFAULT, "<Missing Image>");//GEN-LINE:|205-getter|1|205-postInit
            // write post-init user code here
}//GEN-BEGIN:|205-getter|2|
return imageItem;
}
//</editor-fold>//GEN-END:|205-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: image1 ">//GEN-BEGIN:|206-getter|0|206-preInit
/**
 * Returns an initiliazed instance of image1 component.
 * @return the initialized component instance
 */
public Image getImage1 () {
if (image1 == null) {//GEN-END:|206-getter|0|206-preInit
            // write pre-init user code here
image1 = Image.createImage (1, 1);//GEN-LINE:|206-getter|1|206-postInit
            // write post-init user code here
}//GEN-BEGIN:|206-getter|2|
return image1;
}
//</editor-fold>//GEN-END:|206-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: CommentOne ">//GEN-BEGIN:|207-getter|0|207-preInit
/**
 * Returns an initiliazed instance of CommentOne component.
 * @return the initialized component instance
 */
public Form getCommentOne () {
if (CommentOne == null) {//GEN-END:|207-getter|0|207-preInit
 // write pre-init user code here
CommentOne = new Form ("form2");//GEN-BEGIN:|207-getter|1|207-postInit
CommentOne.addCommand (getBackToComments ());
CommentOne.addCommand (getLike ());
CommentOne.addCommand (getDislike ());
CommentOne.setCommandListener (this);//GEN-END:|207-getter|1|207-postInit
 // write post-init user code here
}//GEN-BEGIN:|207-getter|2|
return CommentOne;
}
//</editor-fold>//GEN-END:|207-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: backToComments ">//GEN-BEGIN:|208-getter|0|208-preInit
/**
 * Returns an initiliazed instance of backToComments component.
 * @return the initialized component instance
 */
public Command getBackToComments () {
if (backToComments == null) {//GEN-END:|208-getter|0|208-preInit
 // write pre-init user code here
backToComments = new Command ("Back", Command.BACK, 0);//GEN-LINE:|208-getter|1|208-postInit
 // write post-init user code here
}//GEN-BEGIN:|208-getter|2|
return backToComments;
}
//</editor-fold>//GEN-END:|208-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: Like ">//GEN-BEGIN:|210-getter|0|210-preInit
/**
 * Returns an initiliazed instance of Like component.
 * @return the initialized component instance
 */
public Command getLike () {
if (Like == null) {//GEN-END:|210-getter|0|210-preInit
 // write pre-init user code here
Like = new Command ("Item", Command.ITEM, 0);//GEN-LINE:|210-getter|1|210-postInit
 // write post-init user code here
}//GEN-BEGIN:|210-getter|2|
return Like;
}
//</editor-fold>//GEN-END:|210-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: Dislike ">//GEN-BEGIN:|212-getter|0|212-preInit
/**
 * Returns an initiliazed instance of Dislike component.
 * @return the initialized component instance
 */
public Command getDislike () {
if (Dislike == null) {//GEN-END:|212-getter|0|212-preInit
 // write pre-init user code here
Dislike = new Command ("Item", Command.ITEM, 0);//GEN-LINE:|212-getter|1|212-postInit
 // write post-init user code here
}//GEN-BEGIN:|212-getter|2|
return Dislike;
}
//</editor-fold>//GEN-END:|212-getter|2|












    
 
   

    
    
    /**
     * Returns a display instance.
     * @return the display instance.
     */
    public Display getDisplay() {
        return Display.getDisplay(this);
    }

    /**
     * Exits MIDlet.
     */
    public void exitMIDlet() {
        switchDisplayable(null, null);
        destroyApp(true);
        notifyDestroyed();
    }

    /**
     * Called when MIDlet is started.
     * Checks whether the MIDlet have been already started and initialize/starts or resumes the MIDlet.
     */
    public void startApp() {
        if (midletPaused) {
            resumeMIDlet();
        } else {
            initialize();
            startMIDlet();
        }
        midletPaused = false;
    }

    /**
     * Called when MIDlet is paused.
     */
    public void pauseApp() {
        midletPaused = true;
    }

    /**
     * Called to signal the MIDlet to terminate.
     * @param unconditional if true, then the MIDlet has to be unconditionally terminated and all resources has to be released.
     */
    public void destroyApp(boolean unconditional) {
    }

    
    
    public void httpconn() /*this method should intiate the connection between the server and the mobile client which
                           should return the json file of list of stories according to the client interests.*/
    {
          HttpConnection httpConn = null;
      String url = "http://192.168.26.136:3000/users/1.json";  

    InputStream is = null;
    OutputStream os = null;

    try {
      // Open an HTTP Connection object
      httpConn = (HttpConnection)Connector.open(url);

      // Setup HTTP Request
      httpConn.setRequestMethod(HttpConnection.GET);
      httpConn.setRequestProperty("User-Agent",
        "Profile/MIDP-1.0 Confirguration/CLDC-1.0");


      int respCode = httpConn.getResponseCode();
      if (respCode == httpConn.HTTP_OK) {
        StringBuffer sb = new StringBuffer();
        os = httpConn.openOutputStream();
        is = httpConn.openDataInputStream();
        int chr;
        while ((chr = is.read()) != -1)
          sb.append((char) chr);

       json =sb.toString();
        System.out.println( sb.toString());
      }
      else {
        System.out.println("Error in opening HTTP Connection. Error#" + respCode);
      }}catch(Exception e){
          
      

      } finally {
        if(is!= null)
            try {
                is.close();
            } catch (IOException ex) {
            }
          if(os != null)
            try {
                os.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
      if(httpConn != null)
            try {
                httpConn.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
     
    }
    }

    public String getTwitterAuthURL(String serverIP, int port) {
        HttpConnection httpConn = null;
        String url = "http://" + serverIP + ":" + port + 
                "/authenticate/get_twitter_url";
        System.out.println(url);
        InputStream is = null;
        OutputStream os = null;

        try {
            // Open an HTTP Connection object
            httpConn = (HttpConnection) Connector.open(url);

            // Setup HTTP Request
            httpConn.setRequestMethod(HttpConnection.GET);
            httpConn.setRequestProperty("User-Agent",
                    "Profile/MIDP-1.1 Confirguration/CLDC-1.0");


            int respCode = httpConn.getResponseCode();
            if (respCode == httpConn.HTTP_OK) {
                StringBuffer sb = new StringBuffer();
                os = httpConn.openOutputStream();
                is = httpConn.openDataInputStream();
                int chr;
                while ((chr = is.read()) != -1) {
                    sb.append((char) chr);
                }

                System.out.println(sb.toString());
                return sb.toString();
            } else {
                System.out.println("Error in opening HTTP Connection. Error#" + respCode);
            }
        } catch (Exception e) {

            e.printStackTrace();

        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
            if (os != null) {
                try {
                    os.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
            if (httpConn != null) {
                try {
                    httpConn.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
        
        return "n/a";
    }
     public static String getInterests() /*this method should intiate the connection between the server and the mobile client which
                         should return the json file of list of stories according to the client interests.*/
    {
       
          HttpConnection httpConn = null;
      String url = "http://192.168.26.145:3000/userinterests?id=5&format=json" ;  

    InputStream is = null;
    OutputStream os = null;

    try {
      // Open an HTTP Connection object
      httpConn = (HttpConnection)Connector.open(url);

      // Setup HTTP Request
      httpConn.setRequestMethod(HttpConnection.GET);
      httpConn.setRequestProperty("User-Agent",
        "Profile/MIDP-1.0 Confirguration/CLDC-1.0");


      int respCode = httpConn.getResponseCode();
      if (respCode == httpConn.HTTP_OK) {
        StringBuffer sb = new StringBuffer();
        os = httpConn.openOutputStream();
        is = httpConn.openDataInputStream();
        int chr;
        while ((chr = is.read()) != -1)
          sb.append((char) chr);

    String x = sb.toString();
    
        System.out.println( sb.toString());
        return x;
      }
      else {
        System.out.println("Error in opening HTTP Connection. Error#" + respCode);
      }}catch(Exception e){
          
      

      } finally {
        if(is!= null)
            try {
                is.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
          if(os != null)
            try {
                os.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
      if(httpConn != null)
            try {
                httpConn.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
     
    }
    return "";

    }
        public  void parseJsonInterest(String x) // user to parse json interests
{
    String interest = "";
    for(int i=0; i<x.length(); i++)
    {
        interest ="";
        if(x.charAt(i) == ':')
        {
            i = i+2;
            while(x.charAt(i)!= '"')
            {
                interest = interest + x.charAt(i);
                i++;
               
            }
            list.append(interest, null);
            System.out.println(interest);
             //list.append(interest, null);
        }
    }
}
        // view one of the comments of a certain story
    public void viewCommentOne(String id,String user,String content,String ups,String downs){
      //  getCommentOne().
        commentItem com = new commentItem(id, user, content, ups, downs, 1);
       
        getCommentOne().append(com);
         switchDisplayable(null, getCommentOne());
    }
    // parse comments list comming from server
    public void parseComments(String storyID){
    HttpConnection httpConn = null;
      String url = "http://192.168.1.3:3000/stories/"+storyID+"/comments" ;  
     // String urltest = "http://192.168.1.3:3000/comments/8";
      String jsonS = getData(url);  
      System.out.println(jsonS);
      commentItem [] comments;
   //   CommentsMany.append(new commentItem(json,this));
   //   switchDisplayable(null, CommentsMany);
   // sendData("http://192.168.1.3:3000/stories/:id/comments/downc", "{\"user_id\":\"3\",\"comment_id\":\"1\"}");
      try {
			JSONObject json = new JSONObject(jsonS);
			
			JSONArray jsonArray = json.getJSONArray("Comments");
			int total = jsonArray.length();
		
                        comments = new commentItem[total];
			for (int i=0;i<total;i++) {
				String commJson = jsonArray.getString(i);
				comments[i] = new commentItem(commJson,this);
				CommentsMany.append(comments[i]);
			}
                        switchDisplayable(null, CommentsMany);
			
		} catch (JSONException ex) {
			ex.printStackTrace();
		}
        
    
    
    }
   public  void putInterests ()
{
    String x = getInterests();
    parseJsonInterest(x);
}
   
     public void dummydata()
   {
       list.append("History", null);
       list.append("sports", null);
       
   }
   
   
   public void filter(String interest){
   for(int i=0;i<MainFeed.size();i++){
        if (MainFeed.get(i) instanceof storyItem){
          storyItem temp =(storyItem) MainFeed.get(i);
            if(temp.storyCategory.getText() != interest){
               MainFeed.delete(i);
           }
        }
   }
}
    boolean flagfriend=false;
    boolean flaglike=false;
    boolean flagdislike=false;
   
   public String storyopt() throws IOException  {

    HttpConnection httpConn = null;
     HttpConnection httpCon = null;
      HttpConnection httpConnn = null;
      
      String mess_url = "http://192.168.1.1:3000/stories/recommend_story?mess";
    String like_url = "http://192.168.1.1:3000/stories/view_friends_like_dislike?listlike";
    String dislike_url = "http://192.168.1.1:3000/stories/view_friends_like_dislike?listdislike";
    InputStream is = null;
    OutputStream os = null;
    InputStream in = null;
    OutputStream on = null;
    InputStream it = null;
    OutputStream ot = null;

    try {
      // Open an HTTP Connection object
      httpConn = (HttpConnection)Connector.open(url);
      httpCon = (HttpConnection)Connector.open(like_url);
      httpConnn = (HttpConnection)Connector.open(dislike_url);
      
      // Setup HTTP Request
      httpConn.setRequestMethod(HttpConnection.GET); 
      httpConn.setRequestProperty("User-Agent",
        "Profile/MIDP-1.0 Confirguration/CLDC-1.0");

       httpCon.setRequestMethod(HttpConnection.GET); 
      httpCon.setRequestProperty("User-Agent",
        "Profile/MIDP-1.0 Confirguration/CLDC-1.0");
      
      httpConnn.setRequestMethod(HttpConnection.GET); 
      httpConnn.setRequestProperty("User-Agent",
        "Profile/MIDP-1.0 Confirguration/CLDC-1.0");
      
      
      int respCode = httpConn.getResponseCode();
      int respCoden = httpConn.getResponseCode();
      int respCodenn = httpConn.getResponseCode();
      if (respCode == httpConn.HTTP_OK) {
        StringBuffer sb = new StringBuffer();
        os = httpConn.openOutputStream();
        is = httpConn.openDataInputStream();
        int chr;
        flagfriend=true;
        while ((chr = is.read()) != -1)
          sb.append((char) chr);

        sb.toString();
         String choosefriend = sb.toString();
        return choosefriend;
      }
      else { 
          if (respCoden == httpCon.HTTP_OK) {
        StringBuffer sb = new StringBuffer();
        os = httpConn.openOutputStream();
        is = httpConn.openDataInputStream();
        int chr;
        flaglike=true;
        while ((chr = is.read()) != -1)
          sb.append((char) chr);

         sb.toString();
      String liked  = sb.toString();
      return liked;
      }
      else {
         
               if (respCodenn == httpConnn.HTTP_OK) {
        StringBuffer sb = new StringBuffer();
        os = httpConn.openOutputStream();
        is = httpConn.openDataInputStream();
        int chr;
        flagdislike=true;
        while ((chr = is.read()) != -1)
          sb.append((char) chr);

        sb.toString();
        String disliked  = sb.toString();
        return disliked;
      }
      else {
        System.out.println("Error in opening HTTP Connection. Error#" + respCode);
      }
      }

     } 
  
    }finally {
        if(is!= null)
            try {
                is.close();
            } catch (IOException ex) {
            }
          if(os != null)
            try {
                os.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
      if(httpConn != null)
            try {
                httpConn.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
     
    }
return "I was missing a return statement!"; //added since there was a syntax error "missing return statement!"

}
    
    
    public  void parseJsonfriends(String choosefriend) // user to parse json frindlist
{
    String friend = "";
    for(int i=0; i<choosefriend.length(); i++)
    {
        friend ="";
        if(choosefriend.charAt(i)=='"' || choosefriend.charAt(i)=='{' || choosefriend.charAt(i)=='}' || choosefriend.charAt(i) == ':'){
             
            }else{
                friend=friend+choosefriend.charAt(i);
            }
            choosefriend1.append(friend, null);
           
        }
    }

     public  void parseJsonfriendsliked(String choosefriend) // user to parse json frindlist
{
    String friend = "";
    for(int i=0; i<choosefriend.length(); i++)
    {
        friend ="";
        if(choosefriend.charAt(i)=='"' || choosefriend.charAt(i)=='{' || choosefriend.charAt(i)=='}' || choosefriend.charAt(i) == ':'){
             
            }else{
                friend=friend+choosefriend.charAt(i);
            }
            likeddisliked.append(friend, null);
           
        }
    }
    
     
      public  void parseJsonfriendsdisliked(String choosefriend) // user to parse json frindlist
{
    String friend = "";
    for(int i=0; i<choosefriend.length(); i++)
    {
        friend ="";
        if(choosefriend.charAt(i)=='"' || choosefriend.charAt(i)=='{' || choosefriend.charAt(i)=='}' || choosefriend.charAt(i) == ':'){
             
            }else{
                friend=friend+choosefriend.charAt(i);
            }
            likeddisliked.append(friend, null);
           
        }
    }
   public  void insertfriendsintolist () throws IOException
{
    String x = storyopt();
    if(flagfriend){
    parseJsonfriends(x);
}
    else{
        if(flaglike){
            parseJsonfriendsliked(x);
        }
        else{
            if(flagdislike){
                parseJsonfriendsdisliked(x);
            }
        }
        
    }
    
    
}
   

    
}
