/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import javax.microedition.lcdui.*;
import javax.microedition.midlet.MIDlet;
import org.netbeans.microedition.util.SimpleCancellableTask;

/**
 * @author mohamed
 */
public class calculator extends MIDlet implements CommandListener, ItemCommandListener {
    
        private boolean midletPaused = false;
//<editor-fold defaultstate="collapsed" desc=" Generated Fields ">//GEN-BEGIN:|fields|0|
    private Form form;
    private StringItem backSpace;
    private TextField FieldA;
    private StringItem plus;
    private StringItem minus;
    private StringItem multiply;
    private StringItem equal;
    private StringItem divide;
    private StringItem pow;
    private Command exitCommand;
    private Command okCommand;
    private Command cancelCommand;
    private Command itemCommand;
    private SimpleCancellableTask task;
    private Font font;
//</editor-fold>//GEN-END:|fields|0|

    public calculator() {
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
        if (displayable == form) {//GEN-BEGIN:|7-commandAction|1|31-preAction
            if (command == cancelCommand) {//GEN-END:|7-commandAction|1|31-preAction
                // write pre-action user code here
                exitMIDlet();//GEN-LINE:|7-commandAction|2|31-postAction
                // write post-action user code here
            } else if (command == okCommand) {//GEN-LINE:|7-commandAction|3|29-preAction
                // write pre-action user code here
//GEN-LINE:|7-commandAction|4|29-postAction
                // write post-action user code here
            }//GEN-BEGIN:|7-commandAction|5|7-postCommandAction
        }//GEN-END:|7-commandAction|5|7-postCommandAction
        // write post-action user code here
    }//GEN-BEGIN:|7-commandAction|6|
//</editor-fold>//GEN-END:|7-commandAction|6|




//<editor-fold defaultstate="collapsed" desc=" Generated Getter: form ">//GEN-BEGIN:|20-getter|0|20-preInit
    /**
     * Returns an initialized instance of form component.
     *
     * @return the initialized component instance
     */
    public Form getForm() {
        if (form == null) {//GEN-END:|20-getter|0|20-preInit
            // write pre-init user code here
            form = new Form("Calculator", new Item[]{getFieldA(), getPlus(), getMinus(), getMultiply(), getDivide(), getEqual(), getPow(), getBackSpace()});//GEN-BEGIN:|20-getter|1|20-postInit
            form.addCommand(getOkCommand());
            form.addCommand(getCancelCommand());
            form.setCommandListener(this);//GEN-END:|20-getter|1|20-postInit
            // write post-init user code here
        }//GEN-BEGIN:|20-getter|2|
        return form;
    }
//</editor-fold>//GEN-END:|20-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: FieldA ">//GEN-BEGIN:|23-getter|0|23-preInit
    /**
     * Returns an initialized instance of FieldA component.
     *
     * @return the initialized component instance
     */
    public TextField getFieldA() {
        if (FieldA == null) {//GEN-END:|23-getter|0|23-preInit
            // write pre-init user code here
            FieldA = new TextField("textField", null, 32, TextField.ANY);//GEN-LINE:|23-getter|1|23-postInit
            // write post-init user code here
        }//GEN-BEGIN:|23-getter|2|
        return FieldA;
    }
//</editor-fold>//GEN-END:|23-getter|2|









//<editor-fold defaultstate="collapsed" desc=" Generated Getter: exitCommand ">//GEN-BEGIN:|26-getter|0|26-preInit
    /**
     * Returns an initialized instance of exitCommand component.
     *
     * @return the initialized component instance
     */
    public Command getExitCommand() {
        if (exitCommand == null) {//GEN-END:|26-getter|0|26-preInit
            // write pre-init user code here
            exitCommand = new Command("Exit", Command.EXIT, 0);//GEN-LINE:|26-getter|1|26-postInit
            // write post-init user code here
        }//GEN-BEGIN:|26-getter|2|
        return exitCommand;
    }
//</editor-fold>//GEN-END:|26-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: okCommand ">//GEN-BEGIN:|28-getter|0|28-preInit
    /**
     * Returns an initialized instance of okCommand component.
     *
     * @return the initialized component instance
     */
    public Command getOkCommand() {
        if (okCommand == null) {//GEN-END:|28-getter|0|28-preInit
            // write pre-init user code here
            okCommand = new Command("Ok", Command.OK, 0);//GEN-LINE:|28-getter|1|28-postInit
            // write post-init user code here
        }//GEN-BEGIN:|28-getter|2|
        return okCommand;
    }
//</editor-fold>//GEN-END:|28-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: cancelCommand ">//GEN-BEGIN:|30-getter|0|30-preInit
    /**
     * Returns an initialized instance of cancelCommand component.
     *
     * @return the initialized component instance
     */
    public Command getCancelCommand() {
        if (cancelCommand == null) {//GEN-END:|30-getter|0|30-preInit
            // write pre-init user code here
            cancelCommand = new Command("Cancel", Command.CANCEL, 10);//GEN-LINE:|30-getter|1|30-postInit
            // write post-init user code here
        }//GEN-BEGIN:|30-getter|2|
        return cancelCommand;
    }
//</editor-fold>//GEN-END:|30-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: task ">//GEN-BEGIN:|19-getter|0|19-preInit
    /**
     * Returns an initialized instance of task component.
     *
     * @return the initialized component instance
     */
    public SimpleCancellableTask getTask() {
        if (task == null) {//GEN-END:|19-getter|0|19-preInit
            // write pre-init user code here
            task = new SimpleCancellableTask();//GEN-BEGIN:|19-getter|1|19-execute
            task.setExecutable(new org.netbeans.microedition.util.Executable() {

                public void execute() throws Exception {//GEN-END:|19-getter|1|19-execute
// write task-execution user code here
                }//GEN-BEGIN:|19-getter|2|19-postInit
            });//GEN-END:|19-getter|2|19-postInit
            // write post-init user code here
        }//GEN-BEGIN:|19-getter|3|
        return task;
    }
//</editor-fold>//GEN-END:|19-getter|3|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: plus ">//GEN-BEGIN:|46-getter|0|46-preInit
    /**
     * Returns an initialized instance of plus component.
     *
     * @return the initialized component instance
     */
    public StringItem getPlus() {
        if (plus == null) {//GEN-END:|46-getter|0|46-preInit
            // write pre-init user code here
            plus = new StringItem("plus", "+", Item.BUTTON);//GEN-BEGIN:|46-getter|1|46-postInit
            plus.addCommand(getItemCommand());
            plus.setItemCommandListener(this);
            plus.setLayout(ImageItem.LAYOUT_DEFAULT);
            plus.setFont(getFont());
            plus.setPreferredSize(-1, -1);//GEN-END:|46-getter|1|46-postInit
            // write post-init user code here
        }//GEN-BEGIN:|46-getter|2|
        return plus;
    }
//</editor-fold>//GEN-END:|46-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Method: commandAction for Items ">//GEN-BEGIN:|8-itemCommandAction|0|8-preItemCommandAction
    /**
     * Called by a system to indicated that a command has been invoked on a
     * particular item.
     *
     * @param command the Command that was invoked
     * @param displayable the Item where the command was invoked
     */
    public void commandAction(Command command, Item item) {//GEN-END:|8-itemCommandAction|0|8-preItemCommandAction
        // write pre-action user code here
        if (item == backSpace) {//GEN-BEGIN:|8-itemCommandAction|1|66-preAction
            if (command == itemCommand) {//GEN-END:|8-itemCommandAction|1|66-preAction
                // write pre-action user code here
//GEN-LINE:|8-itemCommandAction|2|66-postAction
                // write post-action user code here
            }//GEN-BEGIN:|8-itemCommandAction|3|58-preAction
        } else if (item == divide) {
            if (command == itemCommand) {//GEN-END:|8-itemCommandAction|3|58-preAction
                // write pre-action user code here
//GEN-LINE:|8-itemCommandAction|4|58-postAction
                // write post-action user code here
            }//GEN-BEGIN:|8-itemCommandAction|5|60-preAction
        } else if (item == equal) {
            if (command == itemCommand) {//GEN-END:|8-itemCommandAction|5|60-preAction
                // write pre-action user code here
//GEN-LINE:|8-itemCommandAction|6|60-postAction
                // write post-action user code here
            }//GEN-BEGIN:|8-itemCommandAction|7|53-preAction
        } else if (item == minus) {
            if (command == itemCommand) {//GEN-END:|8-itemCommandAction|7|53-preAction
                // write pre-action user code here
//GEN-LINE:|8-itemCommandAction|8|53-postAction
                // write post-action user code here
            }//GEN-BEGIN:|8-itemCommandAction|9|57-preAction
        } else if (item == multiply) {
            if (command == itemCommand) {//GEN-END:|8-itemCommandAction|9|57-preAction
                // write pre-action user code here
//GEN-LINE:|8-itemCommandAction|10|57-postAction
                // write post-action user code here
            }//GEN-BEGIN:|8-itemCommandAction|11|48-preAction
        } else if (item == plus) {
            if (command == itemCommand) {//GEN-END:|8-itemCommandAction|11|48-preAction
                // write pre-action user code here
//GEN-LINE:|8-itemCommandAction|12|48-postAction
                // write post-action user code here
            }//GEN-BEGIN:|8-itemCommandAction|13|62-preAction
        } else if (item == pow) {
            if (command == itemCommand) {//GEN-END:|8-itemCommandAction|13|62-preAction
                // write pre-action user code here
//GEN-LINE:|8-itemCommandAction|14|62-postAction
                // write post-action user code here
            }//GEN-BEGIN:|8-itemCommandAction|15|8-postItemCommandAction
        }//GEN-END:|8-itemCommandAction|15|8-postItemCommandAction
        // write post-action user code here
    }//GEN-BEGIN:|8-itemCommandAction|16|
//</editor-fold>//GEN-END:|8-itemCommandAction|16|



//<editor-fold defaultstate="collapsed" desc=" Generated Getter: itemCommand ">//GEN-BEGIN:|47-getter|0|47-preInit
    /**
     * Returns an initialized instance of itemCommand component.
     *
     * @return the initialized component instance
     */
    public Command getItemCommand() {
        if (itemCommand == null) {//GEN-END:|47-getter|0|47-preInit
            // write pre-init user code here
            itemCommand = new Command("Item", Command.ITEM, 0);//GEN-LINE:|47-getter|1|47-postInit
            // write post-init user code here
        }//GEN-BEGIN:|47-getter|2|
        return itemCommand;
    }
//</editor-fold>//GEN-END:|47-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: minus ">//GEN-BEGIN:|50-getter|0|50-preInit
    /**
     * Returns an initialized instance of minus component.
     *
     * @return the initialized component instance
     */
    public StringItem getMinus() {
        if (minus == null) {//GEN-END:|50-getter|0|50-preInit
            // write pre-init user code here
            minus = new StringItem("minus", "-", Item.BUTTON);//GEN-BEGIN:|50-getter|1|50-postInit
            minus.addCommand(getItemCommand());
            minus.setItemCommandListener(this);
            minus.setDefaultCommand(getItemCommand());
            minus.setFont(getFont());//GEN-END:|50-getter|1|50-postInit
            // write post-init user code here
        }//GEN-BEGIN:|50-getter|2|
        return minus;
    }
//</editor-fold>//GEN-END:|50-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: font ">//GEN-BEGIN:|51-getter|0|51-preInit
    /**
     * Returns an initialized instance of font component.
     *
     * @return the initialized component instance
     */
    public Font getFont() {
        if (font == null) {//GEN-END:|51-getter|0|51-preInit
            // write pre-init user code here
            font = Font.getDefaultFont();//GEN-LINE:|51-getter|1|51-postInit
            // write post-init user code here
        }//GEN-BEGIN:|51-getter|2|
        return font;
    }
//</editor-fold>//GEN-END:|51-getter|2|



//<editor-fold defaultstate="collapsed" desc=" Generated Getter: multiply ">//GEN-BEGIN:|55-getter|0|55-preInit
    /**
     * Returns an initialized instance of multiply component.
     *
     * @return the initialized component instance
     */
    public StringItem getMultiply() {
        if (multiply == null) {//GEN-END:|55-getter|0|55-preInit
            // write pre-init user code here
            multiply = new StringItem("mul", "*", Item.BUTTON);//GEN-BEGIN:|55-getter|1|55-postInit
            multiply.addCommand(getItemCommand());
            multiply.setItemCommandListener(this);
            multiply.setDefaultCommand(getItemCommand());
            multiply.setFont(getFont());//GEN-END:|55-getter|1|55-postInit
            // write post-init user code here
        }//GEN-BEGIN:|55-getter|2|
        return multiply;
    }
//</editor-fold>//GEN-END:|55-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: divide ">//GEN-BEGIN:|56-getter|0|56-preInit
    /**
     * Returns an initialized instance of divide component.
     *
     * @return the initialized component instance
     */
    public StringItem getDivide() {
        if (divide == null) {//GEN-END:|56-getter|0|56-preInit
            // write pre-init user code here
            divide = new StringItem("divide", "/", Item.BUTTON);//GEN-BEGIN:|56-getter|1|56-postInit
            divide.addCommand(getItemCommand());
            divide.setItemCommandListener(this);
            divide.setDefaultCommand(getItemCommand());
            divide.setFont(getFont());//GEN-END:|56-getter|1|56-postInit
            // write post-init user code here
        }//GEN-BEGIN:|56-getter|2|
        return divide;
    }
//</editor-fold>//GEN-END:|56-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: equal ">//GEN-BEGIN:|59-getter|0|59-preInit
    /**
     * Returns an initialized instance of equal component.
     *
     * @return the initialized component instance
     */
    public StringItem getEqual() {
        if (equal == null) {//GEN-END:|59-getter|0|59-preInit
            // write pre-init user code here
            equal = new StringItem("equal", "=", Item.BUTTON);//GEN-BEGIN:|59-getter|1|59-postInit
            equal.addCommand(getItemCommand());
            equal.setItemCommandListener(this);
            equal.setDefaultCommand(getItemCommand());
            equal.setFont(getFont());//GEN-END:|59-getter|1|59-postInit
            // write post-init user code here
        }//GEN-BEGIN:|59-getter|2|
        return equal;
    }
//</editor-fold>//GEN-END:|59-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: pow ">//GEN-BEGIN:|61-getter|0|61-preInit
    /**
     * Returns an initialized instance of pow component.
     *
     * @return the initialized component instance
     */
    public StringItem getPow() {
        if (pow == null) {//GEN-END:|61-getter|0|61-preInit
            // write pre-init user code here
            pow = new StringItem("pow", "^", Item.BUTTON);//GEN-BEGIN:|61-getter|1|61-postInit
            pow.setPreferredSize(-1, -1);
            pow.addCommand(getItemCommand());
            pow.setItemCommandListener(this);
            pow.setDefaultCommand(getItemCommand());
            pow.setLayout(ImageItem.LAYOUT_DEFAULT);
            pow.setFont(getFont());//GEN-END:|61-getter|1|61-postInit
            // write post-init user code here
        }//GEN-BEGIN:|61-getter|2|
        return pow;
    }
//</editor-fold>//GEN-END:|61-getter|2|



//<editor-fold defaultstate="collapsed" desc=" Generated Getter: backSpace ">//GEN-BEGIN:|65-getter|0|65-preInit
    /**
     * Returns an initialized instance of backSpace component.
     *
     * @return the initialized component instance
     */
    public StringItem getBackSpace() {
        if (backSpace == null) {//GEN-END:|65-getter|0|65-preInit
            // write pre-init user code here
            backSpace = new StringItem("backspace", "\\", Item.BUTTON);//GEN-BEGIN:|65-getter|1|65-postInit
            backSpace.addCommand(getItemCommand());
            backSpace.setItemCommandListener(this);
            backSpace.setDefaultCommand(getItemCommand());
            backSpace.setFont(getFont());//GEN-END:|65-getter|1|65-postInit
            // write post-init user code here
        }//GEN-BEGIN:|65-getter|2|
        return backSpace;
    }
//</editor-fold>//GEN-END:|65-getter|2|





    public Display getDisplay() {
          return Display.getDisplay(this);
    }

    public void exitMIDlet() {
        switchDisplayable(null, null);
        destroyApp(true);
        notifyDestroyed();
    }

    public void startApp() {
            initialize();
            startMIDlet();
   initialize();
            startMIDlet();
            minus.setDefaultCommand(new Command("Set", Command.ITEM, 1));
            plus.setDefaultCommand(new Command("Set", Command.ITEM, 1));
            divide.setDefaultCommand(new Command("Set", Command.ITEM, 1));
            multiply.setDefaultCommand(new Command("Set", Command.ITEM, 1));
            equal.setDefaultCommand(new Command("Set", Command.ITEM, 1));
            pow.setDefaultCommand(new Command("Set", Command.ITEM, 1));
            backSpace.setDefaultCommand(new Command("Set", Command.ITEM, 1));
            ItemCommandListener x = new ItemCommandListener() {

                public void commandAction(Command c, Item item){
                    if(item.getLabel().equals(minus.getLabel())){
                        if( FieldA.getString().length()!=0 && FieldA.getString().charAt(FieldA.getString().length()-1)!= '-' && FieldA.getString().charAt(FieldA.getString().length()-1)!= '+'&& FieldA.getString().charAt(FieldA.getString().length()-1)!= '*' && FieldA.getString().charAt(FieldA.getString().length()-1)!= '/'&& FieldA.getString().charAt(FieldA.getString().length()-1)!= '^')
                            FieldA.setString(FieldA.getString()+"-");
                    }
                        else if(item.getLabel().equals(plus.getLabel())){
                        if( FieldA.getString().length()!=0 && FieldA.getString().charAt(FieldA.getString().length()-1)!= '+'&& FieldA.getString().charAt(FieldA.getString().length()-1)!= '-'&& FieldA.getString().charAt(FieldA.getString().length()-1)!= '*'&& FieldA.getString().charAt(FieldA.getString().length()-1)!= '/'&& FieldA.getString().charAt(FieldA.getString().length()-1)!= '^')
                            FieldA.setString(FieldA.getString()+"+");                    
                        }
                        else if(item.getLabel().equals(multiply.getLabel())){
                        if( FieldA.getString().length()!=0 && FieldA.getString().charAt(FieldA.getString().length()-1)!= '*'&& FieldA.getString().charAt(FieldA.getString().length()-1)!= '-'&& FieldA.getString().charAt(FieldA.getString().length()-1)!= '+'&& FieldA.getString().charAt(FieldA.getString().length()-1)!= '/'&& FieldA.getString().charAt(FieldA.getString().length()-1)!= '^')
                            FieldA.setString(FieldA.getString()+"*");                    
                        }
                    else if(item.getLabel().equals(divide.getLabel())){
                        if( FieldA.getString().length()!=0 && FieldA.getString().charAt(FieldA.getString().length()-1)!= '/'&& FieldA.getString().charAt(FieldA.getString().length()-1)!= '-'&& FieldA.getString().charAt(FieldA.getString().length()-1)!= '+'&& FieldA.getString().charAt(FieldA.getString().length()-1)!= '*'&& FieldA.getString().charAt(FieldA.getString().length()-1)!= '^')
                            FieldA.setString(FieldA.getString()+"/");                    
                        }
                    else if(item.getLabel().equals(pow.getLabel())){
                        if( FieldA.getString().length()!=0 && FieldA.getString().charAt(FieldA.getString().length()-1)!= '^'&& FieldA.getString().charAt(FieldA.getString().length()-1)!= '+'&& FieldA.getString().charAt(FieldA.getString().length()-1)!= '-'&& FieldA.getString().charAt(FieldA.getString().length()-1)!= '*'&& FieldA.getString().charAt(FieldA.getString().length()-1)!= '/')
                            FieldA.setString(FieldA.getString()+"^");                    
                        }
                    else if(item.getLabel().equals(equal.getLabel())){
                            String s=""+calculate(FieldA.getString());
                            FieldA.setString(s);
                        }
                    else if(item.getLabel().equals(backSpace.getLabel())){
                            FieldA.setString(FieldA.getString().substring(0,FieldA.getString().length()-1));
                        }
            }
            };
            minus.setItemCommandListener(x);
            divide.setItemCommandListener(x);
            multiply.setItemCommandListener(x);
            equal.setItemCommandListener(x);
            plus.setItemCommandListener(x);
            pow.setItemCommandListener(x);
            backSpace.setItemCommandListener(x);
            midletPaused = false;            
            
    }
    
    
    public int calculate(String x){
        int temp=0;
        int result=0;
        boolean flag=false;
        for(int i=0; i<x.length(); i++){
            if(x.charAt(i)=='*'&&x.length()!=1){
                temp=Integer.parseInt(x.substring(0,i));
                x=x.substring(i+1,x.length());
                result += temp*calculate(x);
                flag = true;
            }
            else if(x.charAt(i)=='+'&&x.length()!=1){
                temp=Integer.parseInt(x.substring(0,i));
                x=x.substring(i+1,x.length());
                result =result + temp + calculate(x);
                flag = true;
            }
            else if(x.charAt(i)=='-'&&x.length()!=1){
                temp=Integer.parseInt(x.substring(0,i));
                x=x.substring(i+1,x.length());
                result += temp-calculate(x);
                flag = true;
            }
            else if(x.charAt(i)=='/'&&x.length()!=1){
                temp=Integer.parseInt(x.substring(0,i));
                x=x.substring(i+1,x.length());
                result += temp/calculate(x);
                flag = true;
            }
            else if(x.charAt(i)=='^'&&x.length()!=1){
                temp=Integer.parseInt(x.substring(0,i));
                x=x.substring(i+1,x.length());
                result += pow(temp,calculate(x));
                flag = true;
            }
            else if(x.charAt(i)!=1||x.charAt(i)!=2||x.charAt(i)!=3||x.charAt(i)!=4||x.charAt(i)!=5||x.charAt(i)!=6||x.charAt(i)!=7||x.charAt(i)!=8||x.charAt(i)!=9||x.charAt(i)!=0)
               FieldA.setString(""); 
        }
        if(flag==true)
        return result;
        else
            return result+Integer.parseInt(x);
    }
    
    public int pow(int x, int y){
        int result=1;
        for(int i=0; i<y; i++)
            result=result*x;
        return result;
    }

    public void pauseApp() {
        midletPaused = true;
    }

    public void destroyApp(boolean unconditional) {
    }

}
