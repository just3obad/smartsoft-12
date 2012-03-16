/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hello;

import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;
import org.netbeans.microedition.lcdui.SimpleTableModel;
import org.netbeans.microedition.lcdui.TableItem;

/**
 * @author 51673r
 */
public class HelloMIDlet extends MIDlet implements CommandListener, ItemCommandListener {
    private double currentValue = 0;
    private boolean firstSymbol = true;
    private char currentCommand = '0';
    private boolean midletPaused = false;
//<editor-fold defaultstate="collapsed" desc=" Generated Fields ">//GEN-BEGIN:|fields|0|
    private Command exitCommand;
    private Command screenCommand;
    private Command screenCommand1;
    private Command screenCommand2;
    private Command screenCommand3;
    private Command screenCommand4;
    private Command screenCommand5;
    private Form form;
    private StringItem stringItem1;
    private StringItem stringItem;
    private StringItem stringItem2;
    private StringItem stringItem3;
    private TextField textField;
    private StringItem stringItem4;
    private StringItem stringItem5;
    private StringItem stringItem6;
//</editor-fold>//GEN-END:|fields|0|

    /**
     * The HelloMIDlet constructor.
     */
    public HelloMIDlet() {
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
        switchDisplayable(null, getForm());//GEN-LINE:|3-startMIDlet|1|3-postAction
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
        if (displayable == form) {//GEN-BEGIN:|7-commandAction|1|19-preAction
            if (command == exitCommand) {//GEN-END:|7-commandAction|1|19-preAction
                // write pre-action user code here
                exitMIDlet();//GEN-LINE:|7-commandAction|2|19-postAction
                // write post-action user code here
            }//GEN-BEGIN:|7-commandAction|3|7-postCommandAction
        }//GEN-END:|7-commandAction|3|7-postCommandAction
        // write post-action user code here
    }//GEN-BEGIN:|7-commandAction|4|
//</editor-fold>//GEN-END:|7-commandAction|4|

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
            form = new Form("Welcome", new Item[]{getStringItem(), getStringItem1(), getStringItem2(), getStringItem3(), getStringItem4(), getStringItem5(), getTextField(), getStringItem6()});//GEN-BEGIN:|14-getter|1|14-postInit
            form.addCommand(getExitCommand());
            form.setCommandListener(this);//GEN-END:|14-getter|1|14-postInit
            // write post-init user code here
        }//GEN-BEGIN:|14-getter|2|
        return form;
    }
//</editor-fold>//GEN-END:|14-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: stringItem1 ">//GEN-BEGIN:|24-getter|0|24-preInit
    /**
     * Returns an initialized instance of stringItem1 component.
     *
     * @return the initialized component instance
     */
    public StringItem getStringItem1() {
        if (stringItem1 == null) {//GEN-END:|24-getter|0|24-preInit
            // write pre-init user code here
            stringItem1 = new StringItem("", "Plus", Item.BUTTON);//GEN-BEGIN:|24-getter|1|24-postInit
            stringItem1.addCommand(getScreenCommand());
            stringItem1.setItemCommandListener(this);//GEN-END:|24-getter|1|24-postInit
            // write post-init user code here
        }//GEN-BEGIN:|24-getter|2|
        return stringItem1;
    }
//</editor-fold>//GEN-END:|24-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: stringItem ">//GEN-BEGIN:|25-getter|0|25-preInit
    /**
     * Returns an initialized instance of stringItem component.
     *
     * @return the initialized component instance
     */
    public StringItem getStringItem() {
        if (stringItem == null) {//GEN-END:|25-getter|0|25-preInit
            // write pre-init user code here
            stringItem = new StringItem("", "Minus", Item.BUTTON);//GEN-BEGIN:|25-getter|1|25-postInit
            stringItem.addCommand(getScreenCommand1());
            stringItem.setItemCommandListener(this);
            stringItem.setPreferredSize(-1, -1);//GEN-END:|25-getter|1|25-postInit
            // write post-init user code here
        }//GEN-BEGIN:|25-getter|2|
        return stringItem;
    }
//</editor-fold>//GEN-END:|25-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Method: commandAction for Items ">//GEN-BEGIN:|17-itemCommandAction|0|17-preItemCommandAction
    /**
     * Called by a system to indicated that a command has been invoked on a
     * particular item.
     *
     * @param command the Command that was invoked
     * @param displayable the Item where the command was invoked
     */
    public void commandAction(Command command, Item item) {//GEN-END:|17-itemCommandAction|0|17-preItemCommandAction
        // write pre-action user code here
        if (item == stringItem) {//GEN-BEGIN:|17-itemCommandAction|1|31-preAction
            if (command == screenCommand1) {//GEN-END:|17-itemCommandAction|1|31-preAction
                // write pre-action user code here
//GEN-LINE:|17-itemCommandAction|2|31-postAction
                if(firstSymbol){
                    try{
                    currentValue = Double.parseDouble(textField.getString());
                    currentCommand = 's';
                    stringItem6.setText("Minus");
                    firstSymbol = false;
                    textField.setString("");
                    } catch(Exception e){
                      textField.setString("Invalid");  
                    }
                }
                else {
                   if(textField.getString().length() == 0){
                       currentCommand = 's';
                       stringItem6.setText("Minus");
                   }
                   else{
                       try{
                       double tempValue = Double.parseDouble(textField.getString());
                        switch(currentCommand){
                            case 's': currentValue -= tempValue;
                            textField.setString(String.valueOf(currentValue));
                            currentCommand = 's'; break;
                            case 'p': currentValue += tempValue;
                            textField.setString(String.valueOf(currentValue));
                            currentCommand = 's'; break;
                            case 'd': currentValue /= tempValue;
                            textField.setString(String.valueOf(currentValue));
                            currentCommand = 's'; break;
                            case 'm': currentValue *= tempValue;
                            textField.setString(String.valueOf(currentValue));
                            currentCommand = 's'; break;
                            default : currentCommand = 's';
                            textField.setString("");
                            stringItem6.setText("Minus"); break;
                        }
                        }catch(Exception e){
                            textField.setString("Invalid");
                    }
                       currentCommand = 's';
                       stringItem6.setText("Minus");
                   }
                }
            }//GEN-BEGIN:|17-itemCommandAction|3|29-preAction
        } else if (item == stringItem1) {
            if (command == screenCommand) {//GEN-END:|17-itemCommandAction|3|29-preAction
                // write pre-action user code here
//GEN-LINE:|17-itemCommandAction|4|29-postAction
                if(firstSymbol){
                    try{
                    currentValue = Double.parseDouble(textField.getString());
                    currentCommand = 'p';
                    stringItem6.setText("Plus");
                    firstSymbol = false;
                    textField.setString("");
                    } catch(Exception e){
                      textField.setString("Invalid");  
                    }
                }
                else {
                   if(textField.getString().length() == 0){
                       currentCommand = 'p';
                       stringItem6.setText("Plus");
                   }
                   else{
                       try{
                       double tempValue = Double.parseDouble(textField.getString());
                        switch(currentCommand){
                            case 's': currentValue -= tempValue;
                            textField.setString(String.valueOf(currentValue));
                            currentCommand = 'p'; break;
                            case 'p': currentValue += tempValue;
                            textField.setString(String.valueOf(currentValue));
                            currentCommand = 'p'; break;
                            case 'd': currentValue /= tempValue;
                            textField.setString(String.valueOf(currentValue));
                            currentCommand = 'p'; break;
                            case 'm': currentValue *= tempValue;
                            textField.setString(String.valueOf(currentValue));
                            currentCommand = 'p'; break;
                            default : currentCommand = 'p';
                            textField.setString("");
                            stringItem6.setText("Plus"); break;
                        }
                        }catch(Exception e){
                            textField.setString("Invalid");
                    }
                       currentCommand = 'p';
                       stringItem6.setText("Plus");
                   }
                }
            }//GEN-BEGIN:|17-itemCommandAction|5|34-preAction
        } else if (item == stringItem2) {
            if (command == screenCommand2) {//GEN-END:|17-itemCommandAction|5|34-preAction
                // write pre-action user code here
//GEN-LINE:|17-itemCommandAction|6|34-postAction
               if(firstSymbol){
                    try{
                    currentValue = Double.parseDouble(textField.getString());
                    currentCommand = 'd';
                    stringItem6.setText("Divide");
                    firstSymbol = false;
                    textField.setString("");
                    } catch(Exception e){
                      textField.setString("Invalid");  
                    }
                }
                else {
                   if(textField.getString().length() == 0){
                       currentCommand = 'd';
                       stringItem6.setText("Divide");
                   }
                   else{
                       try{
                       double tempValue = Double.parseDouble(textField.getString());
                        switch(currentCommand){
                            case 's': currentValue -= tempValue;
                            textField.setString(String.valueOf(currentValue));
                            currentCommand = 'd'; break;
                            case 'p': currentValue += tempValue;
                            textField.setString(String.valueOf(currentValue));
                            currentCommand = 'd'; break;
                            case 'd': currentValue /= tempValue;
                            textField.setString(String.valueOf(currentValue));
                            currentCommand = 'd'; break;
                            case 'm': currentValue *= tempValue;
                            textField.setString(String.valueOf(currentValue));
                            currentCommand = 'd'; break;
                            default : currentCommand = 'd';
                            textField.setString("");
                            stringItem6.setText("Divide"); break;
                        }
                        }catch(Exception e){
                            textField.setString("Invalid");
                    }
                       currentCommand = 'd';
                       stringItem6.setText("Divide");
                   }
                }
            }//GEN-BEGIN:|17-itemCommandAction|7|37-preAction
        } else if (item == stringItem3) {
            if (command == screenCommand3) {//GEN-END:|17-itemCommandAction|7|37-preAction
                // write pre-action user code here
//GEN-LINE:|17-itemCommandAction|8|37-postAction
                if(firstSymbol){
                    try{
                    currentValue = Double.parseDouble(textField.getString());
                    currentCommand = 'm';
                    stringItem6.setText("Multiply");
                    firstSymbol = false;
                    textField.setString("");
                    } catch(Exception e){
                      textField.setString("Invalid");  
                    }
                }
                else {
                   if(textField.getString().length() == 0){
                       currentCommand = 'm';
                       stringItem6.setText("Multiply");
                   }
                   else{
                       try{
                       double tempValue = Double.parseDouble(textField.getString());
                        switch(currentCommand){
                            case 's': currentValue -= tempValue;
                            textField.setString(String.valueOf(currentValue));
                            currentCommand = 'm'; break;
                            case 'p': currentValue += tempValue;
                            textField.setString(String.valueOf(currentValue));
                            currentCommand = 'm'; break;
                            case 'd': currentValue /= tempValue;
                            textField.setString(String.valueOf(currentValue));
                            currentCommand = 'm'; break;
                            case 'm': currentValue *= tempValue;
                            textField.setString(String.valueOf(currentValue));
                            currentCommand = 'm'; break;
                            default : currentCommand = 'm';
                            textField.setString("");
                            stringItem6.setText("Multiply"); break;
                        }
                        }catch(Exception e){
                            textField.setString("Invalid");
                    }
                       currentCommand = 'm';
                       stringItem6.setText("Multiply");
                   }
                }
            }//GEN-BEGIN:|17-itemCommandAction|9|42-preAction
        } else if (item == stringItem4) {
            if (command == screenCommand4) {//GEN-END:|17-itemCommandAction|9|42-preAction
                // write pre-action user code here
//GEN-LINE:|17-itemCommandAction|10|42-postAction
               if(firstSymbol){
                    textField.setString("Please enter an operation");
                }
                else{
                    try{
                        double tempValue = Double.parseDouble(textField.getString());
                        switch(currentCommand){
                            case 's': currentValue -= tempValue;
                            textField.setString(String.valueOf(currentValue));
                            currentCommand = '0'; break;
                            case 'p': currentValue += tempValue;
                            textField.setString(String.valueOf(currentValue));
                            currentCommand = '0'; break;
                            case 'd': currentValue /= tempValue;
                            textField.setString(String.valueOf(currentValue));
                            currentCommand = '0'; break;
                            case 'm': currentValue *= tempValue;
                            textField.setString(String.valueOf(currentValue));
                            currentCommand = '0'; break;
                            default : break;
                        }
                        stringItem6.setText("");
                    }catch(Exception e){
                        textField.setString("Invalid");
                    }
                }
            }//GEN-BEGIN:|17-itemCommandAction|11|45-preAction
        } else if (item == stringItem5) {
            if (command == screenCommand5) {//GEN-END:|17-itemCommandAction|11|45-preAction
                // write pre-action user code here
//GEN-LINE:|17-itemCommandAction|12|45-postAction
                firstSymbol = true;
                currentValue = 0;
                currentCommand = '0';
            }//GEN-BEGIN:|17-itemCommandAction|13|17-postItemCommandAction
        }//GEN-END:|17-itemCommandAction|13|17-postItemCommandAction
        // write post-action user code here
    }//GEN-BEGIN:|17-itemCommandAction|14|
//</editor-fold>//GEN-END:|17-itemCommandAction|14|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: screenCommand ">//GEN-BEGIN:|28-getter|0|28-preInit
    /**
     * Returns an initialized instance of screenCommand component.
     *
     * @return the initialized component instance
     */
    public Command getScreenCommand() {
        if (screenCommand == null) {//GEN-END:|28-getter|0|28-preInit
            // write pre-init user code here
            screenCommand = new Command("Screen", Command.SCREEN, 0);//GEN-LINE:|28-getter|1|28-postInit
            // write post-init user code here
        }//GEN-BEGIN:|28-getter|2|
        return screenCommand;
    }
//</editor-fold>//GEN-END:|28-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: screenCommand1 ">//GEN-BEGIN:|30-getter|0|30-preInit
    /**
     * Returns an initialized instance of screenCommand1 component.
     *
     * @return the initialized component instance
     */
    public Command getScreenCommand1() {
        if (screenCommand1 == null) {//GEN-END:|30-getter|0|30-preInit
            // write pre-init user code here
            screenCommand1 = new Command("Screen", Command.SCREEN, 0);//GEN-LINE:|30-getter|1|30-postInit

        }//GEN-BEGIN:|30-getter|2|
        return screenCommand1;
    }
//</editor-fold>//GEN-END:|30-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: screenCommand2 ">//GEN-BEGIN:|33-getter|0|33-preInit
    /**
     * Returns an initialized instance of screenCommand2 component.
     *
     * @return the initialized component instance
     */
    public Command getScreenCommand2() {
        if (screenCommand2 == null) {//GEN-END:|33-getter|0|33-preInit
            // write pre-init user code here
            screenCommand2 = new Command("Screen", Command.SCREEN, 0);//GEN-LINE:|33-getter|1|33-postInit
            // write post-init user code here
        }//GEN-BEGIN:|33-getter|2|
        return screenCommand2;
    }
//</editor-fold>//GEN-END:|33-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: screenCommand3 ">//GEN-BEGIN:|36-getter|0|36-preInit
    /**
     * Returns an initialized instance of screenCommand3 component.
     *
     * @return the initialized component instance
     */
    public Command getScreenCommand3() {
        if (screenCommand3 == null) {//GEN-END:|36-getter|0|36-preInit
            // write pre-init user code here
            screenCommand3 = new Command("Screen", Command.SCREEN, 0);//GEN-LINE:|36-getter|1|36-postInit
            // write post-init user code here
        }//GEN-BEGIN:|36-getter|2|
        return screenCommand3;
    }
//</editor-fold>//GEN-END:|36-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: stringItem2 ">//GEN-BEGIN:|32-getter|0|32-preInit
    /**
     * Returns an initialized instance of stringItem2 component.
     *
     * @return the initialized component instance
     */
    public StringItem getStringItem2() {
        if (stringItem2 == null) {//GEN-END:|32-getter|0|32-preInit
            // write pre-init user code here
            stringItem2 = new StringItem("", "Divide", Item.BUTTON);//GEN-BEGIN:|32-getter|1|32-postInit
            stringItem2.addCommand(getScreenCommand2());
            stringItem2.setItemCommandListener(this);//GEN-END:|32-getter|1|32-postInit
            // write post-init user code here
        }//GEN-BEGIN:|32-getter|2|
        return stringItem2;
    }
//</editor-fold>//GEN-END:|32-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: stringItem3 ">//GEN-BEGIN:|35-getter|0|35-preInit
    /**
     * Returns an initialized instance of stringItem3 component.
     *
     * @return the initialized component instance
     */
    public StringItem getStringItem3() {
        if (stringItem3 == null) {//GEN-END:|35-getter|0|35-preInit
            // write pre-init user code here
            stringItem3 = new StringItem("", "Multiply", Item.BUTTON);//GEN-BEGIN:|35-getter|1|35-postInit
            stringItem3.addCommand(getScreenCommand3());
            stringItem3.setItemCommandListener(this);//GEN-END:|35-getter|1|35-postInit
            // write post-init user code here
        }//GEN-BEGIN:|35-getter|2|
        return stringItem3;
    }
//</editor-fold>//GEN-END:|35-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: textField ">//GEN-BEGIN:|38-getter|0|38-preInit
    /**
     * Returns an initialized instance of textField component.
     *
     * @return the initialized component instance
     */
    public TextField getTextField() {
        if (textField == null) {//GEN-END:|38-getter|0|38-preInit
            // write pre-init user code here
            textField = new TextField("", "", 32, TextField.ANY);//GEN-LINE:|38-getter|1|38-postInit
            // write post-init user code here
        }//GEN-BEGIN:|38-getter|2|
        return textField;
    }
//</editor-fold>//GEN-END:|38-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: stringItem4 ">//GEN-BEGIN:|40-getter|0|40-preInit
    /**
     * Returns an initialized instance of stringItem4 component.
     *
     * @return the initialized component instance
     */
    public StringItem getStringItem4() {
        if (stringItem4 == null) {//GEN-END:|40-getter|0|40-preInit
            // write pre-init user code here
            stringItem4 = new StringItem("", "Equals", Item.BUTTON);//GEN-BEGIN:|40-getter|1|40-postInit
            stringItem4.addCommand(getScreenCommand4());
            stringItem4.setItemCommandListener(this);//GEN-END:|40-getter|1|40-postInit
            // write post-init user code here
        }//GEN-BEGIN:|40-getter|2|
        return stringItem4;
    }
//</editor-fold>//GEN-END:|40-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: screenCommand4 ">//GEN-BEGIN:|41-getter|0|41-preInit
    /**
     * Returns an initialized instance of screenCommand4 component.
     *
     * @return the initialized component instance
     */
    public Command getScreenCommand4() {
        if (screenCommand4 == null) {//GEN-END:|41-getter|0|41-preInit
            // write pre-init user code here
            screenCommand4 = new Command("Screen", Command.SCREEN, 0);//GEN-LINE:|41-getter|1|41-postInit
            // write post-init user code here
        }//GEN-BEGIN:|41-getter|2|
        return screenCommand4;
    }
//</editor-fold>//GEN-END:|41-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: screenCommand5 ">//GEN-BEGIN:|44-getter|0|44-preInit
    /**
     * Returns an initialized instance of screenCommand5 component.
     *
     * @return the initialized component instance
     */
    public Command getScreenCommand5() {
        if (screenCommand5 == null) {//GEN-END:|44-getter|0|44-preInit
            // write pre-init user code here
            screenCommand5 = new Command("Screen", Command.SCREEN, 0);//GEN-LINE:|44-getter|1|44-postInit
            // write post-init user code here
        }//GEN-BEGIN:|44-getter|2|
        return screenCommand5;
    }
//</editor-fold>//GEN-END:|44-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: stringItem5 ">//GEN-BEGIN:|43-getter|0|43-preInit
    /**
     * Returns an initialized instance of stringItem5 component.
     *
     * @return the initialized component instance
     */
    public StringItem getStringItem5() {
        if (stringItem5 == null) {//GEN-END:|43-getter|0|43-preInit
            // write pre-init user code here
            stringItem5 = new StringItem("", "CE", Item.BUTTON);//GEN-BEGIN:|43-getter|1|43-postInit
            stringItem5.addCommand(getScreenCommand5());
            stringItem5.setItemCommandListener(this);//GEN-END:|43-getter|1|43-postInit
            // write post-init user code here
        }//GEN-BEGIN:|43-getter|2|
        return stringItem5;
    }
//</editor-fold>//GEN-END:|43-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: stringItem6 ">//GEN-BEGIN:|46-getter|0|46-preInit
    /**
     * Returns an initialized instance of stringItem6 component.
     *
     * @return the initialized component instance
     */
    public StringItem getStringItem6() {
        if (stringItem6 == null) {//GEN-END:|46-getter|0|46-preInit
            // write pre-init user code here
            stringItem6 = new StringItem("Current Operation is :", "");//GEN-LINE:|46-getter|1|46-postInit
            // write post-init user code here
        }//GEN-BEGIN:|46-getter|2|
        return stringItem6;
    }
//</editor-fold>//GEN-END:|46-getter|2|

    /**
     * Returns a display instance.
     *
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
     * Called when MIDlet is started. Checks whether the MIDlet have been
     * already started and initialize/starts or resumes the MIDlet.
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
     *
     * @param unconditional if true, then the MIDlet has to be unconditionally
     * terminated and all resources has to be released.
     */
    public void destroyApp(boolean unconditional) {
    }
}

