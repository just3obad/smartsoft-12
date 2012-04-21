/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hello;

import java.io.IOException;
import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;

/**
 * @author Essam Hafez
 */
public class VisualApp extends MIDlet implements CommandListener {
    private ImageItem image;
    private StringItem storyBody;
    private StringItem storyRank;
    private Image addedImage;
    static int countInsertion=0;
    private boolean midletPaused = false;
//<editor-fold defaultstate="collapsed" desc=" Generated Fields ">//GEN-BEGIN:|fields|0|
private Form MainFeed;
private TextBox textBox;
private Form Comment;
private Form Story;
private Command screenCommand2;
private Command backCommand;
private Command screenCommand;
private Command okCommand;
private Command screenCommand1;
private Command okCommand1;
private Command ViewComments;
private Command ViewComments1;
private Command backCommand1;
private Command backCommand2;
private Command enterComment;
private Command okCommand2;
private Command okCommand3;
private Command backCommand3;
private Command Comment1;
//</editor-fold>//GEN-END:|fields|0|
    /**
     * The VisualApp constructor.
     */
    public VisualApp() {
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
switchDisplayable (null, getStory ());//GEN-LINE:|3-startMIDlet|1|3-postAction
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

    
    public void addStory(String imageName,String title,String body,int rank){
            try{
              this.addedImage =Image.createImage("/x.png");
           }catch(Exception e){
               System.out.println("ccccccccc find image");
               e.printStackTrace();
           }
           image = new ImageItem("", addedImage, ImageItem.LAYOUT_DEFAULT, "Nokia");
           image.setPreferredSize(15,15);
           storyBody = new StringItem(title, body);
           storyRank = new StringItem(null,"Ranked: " + rank);
           //image.setLayout(0);
           MainFeed.insert(countInsertion++,image);
           //storyBody.setLayout(2);
           MainFeed.insert(countInsertion++,storyBody );
           //storyRank.setLayout(2);
           MainFeed.insert(countInsertion++,storyRank);
           //MainFeed.insert(countInsertion++,more);
       
        
    }
    public void helpMainFeed(){
        //addStory("/x.png", "new story222", "AaaAaAASa",3);
        for(int i=0;i<5;i++){
        storyItem a = new storyItem("/x.png","new Story","",i+1,"sportssssssssssssssssssss");//category 25 chars MAX
        MainFeed.insert(countInsertion++, a);
        }
  }    
    
    public void addComment(String comment){
        CommentItem  item = new CommentItem("menis",comment,"0","0",1);
        getComment().insert(countInsertion++,item);
    }
    //method to add comments for testing the UI
   public void addComments(){
       String v = "";
       String comm = "This is a";
       String comm2 = " long comment!";
       for(int i=0;i<6;i++){
           String comment = comm+v+comm2;
           v+=" very";
           CommentItem item = new CommentItem("menis",comment,i+1+"","3",i+1);
           Comment.insert(countInsertion++, item);
       }
   }
    
//<editor-fold defaultstate="collapsed" desc=" Generated Getter: MainFeed ">//GEN-BEGIN:|14-getter|0|14-preInit
/**
 * Returns an initiliazed instance of MainFeed component.
 * @return the initialized component instance
 */
public Form getMainFeed () {
if (MainFeed == null) {//GEN-END:|14-getter|0|14-preInit
            // write pre-init user code here
MainFeed = new Form ("form", new Item[] { });//GEN-LINE:|14-getter|1|14-postInit
            // write post-init user code here
           helpMainFeed();
          
           // MainFeed.insert(0, );
}//GEN-BEGIN:|14-getter|2|
return MainFeed;
}
//</editor-fold>//GEN-END:|14-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: textBox ">//GEN-BEGIN:|18-getter|0|18-preInit
/**
 * Returns an initiliazed instance of textBox component.
 * @return the initialized component instance
 */
public TextBox getTextBox () {
if (textBox == null) {//GEN-END:|18-getter|0|18-preInit
            // write pre-init user code here
textBox = new TextBox ("Enter Comment", null, 100, TextField.ANY);//GEN-BEGIN:|18-getter|1|18-postInit
textBox.addCommand (getOkCommand2 ());
textBox.addCommand (getBackCommand3 ());
textBox.setCommandListener (this);//GEN-END:|18-getter|1|18-postInit
            // write post-init user code here
}//GEN-BEGIN:|18-getter|2|
return textBox;
}
//</editor-fold>//GEN-END:|18-getter|2|



//<editor-fold defaultstate="collapsed" desc=" Generated Getter: screenCommand ">//GEN-BEGIN:|19-getter|0|19-preInit
/**
 * Returns an initiliazed instance of screenCommand component.
 * @return the initialized component instance
 */
public Command getScreenCommand () {
if (screenCommand == null) {//GEN-END:|19-getter|0|19-preInit
            // write pre-init user code here
screenCommand = new Command ("Screen", Command.SCREEN, 0);//GEN-LINE:|19-getter|1|19-postInit
            // write post-init user code here
}//GEN-BEGIN:|19-getter|2|
return screenCommand;
}
//</editor-fold>//GEN-END:|19-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: okCommand ">//GEN-BEGIN:|22-getter|0|22-preInit
/**
 * Returns an initiliazed instance of okCommand component.
 * @return the initialized component instance
 */
public Command getOkCommand () {
if (okCommand == null) {//GEN-END:|22-getter|0|22-preInit
            // write pre-init user code here
okCommand = new Command ("Ok", Command.OK, 0);//GEN-LINE:|22-getter|1|22-postInit
            // write post-init user code here
}//GEN-BEGIN:|22-getter|2|
return okCommand;
}
//</editor-fold>//GEN-END:|22-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: screenCommand1 ">//GEN-BEGIN:|25-getter|0|25-preInit
/**
 * Returns an initiliazed instance of screenCommand1 component.
 * @return the initialized component instance
 */
public Command getScreenCommand1 () {
if (screenCommand1 == null) {//GEN-END:|25-getter|0|25-preInit
            // write pre-init user code here
screenCommand1 = new Command ("Screen", Command.SCREEN, 0);//GEN-LINE:|25-getter|1|25-postInit
            // write post-init user code here
}//GEN-BEGIN:|25-getter|2|
return screenCommand1;
}
//</editor-fold>//GEN-END:|25-getter|2|




//<editor-fold defaultstate="collapsed" desc=" Generated Getter: okCommand1 ">//GEN-BEGIN:|29-getter|0|29-preInit
/**
 * Returns an initiliazed instance of okCommand1 component.
 * @return the initialized component instance
 */
public Command getOkCommand1 () {
if (okCommand1 == null) {//GEN-END:|29-getter|0|29-preInit
            // write pre-init user code here
okCommand1 = new Command ("Ok", Command.OK, 0);//GEN-LINE:|29-getter|1|29-postInit
            // write post-init user code here
}//GEN-BEGIN:|29-getter|2|
return okCommand1;
}
//</editor-fold>//GEN-END:|29-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: backCommand ">//GEN-BEGIN:|32-getter|0|32-preInit
/**
 * Returns an initiliazed instance of backCommand component.
 * @return the initialized component instance
 */
public Command getBackCommand () {
if (backCommand == null) {//GEN-END:|32-getter|0|32-preInit
            // write pre-init user code here
backCommand = new Command ("Back", Command.BACK, 0);//GEN-LINE:|32-getter|1|32-postInit
            // write post-init user code here
}//GEN-BEGIN:|32-getter|2|
return backCommand;
}
//</editor-fold>//GEN-END:|32-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: screenCommand2 ">//GEN-BEGIN:|35-getter|0|35-preInit
/**
 * Returns an initiliazed instance of screenCommand2 component.
 * @return the initialized component instance
 */
public Command getScreenCommand2 () {
if (screenCommand2 == null) {//GEN-END:|35-getter|0|35-preInit
            // write pre-init user code here
screenCommand2 = new Command ("Screen", Command.SCREEN, 0);//GEN-LINE:|35-getter|1|35-postInit
            // write post-init user code here
}//GEN-BEGIN:|35-getter|2|
return screenCommand2;
}
//</editor-fold>//GEN-END:|35-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: Comment ">//GEN-BEGIN:|40-getter|0|40-preInit
/**
 * Returns an initiliazed instance of Comment component.
 * @return the initialized component instance
 */
public Form getComment () {
if (Comment == null) {//GEN-END:|40-getter|0|40-preInit
 // write pre-init user code here
Comment = new Form ("Comments");//GEN-BEGIN:|40-getter|1|40-postInit
Comment.addCommand (getBackCommand2 ());
Comment.addCommand (getComment1 ());
Comment.setCommandListener (this);//GEN-END:|40-getter|1|40-postInit
 // write post-init user code here
 addComments();
}//GEN-BEGIN:|40-getter|2|
return Comment;
}
//</editor-fold>//GEN-END:|40-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Method: commandAction for Displayables ">//GEN-BEGIN:|7-commandAction|0|7-preCommandAction
/**
 * Called by a system to indicated that a command has been invoked on a particular displayable.
 * @param command the Command that was invoked
 * @param displayable the Displayable where the command was invoked
 */
public void commandAction (Command command, Displayable displayable) {//GEN-END:|7-commandAction|0|7-preCommandAction
 // write pre-action user code here
if (displayable == Comment) {//GEN-BEGIN:|7-commandAction|1|66-preAction
if (command == Comment1) {//GEN-END:|7-commandAction|1|66-preAction
 // write pre-action user code here
switchDisplayable (null, getTextBox ());//GEN-LINE:|7-commandAction|2|66-postAction
 // write post-action user code here
} else if (command == backCommand2) {//GEN-LINE:|7-commandAction|3|52-preAction
 // write pre-action user code here
switchDisplayable (null, getStory ());//GEN-LINE:|7-commandAction|4|52-postAction
 // write post-action user code here
}//GEN-BEGIN:|7-commandAction|5|46-preAction
} else if (displayable == Story) {
if (command == ViewComments1) {//GEN-END:|7-commandAction|5|46-preAction
 // write pre-action user code here
switchDisplayable (null, getComment ());//GEN-LINE:|7-commandAction|6|46-postAction
 // write post-action user code here
} else if (command == backCommand1) {//GEN-LINE:|7-commandAction|7|49-preAction
 // write pre-action user code here
switchDisplayable (null, getMainFeed ());//GEN-LINE:|7-commandAction|8|49-postAction
 // write post-action user code here
} else if (command == enterComment) {//GEN-LINE:|7-commandAction|9|55-preAction
 // write pre-action user code here
switchDisplayable (null, getTextBox ());//GEN-LINE:|7-commandAction|10|55-postAction
 // write post-action user code here
}//GEN-BEGIN:|7-commandAction|11|63-preAction
} else if (displayable == textBox) {
if (command == backCommand3) {//GEN-END:|7-commandAction|11|63-preAction
 // write pre-action user code here
switchDisplayable (null, getStory ());//GEN-LINE:|7-commandAction|12|63-postAction
 // write post-action user code here
} else if (command == okCommand2) {//GEN-LINE:|7-commandAction|13|58-preAction
 // write pre-action user code here
addComment(getTextBox().getString());
switchDisplayable (null, getStory ());//GEN-LINE:|7-commandAction|14|58-postAction
 // write post-action user code here
}//GEN-BEGIN:|7-commandAction|15|7-postCommandAction
}//GEN-END:|7-commandAction|15|7-postCommandAction
 // write post-action user code here
}//GEN-BEGIN:|7-commandAction|16|
//</editor-fold>//GEN-END:|7-commandAction|16|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: Story ">//GEN-BEGIN:|41-getter|0|41-preInit
/**
 * Returns an initiliazed instance of Story component.
 * @return the initialized component instance
 */
public Form getStory () {
if (Story == null) {//GEN-END:|41-getter|0|41-preInit
 // write pre-init user code here
Story = new Form ("Story");//GEN-BEGIN:|41-getter|1|41-postInit
Story.addCommand (getViewComments1 ());
Story.addCommand (getBackCommand1 ());
Story.addCommand (getEnterComment ());
Story.setCommandListener (this);//GEN-END:|41-getter|1|41-postInit
 // write post-init user code here
}//GEN-BEGIN:|41-getter|2|
return Story;
}
//</editor-fold>//GEN-END:|41-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: ViewComments ">//GEN-BEGIN:|42-getter|0|42-preInit
/**
 * Returns an initiliazed instance of ViewComments component.
 * @return the initialized component instance
 */
public Command getViewComments () {
if (ViewComments == null) {//GEN-END:|42-getter|0|42-preInit
 // write pre-init user code here
ViewComments = new Command ("", Command.SCREEN, 0);//GEN-LINE:|42-getter|1|42-postInit
 // write post-init user code here
}//GEN-BEGIN:|42-getter|2|
return ViewComments;
}
//</editor-fold>//GEN-END:|42-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: ViewComments1 ">//GEN-BEGIN:|45-getter|0|45-preInit
/**
 * Returns an initiliazed instance of ViewComments1 component.
 * @return the initialized component instance
 */
public Command getViewComments1 () {
if (ViewComments1 == null) {//GEN-END:|45-getter|0|45-preInit
 // write pre-init user code here
ViewComments1 = new Command ("View Comments", "View Comments", Command.HELP, 0);//GEN-LINE:|45-getter|1|45-postInit
 // write post-init user code here
}//GEN-BEGIN:|45-getter|2|
return ViewComments1;
}
//</editor-fold>//GEN-END:|45-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: backCommand1 ">//GEN-BEGIN:|48-getter|0|48-preInit
/**
 * Returns an initiliazed instance of backCommand1 component.
 * @return the initialized component instance
 */
public Command getBackCommand1 () {
if (backCommand1 == null) {//GEN-END:|48-getter|0|48-preInit
 // write pre-init user code here
backCommand1 = new Command ("Back", Command.BACK, 0);//GEN-LINE:|48-getter|1|48-postInit
 // write post-init user code here
}//GEN-BEGIN:|48-getter|2|
return backCommand1;
}
//</editor-fold>//GEN-END:|48-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: backCommand2 ">//GEN-BEGIN:|51-getter|0|51-preInit
/**
 * Returns an initiliazed instance of backCommand2 component.
 * @return the initialized component instance
 */
public Command getBackCommand2 () {
if (backCommand2 == null) {//GEN-END:|51-getter|0|51-preInit
 // write pre-init user code here
backCommand2 = new Command ("Back", Command.BACK, 0);//GEN-LINE:|51-getter|1|51-postInit
 // write post-init user code here
}//GEN-BEGIN:|51-getter|2|
return backCommand2;
}
//</editor-fold>//GEN-END:|51-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: enterComment ">//GEN-BEGIN:|54-getter|0|54-preInit
/**
 * Returns an initiliazed instance of enterComment component.
 * @return the initialized component instance
 */
public Command getEnterComment () {
if (enterComment == null) {//GEN-END:|54-getter|0|54-preInit
 // write pre-init user code here
enterComment = new Command ("Comment", Command.ITEM, 0);//GEN-LINE:|54-getter|1|54-postInit
 // write post-init user code here
}//GEN-BEGIN:|54-getter|2|
return enterComment;
}
//</editor-fold>//GEN-END:|54-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: okCommand2 ">//GEN-BEGIN:|57-getter|0|57-preInit
/**
 * Returns an initiliazed instance of okCommand2 component.
 * @return the initialized component instance
 */
public Command getOkCommand2 () {
if (okCommand2 == null) {//GEN-END:|57-getter|0|57-preInit
 // write pre-init user code here
okCommand2 = new Command ("Ok", Command.OK, 0);//GEN-LINE:|57-getter|1|57-postInit
 // write post-init user code here
}//GEN-BEGIN:|57-getter|2|
return okCommand2;
}
//</editor-fold>//GEN-END:|57-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: okCommand3 ">//GEN-BEGIN:|60-getter|0|60-preInit
/**
 * Returns an initiliazed instance of okCommand3 component.
 * @return the initialized component instance
 */
public Command getOkCommand3 () {
if (okCommand3 == null) {//GEN-END:|60-getter|0|60-preInit
 // write pre-init user code here
okCommand3 = new Command ("Ok", Command.OK, 0);//GEN-LINE:|60-getter|1|60-postInit
 // write post-init user code here
}//GEN-BEGIN:|60-getter|2|
return okCommand3;
}
//</editor-fold>//GEN-END:|60-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: backCommand3 ">//GEN-BEGIN:|62-getter|0|62-preInit
/**
 * Returns an initiliazed instance of backCommand3 component.
 * @return the initialized component instance
 */
public Command getBackCommand3 () {
if (backCommand3 == null) {//GEN-END:|62-getter|0|62-preInit
 // write pre-init user code here
backCommand3 = new Command ("Back", Command.BACK, 0);//GEN-LINE:|62-getter|1|62-postInit
 // write post-init user code here
}//GEN-BEGIN:|62-getter|2|
return backCommand3;
}
//</editor-fold>//GEN-END:|62-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: Comment1 ">//GEN-BEGIN:|65-getter|0|65-preInit
/**
 * Returns an initiliazed instance of Comment1 component.
 * @return the initialized component instance
 */
public Command getComment1 () {
if (Comment1 == null) {//GEN-END:|65-getter|0|65-preInit
 // write pre-init user code here
Comment1 = new Command ("Comment", Command.OK, 0);//GEN-LINE:|65-getter|1|65-postInit
 // write post-init user code here
}//GEN-BEGIN:|65-getter|2|
return Comment1;
}
//</editor-fold>//GEN-END:|65-getter|2|











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
}
