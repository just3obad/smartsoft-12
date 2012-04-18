/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hello;

import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;

/**
 * @author Menisy
 */
public class APP extends MIDlet implements CommandListener {

    private boolean midletPaused = false;
   
   /* private ImageItem image;
    private StringItem title;
    private StringItem body;
    private StringItem rank;
    private Image addedImage;*/
    //<editor-fold defaultstate="collapsed" desc=" Generated Fields ">//GEN-BEGIN:|fields|0|
    private List CommentsList;
    private TextBox Comment;
    private Command okCommand;
    private Command okCommand1;
    private Command backCommand;
    private Command exitCommand;
    private Command backCommand1;
    //</editor-fold>//GEN-END:|fields|0|
    /**
     * The APP constructor.
     */
   /* public void initializeStories(){
        try{
        addedImage = Image.createImage("/nokia-logo-1.jpg");
        }catch(Exception e){
            System.out.println("Cannot find image");
        }
        image = new ImageItem("",  addedImage, ImageItem.LAYOUT_DEFAULT, "Nokia");
        title = new StringItem(null,"this is an article");
        body = new StringItem(null, "The body");
        rank = new StringItem(null," Ranked: 5 ");
        story = new Form(null, new Item[] {title,image,rank,body});
    }*/
    public APP() {
        //initializeStories();
        
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
        switchDisplayable(null, getCommentsList());//GEN-LINE:|3-startMIDlet|1|3-postAction
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
        if (displayable == Comment) {//GEN-BEGIN:|7-commandAction|1|27-preAction
            if (command == backCommand) {//GEN-END:|7-commandAction|1|27-preAction
 // write pre-action user code here
                switchDisplayable(null, getCommentsList());//GEN-LINE:|7-commandAction|2|27-postAction
 // write post-action user code here
            } else if (command == okCommand1) {//GEN-LINE:|7-commandAction|3|24-preAction
 // write pre-action user code here
    getCommentsList().append(getComment().getString(), null);
    switchDisplayable(null, getCommentsList());//GEN-LINE:|7-commandAction|4|24-postAction
 // write post-action user code here
            }//GEN-BEGIN:|7-commandAction|5|16-preAction
        } else if (displayable == CommentsList) {
            if (command == List.SELECT_COMMAND) {//GEN-END:|7-commandAction|5|16-preAction
 // write pre-action user code here
                CommentsListAction();//GEN-LINE:|7-commandAction|6|16-postAction
 // write post-action user code here
            } else if (command == exitCommand) {//GEN-LINE:|7-commandAction|7|30-preAction
 // write pre-action user code here
                exitMIDlet();//GEN-LINE:|7-commandAction|8|30-postAction
 // write post-action user code here
            } else if (command == okCommand) {//GEN-LINE:|7-commandAction|9|20-preAction
 // write pre-action user code here
                switchDisplayable(null, getComment());//GEN-LINE:|7-commandAction|10|20-postAction
 // write post-action user code here
            }//GEN-BEGIN:|7-commandAction|11|7-postCommandAction
        }//GEN-END:|7-commandAction|11|7-postCommandAction
 // write post-action user code here
    }//GEN-BEGIN:|7-commandAction|12|44-postAction
    //</editor-fold>//GEN-END:|7-commandAction|12|44-postAction


    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: CommentsList ">//GEN-BEGIN:|14-getter|0|14-preInit
    /**
     * Returns an initiliazed instance of CommentsList component.
     * @return the initialized component instance
     */
    public List getCommentsList() {
        if (CommentsList == null) {//GEN-END:|14-getter|0|14-preInit
 // write pre-init user code here
            CommentsList = new List("Comments", Choice.IMPLICIT);//GEN-BEGIN:|14-getter|1|14-postInit
            CommentsList.addCommand(getOkCommand());
            CommentsList.addCommand(getExitCommand());
            CommentsList.setCommandListener(this);//GEN-END:|14-getter|1|14-postInit
 // write post-init user code here
CommentsList.append("Sample comment 1", null);
        }//GEN-BEGIN:|14-getter|2|
        return CommentsList;
    }
    //</editor-fold>//GEN-END:|14-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Method: CommentsListAction ">//GEN-BEGIN:|14-action|0|14-preAction
    /**
     * Performs an action assigned to the selected list element in the CommentsList component.
     */
    public void CommentsListAction() {//GEN-END:|14-action|0|14-preAction
 // enter pre-action user code here
        String __selectedString = getCommentsList().getString(getCommentsList().getSelectedIndex());//GEN-LINE:|14-action|1|14-postAction
 // enter post-action user code here
    }//GEN-BEGIN:|14-action|2|
    //</editor-fold>//GEN-END:|14-action|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: Comment ">//GEN-BEGIN:|21-getter|0|21-preInit
    /**
     * Returns an initiliazed instance of Comment component.
     * @return the initialized component instance
     */
    public TextBox getComment() {
        if (Comment == null) {//GEN-END:|21-getter|0|21-preInit
 // write pre-init user code here
            Comment = new TextBox("textBox", null, 100, TextField.ANY);//GEN-BEGIN:|21-getter|1|21-postInit
            Comment.addCommand(getOkCommand1());
            Comment.addCommand(getBackCommand());
            Comment.setCommandListener(this);//GEN-END:|21-getter|1|21-postInit
 // write post-init user code here
        }//GEN-BEGIN:|21-getter|2|
        return Comment;
    }
    //</editor-fold>//GEN-END:|21-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: okCommand ">//GEN-BEGIN:|19-getter|0|19-preInit
    /**
     * Returns an initiliazed instance of okCommand component.
     * @return the initialized component instance
     */
    public Command getOkCommand() {
        if (okCommand == null) {//GEN-END:|19-getter|0|19-preInit
 // write pre-init user code here
            okCommand = new Command("Add comment", Command.OK, 0);//GEN-LINE:|19-getter|1|19-postInit
 // write post-init user code here
        }//GEN-BEGIN:|19-getter|2|
        return okCommand;
    }
    //</editor-fold>//GEN-END:|19-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: okCommand1 ">//GEN-BEGIN:|23-getter|0|23-preInit
    /**
     * Returns an initiliazed instance of okCommand1 component.
     * @return the initialized component instance
     */
    public Command getOkCommand1() {
        if (okCommand1 == null) {//GEN-END:|23-getter|0|23-preInit
 // write pre-init user code here
            okCommand1 = new Command("Ok", Command.OK, 0);//GEN-LINE:|23-getter|1|23-postInit
 // write post-init user code here
        }//GEN-BEGIN:|23-getter|2|
        return okCommand1;
    }
    //</editor-fold>//GEN-END:|23-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: backCommand ">//GEN-BEGIN:|26-getter|0|26-preInit
    /**
     * Returns an initiliazed instance of backCommand component.
     * @return the initialized component instance
     */
    public Command getBackCommand() {
        if (backCommand == null) {//GEN-END:|26-getter|0|26-preInit
 // write pre-init user code here
            backCommand = new Command("Back", Command.BACK, 0);//GEN-LINE:|26-getter|1|26-postInit
 // write post-init user code here
        }//GEN-BEGIN:|26-getter|2|
        return backCommand;
    }
    //</editor-fold>//GEN-END:|26-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: exitCommand ">//GEN-BEGIN:|29-getter|0|29-preInit
    /**
     * Returns an initiliazed instance of exitCommand component.
     * @return the initialized component instance
     */
    public Command getExitCommand() {
        if (exitCommand == null) {//GEN-END:|29-getter|0|29-preInit
 // write pre-init user code here
            exitCommand = new Command("Exit", Command.EXIT, 0);//GEN-LINE:|29-getter|1|29-postInit
 // write post-init user code here
        }//GEN-BEGIN:|29-getter|2|
        return exitCommand;
    }
    //</editor-fold>//GEN-END:|29-getter|2|

















    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: backCommand1 ">//GEN-BEGIN:|43-getter|0|43-preInit
    /**
     * Returns an initiliazed instance of backCommand1 component.
     * @return the initialized component instance
     */
    public Command getBackCommand1() {
        if (backCommand1 == null) {//GEN-END:|43-getter|0|43-preInit
            // write pre-init user code here
            backCommand1 = new Command("Back", Command.BACK, 0);//GEN-LINE:|43-getter|1|43-postInit
            // write post-init user code here
        }//GEN-BEGIN:|43-getter|2|
        return backCommand1;
    }
    //</editor-fold>//GEN-END:|43-getter|2|


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
