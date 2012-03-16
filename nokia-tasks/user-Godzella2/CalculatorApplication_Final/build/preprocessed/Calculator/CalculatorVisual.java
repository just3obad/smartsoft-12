/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Calculator;

import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;

/**
 * @author Essam Hafez
 */
public class CalculatorVisual extends MIDlet implements CommandListener {
    StringItem resultedItem;
    private boolean midletPaused = false;
    String backWritten="";

    //<editor-fold defaultstate="collapsed" desc=" Generated Fields ">//GEN-BEGIN:|fields|0|
    private TextBox header;
    private Form form;
    private TextField textField;
    private StringItem stringItem;
    private List list;
    private Form form1;
    private Command okCommand;
    private Command okCommand1;
    private Command backCommand;
    private Command SelectOp;
    private Command okCommand2;
    private Command EqualCommand3;
    private Command NewCommand;
    private Font font1;
    //</editor-fold>//GEN-END:|fields|0|
    /**
     * The CalculatorVisual constructor.
     */
    public CalculatorVisual() {
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
        switchDisplayable(null, getForm1());//GEN-LINE:|3-startMIDlet|1|3-postAction
        // write post-action user code here
    }//GEN-BEGIN:|3-startMIDlet|2|
    //</editor-fold>//GEN-END:|3-startMIDlet|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Method: resumeMIDlet ">//GEN-BEGIN:|4-resumeMIDlet|0|4-preAction
    /**
     * Performs an action assigned to the Mobile Device - MIDlet Resumed point.
     */
    public void resumeMIDlet() {//GEN-END:|4-resumeMIDlet|0|4-preAction
        // write pre-action user code here
        switchDisplayable(null, getForm());//GEN-LINE:|4-resumeMIDlet|1|4-postAction
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

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: header ">//GEN-BEGIN:|18-getter|0|18-preInit
    /**
     * Returns an initiliazed instance of header component.
     * @return the initialized component instance
     */
    public TextBox getHeader() {
        if (header == null) {//GEN-END:|18-getter|0|18-preInit
            // write pre-init user code here
            header = new TextBox("Hello", "Welcome to my first calculator !!", 150, TextField.ANY);//GEN-BEGIN:|18-getter|1|18-postInit
            header.addCommand(getOkCommand());
            header.setCommandListener(this);//GEN-END:|18-getter|1|18-postInit
            // write post-init user code here
        }//GEN-BEGIN:|18-getter|2|
        return header;
    }
    //</editor-fold>//GEN-END:|18-getter|2|

public void calculate(){
    //String y = stringItem.getText();
    String y = backWritten;
                double resulted=0;
                double n1=0;
                double n2=0;
                for(int i=0;i<y.length();i++){
                    
                    if(y.charAt(i)=='+' || y.charAt(i)=='-' ||y.charAt(i)=='*' ||y.charAt(i)=='/'){
                         switch(y.charAt(i))
                         {
                        case '+':
                            try{
                                n1 = Double.parseDouble(y.substring(0, y.indexOf("+")));
                            }catch(Exception e1){
                                n1=0;
                            }
                            try{
                                n2 = Double.parseDouble(y.substring(y.indexOf("+")+1, y.length()));
                            }catch(Exception e2){
                                n2=0;
                            }
                           resulted = n1+n2;
                           resultedItem.setText(resulted + "");
                           textField.setString(null);
                           
                           break;
                        case '-':
                            try{
                                n1 = Double.parseDouble(y.substring(0, y.indexOf("-")));
                            }catch(Exception e1){
                                n1=0;
                            }
                            try{
                                n2 = Double.parseDouble(y.substring(y.indexOf("-")+1, y.length()));
                            }catch(Exception e2){
                                n2=0;
                            }
                           resulted = n1-n2;
                           resultedItem.setText(resulted + "");
                           textField.setString(null);
                           break;
                        case '*':
                            try{
                                n1 = Double.parseDouble(y.substring(0, y.indexOf("*")));
                            }catch(Exception e1){
                                n1=1;
                            }
                            try{
                                n2 = Double.parseDouble(y.substring(y.indexOf("*")+1, y.length()));
                            }catch(Exception e2){
                                n2=1;
                            }
                           resulted = n1*n2;
                           resultedItem.setText(resulted + "");
                           textField.setString(null);
                           break;
                        case '/':
                            try{
                                n1 = Double.parseDouble(y.substring(0, y.indexOf("/")));
                            }catch(Exception e1){
                                n1=1;
                            }
                            try{
                                n2 = Double.parseDouble(y.substring(y.indexOf("/")+1, y.length()));
                            }catch(Exception e2){
                                n2=1;
                            }
                            if(n2 !=0){   
                            resulted = n1/n2;
                            resultedItem.setText(resulted + "");
                            textField.setString(null);
                            break;                   
                            }else{
                                resulted = 0;
                                resultedItem.setText("Don't divide by zero tany");
                                textField.setString(null);
                                break;   
                            }
                        default :resultedItem.setText(textField.getString()) ;break;
                            }
                    }else{
                        try{
                           // double k = Double.parseDouble(y.charAt(i)+"");
                            if(i==y.length()-1)
                            {
                                System.out.println(y);
                            resulted = Double.parseDouble(y);
                            resultedItem.setText(resulted + "");
                            textField.setString(null);
                            }
                        }catch(Exception e){
                        textField.setString(null);
                        
                        }
                    }
                    
                }
                backWritten=resulted+"";
                //stringItem.setText(resulted+"");
}

public boolean NumbersOnly(String s){
    for(int i=0;i<s.length();i++)
    {
        try{
            if(s.charAt(i) != '.'){
                Integer.parseInt(s.charAt(i)+"");
            }     
        }catch(Exception e){
            return false;
        }   
    }
    return true;
}

//<editor-fold defaultstate="collapsed" desc=" Generated Method: commandAction for Displayables ">//GEN-BEGIN:|7-commandAction|0|7-preCommandAction
/**
 * Called by a system to indicated that a command has been invoked on a particular displayable.
 * @param command the Command that was invoked
 * @param displayable the Displayable where the command was invoked
 */
public void commandAction(Command command, Displayable displayable) {//GEN-END:|7-commandAction|0|7-preCommandAction
        // write pre-action user code here
    if (displayable == form) {//GEN-BEGIN:|7-commandAction|1|50-preAction
        if (command == EqualCommand3) {//GEN-END:|7-commandAction|1|50-preAction
                // write pre-action user code here
            if(backWritten== null){
                //stringItem.setText(textField.getString());
                backWritten=textField.getString();
                       
            }else{
                if((NumbersOnly(backWritten))){
                    //stringItem.setText(textField.getString());
                    backWritten=textField.getString();
                }else{
                //stringItem.setText(stringItem.getText()+textField.getString());
                backWritten+=textField.getString();
                }
            }
                
                calculate();               
                form.addCommand(SelectOp);
                
                switchDisplayable(null, getForm());//GEN-LINE:|7-commandAction|2|50-postAction
                // write post-action user code here
        } else if (command == NewCommand) {//GEN-LINE:|7-commandAction|3|53-preAction
                // write pre-action user code here
                textField.setString("");
                resultedItem.setText(null);
                backWritten=null;
                //stringItem.setText(null);
                
                form.addCommand(SelectOp);
                switchDisplayable(null, getForm1());//GEN-LINE:|7-commandAction|4|53-postAction
                // write post-action user code here
        } else if (command == SelectOp) {//GEN-LINE:|7-commandAction|5|41-preAction
                // write pre-action user code here
            switchDisplayable(null, getList());//GEN-LINE:|7-commandAction|6|41-postAction
                // write post-action user code here
               
        }//GEN-BEGIN:|7-commandAction|7|58-preAction
    } else if (displayable == form1) {
        if (command == okCommand) {//GEN-END:|7-commandAction|7|58-preAction
                // write pre-action user code here
            switchDisplayable(null, getForm());//GEN-LINE:|7-commandAction|8|58-postAction
                // write post-action user code here
        }//GEN-BEGIN:|7-commandAction|9|23-preAction
    } else if (displayable == header) {
        if (command == okCommand) {//GEN-END:|7-commandAction|9|23-preAction
                // write pre-action user code here
//GEN-LINE:|7-commandAction|10|23-postAction
                // write post-action user code here
        }//GEN-BEGIN:|7-commandAction|11|28-preAction
    } else if (displayable == list) {
        if (command == List.SELECT_COMMAND) {//GEN-END:|7-commandAction|11|28-preAction
                // write pre-action user code here
            listAction();//GEN-LINE:|7-commandAction|12|28-postAction
                // write post-action user code here
        } else if (command == backCommand) {//GEN-LINE:|7-commandAction|13|44-preAction
                // write pre-action user code here            
            switchDisplayable(null, getForm());//GEN-LINE:|7-commandAction|14|44-postAction
                // write post-action user code here
               
                
        }//GEN-BEGIN:|7-commandAction|15|7-postCommandAction
    }//GEN-END:|7-commandAction|15|7-postCommandAction
        // write post-action user code here
}//GEN-BEGIN:|7-commandAction|16|
//</editor-fold>//GEN-END:|7-commandAction|16|


//<editor-fold defaultstate="collapsed" desc=" Generated Getter: form ">//GEN-BEGIN:|25-getter|0|25-preInit
/**
 * Returns an initiliazed instance of form component.
 * @return the initialized component instance
 */
public Form getForm() {
    if (form == null) {//GEN-END:|25-getter|0|25-preInit
            // write pre-init user code here
        form = new Form("Calculator Application", new Item[] { getTextField(), getStringItem() });//GEN-BEGIN:|25-getter|1|25-postInit
        form.addCommand(getSelectOp());
        form.addCommand(getEqualCommand3());
        form.addCommand(getNewCommand());
        form.setCommandListener(this);//GEN-END:|25-getter|1|25-postInit
            // write post-init user code here
            resultedItem = new StringItem("Result:-", null);
            form.insert(1, resultedItem);
    }//GEN-BEGIN:|25-getter|2|
    return form;
}
//</editor-fold>//GEN-END:|25-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: list ">//GEN-BEGIN:|26-getter|0|26-preInit
/**
 * Returns an initiliazed instance of list component.
 * @return the initialized component instance
 */
public List getList() {
    if (list == null) {//GEN-END:|26-getter|0|26-preInit
            // write pre-init user code here
        list = new List("list", Choice.IMPLICIT);//GEN-BEGIN:|26-getter|1|26-postInit
        list.append("+", null);
        list.append("-", null);
        list.append("*", null);
        list.append("/", null);
        list.addCommand(getBackCommand());
        list.setCommandListener(this);
        list.setFitPolicy(Choice.TEXT_WRAP_DEFAULT);
        list.setSelectedFlags(new boolean[] { false, false, false, false });//GEN-END:|26-getter|1|26-postInit
            // write post-init user code here
    }//GEN-BEGIN:|26-getter|2|
    return list;
}
//</editor-fold>//GEN-END:|26-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Method: listAction ">//GEN-BEGIN:|26-action|0|26-preAction
/**
 * Performs an action assigned to the selected list element in the list component.
 */
public void listAction() {//GEN-END:|26-action|0|26-preAction
        // enter pre-action user code here
    String __selectedString = getList().getString(getList().getSelectedIndex());//GEN-BEGIN:|26-action|1|30-preAction
    if (__selectedString != null) {
        if (__selectedString.equals("+")) {//GEN-END:|26-action|1|30-preAction
                // write pre-action user code here
                //textField.setString(textField.getString()+ "+");
               // stringItem.setText(textField.getString() + "+");
                backWritten+=textField.getString() + "+";
                switchDisplayable(null, getForm());
                 form.removeCommand(SelectOp);
                 textField.setString(null);
//GEN-LINE:|26-action|2|30-postAction
                // write post-action user code here
        } else if (__selectedString.equals("-")) {//GEN-LINE:|26-action|3|31-preAction
                // write pre-action user code here
                //textField.setString(textField.getString()+ "-");
                //stringItem.setText(textField.getString() + "-");
                backWritten+=textField.getString() + "-";
                switchDisplayable(null, getForm());
                 form.removeCommand(SelectOp);
                 textField.setString(null);
//GEN-LINE:|26-action|4|31-postAction
                // write post-action user code here
        } else if (__selectedString.equals("*")) {//GEN-LINE:|26-action|5|32-preAction
                // write pre-action user code here
                //textField.setString(textField.getString()+ "*");
                //stringItem.setText(textField.getString() + "*");
                backWritten+=textField.getString() + "*";
                switchDisplayable(null, getForm());
                 form.removeCommand(SelectOp);
                 textField.setString(null);
//GEN-LINE:|26-action|6|32-postAction
                // write post-action user code here
        } else if (__selectedString.equals("/")) {//GEN-LINE:|26-action|7|33-preAction
                // write pre-action user code here
                //textField.setString(textField.getString()+ "/");
                //stringItem.setText(textField.getString() + "/");
                backWritten+=textField.getString() + "/";
            switchDisplayable(null, getForm());
                 form.removeCommand(SelectOp);
                 textField.setString(null);
//GEN-LINE:|26-action|8|33-postAction
                // write post-action user code here
        }//GEN-BEGIN:|26-action|9|26-postAction
    }//GEN-END:|26-action|9|26-postAction
        // enter post-action user code here
}//GEN-BEGIN:|26-action|10|
//</editor-fold>//GEN-END:|26-action|10|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: okCommand ">//GEN-BEGIN:|22-getter|0|22-preInit
/**
 * Returns an initiliazed instance of okCommand component.
 * @return the initialized component instance
 */
public Command getOkCommand() {
    if (okCommand == null) {//GEN-END:|22-getter|0|22-preInit
            // write pre-init user code here
        okCommand = new Command("Ok", Command.OK, 0);//GEN-LINE:|22-getter|1|22-postInit
            // write post-init user code here
    }//GEN-BEGIN:|22-getter|2|
    return okCommand;
}
//</editor-fold>//GEN-END:|22-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: textField ">//GEN-BEGIN:|34-getter|0|34-preInit
/**
 * Returns an initiliazed instance of textField component.
 * @return the initialized component instance
 */
public TextField getTextField() {
    if (textField == null) {//GEN-END:|34-getter|0|34-preInit
            // write pre-init user code here
        textField = new TextField("Screen", "<null>", 32, TextField.ANY);//GEN-LINE:|34-getter|1|34-postInit
            // write post-init user code here
            textField.setConstraints(textField.DECIMAL);
    }//GEN-BEGIN:|34-getter|2|
    return textField;
}
//</editor-fold>//GEN-END:|34-getter|2|



//<editor-fold defaultstate="collapsed" desc=" Generated Getter: okCommand1 ">//GEN-BEGIN:|37-getter|0|37-preInit
/**
 * Returns an initiliazed instance of okCommand1 component.
 * @return the initialized component instance
 */
public Command getOkCommand1() {
    if (okCommand1 == null) {//GEN-END:|37-getter|0|37-preInit
            // write pre-init user code here
        okCommand1 = new Command("Ok", Command.OK, 0);//GEN-LINE:|37-getter|1|37-postInit
            // write post-init user code here
    }//GEN-BEGIN:|37-getter|2|
    return okCommand1;
}
//</editor-fold>//GEN-END:|37-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: SelectOp ">//GEN-BEGIN:|40-getter|0|40-preInit
/**
 * Returns an initiliazed instance of SelectOp component.
 * @return the initialized component instance
 */
public Command getSelectOp() {
    if (SelectOp == null) {//GEN-END:|40-getter|0|40-preInit
            // write pre-init user code here
        SelectOp = new Command("Operation", Command.ITEM, 0);//GEN-LINE:|40-getter|1|40-postInit
            // write post-init user code here
    }//GEN-BEGIN:|40-getter|2|
    return SelectOp;
}
//</editor-fold>//GEN-END:|40-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: backCommand ">//GEN-BEGIN:|43-getter|0|43-preInit
/**
 * Returns an initiliazed instance of backCommand component.
 * @return the initialized component instance
 */
public Command getBackCommand() {
    if (backCommand == null) {//GEN-END:|43-getter|0|43-preInit
            // write pre-init user code here
        backCommand = new Command("Back", Command.BACK, 0);//GEN-LINE:|43-getter|1|43-postInit
            // write post-init user code here
    }//GEN-BEGIN:|43-getter|2|
    return backCommand;
}
//</editor-fold>//GEN-END:|43-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: okCommand2 ">//GEN-BEGIN:|47-getter|0|47-preInit
/**
 * Returns an initiliazed instance of okCommand2 component.
 * @return the initialized component instance
 */
public Command getOkCommand2() {
    if (okCommand2 == null) {//GEN-END:|47-getter|0|47-preInit
            // write pre-init user code here
        okCommand2 = new Command("Ok", Command.OK, 0);//GEN-LINE:|47-getter|1|47-postInit
            // write post-init user code here
    }//GEN-BEGIN:|47-getter|2|
    return okCommand2;
}
//</editor-fold>//GEN-END:|47-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: EqualCommand3 ">//GEN-BEGIN:|49-getter|0|49-preInit
/**
 * Returns an initiliazed instance of EqualCommand3 component.
 * @return the initialized component instance
 */
public Command getEqualCommand3() {
    if (EqualCommand3 == null) {//GEN-END:|49-getter|0|49-preInit
            // write pre-init user code here
        EqualCommand3 = new Command("Equal", Command.OK, 1);//GEN-LINE:|49-getter|1|49-postInit
            // write post-init user code here
    }//GEN-BEGIN:|49-getter|2|
    return EqualCommand3;
}
//</editor-fold>//GEN-END:|49-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: NewCommand ">//GEN-BEGIN:|52-getter|0|52-preInit
/**
 * Returns an initiliazed instance of NewCommand component.
 * @return the initialized component instance
 */
public Command getNewCommand() {
    if (NewCommand == null) {//GEN-END:|52-getter|0|52-preInit
            // write pre-init user code here
        NewCommand = new Command("New", Command.ITEM, 2);//GEN-LINE:|52-getter|1|52-postInit
            // write post-init user code here
    }//GEN-BEGIN:|52-getter|2|
    return NewCommand;
}
//</editor-fold>//GEN-END:|52-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: form1 ">//GEN-BEGIN:|57-getter|0|57-preInit
/**
 * Returns an initiliazed instance of form1 component.
 * @return the initialized component instance
 */
public Form getForm1() {
    if (form1 == null) {//GEN-END:|57-getter|0|57-preInit
            // write pre-init user code here
        form1 = new Form("form1");//GEN-BEGIN:|57-getter|1|57-postInit
        form1.addCommand(getOkCommand());
        form1.setCommandListener(this);//GEN-END:|57-getter|1|57-postInit
            // write post-init user code here
            StringItem welcomeScreen = new StringItem("Hello", "Welcome to my first calculator");
            form1.insert(0, welcomeScreen);
    }//GEN-BEGIN:|57-getter|2|
    return form1;
}
//</editor-fold>//GEN-END:|57-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: stringItem ">//GEN-BEGIN:|61-getter|0|61-preInit
/**
 * Returns an initiliazed instance of stringItem component.
 * @return the initialized component instance
 */
public StringItem getStringItem() {
    if (stringItem == null) {//GEN-END:|61-getter|0|61-preInit
            // write pre-init user code here
        stringItem = new StringItem("written", null, Item.PLAIN);//GEN-BEGIN:|61-getter|1|61-postInit
        stringItem.setFont(getFont1());//GEN-END:|61-getter|1|61-postInit
            // write post-init user code here
        stringItem.setLabel("");
        
    }//GEN-BEGIN:|61-getter|2|
    return stringItem;
}
//</editor-fold>//GEN-END:|61-getter|2|



//<editor-fold defaultstate="collapsed" desc=" Generated Getter: font1 ">//GEN-BEGIN:|63-getter|0|63-preInit
/**
 * Returns an initiliazed instance of font1 component.
 * @return the initialized component instance
 */
public Font getFont1() {
    if (font1 == null) {//GEN-END:|63-getter|0|63-preInit
        // write pre-init user code here
        font1 = Font.getFont(Font.FACE_SYSTEM, Font.STYLE_PLAIN, Font.SIZE_SMALL);//GEN-LINE:|63-getter|1|63-postInit
        // write post-init user code here
    }//GEN-BEGIN:|63-getter|2|
    return font1;
}
//</editor-fold>//GEN-END:|63-getter|2|

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
