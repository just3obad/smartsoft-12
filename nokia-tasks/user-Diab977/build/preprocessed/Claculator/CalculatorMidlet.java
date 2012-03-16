/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Claculator;


import com.sun.lwuit.Button;
import com.sun.lwuit.Container;
import com.sun.lwuit.Command;
import com.sun.lwuit.Display;
import com.sun.lwuit.Form;
import com.sun.lwuit.Label;
import com.sun.lwuit.TextArea;
import com.sun.lwuit.TextField;
import com.sun.lwuit.events.ActionEvent;
import com.sun.lwuit.events.ActionListener;
import com.sun.lwuit.layouts.BoxLayout;
import com.sun.lwuit.table.TableLayout;
import javax.microedition.midlet.*;
import com.sun.lwuit.util.Resources;
import com.sun.lwuit.plaf.UIManager;


/**
 * @author DIAB
 */
public class CalculatorMidlet extends MIDlet {
    Form main;
    boolean activeValue;
    int storedValue;
    char operator;
    
    public void startApp() {
        activeValue = false;
        storedValue = 0;
        Display.init(this);
         createUI();
    }
    public void createUI(){
        main = new Form("Calculator");
        main.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
        final TextField number = new TextField("0");
        number.setConstraint(TextField.NUMERIC);
        Container opContainer = new Container();
        opContainer.setLayout(new TableLayout(1,4));
        
        Button plusButton = new Button("+");
        plusButton.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent ae) {
                try {
                if (activeValue)
                {
                    switch (operator)
                         {
                        case '+' : storedValue += Integer.parseInt(number.getText());break;
                        case '-' : storedValue -= Integer.parseInt(number.getText());break;
                        case '*' : storedValue *= Integer.parseInt(number.getText());break;
                        case '/' : storedValue /= Integer.parseInt(number.getText());break;    
                    }
                    number.setText("" + storedValue);
                    operator = '+';
                }
                else {
                    activeValue = true;
                    storedValue = Integer.parseInt(number.getText());
                    operator = '+';
                }}
                catch (Exception e)
                {
                    System.out.println("Arithmatic error");
                }
            }
            
        });
        TableLayout.Constraint c1 = new TableLayout.Constraint();
        c1.setWidthPercentage(25);
        TableLayout.Constraint c2 = new TableLayout.Constraint();
        c2.setWidthPercentage(25);
        TableLayout.Constraint c3 = new TableLayout.Constraint();
        c3.setWidthPercentage(25);
        TableLayout.Constraint c4 = new TableLayout.Constraint();
        c4.setWidthPercentage(25);
        opContainer.addComponent(c1,plusButton);
        
         Button minusButton = new Button("-");
        minusButton.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent ae) {
                try {
                if (activeValue)
                {
                    switch (operator)
                         {
                        case '+' : storedValue += Integer.parseInt(number.getText());break;
                        case '-' : storedValue -= Integer.parseInt(number.getText());break;
                        case '*' : storedValue *= Integer.parseInt(number.getText());break;
                        case '/' : storedValue /= Integer.parseInt(number.getText());break;    
                    }
                    number.setText("" + storedValue);
                    operator = '-';
                }
                else {
                    activeValue = true;
                    storedValue = Integer.parseInt(number.getText());
                    operator = '-';
                }}
                catch (Exception e)
                {
                    System.out.println("Arithmatic error");
                }
            }
            
        });
        opContainer.addComponent(c2, minusButton);
        
         Button mulButton = new Button("X");
        mulButton.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent ae) {
               try {
                if (activeValue)
                {
                    switch (operator)
                         {
                        case '+' : storedValue += Integer.parseInt(number.getText());break;
                        case '-' : storedValue -= Integer.parseInt(number.getText());break;
                        case '*' : storedValue *= Integer.parseInt(number.getText());break;
                        case '/' : storedValue /= Integer.parseInt(number.getText());break;    
                    }
                    number.setText("" + storedValue);
                    operator = '*';
                }
                else {
                    activeValue = true;
                    storedValue = Integer.parseInt(number.getText());
                    operator = '*';
                }}
               catch (Exception e)
                {
                    System.out.println("Arithmatic error");
                }
            }
            
        });
        opContainer.addComponent(c3,mulButton);
        
         Button divButton = new Button("/");
        divButton.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent ae) {
               try {
                if (activeValue)
                {
                    switch (operator)
                         {
                        case '+' : storedValue += Integer.parseInt(number.getText());break;
                        case '-' : storedValue -= Integer.parseInt(number.getText());break;
                        case '*' : storedValue *= Integer.parseInt(number.getText());break;
                        case '/' : storedValue /= Integer.parseInt(number.getText());break;    
                    }
                    number.setText("" + storedValue);
                    operator = '/';
                }
                else {
                    activeValue = true;
                    storedValue = Integer.parseInt(number.getText());
                    operator = '/';
                }}
               catch (Exception e)
                {
                    System.out.println("Arithmatic error");
                }
            }
            
        });
        opContainer.addComponent(c4,divButton);
        
        Container aContainer = new Container();
        aContainer.setLayout(new TableLayout(1,2));
        TableLayout.Constraint c5 = new TableLayout.Constraint();
        c5.setWidthPercentage(50);
        TableLayout.Constraint c6 = new TableLayout.Constraint();
        c6.setWidthPercentage(50);
        Button equalButton = new Button("=");
        equalButton.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent ae) {
                try {
                if (activeValue)
                {
                    switch (operator)
                         {
                        case '+' : storedValue += Integer.parseInt(number.getText());break;
                        case '-' : storedValue -= Integer.parseInt(number.getText());break;
                        case '*' : storedValue *= Integer.parseInt(number.getText());break;
                        case '/' : storedValue /= Integer.parseInt(number.getText());break;    
                    }
                    number.setText("" + storedValue);
                   // operator = '/';
                }}
                catch (Exception e)
                {
                    System.out.println("Arithmatic error");
                }
                
            }
            
        });
        aContainer.addComponent(c5,equalButton);
        
        Button cButton = new Button("C");
        cButton.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent ae) {
                              storedValue = 0;
                              activeValue = false;
                              number.setText("0");
                              
                
            }
            
        });
        aContainer.addComponent(c6,cButton);
        
        Command exit = new Command("Exit"){
            public void actionPerformed(ActionEvent evt) {
                notifyDestroyed();
            }
            
        };
        main.addCommand(exit);
        main.addComponent(number);
        main.addComponent(opContainer);
        main.addComponent(aContainer);
        main.show();
        installTheme();
        
    }
    
    public void pauseApp() {
    }
    
    public void destroyApp(boolean unconditional) {
    }
    private void installTheme() {
        try {
            Resources r;
            r = Resources.open("/cal.res");
            UIManager.getInstance().setThemeProps(r.getTheme("caltheme"));
           main.refreshTheme();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
