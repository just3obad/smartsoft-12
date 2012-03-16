/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hello;

import com.sun.lwuit.*;
import com.sun.lwuit.events.ActionEvent;
import com.sun.lwuit.events.ActionListener;
import com.sun.lwuit.layouts.BoxLayout;
import com.sun.lwuit.layouts.GridLayout;
import com.sun.lwuit.plaf.UIManager;
import com.sun.lwuit.util.Resources;
import javax.microedition.midlet.*;

/**
 * @author Bassem
 */
public class calculator extends MIDlet {

     Form mainForm;
     double num1;
     double num2;
     int oper;
    public void startApp() {
        Display.init(this);
         createMainUI();
         installTheme();
    }
    
    public void createMainUI(){
         mainForm = new Form("Calculator");
         mainForm.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
         final Label Output = new Label ("0");
         Container o = new Container (new BoxLayout(BoxLayout.Y_AXIS));
         o.addComponent(Output);
         mainForm.addComponent(o);
         Container buttons = new Container(new GridLayout(4, 4));
         Button one = new Button ("1");
         one.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent ae) {
                num1= (num1*10)+1;
                Output.setText(""+num1);
            }
            
        });
         Button two = new Button ("2");
         two.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent ae) {
                num1= (num1*10)+2;
                Output.setText(""+num1);
            }
            
        });
         Button three = new Button ("3");
         three.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent ae) {
                num1= (num1*10)+3;
                Output.setText(""+num1);
            }
            
        });
         Button four = new Button ("4");
         four.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent ae) {
                num1= (num1*10)+4;
                Output.setText(""+num1);
            }
            
        });
         Button five = new Button ("5");
         five.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent ae) {
                num1= (num1*10)+5;
                Output.setText(""+num1);
            }
            
        });
         Button six = new Button ("6");
         six.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent ae) {
                num1= (num1*10)+6;
                Output.setText(""+num1);
            }
            
        });
         Button seven = new Button ("7");
         seven.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent ae) {
                num1= (num1*10)+7;
                Output.setText(""+num1);
            }
            
        });
         Button eight = new Button ("8");
         eight.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent ae) {
                num1= (num1*10)+8;
                Output.setText(""+num1);
            }
            
        });
         Button nine = new Button ("9");
         nine.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae) {
                num1= (num1*10)+9;
                Output.setText(""+num1);
            }
            
        });
         Button zero = new Button ("0");
         zero.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae) {
                num1= (num1*10);
                Output.setText(""+num1);
            }
            
        });
         Button plus = new Button ("+");
         plus.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent ae) {
                num2=num1;
                num1=0;
                Output.setText("0");
                oper=1;
            }
            
        });
         Button minus = new Button ("-");
         minus.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent ae) {
                num2=num1;
                num1=0;
                Output.setText("0");
                oper=2;
            }
            
        });
         Button times = new Button ("x");
         times.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent ae) {
                num2=num1;
                num1=0;
                Output.setText("0");
                oper=3;
            }
            
        });
         Button divide = new Button ("÷");
         divide.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent ae) {
                num2=num1;
                num1=0;
                Output.setText("0");
                oper=4;
            }
            
        });
         Button equal = new Button ("=");
         equal.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent ae) {
                double result=0;
                if(oper==1){
                    result = num1+num2;
                    Output.setText(""+(result));
                    num1=0;
                    oper=1; 
                }
                else if(oper==2){
                    result = num2-num1;
                    Output.setText(""+(result));
                    num1=0;
                    oper=1; 
                }
                if(oper==3){
                    
                    result = num1*num2;
                    Output.setText(""+(result));
                    num1=0;
                    oper=1; 
                }
                if(oper==4){
                    try{
                    result = num2/num1;
                    Output.setText(""+(result));
                    }
                    catch (Exception e){
                        Output.setText("∞");
                    }
                    num1=0;
                    oper=1; 
                }
                num2= result;
                System.out.println(Output.getText());
            }
            
        });
         Button ce = new Button ("CE");
         ce.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent ae) {
                num1=0;
                num2=0;
                Output.setText("0");
                oper=1;
            }
            
        }); 
        Command exitCommand = new Command("Exit"){

            public void actionPerformed(ActionEvent evt) {
                notifyDestroyed();
            }
            
        };
        Button dec = new Button(".");
        
        
        buttons.addComponent(one);
        buttons.addComponent(two);
        buttons.addComponent(three);
        buttons.addComponent(plus);
        buttons.addComponent(four);
        buttons.addComponent(five);
        buttons.addComponent(six);
        buttons.addComponent(minus);
        buttons.addComponent(seven);
        buttons.addComponent(eight);
        buttons.addComponent(nine);
        buttons.addComponent(times);
        buttons.addComponent(zero);
        buttons.addComponent(dec);
        buttons.addComponent(ce);
        mainForm.addComponent(equal);
        buttons.addComponent(divide);
        
        
        mainForm.addComponent(buttons);
        mainForm.addCommand(exitCommand);
        mainForm.show();

         
         
    }
     private void installTheme() {
        try {
            Resources r;
            r = Resources.open("/theme.res");//you open the.res file
            UIManager.getInstance().setThemeProps(r.getTheme("Theme 1"));
           mainForm.refreshTheme();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void pauseApp() {
    }
    
    public void destroyApp(boolean unconditional) {
    }
}
