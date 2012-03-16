/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;
import java.util.Enumeration.*;
import org.netbeans.microedition.lcdui.SplashScreen;
    
/**
 * @author Yahia
 */

public class CalcMID extends MIDlet implements CommandListener {
    
    private boolean midletPaused = false;
    private double firstNumber = 0;
    private double secondNumber = 0;
    private int operation = -1; //ADD=0, SUB=1, MULT=2, DIV=3
    private String result = "N/A";

    //<editor-fold defaultstate="collapsed" desc=" Generated Fields ">//GEN-BEGIN:|fields|0|
    private TextBox FirstNumberScreen;
    private TextBox SecondNumberScreen;
    private List OperationScreen;
    private Form ResultScreen;
    private StringItem stringItem;
    private SplashScreen splashScreen;
    private Command exitCommand;
    private Command Next;
    private Command cancelCommand;
    private Command Next3;
    private Command copytofirst;
    private Command backCommand;
    private Command cancelCommand1;
    //</editor-fold>//GEN-END:|fields|0|
    /**
     * The CalcMID constructor.
     */
    public CalcMID() {
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
        switchDisplayable(null, getFirstNumberScreen());//GEN-LINE:|3-startMIDlet|1|3-postAction
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
        try{
            if (displayable == FirstNumberScreen) {//GEN-BEGIN:|7-commandAction|1|32-preAction
                if (command == Next) {//GEN-END:|7-commandAction|1|32-preAction
                // write pre-action user code here
                if (FirstNumberScreen.getString().length() == 0)
                    FirstNumberScreen.setString("0");
                
                firstNumber = Double.parseDouble(FirstNumberScreen.getString());
                switchDisplayable(null, getOperationScreen());//GEN-LINE:|7-commandAction|2|32-postAction
                // write post-action user code here
                } else if (command == exitCommand) {//GEN-LINE:|7-commandAction|3|29-preAction
                // write pre-action user code here
                    exitMIDlet();//GEN-LINE:|7-commandAction|4|29-postAction
                // write post-action user code here
                }//GEN-BEGIN:|7-commandAction|5|38-preAction
            } else if (displayable == OperationScreen) {
                if (command == List.SELECT_COMMAND) {//GEN-END:|7-commandAction|5|38-preAction
                // write pre-action user code here
                operation = OperationScreen.getSelectedIndex();
                if (SecondNumberScreen != null)
                    SecondNumberScreen.delete(0, SecondNumberScreen.size());

                OperationScreenAction();//GEN-LINE:|7-commandAction|6|38-postAction
                // write post-action user code here
                } else if (command == cancelCommand) {//GEN-LINE:|7-commandAction|7|85-preAction
                // write pre-action user code here
                    switchDisplayable(null, getFirstNumberScreen());//GEN-LINE:|7-commandAction|8|85-postAction
                // write post-action user code here
                }//GEN-BEGIN:|7-commandAction|9|80-preAction
            } else if (displayable == ResultScreen) {
                if (command == cancelCommand) {//GEN-END:|7-commandAction|9|80-preAction
                // write pre-action user code here
               FirstNumberScreen.delete(0, FirstNumberScreen.size());
                
               switchDisplayable(null, getFirstNumberScreen());//GEN-LINE:|7-commandAction|10|80-postAction
                // write post-action user code here
                } else if (command == copytofirst) {//GEN-LINE:|7-commandAction|11|92-preAction
                // write pre-action user code here
                if (result.equals("N/A"))
                    firstNumber = 0;
                else
                    firstNumber = Double.parseDouble(result);
                switchDisplayable(null, getOperationScreen());//GEN-LINE:|7-commandAction|12|92-postAction
                // write post-action user code here
                } else if (command == exitCommand) {//GEN-LINE:|7-commandAction|13|79-preAction
                // write pre-action user code here
                    exitMIDlet();//GEN-LINE:|7-commandAction|14|79-postAction
                // write post-action user code here
                }//GEN-BEGIN:|7-commandAction|15|54-preAction
            } else if (displayable == SecondNumberScreen) {
                if (command == Next3) {//GEN-END:|7-commandAction|15|54-preAction
                // write pre-action user code here
                if (SecondNumberScreen.getString().length() == 0)
                    SecondNumberScreen.setString("0");

                    secondNumber = Double.parseDouble(SecondNumberScreen.getString());
                switch(operation)
                {
                    case 0: result = ""+(firstNumber + secondNumber);
                        break;
                    case 1: result = ""+(firstNumber - secondNumber);
                        break;
                    case 2: result = ""+(firstNumber * secondNumber);
                        break;
                    case 3:
                        if (secondNumber != 0)
                            result = ""+(firstNumber / secondNumber);
                        else 
                            result = "N/A";
                }
                stringItem = null; // to get updated
                ResultScreen = null; // to get updated
                switchDisplayable(null, getResultScreen());//GEN-LINE:|7-commandAction|16|54-postAction
                // write post-action user code here
               
                } else if (command == cancelCommand) {//GEN-LINE:|7-commandAction|17|46-preAction
                // write pre-action user code here
                    switchDisplayable(null, getFirstNumberScreen());//GEN-LINE:|7-commandAction|18|46-postAction
                // write post-action user code here
                }//GEN-BEGIN:|7-commandAction|19|99-preAction
            } else if (displayable == splashScreen) {
                if (command == SplashScreen.DISMISS_COMMAND) {//GEN-END:|7-commandAction|19|99-preAction
                    // write pre-action user code here
//GEN-LINE:|7-commandAction|20|99-postAction
                    // write post-action user code here
                } else if (command == cancelCommand) {//GEN-LINE:|7-commandAction|21|104-preAction
                    // write pre-action user code here
                    switchDisplayable(null, getFirstNumberScreen());//GEN-LINE:|7-commandAction|22|104-postAction
                    // write post-action user code here
                }//GEN-BEGIN:|7-commandAction|23|7-postCommandAction
            }//GEN-END:|7-commandAction|23|7-postCommandAction
        // write post-action user code here
        }
        catch(Exception e)
        {
            System.out.println("exception handled");
            switchDisplayable(null, getSplashScreen());            
        }
    }//GEN-BEGIN:|7-commandAction|24|
    //</editor-fold>//GEN-END:|7-commandAction|24|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: exitCommand ">//GEN-BEGIN:|20-getter|0|20-preInit
    /**
     * Returns an initiliazed instance of exitCommand component.
     * @return the initialized component instance
     */
    public Command getExitCommand() {
        if (exitCommand == null) {//GEN-END:|20-getter|0|20-preInit
            // write pre-init user code here
            exitCommand = new Command("Exit", Command.EXIT, 0);//GEN-LINE:|20-getter|1|20-postInit
            // write post-init user code here
        }//GEN-BEGIN:|20-getter|2|
        return exitCommand;
    }
    //</editor-fold>//GEN-END:|20-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: FirstNumberScreen ">//GEN-BEGIN:|27-getter|0|27-preInit
    /**
     * Returns an initiliazed instance of FirstNumberScreen component.
     * @return the initialized component instance
     */
    public TextBox getFirstNumberScreen() {
        if (FirstNumberScreen == null) {//GEN-END:|27-getter|0|27-preInit
            // write pre-init user code here
            FirstNumberScreen = new TextBox("Enter the first number", "", 100, TextField.DECIMAL);//GEN-BEGIN:|27-getter|1|27-postInit
            FirstNumberScreen.addCommand(getExitCommand());
            FirstNumberScreen.addCommand(getNext());
            FirstNumberScreen.setCommandListener(this);//GEN-END:|27-getter|1|27-postInit
            // write post-init user code here
        }//GEN-BEGIN:|27-getter|2|
        return FirstNumberScreen;
    }
    //</editor-fold>//GEN-END:|27-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: SecondNumberScreen ">//GEN-BEGIN:|33-getter|0|33-preInit
    /**
     * Returns an initiliazed instance of SecondNumberScreen component.
     * @return the initialized component instance
     */
    public TextBox getSecondNumberScreen() {
        if (SecondNumberScreen == null) {//GEN-END:|33-getter|0|33-preInit
            // write pre-init user code here
            SecondNumberScreen = new TextBox("Enter the scond number", "", 100, TextField.DECIMAL);//GEN-BEGIN:|33-getter|1|33-postInit
            SecondNumberScreen.addCommand(getCancelCommand());
            SecondNumberScreen.addCommand(getNext3());
            SecondNumberScreen.setCommandListener(this);//GEN-END:|33-getter|1|33-postInit
            // write post-init user code here
        }//GEN-BEGIN:|33-getter|2|
        return SecondNumberScreen;
    }
    //</editor-fold>//GEN-END:|33-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: OperationScreen ">//GEN-BEGIN:|37-getter|0|37-preInit
    /**
     * Returns an initiliazed instance of OperationScreen component.
     * @return the initialized component instance
     */
    public List getOperationScreen() {
        if (OperationScreen == null) {//GEN-END:|37-getter|0|37-preInit
            // write pre-init user code here
            OperationScreen = new List("Choose second number", Choice.IMPLICIT);//GEN-BEGIN:|37-getter|1|37-postInit
            OperationScreen.append("ADD", null);
            OperationScreen.append("SUB", null);
            OperationScreen.append("MULT", null);
            OperationScreen.append("DIV", null);
            OperationScreen.addCommand(getCancelCommand());
            OperationScreen.setCommandListener(this);
            OperationScreen.setSelectedFlags(new boolean[] { false, false, false, false });//GEN-END:|37-getter|1|37-postInit
            // write post-init user code here
        }//GEN-BEGIN:|37-getter|2|
        return OperationScreen;
    }
    //</editor-fold>//GEN-END:|37-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Method: OperationScreenAction ">//GEN-BEGIN:|37-action|0|37-preAction
    /**
     * Performs an action assigned to the selected list element in the OperationScreen component.
     */
    public void OperationScreenAction() {//GEN-END:|37-action|0|37-preAction
        // enter pre-action user code here
        String __selectedString = getOperationScreen().getString(getOperationScreen().getSelectedIndex());//GEN-BEGIN:|37-action|1|63-preAction
        if (__selectedString != null) {
            if (__selectedString.equals("ADD")) {//GEN-END:|37-action|1|63-preAction
                // write pre-action user code here
                switchDisplayable(null, getSecondNumberScreen());//GEN-LINE:|37-action|2|63-postAction
                // write post-action user code here
            } else if (__selectedString.equals("SUB")) {//GEN-LINE:|37-action|3|64-preAction
                // write pre-action user code here
                switchDisplayable(null, getSecondNumberScreen());//GEN-LINE:|37-action|4|64-postAction
                // write post-action user code here
            } else if (__selectedString.equals("MULT")) {//GEN-LINE:|37-action|5|65-preAction
                // write pre-action user code here
                switchDisplayable(null, getSecondNumberScreen());//GEN-LINE:|37-action|6|65-postAction
                // write post-action user code here
            } else if (__selectedString.equals("DIV")) {//GEN-LINE:|37-action|7|66-preAction
                // write pre-action user code here
                switchDisplayable(null, getSecondNumberScreen());//GEN-LINE:|37-action|8|66-postAction
                // write post-action user code here
            }//GEN-BEGIN:|37-action|9|37-postAction
        }//GEN-END:|37-action|9|37-postAction
        // enter post-action user code here
    }//GEN-BEGIN:|37-action|10|
    //</editor-fold>//GEN-END:|37-action|10|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: Next ">//GEN-BEGIN:|31-getter|0|31-preInit
    /**
     * Returns an initiliazed instance of Next component.
     * @return the initialized component instance
     */
    public Command getNext() {
        if (Next == null) {//GEN-END:|31-getter|0|31-preInit
            // write pre-init user code here
            Next = new Command("Ok", Command.OK, 0);//GEN-LINE:|31-getter|1|31-postInit
            // write post-init user code here
        }//GEN-BEGIN:|31-getter|2|
        return Next;
    }
    //</editor-fold>//GEN-END:|31-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: cancelCommand ">//GEN-BEGIN:|45-getter|0|45-preInit
    /**
     * Returns an initiliazed instance of cancelCommand component.
     * @return the initialized component instance
     */
    public Command getCancelCommand() {
        if (cancelCommand == null) {//GEN-END:|45-getter|0|45-preInit
            // write pre-init user code here
            cancelCommand = new Command("Cancel", Command.CANCEL, 0);//GEN-LINE:|45-getter|1|45-postInit
            // write post-init user code here
        }//GEN-BEGIN:|45-getter|2|
        return cancelCommand;
    }
    //</editor-fold>//GEN-END:|45-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: Next3 ">//GEN-BEGIN:|53-getter|0|53-preInit
    /**
     * Returns an initiliazed instance of Next3 component.
     * @return the initialized component instance
     */
    public Command getNext3() {
        if (Next3 == null) {//GEN-END:|53-getter|0|53-preInit
            // write pre-init user code here
            Next3 = new Command("Ok", Command.OK, 0);//GEN-LINE:|53-getter|1|53-postInit
            // write post-init user code here
        }//GEN-BEGIN:|53-getter|2|
        return Next3;
    }
    //</editor-fold>//GEN-END:|53-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: ResultScreen ">//GEN-BEGIN:|71-getter|0|71-preInit
    /**
     * Returns an initiliazed instance of ResultScreen component.
     * @return the initialized component instance
     */
    public Form getResultScreen() {
        if (ResultScreen == null) {//GEN-END:|71-getter|0|71-preInit
            // write pre-init user code here
            ResultScreen = new Form("form", new Item[] { getStringItem() });//GEN-BEGIN:|71-getter|1|71-postInit
            ResultScreen.addCommand(getExitCommand());
            ResultScreen.addCommand(getCancelCommand());
            ResultScreen.addCommand(getCopytofirst());
            ResultScreen.setCommandListener(this);//GEN-END:|71-getter|1|71-postInit
            // write post-init user code here
        }//GEN-BEGIN:|71-getter|2|
        return ResultScreen;
    }
    //</editor-fold>//GEN-END:|71-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: stringItem ">//GEN-BEGIN:|88-getter|0|88-preInit
    /**
     * Returns an initiliazed instance of stringItem component.
     * @return the initialized component instance
     */
    public StringItem getStringItem() {
        if (stringItem == null) {//GEN-END:|88-getter|0|88-preInit
            // write pre-init user code here
            stringItem = new StringItem("Result is ", (result+""));//GEN-LINE:|88-getter|1|88-postInit
            // write post-init user code here
        }//GEN-BEGIN:|88-getter|2|
        return stringItem;
    }
    //</editor-fold>//GEN-END:|88-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: copytofirst ">//GEN-BEGIN:|91-getter|0|91-preInit
    /**
     * Returns an initiliazed instance of copytofirst component.
     * @return the initialized component instance
     */
    public Command getCopytofirst() {
        if (copytofirst == null) {//GEN-END:|91-getter|0|91-preInit
            // write pre-init user code here
            copytofirst = new Command("Use", "<null>", Command.ITEM, 0);//GEN-LINE:|91-getter|1|91-postInit
            // write post-init user code here
        }//GEN-BEGIN:|91-getter|2|
        return copytofirst;
    }
    //</editor-fold>//GEN-END:|91-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: splashScreen ">//GEN-BEGIN:|97-getter|0|97-preInit
    /**
     * Returns an initiliazed instance of splashScreen component.
     * @return the initialized component instance
     */
    public SplashScreen getSplashScreen() {
        if (splashScreen == null) {//GEN-END:|97-getter|0|97-preInit
            // write pre-init user code here
            splashScreen = new SplashScreen(getDisplay());//GEN-BEGIN:|97-getter|1|97-postInit
            splashScreen.setTitle("Error");
            splashScreen.addCommand(getCancelCommand());
            splashScreen.setCommandListener(this);
            splashScreen.setText("check your input :/");//GEN-END:|97-getter|1|97-postInit
            // write post-init user code here
        }//GEN-BEGIN:|97-getter|2|
        return splashScreen;
    }
    //</editor-fold>//GEN-END:|97-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: backCommand ">//GEN-BEGIN:|100-getter|0|100-preInit
    /**
     * Returns an initiliazed instance of backCommand component.
     * @return the initialized component instance
     */
    public Command getBackCommand() {
        if (backCommand == null) {//GEN-END:|100-getter|0|100-preInit
            // write pre-init user code here
            backCommand = new Command("Back", Command.BACK, 0);//GEN-LINE:|100-getter|1|100-postInit
            // write post-init user code here
        }//GEN-BEGIN:|100-getter|2|
        return backCommand;
    }
    //</editor-fold>//GEN-END:|100-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: cancelCommand1 ">//GEN-BEGIN:|102-getter|0|102-preInit
    /**
     * Returns an initiliazed instance of cancelCommand1 component.
     * @return the initialized component instance
     */
    public Command getCancelCommand1() {
        if (cancelCommand1 == null) {//GEN-END:|102-getter|0|102-preInit
            // write pre-init user code here
            cancelCommand1 = new Command("Cancel", Command.CANCEL, 0);//GEN-LINE:|102-getter|1|102-postInit
            // write post-init user code here
        }//GEN-BEGIN:|102-getter|2|
        return cancelCommand1;
    }
    //</editor-fold>//GEN-END:|102-getter|2|

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