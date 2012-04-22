/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hello;

import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import javax.microedition.io.Connector;
import javax.microedition.io.HttpConnection;

/**
 * @author Essam Hafez
 */
public class HelloMIDlet extends MIDlet implements ItemStateListener, CommandListener{
    String url;
    static int countInsertion = 0;
    private boolean midletPaused = false;
    String json;
//<editor-fold defaultstate="collapsed" desc=" Generated Fields ">//GEN-BEGIN:|fields|0|
    private Command exitCommand;
    private Command viewComments;
    private Command Comment1;
    private Command backCommandV;
    private Command backCommand1;
    private Command okCommand;
    private Command comment2;
    private Command backCommand2;
    private Command cancelCommand;
    private Command verifyCommand;
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
    private Form form;
    private StringItem stringItem;
    private Form Story;
    private Form MainFeed;
    private Form Comment;
    private TextField textField;
    private TextBox textBox;
    private Form profile;
    private TextField textField1;
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
    private Form Verification;
    private TextField vTF;
    private StringItem stringItem1;
//</editor-fold>//GEN-END:|fields|0|

  
    /**
     * The HelloMIDlet constructor.
     */
    public HelloMIDlet() {
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
    
    public void parseJsonStory(){
    url = "id: 1 title:\"test test test\" body:dh test tany ! : rank 5 image : https://www.google.com.eg/logos/2012/earthday12-sr.png category : arts id: 056 title:\"title title\" body:isA  : rank 3 image : /2.jpg category : sports ";
        String temp = url;
        String title,body,rank,image,category;
        try{
        if(temp.length() !=0){ 
            while(temp.indexOf("id")>=0) { //loop till no more id written
                int idOccur = temp.indexOf("id");    //get first occurance of it
                int titleOccur = temp.indexOf("title");
                int bodyOccur = temp.indexOf("body");
                int rankOccur = temp.indexOf("rank");
                int imageOccur = temp.indexOf("image");
                int categoryOccur = temp.indexOf("category");
                titleOccur +=5;     // add 5 to title to get the rest as the title
                title = temp.substring(titleOccur, bodyOccur-1); //title = after title to index of body-1
                bodyOccur+=4;
                body = temp.substring(bodyOccur, rankOccur-1);
                rankOccur+=4;
                rank = temp.substring(rankOccur, imageOccur-1);
                imageOccur+=5;
                image = temp.substring(imageOccur, categoryOccur-1);
                categoryOccur +=8;
                if(temp.indexOf("id",categoryOccur)!=-1){
                    category = temp.substring(categoryOccur,temp.indexOf("id", categoryOccur));
                    String x = temp.substring(temp.indexOf("id",categoryOccur));
                    temp = x;
                }else{
                    category = temp.substring(categoryOccur);
                    String x = temp.substring(categoryOccur);
                    temp = x;
                }
                
                title = removeExtras(title);
                body = removeExtras(body);
                rank = removeExtras(rank);
                rank = removeSpaces(rank);
                image = removeImageExtras(image);
                image = image.trim();
                category = removeExtras(category);
                storyItem a = new storyItem(image,title,body,Integer.parseInt(rank),category);
                MainFeed.insert(countInsertion++, a);
            }
        }
        }catch(Exception e){
            System.out.println("Exception happened here");
            e.printStackTrace();
        }
    }

    public void helpMainFeed() {
        //addStory("/x.png", "new story222", "AaaAaAASa",3);
        parseJsonStory();
        /*for (int i = 0; i < 5; i++) {
            storyItem a = new storyItem("/x.png", "new Story", "", i + 1, "sportssssssssssssssssssss");//category 25 chars MAX
            
            MainFeed.insert(countInsertion++, a);
        }*/
    }
    
    //Methos to add comment to the comments list

    public void addComment(String comment) {
        commentItem item = new commentItem("menis", comment, "0", "0", 1);
        getComment().insert(countInsertion++, item);
    }
    //method to add comments for testing the UI

    public void addComments() {
        String v = "";
        String comm = "This is a";
        String comm2 = " long comment!";
        for (int i = 0; i < 6; i++) {
            String comment = comm + v + comm2;
            v += " very";
            commentItem item = new commentItem("menis", comment, i + 1 + "", "3", i + 1);
            Comment.insert(countInsertion++, item);
        }
    }
    
//<editor-fold defaultstate="collapsed" desc=" Generated Methods ">//GEN-BEGIN:|methods|0|
//</editor-fold>//GEN-END:|methods|0|
//<editor-fold defaultstate="collapsed" desc=" Generated Method: initialize ">//GEN-BEGIN:|0-initialize|0|0-preInitialize
    /**
     * Initializes the application. It is called only once when the MIDlet is
     * started. The method is called before the
     * <code>startMIDlet</code> method.
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
        connectAccountAction();//GEN-LINE:|3-startMIDlet|1|3-postAction
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
     * Switches a current displayable in a display. The
     * <code>display</code> instance is taken from
     * <code>getDisplay</code> method. This method is used by all actions in the
     * design for switching displayable.
     *
     * @param alert the Alert which is temporarily set to the display; if
     * <code>null</code>, then
     * <code>nextDisplayable</code> is set immediately
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


        // write pre-action user code here

                // write pre-action user code here

                // write pre-action user code here

                // write pre-action user code here



//<editor-fold defaultstate="collapsed" desc=" Generated Getter: exitCommand ">//GEN-BEGIN:|18-getter|0|18-preInit
    /**
     * Returns an initialized instance of exitCommand component.
     *
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
     * Returns an initialized instance of form component.
     *
     * @return the initialized component instance
     */
    public Form getForm() {
        if (form == null) {//GEN-END:|14-getter|0|14-preInit
            // write pre-init user code here
            form = new Form("Welcome", new Item[]{getStringItem()});//GEN-LINE:|14-getter|1|14-postInit
            // write post-init user code here
        }//GEN-BEGIN:|14-getter|2|
        return form;
    }
//</editor-fold>//GEN-END:|14-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: stringItem ">//GEN-BEGIN:|16-getter|0|16-preInit
    /**
     * Returns an initialized instance of stringItem component.
     *
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
     * Returns an initialized instance of MainFeed component.
     *
     * @return the initialized component instance
     */
    public Form getMainFeed() {
        if (MainFeed == null) {//GEN-END:|22-getter|0|22-preInit
            // write pre-init user code here
            MainFeed = new Form("form1");//GEN-LINE:|22-getter|1|22-postInit
            // write post-init user code here
            helpMainFeed();
        }//GEN-BEGIN:|22-getter|2|
        return MainFeed;
    }
//</editor-fold>//GEN-END:|22-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: viewComments ">//GEN-BEGIN:|24-getter|0|24-preInit
    /**
     * Returns an initialized instance of viewComments component.
     *
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
     * Returns an initialized instance of Story component.
     *
     * @return the initialized component instance
     */
    public Form getStory() {
        if (Story == null) {//GEN-END:|23-getter|0|23-preInit
            // write pre-init user code here
            Story = new Form("form1");//GEN-LINE:|23-getter|1|23-postInit
            // write post-init user code here
        }//GEN-BEGIN:|23-getter|2|
        return Story;
    }
//</editor-fold>//GEN-END:|23-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: textBox ">//GEN-BEGIN:|26-getter|0|26-preInit
    /**
     * Returns an initialized instance of textBox component.
     *
     * @return the initialized component instance
     */
    public TextBox getTextBox() {
        if (textBox == null) {//GEN-END:|26-getter|0|26-preInit
            // write pre-init user code here
            textBox = new TextBox("textBox", null, 100, TextField.ANY);//GEN-LINE:|26-getter|1|26-postInit
            // write post-init user code here
        }//GEN-BEGIN:|26-getter|2|
        return textBox;
    }
//</editor-fold>//GEN-END:|26-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: Comment ">//GEN-BEGIN:|27-getter|0|27-preInit
    /**
     * Returns an initialized instance of Comment component.
     *
     * @return the initialized component instance
     */
    public Form getComment() {
        if (Comment == null) {//GEN-END:|27-getter|0|27-preInit
            // write pre-init user code here
    
            Comment = new Form("Comments", new Item[]{getTextField()});//GEN-LINE:|27-getter|1|27-postInit
            // write post-init user code here
addComments(); //adding dummy comments to test UI
               
               
        }//GEN-BEGIN:|27-getter|2|
        return Comment;
    }
//</editor-fold>//GEN-END:|27-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: Comment1 ">//GEN-BEGIN:|28-getter|0|28-preInit
    /**
     * Returns an initialized instance of Comment1 component.
     *
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

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: backCommandV ">//GEN-BEGIN:|30-getter|0|30-preInit
    /**
     * Returns an initialized instance of backCommandV component.
     *
     * @return the initialized component instance
     */
    public Command getBackCommandV() {
        if (backCommandV == null) {//GEN-END:|30-getter|0|30-preInit
            // write pre-init user code here
            backCommandV = new Command("Back", Command.BACK, 0);//GEN-LINE:|30-getter|1|30-postInit
            // write post-init user code here
        }//GEN-BEGIN:|30-getter|2|
        return backCommandV;
    }
//</editor-fold>//GEN-END:|30-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: okCommand ">//GEN-BEGIN:|32-getter|0|32-preInit
    /**
     * Returns an initialized instance of okCommand component.
     *
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
     * Returns an initialized instance of backCommand1 component.
     *
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

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: backCommand2 ">//GEN-BEGIN:|37-getter|0|37-preInit
    /**
     * Returns an initialized instance of backCommand2 component.
     *
     * @return the initialized component instance
     */
    public Command getBackCommand2() {
        if (backCommand2 == null) {//GEN-END:|37-getter|0|37-preInit
            // write pre-init user code here
            backCommand2 = new Command("Back", Command.BACK, 0);//GEN-LINE:|37-getter|1|37-postInit
            // write post-init user code here
        }//GEN-BEGIN:|37-getter|2|
        return backCommand2;
    }
//</editor-fold>//GEN-END:|37-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: comment2 ">//GEN-BEGIN:|39-getter|0|39-preInit
    /**
     * Returns an initialized instance of comment2 component.
     *
     * @return the initialized component instance
     */
    public Command getComment2() {
        if (comment2 == null) {//GEN-END:|39-getter|0|39-preInit
            // write pre-init user code here
            comment2 = new Command("Add", Command.OK, 0);//GEN-LINE:|39-getter|1|39-postInit
            // write post-init user code here
        }//GEN-BEGIN:|39-getter|2|
        return comment2;
    }
//</editor-fold>//GEN-END:|39-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: profile ">//GEN-BEGIN:|47-getter|0|47-preInit
    /**
     * Returns an initialized instance of profile component.
     *
     * @return the initialized component instance
     */
    public Form getProfile() {
        if (profile == null) {//GEN-END:|47-getter|0|47-preInit
            // write pre-init user code here
            profile = new Form("form1", new Item[]{getUserName(), getTextField1(), getLastName(), getDob(), getPas(), getConfPas()});//GEN-LINE:|47-getter|1|47-postInit
            // write post-init user code here
        }//GEN-BEGIN:|47-getter|2|
        return profile;
    }
//</editor-fold>//GEN-END:|47-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: verifyCommand ">//GEN-BEGIN:|48-getter|0|48-preInit
    /**
     * Returns an initialized instance of verifyCommand component.
     *
     * @return the initialized component instance
     */
    public Command getVerifyCommand() {
        if (verifyCommand == null) {//GEN-END:|48-getter|0|48-preInit
            // write pre-init user code here
            verifyCommand = new Command("Ok", Command.OK, 0);//GEN-LINE:|48-getter|1|48-postInit
            // write post-init user code here
        }//GEN-BEGIN:|48-getter|2|
        return verifyCommand;
    }
//</editor-fold>//GEN-END:|48-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: cancelCommand ">//GEN-BEGIN:|50-getter|0|50-preInit
    /**
     * Returns an initialized instance of cancelCommand component.
     *
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
     * Returns an initialized instance of back component.
     *
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
     * Returns an initialized instance of userName component.
     *
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

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: textField1 ">//GEN-BEGIN:|55-getter|0|55-preInit
    /**
     * Returns an initialized instance of textField1 component.
     *
     * @return the initialized component instance
     */
    public TextField getTextField1() {
        if (textField1 == null) {//GEN-END:|55-getter|0|55-preInit
            // write pre-init user code here
            textField1 = new TextField("firstName", null, 32, TextField.ANY);//GEN-LINE:|55-getter|1|55-postInit
            // write post-init user code here
        }//GEN-BEGIN:|55-getter|2|
        return textField1;
    }
//</editor-fold>//GEN-END:|55-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: lastName ">//GEN-BEGIN:|56-getter|0|56-preInit
    /**
     * Returns an initialized instance of lastName component.
     *
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
     * Returns an initialized instance of dob component.
     *
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
     * Returns an initialized instance of pas component.
     *
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
     * Returns an initialized instance of confPas component.
     *
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
     * Returns an initialized instance of options component.
     *
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
     * Returns an initialized instance of textField component.
     *
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
     * Returns an initialized instance of CommentFail component.
     *
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
     * Returns an initialized instance of indicator component.
     *
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
     * Returns an initialized instance of CommentSucc component.
     *
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
     * Returns an initialized instance of okCommand1 component.
     *
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
     * Returns an initialized instance of connectAccount component.
     *
     * @return the initialized component instance
     */
    public List getConnectAccount() {
        if (connectAccount == null) {//GEN-END:|80-getter|0|80-preInit
        // write pre-init user code here
            connectAccount = new List("Network", Choice.IMPLICIT);//GEN-BEGIN:|80-getter|1|80-postInit
            connectAccount.append("Twitter", null);
            connectAccount.append("Facebook", null);
            connectAccount.append("Flickr", null);
            connectAccount.append("Foursquare", null);
            connectAccount.append("Tumblr", null);
            connectAccount.append("Youtube", null);
            connectAccount.setCommandListener(this);
            connectAccount.setSelectedFlags(new boolean[]{false, false, false, false, false, false});//GEN-END:|80-getter|1|80-postInit
        // write post-init user code here
        }//GEN-BEGIN:|80-getter|2|
        return connectAccount;
    }
//</editor-fold>//GEN-END:|80-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Method: connectAccountAction ">//GEN-BEGIN:|80-action|0|80-preAction
    /**
     * Performs an action assigned to the selected list element in the
     * connectAccount component.
     */
    public void connectAccountAction() {//GEN-END:|80-action|0|80-preAction
    // enter pre-action user code here
        String __selectedString = getConnectAccount().getString(getConnectAccount().getSelectedIndex());//GEN-BEGIN:|80-action|1|83-preAction
        if (__selectedString != null) {
            if (__selectedString.equals("Twitter")) {//GEN-END:|80-action|1|83-preAction
            // write pre-action user code here
//GEN-LINE:|80-action|2|83-postAction
            // write post-action user code here
            } else if (__selectedString.equals("Facebook")) {//GEN-LINE:|80-action|3|84-preAction
            // write pre-action user code here
//GEN-LINE:|80-action|4|84-postAction
            // write post-action user code here
            } else if (__selectedString.equals("Flickr")) {//GEN-LINE:|80-action|5|85-preAction
            // write pre-action user code here
//GEN-LINE:|80-action|6|85-postAction
            // write post-action user code here
            } else if (__selectedString.equals("Foursquare")) {//GEN-LINE:|80-action|7|86-preAction
            // write pre-action user code here
//GEN-LINE:|80-action|8|86-postAction
            // write post-action user code here
            } else if (__selectedString.equals("Tumblr")) {//GEN-LINE:|80-action|9|87-preAction
            // write pre-action user code here
//GEN-LINE:|80-action|10|87-postAction
            // write post-action user code here
            } else if (__selectedString.equals("Youtube")) {//GEN-LINE:|80-action|11|88-preAction
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
     * Returns an initialized instance of search component.
     *
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
     * Returns an initialized instance of Add component.
     *
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
     * Returns an initialized instance of findFriend component.
     *
     * @return the initialized component instance
     */
    public Form getFindFriend() {
        if (findFriend == null) {//GEN-END:|92-getter|0|92-preInit
        // write pre-init user code here
            findFriend = new Form("form1", new Item[]{getSearch()});//GEN-BEGIN:|92-getter|1|92-postInit
            findFriend.addCommand(getBack1());
            findFriend.addCommand(getFind());
            findFriend.addCommand(getAdd1());
            findFriend.setCommandListener(this);//GEN-END:|92-getter|1|92-postInit
        // write post-init user code here
        }//GEN-BEGIN:|92-getter|2|
        return findFriend;
    }
//</editor-fold>//GEN-END:|92-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: Back1 ">//GEN-BEGIN:|93-getter|0|93-preInit
    /**
     * Returns an initialized instance of Back1 component.
     *
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

//<editor-fold defaultstate="collapsed" desc=" Generated Method: commandAction for Displayables ">//GEN-BEGIN:|7-commandAction|0|7-preCommandAction
    /**
     * Called by a system to indicated that a command has been invoked on a
     * particular displayable.
     *
     * @param command the Command that was invoked
     * @param displayable the Displayable where the command was invoked
     */
    public void commandAction(Command command, Displayable displayable) {//GEN-END:|7-commandAction|0|7-preCommandAction
        // write pre-action user code here
        if (displayable == Verification) {//GEN-BEGIN:|7-commandAction|1|139-preAction
            if (command == Find) {//GEN-END:|7-commandAction|1|139-preAction
                // write pre-action user code here
//GEN-LINE:|7-commandAction|2|139-postAction
                // write post-action user code here
            } else if (command == backCommandV) {//GEN-LINE:|7-commandAction|3|135-preAction
                // write pre-action user code here
//GEN-LINE:|7-commandAction|4|135-postAction
                // write post-action user code here
            } else if (command == verifyCommand) {//GEN-LINE:|7-commandAction|5|138-preAction
                // write pre-action user code here
//GEN-LINE:|7-commandAction|6|138-postAction
                // write post-action user code here
            }//GEN-BEGIN:|7-commandAction|7|123-preAction
        } else if (displayable == choosefriend1) {
            if (command == Find) {//GEN-END:|7-commandAction|7|123-preAction
                // write pre-action user code here
//GEN-LINE:|7-commandAction|8|123-postAction
                // write post-action user code here
            } else if (command == List.SELECT_COMMAND) {//GEN-LINE:|7-commandAction|9|117-preAction
                // write pre-action user code here
                choosefriend1Action();//GEN-LINE:|7-commandAction|10|117-postAction
                // write post-action user code here
            } else if (command == backCommand4) {//GEN-LINE:|7-commandAction|11|122-preAction
                // write pre-action user code here
//GEN-LINE:|7-commandAction|12|122-postAction
                // write post-action user code here
            } else if (command == okCommand2) {//GEN-LINE:|7-commandAction|13|120-preAction
                // write pre-action user code here
//GEN-LINE:|7-commandAction|14|120-postAction
                // write post-action user code here
            }//GEN-BEGIN:|7-commandAction|15|81-preAction
        } else if (displayable == connectAccount) {
            if (command == List.SELECT_COMMAND) {//GEN-END:|7-commandAction|15|81-preAction
                // write pre-action user code here
                connectAccountAction();//GEN-LINE:|7-commandAction|16|81-postAction
                // write post-action user code here
            }//GEN-BEGIN:|7-commandAction|17|106-preAction
        } else if (displayable == findFriend) {
            if (command == Add1) {//GEN-END:|7-commandAction|17|106-preAction
                // write pre-action user code here
//GEN-LINE:|7-commandAction|18|106-postAction
                // write post-action user code here
            } else if (command == Back1) {//GEN-LINE:|7-commandAction|19|94-preAction
                // write pre-action user code here
//GEN-LINE:|7-commandAction|20|94-postAction
                // write post-action user code here
            } else if (command == Find) {//GEN-LINE:|7-commandAction|21|96-preAction
                // write pre-action user code here
//GEN-LINE:|7-commandAction|22|96-postAction
                // write post-action user code here
            }//GEN-BEGIN:|7-commandAction|23|128-preAction
        } else if (displayable == likeddisliked) {
            if (command == List.SELECT_COMMAND) {//GEN-END:|7-commandAction|23|128-preAction
                // write pre-action user code here
                likeddislikedAction();//GEN-LINE:|7-commandAction|24|128-postAction
                // write post-action user code here
            } else if (command == backCommandV) {//GEN-LINE:|7-commandAction|25|130-preAction
                // write pre-action user code here
//GEN-LINE:|7-commandAction|26|130-postAction
                // write post-action user code here
            }//GEN-BEGIN:|7-commandAction|27|115-preAction
        } else if (displayable == recommend) {
            if (command == backCommand3) {//GEN-END:|7-commandAction|27|115-preAction
                // write pre-action user code here
//GEN-LINE:|7-commandAction|28|115-postAction
                // write post-action user code here
            } else if (command == choosefriend) {//GEN-LINE:|7-commandAction|29|124-preAction
                // write pre-action user code here
//GEN-LINE:|7-commandAction|30|124-postAction
                // write post-action user code here
            } else if (command == okCommand) {//GEN-LINE:|7-commandAction|31|113-preAction
                // write pre-action user code here
//GEN-LINE:|7-commandAction|32|113-postAction
                // write post-action user code here
            }//GEN-BEGIN:|7-commandAction|33|7-postCommandAction
        }//GEN-END:|7-commandAction|33|7-postCommandAction
        // write post-action user code here
    }//GEN-BEGIN:|7-commandAction|34|
//</editor-fold>//GEN-END:|7-commandAction|34|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: Find ">//GEN-BEGIN:|95-getter|0|95-preInit
    /**
     * Returns an initialized instance of Find component.
     *
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

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: choosefriend ">//GEN-BEGIN:|111-getter|0|111-preInit
    /**
     * Returns an initialized instance of choosefriend component.
     *
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

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: Add1 ">//GEN-BEGIN:|105-getter|0|105-preInit
    /**
     * Returns an initialized instance of Add1 component.
     *
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

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: okCommand2 ">//GEN-BEGIN:|119-getter|0|119-preInit
    /**
     * Returns an initialized instance of okCommand2 component.
     *
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

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: backCommand3 ">//GEN-BEGIN:|114-getter|0|114-preInit
    /**
     * Returns an initialized instance of backCommand3 component.
     *
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

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: backCommand4 ">//GEN-BEGIN:|121-getter|0|121-preInit
    /**
     * Returns an initialized instance of backCommand4 component.
     *
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

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: Found ">//GEN-BEGIN:|100-getter|0|100-preInit
    /**
     * Returns an initialized instance of Found component.
     *
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
     * Returns an initialized instance of NotFound component.
     *
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

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: Saved ">//GEN-BEGIN:|98-getter|0|98-preInit
    /**
     * Returns an initialized instance of Saved component.
     *
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

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: Error ">//GEN-BEGIN:|99-getter|0|99-preInit
    /**
     * Returns an initialized instance of Error component.
     *
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

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: recommend ">//GEN-BEGIN:|108-getter|0|108-preInit
    /**
     * Returns an initialized instance of recommend component.
     *
     * @return the initialized component instance
     */
    public Form getRecommend() {
        if (recommend == null) {//GEN-END:|108-getter|0|108-preInit
            // write pre-init user code here
            recommend = new Form("form1", new Item[]{getTextField2(), getTextField3()});//GEN-BEGIN:|108-getter|1|108-postInit
            recommend.addCommand(getOkCommand());
            recommend.addCommand(getBackCommand3());
            recommend.addCommand(getChoosefriend());
            recommend.setCommandListener(this);//GEN-END:|108-getter|1|108-postInit
            // write post-init user code here
        }//GEN-BEGIN:|108-getter|2|
        return recommend;
    }
//</editor-fold>//GEN-END:|108-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: textField3 ">//GEN-BEGIN:|110-getter|0|110-preInit
    /**
     * Returns an initialized instance of textField3 component.
     *
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

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: textField2 ">//GEN-BEGIN:|109-getter|0|109-preInit
    /**
     * Returns an initialized instance of textField2 component.
     *
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

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: ComingSoon ">//GEN-BEGIN:|107-getter|0|107-preInit
    /**
     * Returns an initialized instance of ComingSoon component.
     *
     * @return the initialized component instance
     */
    public Alert getComingSoon() {
        if (ComingSoon == null) {//GEN-END:|107-getter|0|107-preInit
            // write pre-init user code here
            ComingSoon = new Alert("Coming Soon", "This network is coming soon", null, null);//GEN-BEGIN:|107-getter|1|107-postInit
            ComingSoon.setTimeout(Alert.FOREVER);//GEN-END:|107-getter|1|107-postInit
            // write post-init user code here
        }//GEN-BEGIN:|107-getter|2|
        return ComingSoon;
    }
//</editor-fold>//GEN-END:|107-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: EnterUserNameEmail ">//GEN-BEGIN:|104-getter|0|104-preInit
    /**
     * Returns an initialized instance of EnterUserNameEmail component.
     *
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

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: choosefriend1 ">//GEN-BEGIN:|116-getter|0|116-preInit
    /**
     * Returns an initialized instance of choosefriend1 component.
     *
     * @return the initialized component instance
     */
    public List getChoosefriend1() {
        if (choosefriend1 == null) {//GEN-END:|116-getter|0|116-preInit
            // write pre-init user code here
            choosefriend1 = new List("choosefriend", Choice.IMPLICIT);//GEN-BEGIN:|116-getter|1|116-postInit
            choosefriend1.addCommand(getOkCommand2());
            choosefriend1.addCommand(getBackCommand4());
            choosefriend1.addCommand(getFind());
            choosefriend1.setCommandListener(this);//GEN-END:|116-getter|1|116-postInit
            // write post-init user code here
        }//GEN-BEGIN:|116-getter|2|
        return choosefriend1;
    }
//</editor-fold>//GEN-END:|116-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Method: choosefriend1Action ">//GEN-BEGIN:|116-action|0|116-preAction
    /**
     * Performs an action assigned to the selected list element in the
     * choosefriend1 component.
     */
    public void choosefriend1Action() {//GEN-END:|116-action|0|116-preAction
        // enter pre-action user code here
        String __selectedString = getChoosefriend1().getString(getChoosefriend1().getSelectedIndex());//GEN-LINE:|116-action|1|116-postAction
        // enter post-action user code here
    }//GEN-BEGIN:|116-action|2|
//</editor-fold>//GEN-END:|116-action|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: likeddisliked ">//GEN-BEGIN:|127-getter|0|127-preInit
    /**
     * Returns an initialized instance of likeddisliked component.
     *
     * @return the initialized component instance
     */
    public List getLikeddisliked() {
        if (likeddisliked == null) {//GEN-END:|127-getter|0|127-preInit
            // write pre-init user code here
            likeddisliked = new List("liked/disliked", Choice.IMPLICIT);//GEN-BEGIN:|127-getter|1|127-postInit
            likeddisliked.addCommand(getBackCommandV());
            likeddisliked.setCommandListener(this);//GEN-END:|127-getter|1|127-postInit
            // write post-init user code here
        }//GEN-BEGIN:|127-getter|2|
        return likeddisliked;
    }
//</editor-fold>//GEN-END:|127-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Method: likeddislikedAction ">//GEN-BEGIN:|127-action|0|127-preAction
    /**
     * Performs an action assigned to the selected list element in the
     * likeddisliked component.
     */
    public void likeddislikedAction() {//GEN-END:|127-action|0|127-preAction
        // enter pre-action user code here
        String __selectedString = getLikeddisliked().getString(getLikeddisliked().getSelectedIndex());//GEN-LINE:|127-action|1|127-postAction
        // enter post-action user code here
    }//GEN-BEGIN:|127-action|2|
//</editor-fold>//GEN-END:|127-action|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: storynotpublished ">//GEN-BEGIN:|126-getter|0|126-preInit
    /**
     * Returns an initialized instance of storynotpublished component.
     *
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

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: storeypublished ">//GEN-BEGIN:|125-getter|0|125-preInit
    /**
     * Returns an initialized instance of storeypublished component.
     *
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

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: Verification ">//GEN-BEGIN:|131-getter|0|131-preInit
    /**
     * Returns an initialized instance of Verification component.
     *
     * @return the initialized component instance
     */
    public Form getVerification() {
        if (Verification == null) {//GEN-END:|131-getter|0|131-preInit
            // write pre-init user code here
            Verification = new Form("form1", new Item[]{getVTF(), getStringItem1()});//GEN-BEGIN:|131-getter|1|131-postInit
            Verification.addCommand(getBackCommandV());
            Verification.addCommand(getVerifyCommand());
            Verification.addCommand(getFind());
            Verification.setCommandListener(this);//GEN-END:|131-getter|1|131-postInit
            // write post-init user code here
        }//GEN-BEGIN:|131-getter|2|
        return Verification;
    }
//</editor-fold>//GEN-END:|131-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: vTF ">//GEN-BEGIN:|133-getter|0|133-preInit
    /**
     * Returns an initialized instance of vTF component.
     *
     * @return the initialized component instance
     */
    public TextField getVTF() {
        if (vTF == null) {//GEN-END:|133-getter|0|133-preInit
            // write pre-init user code here
            vTF = new TextField("Enter Verification Code:", "", 4, TextField.ANY);//GEN-LINE:|133-getter|1|133-postInit
            // write post-init user code here
        }//GEN-BEGIN:|133-getter|2|
        return vTF;
    }
//</editor-fold>//GEN-END:|133-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: stringItem1 ">//GEN-BEGIN:|134-getter|0|134-preInit
    /**
     * Returns an initialized instance of stringItem1 component.
     *
     * @return the initialized component instance
     */
    public StringItem getStringItem1() {
        if (stringItem1 == null) {//GEN-END:|134-getter|0|134-preInit
            // write pre-init user code here
            stringItem1 = new StringItem("", "Press the \'Resend\' button to resend the verification code to your registered   e-mail adrress.", Item.PLAIN);//GEN-LINE:|134-getter|1|134-postInit
            // write post-init user code here
        }//GEN-BEGIN:|134-getter|2|
        return stringItem1;
    }
//</editor-fold>//GEN-END:|134-getter|2|




    
 
   

    
    
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

    public void itemStateChanged(Item item) {
        throw new UnsupportedOperationException("Not supported yet.");
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

}
