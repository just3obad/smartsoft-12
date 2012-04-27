/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hello;

import java.util.Vector;
import javax.microedition.lcdui.CustomItem;

import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.ImageItem;
import javax.microedition.lcdui.*;
import javax.microedition.midlet.MIDlet;
import org.json.me.*;

/**
 *
 * @author Menisy
 */
public class reqItem extends CustomItem {

    int n, height = 50;
    int  req_id;
    boolean lonely;
    String frName; 
    String frEmail; 
    
    HelloMIDlet MIDlet; //MIDlet to which the custom item belongs to

    
    public reqItem(int id,String fN,String fE,boolean lon){
        super(null);
         id = req_id;
        fN = frName;
        fE = frEmail;
        lonely = lon;
    }
    public reqItem(String jsonString,HelloMIDlet mid) {
        super(null);
        fromJson(jsonString);
        MIDlet = mid;
    }
    
    public void fromJson(String js){
        try {
			JSONObject json = new JSONObject(js);
			req_id = json.getInt("id");
			frName = json.getString("user_name");
			frEmail=(json.getString("email"));
                        
                        System.out.println("printttt "+req_id+" "+frName+" "+" "+" "+frEmail);
                        
		} catch (JSONException ex) {
			ex.printStackTrace();
		}
	
    }

    public int getMinContentWidth() {
        return 240;
    }

    protected void pointerPressed(int x, int y) {
       // System.out.println("pressed");
    }

    protected void pointerReleased(int x, int y) {
      //  System.out.println("released");
        if(!lonely)
        MIDlet.viewReq(req_id,frName,frEmail);
    }

    public int getMinContentHeight() {
        //int num = content.length() / 18; // estimating number of line roughly as each line carries around 18 characters
        return 200 + 105;  // adjusting the height according to number of lines
    }

    public int getPrefContentWidth(int width) {
        return getMinContentWidth();
    }

    public int getPrefContentHeight(int height) {
        return getMinContentHeight();
    }
    //Component Paint method that draws the needed values.

    public void paint(Graphics g, int w, int h) {
        g.drawRect(0, 0, w - 1, h - 1);

        g.setColor(53, 177, 255);
        g.drawString(frName, 5, 0, 0);// Username
        g.setColor(0, 0, 0);
        g.drawString(frEmail, 5, 0, 0);// Email
        g.setColor(38, 193, 255);


    }
}



