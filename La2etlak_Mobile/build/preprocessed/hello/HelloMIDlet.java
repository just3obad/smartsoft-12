/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hello;

import java.io.InputStream;
import java.io.OutputStream;
import javax.microedition.io.ConnectionNotFoundException;
import javax.microedition.io.Connector;
import javax.microedition.io.HttpConnection;
import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;

/**
 * @author Kiro
 */
public class HelloMIDlet extends MIDlet implements CommandListener {

    private boolean midletPaused = false;
    public HttpConnection httpConn;
    public String SERVER_IP = "192.168.1.5";
    public String PORT = "3000";
    public InputStream inputStream;
    public OutputStream outputStream;
    public String response;
//<editor-fold defaultstate="collapsed" desc=" Generated Fields ">//GEN-BEGIN:|fields|0|
    private Command exitCommand;
    private Command loginCommand;
    private Command registerCommand;
    private Command LoginBack;
    private Command RegisterBack;
    private Command LoginOk;
    private Command RegisterOk;
    private Form form;
    private StringItem stringItem;
    private Form Login;
    private TextField loginPassword;
    private TextField loginEmail;
    private Form Register;
    private TextField registerEmail;
    private TextField registerPassword;
    private TextField registerConfirm;
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
        
        String url = "http://"+"192.168.1.9"+":3000/dummyLogin";                                   
        try {
              platformRequest(url);
                              
            } catch (ConnectionNotFoundException ex) {
              ex.printStackTrace();
            }
        if(true) return;
      //  switchDisplayable(null, getForm());                                        
        // write post-action user code here
    
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
        if (displayable == Login) {//GEN-BEGIN:|7-commandAction|1|31-preAction
            if (command == LoginBack) {//GEN-END:|7-commandAction|1|31-preAction
                // write pre-action user code here
                switchDisplayable(null, getForm());//GEN-LINE:|7-commandAction|2|31-postAction
                // write post-action user code here
            } else if (command == LoginOk) {//GEN-LINE:|7-commandAction|3|33-preAction
                // write pre-action user code here
                httpPost("{\"user_session\":{\"email\":\"" + getLoginEmail().getString() + "\",\"password\":\"" + getLoginPassword().getString() + "\"}}","http://" + SERVER_IP + ":" + PORT + "/requestToken");
                 
                if (!response.equalsIgnoreCase("false")) {
                    String url = "http://" + SERVER_IP + ":" + PORT + "/login?token=" + response;
                    try {
                        platformRequest(url);

                    } catch (ConnectionNotFoundException ex) {
                        ex.printStackTrace();
                    }
                    if (true) {
                        return;
                    }
                    switchDisplayable(null, getForm());
                }
//GEN-LINE:|7-commandAction|4|33-postAction
                // write post-action user code here
            }//GEN-BEGIN:|7-commandAction|5|35-preAction
        } else if (displayable == Register) {
            if (command == RegisterBack) {//GEN-END:|7-commandAction|5|35-preAction
                // write pre-action user code here
                switchDisplayable(null, getForm());//GEN-LINE:|7-commandAction|6|35-postAction
                // write post-action user code here
            } else if (command == RegisterOk) {//GEN-LINE:|7-commandAction|7|37-preAction
                
                httpPost("{\"user\":{\"email\":\"" + getRegisterEmail().getString() + "\",\"password\":\"" + getRegisterPassword().getString() + "\",\"password_confirmation\":\"" + getRegisterConfirm().getString() + "\"}}","http://" + SERVER_IP + ":" + PORT + "/register");
                
//GEN-LINE:|7-commandAction|8|37-postAction
                // write post-action user code here
            }//GEN-BEGIN:|7-commandAction|9|19-preAction
        } else if (displayable == form) {
            if (command == exitCommand) {//GEN-END:|7-commandAction|9|19-preAction
                // write pre-action user code here
                exitMIDlet();//GEN-LINE:|7-commandAction|10|19-postAction
                // write post-action user code here
            } else if (command == loginCommand) {//GEN-LINE:|7-commandAction|11|23-preAction
                // write pre-action user code here
                switchDisplayable(null, getLogin());//GEN-LINE:|7-commandAction|12|23-postAction
                // write post-action user code here
            } else if (command == registerCommand) {//GEN-LINE:|7-commandAction|13|25-preAction
                // write pre-action user code here
                switchDisplayable(null, getRegister());//GEN-LINE:|7-commandAction|14|25-postAction
                // write post-action user code here
            }//GEN-BEGIN:|7-commandAction|15|7-postCommandAction
        }//GEN-END:|7-commandAction|15|7-postCommandAction
        // write post-action user code here
    }//GEN-BEGIN:|7-commandAction|16|
//</editor-fold>//GEN-END:|7-commandAction|16|

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
            form = new Form("Welcome", new Item[]{getStringItem()});//GEN-BEGIN:|14-getter|1|14-postInit
            form.addCommand(getExitCommand());
            form.addCommand(getLoginCommand());
            form.addCommand(getRegisterCommand());
            form.setCommandListener(this);//GEN-END:|14-getter|1|14-postInit
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

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: loginCommand ">//GEN-BEGIN:|22-getter|0|22-preInit
    /**
     * Returns an initialized instance of loginCommand component.
     *
     * @return the initialized component instance
     */
    public Command getLoginCommand() {
        if (loginCommand == null) {//GEN-END:|22-getter|0|22-preInit
            // write pre-init user code here
            loginCommand = new Command("Login", Command.OK, 0);//GEN-LINE:|22-getter|1|22-postInit
            // write post-init user code here
        }//GEN-BEGIN:|22-getter|2|
        return loginCommand;
    }
//</editor-fold>//GEN-END:|22-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: registerCommand ">//GEN-BEGIN:|24-getter|0|24-preInit
    /**
     * Returns an initialized instance of registerCommand component.
     *
     * @return the initialized component instance
     */
    public Command getRegisterCommand() {
        if (registerCommand == null) {//GEN-END:|24-getter|0|24-preInit
            // write pre-init user code here
            registerCommand = new Command("Register", Command.SCREEN, 0);//GEN-LINE:|24-getter|1|24-postInit
            // write post-init user code here
        }//GEN-BEGIN:|24-getter|2|
        return registerCommand;
    }
//</editor-fold>//GEN-END:|24-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: LoginBack ">//GEN-BEGIN:|30-getter|0|30-preInit
    /**
     * Returns an initialized instance of LoginBack component.
     *
     * @return the initialized component instance
     */
    public Command getLoginBack() {
        if (LoginBack == null) {//GEN-END:|30-getter|0|30-preInit
            // write pre-init user code here
            LoginBack = new Command("Back", Command.BACK, 0);//GEN-LINE:|30-getter|1|30-postInit
            // write post-init user code here
        }//GEN-BEGIN:|30-getter|2|
        return LoginBack;
    }
//</editor-fold>//GEN-END:|30-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: LoginOk ">//GEN-BEGIN:|32-getter|0|32-preInit
    /**
     * Returns an initialized instance of LoginOk component.
     *
     * @return the initialized component instance
     */
    public Command getLoginOk() {
        if (LoginOk == null) {//GEN-END:|32-getter|0|32-preInit
            // write pre-init user code here
            LoginOk = new Command("Login", Command.OK, 0);//GEN-LINE:|32-getter|1|32-postInit
            // write post-init user code here
        }//GEN-BEGIN:|32-getter|2|
        return LoginOk;
    }
//</editor-fold>//GEN-END:|32-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: RegisterBack ">//GEN-BEGIN:|34-getter|0|34-preInit
    /**
     * Returns an initialized instance of RegisterBack component.
     *
     * @return the initialized component instance
     */
    public Command getRegisterBack() {
        if (RegisterBack == null) {//GEN-END:|34-getter|0|34-preInit
            // write pre-init user code here
            RegisterBack = new Command("Back", Command.BACK, 0);//GEN-LINE:|34-getter|1|34-postInit
            // write post-init user code here
        }//GEN-BEGIN:|34-getter|2|
        return RegisterBack;
    }
//</editor-fold>//GEN-END:|34-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: RegisterOk ">//GEN-BEGIN:|36-getter|0|36-preInit
    /**
     * Returns an initialized instance of RegisterOk component.
     *
     * @return the initialized component instance
     */
    public Command getRegisterOk() {
        if (RegisterOk == null) {//GEN-END:|36-getter|0|36-preInit
            // write pre-init user code here
            RegisterOk = new Command("Ok", Command.OK, 0);//GEN-LINE:|36-getter|1|36-postInit
            // write post-init user code here
        }//GEN-BEGIN:|36-getter|2|
        return RegisterOk;
    }
//</editor-fold>//GEN-END:|36-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: Register ">//GEN-BEGIN:|26-getter|0|26-preInit
    /**
     * Returns an initialized instance of Register component.
     *
     * @return the initialized component instance
     */
    public Form getRegister() {
        if (Register == null) {//GEN-END:|26-getter|0|26-preInit
            // write pre-init user code here
            Register = new Form("Login", new Item[]{getRegisterEmail(), getRegisterPassword(), getRegisterConfirm()});//GEN-BEGIN:|26-getter|1|26-postInit
            Register.addCommand(getRegisterBack());
            Register.addCommand(getRegisterOk());
            Register.setCommandListener(this);//GEN-END:|26-getter|1|26-postInit
            // write post-init user code here
        }//GEN-BEGIN:|26-getter|2|
        return Register;
    }
//</editor-fold>//GEN-END:|26-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: registerEmail ">//GEN-BEGIN:|42-getter|0|42-preInit
    /**
     * Returns an initialized instance of registerEmail component.
     *
     * @return the initialized component instance
     */
    public TextField getRegisterEmail() {
        if (registerEmail == null) {//GEN-END:|42-getter|0|42-preInit
            // write pre-init user code here
            registerEmail = new TextField("Email", null, 50, TextField.EMAILADDR);//GEN-BEGIN:|42-getter|1|42-postInit
            registerEmail.setLayout(ImageItem.LAYOUT_DEFAULT);//GEN-END:|42-getter|1|42-postInit
            // write post-init user code here
        }//GEN-BEGIN:|42-getter|2|
        return registerEmail;
    }
//</editor-fold>//GEN-END:|42-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: registerPassword ">//GEN-BEGIN:|43-getter|0|43-preInit
    /**
     * Returns an initialized instance of registerPassword component.
     *
     * @return the initialized component instance
     */
    public TextField getRegisterPassword() {
        if (registerPassword == null) {//GEN-END:|43-getter|0|43-preInit
            // write pre-init user code here
            registerPassword = new TextField("Password", null, 32, TextField.ANY | TextField.PASSWORD);//GEN-BEGIN:|43-getter|1|43-postInit
            registerPassword.setLayout(ImageItem.LAYOUT_DEFAULT);//GEN-END:|43-getter|1|43-postInit
            // write post-init user code here
        }//GEN-BEGIN:|43-getter|2|
        return registerPassword;
    }
//</editor-fold>//GEN-END:|43-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: registerConfirm ">//GEN-BEGIN:|44-getter|0|44-preInit
    /**
     * Returns an initialized instance of registerConfirm component.
     *
     * @return the initialized component instance
     */
    public TextField getRegisterConfirm() {
        if (registerConfirm == null) {//GEN-END:|44-getter|0|44-preInit
            // write pre-init user code here
            registerConfirm = new TextField("Password Confirmation", null, 32, TextField.ANY | TextField.PASSWORD);//GEN-LINE:|44-getter|1|44-postInit
            // write post-init user code here
        }//GEN-BEGIN:|44-getter|2|
        return registerConfirm;
    }
//</editor-fold>//GEN-END:|44-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: Login ">//GEN-BEGIN:|27-getter|0|27-preInit
    /**
     * Returns an initialized instance of Login component.
     *
     * @return the initialized component instance
     */
    public Form getLogin() {
        if (Login == null) {//GEN-END:|27-getter|0|27-preInit
            // write pre-init user code here
            Login = new Form("Login", new Item[]{getLoginEmail(), getLoginPassword()});//GEN-BEGIN:|27-getter|1|27-postInit
            Login.addCommand(getLoginBack());
            Login.addCommand(getLoginOk());
            Login.setCommandListener(this);//GEN-END:|27-getter|1|27-postInit
            // write post-init user code here
        }//GEN-BEGIN:|27-getter|2|
        return Login;
    }
//</editor-fold>//GEN-END:|27-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: loginEmail ">//GEN-BEGIN:|28-getter|0|28-preInit
    /**
     * Returns an initialized instance of loginEmail component.
     *
     * @return the initialized component instance
     */
    public TextField getLoginEmail() {
        if (loginEmail == null) {//GEN-END:|28-getter|0|28-preInit
            // write pre-init user code here
            loginEmail = new TextField("Email", null, 50, TextField.EMAILADDR | TextField.NON_PREDICTIVE);//GEN-BEGIN:|28-getter|1|28-postInit
            loginEmail.setInitialInputMode("UCB_BASIC_LATIN");//GEN-END:|28-getter|1|28-postInit
            // write post-init user code here
        }//GEN-BEGIN:|28-getter|2|
        return loginEmail;
    }
//</editor-fold>//GEN-END:|28-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: loginPassword ">//GEN-BEGIN:|29-getter|0|29-preInit
    /**
     * Returns an initialized instance of loginPassword component.
     *
     * @return the initialized component instance
     */
    public TextField getLoginPassword() {
        if (loginPassword == null) {//GEN-END:|29-getter|0|29-preInit
            // write pre-init user code here
            loginPassword = new TextField("Password", null, 32, TextField.ANY | TextField.PASSWORD);//GEN-LINE:|29-getter|1|29-postInit
            // write post-init user code here
        }//GEN-BEGIN:|29-getter|2|
        return loginPassword;
    }
//</editor-fold>//GEN-END:|29-getter|2|

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

    /*
     * This method makes a post http request to the URL @postURL and it sends @data along with the post
     */
    public void httpPost(String data, String postURL) {

        httpConn = null;
        try {
            //Change IP accordingly
            httpConn = (HttpConnection) Connector.open(postURL);
            //Request method has to be POST
            httpConn.setRequestMethod(HttpConnection.POST);
            httpConn.setRequestProperty("User-Agent", "Profile/MIDP-1.0 Confirguration/CLDC-1.0");
            httpConn.setRequestProperty("Accept_Language", "en-US");
            //Content-Type is must to pass parameters in POST Request must be application/json
            httpConn.setRequestProperty("Content-Type", "application/json");
            // JSON String that you will send containing the attributes needed for sign up.
            String dataToBeSend = data;
            httpConn.setRequestProperty("Content-Length", "" + dataToBeSend.length());

            StringBuffer sb = new StringBuffer();
            outputStream = httpConn.openOutputStream();

            outputStream.write(dataToBeSend.getBytes());

            outputStream.flush();//data written will be flushed to server.
            inputStream = httpConn.openDataInputStream();
            System.out.println(dataToBeSend);
            int chr;
            while ((chr = inputStream.read()) != -1) {
                sb.append((char) chr);
            }

            response = sb.toString();


        } catch (Throwable t) {
            System.out.println("Exception occured " + t.toString());
        } //Since only limited number of network objects can be in open state
        //it is necessary to clean them up as soon as we are done with them.
        finally {
            try {
                if (outputStream != null) {
                    outputStream.close();
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
        System.out.println("response: " + response);

    }
}
