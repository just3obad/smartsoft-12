/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hello;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import javax.microedition.io.Connector;
import javax.microedition.io.HttpConnection;
import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;

/**
 * @author Administrator
 */
public class HelloMIDlet extends MIDlet implements CommandListener {
    
    private boolean midletPaused = false;
    //<editor-fold defaultstate="collapsed" desc=" Generated Fields ">//GEN-BEGIN:|fields|0|
    private Command exitCommand;
    private Command okCommand;
    private Command exitCommand1;
    private Command itemCommand;
    private Command okCommand1;
    private Command backCommand;
    private Command okCommand2;
    private Command backCommand1;
    private Form form;
    private StringItem stringItem;
    private Form RegisterScreen;
    private TextField textField4;
    private TextField textField2;
    private TextField textField3;
    private Form LoginScreen;
    private TextField textField1;
    private TextField textField;
    private Form Profile;
    private TextField textField7;
    private TextField textField8;
    private TextField textField5;
    private TextField textField6;
    private DateField dateField;
    private TextField textField9;
    private Alert Error;
    private Alert Saved;
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
//GEN-LINE:|3-startMIDlet|1|3-postAction
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
        if (displayable == LoginScreen) {//GEN-BEGIN:|7-commandAction|1|29-preAction
            if (command == exitCommand1) {//GEN-END:|7-commandAction|1|29-preAction
                // write pre-action user code here
//GEN-LINE:|7-commandAction|2|29-postAction
                // write post-action user code here
            } else if (command == itemCommand) {//GEN-LINE:|7-commandAction|3|31-preAction
                // write pre-action user code here
//GEN-LINE:|7-commandAction|4|31-postAction
                // write post-action user code here
            } else if (command == okCommand) {//GEN-LINE:|7-commandAction|5|27-preAction
                // write pre-action user code here
//GEN-LINE:|7-commandAction|6|27-postAction
                // write post-action user code here
            }//GEN-BEGIN:|7-commandAction|7|41-preAction
        } else if (displayable == Profile) {
            if (command == backCommand1) {//GEN-END:|7-commandAction|7|41-preAction
                // write pre-action user code here
//GEN-LINE:|7-commandAction|8|41-postAction
                // write post-action user code here
            } else if (command == okCommand2) {//GEN-LINE:|7-commandAction|9|43-preAction
                // write pre-action user code here
//GEN-LINE:|7-commandAction|10|43-postAction
                // write post-action user code here
            }//GEN-BEGIN:|7-commandAction|11|38-preAction
        } else if (displayable == RegisterScreen) {
            if (command == backCommand) {//GEN-END:|7-commandAction|11|38-preAction
                // write pre-action user code here
//GEN-LINE:|7-commandAction|12|38-postAction
                // write post-action user code here
            } else if (command == okCommand1) {//GEN-LINE:|7-commandAction|13|36-preAction
                // write pre-action user code here
//GEN-LINE:|7-commandAction|14|36-postAction
                // write post-action user code here
                //Register
        HttpConnection httpConn = null;
        OutputStream os = null;
                try {
        //Change IP accordingly
        httpConn = (HttpConnection) Connector.open("http://192.168.1.107:3000/users/");
        //Request method has to be POST
        httpConn.setRequestMethod(HttpConnection.POST);
        httpConn.setRequestProperty("User-Agent",
                "Profile/MIDP-1.0 Confirguration/CLDC-1.0");
        httpConn.setRequestProperty("Accept_Language", "en-US");
        //Content-Type is must to pass parameters in POST Request must be application/json
        httpConn.setRequestProperty("Content-Type", "application/json");
        // JSON String that you will send containing the attributes needed for sign up. 
        String dataToBeSend = "{\"created_at\":\"nil\",\"email\":\"menisy@abfcge.com\",\"name\":\"menisy\",\"updated_at\":\"nil\"}";
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
            }//GEN-BEGIN:|7-commandAction|15|19-preAction
        } else if (displayable == form) {
            if (command == exitCommand) {//GEN-END:|7-commandAction|15|19-preAction
                // write pre-action user code here
//GEN-LINE:|7-commandAction|16|19-postAction
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

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: okCommand ">//GEN-BEGIN:|26-getter|0|26-preInit
    /**
     * Returns an initiliazed instance of okCommand component.
     * @return the initialized component instance
     */
    public Command getOkCommand() {
        if (okCommand == null) {//GEN-END:|26-getter|0|26-preInit
            // write pre-init user code here
            okCommand = new Command("Ok", Command.OK, 0);//GEN-LINE:|26-getter|1|26-postInit
            // write post-init user code here
        }//GEN-BEGIN:|26-getter|2|
        return okCommand;
    }
    //</editor-fold>//GEN-END:|26-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: exitCommand1 ">//GEN-BEGIN:|28-getter|0|28-preInit
    /**
     * Returns an initiliazed instance of exitCommand1 component.
     * @return the initialized component instance
     */
    public Command getExitCommand1() {
        if (exitCommand1 == null) {//GEN-END:|28-getter|0|28-preInit
            // write pre-init user code here
            exitCommand1 = new Command("Exit", Command.EXIT, 0);//GEN-LINE:|28-getter|1|28-postInit
            // write post-init user code here
        }//GEN-BEGIN:|28-getter|2|
        return exitCommand1;
    }
    //</editor-fold>//GEN-END:|28-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: itemCommand ">//GEN-BEGIN:|30-getter|0|30-preInit
    /**
     * Returns an initiliazed instance of itemCommand component.
     * @return the initialized component instance
     */
    public Command getItemCommand() {
        if (itemCommand == null) {//GEN-END:|30-getter|0|30-preInit
            // write pre-init user code here
            itemCommand = new Command("Item", Command.ITEM, 0);//GEN-LINE:|30-getter|1|30-postInit
            // write post-init user code here
        }//GEN-BEGIN:|30-getter|2|
        return itemCommand;
    }
    //</editor-fold>//GEN-END:|30-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: LoginScreen ">//GEN-BEGIN:|22-getter|0|22-preInit
    /**
     * Returns an initiliazed instance of LoginScreen component.
     * @return the initialized component instance
     */
    public Form getLoginScreen() {
        if (LoginScreen == null) {//GEN-END:|22-getter|0|22-preInit
            // write pre-init user code here
            LoginScreen = new Form("Login Screen", new Item[] { getTextField(), getTextField1() });//GEN-BEGIN:|22-getter|1|22-postInit
            LoginScreen.addCommand(getOkCommand());
            LoginScreen.addCommand(getExitCommand1());
            LoginScreen.addCommand(getItemCommand());
            LoginScreen.setCommandListener(this);//GEN-END:|22-getter|1|22-postInit
            // write post-init user code here
        }//GEN-BEGIN:|22-getter|2|
        return LoginScreen;
    }
    //</editor-fold>//GEN-END:|22-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: textField ">//GEN-BEGIN:|24-getter|0|24-preInit
    /**
     * Returns an initiliazed instance of textField component.
     * @return the initialized component instance
     */
    public TextField getTextField() {
        if (textField == null) {//GEN-END:|24-getter|0|24-preInit
            // write pre-init user code here
            textField = new TextField("Email", null, 32, TextField.ANY);//GEN-LINE:|24-getter|1|24-postInit
            // write post-init user code here
        }//GEN-BEGIN:|24-getter|2|
        return textField;
    }
    //</editor-fold>//GEN-END:|24-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: textField1 ">//GEN-BEGIN:|25-getter|0|25-preInit
    /**
     * Returns an initiliazed instance of textField1 component.
     * @return the initialized component instance
     */
    public TextField getTextField1() {
        if (textField1 == null) {//GEN-END:|25-getter|0|25-preInit
            // write pre-init user code here
            textField1 = new TextField("Password", null, 32, TextField.ANY);//GEN-LINE:|25-getter|1|25-postInit
            // write post-init user code here
        }//GEN-BEGIN:|25-getter|2|
        return textField1;
    }
    //</editor-fold>//GEN-END:|25-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: RegisterScreen ">//GEN-BEGIN:|23-getter|0|23-preInit
    /**
     * Returns an initiliazed instance of RegisterScreen component.
     * @return the initialized component instance
     */
    public Form getRegisterScreen() {
        if (RegisterScreen == null) {//GEN-END:|23-getter|0|23-preInit
            // write pre-init user code here
            RegisterScreen = new Form("RegisterScreen", new Item[] { getTextField2(), getTextField3(), getTextField4() });//GEN-BEGIN:|23-getter|1|23-postInit
            RegisterScreen.addCommand(getOkCommand1());
            RegisterScreen.addCommand(getBackCommand());
            RegisterScreen.setCommandListener(this);//GEN-END:|23-getter|1|23-postInit
            // write post-init user code here
        }//GEN-BEGIN:|23-getter|2|
        return RegisterScreen;
    }
    //</editor-fold>//GEN-END:|23-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: okCommand1 ">//GEN-BEGIN:|35-getter|0|35-preInit
    /**
     * Returns an initiliazed instance of okCommand1 component.
     * @return the initialized component instance
     */
    public Command getOkCommand1() {
        if (okCommand1 == null) {//GEN-END:|35-getter|0|35-preInit
            // write pre-init user code here
            okCommand1 = new Command("Ok", Command.OK, 0);//GEN-LINE:|35-getter|1|35-postInit
            // write post-init user code here
        }//GEN-BEGIN:|35-getter|2|
        return okCommand1;
    }
    //</editor-fold>//GEN-END:|35-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: backCommand ">//GEN-BEGIN:|37-getter|0|37-preInit
    /**
     * Returns an initiliazed instance of backCommand component.
     * @return the initialized component instance
     */
    public Command getBackCommand() {
        if (backCommand == null) {//GEN-END:|37-getter|0|37-preInit
            // write pre-init user code here
            backCommand = new Command("Back", Command.BACK, 0);//GEN-LINE:|37-getter|1|37-postInit
            // write post-init user code here
        }//GEN-BEGIN:|37-getter|2|
        return backCommand;
    }
    //</editor-fold>//GEN-END:|37-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: textField2 ">//GEN-BEGIN:|32-getter|0|32-preInit
    /**
     * Returns an initiliazed instance of textField2 component.
     * @return the initialized component instance
     */
    public TextField getTextField2() {
        if (textField2 == null) {//GEN-END:|32-getter|0|32-preInit
            // write pre-init user code here
            textField2 = new TextField("Email", null, 32, TextField.ANY);//GEN-LINE:|32-getter|1|32-postInit
            // write post-init user code here
        }//GEN-BEGIN:|32-getter|2|
        return textField2;
    }
    //</editor-fold>//GEN-END:|32-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: textField3 ">//GEN-BEGIN:|33-getter|0|33-preInit
    /**
     * Returns an initiliazed instance of textField3 component.
     * @return the initialized component instance
     */
    public TextField getTextField3() {
        if (textField3 == null) {//GEN-END:|33-getter|0|33-preInit
            // write pre-init user code here
            textField3 = new TextField("Password", null, 32, TextField.ANY);//GEN-LINE:|33-getter|1|33-postInit
            // write post-init user code here
        }//GEN-BEGIN:|33-getter|2|
        return textField3;
    }
    //</editor-fold>//GEN-END:|33-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: textField4 ">//GEN-BEGIN:|34-getter|0|34-preInit
    /**
     * Returns an initiliazed instance of textField4 component.
     * @return the initialized component instance
     */
    public TextField getTextField4() {
        if (textField4 == null) {//GEN-END:|34-getter|0|34-preInit
            // write pre-init user code here
            textField4 = new TextField("Confirm Password", null, 32, TextField.ANY);//GEN-LINE:|34-getter|1|34-postInit
            // write post-init user code here
        }//GEN-BEGIN:|34-getter|2|
        return textField4;
    }
    //</editor-fold>//GEN-END:|34-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: backCommand1 ">//GEN-BEGIN:|40-getter|0|40-preInit
    /**
     * Returns an initiliazed instance of backCommand1 component.
     * @return the initialized component instance
     */
    public Command getBackCommand1() {
        if (backCommand1 == null) {//GEN-END:|40-getter|0|40-preInit
            // write pre-init user code here
            backCommand1 = new Command("Back", Command.BACK, 0);//GEN-LINE:|40-getter|1|40-postInit
            // write post-init user code here
        }//GEN-BEGIN:|40-getter|2|
        return backCommand1;
    }
    //</editor-fold>//GEN-END:|40-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: okCommand2 ">//GEN-BEGIN:|42-getter|0|42-preInit
    /**
     * Returns an initiliazed instance of okCommand2 component.
     * @return the initialized component instance
     */
    public Command getOkCommand2() {
        if (okCommand2 == null) {//GEN-END:|42-getter|0|42-preInit
            // write pre-init user code here
            okCommand2 = new Command("Ok", Command.OK, 0);//GEN-LINE:|42-getter|1|42-postInit
            // write post-init user code here
        }//GEN-BEGIN:|42-getter|2|
        return okCommand2;
    }
    //</editor-fold>//GEN-END:|42-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: Profile ">//GEN-BEGIN:|39-getter|0|39-preInit
    /**
     * Returns an initiliazed instance of Profile component.
     * @return the initialized component instance
     */
    public Form getProfile() {
        if (Profile == null) {//GEN-END:|39-getter|0|39-preInit
            // write pre-init user code here
            Profile = new Form("form1", new Item[] { getTextField5(), getTextField6(), getTextField7(), getTextField8(), getTextField9(), getDateField() });//GEN-BEGIN:|39-getter|1|39-postInit
            Profile.addCommand(getBackCommand1());
            Profile.addCommand(getOkCommand2());
            Profile.setCommandListener(this);//GEN-END:|39-getter|1|39-postInit
            // write post-init user code here
        }//GEN-BEGIN:|39-getter|2|
        return Profile;
    }
    //</editor-fold>//GEN-END:|39-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: textField5 ">//GEN-BEGIN:|44-getter|0|44-preInit
    /**
     * Returns an initiliazed instance of textField5 component.
     * @return the initialized component instance
     */
    public TextField getTextField5() {
        if (textField5 == null) {//GEN-END:|44-getter|0|44-preInit
            // write pre-init user code here
            textField5 = new TextField("FirsName", null, 32, TextField.ANY);//GEN-LINE:|44-getter|1|44-postInit
            // write post-init user code here
        }//GEN-BEGIN:|44-getter|2|
        return textField5;
    }
    //</editor-fold>//GEN-END:|44-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: textField6 ">//GEN-BEGIN:|45-getter|0|45-preInit
    /**
     * Returns an initiliazed instance of textField6 component.
     * @return the initialized component instance
     */
    public TextField getTextField6() {
        if (textField6 == null) {//GEN-END:|45-getter|0|45-preInit
            // write pre-init user code here
            textField6 = new TextField("LastName", null, 32, TextField.ANY);//GEN-LINE:|45-getter|1|45-postInit
            // write post-init user code here
        }//GEN-BEGIN:|45-getter|2|
        return textField6;
    }
    //</editor-fold>//GEN-END:|45-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: textField7 ">//GEN-BEGIN:|46-getter|0|46-preInit
    /**
     * Returns an initiliazed instance of textField7 component.
     * @return the initialized component instance
     */
    public TextField getTextField7() {
        if (textField7 == null) {//GEN-END:|46-getter|0|46-preInit
            // write pre-init user code here
            textField7 = new TextField("UserName", null, 32, TextField.ANY);//GEN-LINE:|46-getter|1|46-postInit
            // write post-init user code here
        }//GEN-BEGIN:|46-getter|2|
        return textField7;
    }
    //</editor-fold>//GEN-END:|46-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: textField8 ">//GEN-BEGIN:|47-getter|0|47-preInit
    /**
     * Returns an initiliazed instance of textField8 component.
     * @return the initialized component instance
     */
    public TextField getTextField8() {
        if (textField8 == null) {//GEN-END:|47-getter|0|47-preInit
            // write pre-init user code here
            textField8 = new TextField("New Password", null, 32, TextField.ANY | TextField.PASSWORD);//GEN-LINE:|47-getter|1|47-postInit
            // write post-init user code here
        }//GEN-BEGIN:|47-getter|2|
        return textField8;
    }
    //</editor-fold>//GEN-END:|47-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: textField9 ">//GEN-BEGIN:|48-getter|0|48-preInit
    /**
     * Returns an initiliazed instance of textField9 component.
     * @return the initialized component instance
     */
    public TextField getTextField9() {
        if (textField9 == null) {//GEN-END:|48-getter|0|48-preInit
            // write pre-init user code here
            textField9 = new TextField("Confirm New Password", null, 32, TextField.ANY | TextField.PASSWORD);//GEN-LINE:|48-getter|1|48-postInit
            // write post-init user code here
        }//GEN-BEGIN:|48-getter|2|
        return textField9;
    }
    //</editor-fold>//GEN-END:|48-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: dateField ">//GEN-BEGIN:|49-getter|0|49-preInit
    /**
     * Returns an initiliazed instance of dateField component.
     * @return the initialized component instance
     */
    public DateField getDateField() {
        if (dateField == null) {//GEN-END:|49-getter|0|49-preInit
            // write pre-init user code here
            dateField = new DateField("Date of Birth", DateField.DATE_TIME);//GEN-BEGIN:|49-getter|1|49-postInit
            dateField.setDate(new java.util.Date(System.currentTimeMillis()));//GEN-END:|49-getter|1|49-postInit
            // write post-init user code here
        }//GEN-BEGIN:|49-getter|2|
        return dateField;
    }
    //</editor-fold>//GEN-END:|49-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: Saved ">//GEN-BEGIN:|50-getter|0|50-preInit
    /**
     * Returns an initiliazed instance of Saved component.
     * @return the initialized component instance
     */
    public Alert getSaved() {
        if (Saved == null) {//GEN-END:|50-getter|0|50-preInit
            // write pre-init user code here
            Saved = new Alert("alert", null, null, AlertType.CONFIRMATION);//GEN-BEGIN:|50-getter|1|50-postInit
            Saved.setTimeout(Alert.FOREVER);//GEN-END:|50-getter|1|50-postInit
            // write post-init user code here
        }//GEN-BEGIN:|50-getter|2|
        return Saved;
    }
    //</editor-fold>//GEN-END:|50-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: Error ">//GEN-BEGIN:|51-getter|0|51-preInit
    /**
     * Returns an initiliazed instance of Error component.
     * @return the initialized component instance
     */
    public Alert getError() {
        if (Error == null) {//GEN-END:|51-getter|0|51-preInit
            // write pre-init user code here
            Error = new Alert("alert1", null, null, AlertType.ERROR);//GEN-BEGIN:|51-getter|1|51-postInit
            Error.setTimeout(Alert.FOREVER);//GEN-END:|51-getter|1|51-postInit
            // write post-init user code here
        }//GEN-BEGIN:|51-getter|2|
        return Error;
    }
    //</editor-fold>//GEN-END:|51-getter|2|

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
