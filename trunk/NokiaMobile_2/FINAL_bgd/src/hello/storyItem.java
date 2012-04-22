/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hello;

import java.io.DataInputStream;
import java.io.IOException;
import javax.microedition.io.Connector;
import javax.microedition.io.HttpConnection;
import javax.microedition.lcdui.CustomItem;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Font;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.ImageItem;
import javax.microedition.lcdui.StringItem;

/**
 *
 * @author Essam Hafez
 */
class storyItem extends CustomItem {
    private ImageItem image;
    private StringItem storyTitle;
    private StringItem storyBody;
    private StringItem storyRank;
    private StringItem storyCategory;
    private Image addedImage;
    private int id;
    Displayable display;
    HelloMIDlet Hello;
    
    
    // method that takes a url and returns an image after fetching from internet through http connection
    public Image loadImage(String url) throws IOException {
    HttpConnection hpc = null;
    DataInputStream dis = null;
    try {
      hpc = (HttpConnection) Connector.open(url);
      int length = (int) hpc.getLength();
      byte[] data = new byte[length];
      dis = new DataInputStream(hpc.openInputStream());
      dis.readFully(data);
      return Image.createImage(data, 0, data.length);
    } finally {
      if (hpc != null)
        hpc.close();
      if (dis != null)
        dis.close();
    }
  }

  public storyItem(int id,String imageName,String title,String body,int rank,String category,Displayable display,HelloMIDlet Hello){
  super(null);
  this.id=id;
  this.display = display;
  this.Hello = Hello;
  System.out.println(imageName);
       try{
        addedImage = loadImage(imageName); // adds image from internet
       }catch(Exception e){
            System.out.println("Cannot find image");
            try {
                addedImage = Image.createImage("/x.png"); // if error happened, add a local presented image
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
       int width = addedImage.getWidth()>80?80:addedImage.getWidth(); /// customize size of the width  not to exceed 80
       int height = addedImage.getHeight()>60?60:addedImage.getHeight();  // customize size of height not to exceed 60
       body = body.length()>30?body.substring(0, 30)+"...":body; // customize body not to exceed 30 characters
       title = title.length()>30?title.substring(0,30)+"...":title; // customize title not to exceed 30 characters also
                       
       addedImage = Image.createImage(addedImage, 0,0,width,height,0);
        image = new ImageItem("",  addedImage, ImageItem.LAYOUT_DEFAULT, "Nokia");
        
        storyTitle = new StringItem(null,title); 
        storyBody = new StringItem(null, body);
        storyRank = new StringItem(null, rank+"");
        storyCategory = new StringItem(null, category);
  }
  
  
  public int getMinContentWidth(){
  return 240;
  }

  public int getMinContentHeight(){
  return 102;
  }

  public int getPrefContentWidth(int width){
  return getMinContentWidth();
  }

  public int getPrefContentHeight(int height){
  return getMinContentHeight();
  }

  public void paint(Graphics g, int w, int h){
  g.drawRect(0, 0, w-1, h-1);
  g.drawImage(addedImage, 160,40, 0);// draw image right down
  g.setColor(193,0,0);
  g.drawString(storyCategory.getText(), 0, 0, 0);// draw category up left
  g.drawString(storyTitle.getText(), 0, 20, 0); // draw title down category
   g.setColor(31,36,107);
  g.drawString(storyBody.getText().substring(0,storyBody.getText().length()/2), 0, 50, 0); // draw body down title on 2 lines
  g.drawString(storyBody.getText().substring(storyBody.getText().length()/2), 0, 75, 0);
  //g.drawString(storyRank.getText(),160,20, 0);
  drawRank(g);
  }
  
  public void drawRank(Graphics g){
      g.setColor(192, 197, 24);
      g.drawRoundRect(160, 23, 65, 10, 15, 10);// draw a rectangle
      switch(Integer.parseInt(storyRank.getText())){ // fill a certain part of it demeding on the Rank
          case(1):g.fillRoundRect(160, 23, 13, 10, 15, 10);break;         
          case(2):g.fillRoundRect(160, 23, 13*2, 10, 15, 10);break;         
          case(3):g.fillRoundRect(160, 23, 13*3, 10, 15, 10);break;         
          case(4):g.fillRoundRect(160, 23, 13*4, 10, 15, 10);break;         
          case(5):g.fillRoundRect(160, 23, 13*5, 10, 15, 10);break;         
          default:;break;      
      }
  }
        protected void pointerPressed(int x , int y){// if pressed switch display, getStroy should be edited by Omar to take an ID
           Hello.switchDisplayable(null, Hello.getStory());
       }
  
}

