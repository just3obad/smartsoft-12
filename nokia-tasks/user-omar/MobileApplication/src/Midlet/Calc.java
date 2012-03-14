
package Midlet;

import com.sun.lwuit.Button;
import com.sun.lwuit.Command;
import com.sun.lwuit.Display;
import com.sun.lwuit.Form;
import com.sun.lwuit.Label;
import com.sun.lwuit.TextField;
import com.sun.lwuit.events.ActionEvent;
import com.sun.lwuit.events.ActionListener;
import com.sun.lwuit.layouts.FlowLayout;
//import java.io.IOException;
import javax.microedition.midlet.*;


public class Calc extends MIDlet {

    Form mainForm;
    double x = 0;
    double y = 0;
    double z = 0;
    public void startApp() {
         Display.init(this);
         createMainUI();
    }
         
    
     public void setz(double z){
            this.z = z; 
         }
    public double getz() {
        return z;
    }
    public void setx(double x){
            this.x = x; 
         }
    public double getx() {
        return x;
    }
    public void sety(double y){
            this.y = y; 
         }
    public double gety() {
        return y;
    }
    public void createMainUI() {
        mainForm = new Form();
        mainForm.setLayout(new FlowLayout());
        final TextField tf = new TextField("");
        final TextField tf1 = new TextField("");
        final Label n1 = new Label("first number / result :");
        final Label n2 = new Label("second number :");   
        tf.getStyle().setBgColor(0xCC0033);
        tf1.getStyle().setBgColor(0xCC0033);
        
        final Button eq = new Button("                  =                   ");        
            
        Button add = new Button("   +   ");
        add.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent add)   {
                       try{
                       setx(Double.parseDouble(tf.getText())); 
                       sety(Double.parseDouble(tf1.getText()));
                       }
                       catch(NumberFormatException e) {
                           tf.setText("clear & enter valid number");
                           tf1.setText("clear & enter valid number");
                       }
                       eq.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent ae) {
                       setz((getx() + gety()));
                       tf.setText("" + getz());
                       tf1.setText("");
                     }
        });
                        
            }            
        });
        
        Button minus = new Button("   -   ");
        minus.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent min) {
                try {
                setx(Double.parseDouble(tf.getText())); 
                sety(Double.parseDouble(tf1.getText()));
                }
                
                       catch(NumberFormatException e) {
                           tf.setText("clear & enter valid number");
                           tf1.setText("clear & enter valid number");
                       }
                       eq.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent ae2) {
                       setz((getx() - gety()));
                       tf.setText("" + getz());
                       tf1.setText("");
                            
                 
            }
                       });
            }
            });
        
        Button mult = new Button("   *  ");
        mult.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent mult) {
                try {
                setx(Double.parseDouble(tf.getText())); 
                sety(Double.parseDouble(tf1.getText()));
                }
                
                       catch(NumberFormatException e) {
                           tf.setText("clear & enter valid number");
                           tf1.setText("clear & enter valid number");
                       }
                       eq.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent ae3) {
                       setz((getx() * gety()));
                       tf.setText("" + getz());
                       tf1.setText("");
            }
                       });
            }
            });
        
          Button divide = new Button("   /   ");
          divide.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent div) {
                try {       
                       setx(Double.parseDouble(tf.getText())); 
                       sety(Double.parseDouble(tf1.getText()));
                }
                
                       catch(NumberFormatException e) {
                           tf.setText("clear & enter valid number");
                           tf1.setText("clear & enter valid number");
                       }
                       eq.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent ae4) {
                       setz((getx() / gety()));
                       tf.setText("" + getz());
                       tf1.setText((""));
         }
                       });
            }
            });
          
          
          
            
            Button b0 = new Button("   0   ");
            b0.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent ae) {
                tf.setText(tf.getText() + "0");
                 }
        });  
          
            Button b1 = new Button("   1   ");
            b1.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent ae) {
                tf.setText(tf.getText() + "1");
                 }
        });
            
            Button b2 = new Button("   2   ");
            b2.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent ae) {
                tf.setText(tf.getText() + "2");
                 }
        });
            
            Button b3 = new Button("   3   ");
            b3.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent ae) {
                tf.setText(tf.getText() + "3");
                 }
        });
            
            Button b4 = new Button("   4   ");
            b4.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent ae) {
                tf.setText(tf.getText() + "4");
                 }
        });
           
            Button b5 = new Button("   5   ");
            b5.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent ae) {
                tf.setText(tf.getText() + "5");
                 }
        });
           
            Button b6 = new Button("   6   ");
            b6.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent ae) {
                tf.setText(tf.getText() + "6");
                 }
        });
           
            Button b7 = new Button("   7   ");
            b7.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent ae) {
                tf.setText(tf.getText() + "7");
                 }
        });
           
            Button b8 = new Button("   8   ");
            b8.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent ae) {
                tf.setText(tf.getText() + "8");
                 }
        });
                        
            Button b9 = new Button("   9   ");
            b9.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent ae) {
                tf.setText(tf.getText() + "9");
                 }
        });
           
            Button pnt = new Button("    .   ");
            pnt.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent ae) {
                tf.setText(tf.getText() + ".");
                 }
        });
            
            Button clr = new Button("   c   ");
            clr.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent ae) {
                setx(0);
                sety(0);
                setz(0);
                tf.setText("");
                tf1.setText("");
                 }
        });
      

            Command exitCommand = new Command("Exit"){

            public void actionPerformed(ActionEvent evt) {
                notifyDestroyed();
            }
            
        };
        
        
        b1.getStyle().setBgColor(0xFF0000);
        b2.getStyle().setBgColor(0xFF0000);
        b3.getStyle().setBgColor(0xFF0000);
        b4.getStyle().setBgColor(0xFF0000);
        b5.getStyle().setBgColor(0xFF0000);
        b6.getStyle().setBgColor(0xFF0000);
        b7.getStyle().setBgColor(0xFF0000);
        b8.getStyle().setBgColor(0xFF0000);
        b9.getStyle().setBgColor(0xFF0000);
        b0.getStyle().setBgColor(0xFF0000);
        add.getStyle().setBgColor(0xFF0000);
        minus.getStyle().setBgColor(0xFF0000);
        mult.getStyle().setBgColor(0xFF0000);
        divide.getStyle().setBgColor(0xFF0000);
        clr.getStyle().setBgColor(0xFF0000);
        eq.getStyle().setBgColor(0xFF0000);
        pnt.getStyle().setBgColor(0xFF0000);
        
            
        mainForm.addCommand(exitCommand);
        mainForm.addComponent(n1);
        mainForm.addComponent(tf);
        mainForm.addComponent(n2);
        mainForm.addComponent(tf1);
        mainForm.addComponent(b7);
        mainForm.addComponent(b8);
        mainForm.addComponent(b9);
        mainForm.addComponent(divide);
        mainForm.addComponent(b4);
        mainForm.addComponent(b5);
        mainForm.addComponent(b6);
        mainForm.addComponent(mult);
        mainForm.addComponent(b1);
        mainForm.addComponent(b2);
        mainForm.addComponent(b3);
        mainForm.addComponent(minus);
        mainForm.addComponent(b0);
        mainForm.addComponent(pnt);
        mainForm.addComponent(add);
        mainForm.addComponent(clr);
        mainForm.addComponent(eq);
        mainForm.addCommand(exitCommand);
        mainForm.show();
        
        
        
    }
    public void pauseApp() {
    }
    
    public void destroyApp(boolean unconditional) {
    }
}
                  
      
                
