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
import javax.microedition.midlet.*;

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
        opContainer.setLayout(new BoxLayout(BoxLayout.X_AXIS));
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
        opContainer.addComponent(plusButton);
        
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
        opContainer.addComponent(minusButton);
        
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
        opContainer.addComponent(mulButton);
        
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
        opContainer.addComponent(divButton);
        
        Container aContainer = new Container();
        aContainer.setLayout(new BoxLayout(BoxLayout.X_AXIS));
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
        aContainer.addComponent(equalButton);
        
        Button cButton = new Button("C");
        cButton.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent ae) {
                              storedValue = 0;
                              activeValue = false;
                              number.setText("0");
                              
                
            }
            
        });
        aContainer.addComponent(cButton);
        
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
        
    }
    
    public void pauseApp() {
    }
    
    public void destroyApp(boolean unconditional) {
    }
}
