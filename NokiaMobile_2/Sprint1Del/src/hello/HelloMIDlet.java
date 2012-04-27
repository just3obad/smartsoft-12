
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hello;

import javax.microedition.io.ConnectionNotFoundException;
import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;
import com.nokia.mid.ui.LCDUIUtil;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Calendar;
import java.util.Date;
import java.util.Vector;
import javax.microedition.io.Connector;
import javax.microedition.io.HttpConnection;
import org.json.me.*;
import regexp.RE;

/**
 * @author Essam Hafez
 */
public class HelloMIDlet extends MIDlet implements CommandListener {

    // YAHIA : i added those for sake of testing
    //static String SERVER_IP = "172.20.10.4";
    static String SERVER_IP = "172.20.10.3";
    static int PORT = 3000;
    // YAHIA END <-- lol..Menisy! :p
    String url;
    int currentStoryID;
    int countInsertion = 0;
    private boolean midletPaused = false;
    Displayable displayable;
    String json;
    //  int user_id; // The user id of logged in 
    String currentStoryString;
    int userID;
    String message ;
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
    private Command Block;
    private Command FilterFriends;
    private Command okCommand8;
    private Command Filter;
    private Command BackToAccounts;
    private Command ReplaceTwitterAccount;
    private Command backCommand2;
    private Command unFilter;
    private Command friendsLike;
    private Command friendsDislike;
    private Command Resend1;
    private Command backCommand10;
    private Command okCommand11;
    private Command okCommand9;
    private Command backCommand11;
    private Command singOut;
    private Command okCommand10;
    private Command reject;
    private Command accept;
    private Command backCommand12;
    private Command goToVerification;
    private Command backCommand14;
    private Command backCommand13;
    private Command choose;
    private Command viewCommentsMany;
    private Command FilterStories1;
    private Command FilterStories;
    private Command backCommand15;
    private Command registerCommand;
    private Command okCommand13;
    private Command backCommand17;
    private Command okCommand14;
    private Command okCommand12;
    private Command resendPasswordCommand;
    private Command backCommand16;
    private Command connectSocialAccount;
    private Command backToMainFeed;
    private Command editInfo;
    private Command find1;
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
    private List liked;
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
    private Alert ResentAlert;
    private Alert VerifiedAlert;
    private Alert InvalidCode;
    private Form Verification;
    private TextField vTF;
    private StringItem vSI;
    private Form readMore;
    private ImageItem imageItem;
    private Form FriendsStories;
    private List list;
    private List list1;
    private List FriendList;
    private List authTwitter;
    private Form alreadyHaveTwitter;
    private StringItem stringItem2;
    private List disliked;
    private Form authSuccessful;
    private StringItem stringItem3;
    private Form twitterAuthFailed;
    private StringItem stringItem4;
    private Alert URLCorrupted;
    private Form manyReq;
    private Form oneReq;
    private Form Toggle;
    private ChoiceGroup choiceGroup;
    private Form interestConfirm;
    private Alert StorynotFound;
    private Form Dummy;
    private Alert PasswordsDontMatch;
    private Alert WrongEmailPassCombination;
    private Alert WrongEmailFormat;
    private Alert commentSent;
    private Alert UppedBefore;
    private Alert emptyFields;
    private Alert passMissMatch;
    private Alert alert1;
    private Alert ServerError;
    private Alert alert;
    private Alert AlreadyVerified;
    private Alert InternetError;
    private Alert UserExists;
    private Alert PasswordLessThan6Chars;
    private Form friendlist;
    private ChoiceGroup choiceGroup1;
    private Alert UserDoesntExist;
    private Form ResendPassword;
    private TextField textField1;
    private Alert CommentFailed;
    private Alert DownedBefore;
    private Alert alert2;
    private Alert PleaseTryAgain;
    private Alert miniPass;
    private Alert errorProfile;
    private Alert savedProfile;
    private Alert emailisnotincorrectformat;
    private Image image1;
    //</editor-fold>//GEN-END:|fields|0|
    private HttpConnection httpConn;
    private OutputStream os;
    public boolean internetConn;
    public String checkConnURL = "http://www.google.com";
    public Alert connAlert;
    public String serverURL;
    public boolean serverConn, before;
    public HttpConnection httpCheckConn1;
    public InputStream checkConnIS1;
    public OutputStream checkConnOS1;
    public String response1;
//    public Form temp = new Form("");
    //ti5r
    Vector interests ,user;
    static String json1;// = "[\"sports\" ,\"cars\" ,\"nature\" ,{\"created_at\":\"2012-04-22T21:31:19Z\",\"name\":\"sports\",\"updated_at\":\"2012-04-22T21:31:19Z\",\"deleted\":null,\"description\":\"shfgsgsgts\",\"id\":1,\"image\":\"http://www.floral-directory.com/flower.gif\",\"name\":\"science\",\"updated_at\":\"2012-04-22T21:31:19Z\",\"name\":\"nature\",\"updated_at\":\"2012-04-22T21:31:19Z\",\"name\":\"cars\",\"updated_at\":\"2012-04-22T21:31:19Z\",\"name\":\"music\",\"updated_at\":\"2012-04-22T21:31:19Z\",\"name\":\"arts\",\"updated_at\":\"2012-04-22T21:31:19Z\"}]";

    /**
     * The HelloMIDlet constructor.
     */
    public HelloMIDlet() {
    }

    public void getUserID(String details){
        int ind = details.indexOf("\"id\":");
        ind += 5;
        String ret = "";
        while(details.charAt(ind) != ','){
            System.out.println(details.charAt(ind));
            ret = ret + details.charAt(ind);
            ind++;
        }
        System.out.println(ret);
        int reti = Integer.parseInt(ret);
        userID = reti;
    }
    
    public Image getImage1(String s) {
        if (image1 == null) {
            try {
                image1 = Image.createImage(s);
            } catch (java.io.IOException e) {
                e.printStackTrace();
            }
        }
        return image1;
    }

    public void jsonReadMoree() { //fill in readmore form with content of story item
        //currentStoryString
        //String url = "id: 1 title:\"FaceBook \" body To launch the high quality TV channel TNT in Belgium we placed a big red push button on an average Flemish square of an average Flemish town. A sign with the...http://www.3run.co.uk/ - Home of the 3RUN Famly World Wide http://www.3runshop.com/ - Free Running Trainers, Clothing, DVD's, Bags, Accessories. 3RUN  : rank 5 image : /x.png category : arts";
        //String s = url;  
        //
        String[] s = split(currentStoryString, "~");
        //s[0] = title  s[1] = image s[2] = rank s[3] = body s[4] = category s[5] = id
        if (s.length == 0 || s == null) {
            switchDisplayable(getAlert1(), displayable);
        } else {

            Image addedImage = null;
            try {
                addedImage = loadImage(s[1]); // adds image from internet
            } catch (Exception e) {
                System.out.println("Cannot find image");
                try {
                    addedImage = Image.createImage("/x.png"); // if error happened, add a local presented image
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }


            //readMore.append("\n");
            readMore.append(s[0].toUpperCase());
            readMore.append("\n");
            readMore.append("related to:  " + s[4]);
            readMore.append("\n");
            readMore.append(addedImage);
            readMore.append("\n");
            readMore.append("with ranking:   " + s[2]);
            readMore.append("\n");
            readMore.append("\n");
            readMore.append(": " + s[3]);

        }
    }

    public void rm() {
        jsonReadMoree();
    }

    public String recieveData(String url) { //method to get data from server
        InputStream is = null;
        OutputStream os = null;
        String r = null;
        url+="&format=json";
        

        try {
            // Open an HTTP Connection object
            httpConn = (HttpConnection) Connector.open(url);

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
                while ((chr = is.read()) != -1) {
                    sb.append((char) chr);
                }
                r = sb.toString();
            } else {
                System.out.println("Error in opening HTTP Connection. Error#" + respCode);
            }

        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (is != null) {
                    is.close();
                }
                if (os != null) {
                    os.close();
                }
                if (httpConn != null) {
                    httpConn.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return r;
    }

    public void friendsConnection() { //method to get friends_feed

        String url = "http://"+SERVER_IP+":"+PORT+"/users/friends_feed?id=" +userID;
        String friendsStories = recieveData(url);
        createStories st = new createStories(friendsStories, FriendsStories, displayable, this);
    }

    public void up(String comment_id) {
     //   currentStoryID = 8; // for testing
        String s = sendData("http://"+SERVER_IP+":3000/stories/"+currentStoryID+"/comments/upc", "{\"comment_id\":\"" + comment_id + "\",\"user_id\":\"" + userID + "\"}");
     //   CommentsMany = null;  //make null to re-init
     //   before = true; //to re-init textfield in the CommentsMany form
     //   Dummy = null;  //make null to re-init
        if(getTextField().getString().length()!=0)
        textField.delete(0, getTextField().getString().length()); //cleat text in textfield
        CommentsMany.deleteAll();
                   // CommentsMany = null;
      CommentsMany.append(getTextField());
      parseComments(currentStoryID+"");
                //    switchDisplayable(null, getCommentsMany());
       if(s.equals("ok"))
        switchDisplayable(null, getCommentsMany()); // switch to dummy display
        else{
        switchDisplayable(getUppedBefore(), getCommentsMany()); // switch to dummy display and show alert
        }

    }

    public void down(String comment_id) {
   //     currentStoryID = 8; // for testing
       String s =  sendData("http://"+SERVER_IP+":3000/stories/"+currentStoryID+"/comments/downc", "{\"comment_id\":\"" + comment_id + "\",\"user_id\":\"" + userID + "\"}");
   //     CommentsMany = null;  //make null to re-init
    //    before = true;  //to re-init textfield in the CommentsMany form
   //     Dummy = null;  //make null to re-init
       if(getTextField().getString().length()!=0)
       textField.delete(0, getTextField().getString().length()); //cleat text in textfield
       
        CommentsMany.deleteAll();
                   // CommentsMany = null;
      CommentsMany.append(getTextField());
      parseComments(currentStoryID+"");
                //    switchDisplayable(null, getCommentsMany());
       if(s.equals("ok"))
        switchDisplayable(null, getCommentsMany()); // switch to dummy display
        else{
        switchDisplayable(getDownedBefore(), getCommentsMany()); // switch to dummy display and show alert
       }
    }
// This method is to send data to a specific ip in the json format
    public String sendData(String ip, String data) {


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
            InputStream is = null;
              os.flush();//data written will be flushed to server.
              is = httpConn.openDataInputStream();
            System.out.println(httpConn.getResponseCode());
            System.out.println(dataToBeSend);
            
            StringBuffer sb = new StringBuffer();
             //   os = httpConn.openOutputStream();
                
                int chr;
                while ((chr = is.read()) != -1) {
                    sb.append((char) chr);
                }


                System.out.println(sb.toString());
                return sb.toString();

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
        return "";
    }
// This Method us responsible for getting a json string from a given ip, to save reusing the code to get ths json string
    public String getData(String ip) {
        String ret = "";
        HttpConnection httpConn = null;
        String url = ip + ".json";

        InputStream is = null;
        OutputStream os = null;

        try {
            // Open an HTTP Connection object
            httpConn = (HttpConnection) Connector.open(url);

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
                while ((chr = is.read()) != -1) {
                    sb.append((char) chr);
                }


                System.out.println(sb.toString());
                ret = sb.toString();
            } else {
                System.out.println("Error in opening HTTP Connection. Error#" + respCode);
            }
        } catch (Exception e) {
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
        return ret;
    }

    //<editor-fold defaultstate="collapsed" desc=" Generated Methods ">//GEN-BEGIN:|methods|0|
    //</editor-fold>//GEN-END:|methods|0|
    //<editor-fold defaultstate="collapsed" desc=" Generated Method: initialize ">//GEN-BEGIN:|0-initialize|0|0-preInitialize
    /**
     * Initializes the application.
     * It is called only once when the MIDlet is started. The method is called before the <code>startMIDlet</code> method.
     */
    private void initialize() {//GEN-END:|0-initialize|0|0-preInitialize
        // write pre-initialize user code here
//GEN-LINE:|0-initialize|1|0-postInitialize
        // write post-initialize user code here
    }//GEN-BEGIN:|0-initialize|2|
    //</editor-fold>//GEN-END:|0-initialize|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Method: startMIDlet ">//GEN-BEGIN:|3-startMIDlet|0|3-preAction
    /**
     * Performs an action assigned to the Mobile Device - MIDlet Started point.
     */
    public void startMIDlet() {//GEN-END:|3-startMIDlet|0|3-preAction
        // write pre-action user code here
        switchDisplayable(null, getLoginScreen());//GEN-LINE:|3-startMIDlet|1|3-postAction
        // write post-action user code here
    }//GEN-BEGIN:|3-startMIDlet|2|
    //</editor-fold>//GEN-END:|3-startMIDlet|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Method: resumeMIDlet ">//GEN-BEGIN:|4-resumeMIDlet|0|4-preAction
    /**
     * Performs an action assigned to the Mobile Device - MIDlet Resumed point.
     */
    public void resumeMIDlet() {//GEN-END:|4-resumeMIDlet|0|4-preAction
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
    public void switchDisplayable(Alert alert, Displayable nextDisplayable) {//GEN-END:|5-switchDisplayable|0|5-preSwitch
        // write pre-switch user code here
        Display display = getDisplay();//GEN-BEGIN:|5-switchDisplayable|1|5-postSwitch
        if (alert == null) {
            display.setCurrent(nextDisplayable);
        } else {
            display.setCurrent(alert, nextDisplayable);
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
    public void commandAction(Command command, Displayable displayable) {//GEN-END:|7-commandAction|0|7-preCommandAction
        // write pre-action user code here
        this.displayable = displayable;
        if (displayable == CommentsMany) {//GEN-BEGIN:|7-commandAction|1|40-preAction
            if (command == AddComment) {//GEN-END:|7-commandAction|1|40-preAction
                // write pre-action user code here
//GEN-LINE:|7-commandAction|2|40-postAction
                // write post-action user code here
                String comment = getTextField().getString();

                if (comment.length() == 0) {
                    switchDisplayable(getCommentFail(), displayable);  //show failure alert
                } else {
                //    currentStoryID = 8; //for testing
                //    user_id = 3; //for testing
                    String url = "http://"+SERVER_IP+":3000/stories/" + currentStoryID + "/comments/new";
                    String data = "{\"user_id\":\"" + userID + "\",\"story_id\":\"" + currentStoryID + "\",\"content\":\"" + comment + "\"}";
                 String s =  sendData(url, data);
                    textField.delete(0, getTextField().getString().length()); //cleat text in textfield
                     CommentsMany.deleteAll();
                   // CommentsMany = null;
                    CommentsMany.append(getTextField());
                    parseComments(currentStoryID+"");
             //       if(s.equals("ok"))
                    switchDisplayable(getCommentSent(), getCommentsMany());
                //    else
                //    switchDisplayable(getCommentFailed(),getCommentsMany());
               // 
            } else if (command == backToStory) {//GEN-LINE:|7-commandAction|3|38-preAction
                // write pre-action user code here
                switchDisplayable(null, getReadMore());//GEN-LINE:|7-commandAction|4|38-postAction
                // write post-action user code here
            }//GEN-BEGIN:|7-commandAction|5|239-preAction
        } else if (displayable == FriendList) {
            if (command == Block) {//GEN-END:|7-commandAction|5|239-preAction
                // write pre-action user code here
                if (!checkInternetConn()) {
                    switchDisplayable(getInternetError(), getVerification()); //internet alert
                    return;
                }

                if (!checkServerConn()) {
                    switchDisplayable(getServerError(), getVerification()); //server alert
                    return;
                }
                String r = recieveData("http://"+SERVER_IP+":"+PORT+"/users/block_friends_feed?id=" +userID);
                switchDisplayable(null, getMainFeed());//GEN-LINE:|7-commandAction|6|239-postAction
                // write post-action user code here
            } else if (command == Filter) {//GEN-LINE:|7-commandAction|7|241-preAction
                // write pre-action user code here
                if (!checkInternetConn()) {
                    switchDisplayable(getInternetError(), getVerification()); //internet alert
                    return;
                }

                if (!checkServerConn()) {
                    switchDisplayable(getServerError(), getVerification()); //server alert
                    return;
                }
                friendsConnection();
                switchDisplayable(null, getFriendsStories());//GEN-LINE:|7-commandAction|8|241-postAction
                // write post-action user code here
            } else if (command == List.SELECT_COMMAND) {//GEN-LINE:|7-commandAction|9|230-preAction
                // write pre-action user code here
                FriendListAction();//GEN-LINE:|7-commandAction|10|230-postAction
                // write post-action user code here
            }//GEN-BEGIN:|7-commandAction|11|137-preAction
        } else if (displayable == LoginScreen) {
            if (command == backCommand5) {//GEN-END:|7-commandAction|11|137-preAction
                // write pre-action user code here
//GEN-LINE:|7-commandAction|12|137-postAction
                // write post-action user code here
            } else if (command == okCommand3) {//GEN-LINE:|7-commandAction|13|135-preAction
                // write pre-action user code here
                String user_details = "";
                RE regex = new RE("^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)"
                        + "*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
                if (regex.match(textField4.getString())) {
                    HttpConnection httpConn = null;
                    InputStream is = null;
                    OutputStream os = null;
                    try {
                        //Change IP accordingly
                        httpConn = (HttpConnection) Connector.open("http://"
                                +SERVER_IP+":"+String.valueOf(PORT)+
                                "/h_accounts/sign_in.json");
                        //Request method has to be POST
                        httpConn.setRequestMethod(HttpConnection.POST);
                        httpConn.setRequestProperty("User-Agent",
                                "Profile/MIDP-1.0 Confirguration/CLDC-1.0");
                        httpConn.setRequestProperty("Accept_Language", "en-US");
                        //Content-Type is must to pass parameters in POST Request must be application/json
                        httpConn.setRequestProperty("Content-Type", "application/json");
                        // JSON String that you will send containing the attributes needed for sign up.
                        String dataToBeSend = "{\"email\":\"" + textField4.getString()
                                + "\",\"password\":\"" + textField5.getString() + "\"}";
                        httpConn.setRequestProperty("Content-Length",
                                "" + dataToBeSend.length());
                        os = httpConn.openOutputStream();
                        os.write(dataToBeSend.getBytes());
                        os.flush();//data written will be flushed to server.
                        int respCode = httpConn.getResponseCode();
                        System.out.println(respCode);
                        System.out.println(dataToBeSend);
                        if(respCode == 200 || respCode == 201){
                                StringBuffer sb = new StringBuffer();
                                os = httpConn.openOutputStream();
                                is = httpConn.openDataInputStream();
                                int chr;
                                while ((chr = is.read()) != -1){
                                    sb.append((char) chr);
                                }
                                System.out.println(sb.toString());
                                user_details = sb.toString();
                                getUserID(user_details);
                        }
                        else if(respCode == 400 || respCode == 422){
                            //TODO wrong pass
                            textField5.setString("");
                            switchDisplayable(getWrongEmailPassCombination(),displayable);
                            return;
                        } else if(respCode == 500){
                            //TODO user doesn't exist
                            switchDisplayable(this.getPleaseTryAgain(),displayable);
                            return;
                        }
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
                } else {
                    //TODO wrong email format login
                    switchDisplayable(getWrongEmailFormat(), displayable);
                    return;
                }
                switchDisplayable(null, getMainFeed());//GEN-LINE:|7-commandAction|14|135-postAction
                // write post-action user code here
            } else if (command == registerCommand) {//GEN-LINE:|7-commandAction|15|395-preAction
                // write pre-action user code here
                switchDisplayable(null, getRegisterScreen());//GEN-LINE:|7-commandAction|16|395-postAction
                // write post-action user code here
            } else if (command == resendPasswordCommand) {//GEN-LINE:|7-commandAction|17|428-preAction
                // write pre-action user code here
                switchDisplayable(null, getResendPassword());//GEN-LINE:|7-commandAction|18|428-postAction
                // write post-action user code here
            }//GEN-BEGIN:|7-commandAction|19|233-preAction
        } else if (displayable == MainFeed) {
            if (command == FilterFriends) {//GEN-END:|7-commandAction|19|233-preAction
                // write pre-action user code here
                switchDisplayable(null, getFriendList());//GEN-LINE:|7-commandAction|20|233-postAction
                // write post-action user code here
            } else if (command == FilterStories1) {//GEN-LINE:|7-commandAction|21|365-preAction
                // write pre-action user code here
                switchDisplayable(null, getList());//GEN-LINE:|7-commandAction|22|365-postAction
                // write post-action user code here
            } else if (command == connectSocialAccount) {//GEN-LINE:|7-commandAction|23|420-preAction
                // write pre-action user code here
                switchDisplayable(null, getConnectAccount());//GEN-LINE:|7-commandAction|24|420-postAction
                // write post-action user code here
            } else if (command == editInfo) {//GEN-LINE:|7-commandAction|25|465-preAction
                // write pre-action user code here
                switchDisplayable(null, getProfile());//GEN-LINE:|7-commandAction|26|465-postAction
                // write post-action user code here
            } else if (command == goToVerification) {//GEN-LINE:|7-commandAction|27|370-preAction
                // write pre-action user code here
                switchDisplayable(null, getVerification());//GEN-LINE:|7-commandAction|28|370-postAction
                // write post-action user code here
            } else if (command == options) {//GEN-LINE:|7-commandAction|29|63-preAction
                // write pre-action user code here
                switchDisplayable(null, getToggle());//GEN-LINE:|7-commandAction|30|63-postAction
                // write post-action user code here
            }//GEN-BEGIN:|7-commandAction|31|155-preAction
        } else if (displayable == RegisterScreen) {
            if (command == backCommand7) {//GEN-END:|7-commandAction|31|155-preAction
                // write pre-action user code here
                switchDisplayable(null, getLoginScreen());//GEN-LINE:|7-commandAction|32|155-postAction
                // write post-action user code here
            } else if (command == okCommand5) {//GEN-LINE:|7-commandAction|33|153-preAction
                // write pre-action user code here
                 String user_details = "";
                RE regex = new RE("^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
                if (regex.match(textField6.getString())) {
                    if(textField7.getString().equals(textField8.getString())){
                        if(textField7.getString().length() >= 6){
                    HttpConnection httpConn = null;
                    InputStream is = null;
                    OutputStream os = null;

                    try {
                        //Change IP accordingly
                        httpConn = (HttpConnection) Connector.open("http://"+SERVER_IP+":"
                                +String.valueOf(PORT)+"/users/new.json");
                        //Request method has to be POST
                        httpConn.setRequestMethod(HttpConnection.POST);
                        httpConn.setRequestProperty("User-Agent",
                                "Profile/MIDP-1.0 Confirguration/CLDC-1.0");
                        httpConn.setRequestProperty("Accept_Language", "en-US");
                        //Content-Type is must to pass parameters in POST Request must be application/json
                        httpConn.setRequestProperty("Content-Type", "application/json");
                        // JSON String that you will send containing the attributes needed for sign up.

                        String dataToBeSend = "{\"email\":\"" + textField6.getString()
                                + "\",\"password\":\"" + textField7.getString() + "\"}";
                        httpConn.setRequestProperty("Content-Length",
                                "" + dataToBeSend.length());
                        os = httpConn.openOutputStream();
                        os.write(dataToBeSend.getBytes());
                        os.flush();//data written will be flushed to server.
                        int respCode = httpConn.getResponseCode();
                        System.out.println(respCode);
                        System.out.println(dataToBeSend);

                        if(respCode == 200 || respCode == 201){
                                StringBuffer sb = new StringBuffer();
                                os = httpConn.openOutputStream();
                                is = httpConn.openDataInputStream();
                                int chr;
                                while ((chr = is.read()) != -1){
                                    sb.append((char) chr);
                                }
                                System.out.println(sb.toString());
                                user_details = sb.toString();
                                getUserID(user_details);
                        }
                        else if(respCode == 400){
                            //TODO wrong pass
                            textField7.setString("");
                            textField8.setString("");
                            switchDisplayable(getPasswordsDontMatch(), displayable);
                            return;
                        }
                        else if(respCode == 500){
                            //TODO user exists
                            switchDisplayable(getUserExists(), displayable);
                            return;
                        }
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
                        } else{
                            //TODO pass less than 6 characters
                            textField7.setString("");
                            textField8.setString("");
                            switchDisplayable(getPasswordLessThan6Chars(), displayable);
                            return;
                        }
                    } else{
                        //TODO passes don't match
                        textField7.setString("");
                        textField8.setString("");
                        switchDisplayable(getPasswordsDontMatch(),displayable);
                        return;
                    }
                } else {
                    //TODO wrong email format register
                    switchDisplayable(getWrongEmailFormat(), displayable);
                    return;
                }help();
                 if(json1 != null && json1.length() > 3) {
                //OMAR CODE
                     switchDisplayable(null, getToggle());//GEN-LINE:|7-commandAction|34|153-postAction
                // write post-action user code here
            }
                 else
                     switchDisplayable(getAlert2(), getLoginScreen());
            }//GEN-BEGIN:|7-commandAction|35|431-preAction
        } else if (displayable == ResendPassword) {
            if (command == backCommand16) {//GEN-END:|7-commandAction|35|431-preAction
                // write pre-action user code here
                switchDisplayable(null, getLoginScreen());//GEN-LINE:|7-commandAction|36|431-postAction
                // write post-action user code here
            } else if (command == okCommand12) {//GEN-LINE:|7-commandAction|37|434-preAction
                // write pre-action user code here
                RE regex = new RE("^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)"
                        + "*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
                if (regex.match(textField1.getString())) {
                    HttpConnection httpConn = null;
                    InputStream is = null;
                    OutputStream os = null;

                    try {
                        //Change IP accordingly
                        httpConn = (HttpConnection) Connector.open("http://"
                                +SERVER_IP+":"+String.valueOf(PORT)+
                                "/h_accounts/password.json");
                        //Request method has to be POST
                        httpConn.setRequestMethod(HttpConnection.POST);
                        httpConn.setRequestProperty("User-Agent",
                                "Profile/MIDP-1.0 Confirguration/CLDC-1.0");
                        httpConn.setRequestProperty("Accept_Language", "en-US");
                        //Content-Type is must to pass parameters in POST Request must be application/json
                        httpConn.setRequestProperty("Content-Type", "application/json");
                        // JSON String that you will send containing the attributes needed for sign up.

                        String dataToBeSend = "{\"email\":\"" + textField1.getString()+"\"}";
                        httpConn.setRequestProperty("Content-Length",
                                "" + dataToBeSend.length());
                        os = httpConn.openOutputStream();
                        os.write(dataToBeSend.getBytes());
                        os.flush();//data written will be flushed to server.
                        int respCode = httpConn.getResponseCode();
                        System.out.println(respCode);
                        System.out.println(dataToBeSend);

                        if(respCode == 200){
                                StringBuffer sb = new StringBuffer();
                                os = httpConn.openOutputStream();
                                is = httpConn.openDataInputStream();
                                int chr;
                                while ((chr = is.read()) != -1){
                                    sb.append((char) chr);
                                }
                                System.out.println(sb.toString());
                        }
                        if(respCode == 400){
                            //TODO wrong pass
                            switchDisplayable(getWrongEmailPassCombination(),displayable);
                            return;
                        }
                        if(respCode == 500){
                            //TODO user doesn't exist
                            switchDisplayable(getUserDoesntExist(), displayable);
                            return;
                        }
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
                } else {
                    //TODO wrong email format login
                    textField1.setString("");
                    switchDisplayable(getWrongEmailFormat(), displayable);
                }
//GEN-LINE:|7-commandAction|38|434-postAction
                // write post-action user code here
            }//GEN-BEGIN:|7-commandAction|39|29-preAction
        } else if (displayable == Story) {
            if (command == Comment1) {//GEN-END:|7-commandAction|39|29-preAction
                // write pre-action user code here
//GEN-LINE:|7-commandAction|40|29-postAction
                // write post-action user code here
            } else if (command == backCommand) {//GEN-LINE:|7-commandAction|41|31-preAction
                // write pre-action user code here
//GEN-LINE:|7-commandAction|42|31-postAction
                // write post-action user code here
            } else if (command == okCommand1) {//GEN-LINE:|7-commandAction|43|79-preAction
                // write pre-action user code here
//GEN-LINE:|7-commandAction|44|79-postAction
                // write post-action user code here
            }//GEN-BEGIN:|7-commandAction|45|378-preAction
        } else if (displayable == Toggle) {
            if (command == backCommand13) {//GEN-END:|7-commandAction|45|378-preAction
                // write pre-action user code here
                switchDisplayable(null, getMainFeed());//GEN-LINE:|7-commandAction|46|378-postAction
                // write post-action user code here
            } else if (command == choose) {//GEN-LINE:|7-commandAction|47|380-preAction
                // write pre-action user code here
                user.removeAllElements();
                for(int i = 0; i <getChoiceGroup().size(); i++) {
                if(getChoiceGroup().isSelected(i) == true)
                user.addElement(getChoiceGroup().getString(i));
  }
                getInterestConfirm();
                switchDisplayable(null, getInterestConfirm());//GEN-LINE:|7-commandAction|48|380-postAction
                // write post-action user code here
            }//GEN-BEGIN:|7-commandAction|49|345-preAction
        } else if (displayable == URLCorrupted) {
            if (command == okCommand11) {//GEN-END:|7-commandAction|49|345-preAction
                // write pre-action user code here
                switchDisplayable(null, getAuthTwitter());//GEN-LINE:|7-commandAction|50|345-postAction
                // write post-action user code here
            }//GEN-BEGIN:|7-commandAction|51|170-preAction
        } else if (displayable == Verification) {
            if (command == Resend) {//GEN-END:|7-commandAction|51|170-preAction
                // write pre-action user code here
               if(!checkAllConn()){
                    return;
                }

                     httpCheckConn1 = null;
      String urlR = "http://"+SERVER_IP+":"+PORT+"/h_accounts/"+userID+"/resend.json" ;

    InputStream is1 = null;
    OutputStream os1 = null;

    try {
      // Open an HTTP Connection object
      httpCheckConn1 = (HttpConnection)Connector.open(urlR);

      // Setup HTTP Request
      httpCheckConn1.setRequestMethod(HttpConnection.GET);
      httpCheckConn1.setRequestProperty("User-Agent",
        "Profile/MIDP-1.0 Confirguration/CLDC-1.0");


      int respCode = httpCheckConn1.getResponseCode();
      if (respCode == httpCheckConn1.HTTP_OK) {
        StringBuffer sb = new StringBuffer();
        os1 = httpCheckConn1.openOutputStream();
        is1 = httpCheckConn1.openDataInputStream();
        int chr;
        while ((chr = is1.read()) != -1)
          sb.append((char) chr);

        if(sb.toString().equalsIgnoreCase("true")){
            switchDisplayable(getResentAlert(), getVerification());
            System.out.println("resend done");
        }
        else{
            switchDisplayable(getAlreadyVerified(), getVerification());
            System.out.println("resend error");
        }

        System.out.println("response: "+ sb.toString());
      }
      else {
        System.out.println("Error in opening HTTP Connection. Error#" + respCode);
      }}catch(Exception e){



      } finally {
        if(is1!= null)
            try {
                is1.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
          if(os1 != null)
            try {
                os1.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
      if(httpCheckConn1 != null)
            try {
                httpCheckConn1.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
      System.out.println("resend end");

    }

               //switchDisplayable(ResentAlert, getVerification());


//GEN-LINE:|7-commandAction|52|170-postAction
                // write post-action user code here
            } else if (command == Verify) {//GEN-LINE:|7-commandAction|53|168-preAction
                // write pre-action user code here

                        if(vTF.getString().length()!=4){
                        switchDisplayable(getInvalidCode(),getVerification());
						return;
                    }


               if(!checkAllConn()){
                    return;
                }

            httpCheckConn1 = null;
                 try {
        //Change IP accordingly
        httpCheckConn1 = (HttpConnection) Connector.open("http://"+SERVER_IP+":"+PORT+"/h_accounts/verify");
        //Request method has to be POST
        httpCheckConn1.setRequestMethod(HttpConnection.POST);
        httpCheckConn1.setRequestProperty("User-Agent",
                "Profile/MIDP-1.0 Confirguration/CLDC-1.0");
        httpCheckConn1.setRequestProperty("Accept_Language", "en-US");
        //Content-Type is must to pass parameters in POST Request must be application/json
        httpCheckConn1.setRequestProperty("Content-Type", "application/json");
        //String code = "fnay";
        //hID =4;
        // JSON String that you will send containing the attributes needed for sign up.
        String dataToBeSend = "{\"id\":\""+userID+"\",\"code\":\""+vTF.getString()+"\"}";
       // String dataToBeSend = "{\"id\":\"3\",\"code\":\"fcay\"}";
        httpCheckConn1.setRequestProperty("Content-Length",
                "" + dataToBeSend.length());
        StringBuffer sb = new StringBuffer();



        checkConnOS1 = httpCheckConn1.openOutputStream();

        checkConnOS1.write(dataToBeSend.getBytes());

        checkConnOS1.flush();//data written will be flushed to server.
        checkConnIS1 = httpCheckConn1.openDataInputStream();
        System.out.println(httpCheckConn1.getResponseCode());
        System.out.println(dataToBeSend);
         int chr;
        while ((chr = checkConnIS1.read()) != -1)
          sb.append((char) chr);

        response1 =sb.toString();


           // switchDisplayable(VerifiedAlert, getMainFeed());
          //  getMainFeed().removeCommand(goToVerification);

        System.out.println("response: "+ sb.toString());

    } catch (Throwable t) {
        System.out.println("Exception occured " + t.toString());
    } //Since only limited number of network objects can be in open state
    //it is necessary to clean them up as soon as we are done with them.
    finally {
        try {
            if (checkConnOS1 != null) {
                checkConnOS1.close();
            }
        } catch (Throwable t) {
            System.out.println("Exception occured " + t.toString());
        }
        try {
            if (httpCheckConn1 != null) {
                httpCheckConn1.close();
            }
        } catch (Throwable t) {
            System.out.println("Exception occured " + t.toString());
        }
    }


          //  if(response1.equalsIgnoreCase("true")){
                //switchDisplayable(getVerifiedAlert(),getVerification());
            //    switchDisplayable(getVerifiedAlert(),getMainFeed());
//GEN-LINE:|7-commandAction|54|168-postAction
          //  }
           // else {
                switchDisplayable(getIncorrectCode(),getVerification());
         //   }
            if(response1.equalsIgnoreCase("true")){
                //switchDisplayable(getVerifiedAlert(),getVerification());
                switchDisplayable(getVerifiedAlert(),getMainFeed());
                                             
            }
            else if(response1.equalsIgnoreCase("verified")){
                switchDisplayable(getAlreadyVerified(), getMainFeed());
            }
               else {
                switchDisplayable(getIncorrectCode(),getVerification());
            }
            //  }// write post-action user code here

            } else if (command == backV) {//GEN-LINE:|7-commandAction|55|166-preAction
                // write pre-action user code here
                switchDisplayable(null, getMainFeed());//GEN-LINE:|7-commandAction|56|166-postAction
                // write post-action user code here
            }//GEN-BEGIN:|7-commandAction|57|406-preAction
        } else if (displayable == alert1) {
            if (command == backCommand15) {//GEN-END:|7-commandAction|57|406-preAction
                // write pre-action user code here
                switchDisplayable(null, getMainFeed());//GEN-LINE:|7-commandAction|58|406-postAction
                // write post-action user code here
            }//GEN-BEGIN:|7-commandAction|59|283-preAction
        } else if (displayable == alreadyHaveTwitter) {
            if (command == BackToAccounts) {//GEN-END:|7-commandAction|59|283-preAction
                // write pre-action user code here
                switchDisplayable(null, getConnectAccount());//GEN-LINE:|7-commandAction|60|283-postAction
                // write post-action user code here
            } else if (command == ReplaceTwitterAccount) {//GEN-LINE:|7-commandAction|61|281-preAction
                // write pre-action user code here
                genReqURL();//GEN-LINE:|7-commandAction|62|281-postAction
                // write post-action user code here
            }//GEN-BEGIN:|7-commandAction|63|296-preAction
        } else if (displayable == authSuccessful) {
            if (command == BackToAccounts) {//GEN-END:|7-commandAction|63|296-preAction
                // write pre-action user code here
                switchDisplayable(null, getConnectAccount());//GEN-LINE:|7-commandAction|64|296-postAction
                // write post-action user code here
            }//GEN-BEGIN:|7-commandAction|65|253-preAction
        } else if (displayable == authTwitter) {
            if (command == List.SELECT_COMMAND) {//GEN-END:|7-commandAction|65|253-preAction
                // write pre-action user code here
                authTwitterAction();//GEN-LINE:|7-commandAction|66|253-postAction
                // write post-action user code here
            } else if (command == backCommand2) {//GEN-LINE:|7-commandAction|67|266-preAction
                // write pre-action user code here
                switchDisplayable(null, getConnectAccount());//GEN-LINE:|7-commandAction|68|266-postAction
                // write post-action user code here
            }//GEN-BEGIN:|7-commandAction|69|81-preAction
        } else if (displayable == connectAccount) {
            if (command == List.SELECT_COMMAND) {//GEN-END:|7-commandAction|69|81-preAction
                // write pre-action user code here
                connectAccountAction();//GEN-LINE:|7-commandAction|70|81-postAction
                // write post-action user code here
            } else if (command == backToMainFeed) {//GEN-LINE:|7-commandAction|71|423-preAction
                // write pre-action user code here
                switchDisplayable(null, getMainFeed());//GEN-LINE:|7-commandAction|72|423-postAction
                // write post-action user code here
            }//GEN-BEGIN:|7-commandAction|73|312-preAction
        } else if (displayable == disliked) {
            if (command == List.SELECT_COMMAND) {//GEN-END:|7-commandAction|73|312-preAction
                // write pre-action user code here
                dislikedAction();//GEN-LINE:|7-commandAction|74|312-postAction
                // write post-action user code here
            } else if (command == backCommand) {//GEN-LINE:|7-commandAction|75|314-preAction
                // write pre-action user code here
                switchDisplayable(null, getReadMore());//GEN-LINE:|7-commandAction|76|314-postAction
                // write post-action user code here
            }//GEN-BEGIN:|7-commandAction|77|106-preAction
        } else if (displayable == findFriend) {
            if (command == Add1) {//GEN-END:|7-commandAction|77|106-preAction
                // write pre-action user code here
//GEN-LINE:|7-commandAction|78|106-postAction

                // write post-action user code here
                if (this.search.getString().length() != 0) {
                    //" \"date_of_birth\":"+this.dob.getDate().toString()
                    String s = " \"receiver\":" + this.search.getString() + " \"sender_id\":" + this.userID;
                    this.sendData("friends/add/ip", s);
                }
            } else if (command == Back1) {//GEN-LINE:|7-commandAction|79|94-preAction
                // write pre-action user code here
//GEN-LINE:|7-commandAction|80|94-postAction
                // write post-action user code here
            }//GEN-BEGIN:|7-commandAction|81|19-preAction
        } else if (displayable == form) {
            if (command == exitCommand) {//GEN-END:|7-commandAction|81|19-preAction
                // write pre-action user code here
//GEN-LINE:|7-commandAction|82|19-postAction
                // write post-action user code here
            }//GEN-BEGIN:|7-commandAction|83|159-preAction
        } else if (displayable == form1) {
            if (command == backCommand8) {//GEN-END:|7-commandAction|83|159-preAction
                // write pre-action user code here
//GEN-LINE:|7-commandAction|84|159-postAction
                // write post-action user code here
            } else if (command == okCommand6) {//GEN-LINE:|7-commandAction|85|161-preAction
                // write pre-action user code here
//GEN-LINE:|7-commandAction|86|161-postAction
                // write post-action user code here
            }//GEN-BEGIN:|7-commandAction|87|445-preAction
        } else if (displayable == friendlist) {
            if (command == backCommand17) {//GEN-END:|7-commandAction|87|445-preAction
                // write pre-action user code here
                switchDisplayable(null, getRecommend());//GEN-LINE:|7-commandAction|88|445-postAction
                // write post-action user code here
            } else if (command == find1) {//GEN-LINE:|7-commandAction|89|451-preAction
                // write pre-action user code here
                switchDisplayable(null, getFindFriend());//GEN-LINE:|7-commandAction|90|451-postAction
                // write post-action user code here
            } else if (command == okCommand14) {//GEN-LINE:|7-commandAction|91|448-preAction
                // write pre-action user code here

                boolean get[] = new boolean[choiceGroup1.size()];
                choiceGroup1.getSelectedFlags(get);
                for (int i = 0; i < get.length; i++) {
                if (get[i]) {
                message =  choiceGroup1.getString(i);
                textField2.insert(message, 1);
                  }
                }
                switchDisplayable(null, getRecommend());//GEN-LINE:|7-commandAction|92|448-postAction
                // write post-action user code here
            }//GEN-BEGIN:|7-commandAction|93|383-preAction
        } else if (displayable == interestConfirm) {
            if (command == backCommand14) {//GEN-END:|7-commandAction|93|383-preAction
               interestConfirm = null;
                // write pre-action user code here
               switchDisplayable(null, getToggle());//GEN-LINE:|7-commandAction|94|383-postAction
                // write post-action user code here
            } else if (command == okCommand13) {//GEN-LINE:|7-commandAction|95|385-preAction
                // write pre-action user code here
                   UserInterestsJson();
                   switchDisplayable(null, getMainFeed());//GEN-LINE:|7-commandAction|96|385-postAction
                // write post-action user code here
            }//GEN-BEGIN:|7-commandAction|97|128-preAction
        } else if (displayable == liked) {
            if (command == List.SELECT_COMMAND) {//GEN-END:|7-commandAction|97|128-preAction
                // write pre-action user code here
                likedAction();//GEN-LINE:|7-commandAction|98|128-postAction
                // write post-action user code here
            } else if (command == backCommand) {//GEN-LINE:|7-commandAction|99|130-preAction
                // write pre-action user code here
                switchDisplayable(null, getReadMore());//GEN-LINE:|7-commandAction|100|130-postAction
                // write post-action user code here
            }//GEN-BEGIN:|7-commandAction|101|182-preAction
        } else if (displayable == list) {
            if (command == List.SELECT_COMMAND) {//GEN-END:|7-commandAction|101|182-preAction
                // write pre-action user code here
                listAction();//GEN-LINE:|7-commandAction|102|182-postAction
                // write post-action user code here
            } else if (command == back) {//GEN-LINE:|7-commandAction|103|367-preAction
                // write pre-action user code here
                 switchDisplayable(null, getMainFeed());
                 switchDisplayable(null, getMainFeed());//GEN-LINE:|7-commandAction|104|367-postAction
                // write post-action user code here
            } else if (command == unFilter) {//GEN-LINE:|7-commandAction|105|469-preAction
                // write pre-action user code here
                
                 MainFeed.deleteAll();     
                 new createStories(url,MainFeed,displayable,this);
               switchDisplayable(null , getMainFeed());
                  
//GEN-LINE:|7-commandAction|106|469-postAction
                // write post-action user code here
            }//GEN-BEGIN:|7-commandAction|107|236-preAction
        } else if (displayable == list1) {
            if (command == List.SELECT_COMMAND) {//GEN-END:|7-commandAction|107|236-preAction
                // write pre-action user code here
                list1Action();//GEN-LINE:|7-commandAction|108|236-postAction
                // write post-action user code here
            }//GEN-BEGIN:|7-commandAction|109|325-preAction
        } else if (displayable == manyReq) {
            if (command == backCommand11) {//GEN-END:|7-commandAction|109|325-preAction
                // write pre-action user code here
//GEN-LINE:|7-commandAction|110|325-postAction
                // write post-action user code here
            } else if (command == okCommand9) {//GEN-LINE:|7-commandAction|111|327-preAction
                // write pre-action user code here
//GEN-LINE:|7-commandAction|112|327-postAction
                // write post-action user code here
            }//GEN-BEGIN:|7-commandAction|113|331-preAction
        } else if (displayable == oneReq) {
            if (command == accept) {//GEN-END:|7-commandAction|113|331-preAction
                // write pre-action user code here
//GEN-LINE:|7-commandAction|114|331-postAction
                // write post-action user code here
            } else if (command == backCommand12) {//GEN-LINE:|7-commandAction|115|329-preAction
                // write pre-action user code here
//GEN-LINE:|7-commandAction|116|329-postAction
                // write post-action user code here
            } else if (command == reject) {//GEN-LINE:|7-commandAction|117|333-preAction
                // write pre-action user code here
//GEN-LINE:|7-commandAction|118|333-postAction
                // write post-action user code here
            }//GEN-BEGIN:|7-commandAction|119|53-preAction
        } else if (displayable == profile) {
            if (command == back) {//GEN-END:|7-commandAction|119|53-preAction
                // write pre-action user code here
                switchDisplayable(null, getMainFeed());//GEN-LINE:|7-commandAction|120|53-postAction
                // write post-action user code here
            } else if (command == ok) {//GEN-LINE:|7-commandAction|121|49-preAction
                // write pre-action user code here
                // The data that will be sent from the phone to the server...
                   // The data that will be sent from the phone to the server...
                String s = " "; // String s to carry all the data that will be sent
                int c = 0; // To check of no data was entered               
                boolean first = false; // Boolean flag to to check the first argument that will be sent (to match the json format)



                if (this.userName.getString().length() != 0) {//Check if user entered anything in the field

                    s += "{\"name\":" + "\"" + this.userName.getString() + "\",";
                    first = true;

                    c++;

                }
                if (this.pas.getString().length() != 0 || this.confPas.getString().length() != 0) {//Check if user entered anything in the field

                    if (this.pas.getString().equals(this.confPas.getString())) {//check equality pass and its confirmation

                        if (this.pas.getString().length() < 8) {// check length of the pass

                            this.switchDisplayable(this.getMiniPass(), this.getProfile());// pass < 8 alert
                            this.getPas().setString("");// clear them
                            this.getConfPas().setString("");
                            return;

                        }

                        String g = (!first) ? "{" : "";//check if this is the first field that the user use

                        s += g + " \"password\":" + "\"" + this.pas.getString() + "\",";

                        c++;

                        first = true;


                    } else {

                        this.switchDisplayable(this.getPassMissMatch(), this.getProfile());//missMatch Alert
                        this.getPas().setString("");
                        this.getConfPas().setString("");
                        return;
                    }

                }
                if (this.lastName.getString().length() != 0) {


                    s += " \"last_name\":" + "\"" + this.lastName.getString() + "\",";
                    first = true;
                    c++;


                }
                if (this.firstName.getString().length() != 0) {

                    //System.out.println("iiih");
                    s += " \"first_name\":" + "\"" + this.firstName.getString() + "\",";
                    first = true;
                    c++;
                }
                if (this.dob.getDate().toString().length() != 0) {
                    //Date a = new Date();
                    Calendar x = Calendar.getInstance();
                    x.getTime();
                    x.set(Calendar.HOUR_OF_DAY, 0);
                    x.set(Calendar.MINUTE, 0);
                    x.set(Calendar.SECOND, 0);
                    if (!(this.dob.getDate().toString().equals(x.getTime().toString()))) {
                        System.out.println(x.getTime());
                        System.out.println(dob.getDate().toString());


                        s += " \"date_of_birth\":" + "\"" + this.dob.getDate().toString() + "\",";
                        first = true;
                    }
                }
                s = s.substring(0, s.length() - 1);// To match the json format

                s += "}";
                if (c == 0) {// Check if the user entered a value 
                    switchDisplayable(this.getEmptyFields(), this.getProfile());
                    return;
                }
                String url = "http://" + this.SERVER_IP + ":" + this.PORT + "/users/" + this.userID + "/profile";//url
                String rep = this.sendData(url, s);
                if (rep.equals("Success")) {
                    switchDisplayable(this.getSavedProfile(), this.getMainFeed());
                    return;
                } else {
                    switchDisplayable(this.getErrorProfile(), displayable);
                    return;
                }


               
                

//GEN-LINE:|7-commandAction|122|49-postAction
                // write post-action user code here
            }//GEN-BEGIN:|7-commandAction|123|186-preAction
        } else if (displayable == readMore) {
            if (command == backCommand9) {//GEN-END:|7-commandAction|123|186-preAction
                // write pre-action user code here
                switchDisplayable(null, getMainFeed());//GEN-LINE:|7-commandAction|124|186-postAction
                // write post-action user code here
            } else if (command == blockinterest) {//GEN-LINE:|7-commandAction|125|200-preAction
                // write pre-action user code here
                if (!checkInternetConn()) {
                    switchDisplayable(getInternetError(), getVerification()); //internet alert
                    return;
                }

                if (!checkServerConn()) {
                    switchDisplayable(getServerError(), getVerification()); //server alert
                    return;
                }
                recieveData("http://"+SERVER_IP+":"+PORT+"/users/block_interest?uid=" +userID +"&id="+ currentStoryID);
                switchDisplayable(null, getMainFeed());//GEN-LINE:|7-commandAction|126|200-postAction
                // write post-action user code here
            } else if (command == blockstory) {//GEN-LINE:|7-commandAction|127|202-preAction
                // write pre-action user code here
                if (!checkInternetConn()) {
                    switchDisplayable(getInternetError(), getVerification()); //internet alert
                    return;
                }

                if (!checkServerConn()) {
                    switchDisplayable(getServerError(), getVerification()); //server alert
                    return;
                }
                String r = recieveData("http://"+SERVER_IP+":"+PORT+"/users/block_story?uid=" +userID +"&id="+ currentStoryID);
                if (r.equals("story is blocked successfully")) {
                    switchDisplayable(null, getMainFeed());//GEN-LINE:|7-commandAction|128|202-postAction
                } else
                    switchDisplayable(null, getStorynotFound());
                // write post-action user code here
            } else if (command == flag) {//GEN-LINE:|7-commandAction|129|194-preAction
                // write pre-action user code here
                if(checkInternetConn())
                {
                getDataServer("http://"+SERVER_IP+":"+PORT+"/flags/flag?uid="+userID+"&sid="+currentStoryID+"&format=json");
                }
                else
                {
                    switchDisplayable(getAlert(), getReadMore());
                }
//GEN-LINE:|7-commandAction|130|194-postAction
                // write post-action user code here
            } else if (command == friendsDislike) {//GEN-LINE:|7-commandAction|131|319-preAction
                // write pre-action user code here
                try {
                    // write pre-action user code here

                insertfriendsintolist(3);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                switchDisplayable(null, getDisliked());//GEN-LINE:|7-commandAction|132|319-postAction
                // write post-action user code here
            } else if (command == friendsLike) {//GEN-LINE:|7-commandAction|133|317-preAction
                // write pre-action user code here
                try {
                    // write pre-action user code here

                insertfriendsintolist(2);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                switchDisplayable(null, getLiked());//GEN-LINE:|7-commandAction|134|317-postAction
                // write post-action user code here
            } else if (command == okCommand7) {//GEN-LINE:|7-commandAction|135|188-preAction
                // write pre-action user code here
//GEN-LINE:|7-commandAction|136|188-postAction
                // write post-action user code here
            } else if (command == recommend1) {//GEN-LINE:|7-commandAction|137|198-preAction
                try {
                    // write pre-action user code here
                     insertfriendsintolist(1);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                switchDisplayable(null, getRecommend());//GEN-LINE:|7-commandAction|138|198-postAction
                // write post-action user code here
            } else if (command == share) {//GEN-LINE:|7-commandAction|139|196-preAction
                // write pre-action user code here
                shareonnetwork();//GEN-LINE:|7-commandAction|140|196-postAction
                // write post-action user code here
            } else if (command == singOut) {//GEN-LINE:|7-commandAction|141|321-preAction
                // write pre-action user code here
//GEN-LINE:|7-commandAction|142|321-postAction
                // write post-action user code here
            } else if (command == thumbdown) {//GEN-LINE:|7-commandAction|143|192-preAction
                // write pre-action user code here
                if(checkInternetConn())
                {
                getDataServer("http://"+SERVER_IP+":"+PORT+"/likedislikes/dislike?uid="+userID+"&sid="+currentStoryID+"&format=json");
                }
                else
                {
                    switchDisplayable(getAlert(), getReadMore());
                }
//GEN-LINE:|7-commandAction|144|192-postAction
                // write post-action user code here
            } else if (command == thumbup) {//GEN-LINE:|7-commandAction|145|190-preAction
                if(checkInternetConn())
                {

                getDataServer("http://"+SERVER_IP+":"+PORT+"/likedislikes/like?uid="+userID+"&sid="+currentStoryID+"&format=json");
                }
                else
                {
                   switchDisplayable(getAlert(), getReadMore());
                }
//GEN-LINE:|7-commandAction|146|190-postAction
                // write post-action user code here
            } else if (command == viewCommentsMany) {//GEN-LINE:|7-commandAction|147|356-preAction
                // write pre-action user code here
                switchDisplayable(null, getCommentsMany());//GEN-LINE:|7-commandAction|148|356-postAction
                // write post-action user code here
            }//GEN-BEGIN:|7-commandAction|149|115-preAction
        } else if (displayable == recommend) {
            if (command == backCommand3) {//GEN-END:|7-commandAction|149|115-preAction
                // write pre-action user code here
                switchDisplayable(null, getReadMore());//GEN-LINE:|7-commandAction|150|115-postAction
                // write post-action user code here
            } else if (command == choosefriend) {//GEN-LINE:|7-commandAction|151|124-preAction
                // write pre-action user code here

                switchDisplayable(null, getFriendlist());//GEN-LINE:|7-commandAction|152|124-postAction
                // write post-action user code here
            } else if (command == okCommand) {//GEN-LINE:|7-commandAction|153|113-preAction


                emailformat();//GEN-LINE:|7-commandAction|154|113-postAction
              // write post-action user code here
            }//GEN-BEGIN:|7-commandAction|155|35-preAction
        } else if (displayable == textBox) {
            if (command == backCommand1) {//GEN-END:|7-commandAction|155|35-preAction
                // write pre-action user code here
//GEN-LINE:|7-commandAction|156|35-postAction
                // write post-action user code here
            } else if (command == okCommand) {//GEN-LINE:|7-commandAction|157|33-preAction
                // write pre-action user code here
//GEN-LINE:|7-commandAction|158|33-postAction
                // write post-action user code here
            }//GEN-BEGIN:|7-commandAction|159|305-preAction
        } else if (displayable == twitterAuthFailed) {
            if (command == BackToAccounts) {//GEN-END:|7-commandAction|159|305-preAction
                // write pre-action user code here
                switchDisplayable(null, getConnectAccount());//GEN-LINE:|7-commandAction|160|305-postAction
                // write post-action user code here
            } else if (command == Resend1) {//GEN-LINE:|7-commandAction|161|302-preAction
                // write pre-action user code here
                switchDisplayable(null, getAuthTwitter());//GEN-LINE:|7-commandAction|162|302-postAction
                // write post-action user code here
            }//GEN-BEGIN:|7-commandAction|163|7-postCommandAction
        }//GEN-END:|7-commandAction|163|7-postCommandAction
        // write post-action user code here
    }//GEN-BEGIN:|7-commandAction|164|
    //</editor-fold>//GEN-END:|7-commandAction|164|




    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: exitCommand ">//GEN-BEGIN:|18-getter|0|18-preInit
    /**
     * Returns an initiliazed instance of exitCommand component.
     * @return the initialized component instance
     */
    public Command getExitCommand() {
        if (exitCommand == null) {//GEN-END:|18-getter|0|18-preInit
            // write pre-init user code here
            exitCommand = new Command("Exit", Command.EXIT, 0);//GEN-LINE:|18-getter|1|18-postInit
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
    public Form getForm() {
        if (form == null) {//GEN-END:|14-getter|0|14-preInit
            // write pre-init user code here
            form = new Form("Welcome", new Item[] { getStringItem() });//GEN-BEGIN:|14-getter|1|14-postInit
            form.addCommand(getExitCommand());
            form.setCommandListener(this);//GEN-END:|14-getter|1|14-postInit
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
    public StringItem getStringItem() {
        if (stringItem == null) {//GEN-END:|16-getter|0|16-preInit
            // write pre-init user code here
            stringItem = new StringItem("Hello", "Hello, World!");//GEN-LINE:|16-getter|1|16-postInit
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
    public Form getMainFeed() {
        if (MainFeed == null) {//GEN-END:|22-getter|0|22-preInit
            // write pre-init user code here
            if(!checkInternetConn()){
                switchDisplayable(getInternetError(),getVerification());
                //return;
            }
            if(!checkServerConn()){
                switchDisplayable(getServerError(),getVerification());
            }
            MainFeed = new Form("form1");//GEN-BEGIN:|22-getter|1|22-postInit
            MainFeed.addCommand(getOptions());
            MainFeed.addCommand(getFilterFriends());
            MainFeed.addCommand(getFilterStories1());
            MainFeed.addCommand(getGoToVerification());
            MainFeed.addCommand(getConnectSocialAccount());
            MainFeed.addCommand(getEditInfo());
            MainFeed.setCommandListener(this);//GEN-END:|22-getter|1|22-postInit
            // write post-init user code here
            this.url = getDataServer("http://" + SERVER_IP + ":" + PORT + "/users/"+userID+"/stories?format=json");
            this.url = "{ \"storyItem\" :" + this.url + "}";
            new createStories(url, MainFeed, displayable, this);
        }//GEN-BEGIN:|22-getter|2|
        return MainFeed;
    }
    //</editor-fold>//GEN-END:|22-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: viewComments ">//GEN-BEGIN:|24-getter|0|24-preInit
    /**
     * Returns an initiliazed instance of viewComments component.
     * @return the initialized component instance
     */
    public Command getViewComments() {
        if (viewComments == null) {//GEN-END:|24-getter|0|24-preInit
            // write pre-init user code here
            viewComments = new Command("Ok", Command.OK, 0);//GEN-LINE:|24-getter|1|24-postInit
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
    public Form getStory() {
        if (Story == null) {//GEN-END:|23-getter|0|23-preInit
            // write pre-init user code here
            Story = new Form("form1");//GEN-BEGIN:|23-getter|1|23-postInit
            Story.addCommand(getOkCommand1());
            Story.addCommand(getComment1());
            Story.addCommand(getBackCommand());
            Story.setCommandListener(this);//GEN-END:|23-getter|1|23-postInit
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
    public TextBox getTextBox() {
        if (textBox == null) {//GEN-END:|26-getter|0|26-preInit
            // write pre-init user code here
            textBox = new TextBox("textBox", null, 100, TextField.ANY);//GEN-BEGIN:|26-getter|1|26-postInit
            textBox.addCommand(getOkCommand());
            textBox.addCommand(getBackCommand1());
            textBox.setCommandListener(this);//GEN-END:|26-getter|1|26-postInit
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
    public Form getCommentsMany() {
        if (CommentsMany == null) {//GEN-END:|27-getter|0|27-preInit
            // write pre-init user code here
            if (before) {
                textField = null;
            }
            CommentsMany = new Form("Comments", new Item[] { getTextField() });//GEN-BEGIN:|27-getter|1|27-postInit
            CommentsMany.addCommand(getBackToStory());
            CommentsMany.addCommand(getAddComment());
            CommentsMany.setCommandListener(this);//GEN-END:|27-getter|1|27-postInit
        //    currentStoryID = 8; //for testing
            parseComments(currentStoryID + "");


        }//GEN-BEGIN:|27-getter|2|
        return CommentsMany;
    }
    //</editor-fold>//GEN-END:|27-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: Comment1 ">//GEN-BEGIN:|28-getter|0|28-preInit
    /**
     * Returns an initiliazed instance of Comment1 component.
     * @return the initialized component instance
     */
    public Command getComment1() {
        if (Comment1 == null) {//GEN-END:|28-getter|0|28-preInit
            // write pre-init user code here
            Comment1 = new Command("Comments", Command.OK, 0);//GEN-LINE:|28-getter|1|28-postInit
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
    public Command getBackCommand() {
        if (backCommand == null) {//GEN-END:|30-getter|0|30-preInit
            // write pre-init user code here
            backCommand = new Command("Back", Command.BACK, 0);//GEN-LINE:|30-getter|1|30-postInit
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
    public Command getOkCommand() {
        if (okCommand == null) {//GEN-END:|32-getter|0|32-preInit
            // write pre-init user code here
            okCommand = new Command("send", Command.OK, 0);//GEN-LINE:|32-getter|1|32-postInit
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
    public Command getBackCommand1() {
        if (backCommand1 == null) {//GEN-END:|34-getter|0|34-preInit
            // write pre-init user code here
            backCommand1 = new Command("Back", Command.BACK, 0);//GEN-LINE:|34-getter|1|34-postInit
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
    public Command getBackToStory() {
        if (backToStory == null) {//GEN-END:|37-getter|0|37-preInit
            // write pre-init user code here
            backToStory = new Command("Back", Command.BACK, 0);//GEN-LINE:|37-getter|1|37-postInit
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
    public Command getAddComment() {
        if (AddComment == null) {//GEN-END:|39-getter|0|39-preInit
            // write pre-init user code here
            AddComment = new Command("Add", Command.OK, 0);//GEN-LINE:|39-getter|1|39-postInit
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
    public Form getProfile() {
        if (profile == null) {//GEN-END:|47-getter|0|47-preInit
            // write pre-init user code here
            profile = new Form("Edit Info", new Item[] { getUserName(), getFirstName(), getLastName(), getDob(), getPas(), getConfPas() });//GEN-BEGIN:|47-getter|1|47-postInit
            profile.addCommand(getOk());
            profile.addCommand(getBack());
            profile.setCommandListener(this);//GEN-END:|47-getter|1|47-postInit
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
    public Command getOk() {
        if (ok == null) {//GEN-END:|48-getter|0|48-preInit
            // write pre-init user code here
            ok = new Command("Ok", Command.OK, 0);//GEN-LINE:|48-getter|1|48-postInit
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
    public Command getCancelCommand() {
        if (cancelCommand == null) {//GEN-END:|50-getter|0|50-preInit
            // write pre-init user code here
            cancelCommand = new Command("Cancel", Command.CANCEL, 0);//GEN-LINE:|50-getter|1|50-postInit
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
    public Command getBack() {
        if (back == null) {//GEN-END:|52-getter|0|52-preInit
            // write pre-init user code here
            back = new Command("Back", Command.BACK, 0);//GEN-LINE:|52-getter|1|52-postInit
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
    public TextField getUserName() {
        if (userName == null) {//GEN-END:|54-getter|0|54-preInit
            // write pre-init user code here
            userName = new TextField("User Name", null, 32, TextField.ANY);//GEN-LINE:|54-getter|1|54-postInit
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
    public TextField getFirstName() {
        if (firstName == null) {//GEN-END:|55-getter|0|55-preInit
            // write pre-init user code here
            firstName = new TextField("firstName", null, 32, TextField.ANY);//GEN-LINE:|55-getter|1|55-postInit
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
    public TextField getLastName() {
        if (lastName == null) {//GEN-END:|56-getter|0|56-preInit
            // write pre-init user code here
            lastName = new TextField("Last Name", null, 32, TextField.ANY);//GEN-LINE:|56-getter|1|56-postInit
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
    public DateField getDob() {
        if (dob == null) {//GEN-END:|57-getter|0|57-preInit
            // write pre-init user code here
            dob = new DateField("Date of Birth", DateField.DATE);//GEN-BEGIN:|57-getter|1|57-postInit
            dob.setDate(new java.util.Date(System.currentTimeMillis()));//GEN-END:|57-getter|1|57-postInit
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
    public TextField getPas() {
        if (pas == null) {//GEN-END:|58-getter|0|58-preInit
            // write pre-init user code here
            pas = new TextField("New Password", null, 32, TextField.ANY | TextField.PASSWORD | TextField.SENSITIVE | TextField.NON_PREDICTIVE);//GEN-LINE:|58-getter|1|58-postInit
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
    public TextField getConfPas() {
        if (confPas == null) {//GEN-END:|59-getter|0|59-preInit
            // write pre-init user code here
            confPas = new TextField("Confirm  New Password", null, 32, TextField.ANY | TextField.PASSWORD | TextField.SENSITIVE | TextField.NON_PREDICTIVE);//GEN-LINE:|59-getter|1|59-postInit
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
    public Command getOptions() {
        if (options == null) {//GEN-END:|62-getter|0|62-preInit
            // write pre-init user code here
            options = new Command("Options", Command.SCREEN, 0);//GEN-LINE:|62-getter|1|62-postInit
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
    public TextField getTextField() {
        if (textField == null) {//GEN-END:|67-getter|0|67-preInit
            // write pre-init user code here
            textField = new TextField("Enter Comment", null, 200, TextField.ANY);//GEN-BEGIN:|67-getter|1|67-postInit
            textField.setLayout(ImageItem.LAYOUT_DEFAULT);//GEN-END:|67-getter|1|67-postInit
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
    public Alert getCommentFail() {
        if (CommentFail == null) {//GEN-END:|68-getter|0|68-preInit
            // write pre-init user code here
            CommentFail = new Alert("alert", "Please enter something!", null, AlertType.WARNING);//GEN-BEGIN:|68-getter|1|68-postInit
            CommentFail.setIndicator(getIndicator());
            CommentFail.setTimeout(Alert.FOREVER);//GEN-END:|68-getter|1|68-postInit
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
    public Gauge getIndicator() {
        if (indicator == null) {//GEN-END:|69-getter|0|69-preInit
            // write pre-init user code here
            indicator = new Gauge(null, false, 100, 0);//GEN-LINE:|69-getter|1|69-postInit
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
    public Alert getCommentSucc() {
        if (CommentSucc == null) {//GEN-END:|71-getter|0|71-preInit
            // write pre-init user code here
            CommentSucc = new Alert("", "Comment added Successfully!", null, AlertType.CONFIRMATION);//GEN-BEGIN:|71-getter|1|71-postInit
            CommentSucc.setTimeout(Alert.FOREVER);//GEN-END:|71-getter|1|71-postInit
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
    public Command getOkCommand1() {
        if (okCommand1 == null) {//GEN-END:|78-getter|0|78-preInit
            // write pre-init user code here
            okCommand1 = new Command(" ", Command.OK, 0);//GEN-LINE:|78-getter|1|78-postInit
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
    public List getConnectAccount() {
        if (connectAccount == null) {//GEN-END:|80-getter|0|80-preInit
            // write pre-init user code here
            /*
             * This method redirects different Social acocunts to their
             * corresponding flow. If the social account is not included
             * yet, an alert method will be displayed.
             */
            connectAccount = new List("Network", Choice.IMPLICIT);//GEN-BEGIN:|80-getter|1|80-postInit
            connectAccount.append("Twitter", null);
            connectAccount.append("Facebook", null);
            connectAccount.append("Flickr", null);
            connectAccount.append("Foursquare", null);
            connectAccount.append("Tumblr", null);
            connectAccount.append("Youtube", null);
            connectAccount.addCommand(getBackToMainFeed());
            connectAccount.setCommandListener(this);
            connectAccount.setSelectedFlags(new boolean[] { false, false, false, false, false, false });//GEN-END:|80-getter|1|80-postInit
            // write post-init user code here

        }//GEN-BEGIN:|80-getter|2|
        return connectAccount;
    }
    //</editor-fold>//GEN-END:|80-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Method: connectAccountAction ">//GEN-BEGIN:|80-action|0|80-preAction
    /**
     * Performs an action assigned to the selected list element in the connectAccount component.
     */
    public void connectAccountAction() {//GEN-END:|80-action|0|80-preAction
        // enter pre-action user code here
        String __selectedString = getConnectAccount().getString(getConnectAccount().getSelectedIndex());//GEN-BEGIN:|80-action|1|83-preAction
        if (__selectedString != null) {
            if (__selectedString.equals("Twitter")) {//GEN-END:|80-action|1|83-preAction
                // write pre-action user code here
                switchDisplayable(null, getAuthTwitter());//GEN-LINE:|80-action|2|83-postAction
                // write post-action user code here
            } else if (__selectedString.equals("Facebook")) {//GEN-LINE:|80-action|3|84-preAction
                // write pre-action user code here
                switchDisplayable(getComingSoon(), getConnectAccount());//GEN-LINE:|80-action|4|84-postAction
                // write post-action user code here
            } else if (__selectedString.equals("Flickr")) {//GEN-LINE:|80-action|5|85-preAction
                // write pre-action user code here
                switchDisplayable(getComingSoon(), getConnectAccount());//GEN-LINE:|80-action|6|85-postAction
                // write post-action user code here
            } else if (__selectedString.equals("Foursquare")) {//GEN-LINE:|80-action|7|86-preAction
                // write pre-action user code here
                switchDisplayable(getComingSoon(), getConnectAccount());//GEN-LINE:|80-action|8|86-postAction
                // write post-action user code here
            } else if (__selectedString.equals("Tumblr")) {//GEN-LINE:|80-action|9|87-preAction
                // write pre-action user code here
                switchDisplayable(getComingSoon(), getConnectAccount());//GEN-LINE:|80-action|10|87-postAction
                // write post-action user code here
            } else if (__selectedString.equals("Youtube")) {//GEN-LINE:|80-action|11|88-preAction
                // write pre-action user code here
                switchDisplayable(getComingSoon(), getConnectAccount());//GEN-LINE:|80-action|12|88-postAction
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
    public TextField getSearch() {
        if (search == null) {//GEN-END:|97-getter|0|97-preInit
            // write pre-init user code here
            search = new TextField("Enter A User Name or An Email", null, 32, TextField.ANY);//GEN-LINE:|97-getter|1|97-postInit
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
    public Command getAdd() {
        if (Add == null) {//GEN-END:|102-getter|0|102-preInit
            // write pre-init user code here
            Add = new Command("Screen", Command.SCREEN, 0);//GEN-LINE:|102-getter|1|102-postInit
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
    public Form getFindFriend() {
        if (findFriend == null) {//GEN-END:|92-getter|0|92-preInit
            // write pre-init user code here
            findFriend = new Form("form1", new Item[] { getSearch() });//GEN-BEGIN:|92-getter|1|92-postInit
            findFriend.addCommand(getBack1());
            findFriend.addCommand(getAdd1());
            findFriend.setCommandListener(this);//GEN-END:|92-getter|1|92-postInit
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
    public Command getBack1() {
        if (Back1 == null) {//GEN-END:|93-getter|0|93-preInit
            // write pre-init user code here
            Back1 = new Command("Back", Command.BACK, 0);//GEN-LINE:|93-getter|1|93-postInit
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
    public Alert getError() {
        if (Error == null) {//GEN-END:|99-getter|0|99-preInit
            // write pre-init user code here
            Error = new Alert("alert1", "Error while saving Info ", null, AlertType.ERROR);//GEN-BEGIN:|99-getter|1|99-postInit
            Error.setTimeout(Alert.FOREVER);//GEN-END:|99-getter|1|99-postInit
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
    public Command getFind() {
        if (Find == null) {//GEN-END:|95-getter|0|95-preInit
            // write pre-init user code here
            Find = new Command("findfriend", Command.OK, 0);//GEN-LINE:|95-getter|1|95-postInit
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
    public Command getAdd1() {
        if (Add1 == null) {//GEN-END:|105-getter|0|105-preInit
            // write pre-init user code here
            Add1 = new Command("Ok", Command.OK, 0);//GEN-LINE:|105-getter|1|105-postInit
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
    public Alert getSaved() {
        if (Saved == null) {//GEN-END:|98-getter|0|98-preInit
            // write pre-init user code here
            Saved = new Alert("alert", "Your Info was saved ", null, AlertType.CONFIRMATION);//GEN-BEGIN:|98-getter|1|98-postInit
            Saved.setTimeout(Alert.FOREVER);//GEN-END:|98-getter|1|98-postInit
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
    public Alert getFound() {
        if (Found == null) {//GEN-END:|100-getter|0|100-preInit
            // write pre-init user code here
            Found = new Alert("alert", "Friend Found\n", null, AlertType.CONFIRMATION);//GEN-BEGIN:|100-getter|1|100-postInit
            Found.setTimeout(Alert.FOREVER);//GEN-END:|100-getter|1|100-postInit
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
    public Alert getNotFound() {
        if (NotFound == null) {//GEN-END:|101-getter|0|101-preInit
            // write pre-init user code here
            NotFound = new Alert("alert1", "Check User Name/Email", null, AlertType.ERROR);//GEN-BEGIN:|101-getter|1|101-postInit
            NotFound.setTimeout(Alert.FOREVER);//GEN-END:|101-getter|1|101-postInit
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
    public Alert getEnterUserNameEmail() {
        if (EnterUserNameEmail == null) {//GEN-END:|104-getter|0|104-preInit
            // write pre-init user code here
            EnterUserNameEmail = new Alert("alert", "Enter A User Name/EMail", null, AlertType.ERROR);//GEN-BEGIN:|104-getter|1|104-postInit
            EnterUserNameEmail.setTimeout(Alert.FOREVER);//GEN-END:|104-getter|1|104-postInit
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
    public Alert getComingSoon() {
        if (ComingSoon == null) {//GEN-END:|107-getter|0|107-preInit
            // write pre-init user code here
            /*
             * This is the alert whenever a user tries to connect to a non-added
             * social network.
             */
            ComingSoon = new Alert("Coming Soon", "This network is coming soon", null, null);//GEN-BEGIN:|107-getter|1|107-postInit
            ComingSoon.setTimeout(Alert.FOREVER);//GEN-END:|107-getter|1|107-postInit
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
    public Command getChoosefriend() {
        if (choosefriend == null) {//GEN-END:|111-getter|0|111-preInit
            // write pre-init user code here
            choosefriend = new Command("choosefriend", Command.SCREEN, 0);//GEN-LINE:|111-getter|1|111-postInit
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
    public Command getBackCommand3() {
        if (backCommand3 == null) {//GEN-END:|114-getter|0|114-preInit
            // write pre-init user code here
            backCommand3 = new Command("Back", Command.BACK, 0);//GEN-LINE:|114-getter|1|114-postInit
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
    public Command getOkCommand2() {
        if (okCommand2 == null) {//GEN-END:|119-getter|0|119-preInit
            // write pre-init user code here
            okCommand2 = new Command("ok", Command.OK, 0);//GEN-LINE:|119-getter|1|119-postInit
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
    public Command getBackCommand4() {
        if (backCommand4 == null) {//GEN-END:|121-getter|0|121-preInit
            // write pre-init user code here
            backCommand4 = new Command("Back", Command.BACK, 0);//GEN-LINE:|121-getter|1|121-postInit
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
    public Form getRecommend() {
        if (recommend == null) {//GEN-END:|108-getter|0|108-preInit
            // write pre-init user code here
            recommend = new Form("form1", new Item[] { getTextField2(), getTextField3() });//GEN-BEGIN:|108-getter|1|108-postInit
            recommend.addCommand(getOkCommand());
            recommend.addCommand(getBackCommand3());
            recommend.addCommand(getChoosefriend());
            recommend.setCommandListener(this);//GEN-END:|108-getter|1|108-postInit
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
    public TextField getTextField2() {
        if (textField2 == null) {//GEN-END:|109-getter|0|109-preInit
            // write pre-init user code here
            textField2 = new TextField("Email", null, 32, TextField.ANY);//GEN-LINE:|109-getter|1|109-postInit
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
    public TextField getTextField3() {
        if (textField3 == null) {//GEN-END:|110-getter|0|110-preInit
            // write pre-init user code here
            textField3 = new TextField("message", null, 32, TextField.ANY);//GEN-LINE:|110-getter|1|110-postInit
            // write post-init user code here
        }//GEN-BEGIN:|110-getter|2|
        return textField3;
    }
    //</editor-fold>//GEN-END:|110-getter|2|





    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: storeypublished ">//GEN-BEGIN:|125-getter|0|125-preInit
    /**
     * Returns an initiliazed instance of storeypublished component.
     * @return the initialized component instance
     */
    public Alert getStoreypublished() {
        if (storeypublished == null) {//GEN-END:|125-getter|0|125-preInit
            // write pre-init user code here
            storeypublished = new Alert("", "story published successfully", null, AlertType.CONFIRMATION);//GEN-BEGIN:|125-getter|1|125-postInit
            storeypublished.setTimeout(Alert.FOREVER);//GEN-END:|125-getter|1|125-postInit
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
    public Alert getStorynotpublished() {
        if (storynotpublished == null) {//GEN-END:|126-getter|0|126-preInit
            // write pre-init user code here
            storynotpublished = new Alert("", "story hasn\'t published , please try again later", null, AlertType.ALARM);//GEN-BEGIN:|126-getter|1|126-postInit
            storynotpublished.setTimeout(Alert.FOREVER);//GEN-END:|126-getter|1|126-postInit
            // write post-init user code here
        }//GEN-BEGIN:|126-getter|2|
        return storynotpublished;
    }
    //</editor-fold>//GEN-END:|126-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: liked ">//GEN-BEGIN:|127-getter|0|127-preInit
    /**
     * Returns an initiliazed instance of liked component.
     * @return the initialized component instance
     */
    public List getLiked() {
        if (liked == null) {//GEN-END:|127-getter|0|127-preInit
            // write pre-init user code here
            liked = new List("liked", Choice.IMPLICIT);//GEN-BEGIN:|127-getter|1|127-postInit
            liked.addCommand(getBackCommand());
            liked.setCommandListener(this);//GEN-END:|127-getter|1|127-postInit
            // write post-init user code here
        }//GEN-BEGIN:|127-getter|2|
        return liked;
    }
    //</editor-fold>//GEN-END:|127-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Method: likedAction ">//GEN-BEGIN:|127-action|0|127-preAction
    /**
     * Performs an action assigned to the selected list element in the liked component.
     */
    public void likedAction() {//GEN-END:|127-action|0|127-preAction
        // enter pre-action user code here
        String __selectedString = getLiked().getString(getLiked().getSelectedIndex());//GEN-LINE:|127-action|1|127-postAction
        // enter post-action user code here
    }//GEN-BEGIN:|127-action|2|
    //</editor-fold>//GEN-END:|127-action|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: okCommand3 ">//GEN-BEGIN:|134-getter|0|134-preInit
    /**
     * Returns an initiliazed instance of okCommand3 component.
     * @return the initialized component instance
     */
    public Command getOkCommand3() {
        if (okCommand3 == null) {//GEN-END:|134-getter|0|134-preInit
            // write pre-init user code here
            okCommand3 = new Command("Ok", Command.OK, 0);//GEN-LINE:|134-getter|1|134-postInit
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
    public Command getBackCommand5() {
        if (backCommand5 == null) {//GEN-END:|136-getter|0|136-preInit
            // write pre-init user code here
            backCommand5 = new Command("Back", Command.BACK, 0);//GEN-LINE:|136-getter|1|136-postInit
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
    public Command getOkCommand4() {
        if (okCommand4 == null) {//GEN-END:|142-getter|0|142-preInit
            // write pre-init user code here
            okCommand4 = new Command("Ok", Command.OK, 0);//GEN-LINE:|142-getter|1|142-postInit
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
    public Command getBackCommand6() {
        if (backCommand6 == null) {//GEN-END:|144-getter|0|144-preInit
            // write pre-init user code here
            backCommand6 = new Command("Back", Command.BACK, 0);//GEN-LINE:|144-getter|1|144-postInit
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
    public Command getExitCommand1() {
        if (exitCommand1 == null) {//GEN-END:|146-getter|0|146-preInit
            // write pre-init user code here
            exitCommand1 = new Command("Exit", Command.EXIT, 0);//GEN-LINE:|146-getter|1|146-postInit
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
    public Form getLoginScreen() {
        if (LoginScreen == null) {//GEN-END:|131-getter|0|131-preInit
            // write pre-init user code here
            LoginScreen = new Form("LoginScreen", new Item[] { getTextField4(), getTextField5() });//GEN-BEGIN:|131-getter|1|131-postInit
            LoginScreen.addCommand(getOkCommand3());
            LoginScreen.addCommand(getBackCommand5());
            LoginScreen.addCommand(getRegisterCommand());
            LoginScreen.addCommand(getResendPasswordCommand());
            LoginScreen.setCommandListener(this);//GEN-END:|131-getter|1|131-postInit
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
    public TextField getTextField4() {
        if (textField4 == null) {//GEN-END:|132-getter|0|132-preInit
            // write pre-init user code here
            textField4 = new TextField("Email", null, 32, TextField.EMAILADDR);//GEN-LINE:|132-getter|1|132-postInit
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
    public TextField getTextField5() {
        if (textField5 == null) {//GEN-END:|133-getter|0|133-preInit
            // write pre-init user code here
            textField5 = new TextField("Password", null, 32, TextField.ANY | TextField.PASSWORD);//GEN-LINE:|133-getter|1|133-postInit
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
    public Command getOkCommand5() {
        if (okCommand5 == null) {//GEN-END:|152-getter|0|152-preInit
            // write pre-init user code here
            okCommand5 = new Command("Ok", Command.OK, 0);//GEN-LINE:|152-getter|1|152-postInit
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
    public Command getBackCommand7() {
        if (backCommand7 == null) {//GEN-END:|154-getter|0|154-preInit
            // write pre-init user code here
            backCommand7 = new Command("Back", Command.BACK, 0);//GEN-LINE:|154-getter|1|154-postInit
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
    public Form getRegisterScreen() {
        if (RegisterScreen == null) {//GEN-END:|148-getter|0|148-preInit
            // write pre-init user code here
            RegisterScreen = new Form("RegisterScreen", new Item[] { getTextField6(), getTextField7(), getTextField8() });//GEN-BEGIN:|148-getter|1|148-postInit
            RegisterScreen.addCommand(getOkCommand5());
            RegisterScreen.addCommand(getBackCommand7());
            RegisterScreen.setCommandListener(this);//GEN-END:|148-getter|1|148-postInit
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
    public TextField getTextField6() {
        if (textField6 == null) {//GEN-END:|149-getter|0|149-preInit
            // write pre-init user code here
            textField6 = new TextField("Email", null, 32, TextField.EMAILADDR);//GEN-LINE:|149-getter|1|149-postInit
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
    public TextField getTextField7() {
        if (textField7 == null) {//GEN-END:|150-getter|0|150-preInit
            // write pre-init user code here
            textField7 = new TextField("Password", null, 32, TextField.ANY | TextField.PASSWORD);//GEN-LINE:|150-getter|1|150-postInit
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
    public TextField getTextField8() {
        if (textField8 == null) {//GEN-END:|151-getter|0|151-preInit
            // write pre-init user code here
            textField8 = new TextField("Confirm Password", null, 32, TextField.ANY | TextField.PASSWORD);//GEN-LINE:|151-getter|1|151-postInit
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
    public Command getBackCommand8() {
        if (backCommand8 == null) {//GEN-END:|158-getter|0|158-preInit
            // write pre-init user code here
            backCommand8 = new Command("Back", Command.BACK, 0);//GEN-LINE:|158-getter|1|158-postInit
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
    public Command getOkCommand6() {
        if (okCommand6 == null) {//GEN-END:|160-getter|0|160-preInit
            // write pre-init user code here
            okCommand6 = new Command("Ok", Command.OK, 0);//GEN-LINE:|160-getter|1|160-postInit
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
    public Form getForm1() {
        if (form1 == null) {//GEN-END:|156-getter|0|156-preInit
            // write pre-init user code here
            form1 = new Form("ForgotPasswordScreen", new Item[] { getTextField9() });//GEN-BEGIN:|156-getter|1|156-postInit
            form1.addCommand(getBackCommand8());
            form1.addCommand(getOkCommand6());
            form1.setCommandListener(this);//GEN-END:|156-getter|1|156-postInit
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
    public TextField getTextField9() {
        if (textField9 == null) {//GEN-END:|157-getter|0|157-preInit
            // write pre-init user code here
            textField9 = new TextField("Email", null, 32, TextField.ANY);//GEN-LINE:|157-getter|1|157-postInit
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
    public Command getResend() {
        if (Resend == null) {//GEN-END:|169-getter|0|169-preInit
            // write pre-init user code here
            Resend = new Command("Resend", Command.SCREEN, 1);//GEN-LINE:|169-getter|1|169-postInit
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
    public Command getVerify() {
        if (Verify == null) {//GEN-END:|167-getter|0|167-preInit
            // write pre-init user code here
            Verify = new Command("Verify", Command.OK, 2);//GEN-LINE:|167-getter|1|167-postInit
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
    public Command getBackV() {
        if (backV == null) {//GEN-END:|165-getter|0|165-preInit
            // write pre-init user code here
            backV = new Command("Back", Command.BACK, 1);//GEN-LINE:|165-getter|1|165-postInit
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
    public Alert getIncorrectCode() {
        if (IncorrectCode == null) {//GEN-END:|171-getter|0|171-preInit
            // write pre-init user code here
            IncorrectCode = new Alert("Incorrect Verification", "Incorrect verification code", null, AlertType.ERROR);//GEN-BEGIN:|171-getter|1|171-postInit
            IncorrectCode.setTimeout(Alert.FOREVER);//GEN-END:|171-getter|1|171-postInit
            // write post-init user code here
        }//GEN-BEGIN:|171-getter|2|
        return IncorrectCode;
    }
    //</editor-fold>//GEN-END:|171-getter|2|



    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: ResentAlert ">//GEN-BEGIN:|174-getter|0|174-preInit
    /**
     * Returns an initiliazed instance of ResentAlert component.
     * @return the initialized component instance
     */
    public Alert getResentAlert() {
        if (ResentAlert == null) {//GEN-END:|174-getter|0|174-preInit
            // write pre-init user code here
            ResentAlert = new Alert("Code Resent", "The verification code has been sent to your email", null, AlertType.CONFIRMATION);//GEN-BEGIN:|174-getter|1|174-postInit
            ResentAlert.setTimeout(Alert.FOREVER);//GEN-END:|174-getter|1|174-postInit
            // write post-init user code here
        }//GEN-BEGIN:|174-getter|2|
        return ResentAlert;
    }
    //</editor-fold>//GEN-END:|174-getter|2|



    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: VerifiedAlert ">//GEN-BEGIN:|173-getter|0|173-preInit
    /**
     * Returns an initiliazed instance of VerifiedAlert component.
     * @return the initialized component instance
     */
    public Alert getVerifiedAlert() {
        if (VerifiedAlert == null) {//GEN-END:|173-getter|0|173-preInit
            // write pre-init user code here
            VerifiedAlert = new Alert("Account Verified", "your account has been successfully verified", null, AlertType.CONFIRMATION);//GEN-BEGIN:|173-getter|1|173-postInit
            VerifiedAlert.setTimeout(Alert.FOREVER);//GEN-END:|173-getter|1|173-postInit
            // write post-init user code here
        }//GEN-BEGIN:|173-getter|2|
        return VerifiedAlert;
    }
    //</editor-fold>//GEN-END:|173-getter|2|



    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: InvalidCode ">//GEN-BEGIN:|172-getter|0|172-preInit
    /**
     * Returns an initiliazed instance of InvalidCode component.
     * @return the initialized component instance
     */
    public Alert getInvalidCode() {
        if (InvalidCode == null) {//GEN-END:|172-getter|0|172-preInit
            // write pre-init user code here
            InvalidCode = new Alert("Invalid Verification", "The verification code can\'t be less than 4 characters", null, AlertType.ERROR);//GEN-BEGIN:|172-getter|1|172-postInit
            InvalidCode.setTimeout(Alert.FOREVER);//GEN-END:|172-getter|1|172-postInit
            // write post-init user code here
        }//GEN-BEGIN:|172-getter|2|
        return InvalidCode;
    }
    //</editor-fold>//GEN-END:|172-getter|2|



    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: Verification ">//GEN-BEGIN:|162-getter|0|162-preInit
    /**
     * Returns an initiliazed instance of Verification component.
     * @return the initialized component instance
     */
    public Form getVerification() {
        if (Verification == null) {//GEN-END:|162-getter|0|162-preInit
            // write pre-init user code here
            Verification = new Form("Verify your Account", new Item[] { getVTF(), getVSI() });//GEN-BEGIN:|162-getter|1|162-postInit
            Verification.addCommand(getBackV());
            Verification.addCommand(getVerify());
            Verification.addCommand(getResend());
            Verification.setCommandListener(this);//GEN-END:|162-getter|1|162-postInit
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
    public TextField getVTF() {
        if (vTF == null) {//GEN-END:|163-getter|0|163-preInit
            // write pre-init user code here
            vTF = new TextField("Enter verification code:", "", 4, TextField.ANY);//GEN-LINE:|163-getter|1|163-postInit
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
    public StringItem getVSI() {
        if (vSI == null) {//GEN-END:|164-getter|0|164-preInit
            // write pre-init user code here
            vSI = new StringItem("", "Press the \'Resend\' button to resend the verification code to your registered   e-mail adrress.");//GEN-LINE:|164-getter|1|164-postInit
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
    public Form getFriendsStories() {
        if (FriendsStories == null) {//GEN-END:|180-getter|0|180-preInit
            // write pre-init user code here
            FriendsStories = new Form("FriendsStories");//GEN-LINE:|180-getter|1|180-postInit
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
    public List getList() {
        if (list == null) {//GEN-END:|181-getter|0|181-preInit
            // write pre-init user code here
            list = new List("Select an Interest", Choice.IMPLICIT);//GEN-BEGIN:|181-getter|1|181-postInit
            list.addCommand(getBack());
            list.addCommand(getUnFilter());
            list.setCommandListener(this);//GEN-END:|181-getter|1|181-postInit
            test();
        }//GEN-BEGIN:|181-getter|2|
        return list;
    }
    //</editor-fold>//GEN-END:|181-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Method: listAction ">//GEN-BEGIN:|181-action|0|181-preAction
    /**
     * Performs an action assigned to the selected list element in the list component.
     */
    public void listAction() {//GEN-END:|181-action|0|181-preAction
        // enter pre-action user code here
        String __selectedString = getList().getString(getList().getSelectedIndex());//GEN-LINE:|181-action|1|181-postAction
        filter(__selectedString);
        switchDisplayable(null, getMainFeed());

    }//GEN-BEGIN:|181-action|2|
    //</editor-fold>//GEN-END:|181-action|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: backCommand9 ">//GEN-BEGIN:|185-getter|0|185-preInit
    /**
     * Returns an initiliazed instance of backCommand9 component.
     * @return the initialized component instance
     */
    public Command getBackCommand9() {
        if (backCommand9 == null) {//GEN-END:|185-getter|0|185-preInit
            // write pre-init user code here
            backCommand9 = new Command("Back", Command.BACK, 0);//GEN-LINE:|185-getter|1|185-postInit
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
    public Command getOkCommand7() {
        if (okCommand7 == null) {//GEN-END:|187-getter|0|187-preInit
            // write pre-init user code here
            okCommand7 = new Command("  ", Command.OK, 0);//GEN-LINE:|187-getter|1|187-postInit
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
    public Command getThumbup() {
        if (thumbup == null) {//GEN-END:|189-getter|0|189-preInit
            // write pre-init user code here
            thumbup = new Command("Thumb Up", Command.OK, 0);//GEN-LINE:|189-getter|1|189-postInit
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
    public Command getThumbdown() {
        if (thumbdown == null) {//GEN-END:|191-getter|0|191-preInit
            // write pre-init user code here
            thumbdown = new Command("Thumb Down", Command.OK, 0);//GEN-LINE:|191-getter|1|191-postInit
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
    public Command getFlag() {
        if (flag == null) {//GEN-END:|193-getter|0|193-preInit
            // write pre-init user code here
            flag = new Command("Flag", Command.OK, 0);//GEN-LINE:|193-getter|1|193-postInit
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
    public Command getShare() {
        if (share == null) {//GEN-END:|195-getter|0|195-preInit
            // write pre-init user code here
            share = new Command("Share", Command.OK, 0);//GEN-LINE:|195-getter|1|195-postInit
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
    public Command getRecommend1() {
        if (recommend1 == null) {//GEN-END:|197-getter|0|197-preInit
            // write pre-init user code here
            recommend1 = new Command("Recommend", Command.OK, 0);//GEN-LINE:|197-getter|1|197-postInit
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
    public Command getBlockinterest() {
        if (blockinterest == null) {//GEN-END:|199-getter|0|199-preInit
            // write pre-init user code here
            blockinterest = new Command("Block Interest", Command.OK, 0);//GEN-LINE:|199-getter|1|199-postInit
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
    public Command getBlockstory() {
        if (blockstory == null) {//GEN-END:|201-getter|0|201-preInit
            // write pre-init user code here
            blockstory = new Command("Block Story", Command.OK, 0);//GEN-LINE:|201-getter|1|201-postInit
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
    public Command getSignout() {
        if (signout == null) {//GEN-END:|203-getter|0|203-preInit
            // write pre-init user code here
            signout = new Command("Sign out", Command.OK, 0);//GEN-LINE:|203-getter|1|203-postInit
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
    public Form getReadMore() {
        if (readMore == null) {//GEN-END:|184-getter|0|184-preInit
            // write pre-init user code here
            readMore = new Form("2allak", new Item[] { getImageItem() });//GEN-BEGIN:|184-getter|1|184-postInit
            readMore.addCommand(getBackCommand9());
            readMore.addCommand(getOkCommand7());
            readMore.addCommand(getViewCommentsMany());
            readMore.addCommand(getThumbup());
            readMore.addCommand(getThumbdown());
            readMore.addCommand(getShare());
            readMore.addCommand(getFlag());
            readMore.addCommand(getRecommend1());
            readMore.addCommand(getBlockinterest());
            readMore.addCommand(getBlockstory());
            readMore.addCommand(getFriendsLike());
            readMore.addCommand(getFriendsDislike());
            readMore.addCommand(getSingOut());
            readMore.setCommandListener(this);//GEN-END:|184-getter|1|184-postInit
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
    public ImageItem getImageItem() {
        if (imageItem == null) {//GEN-END:|205-getter|0|205-preInit
            // write pre-init user code here
            imageItem = new ImageItem("", getImage1(), ImageItem.LAYOUT_DEFAULT, "<Missing Image>");//GEN-LINE:|205-getter|1|205-postInit
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
    public Image getImage1() {
        if (image1 == null) {//GEN-END:|206-getter|0|206-preInit
            // write pre-init user code here
            image1 = Image.createImage(1, 1);//GEN-LINE:|206-getter|1|206-postInit
            // write post-init user code here
        }//GEN-BEGIN:|206-getter|2|
        return image1;
    }
    //</editor-fold>//GEN-END:|206-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: backToComments ">//GEN-BEGIN:|208-getter|0|208-preInit
    /**
     * Returns an initiliazed instance of backToComments component.
     * @return the initialized component instance
     */
    public Command getBackToComments() {
        if (backToComments == null) {//GEN-END:|208-getter|0|208-preInit
            // write pre-init user code here
            backToComments = new Command("Back", Command.BACK, 0);//GEN-LINE:|208-getter|1|208-postInit
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
    public Command getLike() {
        if (Like == null) {//GEN-END:|210-getter|0|210-preInit
            // write pre-init user code here
            Like = new Command("Like", Command.ITEM, 0);//GEN-LINE:|210-getter|1|210-postInit
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
    public Command getDislike() {
        if (Dislike == null) {//GEN-END:|212-getter|0|212-preInit
            // write pre-init user code here
            Dislike = new Command("Dislike", Command.ITEM, 0);//GEN-LINE:|212-getter|1|212-postInit
            // write post-init user code here
        }//GEN-BEGIN:|212-getter|2|
        return Dislike;
    }
    //</editor-fold>//GEN-END:|212-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Method: shareonnetwork ">//GEN-BEGIN:|214-if|0|214-preIf
    /**
     * Performs an action assigned to the shareonnetwork if-point.
     */
    public void shareonnetwork() {//GEN-END:|214-if|0|214-preIf
        try {
            // enter pre-if user code here
            boolean x = parsestoryshare(getstoryshare());

            if (x) {//GEN-LINE:|214-if|1|215-preAction
            // write pre-action user code here
                switchDisplayable(null, getStoreypublished());//GEN-LINE:|214-if|2|215-postAction
            // write post-action user code here
            } else {//GEN-LINE:|214-if|3|216-preAction
            // write pre-action user code here
                switchDisplayable(null, getStorynotpublished());//GEN-LINE:|214-if|4|216-postAction
            // write post-action user code here
            }//GEN-LINE:|214-if|5|214-postIf
        // enter post-if user code here
        }
        //</editor-fold>
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }//GEN-BEGIN:|214-if|6|
    //</editor-fold>//GEN-END:|214-if|6|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: FriendList ">//GEN-BEGIN:|229-getter|0|229-preInit
    /**
     * Returns an initiliazed instance of FriendList component.
     * @return the initialized component instance
     */
    public List getFriendList() {
        if (FriendList == null) {//GEN-END:|229-getter|0|229-preInit
            // write pre-init user code here
            FriendList = new List("FriendList", Choice.IMPLICIT);//GEN-BEGIN:|229-getter|1|229-postInit
            FriendList.addCommand(getBlock());
            FriendList.addCommand(getFilter());
            FriendList.setCommandListener(this);//GEN-END:|229-getter|1|229-postInit
            // write post-init user code here
        }//GEN-BEGIN:|229-getter|2|
        return FriendList;
    }
    //</editor-fold>//GEN-END:|229-getter|2|
 
    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: FilterFriends ">//GEN-BEGIN:|232-getter|0|232-preInit
    /**
     * Returns an initiliazed instance of FilterFriends component.
     * @return the initialized component instance
     */
    public Command getFilterFriends() {
        if (FilterFriends == null) {//GEN-END:|232-getter|0|232-preInit
            // write pre-init user code here
            FilterFriends = new Command("Filter", Command.OK, 0);//GEN-LINE:|232-getter|1|232-postInit
            // write post-init user code here
        }//GEN-BEGIN:|232-getter|2|
        return FilterFriends;
    }
    //</editor-fold>//GEN-END:|232-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Method: FriendListAction ">//GEN-BEGIN:|229-action|0|229-preAction
    /**
     * Performs an action assigned to the selected list element in the FriendList component.
     */
    public void FriendListAction() {//GEN-END:|229-action|0|229-preAction
        // enter pre-action user code here
        String __selectedString = getFriendList().getString(getFriendList().getSelectedIndex());//GEN-LINE:|229-action|1|229-postAction
        // enter post-action user code here
    }//GEN-BEGIN:|229-action|2|
    //</editor-fold>//GEN-END:|229-action|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: list1 ">//GEN-BEGIN:|235-getter|0|235-preInit
    /**
     * Returns an initiliazed instance of list1 component.
     * @return the initialized component instance
     */
    public List getList1() {
        if (list1 == null) {//GEN-END:|235-getter|0|235-preInit
            // write pre-init user code here
            list1 = new List("list1", Choice.IMPLICIT);//GEN-BEGIN:|235-getter|1|235-postInit
            list1.setCommandListener(this);//GEN-END:|235-getter|1|235-postInit
            // write post-init user code here
        }//GEN-BEGIN:|235-getter|2|
        return list1;
    }
    //</editor-fold>//GEN-END:|235-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Method: list1Action ">//GEN-BEGIN:|235-action|0|235-preAction
    /**
     * Performs an action assigned to the selected list element in the list1 component.
     */
    public void list1Action() {//GEN-END:|235-action|0|235-preAction
        // enter pre-action user code here
        String __selectedString = getList1().getString(getList1().getSelectedIndex());//GEN-LINE:|235-action|1|235-postAction
        // enter post-action user code here
    }//GEN-BEGIN:|235-action|2|
    //</editor-fold>//GEN-END:|235-action|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: Block ">//GEN-BEGIN:|238-getter|0|238-preInit
    /**
     * Returns an initiliazed instance of Block component.
     * @return the initialized component instance
     */
    public Command getBlock() {
        if (Block == null) {//GEN-END:|238-getter|0|238-preInit
            // write pre-init user code here
            Block = new Command("Block", Command.OK, 0);//GEN-LINE:|238-getter|1|238-postInit
            // write post-init user code here
        }//GEN-BEGIN:|238-getter|2|
        return Block;
    }
    //</editor-fold>//GEN-END:|238-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: Filter ">//GEN-BEGIN:|240-getter|0|240-preInit
    /**
     * Returns an initiliazed instance of Filter component.
     * @return the initialized component instance
     */
    public Command getFilter() {
        if (Filter == null) {//GEN-END:|240-getter|0|240-preInit
            // write pre-init user code here
            Filter = new Command("Filter", Command.OK, 0);//GEN-LINE:|240-getter|1|240-postInit
            // write post-init user code here
        }//GEN-BEGIN:|240-getter|2|
        return Filter;
    }
    //</editor-fold>//GEN-END:|240-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: okCommand8 ">//GEN-BEGIN:|242-getter|0|242-preInit
    /**
     * Returns an initiliazed instance of okCommand8 component.
     * @return the initialized component instance
     */
    public Command getOkCommand8() {
        if (okCommand8 == null) {//GEN-END:|242-getter|0|242-preInit
            // write pre-init user code here
            okCommand8 = new Command("Ok", Command.OK, 0);//GEN-LINE:|242-getter|1|242-postInit
            // write post-init user code here
        }//GEN-BEGIN:|242-getter|2|
        return okCommand8;
    }
    //</editor-fold>//GEN-END:|242-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Method: haveTwitterAcount ">//GEN-BEGIN:|255-if|0|255-preIf
    /**
     * Performs an action assigned to the haveTwitterAcount if-point.
     */
    public void haveTwitterAcount() {//GEN-END:|255-if|0|255-preIf
        // enter pre-if user code here
        boolean hasAccount;
        try {
            hasAccount = isTwitterAccountExists(SERVER_IP, PORT, userID);
        } catch (InvalidUserIdException e){
            e.printStackTrace();
            switchDisplayable(getError(), getLoginScreen());
            return;
        }
        if (hasAccount) {//GEN-LINE:|255-if|1|256-preAction
            // write pre-action user code here
            switchDisplayable(null, getAlreadyHaveTwitter());//GEN-LINE:|255-if|2|256-postAction
            // write post-action user code here
        } else {//GEN-LINE:|255-if|3|257-preAction
            // write pre-action user code here
            genReqURL();//GEN-LINE:|255-if|4|257-postAction
            // write post-action user code here
        }//GEN-LINE:|255-if|5|255-postIf
        // enter post-if user code here
    }//GEN-BEGIN:|255-if|6|
    //</editor-fold>//GEN-END:|255-if|6|

    //<editor-fold defaultstate="collapsed" desc=" Generated Method: isAuthSuccess ">//GEN-BEGIN:|259-if|0|259-preIf
    /**
     * Performs an action assigned to the isAuthSuccess if-point.
     */
    public void isAuthSuccess() {//GEN-END:|259-if|0|259-preIf
        // enter pre-if user code here
        boolean accountCreated;
        try {
            accountCreated = createTwitterAccount(SERVER_IP, PORT, userID);
        } catch (InvalidUserIdException e ) {
            e.printStackTrace();
                switchDisplayable(getError(), getLoginScreen());
            return;
        }
        if (accountCreated) {//GEN-LINE:|259-if|1|260-preAction
            // write pre-action user code here
            switchDisplayable(null, getAuthSuccessful());//GEN-LINE:|259-if|2|260-postAction
            // write post-action user code here
        } else {//GEN-LINE:|259-if|3|261-preAction
            // write pre-action user code here
            switchDisplayable(null, getTwitterAuthFailed());//GEN-LINE:|259-if|4|261-postAction
            // write post-action user code here
        }//GEN-LINE:|259-if|5|259-postIf
        // enter post-if user code here
    }//GEN-BEGIN:|259-if|6|
    //</editor-fold>//GEN-END:|259-if|6|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: authTwitter ">//GEN-BEGIN:|252-getter|0|252-preInit
    /**
     * Returns an initiliazed instance of authTwitter component.
     * @return the initialized component instance
     */
    public List getAuthTwitter() {
        if (authTwitter == null) {//GEN-END:|252-getter|0|252-preInit
            // write pre-init user code here
            authTwitter = new List("Twitter Authorization", Choice.IMPLICIT);//GEN-BEGIN:|252-getter|1|252-postInit
            authTwitter.append("Go to Authorization page", null);
            authTwitter.append("Authorized", null);
            authTwitter.addCommand(getBackCommand2());
            authTwitter.setCommandListener(this);
            authTwitter.setSelectedFlags(new boolean[] { false, false });//GEN-END:|252-getter|1|252-postInit
            // write post-init user code here
        }//GEN-BEGIN:|252-getter|2|
        return authTwitter;
    }
    //</editor-fold>//GEN-END:|252-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Method: authTwitterAction ">//GEN-BEGIN:|252-action|0|252-preAction
    /**
     * Performs an action assigned to the selected list element in the authTwitter component.
     */
    public void authTwitterAction() {//GEN-END:|252-action|0|252-preAction
        // enter pre-action user code here
        String __selectedString = getAuthTwitter().getString(getAuthTwitter().getSelectedIndex());//GEN-BEGIN:|252-action|1|263-preAction
        if (__selectedString != null) {
            if (__selectedString.equals("Go to Authorization page")) {//GEN-END:|252-action|1|263-preAction
                // write pre-action user code here
                haveTwitterAcount();//GEN-LINE:|252-action|2|263-postAction
                // write post-action user code here
            } else if (__selectedString.equals("Authorized")) {//GEN-LINE:|252-action|3|264-preAction
                // write pre-action user code here
                isAuthSuccess();//GEN-LINE:|252-action|4|264-postAction
                // write post-action user code here
            }//GEN-BEGIN:|252-action|5|252-postAction
        }//GEN-END:|252-action|5|252-postAction
        // enter post-action user code here
    }//GEN-BEGIN:|252-action|6|
    //</editor-fold>//GEN-END:|252-action|6|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: backCommand2 ">//GEN-BEGIN:|265-getter|0|265-preInit
    /**
     * Returns an initiliazed instance of backCommand2 component.
     * @return the initialized component instance
     */
    public Command getBackCommand2() {
        if (backCommand2 == null) {//GEN-END:|265-getter|0|265-preInit
            // write pre-init user code here
            backCommand2 = new Command("Back", Command.BACK, 0);//GEN-LINE:|265-getter|1|265-postInit
            // write post-init user code here
        }//GEN-BEGIN:|265-getter|2|
        return backCommand2;
    }
    //</editor-fold>//GEN-END:|265-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: unFilter ">//GEN-BEGIN:|271-getter|0|271-preInit
    /**
     * Returns an initiliazed instance of unFilter component.
     * @return the initialized component instance
     */
    public Command getUnFilter() {
        if (unFilter == null) {//GEN-END:|271-getter|0|271-preInit
            // write pre-init user code here
            unFilter = new Command("Back", Command.BACK, 0);//GEN-LINE:|271-getter|1|271-postInit
            // write post-init user code here
        }//GEN-BEGIN:|271-getter|2|
        return unFilter;
    }
    //</editor-fold>//GEN-END:|271-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: ReplaceTwitterAccount ">//GEN-BEGIN:|280-getter|0|280-preInit
    /**
     * Returns an initiliazed instance of ReplaceTwitterAccount component.
     * @return the initialized component instance
     */
    public Command getReplaceTwitterAccount() {
        if (ReplaceTwitterAccount == null) {//GEN-END:|280-getter|0|280-preInit
            // write pre-init user code here
            ReplaceTwitterAccount = new Command("Replace", Command.OK, 0);//GEN-LINE:|280-getter|1|280-postInit
            // write post-init user code here
        }//GEN-BEGIN:|280-getter|2|
        return ReplaceTwitterAccount;
    }
    //</editor-fold>//GEN-END:|280-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: BackToAccounts ">//GEN-BEGIN:|282-getter|0|282-preInit
    /**
     * Returns an initiliazed instance of BackToAccounts component.
     * @return the initialized component instance
     */
    public Command getBackToAccounts() {
        if (BackToAccounts == null) {//GEN-END:|282-getter|0|282-preInit
            // write pre-init user code here
            BackToAccounts = new Command("Back", Command.BACK, 0);//GEN-LINE:|282-getter|1|282-postInit
            // write post-init user code here
        }//GEN-BEGIN:|282-getter|2|
        return BackToAccounts;
    }
    //</editor-fold>//GEN-END:|282-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: Resend1 ">//GEN-BEGIN:|301-getter|0|301-preInit
    /**
     * Returns an initiliazed instance of Resend1 component.
     * @return the initialized component instance
     */
    public Command getResend1() {
        if (Resend1 == null) {//GEN-END:|301-getter|0|301-preInit
            // write pre-init user code here
            Resend1 = new Command("Resend", Command.OK, 0);//GEN-LINE:|301-getter|1|301-postInit
            // write post-init user code here
        }//GEN-BEGIN:|301-getter|2|
        return Resend1;
    }
    //</editor-fold>//GEN-END:|301-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: backCommand10 ">//GEN-BEGIN:|303-getter|0|303-preInit
    /**
     * Returns an initiliazed instance of backCommand10 component.
     * @return the initialized component instance
     */
    public Command getBackCommand10() {
        if (backCommand10 == null) {//GEN-END:|303-getter|0|303-preInit
            // write pre-init user code here
            backCommand10 = new Command("Back", Command.BACK, 0);//GEN-LINE:|303-getter|1|303-postInit
            // write post-init user code here
        }//GEN-BEGIN:|303-getter|2|
        return backCommand10;
    }
    //</editor-fold>//GEN-END:|303-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: alreadyHaveTwitter ">//GEN-BEGIN:|276-getter|0|276-preInit
    /**
     * Returns an initiliazed instance of alreadyHaveTwitter component.
     * @return the initialized component instance
     */
    public Form getAlreadyHaveTwitter() {
        if (alreadyHaveTwitter == null) {//GEN-END:|276-getter|0|276-preInit
            // write pre-init user code here
            alreadyHaveTwitter = new Form("Twitter Authorization", new Item[] { getStringItem2() });//GEN-BEGIN:|276-getter|1|276-postInit
            alreadyHaveTwitter.addCommand(getReplaceTwitterAccount());
            alreadyHaveTwitter.addCommand(getBackToAccounts());
            alreadyHaveTwitter.setCommandListener(this);//GEN-END:|276-getter|1|276-postInit
            // write post-init user code here
        }//GEN-BEGIN:|276-getter|2|
        return alreadyHaveTwitter;
    }
    //</editor-fold>//GEN-END:|276-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: stringItem2 ">//GEN-BEGIN:|279-getter|0|279-preInit
    /**
     * Returns an initiliazed instance of stringItem2 component.
     * @return the initialized component instance
     */
    public StringItem getStringItem2() {
        if (stringItem2 == null) {//GEN-END:|279-getter|0|279-preInit
            // write pre-init user code here
            stringItem2 = new StringItem("Twitter Account Exists", "You already have a twitter account. To replace it with a new one, press Replace");//GEN-LINE:|279-getter|1|279-postInit
            // write post-init user code here
        }//GEN-BEGIN:|279-getter|2|
        return stringItem2;
    }
    //</editor-fold>//GEN-END:|279-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: authSuccessful ">//GEN-BEGIN:|289-getter|0|289-preInit
    /**
     * Returns an initiliazed instance of authSuccessful component.
     * @return the initialized component instance
     */
    public Form getAuthSuccessful() {
        if (authSuccessful == null) {//GEN-END:|289-getter|0|289-preInit
            // write pre-init user code here
            authSuccessful = new Form("Twitter Authorization", new Item[] { getStringItem3() });//GEN-BEGIN:|289-getter|1|289-postInit
            authSuccessful.addCommand(getBackToAccounts());
            authSuccessful.setCommandListener(this);//GEN-END:|289-getter|1|289-postInit
            // write post-init user code here
        }//GEN-BEGIN:|289-getter|2|
        return authSuccessful;
    }
    //</editor-fold>//GEN-END:|289-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: stringItem3 ">//GEN-BEGIN:|293-getter|0|293-preInit
    /**
     * Returns an initiliazed instance of stringItem3 component.
     * @return the initialized component instance
     */
    public StringItem getStringItem3() {
        if (stringItem3 == null) {//GEN-END:|293-getter|0|293-preInit
            // write pre-init user code here
            stringItem3 = new StringItem("Authorization successful ", "You are now connected to your twitter account");//GEN-LINE:|293-getter|1|293-postInit
            // write post-init user code here
        }//GEN-BEGIN:|293-getter|2|
        return stringItem3;
    }
    //</editor-fold>//GEN-END:|293-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: twitterAuthFailed ">//GEN-BEGIN:|300-getter|0|300-preInit
    /**
     * Returns an initiliazed instance of twitterAuthFailed component.
     * @return the initialized component instance
     */
    public Form getTwitterAuthFailed() {
        if (twitterAuthFailed == null) {//GEN-END:|300-getter|0|300-preInit
            // write pre-init user code here
            twitterAuthFailed = new Form("Twitter Authorization", new Item[] { getStringItem4() });//GEN-BEGIN:|300-getter|1|300-postInit
            twitterAuthFailed.addCommand(getResend1());
            twitterAuthFailed.addCommand(getBackToAccounts());
            twitterAuthFailed.setCommandListener(this);//GEN-END:|300-getter|1|300-postInit
            // write post-init user code here
        }//GEN-BEGIN:|300-getter|2|
        return twitterAuthFailed;
    }
    //</editor-fold>//GEN-END:|300-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: stringItem4 ">//GEN-BEGIN:|306-getter|0|306-preInit
    /**
     * Returns an initiliazed instance of stringItem4 component.
     * @return the initialized component instance
     */
    public StringItem getStringItem4() {
        if (stringItem4 == null) {//GEN-END:|306-getter|0|306-preInit
            // write pre-init user code here
            stringItem4 = new StringItem("Authorization Failed", "Please press resend to resend the authorization url. Note that Twitter timesout requests");//GEN-LINE:|306-getter|1|306-postInit
            // write post-init user code here
        }//GEN-BEGIN:|306-getter|2|
        return stringItem4;
    }
    //</editor-fold>//GEN-END:|306-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: disliked ">//GEN-BEGIN:|311-getter|0|311-preInit
    /**
     * Returns an initiliazed instance of disliked component.
     * @return the initialized component instance
     */
    public List getDisliked() {
        if (disliked == null) {//GEN-END:|311-getter|0|311-preInit
            // write pre-init user code here
            disliked = new List("disliked", Choice.IMPLICIT);//GEN-BEGIN:|311-getter|1|311-postInit
            disliked.addCommand(getBackCommand());
            disliked.setCommandListener(this);//GEN-END:|311-getter|1|311-postInit
            // write post-init user code here
        }//GEN-BEGIN:|311-getter|2|
        return disliked;
    }
    //</editor-fold>//GEN-END:|311-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Method: dislikedAction ">//GEN-BEGIN:|311-action|0|311-preAction
    /**
     * Performs an action assigned to the selected list element in the disliked component.
     */
    public void dislikedAction() {//GEN-END:|311-action|0|311-preAction
        // enter pre-action user code here
        String __selectedString = getDisliked().getString(getDisliked().getSelectedIndex());//GEN-LINE:|311-action|1|311-postAction
        // enter post-action user code here
    }//GEN-BEGIN:|311-action|2|
    //</editor-fold>//GEN-END:|311-action|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: friendsLike ">//GEN-BEGIN:|316-getter|0|316-preInit
    /**
     * Returns an initiliazed instance of friendsLike component.
     * @return the initialized component instance
     */
    public Command getFriendsLike() {
        if (friendsLike == null) {//GEN-END:|316-getter|0|316-preInit
            // write pre-init user code here
            friendsLike = new Command("Thumbed Up by", Command.OK, 0);//GEN-LINE:|316-getter|1|316-postInit
            // write post-init user code here
        }//GEN-BEGIN:|316-getter|2|
        return friendsLike;
    }
    //</editor-fold>//GEN-END:|316-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: friendsDislike ">//GEN-BEGIN:|318-getter|0|318-preInit
    /**
     * Returns an initiliazed instance of friendsDislike component.
     * @return the initialized component instance
     */
    public Command getFriendsDislike() {
        if (friendsDislike == null) {//GEN-END:|318-getter|0|318-preInit
            // write pre-init user code here
            friendsDislike = new Command("Thumbed Down by", Command.OK, 0);//GEN-LINE:|318-getter|1|318-postInit
            // write post-init user code here
        }//GEN-BEGIN:|318-getter|2|
        return friendsDislike;
    }
    //</editor-fold>//GEN-END:|318-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: singOut ">//GEN-BEGIN:|320-getter|0|320-preInit
    /**
     * Returns an initiliazed instance of singOut component.
     * @return the initialized component instance
     */
    public Command getSingOut() {
        if (singOut == null) {//GEN-END:|320-getter|0|320-preInit
            // write pre-init user code here
            singOut = new Command("Sign out", Command.OK, 0);//GEN-LINE:|320-getter|1|320-postInit
            // write post-init user code here
        }//GEN-BEGIN:|320-getter|2|
        return singOut;
    }
    //</editor-fold>//GEN-END:|320-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: backCommand11 ">//GEN-BEGIN:|324-getter|0|324-preInit
    /**
     * Returns an initiliazed instance of backCommand11 component.
     * @return the initialized component instance
     */
    public Command getBackCommand11() {
        if (backCommand11 == null) {//GEN-END:|324-getter|0|324-preInit
            // write pre-init user code here
            backCommand11 = new Command("Back", Command.BACK, 0);//GEN-LINE:|324-getter|1|324-postInit
            // write post-init user code here
        }//GEN-BEGIN:|324-getter|2|
        return backCommand11;
    }
    //</editor-fold>//GEN-END:|324-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: okCommand9 ">//GEN-BEGIN:|326-getter|0|326-preInit
    /**
     * Returns an initiliazed instance of okCommand9 component.
     * @return the initialized component instance
     */
    public Command getOkCommand9() {
        if (okCommand9 == null) {//GEN-END:|326-getter|0|326-preInit
            // write pre-init user code here
            okCommand9 = new Command("Ok", Command.OK, 0);//GEN-LINE:|326-getter|1|326-postInit
            // write post-init user code here
        }//GEN-BEGIN:|326-getter|2|
        return okCommand9;
    }
    //</editor-fold>//GEN-END:|326-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: backCommand12 ">//GEN-BEGIN:|328-getter|0|328-preInit
    /**
     * Returns an initiliazed instance of backCommand12 component.
     * @return the initialized component instance
     */
    public Command getBackCommand12() {
        if (backCommand12 == null) {//GEN-END:|328-getter|0|328-preInit
            // write pre-init user code here
            backCommand12 = new Command("Back", Command.BACK, 0);//GEN-LINE:|328-getter|1|328-postInit
            // write post-init user code here
        }//GEN-BEGIN:|328-getter|2|
        return backCommand12;
    }
    //</editor-fold>//GEN-END:|328-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: accept ">//GEN-BEGIN:|330-getter|0|330-preInit
    /**
     * Returns an initiliazed instance of accept component.
     * @return the initialized component instance
     */
    public Command getAccept() {
        if (accept == null) {//GEN-END:|330-getter|0|330-preInit
            // write pre-init user code here
            accept = new Command("Ok", Command.OK, 0);//GEN-LINE:|330-getter|1|330-postInit
            // write post-init user code here
        }//GEN-BEGIN:|330-getter|2|
        return accept;
    }
    //</editor-fold>//GEN-END:|330-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: reject ">//GEN-BEGIN:|332-getter|0|332-preInit
    /**
     * Returns an initiliazed instance of reject component.
     * @return the initialized component instance
     */
    public Command getReject() {
        if (reject == null) {//GEN-END:|332-getter|0|332-preInit
            // write pre-init user code here
            reject = new Command("Ok", Command.OK, 0);//GEN-LINE:|332-getter|1|332-postInit
            // write post-init user code here
        }//GEN-BEGIN:|332-getter|2|
        return reject;
    }
    //</editor-fold>//GEN-END:|332-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: manyReq ">//GEN-BEGIN:|322-getter|0|322-preInit
    /**
     * Returns an initiliazed instance of manyReq component.
     * @return the initialized component instance
     */
    public Form getManyReq() {
        if (manyReq == null) {//GEN-END:|322-getter|0|322-preInit
            // write pre-init user code here
            manyReq = new Form("form2");//GEN-BEGIN:|322-getter|1|322-postInit
            manyReq.addCommand(getBackCommand11());
            manyReq.addCommand(getOkCommand9());
            manyReq.setCommandListener(this);//GEN-END:|322-getter|1|322-postInit
            // write post-init user code here
        }//GEN-BEGIN:|322-getter|2|
        return manyReq;
    }
    //</editor-fold>//GEN-END:|322-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: oneReq ">//GEN-BEGIN:|323-getter|0|323-preInit
    /**
     * Returns an initiliazed instance of oneReq component.
     * @return the initialized component instance
     */
    public Form getOneReq() {
        if (oneReq == null) {//GEN-END:|323-getter|0|323-preInit
            // write pre-init user code here
            oneReq = new Form("form3");//GEN-BEGIN:|323-getter|1|323-postInit
            oneReq.addCommand(getBackCommand12());
            oneReq.addCommand(getAccept());
            oneReq.addCommand(getReject());
            oneReq.setCommandListener(this);//GEN-END:|323-getter|1|323-postInit
            // write post-init user code here
        }//GEN-BEGIN:|323-getter|2|
        return oneReq;
    }
    //</editor-fold>//GEN-END:|323-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: okCommand10 ">//GEN-BEGIN:|334-getter|0|334-preInit
    /**
     * Returns an initiliazed instance of okCommand10 component.
     * @return the initialized component instance
     */
    public Command getOkCommand10() {
        if (okCommand10 == null) {//GEN-END:|334-getter|0|334-preInit
            // write pre-init user code here
            okCommand10 = new Command("Next", Command.OK, 0);//GEN-LINE:|334-getter|1|334-postInit
            // write post-init user code here
        }//GEN-BEGIN:|334-getter|2|
        return okCommand10;
    }
    //</editor-fold>//GEN-END:|334-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Method: genReqURL ">//GEN-BEGIN:|338-if|0|338-preIf
    /**
     * Performs an action assigned to the genReqURL if-point.
     */
    public void genReqURL() {//GEN-END:|338-if|0|338-preIf
        // enter pre-if user code here
        boolean valid;
        try {
            String reqURL = getTwitterAuthURL(SERVER_IP, PORT);
            valid = reqURL.startsWith("http");
            // write pre-action user code here
            platformRequest(reqURL);
        } catch (Exception e) {
            e.printStackTrace();
            valid = false;
        }

        if (valid) {//GEN-LINE:|338-if|1|339-preAction
            // write pre-action user code here
            switchDisplayable(null, getAuthTwitter());//GEN-LINE:|338-if|2|339-postAction
            // write post-action user code here
        } else {//GEN-LINE:|338-if|3|340-preAction
            // write pre-action user code here
            switchDisplayable(null, getURLCorrupted());//GEN-LINE:|338-if|4|340-postAction
            // write post-action user code here
        }//GEN-LINE:|338-if|5|338-postIf
        // enter post-if user code here
    }//GEN-BEGIN:|338-if|6|
    //</editor-fold>//GEN-END:|338-if|6|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: okCommand11 ">//GEN-BEGIN:|344-getter|0|344-preInit
    /**
     * Returns an initiliazed instance of okCommand11 component.
     * @return the initialized component instance
     */
    public Command getOkCommand11() {
        if (okCommand11 == null) {//GEN-END:|344-getter|0|344-preInit
            // write pre-init user code here
            okCommand11 = new Command("Ok", Command.OK, 0);//GEN-LINE:|344-getter|1|344-postInit
            // write post-init user code here
        }//GEN-BEGIN:|344-getter|2|
        return okCommand11;
    }
    //</editor-fold>//GEN-END:|344-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: URLCorrupted ">//GEN-BEGIN:|342-getter|0|342-preInit
    /**
     * Returns an initiliazed instance of URLCorrupted component.
     * @return the initialized component instance
     */
    public Alert getURLCorrupted() {
        if (URLCorrupted == null) {//GEN-END:|342-getter|0|342-preInit
            // write pre-init user code here
            URLCorrupted = new Alert("Twitter Authorization", "Coudn\'t get authorization url. There might be something wrong with your internet connecion, our server or twitter server", null, null);//GEN-BEGIN:|342-getter|1|342-postInit
            URLCorrupted.addCommand(getOkCommand11());
            URLCorrupted.setCommandListener(this);
            URLCorrupted.setTimeout(Alert.FOREVER);//GEN-END:|342-getter|1|342-postInit
            // write post-init user code here
        }//GEN-BEGIN:|342-getter|2|
        return URLCorrupted;
    }
    //</editor-fold>//GEN-END:|342-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: StorynotFound ">//GEN-BEGIN:|353-getter|0|353-preInit
    /**
     * Returns an initiliazed instance of StorynotFound component.
     * @return the initialized component instance
     */
    public Alert getStorynotFound() {
        if (StorynotFound == null) {//GEN-END:|353-getter|0|353-preInit
            // write pre-init user code here
            StorynotFound = new Alert("Story not Found", null, null, AlertType.ERROR);//GEN-BEGIN:|353-getter|1|353-postInit
            StorynotFound.setTimeout(Alert.FOREVER);//GEN-END:|353-getter|1|353-postInit
            // write post-init user code here
        }//GEN-BEGIN:|353-getter|2|
        return StorynotFound;
    }
    //</editor-fold>//GEN-END:|353-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: Dummy ">//GEN-BEGIN:|354-getter|0|354-preInit
    /**
     * Returns an initiliazed instance of Dummy component.
     * @return the initialized component instance
     */
    public Form getDummy() {
        if (Dummy == null) {//GEN-END:|354-getter|0|354-preInit
            // write pre-init user code here
            Dummy = new Form("form2");//GEN-LINE:|354-getter|1|354-postInit
            //this dummy form is used as a temp form which switches back to CommentsMany "to avoid illegalStateException
            switchDisplayable(null, getCommentsMany());
            return null;  //switch back to getCommentsMany
        }//GEN-BEGIN:|354-getter|2|
        return Dummy;
    }
    //</editor-fold>//GEN-END:|354-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: viewCommentsMany ">//GEN-BEGIN:|355-getter|0|355-preInit
    /**
     * Returns an initiliazed instance of viewCommentsMany component.
     * @return the initialized component instance
     */
    public Command getViewCommentsMany() {
        if (viewCommentsMany == null) {//GEN-END:|355-getter|0|355-preInit
            // write pre-init user code here
            viewCommentsMany = new Command("Comments", Command.ITEM, 0);//GEN-LINE:|355-getter|1|355-postInit
            // write post-init user code here
        }//GEN-BEGIN:|355-getter|2|
        return viewCommentsMany;
    }
    //</editor-fold>//GEN-END:|355-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: FilterStories ">//GEN-BEGIN:|360-getter|0|360-preInit
    /**
     * Returns an initiliazed instance of FilterStories component.
     * @return the initialized component instance
     */
    public Command getFilterStories() {
        if (FilterStories == null) {//GEN-END:|360-getter|0|360-preInit
            // write pre-init user code here
            FilterStories = new Command("Screen", Command.SCREEN, 0);//GEN-LINE:|360-getter|1|360-postInit
            // write post-init user code here
        }//GEN-BEGIN:|360-getter|2|
        return FilterStories;
    }
    //</editor-fold>//GEN-END:|360-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: FilterStories1 ">//GEN-BEGIN:|364-getter|0|364-preInit
    /**
     * Returns an initiliazed instance of FilterStories1 component.
     * @return the initialized component instance
     */
    public Command getFilterStories1() {
        if (FilterStories1 == null) {//GEN-END:|364-getter|0|364-preInit
            // write pre-init user code here
            FilterStories1 = new Command("FilterStories", Command.ITEM, 0);//GEN-LINE:|364-getter|1|364-postInit
            // write post-init user code here
        }//GEN-BEGIN:|364-getter|2|
        return FilterStories1;
    }
    //</editor-fold>//GEN-END:|364-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: goToVerification ">//GEN-BEGIN:|369-getter|0|369-preInit
    /**
     * Returns an initiliazed instance of goToVerification component.
     * @return the initialized component instance
     */
    public Command getGoToVerification() {
        if (goToVerification == null) {//GEN-END:|369-getter|0|369-preInit
            // write pre-init user code here
            goToVerification = new Command("Verify", Command.ITEM, 0);//GEN-LINE:|369-getter|1|369-postInit
            // write post-init user code here
        }//GEN-BEGIN:|369-getter|2|
        return goToVerification;
    }
    //</editor-fold>//GEN-END:|369-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: backCommand13 ">//GEN-BEGIN:|377-getter|0|377-preInit
    /**
     * Returns an initiliazed instance of backCommand13 component.
     * @return the initialized component instance
     */
    public Command getBackCommand13() {
        if (backCommand13 == null) {//GEN-END:|377-getter|0|377-preInit
            // write pre-init user code here
            backCommand13 = new Command("Back", Command.BACK, 0);//GEN-LINE:|377-getter|1|377-postInit
            // write post-init user code here
        }//GEN-BEGIN:|377-getter|2|
        return backCommand13;
    }
    //</editor-fold>//GEN-END:|377-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: choose ">//GEN-BEGIN:|379-getter|0|379-preInit
    /**
     * Returns an initiliazed instance of choose component.
     * @return the initialized component instance
     */
    public Command getChoose() {
        if (choose == null) {//GEN-END:|379-getter|0|379-preInit
            // write pre-init user code here
            choose = new Command("Choose", Command.OK, 0);//GEN-LINE:|379-getter|1|379-postInit
            // write post-init user code here
        }//GEN-BEGIN:|379-getter|2|
        return choose;
    }
    //</editor-fold>//GEN-END:|379-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: Toggle ">//GEN-BEGIN:|375-getter|0|375-preInit
    /**
     * Returns an initiliazed instance of Toggle component.
     * @return the initialized component instance
     */
    public Form getToggle() {
        if (Toggle == null) {//GEN-END:|375-getter|0|375-preInit
            // write pre-init user code here
            Toggle = new Form("Interests", new Item[] { getChoiceGroup() });//GEN-BEGIN:|375-getter|1|375-postInit
            Toggle.addCommand(getBackCommand13());
            Toggle.addCommand(getChoose());
            Toggle.setCommandListener(this);//GEN-END:|375-getter|1|375-postInit
            // write post-init user code here
        }//GEN-BEGIN:|375-getter|2|
        return Toggle;
    }
    //</editor-fold>//GEN-END:|375-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: choiceGroup ">//GEN-BEGIN:|376-getter|0|376-preInit
    /**
     * Returns an initiliazed instance of choiceGroup component.
     * @return the initialized component instance
     */
    public ChoiceGroup getChoiceGroup() {
        if (choiceGroup == null) {//GEN-END:|376-getter|0|376-preInit
            // write pre-init user code here
            choiceGroup = new ChoiceGroup("choiceGroup", Choice.MULTIPLE);//GEN-LINE:|376-getter|1|376-postInit
            // write post-init user code here
        }//GEN-BEGIN:|376-getter|2|
        return choiceGroup;
    }
    //</editor-fold>//GEN-END:|376-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: backCommand14 ">//GEN-BEGIN:|382-getter|0|382-preInit
    /**
     * Returns an initiliazed instance of backCommand14 component.
     * @return the initialized component instance
     */
    public Command getBackCommand14() {
        if (backCommand14 == null) {//GEN-END:|382-getter|0|382-preInit
            // write pre-init user code here
            backCommand14 = new Command("Back", Command.BACK, 0);//GEN-LINE:|382-getter|1|382-postInit
            // write post-init user code here
        }//GEN-BEGIN:|382-getter|2|
        return backCommand14;
    }
    //</editor-fold>//GEN-END:|382-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: okCommand13 ">//GEN-BEGIN:|384-getter|0|384-preInit
    /**
     * Returns an initiliazed instance of okCommand13 component.
     * @return the initialized component instance
     */
    public Command getOkCommand13() {
        if (okCommand13 == null) {//GEN-END:|384-getter|0|384-preInit
            // write pre-init user code here
            okCommand13 = new Command("Ok", Command.OK, 0);//GEN-LINE:|384-getter|1|384-postInit
            // write post-init user code here
        }//GEN-BEGIN:|384-getter|2|
        return okCommand13;
    }
    //</editor-fold>//GEN-END:|384-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: interestConfirm ">//GEN-BEGIN:|381-getter|0|381-preInit
    /**
     * Returns an initiliazed instance of interestConfirm component.
     * @return the initialized component instance
     */
    public Form getInterestConfirm() {
        if (interestConfirm == null) {//GEN-END:|381-getter|0|381-preInit
            // write pre-init user code here
            interestConfirm = new Form("2allak");//GEN-BEGIN:|381-getter|1|381-postInit
            interestConfirm.addCommand(getBackCommand14());
            interestConfirm.addCommand(getOkCommand13());
            interestConfirm.setCommandListener(this);//GEN-END:|381-getter|1|381-postInit
            // write post-init user code here
            help1();
        }//GEN-BEGIN:|381-getter|2|
        return interestConfirm;
    }
    //</editor-fold>//GEN-END:|381-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: InternetError ">//GEN-BEGIN:|389-getter|0|389-preInit
    /**
     * Returns an initiliazed instance of InternetError component.
     * @return the initialized component instance
     */
    public Alert getInternetError() {
        if (InternetError == null) {//GEN-END:|389-getter|0|389-preInit
            // write pre-init user code here
            InternetError = new Alert("Connection Error", "Cannot connect to the Internet, please check your connection", null, AlertType.ERROR);//GEN-BEGIN:|389-getter|1|389-postInit
            InternetError.setTimeout(Alert.FOREVER);//GEN-END:|389-getter|1|389-postInit
            // write post-init user code here
        }//GEN-BEGIN:|389-getter|2|
        return InternetError;
    }
    //</editor-fold>//GEN-END:|389-getter|2|



    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: AlreadyVerified ">//GEN-BEGIN:|391-getter|0|391-preInit
    /**
     * Returns an initiliazed instance of AlreadyVerified component.
     * @return the initialized component instance
     */
    public Alert getAlreadyVerified() {
        if (AlreadyVerified == null) {//GEN-END:|391-getter|0|391-preInit
            // write pre-init user code here
            AlreadyVerified = new Alert("Already Verified", "Your account is already verified", null, AlertType.INFO);//GEN-BEGIN:|391-getter|1|391-postInit
            AlreadyVerified.setTimeout(Alert.FOREVER);//GEN-END:|391-getter|1|391-postInit
            // write post-init user code here
        }//GEN-BEGIN:|391-getter|2|
        return AlreadyVerified;
    }
    //</editor-fold>//GEN-END:|391-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: registerCommand ">//GEN-BEGIN:|394-getter|0|394-preInit
    /**
     * Returns an initiliazed instance of registerCommand component.
     * @return the initialized component instance
     */
    public Command getRegisterCommand() {
        if (registerCommand == null) {//GEN-END:|394-getter|0|394-preInit
            // write pre-init user code here
            registerCommand = new Command("Register", Command.ITEM, 0);//GEN-LINE:|394-getter|1|394-postInit
            // write post-init user code here
        }//GEN-BEGIN:|394-getter|2|
        return registerCommand;
    }
    //</editor-fold>//GEN-END:|394-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: alert ">//GEN-BEGIN:|396-getter|0|396-preInit
    /**
     * Returns an initiliazed instance of alert component.
     * @return the initialized component instance
     */
    public Alert getAlert() {
        if (alert == null) {//GEN-END:|396-getter|0|396-preInit
            // write pre-init user code here
            alert = new Alert("Connection Lost");//GEN-BEGIN:|396-getter|1|396-postInit
            alert.setTimeout(Alert.FOREVER);//GEN-END:|396-getter|1|396-postInit
            // write post-init user code here
            alert.setString("No Internet Connection");
        }//GEN-BEGIN:|396-getter|2|
        return alert;
    }
    //</editor-fold>//GEN-END:|396-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: ServerError ">//GEN-BEGIN:|399-getter|0|399-preInit
    /**
     * Returns an initiliazed instance of ServerError component.
     * @return the initialized component instance
     */
    public Alert getServerError() {
        if (ServerError == null) {//GEN-END:|399-getter|0|399-preInit
            // write pre-init user code here
            ServerError = new Alert("Server Error", "Error connecting to the server, please try again later", null, AlertType.ERROR);//GEN-BEGIN:|399-getter|1|399-postInit
            ServerError.setTimeout(Alert.FOREVER);//GEN-END:|399-getter|1|399-postInit
            // write post-init user code here
        }//GEN-BEGIN:|399-getter|2|
        return ServerError;
    }
    //</editor-fold>//GEN-END:|399-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: passMissMatch ">//GEN-BEGIN:|401-getter|0|401-preInit
    /**
     * Returns an initiliazed instance of passMissMatch component.
     * @return the initialized component instance
     */
    public Alert getPassMissMatch() {
        if (passMissMatch == null) {//GEN-END:|401-getter|0|401-preInit
            // write pre-init user code here
            passMissMatch = new Alert("alert1", "Password Missmatch", null, AlertType.ALARM);//GEN-BEGIN:|401-getter|1|401-postInit
            passMissMatch.setTimeout(Alert.FOREVER);//GEN-END:|401-getter|1|401-postInit
            // write post-init user code here
        }//GEN-BEGIN:|401-getter|2|
        return passMissMatch;
    }
    //</editor-fold>//GEN-END:|401-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: emptyFields ">//GEN-BEGIN:|402-getter|0|402-preInit
    /**
     * Returns an initiliazed instance of emptyFields component.
     * @return the initialized component instance
     */
    public Alert getEmptyFields() {
        if (emptyFields == null) {//GEN-END:|402-getter|0|402-preInit
            // write pre-init user code here
            emptyFields = new Alert("alert1", "You Have Entered Nothing", null, AlertType.ERROR);//GEN-BEGIN:|402-getter|1|402-postInit
            emptyFields.setTimeout(Alert.FOREVER);//GEN-END:|402-getter|1|402-postInit
            // write post-init user code here
        }//GEN-BEGIN:|402-getter|2|
        return emptyFields;
    }
    //</editor-fold>//GEN-END:|402-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: backCommand15 ">//GEN-BEGIN:|405-getter|0|405-preInit
    /**
     * Returns an initiliazed instance of backCommand15 component.
     * @return the initialized component instance
     */
    public Command getBackCommand15() {
        if (backCommand15 == null) {//GEN-END:|405-getter|0|405-preInit
            // write pre-init user code here
            backCommand15 = new Command("Back", Command.BACK, 0);//GEN-LINE:|405-getter|1|405-postInit
            // write post-init user code here
        }//GEN-BEGIN:|405-getter|2|
        return backCommand15;
    }
    //</editor-fold>//GEN-END:|405-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: alert1 ">//GEN-BEGIN:|404-getter|0|404-preInit
    /**
     * Returns an initiliazed instance of alert1 component.
     * @return the initialized component instance
     */
    public Alert getAlert1() {
        if (alert1 == null) {//GEN-END:|404-getter|0|404-preInit
            // write pre-init user code here
            alert1 = new Alert("alert1", "The selected story was removed!", null, null);//GEN-BEGIN:|404-getter|1|404-postInit
            alert1.addCommand(getBackCommand15());
            alert1.setCommandListener(this);
            alert1.setTimeout(Alert.FOREVER);//GEN-END:|404-getter|1|404-postInit
            // write post-init user code here
        }//GEN-BEGIN:|404-getter|2|
        return alert1;
    }
    //</editor-fold>//GEN-END:|404-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: WrongEmailFormat ">//GEN-BEGIN:|409-getter|0|409-preInit
    /**
     * Returns an initiliazed instance of WrongEmailFormat component.
     * @return the initialized component instance
     */
    public Alert getWrongEmailFormat() {
        if (WrongEmailFormat == null) {//GEN-END:|409-getter|0|409-preInit
            // write pre-init user code here
            WrongEmailFormat = new Alert("Wrong Email Format", "The email you entered is incorrectly formatted", null, null);//GEN-BEGIN:|409-getter|1|409-postInit
            WrongEmailFormat.setTimeout(Alert.FOREVER);//GEN-END:|409-getter|1|409-postInit
            // write post-init user code here
        }//GEN-BEGIN:|409-getter|2|
        return WrongEmailFormat;
    }
    //</editor-fold>//GEN-END:|409-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: PasswordsDontMatch ">//GEN-BEGIN:|410-getter|0|410-preInit
    /**
     * Returns an initiliazed instance of PasswordsDontMatch component.
     * @return the initialized component instance
     */
    public Alert getPasswordsDontMatch() {
        if (PasswordsDontMatch == null) {//GEN-END:|410-getter|0|410-preInit
            // write pre-init user code here
            PasswordsDontMatch = new Alert("Passwords Not Matching Error", "The passwords you entered don\'t match", null, null);//GEN-BEGIN:|410-getter|1|410-postInit
            PasswordsDontMatch.setTimeout(Alert.FOREVER);//GEN-END:|410-getter|1|410-postInit
            // write post-init user code here
        }//GEN-BEGIN:|410-getter|2|
        return PasswordsDontMatch;
    }
    //</editor-fold>//GEN-END:|410-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: WrongEmailPassCombination ">//GEN-BEGIN:|411-getter|0|411-preInit
    /**
     * Returns an initiliazed instance of WrongEmailPassCombination component.
     * @return the initialized component instance
     */
    public Alert getWrongEmailPassCombination() {
        if (WrongEmailPassCombination == null) {//GEN-END:|411-getter|0|411-preInit
            // write pre-init user code here
            WrongEmailPassCombination = new Alert("Wrong Email/Password Combination", "The email/password combination you entered doesn\'t exist", null, null);//GEN-BEGIN:|411-getter|1|411-postInit
            WrongEmailPassCombination.setTimeout(Alert.FOREVER);//GEN-END:|411-getter|1|411-postInit
            // write post-init user code here
        }//GEN-BEGIN:|411-getter|2|
        return WrongEmailPassCombination;
    }
    //</editor-fold>//GEN-END:|411-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: commentSent ">//GEN-BEGIN:|414-getter|0|414-preInit
    /**
     * Returns an initiliazed instance of commentSent component.
     * @return the initialized component instance
     */
    public Alert getCommentSent() {
        if (commentSent == null) {//GEN-END:|414-getter|0|414-preInit
            // write pre-init user code here
            commentSent = new Alert("alert2", "Comment Posted!", null, AlertType.INFO);//GEN-BEGIN:|414-getter|1|414-postInit
            commentSent.setTimeout(Alert.FOREVER);//GEN-END:|414-getter|1|414-postInit
            // write post-init user code here
        }//GEN-BEGIN:|414-getter|2|
        return commentSent;
    }
    //</editor-fold>//GEN-END:|414-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: UppedBefore ">//GEN-BEGIN:|415-getter|0|415-preInit
    /**
     * Returns an initiliazed instance of UppedBefore component.
     * @return the initialized component instance
     */
    public Alert getUppedBefore() {
        if (UppedBefore == null) {//GEN-END:|415-getter|0|415-preInit
            // write pre-init user code here
            UppedBefore = new Alert("alert2", "You Upped this comment before!", null, AlertType.WARNING);//GEN-BEGIN:|415-getter|1|415-postInit
            UppedBefore.setTimeout(Alert.FOREVER);//GEN-END:|415-getter|1|415-postInit
            // write post-init user code here
        }//GEN-BEGIN:|415-getter|2|
        return UppedBefore;
    }
    //</editor-fold>//GEN-END:|415-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: DownedBefore ">//GEN-BEGIN:|416-getter|0|416-preInit
    /**
     * Returns an initiliazed instance of DownedBefore component.
     * @return the initialized component instance
     */
    public Alert getDownedBefore() {
        if (DownedBefore == null) {//GEN-END:|416-getter|0|416-preInit
            // write pre-init user code here
            DownedBefore = new Alert("alert2", "You downed this comment before!", null, AlertType.WARNING);//GEN-BEGIN:|416-getter|1|416-postInit
            DownedBefore.setTimeout(Alert.FOREVER);//GEN-END:|416-getter|1|416-postInit
            // write post-init user code here
        }//GEN-BEGIN:|416-getter|2|
        return DownedBefore;
    }
    //</editor-fold>//GEN-END:|416-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: CommentFailed ">//GEN-BEGIN:|417-getter|0|417-preInit
    /**
     * Returns an initiliazed instance of CommentFailed component.
     * @return the initialized component instance
     */
    public Alert getCommentFailed() {
        if (CommentFailed == null) {//GEN-END:|417-getter|0|417-preInit
            // write pre-init user code here
            CommentFailed = new Alert("alert2", "Failed to post your comment, please try again later.", null, null);//GEN-BEGIN:|417-getter|1|417-postInit
            CommentFailed.setTimeout(Alert.FOREVER);//GEN-END:|417-getter|1|417-postInit
            // write post-init user code here
        }//GEN-BEGIN:|417-getter|2|
        return CommentFailed;
    }
    //</editor-fold>//GEN-END:|417-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: alert2 ">//GEN-BEGIN:|418-getter|0|418-preInit
    /**
     * Returns an initiliazed instance of alert2 component.
     * @return the initialized component instance
     */
    public Alert getAlert2() {
        if (alert2 == null) {//GEN-END:|418-getter|0|418-preInit
            // write pre-init user code here
            alert2 = new Alert("Alert", "No interests on the system yet!", null, null);//GEN-BEGIN:|418-getter|1|418-postInit
            alert2.setTimeout(Alert.FOREVER);//GEN-END:|418-getter|1|418-postInit
            // write post-init user code here
        }//GEN-BEGIN:|418-getter|2|
        return alert2;
    }
    //</editor-fold>//GEN-END:|418-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: connectSocialAccount ">//GEN-BEGIN:|419-getter|0|419-preInit
    /**
     * Returns an initiliazed instance of connectSocialAccount component.
     * @return the initialized component instance
     */
    public Command getConnectSocialAccount() {
        if (connectSocialAccount == null) {//GEN-END:|419-getter|0|419-preInit
            // write pre-init user code here
            connectSocialAccount = new Command("Connect Account", Command.ITEM, 0);//GEN-LINE:|419-getter|1|419-postInit
            // write post-init user code here
        }//GEN-BEGIN:|419-getter|2|
        return connectSocialAccount;
    }
    //</editor-fold>//GEN-END:|419-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: backToMainFeed ">//GEN-BEGIN:|422-getter|0|422-preInit
    /**
     * Returns an initiliazed instance of backToMainFeed component.
     * @return the initialized component instance
     */
    public Command getBackToMainFeed() {
        if (backToMainFeed == null) {//GEN-END:|422-getter|0|422-preInit
            // write pre-init user code here
            backToMainFeed = new Command("Back", Command.BACK, 0);//GEN-LINE:|422-getter|1|422-postInit
            // write post-init user code here
        }//GEN-BEGIN:|422-getter|2|
        return backToMainFeed;
    }
    //</editor-fold>//GEN-END:|422-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: ResendPassword ">//GEN-BEGIN:|425-getter|0|425-preInit
    /**
     * Returns an initiliazed instance of ResendPassword component.
     * @return the initialized component instance
     */
    public Form getResendPassword() {
        if (ResendPassword == null) {//GEN-END:|425-getter|0|425-preInit
            // write pre-init user code here
            ResendPassword = new Form("Reset User Password", new Item[] { getTextField1() });//GEN-BEGIN:|425-getter|1|425-postInit
            ResendPassword.addCommand(getBackCommand16());
            ResendPassword.addCommand(getOkCommand12());
            ResendPassword.setCommandListener(this);//GEN-END:|425-getter|1|425-postInit
            // write post-init user code here
        }//GEN-BEGIN:|425-getter|2|
        return ResendPassword;
    }
    //</editor-fold>//GEN-END:|425-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: textField1 ">//GEN-BEGIN:|426-getter|0|426-preInit
    /**
     * Returns an initiliazed instance of textField1 component.
     * @return the initialized component instance
     */
    public TextField getTextField1() {
        if (textField1 == null) {//GEN-END:|426-getter|0|426-preInit
            // write pre-init user code here
            textField1 = new TextField("Email", null, 32, TextField.ANY);//GEN-LINE:|426-getter|1|426-postInit
            // write post-init user code here
        }//GEN-BEGIN:|426-getter|2|
        return textField1;
    }
    //</editor-fold>//GEN-END:|426-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: resendPasswordCommand ">//GEN-BEGIN:|427-getter|0|427-preInit
    /**
     * Returns an initiliazed instance of resendPasswordCommand component.
     * @return the initialized component instance
     */
    public Command getResendPasswordCommand() {
        if (resendPasswordCommand == null) {//GEN-END:|427-getter|0|427-preInit
            // write pre-init user code here
            resendPasswordCommand = new Command("Resend Password", Command.ITEM, 0);//GEN-LINE:|427-getter|1|427-postInit
            // write post-init user code here
        }//GEN-BEGIN:|427-getter|2|
        return resendPasswordCommand;
    }
    //</editor-fold>//GEN-END:|427-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: backCommand16 ">//GEN-BEGIN:|430-getter|0|430-preInit
    /**
     * Returns an initiliazed instance of backCommand16 component.
     * @return the initialized component instance
     */
    public Command getBackCommand16() {
        if (backCommand16 == null) {//GEN-END:|430-getter|0|430-preInit
            // write pre-init user code here
            backCommand16 = new Command("Back", Command.BACK, 0);//GEN-LINE:|430-getter|1|430-postInit
            // write post-init user code here
        }//GEN-BEGIN:|430-getter|2|
        return backCommand16;
    }
    //</editor-fold>//GEN-END:|430-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: okCommand12 ">//GEN-BEGIN:|433-getter|0|433-preInit
    /**
     * Returns an initiliazed instance of okCommand12 component.
     * @return the initialized component instance
     */
    public Command getOkCommand12() {
        if (okCommand12 == null) {//GEN-END:|433-getter|0|433-preInit
            // write pre-init user code here
            okCommand12 = new Command("Ok", Command.OK, 0);//GEN-LINE:|433-getter|1|433-postInit
            // write post-init user code here
        }//GEN-BEGIN:|433-getter|2|
        return okCommand12;
    }
    //</editor-fold>//GEN-END:|433-getter|2|













    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: UserDoesntExist ">//GEN-BEGIN:|438-getter|0|438-preInit
    /**
     * Returns an initiliazed instance of UserDoesntExist component.
     * @return the initialized component instance
     */
    public Alert getUserDoesntExist() {
        if (UserDoesntExist == null) {//GEN-END:|438-getter|0|438-preInit
            // write pre-init user code here
            UserDoesntExist = new Alert("Email Not Found Error", "The email you entered doesn\'t exist in our database", null, null);//GEN-BEGIN:|438-getter|1|438-postInit
            UserDoesntExist.setTimeout(Alert.FOREVER);//GEN-END:|438-getter|1|438-postInit
            // write post-init user code here
        }//GEN-BEGIN:|438-getter|2|
        return UserDoesntExist;
    }
    //</editor-fold>//GEN-END:|438-getter|2|







    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: UserExists ">//GEN-BEGIN:|440-getter|0|440-preInit
    /**
     * Returns an initiliazed instance of UserExists component.
     * @return the initialized component instance
     */
    public Alert getUserExists() {
        if (UserExists == null) {//GEN-END:|440-getter|0|440-preInit
            // write pre-init user code here
            UserExists = new Alert("User Exists", "The email you entered already exists in our database.", null, null);//GEN-BEGIN:|440-getter|1|440-postInit
            UserExists.setTimeout(Alert.FOREVER);//GEN-END:|440-getter|1|440-postInit
            // write post-init user code here
        }//GEN-BEGIN:|440-getter|2|
        return UserExists;
    }
    //</editor-fold>//GEN-END:|440-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: PasswordLessThan6Chars ">//GEN-BEGIN:|441-getter|0|441-preInit
    /**
     * Returns an initiliazed instance of PasswordLessThan6Chars component.
     * @return the initialized component instance
     */
    public Alert getPasswordLessThan6Chars() {
        if (PasswordLessThan6Chars == null) {//GEN-END:|441-getter|0|441-preInit
            // write pre-init user code here
            PasswordLessThan6Chars = new Alert("alert3", "Please enter a password larger than 6 characters.", null, null);//GEN-BEGIN:|441-getter|1|441-postInit
            PasswordLessThan6Chars.setTimeout(Alert.FOREVER);//GEN-END:|441-getter|1|441-postInit
            // write post-init user code here
        }//GEN-BEGIN:|441-getter|2|
        return PasswordLessThan6Chars;
    }
    //</editor-fold>//GEN-END:|441-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: backCommand17 ">//GEN-BEGIN:|444-getter|0|444-preInit
    /**
     * Returns an initiliazed instance of backCommand17 component.
     * @return the initialized component instance
     */
    public Command getBackCommand17() {
        if (backCommand17 == null) {//GEN-END:|444-getter|0|444-preInit
            // write pre-init user code here
            backCommand17 = new Command("Back", Command.BACK, 0);//GEN-LINE:|444-getter|1|444-postInit
            // write post-init user code here
        }//GEN-BEGIN:|444-getter|2|
        return backCommand17;
    }
    //</editor-fold>//GEN-END:|444-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: okCommand14 ">//GEN-BEGIN:|447-getter|0|447-preInit
    /**
     * Returns an initiliazed instance of okCommand14 component.
     * @return the initialized component instance
     */
    public Command getOkCommand14() {
        if (okCommand14 == null) {//GEN-END:|447-getter|0|447-preInit
            // write pre-init user code here
            okCommand14 = new Command("Ok", Command.OK, 0);//GEN-LINE:|447-getter|1|447-postInit
            // write post-init user code here
        }//GEN-BEGIN:|447-getter|2|
        return okCommand14;
    }
    //</editor-fold>//GEN-END:|447-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: find1 ">//GEN-BEGIN:|450-getter|0|450-preInit
    /**
     * Returns an initiliazed instance of find1 component.
     * @return the initialized component instance
     */
    public Command getFind1() {
        if (find1 == null) {//GEN-END:|450-getter|0|450-preInit
            // write pre-init user code here
            find1 = new Command("findfriend", Command.OK, 0);//GEN-LINE:|450-getter|1|450-postInit
            // write post-init user code here
        }//GEN-BEGIN:|450-getter|2|
        return find1;
    }
    //</editor-fold>//GEN-END:|450-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: friendlist ">//GEN-BEGIN:|442-getter|0|442-preInit
    /**
     * Returns an initiliazed instance of friendlist component.
     * @return the initialized component instance
     */
    public Form getFriendlist() {
        if (friendlist == null) {//GEN-END:|442-getter|0|442-preInit
            // write pre-init user code here
            friendlist = new Form("friendlist", new Item[] { getChoiceGroup1() });//GEN-BEGIN:|442-getter|1|442-postInit
            friendlist.addCommand(getBackCommand17());
            friendlist.addCommand(getOkCommand14());
            friendlist.addCommand(getFind1());
            friendlist.setCommandListener(this);//GEN-END:|442-getter|1|442-postInit
            // write post-init user code here
        }//GEN-BEGIN:|442-getter|2|
        return friendlist;
    }
    //</editor-fold>//GEN-END:|442-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: choiceGroup1 ">//GEN-BEGIN:|443-getter|0|443-preInit
    /**
     * Returns an initiliazed instance of choiceGroup1 component.
     * @return the initialized component instance
     */
    public ChoiceGroup getChoiceGroup1() {
        if (choiceGroup1 == null) {//GEN-END:|443-getter|0|443-preInit
            // write pre-init user code here
            choiceGroup1 = new ChoiceGroup("choosefriend", Choice.EXCLUSIVE);//GEN-LINE:|443-getter|1|443-postInit
            // write post-init user code here
        }//GEN-BEGIN:|443-getter|2|
        return choiceGroup1;
    }
    //</editor-fold>//GEN-END:|443-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Method: emailformat ">//GEN-BEGIN:|454-if|0|454-preIf
    /**
     * Performs an action assigned to the emailformat if-point.
     */
    public void emailformat() {//GEN-END:|454-if|0|454-preIf
        // enter pre-if user code here
      RE form = new RE("^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)"+ "*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");

      boolean format =  form.match(textField2.getString());

      if (format) {//GEN-LINE:|454-if|1|455-preAction
            // write pre-action user code here
            if(checkServerConn())
                {
                //save what the user enters and send it via send data
           String ss="friend:"+getChoiceGroup1()+"email:"+getTextField2()+" message:"+getTextField3();
         sendData("http://" + SERVER_IP + ":" + PORT+"/stories/recommend_story?sid="+currentStoryString+"&uid="+userID, ss);

            }else{switchDisplayable(getAlert(), getRecommend());
                 }
//GEN-LINE:|454-if|2|455-postAction
            // write post-action user code here
      } else {//GEN-LINE:|454-if|3|456-preAction
            // write pre-action user code here
          switchDisplayable(null, getEmailisnotincorrectformat());//GEN-LINE:|454-if|4|456-postAction
            // write post-action user code here
      }//GEN-LINE:|454-if|5|454-postIf
        // enter post-if user code here
    }//GEN-BEGIN:|454-if|6|
    //</editor-fold>//GEN-END:|454-if|6|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: emailisnotincorrectformat ">//GEN-BEGIN:|458-getter|0|458-preInit
    /**
     * Returns an initiliazed instance of emailisnotincorrectformat component.
     * @return the initialized component instance
     */
    public Alert getEmailisnotincorrectformat() {
        if (emailisnotincorrectformat == null) {//GEN-END:|458-getter|0|458-preInit
            // write pre-init user code here
            emailisnotincorrectformat = new Alert("alert3", "emailisnotincorrectformat(xxxx@example.com)", null, null);//GEN-BEGIN:|458-getter|1|458-postInit
            emailisnotincorrectformat.setTimeout(Alert.FOREVER);//GEN-END:|458-getter|1|458-postInit
            // write post-init user code here
        }//GEN-BEGIN:|458-getter|2|
        return emailisnotincorrectformat;
    }
    //</editor-fold>//GEN-END:|458-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: editInfo ">//GEN-BEGIN:|464-getter|0|464-preInit
    /**
     * Returns an initiliazed instance of editInfo component.
     * @return the initialized component instance
     */
    public Command getEditInfo() {
        if (editInfo == null) {//GEN-END:|464-getter|0|464-preInit
            // write pre-init user code here
            editInfo = new Command("Edit Info", Command.ITEM, 0);//GEN-LINE:|464-getter|1|464-postInit
            // write post-init user code here
        }//GEN-BEGIN:|464-getter|2|
        return editInfo;
    }
    //</editor-fold>//GEN-END:|464-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: savedProfile ">//GEN-BEGIN:|461-getter|0|461-preInit
    /**
     * Returns an initiliazed instance of savedProfile component.
     * @return the initialized component instance
     */
    public Alert getSavedProfile() {
        if (savedProfile == null) {//GEN-END:|461-getter|0|461-preInit
            // write pre-init user code here
            savedProfile = new Alert("alert3");//GEN-BEGIN:|461-getter|1|461-postInit
            savedProfile.setTimeout(Alert.FOREVER);//GEN-END:|461-getter|1|461-postInit
            // write post-init user code here
        }//GEN-BEGIN:|461-getter|2|
        return savedProfile;
    }
    //</editor-fold>//GEN-END:|461-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: errorProfile ">//GEN-BEGIN:|462-getter|0|462-preInit
    /**
     * Returns an initiliazed instance of errorProfile component.
     * @return the initialized component instance
     */
    public Alert getErrorProfile() {
        if (errorProfile == null) {//GEN-END:|462-getter|0|462-preInit
            // write pre-init user code here
            errorProfile = new Alert("alert4");//GEN-BEGIN:|462-getter|1|462-postInit
            errorProfile.setTimeout(Alert.FOREVER);//GEN-END:|462-getter|1|462-postInit
            // write post-init user code here
        }//GEN-BEGIN:|462-getter|2|
        return errorProfile;
    }
    //</editor-fold>//GEN-END:|462-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: miniPass ">//GEN-BEGIN:|463-getter|0|463-preInit
    /**
     * Returns an initiliazed instance of miniPass component.
     * @return the initialized component instance
     */
    public Alert getMiniPass() {
        if (miniPass == null) {//GEN-END:|463-getter|0|463-preInit
            // write pre-init user code here
            miniPass = new Alert("Password error", "Passwords are not valid. ", null, null);//GEN-BEGIN:|463-getter|1|463-postInit
            miniPass.setTimeout(Alert.FOREVER);//GEN-END:|463-getter|1|463-postInit
            // write post-init user code here
        }//GEN-BEGIN:|463-getter|2|
        return miniPass;
    }
    //</editor-fold>//GEN-END:|463-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: PleaseTryAgain ">//GEN-BEGIN:|470-getter|0|470-preInit
    /**
     * Returns an initiliazed instance of PleaseTryAgain component.
     * @return the initialized component instance
     */
    public Alert getPleaseTryAgain() {
        if (PleaseTryAgain == null) {//GEN-END:|470-getter|0|470-preInit
            // write pre-init user code here
            PleaseTryAgain = new Alert("alert3");//GEN-BEGIN:|470-getter|1|470-postInit
            PleaseTryAgain.setTimeout(Alert.FOREVER);//GEN-END:|470-getter|1|470-postInit
            // write post-init user code here
        }//GEN-BEGIN:|470-getter|2|
        return PleaseTryAgain;
    }
    //</editor-fold>//GEN-END:|470-getter|2|







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
    should return the json file of list of stories according to the client interests.*/ {
        HttpConnection httpConn = null;
        String url = "http://192.168.26.136:3000/users/stories?id=1";

        InputStream is = null;
        OutputStream os = null;

        try {
            // Open an HTTP Connection object
            httpConn = (HttpConnection) Connector.open(url);

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
                while ((chr = is.read()) != -1) {
                    sb.append((char) chr);
                }

                json = sb.toString();
                System.out.println(sb.toString());
            } else {
                System.out.println("Error in opening HTTP Connection. Error#" + respCode);
            }
        } catch (Exception e) {
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException ex) {
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
    }

    /**
     * This method triggers the first phase of the authentication between 
     * twitter and our system. It triggers the authentication from the 
     * twitter_request_controller.rb from the server and gets in return an 
     * authorization url. For more info about the authorization process, check 
     * twitter_request_controller.rb
     * @param serverIP: The IP of the server 
     * @param port: The port of the server
     * @return a string which corresponds to the authorization URL which the 
     * user needs to go to to authorize our app.
     */
    public String getTwitterAuthURL(String serverIP, 
            int port) throws InvalidUserIdException{
        if (userID == 0)
            throw new InvalidUserIdException();
        String sendURL = "/authenticate/" + userID + "/get_twitter_url";
        return getHttpResponse(serverIP, port, sendURL);
    }

    /**
     * This method checks if the user with userID has a twitter account or not. 
     * This is done by calling the corresponding method (exists?) on the server
     * backend.
     * @param serverIP
     * @param port
     * @param userID
     * @return boolean if the user already have a twitter account
     */
    public boolean isTwitterAccountExists(String serverIP, 
            int port, int userID) throws InvalidUserIdException{
        String sendURL = "/twitter/" + userID + "/exists";
        if (userID == 0)
        {
            throw new InvalidUserIdException();
        }
        String resp = getHttpResponse(serverIP, port, sendURL);
        return resp.equals("true");
    }

    /**
     * This method tries to create a new twitter account and hooks it with the
     * user of id userID. This is done by calling the corresponding method in 
     * twitter_requests_controller.rb
     * @param serverIP
     * @param port
     * @param userID
     * @return boolean if the creation was successfull
     */
    public boolean createTwitterAccount(String serverIP, int port, 
            int userID) throws InvalidUserIdException {
        if (userID == 0)
            throw new InvalidUserIdException();
            
        String sendURL = "/authenticate/" + userID + "/new_twitter_account";
        String resp = getHttpResponse(serverIP, port, sendURL);
//        System.out.println("resp is " + resp + " " +
//                resp.startsWith("n/a"));
        if (resp.toLowerCase().startsWith("n/a")) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * This is a helper method in which it takes a crertain IP, port and URL
     * then makes the actual url by appending IP, port and url into one string.
     * Then it sends the http request and open a thread to take the response 
     * from the server. if problems occurred, a string 'n/a' is returned
     * @param serverIP
     * @param port
     * @param url
     * @return String which is the response of the server or 'n/a' if problems
     * occurred
     */
    public String getHttpResponse(String serverIP, int port, String url){
        url = "http://" + serverIP + ":" + port + url;
        System.out.println("Getting http response for " + url);
        InputStream is = null;
        OutputStream os = null;
        HttpConnection httpConn = null;
        
        if (!checkInternetConn()){
            switchDisplayable(getInternetError(), getLoginScreen());
        }
        if (!checkServerConn()){
            switchDisplayable(getServerError(), getLoginScreen());
        }

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

                System.out.println("Response is " + sb.toString());
                return sb.toString();
            } else {
                System.out.println("Error in opening HTTP Connection. Error#" 
                        + respCode);
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

//    public static String getInterests() /*this method should intiate the connection between the server and the mobile client which
//    should return the json file of list of stories according to the client interests.*/ {
//
//        HttpConnection httpConn = null;
//        String url = "http://192.168.26.148:3000/user_add_interests?id=1&format=json";
//
//        InputStream is = null;
//        OutputStream os = null;
//
//        try {
//            // Open an HTTP Connection object
//            httpConn = (HttpConnection) Connector.open(url);
//
//            // Setup HTTP Request
//            httpConn.setRequestMethod(HttpConnection.GET);
//            httpConn.setRequestProperty("User-Agent",
//                    "Profile/MIDP-1.0 Confirguration/CLDC-1.0");
//
//
//            int respCode = httpConn.getResponseCode();
//            if (respCode == httpConn.HTTP_OK) {
//                StringBuffer sb = new StringBuffer();
//                os = httpConn.openOutputStream();
//                is = httpConn.openDataInputStream();
//                int chr;
//                while ((chr = is.read()) != -1) {
//                    sb.append((char) chr);
//                }
//
//                String x = sb.toString();
//
//                System.out.println(sb.toString());
//                return x;
//            } else {
//                System.out.println("Error in opening HTTP Connection. Error#" + respCode);
//            }
//        } catch (Exception e) {
//        } finally {
//            if (is != null) {
//                try {
//                    is.close();
//                } catch (IOException ex) {
//                    ex.printStackTrace();
//                }
//            }
//            if (os != null) {
//                try {
//                    os.close();
//                } catch (IOException ex) {
//                    ex.printStackTrace();
//                }
//            }
//            if (httpConn != null) {
//                try {
//                    httpConn.close();
//                } catch (IOException ex) {
//                    ex.printStackTrace();
//                }
//            }
//
//        }
//        return "";
//    }
//    public void parseJsonInterest(String x) // user to parse json interests
//    {
//        String interest = "";
//        for (int i = 0; i < x.length(); i++) {
//            interest = "";
//            if (x.charAt(i) == ':') {
//                i = i + 2;
//                while (x.charAt(i) != '"') {
//                    interest = interest + x.charAt(i);
//                    i++;
//
//                }
//                list.append(interest, null);
//                System.out.println(interest);
//                //list.append(interest, null);
//            }
//        }
//    }
    // view one of the comments of a certain story
//    public void viewCommentOne(String id,String user,String content,String ups,String downs,String date){
//      //  getCommentOne().
//        commentItem com = new commentItem(id, user, content, ups, downs, date,true);
//       
//        getCommentOne().append(com);
//         switchDisplayable(null, getCommentOne());
//    }
    // parse comments list comming from server
    public void parseComments(String storyID) {
        HttpConnection httpConn = null;
        String url = "http://"+SERVER_IP+":3000/stories/" + storyID + "/comments";
        // String urltest = "http://192.168.1.3:3000/comments/8";
        String jsonS = getData(url); //get json data
     //   System.out.println(jsonS);
        commentItem[] comments; //commentItem array to be added to CommentsMany form
        //   CommentsMany.append(new commentItem(json,this));
        //   switchDisplayable(null, CommentsMany);
        // sendData("http://192.168.1.3:3000/stories/:id/comments/downc", "{\"user_id\":\"3\",\"comment_id\":\"1\"}");
        try {
            JSONObject json = new JSONObject(jsonS);  // create json object from string

            JSONArray jsonArray = json.getJSONArray("Comments");  // get array
            int total = jsonArray.length();

            comments = new commentItem[total];  // init commentItem array
            for (int i = 0; i < total; i++) {
                String commJson = jsonArray.getString(i); // get json representation of comment
                comments[i] = new commentItem(commJson, this); // create a commentItem using it
                CommentsMany.append(comments[i]); // append commentItem tp form
            }
            switchDisplayable(null, CommentsMany); 

        } catch (JSONException ex) {
            ex.printStackTrace();
        }



    }
    
    public void UserInterestsJson() { // parse user interests after confirmation to json array to send to server still needs url modification
                String dataToBeSend = "{\"id\": " +userID+ " , \"interests\": [";
       for(int i=0; i<user.size(); i++) {
           
           dataToBeSend = dataToBeSend + "\"" +((String)user.elementAt(i)) + "\" ," ;
           
       }
       dataToBeSend = dataToBeSend.substring(0, (dataToBeSend.length()-1)) ;
       dataToBeSend = dataToBeSend + "]}"; 
       //dataToBeSend = "ayhaga 3alasajfiosdfsdfs";
       System.out.println(dataToBeSend);
       
       if(checkInternetConn() && checkServerConn() ){
            try {
        //Change IP accordingly
        httpConn = (HttpConnection) Connector.open("http://"+SERVER_IP+":"+PORT+"/user_add_interests.json");
        //Request method has to be POST
        httpConn.setRequestMethod(HttpConnection.POST);
        httpConn.setRequestProperty("User-Agent",
                "Profile/MIDP-1.0 Confirguration/CLDC-1.0");
        httpConn.setRequestProperty("Accept_Language", "en-US");
        //Content-Type is must to pass parameters in POST Request must be application/json
        httpConn.setRequestProperty("Content-Type", "application/json");
        // JSON String that you will send containing the attributes needed for sign up. 
        //String dataToBeSend = "{\"created_at\":\"nil\",\"email\":\"menisy@abfcge.com\",\"name\":\"menisy\",\"updated_at\":\"nil\"}";
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
       else {
           System.out.println("no connection");
       }
   }
   
 public static Vector allInterests(String g) {  //parse all interests into interests vector
String a = g;
int firstName = 0;
int first = 0;
Vector interests = new Vector();
while (a.indexOf("name\":") != -1) {
firstName = a.indexOf("name\":");
firstName += 7;
first = a.indexOf("updated_at");
interests.addElement(a.substring(firstName, first - 3));
a = a.substring(first + 4, a.length());
}
return interests;
}
   public static Vector userInterests(String g) { //parse user interests into user vector
Vector user = new Vector();
//String u = g.substring(1, json.indexOf("{"));
                String u = g;
while (u.indexOf(",") != -1) {
int f = u.indexOf("\"");
int s = u.indexOf(",");
user.addElement(u.substring(f + 1, s - 1));
u = u.substring(s + 1, u.length());
}    
                return user;
}
   public void help() {  //fill interets inside toggle form
       //json1 string is the Json String from server with same format just for testing 
       System.out.println("help");
       HttpConnection httpConn = null;
      String url = "http://"+SERVER_IP+":"+PORT+"/users/"+userID+"/toggle.json" ;  

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
        while ((chr = is.read()) != -1) {
          sb.append((char) chr); }
          json1 = sb.toString();
          System.out.println(json1);
          System.out.println(json1.length());
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
        
        if(json1 != null && json1.length() > 3) {
       interests = allInterests(json1);
       user = userInterests(json1.substring(1, json1.indexOf("{")));
       for(int i = 0; i <user.size(); i++) {
           System.out.println(user.elementAt(i));
       }
       
         for(int j = 0; j <interests.size(); j++) {
      getChoiceGroup().append((String)interests.elementAt(j), null);
  }
  if(user.size() > 0) {
  for(int i = 0; i <user.size(); i++) {
      for(int k = 0; k <interests.size(); k++) {
          if(((String)user.elementAt(i)).equalsIgnoreCase(getChoiceGroup().getString(k)))
              getChoiceGroup().setSelectedIndex(k, true); }
  }
  }
        }
   }
   public void help1() { // confirmation form helper adds content
           
       if(user.size() > 0) {
                interestConfirm.append("Your Selected Interets :");
                interestConfirm.append("\n \n \n");
                for(int i = 0; i<user.size(); i++) {
                    interestConfirm.append(((String) user.elementAt(i)));
                    interestConfirm.append("\n \n \n");
                }
                interestConfirm.append("to confrim press OK \n");
                }
            else {
                interestConfirm.append("You Didn't Select Any Interest !!");
                interestConfirm.append("to confrim press OK \n");
            }
    
   }

    private static String[] split(String original, String separator) {  //string split method
        Vector nodes = new Vector();
        int index = original.indexOf(separator);
        while (index >= 0) {
            nodes.addElement(original.substring(0, index));
            original = original.substring(index + separator.length());
            index = original.indexOf(separator);
        }
        nodes.addElement(original);

        String[] result = new String[nodes.size()];
        if (nodes.size() > 0) {
            for (int loop = 0; loop < nodes.size(); loop++) {
                result[loop] = (String) nodes.elementAt(loop);
            }

        }
        return result;
    }

    public Image loadImage(String url) throws IOException { //loads image from internet given image url
        HttpConnection hpc = null;
        DataInputStream dis = null;
        try {
            hpc = (HttpConnection) Connector.open(url);
            int length = (int) hpc.getLength();
            byte[] data = new byte[length];
            dis = new DataInputStream(hpc.openInputStream());
            dis.readFully(data);
            return Image.createImage(data, 0, data.length);
        } finally {
            if (hpc != null) {
                hpc.close();
            }
            if (dis != null) {
                dis.close();
            }
        }
    }

    public void filter(String interest){ // takes a certain interest and filters the main feed on it
       
        for (int i = 0; i < MainFeed.size(); i++) {
            if (MainFeed.get(i) instanceof storyItem) {
                storyItem temp = (storyItem) MainFeed.get(i);
                if (temp.storyCategory.getText() != interest) {
                    MainFeed.delete(i);
                }
            }
        }
    }
   
    //the getstoryshare method get the json file from the back end of
    //share_story_social_network method and change it to array of string
     public String[] getstoryshare() throws IOException {
         String[] share = null;
           if(checkInternetConn())
                {
        String like_url = "http://" + SERVER_IP + ":" + PORT+"/stories/share_story_social_network?sid="+currentStoryID+"&uid="+userID+"&format=json";
        String jsonS1 = getData(like_url);
        
        try {
            JSONObject json = new JSONObject(jsonS1);

            JSONArray jsonArray = json.getJSONArray("stories");
            int total = jsonArray.length();

            share = new String[total];
            for (int i = 0; i < total; i++) {
                String commJson = jsonArray.getString(i);
                share[i] = new String(commJson);
            }
        } catch (JSONException ex) {
            ex.printStackTrace();
        }
        
                }else{
                switchDisplayable(getAlert(), getReadMore());
           }
           return share;
    }
     
     //the parsestoryshare method is to parse the array and return the result stored in it
     
     public boolean parsestoryshare(String[] share) {
        String state = "";
        boolean sss = false;
        for (int j = 0; j < share.length; j++) {
            for (int i = 0; i < share[j].length(); i++) {
                state = "";
                if (share[j].charAt(i) == '"' || share[j].charAt(i) == '{' || share[j].charAt(i) == '}' || share[j].charAt(i) == ':') {
                } else {
                    state = state + share[j].charAt(i);
                }
 }
        }
        if (state.startsWith("true")){
            sss= true;
        }
        else {
            if (state.startsWith("false"))
            sss= false;
    }
        return sss;
     }
 //the getfriendslike method get the json file from the back end of
    //view_friends_like method and change it to array of string
    public String[] getfriendslike() throws IOException {
        String[] friendlist = null;
           if(checkInternetConn())
                {
        
        String like_url =  "http://" + SERVER_IP + ":" + PORT+"/stories/view_friends_like?sid="+currentStoryID+"&uid="+userID+"&format=json";

        String jsonS1 = getData(like_url);
        try {
            JSONObject json = new JSONObject(jsonS1);

            JSONArray jsonArray = json.getJSONArray("stories");
            int total = jsonArray.length();

            friendlist = new String[total];
            for (int i = 0; i < total; i++) {
                String commJson = jsonArray.getString(i);
                friendlist[i] = new String(commJson);
            }
        } catch (JSONException ex) {
            ex.printStackTrace();
        }
        
                }
           else{switchDisplayable(getAlert(), getReadMore());
               
           }
           return friendlist;
    }
 //the getfriendsdislike method get the json file from the back end of
    //view_friends_dislike method and change it to array of string
    public String[] getfriendsdislike() throws IOException {
        
        String[] friendlist = null;
           if(checkInternetConn())
                {
        String dislike_url =  "http://" + SERVER_IP + ":" + PORT+"/stories/view_friends_dislike?sid="+currentStoryID+"&uid="+userID+"&format=json";
        String jsonS2 = getData(dislike_url);
        
        try {
            JSONObject json = new JSONObject(jsonS2);

            JSONArray jsonArray = json.getJSONArray("stories");
            int total = jsonArray.length();

            friendlist = new String[total];
            for (int i = 0; i < total; i++) {
                String commJson = jsonArray.getString(i);
                friendlist[i] = new String(commJson);

            }

        } catch (JSONException ex) {
            ex.printStackTrace();
        }
}
           else{switchDisplayable(getAlert(), getReadMore());
               
           }
           return friendlist;
    }
 //the getfriendrecstory method get the json file from the back end of
    //recommend_story method and change it to array of string
    public String[] getfriendrecstory() throws IOException {

String[] friendlist = null;
           if(checkInternetConn())
                {
        String mess_url =  "http://" + SERVER_IP + ":" + PORT+"/stories/get_friends_email?uid="+userID+"&format=json";

        String jsonS = getData(mess_url);

        try {
            JSONObject json = new JSONObject(jsonS);

            JSONArray jsonArray = json.getJSONArray("stories");
            int total = jsonArray.length();

            friendlist = new String[total];
            for (int i = 0; i < total; i++) {
                String commJson = jsonArray.getString(i);
                friendlist[i] = new String(commJson);

            }

        } catch (JSONException ex) {
            ex.printStackTrace();
        }

        }
           else{switchDisplayable(getAlert(), getReadMore());
               
           }
           return friendlist;
    }
//parseJsonfriends method is to parse the array and insert each friend in the list of friends
    public void parseJsonfriends(String[] choosefriendlist) // user to parse json frindlist
    {
        
        for (int j = 0; j < choosefriendlist.length; j++) {
            String friend = "";
            for (int i = 0; i < choosefriendlist[j].length(); i++) {
                if (choosefriendlist[j].charAt(i) == '"' || choosefriendlist[j].charAt(i) == '{' || choosefriendlist[j].charAt(i) == '}' || choosefriendlist[j].charAt(i) == ':') {
                } else {
                    friend = friend + choosefriendlist[j].charAt(i);
                }


            }
           
            choiceGroup1.append(friend, null);
        }
    }
//parseJsonfriends method is to parse the array and insert each friend in the list of friends who liked this story
    public void parseJsonfriendsliked(String[] choosefriend) // user to parse json frindlist
    {
       
        for (int j = 0; j < choosefriend.length; j++) {
             String friend = "";
            for (int i = 0; i < choosefriend[j].length(); i++) {
                if (choosefriend[j].charAt(i) == '"' || choosefriend[j].charAt(i) == '{' || choosefriend[j].charAt(i) == '}' || choosefriend[j].charAt(i) == ':') {
                } else {
                    friend = friend + choosefriend[j].charAt(i);
                }


            }
            liked.append(friend, null);
        }
    }
//parseJsonfriends method is to parse the array and insert each friend in the list of friends who disliked this story
    public void parseJsonfriendsdisliked(String[] choosefriend) // user to parse json frindlist
    {
        
        for (int j = 0; j < choosefriend.length; j++) {
            String friend = "";
            for (int i = 0; i < choosefriend[j].length(); i++) {
                if (choosefriend[j].charAt(i) == '"' || choosefriend[j].charAt(i) == '{' || choosefriend[j].charAt(i) == '}' || choosefriend[j].charAt(i) == ':') {
                } else {
                    friend = friend + choosefriend[j].charAt(i);
                }


            }
            disliked.append(friend, null);
        }
    }
//insertfriendsintolist method is to call the method that generates the friends that will be inserted into the lists
    public void insertfriendsintolist(int n) throws IOException {
        String[] x = getfriendrecstory();
        String[] y = getfriendslike();
        String[] z = getfriendsdislike();

        if (n==1) {
            parseJsonfriends(x);
        } else {
            if (n==2) {
                parseJsonfriendsliked(x);
            } else {
                if (n==3) {
                    parseJsonfriendsdisliked(x);
                }
            }

        }


    }
    

    public void parseRequests(String userID) {
        HttpConnection httpConn = null;
        String url = "http://192.168.1.3:3000/friend_requests/" + this.userID;
        // String urltest = "http://192.168.1.3:3000/comments/8";
        String jsonS = getData(url);
        System.out.println(jsonS);
        reqItem[] reqs;
        //   CommentsMany.append(new commentItem(json,this));
        //   switchDisplayable(null, CommentsMany);
        // sendData("http://192.168.1.3:3000/stories/:id/comments/downc", "{\"user_id\":\"3\",\"comment_id\":\"1\"}");
        try {
            JSONObject json = new JSONObject(jsonS);

            JSONArray jsonArray = json.getJSONArray("Friend_requests");
            int total = jsonArray.length();

            reqs = new reqItem[total];
            for (int i = 0; i < total; i++) {
                String commJson = jsonArray.getString(i);
                reqs[i] = new reqItem(commJson, this);
                CommentsMany.append(reqs[i]);
            }
            switchDisplayable(null, CommentsMany);

        } catch (JSONException ex) {
            ex.printStackTrace();
        }
    }

    void viewReq(int req_id, String frName, String frEmail) {
        reqItem com = new reqItem(req_id, frName, frEmail, true);
        getOneReq().append(com);
        switchDisplayable(null, getOneReq());
    }

    public void test() { //get data from server and passes it to be parsed
        String x = getDataServer("http://"+SERVER_IP+":"+PORT+"/user_add_interests/interests?id="+userID+"&format=json");
        String y;
        y = x.substring(2, x.length() - 2);
        parseJsonfile(y);
    }

    public void parseJsonfile(String x) { // parses json string of interests from server

        String interest = "";


        for (int i = 0; i < x.length(); i++) {
            if (x.charAt(i) == '"' || x.charAt(i) == ',') {
                interest = interest + " ";
                continue;
            }

            interest = interest + x.charAt(i);
        }
        String[] z = split(interest);
        int k = 0;
        while (k < z.length) {
            list.append(z[k], null);
            k++;
        }

    }

    private String[] split(String original) { // split method 
        Vector nodes = new Vector();
        String separator = "   ";
        System.out.println("split start...................");
        // Parse nodes into vector
        int index = original.indexOf(separator);
        while (index >= 0) {
            nodes.addElement(original.substring(0, index));
            original = original.substring(index + separator.length());
            index = original.indexOf(separator);
        }
        // Get the last node
        nodes.addElement(original);

        // Create splitted string array
        String[] result = new String[nodes.size()];
        if (nodes.size() > 0) {
            for (int loop = 0; loop < nodes.size(); loop++) {
                result[loop] = (String) nodes.elementAt(loop);
                System.out.println(result[loop]);
            }

        }

        return result;
    }

    public static String getDataServer(String link) /*
     * this method should intiate the connection between the server and the
     * mobile client and returns a String with the Returned Data
     */ {

        HttpConnection httpConn = null;
        String url = link;

        InputStream is = null;
        OutputStream os = null;

        try {
            // Open an HTTP Connection object
            httpConn = (HttpConnection) Connector.open(url);

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
                while ((chr = is.read()) != -1) {
                    sb.append((char) chr);
                }

                String x = sb.toString();

                System.out.println(sb.toString());
                return x;
            } else {
                System.out.println("Error in opening HTTP Connection. Error#" + respCode);
            }
        } catch (Exception e) {
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
        return "";

    }

    public void pingInternet() {
        /*
         * This Method Pings http://www.google.com to check if the user has
         * access to the internet, if the ping was successful it sets the
         * value of internetConn to true, otherwise it sets the value of
         * internetConn to false
         */
        // Creates the HttpConnection, InputStream, OutputStream
        // and StringBuffer to be used in the process
        HttpConnection httpCheckConn = null;
        OutputStream checkConnOS = null;
        InputStream checkConnIS = null;
        int ch;
        StringBuffer checkConnBuffer = new StringBuffer();
        try {
            // sets the connection to http://www.google.com
            httpCheckConn = (HttpConnection) Connector.open(checkConnURL);

            // Set the request method and headers
            httpCheckConn.setRequestMethod(HttpConnection.POST);
            httpCheckConn.setRequestProperty(
                    "If-Modified-Since", "7 Sep 2005 19:43:31 GMT");

            httpCheckConn.setRequestProperty(
                    "User-Agent", "Profile/MIDP-1.0 Configuration/CLDC-1.0");

            httpCheckConn.setRequestProperty("Content-Language", "en-US");
            // Getting the output stream may flush the headers
            checkConnOS = httpCheckConn.openOutputStream();
            checkConnOS.write("Ping".getBytes());
            checkConnOS.flush();

            checkConnIS = httpCheckConn.openInputStream();
            while ((ch = checkConnIS.read()) != -1) {
                checkConnBuffer.append((char) ch);
            }
            System.out.println("connected");
            // if ping success, sets internetConn to true
            internetConn = true;
        } catch (IOException e) {
            // if ping failed, sets internetConn to false
            System.out.println("error");
            internetConn = false;
        }
        finally {
               try{
                   if(checkConnIS != null)
                checkConnIS.close();
                   if(checkConnOS != null)
                checkConnOS.close();
                   if(httpCheckConn != null)
                httpCheckConn.close();
                   
               }
               catch(Exception e){
                   e.printStackTrace();
               }
            }
    }

    public void pingServer() {
        /*
         * This Method Pings the server to check if the user has
         * access to the internet, if the ping was successful it sets the
         * value of serverConn to true, otherwise it sets the value of
         * serverConn to false
         */
        // Creates the HttpConnection, InputStream, OutputStream
        // and StringBuffer to be used in the process     
        HttpConnection httpCheckConn = null;
        OutputStream checkConnOS = null;
        InputStream checkConnIS = null;
        int ch;
        StringBuffer checkConnBuffer = new StringBuffer();
        try {
            // sets connection to the server address which is specifed in
            // in the String serverURL
            httpCheckConn = (HttpConnection) Connector.open("http://"+SERVER_IP+":3000");

            // Set the request method and headers
            httpCheckConn.setRequestMethod(HttpConnection.POST);
            httpCheckConn.setRequestProperty(
                    "If-Modified-Since", "7 Sep 2005 19:43:31 GMT");

            httpCheckConn.setRequestProperty(
                    "User-Agent", "Profile/MIDP-1.0 Configuration/CLDC-1.0");

            httpCheckConn.setRequestProperty("Content-Language", "en-US");
            // Getting the output stream may flush the headers
            checkConnOS = httpCheckConn.openOutputStream();
            checkConnOS.write("Ping".getBytes());
            checkConnOS.flush();

            checkConnIS = httpCheckConn.openInputStream();
            while ((ch = checkConnIS.read()) != -1) {
                checkConnBuffer.append((char) ch);
            }
            System.out.println("connected");
            //if ping success, sets server connection to true    
            serverConn = true;
        } catch (IOException e) {
            System.out.println("error");
            //if ping failed, sets server connection to false 
            serverConn = false;
        }
        finally {
               try{
                   if(checkConnIS != null)
                checkConnIS.close();
                   if(checkConnOS != null)
                checkConnOS.close();
                   if(httpCheckConn != null)
                httpCheckConn.close();
                   
               }
               catch(Exception e){
                   e.printStackTrace();
               }
            }
    }

    public boolean checkInternetConn() {
        /*
         * This method checks if the device is connected to the internet,
         * if it is connected it returns true, otherwise return false
         */
        pingInternet();
        if (internetConn) {
            return true;
        } else {
            switchDisplayable(getInternetError(), displayable);
            return false;
        }

    }

    public boolean checkServerConn() {
        /*
         * This method checks if the device is connected to the server,
         * if it is connected it returns true, otherwise return false
         */
        pingServer();
        if (serverConn) {
            return true;
        } else {
            switchDisplayable(getServerError(), displayable);
            return false;
        }

    }
    
        public void checkConn(){
        
                        httpCheckConn1 = null;
      String urlR = "http://"+SERVER_IP+":"+PORT+"/check.json";  

    try {
        

      // Open an HTTP Connection object
      httpCheckConn1 = (HttpConnection)Connector.open(urlR);

      // Setup HTTP Request
      httpCheckConn1.setRequestMethod(HttpConnection.GET);
      System.out.println("1");
      httpCheckConn1.setRequestProperty("User-Agent",
        "Profile/MIDP-1.0 Confirguration/CLDC-1.0");

      System.out.println("2");
      //System.out.println(httpCheckConn1.);
      int respCode = httpCheckConn1.getResponseCode();
            System.out.println("3");
      if(respCode == HttpConnection.HTTP_OK){
          serverConn = true;
          internetConn = true;
      }else {
          internetConn = false;
          serverConn =false;
      }
        }catch (Exception e){
            
            serverConn = false;
            internetConn = false;
            e.printStackTrace();
        }finally {
        
      if(httpCheckConn1 != null)
            try {
                httpCheckConn1.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
     
    }
        
    }
     
     public boolean checkAllConn(){
         checkConn();
         if(!serverConn){
             switchDisplayable(getServerError(),displayable);
             return false;
         }else return true;
     }
    
}

class InvalidUserIdException extends Exception{
    public InvalidUserIdException(){
        super("An Invalid user ID was found");
    }
}