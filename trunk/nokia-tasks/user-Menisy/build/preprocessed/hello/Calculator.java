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
import com.sun.lwuit.Container;
import com.sun.lwuit.Display;
import com.sun.lwuit.Form;
import com.sun.lwuit.Label;
import com.sun.lwuit.TextArea;
import com.sun.lwuit.events.ActionEvent;
import com.sun.lwuit.events.ActionListener;
import com.sun.lwuit.layouts.BoxLayout;


import com.sun.lwuit.layouts.GridLayout;

public class Calculator extends Midlet{
      public void startApp() {
         Display.init(this);
         createMainUI();
         
    }
     
      String digits="";
      TextArea textarea;
      Label resultLabel;
      double result = 0;
      double temp = 0;
      boolean firstTime = true;
      boolean resultOut = false;
      boolean firstScreen = true;
      boolean comeBack = true;
      int lastOperation = 1; //1 add //2 subtract //3 multiply //4 divide 
    public void createMainUI(){
        mainForm = new Form("Calculator");
       mainForm.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
       Container con1 = new Container();
       Container con2 = new Container();
       Container con3 = new Container();
       con1.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
       con1.setPreferredH(130);
       con2.setLayout(new GridLayout(5,4));
       con2.setPreferredH(130);
         textarea = new TextArea();
        textarea.setX(10);
  
     //  tf.setInputMode("Numeric");
     
        textarea.setEditable(false);
        textarea.setPreferredH(100);
        textarea.setPreferredW(240);
         resultLabel = new Label();
         resultLabel.getStyle().setBgColor(0x808080);
         resultLabel.getStyle().setFgColor(0xFFFFFF);
        resultLabel.setPreferredH(30);
        resultLabel.setPreferredW(240);
       
        con1.addComponent(textarea);
        con3.addComponent(resultLabel);
        con1.setPreferredH(90);
        mainForm.addComponent(con1);
        
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
            }
            else if(i==13){
                l.setText(".");
            }
            else if(i==14){

            con2.addComponent(new Label());
            continue;
            }else {
            l.setText(num+"");
            num++;
            if(num==10){ num=0; }
            }
            
            
           
            l.addActionListener(new ActionListener(){
        

            public void actionPerformed(ActionEvent ae) {
                if("+-/x".indexOf(l.getText())>=0){
                    if(l.getText().equals("-")&& firstTime){
                        firstTime = false;
                        digits="-";
                        updateTextArea();
                        return;
                    }
                       
                       if(resultOut){
                           
                           String s = l.getText();
                       if(s.equals("+")) lastOperation=1;
                    else if(s.equals("-")) lastOperation = 2;
                    else if(s.equals("x")) lastOperation = 3;
                    else if(s.equals("/")) lastOperation = 4;
                           resultOut = false;
                           return;
                           
                       }
                       
                        if(digits.length()==0) return;
                        temp = Double.parseDouble(digits);
                        doPrevCalc();
                        
                         
                        String s = l.getText();
                       if(s.equals("+")) lastOperation=1;
                    else if(s.equals("-")) lastOperation = 2;
                    else if(s.equals("x")) lastOperation = 3;
                    else if(s.equals("/")) lastOperation = 4;
                   digits = "";
                   updateTextArea();
                   
                }else if(l.getText().equals(".") && resultLabel.getText().indexOf(".")>=1){
                    return;
                }
                else{
                    if(digits.length()<10){
                digits+=l.getText();
                firstTime = false;
                    updateTextArea();
                    }
                    
                }
            }
            
        });
            numButts[i] = l;
            numButts[i].getUnselectedStyle().setAlignment(Component.CENTER);
            numButts[i].getSelectedStyle().setAlignment(Component.CENTER);
            numButts[i].getSelectedStyle().setFgColor(0xFF00000);
            numButts[i].setPreferredH(10);
            numButts[i].setPreferredW(20);
            
            con2.addComponent(numButts[i]);
        }
        Button clear = new Button("C");
         clear.getUnselectedStyle().setAlignment(Component.CENTER);
            clear.getSelectedStyle().setAlignment(Component.CENTER);
            clear.getSelectedStyle().setFgColor(0xFF00000);
        
        
        clear.addActionListener(new ActionListener(){
        

            public void actionPerformed(ActionEvent ae) {
               result = 0;
               temp = 0;
               lastOperation = 1;
               digits = "";
               resultLabel.setText("");
               textarea.setText("");
               firstTime = true;
               resultOut = false;
               firstScreen = true;
            }
            
        });
        Label l = new Label();
        
        clear.setPreferredH(30);
        clear.setPreferredW(50);
        con2.addComponent(clear);
        con2.addComponent(new Label());
        con2.addComponent(l);
        Button eq = new Button("=");
         
            eq.getSelectedStyle().setAlignment(Component.CENTER);
            eq.getSelectedStyle().setFgColor(0xFF00000);
        eq.getUnselectedStyle().setAlignment(Component.CENTER);
        eq.addActionListener(new ActionListener(){
                    public void actionPerformed(ActionEvent ae) {
                if(digits.length()==0) return;
                temp = Double.parseDouble(digits);
                doPrevCalc();
              temp = 0;
            lastOperation = 1;  
                digits = "";
                
                resultOut = true;
            }
            
        });
        eq.setPreferredH(30);
        eq.setPreferredW(70);
        con2.addComponent(eq);
          Command exitCommand = new Command("Exit"){

            public void actionPerformed(ActionEvent evt) {
                notifyDestroyed();
            }
            
        };
        mainForm.addComponent(con3);
        mainForm.addComponent(con2);
        mainForm.addCommand(exitCommand);
        mainForm.show();
        
        
        
    }
    public void updateTextArea(){
        resultLabel.setText(digits);
    }
    public void doPrevCalc(){
        
        if(lastOperation == 0) return;
        else if(lastOperation == 1) {
            double firstOp = result;
            double secondOp = temp;
            result = result + temp;
            if(!firstScreen)
            textarea.setText(textarea.getText()+"\n\n"+firstOp+"\n+\n"+secondOp+"\n_________\n"+ result);}
        else if(lastOperation == 2) {
            double firstOp = result;
            double secondOp = temp;
            result = result - temp;
            if(!firstScreen)
            textarea.setText(textarea.getText()+"\n\n"+firstOp+"\n-\n"+secondOp+"\n_________\n"+ result);}
        else if(lastOperation == 3) {
            double firstOp = result;
            double secondOp = temp;
            result = result * temp;
            if(!firstScreen)
            textarea.setText(textarea.getText()+"\n\n"+firstOp+"\nx\n"+secondOp+"\n_________\n"+ result);}
        else {
            double firstOp = result;
            double secondOp = temp;
            result = result / temp;
            if(!firstScreen)
            textarea.setText(textarea.getText()+"\n\n"+firstOp+"\n/\n"+secondOp+"\n_________\n"+ result);}
        if(firstScreen) firstScreen = false;
        resultLabel.setText(result+"");
        temp = 0;
    
        
    }
    public void pauseApp() {
    }
   
    public void destroyApp(boolean unconditional) {
    }
    
}
