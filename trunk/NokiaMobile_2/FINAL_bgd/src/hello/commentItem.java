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
public class commentItem extends CustomItem {

    int n, height = 50;
    String comment_id;
    int lonely;
    String user; //username of the comment
    String content; //content of the comment
    String ups, downs; //number of thumbs up and down
    HelloMIDlet MIDlet; //MIDlet to which the custom item belongs to

    
    public commentItem(String id,String us,String cont,String up,String down,int n){
        super(null);
        comment_id = id;
        user = us;
        content = cont;
        ups = up;
        downs = down;
        lonely = n;
    }
    public commentItem(String jsonString,HelloMIDlet mid) {
        super(null);
        fromJson(jsonString);
        MIDlet = mid;
    }
    
    public void fromJson(String js){
        try {
			JSONObject json = new JSONObject(js);
			comment_id = json.getInt("id")+"";
			user = json.getString("user_name");
			content=(json.getString("content"));
                        ups = json.getString("ups");
                        downs = json.getString("downs");
                        System.out.println("printttt "+comment_id+" "+user+" "+ups+" "+downs+" "+content);
                        
		} catch (JSONException ex) {
			ex.printStackTrace();
		}
	
    }
    // helper method for wrapping long Strings into multiple lines.

    static Vector wrap(String text, Font font, int width) {
        Vector result = new Vector();
        if (text == null) {
            return result;
        }

        boolean hasMore = true;

        // The current index of the cursor
        int current = 0;

        // The next line break index
        int lineBreak = -1;

        // The space after line break
        int nextSpace = -1;

        while (hasMore) {
            //Find the line break
            while (true) {
                lineBreak = nextSpace;
                if (lineBreak == text.length() - 1) {
                    // We have reached the last line
                    hasMore = false;
                    break;
                } else {
                    nextSpace = text.indexOf(' ', lineBreak + 1);
                    if (nextSpace == -1) {
                        nextSpace = text.length() - 1;
                    }
                    int linewidth = font.substringWidth(text, current, nextSpace - current);
                    // If too long, break out of the find loop
                    if (linewidth > width) {
                        break;
                    }
                }
            }
            String line = text.substring(current, lineBreak + 1);
            result.addElement(line);
            current = lineBreak + 1;
        }
        return result;
    }
    // Method used to divide a long String into multiple lines, uses wrap as its helper.

    static public int drawMultilineString(Graphics g, Font font, String str, int x, int y, int anchor, int width) {

        Vector lines = wrap(str, font, width);
        for (int i = 0; i < lines.size(); i++) {
            int liney = y + (i * font.getHeight());
            g.drawString((String) lines.elementAt(i), x, liney, anchor);
        }

        return y + (lines.size() * font.getHeight());
    }

    public int getMinContentWidth() {
        return 240;
    }

    protected void pointerPressed(int x, int y) {
       // System.out.println("pressed");
    }

    protected void pointerReleased(int x, int y) {
      //  System.out.println("released");
        if(lonely == 0)
        MIDlet.viewCommentOne(comment_id,user,content,ups,downs);
    }

    public int getMinContentHeight() {
        int num = content.length() / 18; // estimating number of line roughly as each line carries around 18 characters
        return (num * 21) + 105;  // adjusting the height according to number of lines
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
        g.drawString(user, 5, 0, 0);// Username
        g.setColor(0, 0, 0);
        n = drawMultilineString(g, g.getFont(), content, 8, 20, 0, 200);
        g.setColor(38, 193, 255);
        g.drawString("Upped: " + ups, 100, n + 5, 0); //Number of thumbs up
        g.drawString("Downed: " + downs, 100, n + 30, 0);  //Number of thumbs down


    }
}



