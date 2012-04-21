/*
 * This alert is displayed when the user enters
 * an incorrect verification code
 */
package verification.alerts;

import javax.microedition.lcdui.Alert;
import javax.microedition.lcdui.AlertType;

/**
 *
 * @author Kiro
 */
public class IncorrectCodeAlert extends Alert{
    
 
    public IncorrectCodeAlert(){
        super("Incorrect Verification Code");
        this.setString("Incorrect Verification Code");
        this.setType(AlertType.ERROR);
        this.setTimeout(2000);  //this alert is automatically removed after 2 seconds
    }
    
}
