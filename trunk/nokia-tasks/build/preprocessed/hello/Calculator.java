/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hello;

import com.sun.lwuit.Button;
import com.sun.lwuit.Command;
import com.sun.lwuit.Display;
import com.sun.lwuit.Form;
import com.sun.lwuit.Label;
import com.sun.lwuit.TextField;
import com.sun.lwuit.events.ActionEvent;
import com.sun.lwuit.events.ActionListener;
import com.sun.lwuit.layouts.BoxLayout;
import com.sun.lwuit.plaf.UIManager;
import com.sun.lwuit.util.Resources;
import javax.microedition.midlet.*;

/**
 * @author RG
 */
public class Calculator extends MIDlet {
    Form mainForm;
    double res;
    boolean a,s,m,d = false;

    public void startApp() {
        Display.init(this);
        createUI();
        createTheme();
    }
    public void createUI() {
        mainForm = new Form("Calculator");
        mainForm.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
        final TextField in1 = new TextField();
        in1.setHint("Enter 1st num!");
        final TextField in2 = new TextField();
        in2.setHint("Enter 2nd num!");
        final Label result = new Label("Result");
           Button add = new Button("+");
        add.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent ae) {
                a = true;
                s = false;
                m = false;
                d = false;
            }
            
        });
          Button sub = new Button("-");
        sub.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent ae) {
                s = true;
                a = false;
                m =false;
                d =false;
            }
            
        });
          Button dev = new Button("/");
        dev.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent ae) {
                d = true;
                a = false;
                s = false;
                m = false;
            }
            
        });
          Button mult = new Button("*");
        mult.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent ae) {
                m = true;
                a = false;
                s = false;
                d = false;
            }
            
        });
        Button equal = new Button("OK");
        equal.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent ae) {
                try {
                    double num1 = Double.parseDouble(in1.getText());
                    double num2 = Double.parseDouble(in2.getText());
                    if(m == true)
                        res = num1 * num2;
                    if(d == true)
                        res = num1 / num2;
                    if(a == true)
                        res = num1 + num2;
                    if(s == true)
                        res = num1 - num2;
                    result.setText(""+res);
                }
                catch(NumberFormatException e) {
                    result.setText("Please enter a num!");
                }
            }
            
        });
        Command exitCommand = new Command("Exit"){

            public void actionPerformed(ActionEvent evt) {
                notifyDestroyed();
            }
            
        };
        mainForm.addComponent(result);
        mainForm.addComponent(in1);
        mainForm.addComponent(in2);
        mainForm.addComponent(add);
        mainForm.addComponent(dev);
        mainForm.addComponent(mult);
        mainForm.addComponent(sub);
        mainForm.addComponent(equal);
        mainForm.addCommand(exitCommand);
        
        
    }
    private void createTheme() { 
        try { 
            Resources r; 
            r = Resources.open("/Theme1.res");
            UIManager.getInstance().setThemeProps(r.getTheme("Theme1")); 
           mainForm.refreshTheme(); 
           mainForm.show();
        } catch (Exception e) { 
            e.printStackTrace(); 
        }
    }

    
    public void pauseApp() {
    }
    
    public void destroyApp(boolean unconditional) {
    }
}
