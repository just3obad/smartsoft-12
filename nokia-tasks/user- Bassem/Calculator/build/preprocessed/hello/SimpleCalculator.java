/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hello;

import com.sun.lwuit.*;
import com.sun.lwuit.events.ActionEvent;
import com.sun.lwuit.events.ActionListener;
import com.sun.lwuit.layouts.BoxLayout;
import com.sun.lwuit.plaf.UIManager;
import com.sun.lwuit.util.Resources;
import javax.microedition.midlet.MIDlet;

/**
 * @author Bassem
 */
public class SimpleCalculator extends MIDlet {

     Form mainForm;
     double num1;
     double num2;
     double res;
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
         mainForm.addComponent(Output);
        
         final TextField t1 = new TextField(); 
         t1.setHint("Insert first number");
         t1.setConstraint(TextField.DECIMAL);
         final TextField t2 = new TextField();
         t2.setHint("Insert second number");
         t2.setConstraint(TextField.DECIMAL);
         mainForm.addComponent(t1);
         mainForm.addComponent(t2);
         final Button use = new Button ("Use");
         use.setVisible(false);
         
         use.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent ae) {
               num1=res;
               t1.setText(""+res);
               t2.setText("");
               use.setVisible(false);
            
        }});
         

         Button add = new Button ("+");
         add.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent ae) {
                try{
               num1 = Double.parseDouble(t1.getText());
               num2 = Double.parseDouble(t2.getText());
               res = num1+num2;
               Output.setText(""+res); 
               use.setVisible(true);
               }
                catch(Exception e){
                    Output.setText("Please use valid numbers");
                }
            
        }});
         Button subtract = new Button ("-");
         subtract.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent ae) {
                try{
               num1 = Double.parseDouble(t1.getText());
               num2 = Double.parseDouble(t2.getText());
               res = num1-num2;
               Output.setText(""+res);   
               use.setVisible(true);
               }
                catch(Exception e){
                    Output.setText("Please use valid numbers");
                }
            
        }});
         Button multiply = new Button ("x");
         multiply.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent ae) {
                try{
               num1 = Double.parseDouble(t1.getText());
               num2 = Double.parseDouble(t2.getText());
               res = num1*num2;
               Output.setText(""+res);    
               use.setVisible(true);
               }
                catch(Exception e){
                    Output.setText("Please use valid numbers");
                }
            
        }});
         Button divide = new Button ("÷");
         divide.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent ae) {
                try{
               num1 = Double.parseDouble(t1.getText());
               num2 = Double.parseDouble(t2.getText());
               res = num1/num2;
               Output.setText(""+res);   
               use.setVisible(true);
                }
                catch(Exception e){
                    Output.setText("Please use valid numbers");
                }
            
        }});
         
         
        mainForm.addComponent(add);
        mainForm.addComponent(subtract);        
        mainForm.addComponent(multiply);        
        mainForm.addComponent(divide);   
        mainForm.addComponent(use);
        Command exitCommand = new Command("Exit"){

            public void actionPerformed(ActionEvent evt) {
                notifyDestroyed();
            }
            
        };
        
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
