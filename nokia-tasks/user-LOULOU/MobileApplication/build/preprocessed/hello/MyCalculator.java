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
import com.sun.lwuit.TextArea;
import com.sun.lwuit.TextField;
import com.sun.lwuit.events.ActionEvent;
import com.sun.lwuit.events.ActionListener;
import com.sun.lwuit.layouts.BoxLayout;
import com.sun.lwuit.layouts.GridLayout;
import com.sun.lwuit.plaf.UIManager;
import com.sun.lwuit.util.Resources;
import javax.microedition.midlet.*;

/**
 * @author MESAI
 */
public class MyCalculator extends MIDlet {

    Form mainForm; //this is your main Form
    Form equalForm;
    double result;
    char operation;
    double operand1;
    double operand2;

    public void startApp() {
        Display.init(this);
        createMainUI();//this method is a way to organize your code ican write whats inside createMainUI()here
        installTheme();
    }

    public void createMainUI() {
        mainForm = new Form("Calculator");
        mainForm.setLayout(new GridLayout(8,1));
        //equalForm = new Form();
        //equalForm.setLayout(new BoxLayout(BoxLayout.X_AXIS));
        
        final TextField input1 = new TextField();
        input1.setConstraint(TextField.NUMERIC);
        input1.setHint("Enter first operand here!!!");
      
        final TextField input2 = new TextField();
        input2.setConstraint(TextField.NUMERIC);
        input2.setHint("Enter second operand here!!!");
        
       
        final Label output = new Label("0");
       
        Button equals = new Button("=");
        Button plus = new Button("+");
        Button minus = new Button("-");
        Button multiply = new Button("*");
        Button divide = new Button("/");
       
        plus.addActionListener(new ActionListener() {
            
            public void actionPerformed(ActionEvent ae) {
                   operand1 = Double.parseDouble(input1.getText());
                   operand2 = Double.parseDouble(input2.getText());
                   operation = '+';
                   input1.setText("");
                   input2.setText("");
            }
        });
        minus.addActionListener(new ActionListener() {
            
            public void actionPerformed(ActionEvent ae) {
                   operand1 = Double.parseDouble(input1.getText());
                   operand2 = Double.parseDouble(input2.getText());
                   operation = '-';
                   input1.setText("");
                   input2.setText("");
            }
        });
        multiply.addActionListener(new ActionListener() {
            
            public void actionPerformed(ActionEvent ae) {
                   operand1 = Double.parseDouble(input1.getText());
                   operand2 = Double.parseDouble(input2.getText());
                   operation = '*';
                   input1.setText("");
                   input2.setText("");
            }
        });
        divide.addActionListener(new ActionListener() {
            
            public void actionPerformed(ActionEvent ae) {
                   operand1 = Double.parseDouble(input1.getText());
                   operand2 = Double.parseDouble(input2.getText());
                   operation = '/';
                   input1.setText("");
                   input2.setText("");
            }
        });
        equals.addActionListener(new ActionListener() {
            
            public void actionPerformed(ActionEvent ae) {
                switch(operation) {
                    case '+': result = operand1 + operand2; break;
                    case '-': result = operand1 - operand2; break;
                    case '*': result = operand1 * operand2; break;
                    case '/': result = operand1 / operand2; break;
                }
                output.setText(result + "");
            }
        });
        
        // a command to exit
        Command exitCommand = new Command("Exit") {

            public void actionPerformed(ActionEvent evt) {
                notifyDestroyed();
            }
        };
        mainForm.addCommand(exitCommand);
        mainForm.addComponent(input1);
        mainForm.addComponent(input2);
        mainForm.addComponent(plus);
        mainForm.addComponent(minus);
        mainForm.addComponent(multiply);
        mainForm.addComponent(divide);
        //mainForm.addComponent(equalForm);
        mainForm.addComponent(equals);
        mainForm.addComponent(output);
        mainForm.addCommand(exitCommand);
        // last thing you need to show all this on the screen



    }

    private void installTheme() {
// This is not the normal way to do this.
// Usually you load a theme from a file.
// UIManager uim = UIManager.getInstance();
// Hashtable ht = new Hashtable();
// ht.put("sel#" + Style.BG_COLOR, "ffffff");
// ht.put(Style.BG_COLOR, "ffffff");
// ht.put(Style.FG_COLOR, "000000");
// uim.setThemeProps(ht);
        try {
            Resources r;

            r = Resources.open("/calculatorTheme.res");


            UIManager.getInstance().setThemeProps(r.getTheme("Calculator"));
// UIManager.getInstance().setResourceBundle(r.getL10N("localize", "en"));
// UIManager.getInstance().setResourceBundle(r.getL10N("localize", "en"));

// mHomeForm.refreshTheme();
            mainForm.refreshTheme();
            mainForm.show();
        } catch (Exception e) {
            System.out.println("Error");
        }
    }

    public void pauseApp() {
    }

    public void destroyApp(boolean unconditional) {
    }
}
