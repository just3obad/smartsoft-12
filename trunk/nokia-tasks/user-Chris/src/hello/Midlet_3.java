/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hello;
import com.sun.lwuit.*;
import com.sun.lwuit.events.ActionEvent;
import com.sun.lwuit.events.ActionListener;
import com.sun.lwuit.layouts.BorderLayout;
import com.sun.lwuit.layouts.BoxLayout;
import com.sun.lwuit.layouts.GridLayout;
import com.sun.lwuit.plaf.UIManager;
import com.sun.lwuit.util.Resources;
import java.util.Stack;
import java.util.Vector;
import javax.microedition.amms.control.PanControl;
import javax.microedition.lcdui.Alert;
import javax.microedition.midlet.*;


/**
 * @author MESAI
 */
public class Midlet_3 extends MIDlet {
    int lastop=0;
    
    Form mainForm; //this is your main Form
    Label inp;
    double lastresult;
    //Form subform;
    public void startApp() {
         Display.init(this);
         createMainUI();//this method is a way to organize your code ican write whats inside createMainUI()here
         installTheme();
    }
    public String eval(String x) {
        Stack s=new Stack();
        Vector xxx=new Vector();
        String inppp="";
        for(int i=0;i<x.length();i++) {
            if(x.charAt(i)==' ') {
                if(!inppp.equals(""))
                xxx.addElement(inppp);
                inppp="";
            } else {
                inppp+=x.charAt(i);
            }
        }
        if(!inppp.equals("")) {
            xxx.addElement(inppp);
        }
        for(int i=0;i<xxx.size();i++) {
            System.out.println((String)xxx.elementAt(i));
            
        }
        for(int i=0;i<xxx.size();i++) {
            String k=(String)xxx.elementAt(i); 
            if(k.equals("+")) {
                double yy=0;
                double xx=0;
                if(s.isEmpty()) return null;
                xx=Double.parseDouble((String)s.pop());
                 if(s.isEmpty()) return null;
                 yy=Double.parseDouble((String)s.pop());
                 System.out.println(xx+yy);
                 s.push(xx+yy+"");
                
            }
            else if(k.equals("-")) {
                double yy=0;
                double xx=0;
                if(s.isEmpty()) return null;
                xx=Double.parseDouble((String)s.pop());
                 if(s.isEmpty()) return null;
                 yy=Double.parseDouble((String)s.pop());
                 System.out.println(xx+yy);
                 s.push(yy-xx+"");
                
            } else if(k.equals("*")) {
               double yy=0;
                double xx=0;
                if(s.isEmpty()) return null;
                xx=Double.parseDouble((String)s.pop());
                 if(s.isEmpty()) return null;
                 yy=Double.parseDouble((String)s.pop());
                 System.out.println(xx+yy);
                 s.push(xx*yy+"");
                
            } else if(k.equals("/")) {
               double yy=0;
                double xx=0;
                if(s.isEmpty()) return null;
                xx=Double.parseDouble((String)s.pop());
                 if(s.isEmpty()) return null;
                 yy=Double.parseDouble((String)s.pop());
                 System.out.println(xx+yy);
                 s.push(yy/xx+"");
                
            } else {
                s.push(k);
            }
        }
        
        String myres=(String)s.pop();
        if(!s.isEmpty()) {
            return null;
        } else {
            return myres;
        }
    }
    public void createMyUI() {
        
    }
    public void createMainUI(){
        mainForm = new Form("Calculator");
        //subform=new Form();
        inp=new Label("0");
        mainForm.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
        //// A TextField,it is final to be able to use in the action listener part 
        //and in addition i will no change its value later so no problem
        // but if you need to change the value later you can set as a global variable
        /*final TextField input = new TextField();
        input.setHint("Enter first number");
        
        final TextField input2 = new TextField();
        input2.setHint("Enter second number");*/
        Button add=new Button("+");
        Button subtract=new Button("-");
        Button multiply=new Button("*");
        Button divide=new Button("/");
        final Label Output = new Label("Output");
        final Label result=new Label("0");
        mainForm.addComponent(Output);
        Container ourcontainer=new Container();
        ourcontainer.setLayout(new GridLayout(4,5));
        Button one=new Button("1");
        Button two=new Button("2");
        Button three=new Button("3");
        Button four=new Button("4");
        Button five=new Button("5");
        Button six=new Button("6");
        Button seven=new Button("7");
        Button eight=new Button("8");
        Button nine=new Button("9");
        Button zero=new Button("0");
        Button dot=new Button(".");
        Button ac=new Button("AC");
        Button back=new Button("C");
        Button ans=new Button("ans");
        Button left=new Button("(");
        Button right=new Button(")");
       // left.addActionListener(my)
        lastresult=0;
        Form subform1=new Form();
        Form subform2=new Form();
        Form subform3=new Form();
        Form subform4=new Form();
        ac.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent ae) {
                inp.setText("");
            }
        });
       back.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent ae) {
                if(inp.getText().length()!=0) {
                    inp.setText(inp.getText().substring(0, inp.getText().length()-1));
                }
            }
        });
       ans.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent ae) {
                inp.setText(inp.getText()+lastresult);
                
            }
        });
        ActionListener myAction=new ActionListener() {

            public void actionPerformed(ActionEvent ae) {
                if(inp.getText().equals("0")) {
                   inp.setText("");
                   
                }
                Output.setText("");
                Button x=(Button)(ae).getSource();
               inp.setText(inp.getText()+x.getText());
                
            }
        };
        one.addActionListener(myAction);
        two.addActionListener(myAction);
        three.addActionListener(myAction);
        four.addActionListener(myAction);
        five.addActionListener(myAction);
        six.addActionListener(myAction);
        seven.addActionListener(myAction);
        eight.addActionListener(myAction);
        nine.addActionListener(myAction);
        zero.addActionListener(myAction);
        dot.addActionListener(myAction);
        left.addActionListener(myAction);
        right.addActionListener(myAction);
        Button equal=new Button("=");
        add.addActionListener(myAction);
        subtract.addActionListener(myAction);
        multiply.addActionListener(myAction);
        divide.addActionListener(myAction);
        equal.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent ae) {
                String myfinalres=inp.getText();
                boolean x=checkrightformatof(myfinalres);
                if(!x) {
                    Output.setText("Wrong Format of bracket");
                   mainForm.setEnabled(false);
                   Form kk=new Form("Change Input");
                   Button ret=new Button("Return");
                   ret.addActionListener(new ActionListener() {

                        public void actionPerformed(ActionEvent ae) {
                           mainForm.setEnabled(true);
                           mainForm.showBack();
                        }
                    });
                   
                   kk.addComponent(ret);
                   kk.show();
                } else {
                    
                
                while(!myfinalres.equals(changeInputCompact(myfinalres))) {
                    myfinalres=changeInputCompact(myfinalres);
                }
                System.out.println(
                        "Heeere"+myfinalres);
                InToPost expr=new InToPost(myfinalres);
               String realexpr=expr.doTrans();
                
                /*for(int i=0;i<realexpr.size();i++) {
                    System.out.print(realexpr.elementAt(i)+" ");
                }*/
               String resss=eval(realexpr);
               if(resss==null) {
                   Output.setText("Math Error");
                   mainForm.setEnabled(false);
                   Form kk=new Form("Change Input");
                   Button ret=new Button("Return");
                   ret.addActionListener(new ActionListener() {

                        public void actionPerformed(ActionEvent ae) {
                           mainForm.setEnabled(true);
                           mainForm.showBack();
                        }
                    });
                   
                   kk.addComponent(ret);
                   kk.show();
               } else {
                inp.setText(resss);
                if(resss.equals("Infinity")) {
                    
                } else 
                lastresult=Double.parseDouble(resss);
               }
                }
              
            }

            private boolean checkrightformatof(String myfinalres) {
                int count=0;
                for(int i=0;i<myfinalres.length();i++) {
                    if(myfinalres.charAt(i)=='(') count++;
                    if(myfinalres.charAt(i)==')') count--;
                    if(count<0) return false;
                }
                if(count==0) return true;
                else return false;
            }
        });
        
        /*equal.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent ae) {
                 double y;    
                       y=Double.parseDouble(result.getText());
                    double x;
                       if(inp.getText().equals("")) {
                           x=0.0;
                       }
                   else x=Double.parseDouble(inp.getText());
                    switch(lastop) {
                        case 1: result.setText(x+y+"");break;
                        case 2: result.setText(y-x+"");break;
                        case 3: result.setText(x*y+"");break;
                        case 4: result.setText(y/x +"");break;
                        case 0: result.setText(x+"");break;
                    }
                   
                    
                    inp.setText("");
            }
        });
        //subform1.setLayout(new BoxLayout(BoxLayout.X_AXIS));
        //subform2.setLayout(new BoxLayout((BoxLayout.X_AXIS)));
        //subform3.setLayout(new BoxLayout(BoxLayout.X_AXIS));
        //subform4.setLayout(new BoxLayout((BoxLayout.X_AXIS)));
        /*subform1.addComponent(one);
        subform1.addComponent(two);
         subform1.addComponent(three);
        subform2.addComponent(four);
         subform2.addComponent(five);
        subform2.addComponent(six);
         subform3.addComponent(seven);
        subform3.addComponent(eight);
         subform3.addComponent(nine);
         subform4.addComponent(zero);
         subform4.addComponent(dot);
         subform4.addComponent(power);*/
      //  subform.setLayout(new BoxLayout(BoxLayout.X_AXIS));
        /*add.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent ae) {
                double y;    
                
                       y=Double.parseDouble(result.getText());
                    
                double x;
                   if(inp.getText().equals("")) {
                           x=0.0;
                       }
                   else x=Double.parseDouble(inp.getText());
                    switch(lastop) {
                        case 1: result.setText(x+y+"");break;
                        case 2: result.setText(y-x+"");break;
                        case 3: result.setText(x*y+"");break;
                        case 4: result.setText(y/x +"");break;
                        case 0: result.setText(x+"");break;
                    }
                   
                    
                    inp.setText("");
                    lastop=1;
                    
            }
        });
        subtract.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent ae) {
               
                     double y;    
                
                       y=Double.parseDouble(result.getText());
                    
                  double x;
                   if(inp.getText().equals("")) {
                           x=0.0;
                       }
                   else x=Double.parseDouble(inp.getText());
                    switch(lastop) {
                        case 1: result.setText(x+y+"");break;
                        case 2: result.setText(y-x+"");break;
                        case 3: result.setText(x*y+"");break;
                        case 4: result.setText(y/x +"");break;
                        case 0: result.setText(x+"");break;
                    }
                   
                    
                    inp.setText("");
                    lastop=2;
            }
              
        });
         multiply.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent ae) {
                double y;    
                
                       y=Double.parseDouble(result.getText());
                    
                   double x;
                   if(inp.getText().equals("")) {
                           x=1;
                       }
                   else x=Double.parseDouble(inp.getText());
                    switch(lastop) {
                        case 1: result.setText(x+y+"");break;
                        case 2: result.setText(y-x+"");break;
                        case 3: result.setText(x*y+"");break;
                        case 4: result.setText(y/x +"");break;
                        case 0: result.setText(x+"");break;
                    }
                   
                    
                    inp.setText("");
                    lastop=3;
            }
        });
          divide.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent ae) {
               double y;    
                
                       y=Double.parseDouble(result.getText());
                   
                    double x;
                   if(inp.getText().equals("")) {
                           x=1;
                       }
                   else x=Double.parseDouble(inp.getText());
                    switch(lastop) {
                        case 1: result.setText(x+y+"");break;
                        case 2: result.setText(y-x+"");break;
                        case 3: result.setText(x*y+"");break;
                        case 4: result.setText(y/x +"");break;
                        case 0: result.setText(x+"");break;
                    }
                   
                    
                    inp.setText("");
                    lastop=4;
            }
        });
          power.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent ae) {
                 double y;    
                
                       y=Double.parseDouble(result.getText());
                    if(y>=0) {
                         result.setText(Math.sqrt(y)+"");
                    }
                    else {
                       // Alert.
                    }
                   
                    
                    inp.setText("");
                    lastop=0;
            }
            
        });*/
        
        //// A TextArea, it is final to be able to use in the action listener part 
        //and in addition i will no change its value later so no problem
        // but if you need to change the value later you can set as a global variable
        
        //// Abutton to insert
        /*subform1.addComponent(add);
        subform1.addComponent(equal);
        subform2.addComponent(subtract);
        subform3.addComponent(multiply);
        subform4.addComponent(divide);*/
        mainForm.addComponent(inp);
        ourcontainer.addComponent(one);
        ourcontainer.addComponent(two);
        ourcontainer.addComponent(three);
        ourcontainer.addComponent(add);
        ourcontainer.addComponent(back);
        ourcontainer.addComponent(four);
        ourcontainer.addComponent(five);
        ourcontainer.addComponent(six);
        ourcontainer.addComponent(subtract);
        ourcontainer.addComponent(ac);
        ourcontainer.addComponent(seven);
        ourcontainer.addComponent(eight);
        ourcontainer.addComponent(nine);
        ourcontainer.addComponent(multiply);
        ourcontainer.addComponent(equal);
        ourcontainer.addComponent(zero);
        ourcontainer.addComponent(dot);
        
        ourcontainer.addComponent(divide);
        ourcontainer.addComponent(left);
        ourcontainer.addComponent(right);
        //ourcontainer.addComponent(ans);
       // mainForm.addComponent(ans);
        
       /* mainForm.addComponent(subform1);
        mainForm.addComponent(subform2);
        mainForm.addComponent(subform3);
        mainForm.addComponent(subform4);*/
        
        //Button submit = new Button("Ok !!!");
        /*submit.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent ae) {
                if(input.getText().length()!=0){
               output.setText(input.getText());
                }
                else{
                    output.setText("Write Something Please");
                }
            }
            
        });*/
        // a command to exit
        Command exitCommand = new Command("Exit"){

            public void actionPerformed(ActionEvent evt) {
                notifyDestroyed();
                
            }
            
        };
        mainForm.addCommand(exitCommand);
        //mainForm.addComponent(output);
        //mainForm.addComponent( result);
        mainForm.addComponent( ourcontainer);
        mainForm.addComponent(ans);
        //mainForm.addComponent(submit);
        mainForm.addCommand(exitCommand);
        // last thing you need to shoe all this on the screen
        mainForm.show();
        
        
        
    }
    String changeInputCompact(String x) {
        String result="";
        int i=0;
        while(i+1<x.length()) {
            if(x.charAt(i)=='+' && x.charAt(i+1)=='-') {
                i=i+2;
               result+='-';
                
            } else if(x.charAt(i)=='+' && x.charAt(i+1)=='-') {
                i=i+2;
               result+='-';
                
            } else if(x.charAt(i)=='-' && x.charAt(i+1)=='-') {
                i=i+2;
                result+='+';
               
                
            } else if(x.charAt(i)=='*' && x.charAt(i+1)=='+') {
                i=i+2;
               result+='*';
                
            } else if(x.charAt(i)=='/' && x.charAt(i+1)=='+') {
                i=i+2;
                result+='/';
            } else if(x.charAt(i)=='+' && x.charAt(i+1)=='+') {
                i=i+2;
               result+='+';
                
            } else {
                result+=x.charAt(i);
                i++;
            }
        }
        if(x.charAt(x.length()-1)>='0' && x.charAt(x.length()-1)<='9' || x.charAt(x.length()-1)==')') {
            result+=x.charAt(x.length()-1);
        }
        return result;
    }
    private void installTheme() {
        // This is not the normal way to do this.
        // Usually you load a theme from a file.
//        UIManager uim = UIManager.getInstance();
//        Hashtable ht = new Hashtable();
//        ht.put("sel#" + Style.BG_COLOR, "ffffff");
//        ht.put(Style.BG_COLOR, "ffffff");
//        ht.put(Style.FG_COLOR, "000000");
//        uim.setThemeProps(ht);
        try {
            Resources r;

            r = Resources.open("/christine_1.res");

            //UIManager.getInstance().
            UIManager.getInstance().setThemeProps(r.getTheme("Theme 3"));
            // UIManager.getInstance().setResourceBundle(r.getL10N("localize", "en"));
            //  UIManager.getInstance().setResourceBundle(r.getL10N("localize", "en"));

           // mHomeForm.refreshTheme();
           mainForm.refreshTheme();
           //mainForm .show();
        } catch (Exception e) {
           System.out.println("Christine");
        }
    }
  
    public void pauseApp() {
    }
    
    public void destroyApp(boolean unconditional) {
    }
    
}
