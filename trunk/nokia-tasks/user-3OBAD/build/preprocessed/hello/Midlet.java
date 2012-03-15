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
import javax.microedition.midlet.*;

public class Midlet extends MIDlet {

    Form mainForm;
    public void startApp() {
         Display.init(this);
         createMainUI();
         
    }
    public void createMainUI(){
        mainForm = new Form("Simple Calculator");
        mainForm.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
        
        final TextField input1 = new TextField();
       // String in1 = input1.getText();
        //final int n1 = Integer.parseInt(in1);
        input1.setHint("First number here");
        
        final TextField input2 = new TextField();
        //String in2 = input2.getText();
        //final int n2 = Integer.parseInt(in2);
        input2.setHint("Second number here");
        
        final Label result = new Label("Result here");
        
        Button add = new Button("+");
        Button sub = new Button("-");
        Button mul = new Button("X");
        Button div = new Button("÷");
        
        add.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent ae)  {

                    try{
                        result.setText(Integer.parseInt(input1.getText()) + Integer.parseInt(input2.getText()) + "");
                    }
                    catch(NumberFormatException e){
                        result.setText("input error");
                    }
            }
            
        });
        
         div.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent ae)  {

                    try{
                        result.setText(Integer.parseInt(input1.getText()) / Integer.parseInt(input2.getText()) + "");
                    }
                    catch(NumberFormatException e){
                        result.setText("input error");
                    }
            }
            
        });
         
          sub.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent ae)  {

                    try{
                        result.setText(Integer.parseInt(input1.getText()) - Integer.parseInt(input2.getText()) + "");
                    }
                    catch(NumberFormatException e){
                        result.setText("input error");
                    }
            }
            
        });
          
           mul.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent ae)  {

                    try{
                        result.setText(Integer.parseInt(input1.getText()) * Integer.parseInt(input2.getText()) + "");
                    }
                    catch(NumberFormatException e){
                        result.setText("input error");
                    }
            }
            
        });
        
      
        Command exitCommand = new Command("Exit"){

            public void actionPerformed(ActionEvent evt) {
                notifyDestroyed();
            }
            
        };
        mainForm.addCommand(exitCommand);
        mainForm.addComponent(result);
        mainForm.addComponent(input1);
        mainForm.addComponent(input2);
        mainForm.addComponent(add);
        mainForm.addComponent(sub);
        mainForm.addComponent(mul);
        mainForm.addComponent(div);
        mainForm.addCommand(exitCommand);
        mainForm.show();
        
        
        
    }
    public void pauseApp() {
    }
    
    public void destroyApp(boolean unconditional) {
    }
}
