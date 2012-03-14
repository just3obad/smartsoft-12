/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hello;

/**
 *
 * @author Menisy
 */


import com.sun.lwuit.Button;
import com.sun.lwuit.Command;
import com.sun.lwuit.Component;
import com.sun.lwuit.Display;
import com.sun.lwuit.Font;
import com.sun.lwuit.Form;
import com.sun.lwuit.Image;
import com.sun.lwuit.Label;
import com.sun.lwuit.TextArea;
import com.sun.lwuit.TextField;
import com.sun.lwuit.events.ActionEvent;
import com.sun.lwuit.events.ActionListener;
import com.sun.lwuit.layouts.FlowLayout;
import com.sun.lwuit.table.TableLayout;

import com.sun.lwuit.layouts.GridLayout;
import javax.microedition.midlet.*;
public class Calculator extends Midlet{
      public void startApp() {
         Display.init(this);
         createMainUI();//this method is a way to organize your code ican write whats inside createMainUI()here
         
    }
     
      double firstOp=0;
      double secondOp=0;
      double xxx = 0;
      boolean wasDig;
      String digits="";
      TextArea textarea;
      Label lbl1;
      boolean firstSet = false;
      boolean wasResult = false;
      int operation; //1 add //2 subtract //3 multiply //4 divide 
    public void createMainUI(){
        mainForm = new Form("Calculator");
       mainForm.setLayout(new FlowLayout());
        
    
         textarea = new TextArea();
        textarea.setX(10);
     
        
        textarea.setEditable(false);
        textarea.setPreferredH(60);
        textarea.setPreferredW(240);
         lbl1 = new Label();
         lbl1.getStyle().setBgColor(0x7FC9FF);
         lbl1.getStyle().setFgColor(0xFFFFFF);
        lbl1.setPreferredH(20);
        lbl1.setPreferredW(240);
        
        mainForm.addComponent(textarea);
        mainForm.addComponent(lbl1);
        Button [] numButts = new Button[16];
        int c = 0;
        int num = 1;
        for(int i=0;i<numButts.length;i++){
            
            final Button l = new Button();
            int x = i+1;
            if(x%4==0){
                c++;
                switch(c){
                        case 1:l.setText("+"); break;
                        case 2:l.setText("-"); break;
                        case 3:l.setText("x"); break;
                        case 4:l.setText("/"); break;
            }
                Label lbl = new Label();
                lbl.setPreferredH(30);
                lbl.setPreferredW(15);
                mainForm.addComponent(lbl);
            }
            else if(i==13){
                l.setText(".");
            }else{
            l.setText(num+"");
            num++;
            if(num==10) num=0;
            }
            
            
           
            l.addActionListener(new ActionListener(){
        

            public void actionPerformed(ActionEvent ae) {
                if("+-/x".indexOf(l.getText())>=0){
                        if(digits.length()==0) return;        
                        firstOp = Double.parseDouble(digits);
                        String s = l.getText();
                       if(s.equals("+")) operation=1;
                    else if(s.equals("-")) operation = 2;
                    else if(s.equals("x")) operation = 3;
                    else if(s.equals("/")) operation = 4;
                   digits = "";
                    
                   
                   
                    
                   
                }
                else{
                    if(digits.length()<10){
                digits+=l.getText();
                wasDig = true;
                    updateTextArea();
                    }
                    else{
                        return;
                    }
                }
            }
            
        });
            numButts[i] = l;
            numButts[i].getUnselectedStyle().setAlignment(Component.CENTER);
            numButts[i].getSelectedStyle().setAlignment(Component.CENTER);
            numButts[i].getSelectedStyle().setFgColor(0xFF00000);
            numButts[i].setPreferredH(25);
            numButts[i].setPreferredW(50);
            if(i==14){
               Label lbl = new Label();
                lbl.setPreferredH(28);
                lbl.setPreferredW(50);
                mainForm.addComponent(lbl);
            }else
            mainForm.addComponent(numButts[i]);
        }
        Button clear = new Button("C");
        clear.getUnselectedStyle().setAlignment(Component.CENTER);
        
        clear.addActionListener(new ActionListener(){
        

            public void actionPerformed(ActionEvent ae) {
               firstOp = 0;
               secondOp = 0;
               operation = 0;
               wasDig = false;
               digits = "";
               lbl1.setText("");
               textarea.setText("");
               firstSet = false;
            }
            
        });
        clear.setPreferredH(30);
        clear.setPreferredW(50);
        mainForm.addComponent(clear);
         Label lbl = new Label();
                lbl.setPreferredH(30);
                lbl.setPreferredW(50);
                mainForm.addComponent(lbl);
        Button eq = new Button("=");
        eq.getUnselectedStyle().setAlignment(Component.CENTER);
        eq.addActionListener(new ActionListener(){
                    public void actionPerformed(ActionEvent ae) {
                if(digits.length()==0) return;
                secondOp = Double.parseDouble(digits);
                doMath();
                digits = "";
            }
            
        });
        eq.setPreferredH(30);
        eq.setPreferredW(70);
        mainForm.addComponent(eq);
          Command exitCommand = new Command("Exit"){

            public void actionPerformed(ActionEvent evt) {
                notifyDestroyed();
            }
            
        };
        mainForm.addCommand(exitCommand);
        mainForm.show();
        
        
        
    }
    public void updateTextArea(){
        lbl1.setText(digits);
    }
    public void doMath(){
        
        if(operation == 0) return;
        else if(operation == 1) {
            xxx = firstOp + secondOp;
            textarea.setText(textarea.getText()+"\n\n"+firstOp+"\n+\n"+secondOp+"\n_________\n"+ xxx);}
        else if(operation == 2) {xxx = firstOp - secondOp;
            textarea.setText(textarea.getText()+"\n\n"+firstOp+"\n-\n"+secondOp+"\n_________\n"+ xxx);}
        else if(operation == 3) {xxx = firstOp * secondOp;
            textarea.setText(textarea.getText()+"\n\n"+firstOp+"\nx\n"+secondOp+"\n_________\n"+ xxx);}
        else {xxx = firstOp / secondOp;
            textarea.setText(textarea.getText()+"\n\n"+firstOp+"\n/\n"+secondOp+"\n_________\n"+ xxx);}
        lbl1.setText(xxx+"");
        firstOp = 0;
        secondOp = 0;
        
    }
    public void pauseApp() {
    }
    
    public void destroyApp(boolean unconditional) {
    }
    
}
