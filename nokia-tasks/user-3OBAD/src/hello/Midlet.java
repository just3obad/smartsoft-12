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
    boolean flag = true;
    int operator ;
    int counter=0;
    
    
    
    public void startApp() {
         Display.init(this);
         createMainUI();
        // createTheme();
         
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
                out.setText("0");
                op.setText("");
            }
        });
        
        add.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent ae)  {

                   try{
                        operator = 0;
                        result += Double.parseDouble(input1.getText());
                        flag=false;
  
                   }
                   catch(Exception e){
                       out.setText("Input Error");
                   }
    
            }
            
        });
        
         div.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent ae)  {

                    try{
                        operator = 1;
                        result /= Double.parseDouble(input1.getText());
                        flag=false;
                       
                    }
                    catch(NumberFormatException e){
                        out.setText("input error");
                    }
            }
            
        });
         
          sub.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent ae)  {

                    try{
                        operator = 2;
                        result -= Double.parseDouble(input1.getText());
                        flag=false;
                      
                    }
                    catch(NumberFormatException e){
                        out.setText("input error");
                    }
            }
            
        });
          
           mul.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent ae)  {

                    try{
                         operator = 3;
                         result *= Double.parseDouble(input1.getText());
                         flag=false;
                         
                    }
                    catch(NumberFormatException e){
                        out.setText("input error");
                    }
            }
            
        });
        
      
           eq.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent ae) {

                switch(operator)
                {
                    case 0:
                        if(flag == true)
                        {
                          op.setText("");  
                          
                          
                        }
                        else{
                        op.setText(old+"\n"+"+"+"\n"+input1.getText());
                        old=result+"";
                        
                        }
                        break;
                    case 1:
                        if(flag == true)
                        {
                          op.setText("");  
                         
                        }
                        else{
                            
                        op.setText(old+"\n"+"÷"+"\n"+input1.getText());
                        old=result+"";
                        
                        }
                        break;
                    case 2:
                        if(flag == true)
                        {
                          op.setText("");  
                          
                        }
                        else{
                          
                        op.setText(old+"\n"+"-"+"\n"+input1.getText());
                        old=result+""; 
                        
                        }
                        break;
                    case 3:
                        if(flag == true)
                        {
                          op.setText("");  
                          
                        }
                        else{
                        
                        op.setText(old+"\n"+"x"+"\n"+input1.getText());
                        old=result+"";
                        
                        }  
                        break;
                     default: op.setText(input1.getText()); 
                }
                 
               out.setText(result+"");
               
               
 
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
    
   /* private void createTheme() { 
        try { 
            Resources r; 
            r = Resources.open("/Theme1.res");
            UIManager.getInstance().setThemeProps(r.getTheme("Theme1")); 
           mainForm.refreshTheme(); 
           mainForm.show();
        } catch (Exception e) { 
            e.printStackTrace(); 
        }
    }*/
    public void pauseApp() {
    }
    
    public void destroyApp(boolean unconditional) {
    }
}
