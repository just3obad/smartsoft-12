package hello;

import com.sun.lwuit.*;
import com.sun.lwuit.events.ActionEvent;
import com.sun.lwuit.events.ActionListener;
import com.sun.lwuit.layouts.BoxLayout;
import com.sun.lwuit.layouts.GridLayout;
import com.sun.lwuit.plaf.UIManager;
import com.sun.lwuit.util.Resources;
import javax.microedition.midlet.*;

public class Midlet extends MIDlet {

    Form mainForm;
    double result=0;
    String old="";
    int operator ;
    int counter=0;
    boolean flag = true;
    boolean flaga = true;
    boolean flagb = true;
    boolean flagc = true;
    boolean flagd = true;
    
    
    
    public void startApp() {
         Display.init(this);
         createMainUI();
         createTheme();
         
    }
    public void createMainUI(){
        mainForm = new Form("Simple Calculator");
        mainForm.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
        mainForm.setDraggable(false);
        Container cont = new Container();
        cont.setLayout(new GridLayout(3, 2));
       
        
        final TextField input1 = new TextField();
        input1.setHint("First number here");
        input1.setConstraint(TextField.NUMERIC);
        
        final TextArea op = new TextArea();
        op.setHint("Equation");
        op.setEditable(false);
        //op.setHeight(100);
        op.setPreferredH(100);
        
        final Label out = new Label("Result here");
       
        
        
        
        Button add = new Button("+");
        Button sub = new Button("-");
        Button mul = new Button("X");
        Button div = new Button("÷");
        Button eq = new Button("=");
        Button cl = new Button("C");
        
        cont.addComponent(add);
        cont.addComponent(sub);
        cont.addComponent(mul);
        cont.addComponent(div);
        cont.addComponent(eq);
        cont.addComponent(cl);
        
        cl.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent ae) {
                input1.clear();
                result = 0;
                old="";
                out.setText("0");
                op.setText("");
                flag = true;
                flaga=true;
            }
        });
        
        add.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent ae)  {

                   try{
                       if(flaga){
                        operator = 0;
                        result += Double.parseDouble(input1.getText());
                        flag=false;
                        flaga=false;
                        flagb=true;
                        flagc=true;
                        flagd=true;
                       }

                   }
                   catch(Exception e){
                       out.setText("Input Error");
                   }
    
            }
            
        });
        
         div.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent ae)  {

                    try{
                        if(flagb){
                        operator = 1;
                        if(Double.parseDouble(input1.getText())!=0)
                        result /= Double.parseDouble(input1.getText());
                        else
                        out.setText("Arithmatic Error");    
                        flag=false;
                        flaga=true;
                        flagb=false;
                        flagc=true;
                        flagd=true;
                        }
                    }
                       
                    catch(NumberFormatException e){
                        out.setText("input error");
                    }
            }
            
        });
         
          sub.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent ae)  {

                    try{
                        if(flagc){
                        operator = 2;
                        if(flag)
                           result += Double.parseDouble(input1.getText()); 
                        else
                            result -= Double.parseDouble(input1.getText());
                        flag=false;
                        flaga=true;
                        flagb=true;
                        flagc=false;
                        flagd=true;
                        }
                    }
                    catch(NumberFormatException e){
                        out.setText("input error");
                    }
            }
            
        });
          
           mul.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent ae)  {

                    try{
                        if(flagd){
                         operator = 3;
                         result *= Double.parseDouble(input1.getText());
                        flag=false;
                        flaga=true;
                        flagb=true;
                        flagc=true;
                        flagd=false;
                        }
                    }
                    catch(NumberFormatException e){
                        out.setText("input error");
                    }
            }
            
        });
        
      
           eq.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent ae) {
        
               if(!flaga){
                   if(flag)
                   {
                     op.setText(input1.getText());  
                   }
               flaga=true;
               op.setText(old+"\n"+"+"+"\n"+input1.getText());
               old=result+"";
               out.setText(result+"");
               }
               else
               {
                   if(!flagb)
                   {
                       if(flag)
                   {
                     op.setText(input1.getText());  
                   }
                       flagb=true;
                       op.setText(old+"\n"+"÷"+"\n"+input1.getText());
                       old=result+"";
                       out.setText(result+"");
                   }
                   else{
                       if(!flagc)
                       {
                           if(flag)
                   {
                     op.setText(input1.getText());  
                   }
                           flagc=true;
                           op.setText(old+"\n"+"-"+"\n"+input1.getText());
                           old=result+""; 
                           out.setText(result+"");
                       }
                       else
                       {
                           if(!flagd)
                           {
                               if(flag)
                   {
                     op.setText(input1.getText());  
                   }
                            flagd=true;
                            op.setText(old+"\n"+"x"+"\n"+input1.getText());
                            old=result+"";
                            out.setText(result+"");
                           }
                       }
                   }
               }
            }
        });
           
        Command exitCommand = new Command("Exit"){

            public void actionPerformed(ActionEvent evt) {
                notifyDestroyed();
            }
            
        };
        mainForm.addCommand(exitCommand);
        mainForm.addComponent(out);
        mainForm.addComponent(op);
        mainForm.addComponent(input1);
        mainForm.addComponent(cont);
        mainForm.addCommand(exitCommand);
        mainForm.show();
        
        
        
    }
    
   private void createTheme() { 
        try { 
            Resources r; 
            r = Resources.open("/3OBAD.res");
            UIManager.getInstance().setThemeProps(r.getTheme("3OBAD")); 
           mainForm.refreshTheme(); 
           mainForm.show();
        } catch (Exception e) { 
            e.printStackTrace(); 
        }
    }
    public void pauseApp() {
    }
    
    public void destroyApp(boolean unconditional) {
    }
}
