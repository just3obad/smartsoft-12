/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package verification;

import javax.microedition.lcdui.*;
import verification.alerts.*;

/**
 *
 * @author Kiro
 */
public class VerificationForm extends Form implements CommandListener{
    
    public Display display;  //The display of the running program
    public Command backCommand; //This command returns the user to the previous display
    public Command verifyCommand;  //This is the command used by the user to send the code
    public Command resendCommand;  //This command resends the code to the user's email
    public TextField vTF;  //This is the verification text field
    public StringItem stringItem; //this is a string items containing some info to the user
    public IncorrectCodeAlert icA;  //Alert stating that the user entered an incorrect code
    public InvalidCodeAlert ivA;  //Alert stating that the user entered an invalid code
    public ResendAlert rsA;  //Alert to confirm that the code has been resent to the user's email
    public VerificationAlert vfA; //Alert to confirm the verification of the account
    public Form callingForm; //The form displayed this verificationForm
    
    public VerificationForm(Display display, Form form){
        super("ourApp");
        this.display = display;
        this.callingForm = form;
        
        backCommand = new Command("Back", Command.EXIT, 1);
        verifyCommand = new Command("Verify", Command.SCREEN,1);
        resendCommand = new Command("Resend", Command.SCREEN,1);
        stringItem = new StringItem("", "Press the 'Resend' button to resend the verification code to your registered   e-mail adrress.");
            ivA = new InvalidCodeAlert();
            icA = new IncorrectCodeAlert();
            rsA = new ResendAlert();
            vfA = new VerificationAlert();
            vTF = new TextField("Enter Verification Code","", 10,2);
            this.insert(0, vTF);
            this.insert(1,stringItem);
            this.addCommand(backCommand);
            this.addCommand(verifyCommand);
            this.addCommand(resendCommand);
            this.setCommandListener(this);
    }

    public void commandAction(Command c, Displayable d) {
         if (d == this) {
            if (c == backCommand) {
                display.setCurrent(callingForm);
                
            }
            if (c == verifyCommand){
                if(vTF.getString().length()!=10){
                    display.setCurrent(ivA, this);
                    ////////////TODO//////////
                }
                else{
                    display.setCurrent(icA, this);
                }
                
            }
            
            if (c == resendCommand){
               display.setCurrent(rsA, this); 
            }
        }
    }
    
}
