/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hello;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.Vector;
import javax.microedition.lcdui.CustomItem;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.ImageItem;
import javax.microedition.lcdui.*;
import javax.microedition.midlet.MIDlet;
import org.json.me.JSONException;
import org.json.me.JSONObject;

//import json.me.*;
/**
 *
 * @author Menisy
 */
public class commentItem extends CustomItem {

    int n, height = 50; // variables to be used to painting the component
    String comment_id; // comment id
    boolean lonely; // to check for if the comment is in a list or a form of its own "which in this case lonely == true"
    String user; //username of the comment
    String content; //content of the comment
    String ups, downs; //number of thumbs up and down
    String date; //date and time in "dd-MM-yy hh:mm" format which the comment was created
    HelloMIDlet MIDlet; //MIDlet to which the custom item belongs to
    int likesX, likesY, dislikesX, dislikesY; //positions of the thumbs up and down images "to be used for listeners"
    Image likes = null, dislikes = null; //thumbs up and down images

    
    public commentItem(String jsonString, HelloMIDlet mid) {
        super(null);
        fromJson(jsonString);
        MIDlet = mid;
        fancyDateTime(date);
    }
    
    public commentItem(String id, String us, String cont, String up, String down, String dateCreated, boolean lon) {
        super(null);
        comment_id = id;
        user = us;
        content = cont;
        ups = up;
        downs = down;
        lonely = lon;
        // date = dateCreated;
        fancyDateTime(dateCreated);
    }

    public void fancyDateTime(String s) {
        //2012-04-20 13:11:49  ==> this is the Rails format of Date we want to change it to 20-4-2012 1:11 pm
        String day = s.substring(8, 10);
        String month = (Integer.parseInt(s.substring(5, 7)) - 1) + "";
        String year = s.substring(2, 4);
        String hour24 = s.substring(11, 13);
        String mins = s.substring(14, 16);
        String prettyDate = prettifyDate(day, month, year); // make the date part pretty
        String prettyTime = prettifyTime(hour24, mins);
        date = prettyDate + "  " + prettyTime;

    }

    // make time pretty

    public String prettifyTime(String h, String mins) {
        String hh = Integer.parseInt(h) + ""; // remove trailing left zero from hours
        return hh + ":" + mins;                 // return time in pretty format
    }
    //make date pretty

    public String prettifyDate(String d, String m, String y) {
        String dd = Integer.parseInt(d) + ""; // remove trailing left zero from day
        String mm = Integer.parseInt(m) + ""; // remove trailing left zero from months
        return dd + "-" + mm + "-" + y;             // return date in pretty format
    }
    /* Sets the variables of the comment item using the parameters and values
    supplied by the constructor */

    public void fromJson(String js) {
        try {
            JSONObject json = new JSONObject(js);
            comment_id = json.getInt("id") + "";
            user = json.getString("user_name");
            content = (json.getString("content"));
            ups = json.getString("ups");
            downs = json.getString("downs");
            date = json.getString("created_at");
            System.out.println("printttt " + comment_id + " " + user + " " + ups + " " + downs + " " + content);

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
    boolean dragged = false;
    //override the listener in order to detect touch events

    protected void pointerPressed(int x, int y) {
        dragged = false;
        //System.out.println(x+"  "+y);

    }
    int lastX = 0, lastY = 0;

    protected void pointerDragged(int x, int y) {
        dragged = true;
        if (lastX != 0 && lastY != 0) {
            if (lastX > x) {
                System.out.println("dragged left!");
            } else {
                System.out.println("dragged right!");
            }
        }
        System.out.println(x + "  " + y);
        lastX = x;
        lastY = y;

    }
    //override the listener in order to detect touch events

    protected void pointerReleased(int x, int y) {
        //  System.out.println("released");
        //     if(!lonely)
        //   MIDlet.viewCommentOne(comment_id,user,content,ups,downs,date);
        System.out.println("pressksdjs");
        repaint();
        if (x > likesX && x < likesX + likes.getHeight() && y > likesY && y < likesY + likes.getHeight() && !dragged) {
            System.out.println("likingngng");
            MIDlet.up(comment_id);
        } else if (x > dislikesX && x < dislikesX + dislikes.getHeight() && y > dislikesY && y < dislikesY + dislikes.getHeight() && !dragged) {
            System.out.println("disliking");
            MIDlet.down(comment_id);
        }
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
        try {
            likes = Image.createImage("/ups.png");
            dislikes = Image.createImage("/downs.png");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        int width = likes.getWidth() > 80 ? 80 : likes.getWidth(); /// customize size of the width  not to exceed 80
        int height = dislikes.getHeight() > 60 ? 60 : dislikes.getHeight();  // customize size of height not to exceed 60                       
        likes = Image.createImage(likes, 0, 0, width, height, 0);
        g.setColor(0xffffff);
        g.fillRect(0, 0, getMinContentWidth(), getMinContentHeight());
        g.setColor(0x000000);
        g.drawRoundRect(0, 0, w - 1, h - 1, 8, 8);
        g.setColor(53, 177, 255);
        Font f2 = Font.getFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_MEDIUM);
        g.setFont(f2);
        g.drawString((user.length() < 10) ? user : user.substring(0, 10) + "...", 5, 0, 0);// Username shortened if more than 10 characters
        g.setColor(0, 0, 0);
        Font f4 = Font.getFont(Font.FACE_SYSTEM, Font.STYLE_PLAIN, Font.SIZE_MEDIUM);
        g.setFont(f4);
        n = drawMultilineString(g, g.getFont(), content, 8, 25, 0, 200);
        g.setColor(38, 193, 255);
        g.drawImage(likes, 135, n + 8, 0); // dislikes button
        g.drawImage(dislikes, 180, n + 8, 0);  // likes button
        likesX = 135;
        likesY = n + 8;
        dislikesX = 180;
        dislikesY = n + 8;
        int up = Integer.parseInt(ups);
        int down = Integer.parseInt(downs);
        int tot = up + down;
        if(tot==0){ // avoid dividing by zero and paint a grey bar
            g.setColor(150, 150, 150);
            g.fillRoundRect(14, n + 40, 200, 8, 7, 7);
            Font f3 = Font.getFont(Font.FACE_SYSTEM, Font.STYLE_PLAIN, Font.SIZE_SMALL);
            g.setColor(12, 16, 255);
            g.setFont(f3);
            g.drawString(date, 17, n + 13, 0);
            return;
        }
        int upR = ((up * 200) / tot);
        int downR = ((down * 200) / tot);
        g.setColor(67, 186, 70);
        g.fillRoundRect(14, n + 40, upR + 2, 8, 7, 7);
        g.setColor(238, 44, 44);
        g.fillRoundRect(14 + upR - 2, n + 40, downR, 8, 7, 7);
        g.setColor(12, 16, 255);
        Font f3 = Font.getFont(Font.FACE_SYSTEM, Font.STYLE_PLAIN, Font.SIZE_SMALL);
        g.setFont(f3);
        g.drawString(date, 17, n + 13, 0);

    }
}
