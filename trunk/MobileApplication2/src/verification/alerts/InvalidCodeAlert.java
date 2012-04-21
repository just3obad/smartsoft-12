/*
 * This Alert us the alert displayed to the user when he enters
 * a verification code less than 10 characters.
 */
package verification.alerts;

import javax.microedition.lcdui.Alert;
import javax.microedition.lcdui.AlertType;

/**
 *
 * @author Kiro
 */
public class InvalidCodeAlert extends Alert{
    
 
    public InvalidCodeAlert(){
        super("Invalid Verification Code");
        this.setString("The verification code can't be less than 10 digits");
        this.setType(AlertType.ERROR);
        this.setTimeout(2000);  //The alert is automatically removed after 2 seconds
    }
    
}
