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
import javax.microedition.midlet.*;

/**
 * @author MESAI
 */
public class Midlet extends MIDlet {

    Form mainForm; //this is your main Form
    public void startApp() {
         Display.init(this);
         createMainUI();//this method is a way to organize your code ican write whats inside createMainUI()here
         
    }
    public void createMainUI(){
        mainForm = new Form("Tutorial 1");
        mainForm.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
        //// A TextField,it is final to be able to use in the action listener part 
        //and in addition i will no change its value later so no problem
        // but if you need to change the value later you can set as a global variable
        final TextField input = new TextField();
        input.setHint("Please Write Something Here !!!");
        //// A TextArea, it is final to be able to use in the action listener part 
        //and in addition i will no change its value later so no problem
        // but if you need to change the value later you can set as a global variable
        final Label output = new Label("Output");
        //// Abutton to insert
        Button submit = new Button("Ok !!!");
        submit.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent ae) {
                if(input.getText().length()!=0){
               output.setText(input.getText());
                }
                else{
                    output.setText("Write Something Please");
                }
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
        mainForm.addComponent(input);
        mainForm.addComponent(submit);
        mainForm.addCommand(exitCommand);
        // last thing you need to shoe all this on the screen
        mainForm.show();
        
        
        
    }
    public void pauseApp() {
    }
    
    public void destroyApp(boolean unconditional) {
    }
}
