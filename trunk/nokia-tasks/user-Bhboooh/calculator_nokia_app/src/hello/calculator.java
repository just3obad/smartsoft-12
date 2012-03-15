
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



public class calculator extends MIDlet {
  
      
     double result;
     public int x=0;
     boolean add=false;
     boolean sub=false;
     boolean mult=false;
     boolean div=false;
     int count=0;
     
    Form mainForm; 
    
    public void installTheme(){
        
        try{
        Resources r = Resources.open("/kbhaey.res");
        
        
        UIManager.getInstance().setThemeProps(r.getTheme("Theme"));
        
        mainForm.refreshTheme();
       
        }
        catch(Exception e){
            
        }
    }
    
  
    public void startApp() {
         Display.init(this);
         createMainUI();
         installTheme();
         
    }
    
    public void createMainUI(){
        
        mainForm = new Form("Calculator");
        mainForm.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
     
        final TextField input = new TextField();
        
        input.setConstraint(TextField.NUMERIC);

        final Label output = new Label("Output");
       
       
         Button addition = new Button("+");
        
      
          addition.addActionListener(new ActionListener(){
               
            public void actionPerformed(ActionEvent ae) {
                
               if(count==0){
                   
               output.setText(input.getText());
               input.clear();
               add=true;
               
               }
                else{
                   
                   if(add){
                    result = (Double.parseDouble(input.getText()))+(Double.parseDouble(output.getText()));
                    output.setText(""+result);
                    input.clear();
                    add=false;
                    }
                    if(sub){
                    result = (Double.parseDouble(output.getText()))-(Double.parseDouble(input.getText()));
                    output.setText(""+result);
                    input.clear();
                    sub=false;
                    }
                    if(mult){
                    result = (Double.parseDouble(input.getText()))*(Double.parseDouble(output.getText()));
                    output.setText(""+result);
                    input.clear();
                    mult=false;
                    }
                    if(div){
                    result = (Double.parseDouble(output.getText()))/(Double.parseDouble(input.getText()));
                    output.setText(""+result);
                    input.clear();
                    div=false;
                    }
                    
                 input.clear();
                 add=true;
               
                }
               count++;
            }
            
        });
          
         Button subtraction = new Button("-");
         
          subtraction.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent ae) {
                
                if(count==0){
                    
                output.setText(input.getText());
               input.clear();
               sub=true;
               }
                else{
                    
                   if(add){
                    result = (Double.parseDouble(input.getText()))+(Double.parseDouble(output.getText()));
                    output.setText(""+result);
                    input.clear();
                    add=false;
                    }
                    if(sub){
                    result = (Double.parseDouble(output.getText()))-(Double.parseDouble(input.getText()));
                    output.setText(""+result);
                    input.clear();
                    sub=false;
                    }
                    if(mult){
                    result = (Double.parseDouble(input.getText()))*(Double.parseDouble(output.getText()));
                    output.setText(""+result);
                    input.clear();
                    mult=false;
                    }
                    if(div){
                    result = (Double.parseDouble(output.getText()))/(Double.parseDouble(input.getText()));
                    output.setText(""+result);
                    input.clear();
                    div=false;
                    }
                    
                      input.clear();
               sub=true;
                }
                 count++;
            }
            
        });
          
           Button multiplication = new Button("*");
            multiplication.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent ae) {
                if(count==0){
                output.setText(input.getText());
               input.clear();
               mult=true;
               }
                else{
                    
                    if(add){
                    result = (Double.parseDouble(input.getText()))+(Double.parseDouble(output.getText()));
                    output.setText(""+result);
                    input.clear();
                    add=false;
                    }
                    if(sub){
                    result = (Double.parseDouble(output.getText()))-(Double.parseDouble(input.getText()));
                    output.setText(""+result);
                    input.clear();
                    sub=false;
                    }
                    if(mult){
                    result = (Double.parseDouble(input.getText()))*(Double.parseDouble(output.getText()));
                    output.setText(""+result);
                    input.clear();
                    mult=false;
                    }
                    if(div){
                    result = (Double.parseDouble(output.getText()))/(Double.parseDouble(input.getText()));
                    output.setText(""+result);
                    input.clear();
                    div=false;
                    }
                    
                     input.clear();
               mult=true;
                }
                count++;
            }
            
        });
            
            Button divition = new Button("/"); 
             divition.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent ae) {
                if(count==0){
                output.setText(input.getText());
               input.clear();
               div=true;
               }
                else{
                    
                    if(add){
                    result = (Double.parseDouble(input.getText()))+(Double.parseDouble(output.getText()));
                    output.setText(""+result);
                    input.clear();
                    add=false;
                    }
                    if(sub){
                    result = (Double.parseDouble(output.getText()))-(Double.parseDouble(input.getText()));
                    output.setText(""+result);
                    input.clear();
                    sub=false;
                    }
                    if(mult){
                    result = (Double.parseDouble(input.getText()))*(Double.parseDouble(output.getText()));
                    output.setText(""+result);
                    input.clear();
                    mult=false;
                    }
                    if(div){
                    result = (Double.parseDouble(output.getText()))/(Double.parseDouble(input.getText()));
                    output.setText(""+result);
                    input.clear();
                    div=false;
                    }
                    
                   input.clear();
               div=true;
                }
                count++;
            }
            
        });
            
            
        Button submit = new Button("result");
        
        submit.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent ae) {
                if(input.getText().length()!=0){
                    if(add){
                    result = (Double.parseDouble(input.getText()))+(Double.parseDouble(output.getText()));
                    output.setText(""+result);
                    input.clear();
                    add=false;
                    }
                    if(sub){
                    result = (Double.parseDouble(output.getText()))-(Double.parseDouble(input.getText()));
                    output.setText(""+result);
                    input.clear();
                    sub=false;
                    }
                    if(mult){
                    result = (Double.parseDouble(input.getText()))*(Double.parseDouble(output.getText()));
                    output.setText(""+result);
                    input.clear();
                    mult=false;
                    }
                    if(div){
                    result = (Double.parseDouble(output.getText()))/(Double.parseDouble(input.getText()));
                    output.setText(""+result);
                    input.clear();
                    div=false;
                    }
                }
                else{
                    output.setText("Write Something Please");
                }
            }
            
        });
        
        Command exitCommand = new Command("Exit"){

            public void actionPerformed(ActionEvent evt) {
                notifyDestroyed();
            }
            
        };
        mainForm.addCommand(exitCommand);
        mainForm.addComponent(output);
        mainForm.addComponent(input);
        mainForm.addComponent(submit);
         mainForm.addComponent(addition);
          mainForm.addComponent(subtraction);
           mainForm.addComponent(multiplication);
            mainForm.addComponent(divition);
         mainForm.addCommand(exitCommand);
        mainForm.show();
        
        
        
    }
    public void pauseApp() {
    }
    
    public void destroyApp(boolean unconditional) {
    }
}
