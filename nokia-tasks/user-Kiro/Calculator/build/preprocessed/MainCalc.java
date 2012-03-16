/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import com.sun.lwuit.*;
import com.sun.lwuit.events.ActionEvent;
import com.sun.lwuit.events.ActionListener;
import com.sun.lwuit.geom.Dimension;
import com.sun.lwuit.layouts.BoxLayout;
import com.sun.lwuit.layouts.FlowLayout;
import com.sun.lwuit.layouts.GridLayout;
import com.sun.lwuit.plaf.Style;
import com.sun.lwuit.plaf.UIManager;
import com.sun.lwuit.util.Resources;
import javax.microedition.midlet.*;


public class MainCalc extends MIDlet {

    Form mainForm;
    GridLayout buttonsLayout;
    BoxLayout mainLayout;
    String num;
    double result;
    int op;
    boolean decFlag;
    boolean equal;
    public void startApp() {
         Display.init(this);
         createMainUI();
         installTheme();
         
    }
    public void createMainUI(){
        equal = false;
        decFlag= false;
        num="0";
        result=0;
        op=0;
        mainForm = new Form("Calculator");
        final Label output = new Label("result");
        // w240 h320
        output.setText("0");
        Style s = new Style();
        s.setAlignment(Label.CENTER,true);
        s.setBgColor(0x990000);
        output.setUnselectedStyle(s);
        
        Button bC = new Button("C");
        bC.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent ae) {
                num="0";
                result=0;
                op=0;
                decFlag=false;
                equal = false;
                output.setText("0");
            }
        });
        Button pm = new Button("+/-");
        pm.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent ae) {
               if(num.charAt(0)=='-')
                   num = num.substring(1);
               else num = "-"+num;
               output.setText(num);
            }
        });
        Button div = new Button("/");
        div.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent ae) {
                
                  
                switch(op){
                    case 0: result = parse(num);break;
                    case 1: result = result+parse(num);break;
                    case 2: result = result*parse(num);break;
                    case 3: result = result-parse(num);break;
                    case 4:  if(parse(num)==0)
                    {
                        output.setText("Error");
                         num="0";
                         result=0;
                         op=0;
                         decFlag=false;
                         break;
                    }else{
                        result = result/parse(num);break;
                        }
                }
                num = "0";
                op=4;
                output.setText(""+result);
                decFlag=false;
                equal = false;
                
            }
        });
        Button mult = new Button("x");
        mult.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent ae) {
               
                   
                  
                switch(op){
                    case 0: result = parse(num);break;
                    case 1: result = result+parse(num);break;
                    case 2: result = result*parse(num);break;
                    case 3: result = result-parse(num);break;
                    case 4:  if(parse(num)==0)
                    {
                        output.setText("Error");
                         num="0";
                         result=0;
                         op=0;
                         decFlag=false;
                         break;
                    }else{
                        result = result/parse(num);break;
                        }
                }
                num = "0";
                op=2;
                output.setText(""+result);
                decFlag=false;
                equal = false;
                
            }
        });
        Button n7 = new Button("7");
        n7.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent ae) {
               if(num=="0" || equal)
               {
                   num="7";
                   equal = false;
               
               }
                else if((num.length()==9 && !decFlag) || (num.length()==10 && decFlag))
                {}
                else num=num+"7";
                
                output.setText(num);
            }
        });
        Button n8 = new Button("8");
         n8.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent ae) {
               if(num=="0" || equal)
               {
                   num="8";
                   equal = false;
               
               }
                else if((num.length()==9 && !decFlag) || (num.length()==10 && decFlag))
                {}
                else num=num+"8";
                
                output.setText(num);
            }
        });
        Button n9 = new Button("9");
         n9.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent ae) {
              if(num=="0" || equal)
               {
                   num="9";
                   equal = false;
               
               }
                else if((num.length()==9 && !decFlag) || (num.length()==10 && decFlag))
                {}
                else num=num+"9";
                
                output.setText(num);
            }
        });
        Button n6 = new Button("6");
         n6.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent ae) {
              if(num=="0" || equal)
               {
                   num="6";
                   equal = false;
               
               }
                else if((num.length()==9 && !decFlag) || (num.length()==10 && decFlag))
                {}
                else num=num+"6";
                
                output.setText(num);
            }
        });
        Button n1 = new Button("1");
        Button n2 = new Button("2");
        n1.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent ae) {
                if(num=="0" || equal)
               {
                   num="1";
                   equal = false;
               
               }
                else if((num.length()==9 && !decFlag) || (num.length()==10 && decFlag))
                {}
                else num=num+"1";
                
                output.setText(num);
            }
        });
        n2.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent ae) {
              if(num=="0" || equal)
               {
                   num="2";
                   equal = false;
               
               }
                else if((num.length()==9 && !decFlag) || (num.length()==10 && decFlag))
                {}
                else num=num+"2";
                
                output.setText(num);
            }
        });
        Button n3 = new Button("3");
         n3.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent ae) {
               if(num=="0" || equal)
               {
                   num="3";
                   equal = false;
               
               }
                else if((num.length()==9 && !decFlag) || (num.length()==10 && decFlag))
                {}
                else num=num+"3";
                
                output.setText(num);
            }
        });
        Button n4 = new Button("4");
         n4.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent ae) {
               if(num=="0" || equal)
               {
                   num="4";
                   equal = false;
               
               }
                else if((num.length()==9 && !decFlag) || (num.length()==10 && decFlag))
                {}
                else num=num+"4";
                
                output.setText(num);
            }
        });
        Button n5 = new Button("5");
         n5.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent ae) {
               if(num=="0" || equal)
               {
                   num="5";
                   equal = false;
               
               }
                else if((num.length()==9 && !decFlag) || (num.length()==10 && decFlag))
                {}
                else num=num+"5";
                
                output.setText(num);
            }
        });
        Button n0 = new Button("0");
         n0.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent ae) {
               if(num=="0" || equal)
               {
                   num="0";
                   equal = false;
               
               }
                else if((num.length()==9 && !decFlag) || (num.length()==10 && decFlag))
                {}
                else num=num+"0";
                
                output.setText(num);
            }
        });
        Button plus = new Button("+");
        plus.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent ae) {
                
                switch(op){
                    case 0: result = parse(num);break;
                    case 1: result = result+parse(num);break;
                    case 2: result = result*parse(num);break;
                    case 3: result = result-parse(num);break;
                    case 4:  if(parse(num)==0)
                    {
                        output.setText("Error");
                         num="0";
                         result=0;
                         op=0;
                         decFlag=false;
                         break;
                    }else{
                        result = result/parse(num);break;
                        }
                }
                num = "0";
                op=1;
                output.setText(""+result);
                decFlag=false;
                equal = false;
            }
        });
        Button eq = new Button("=");
        eq.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent ae) {
                switch(op){
                    case 0: result = parse(num);break;
                    case 1: result = result+parse(num);break;
                    case 2: result = result*parse(num);break;
                    case 3: result = result-parse(num);break;
                    case 4:  if(parse(num)==0)
                    {
                        output.setText("Error");
                         num="0";
                         result=0;
                         op=0;
                         decFlag=false;
                         break;
                    }else{
                        result = result/parse(num);break;
                        }
                }
                output.setText(""+result);
                if(result==0)
                    num="0";
                else
                num=result+"";
                op=0;
                equal = true;
            }
        });
        
        Button dec = new Button(".");
        dec.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent ae) {
              
                if(!decFlag && num.length()<=9){
                    if(equal){
                        num="0.";
                        equal=false;
                    }else {
                    num=num+".";
                    decFlag = true;
                    }
                }
                output.setText(num);
            }
        });
        Label emp1 = new Label();
        Label emp2 = new Label();
        Label emp3 = new Label();
        Button minus = new Button("-");
        minus.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent ae) {
                
                  
                switch(op){
                    case 0: result = parse(num);break;
                    case 1: result = result+parse(num);break;
                    case 2: result = result*parse(num);break;
                    case 3: result = result-parse(num);break;
                    case 4:  if(parse(num)==0)
                    {
                        output.setText("Error");
                         num="0";
                         result=0;
                         op=0;
                         decFlag=false;
                         break;
                    }else{
                        result = result/parse(num);break;
                        }
                }
                
                num = "0";
                op=3;
                output.setText(""+result);
                decFlag=false;
                equal = false;
                
            }
        });

        mainLayout = new BoxLayout(BoxLayout.Y_AXIS);
        mainForm.setLayout(mainLayout);
        Container spacetop = new Container();
        spacetop.setPreferredSize(new Dimension(200,20));
        Container spacemid = new Container();
        spacemid.setPreferredSize(new Dimension(200,20));
       
        buttonsLayout = new GridLayout(5,4);
        Form buttons = new Form();

        buttons.setLayout(buttonsLayout);
        buttons.addComponent(bC);
        buttons.addComponent(pm);
        buttons.addComponent(div);
        buttons.addComponent(mult);
        
        buttons.addComponent(n7);
        buttons.addComponent(n8);
        buttons.addComponent(n9);
        buttons.addComponent(minus);
        
        buttons.addComponent(n4);
        buttons.addComponent(n5);
        buttons.addComponent(n6);
        buttons.addComponent(plus);
        
        buttons.addComponent(n1);
        buttons.addComponent(n2);
        buttons.addComponent(n3);
        buttons.addComponent(eq);
        
        buttons.addComponent(emp2);
        buttons.addComponent(n0);
        buttons.addComponent(dec);
        buttons.addComponent(emp1);


       

        Command exitCommand = new Command("Exit"){

            public void actionPerformed(ActionEvent evt) {
                notifyDestroyed();
            }
            
        };
        mainForm.addCommand(exitCommand);
        mainForm.addComponent(spacetop);
        mainForm.addComponent(output);
        mainForm.addComponent(spacemid);
        mainForm.addComponent(buttons);

        mainForm.show();
        
        
        
    }
    public void pauseApp() {
    }
    
    public void destroyApp(boolean unconditional) {
    }
    public double parse(String s){
        return Double.parseDouble(s);
    }
     private void installTheme() {
        try {
            Resources r;
            r = Resources.open("/xxx.res");
            UIManager.getInstance().setThemeProps(r.getTheme("Theme 1"));
            mainForm.refreshTheme();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
            
}
