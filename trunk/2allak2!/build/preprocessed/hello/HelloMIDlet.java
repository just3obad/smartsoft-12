/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hello;

import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;

/**
 * @author Essam Hafez
 */
public class HelloMIDlet extends MIDlet implements CommandListener {
      private ImageItem image;
    private StringItem storyBody;
    private StringItem storyRank;
    private Image addedImage;
    static int countInsertion = 0;
    private boolean midletPaused = false;
    //<editor-fold defaultstate="collapsed" desc=" Generated Fields ">//GEN-BEGIN:|fields|0|
    private Command exitCommand;
    private Command viewComments;
    private Command Comment1;
    private Command backCommand;
    private Command okCommand;
    private Command backCommand1;
    private Command backCommand2;
    private Command comment2;
    private Form form;
    private StringItem stringItem;
    private Form MainFeed;
    private Form Story;
    private TextBox textBox;
    private Form Comment;
    //</editor-fold>//GEN-END:|fields|0|

    /**
     * The HelloMIDlet constructor.
     */
    public HelloMIDlet() {
    }
       /* public void addStory(String imageName, String title, String body, int rank) {
        try {
            this.addedImage = Image.createImage("/x.png");
        } catch (Exception e) {
            System.out.println("ccccccccc find image");
            e.printStackTrace();
        }
        image = new ImageItem("", addedImage, ImageItem.LAYOUT_DEFAULT, "Nokia");
        image.setPreferredSize(15, 15);
        storyBody = new StringItem(title, body);
        storyRank = new StringItem(null, "Ranked: " + rank);
        //image.setLayout(0);
        MainFeed.insert(countInsertion++, image);
        //storyBody.setLayout(2);
        MainFeed.insert(countInsertion++, storyBody);
        //storyRank.setLayout(2);
        MainFeed.insert(countInsertion++, storyRank);
        //MainFeed.insert(countInsertion++,more);


    }*/

    public void helpMainFeed() {
        //addStory("/x.png", "new story222", "AaaAaAASa",3);
        for (int i = 0; i < 5; i++) {
            storyItem a = new storyItem("/x.png", "new Story", "", i + 1, "sportssssssssssssssssssss");//category 25 chars MAX
            MainFeed.insert(countInsertion++, a);
        }
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
        switchDisplayable(null, getStory());//GEN-LINE:|3-startMIDlet|1|3-postAction
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
        if (displayable == Comment) {//GEN-BEGIN:|7-commandAction|1|38-preAction
            if (command == backCommand2) {//GEN-END:|7-commandAction|1|38-preAction
                // write pre-action user code here
                switchDisplayable(null, getStory());//GEN-LINE:|7-commandAction|2|38-postAction
                // write post-action user code here
            } else if (command == comment2) {//GEN-LINE:|7-commandAction|3|40-preAction
                // write pre-action user code here
                switchDisplayable(null, getTextBox());//GEN-LINE:|7-commandAction|4|40-postAction
                // write post-action user code here
            }//GEN-BEGIN:|7-commandAction|5|29-preAction
        } else if (displayable == Story) {
            if (command == Comment1) {//GEN-END:|7-commandAction|5|29-preAction
                // write pre-action user code here
                switchDisplayable(null, getTextBox());//GEN-LINE:|7-commandAction|6|29-postAction
                // write post-action user code here
            } else if (command == backCommand) {//GEN-LINE:|7-commandAction|7|31-preAction
                // write pre-action user code here
                switchDisplayable(null, getMainFeed());//GEN-LINE:|7-commandAction|8|31-postAction
                // write post-action user code here
            } else if (command == viewComments) {//GEN-LINE:|7-commandAction|9|25-preAction
                // write pre-action user code here
                switchDisplayable(null, getComment());//GEN-LINE:|7-commandAction|10|25-postAction
                // write post-action user code here
            }//GEN-BEGIN:|7-commandAction|11|19-preAction
        } else if (displayable == form) {
            if (command == exitCommand) {//GEN-END:|7-commandAction|11|19-preAction
                // write pre-action user code here
                exitMIDlet();//GEN-LINE:|7-commandAction|12|19-postAction
                // write post-action user code here
            }//GEN-BEGIN:|7-commandAction|13|35-preAction
        } else if (displayable == textBox) {
            if (command == backCommand1) {//GEN-END:|7-commandAction|13|35-preAction
                // write pre-action user code here
                switchDisplayable(null, getStory());//GEN-LINE:|7-commandAction|14|35-postAction
                // write post-action user code here
            } else if (command == okCommand) {//GEN-LINE:|7-commandAction|15|33-preAction
                // write pre-action user code here
                switchDisplayable(null, getStory());//GEN-LINE:|7-commandAction|16|33-postAction
                // write post-action user code here
            }//GEN-BEGIN:|7-commandAction|17|7-postCommandAction
        }//GEN-END:|7-commandAction|17|7-postCommandAction
        // write post-action user code here
    }//GEN-BEGIN:|7-commandAction|18|
    //</editor-fold>//GEN-END:|7-commandAction|18|

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
            MainFeed = new Form("form1");//GEN-LINE:|22-getter|1|22-postInit
            // write post-init user code here
            helpMainFeed();
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
            Story.addCommand(getViewComments());
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

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: Comment ">//GEN-BEGIN:|27-getter|0|27-preInit
    /**
     * Returns an initiliazed instance of Comment component.
     * @return the initialized component instance
     */
    public Form getComment() {
        if (Comment == null) {//GEN-END:|27-getter|0|27-preInit
            // write pre-init user code here
            Comment = new Form("form1");//GEN-BEGIN:|27-getter|1|27-postInit
            Comment.addCommand(getBackCommand2());
            Comment.addCommand(getComment2());
            Comment.setCommandListener(this);//GEN-END:|27-getter|1|27-postInit
            // write post-init user code here
               addComments(); //adding dummy comments to test UI
        }//GEN-BEGIN:|27-getter|2|
        return Comment;
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
            Comment1 = new Command("Ok", Command.OK, 0);//GEN-LINE:|28-getter|1|28-postInit
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
            okCommand = new Command("Ok", Command.OK, 0);//GEN-LINE:|32-getter|1|32-postInit
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

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: backCommand2 ">//GEN-BEGIN:|37-getter|0|37-preInit
    /**
     * Returns an initiliazed instance of backCommand2 component.
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
     * Returns an initiliazed instance of comment2 component.
     * @return the initialized component instance
     */
    public Command getComment2() {
        if (comment2 == null) {//GEN-END:|39-getter|0|39-preInit
            // write pre-init user code here
            comment2 = new Command("Ok", Command.OK, 0);//GEN-LINE:|39-getter|1|39-postInit
            // write post-init user code here
        }//GEN-BEGIN:|39-getter|2|
        return comment2;
    }
    //</editor-fold>//GEN-END:|39-getter|2|

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
