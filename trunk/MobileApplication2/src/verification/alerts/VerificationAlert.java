/*
 * This alert is displayed to the user to confirm that
 * the verification code has been send to his e-mail
 */
package verification.alerts;

import javax.microedition.lcdui.Alert;
import javax.microedition.lcdui.AlertType;

/**
 *
 * @author Kiro
 */
public class VerificationAlert extends Alert{
    
 
    public VerificationAlert(){
        super("Account Verified");
        this.setString("Your account has been verified");
        this.setType(AlertType.CONFIRMATION);
        this.setTimeout(2000);  //This alert is automatically removed after 2 seconds
    }
    
}
