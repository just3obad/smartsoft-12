
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
import javax.microedition.midlet.*;


public class Midlet extends MIDlet {

    Form mainForm; 
    public void startApp() {
         Display.init(this);
         createMainUI();
         
    }
    public void createMainUI(){
        mainForm = new Form("Calculator");
        mainForm.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
      
        final TextField input1 = new TextField();
        final TextField input2 = new TextField();
        input1.setHint("Enter First Input");
        input2.setHint("Enter 2nd Input");
        input1.setConstraint(TextField.NUMERIC);
        input2.setConstraint(TextField.NUMERIC);
     
        final Label output = new Label("Output");
        Button plus = new Button("+");
        Button minus = new Button ("-");
        Button mult = new Button("X");
        Button div = new Button("/");
       
        
        
        plus.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent ae) {
                try{
                if(input1.getText().length()!=0 && input2.getText().length()!=0){
               int sum =  Integer.parseInt(input1.getText()) + Integer.parseInt(input2.getText());
               output.setText(sum+"");
               
               
                }
                
                else{
                    output.setText("Write Something Please");
                }
                }
                catch (Exception e)
                {
                    output.setText("Please Enter a Valid input");
                }
            }
            
            
        });
        
        minus.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent ae) {
                try{
                if(input1.getText().length()!=0 && input2.getText().length()!=0){
               int sum =  Integer.parseInt(input1.getText()) - Integer.parseInt(input2.getText());
               output.setText(sum+"");
               
               
                }
                
                else{
                    output.setText("Write Something Please");
                }
                }
                catch (Exception e)
                {
                    output.setText("Please Enter a Valid input");
                }
                input1.clear();
                input2.clear();
            }
            
            
        });
        
        
        
        mult.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent ae) {
                try{
                if(input1.getText().length()!=0 && input2.getText().length()!=0){
               int sum =  Integer.parseInt(input1.getText()) * Integer.parseInt(input2.getText());
               output.setText(sum+"");
               
               
                }
                
                else{
                    output.setText("Write Something Please");
                }
                }
                catch (Exception e)
                {
                    output.setText("Please Enter a Valid input");
                }
                input1.clear();
                input2.clear();
                
            }
            
            
        });
            div.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent ae) {
                try{
                if(input1.getText().length()!=0 && input2.getText().length()!=0){
               int sum =  Integer.parseInt(input1.getText()) / Integer.parseInt(input2.getText());
               output.setText(sum+"");
               
               
                }
                
                else{
                    output.setText("Write Something Please");
                }
                }
                catch (Exception e)
                {
                    output.setText("Please Enter a Valid input");
                }
                input1.clear();
                input2.clear();
            }
            
            
        });
        // a command to exit
        Command exitCommand = new Command("Exit"){

            public void actionPerformed(ActionEvent evt) {
                notifyDestroyed();
            }
            
        };
    
        
        
        mainForm.addCommand(exitCommand);
        mainForm.addComponent(output);
        mainForm.addComponent(input1);
        mainForm.addComponent(input2);
        mainForm.addComponent(plus);
        mainForm.addComponent(minus);
        mainForm.addComponent(mult);
        mainForm.addComponent(div);
        
        mainForm.addCommand(exitCommand);
        mainForm.show();
        
        
        
    }
    public void pauseApp() {
    }
    
    public void destroyApp(boolean unconditional) {
    }
}
