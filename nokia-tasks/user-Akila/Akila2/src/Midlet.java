/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import com.sun.lwuit.Button;
import com.sun.lwuit.Command;
import com.sun.lwuit.Container;
import com.sun.lwuit.Display;
import com.sun.lwuit.Form;
import com.sun.lwuit.Label;
import com.sun.lwuit.TextField;
import com.sun.lwuit.events.ActionEvent;
import com.sun.lwuit.events.ActionListener;
import com.sun.lwuit.layouts.BoxLayout;
import javax.microedition.midlet.*;

/**
 * @author MESAI
 */
public class Midlet extends MIDlet {

    Form mainForm; //this is your main Form
    TextField input;
    Label output;
    double op1;
    double op2;
    int op;

    public void startApp() {
        Display.init(this);
        createMainUI();//this method is a way to organize your code ican write whats inside createMainUI()here

    }

    public void createMainUI() {
        mainForm = new Form("My Calculator");
        mainForm.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
        //// A TextField,it is final to be able to use in the action listener part 
        //and in addition i will no change its value later so no problem
        // but if you need to change the value later you can set as a global variable
        input = new TextField();
        input.setConstraint(TextField.NUMERIC);
        input.setEditable(true);
        input.setHint("Enter a number");
        output = new Label("Result");
        output.setPreferredH(50);
        Container c1 = new Container();
        c1.setLayout(new BoxLayout(BoxLayout.X_AXIS));
        Container c2 = new Container();
        c2.setLayout(new BoxLayout(BoxLayout.X_AXIS));
        Button add = new Button("+");
        add.setPreferredW(30);
        Button sub = new Button("-");
        sub.setPreferredW(30);
        Button mul = new Button("*");
        mul.setPreferredW(30);
        Button div = new Button("/");
        div.setPreferredW(30);
        Button neg = new Button("_");
        neg.setPreferredW(30);
        Button clr = new Button("CE");
        clr.setPreferredW(30);
        Button del=new Button("C");
        del.setPreferredW(30);
        Button dot = new Button(".");
        dot.setPreferredW(30);
        Button eq = new Button("=");
        eq.setPreferredW(30);
        add.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent ae) {
                if(input.getText().length()!=0)
                op1 = Double.parseDouble(input.getText());
                //System.out.println(op1);
                else op1=0;
                input.setText("");
                op=0;
                //output.setText("\n" + "_________");
                //output.setText("\n" + (op1 + op2));
            }
        });
        sub.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent ae) {
                if(input.getText().length()!=0)
                op1 = Double.parseDouble(input.getText());
                //System.out.println(op1);
                else op1=0;
                input.setText("");
                op=1;
                //output.setText("\n" + "_________");
                //output.setText("\n" + (op1 + op2));
            }
        });
        mul.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent ae) {
                if(input.getText().length()!=0)
                op1 = Double.parseDouble(input.getText());
                else op1=1;
                //System.out.println(op1);
                input.setText("");
                op=2;
                //output.setText("\n" + "_________");
                //output.setText("\n" + (op1 + op2));
            }
        });
        div.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent ae) {
                if(input.getText().length()!=0)
                op1 = Double.parseDouble(input.getText());
                //System.out.println(op1);
                else op1=0;              
                input.setText("");
                op=3;
                //output.setText("\n" + "_________");
                //output.setText("\n" + (op1 + op2));
            }
        });
        neg.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent ae) {
                if(input.getText().length()!=0){
                op1 = Double.parseDouble(input.getText());
                input.setText((0-op1)+"");
                output.setText("\n" + (-op1));}
                
            }
        });
        dot.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent ae) {
                if (input.getText().length() != 0 && input.getText().replace('.','.')) {
                    input.setText(input.getText() + ".");
                } else {
                    input.setText("0.");
                }
            }
        });
        clr.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent ae) {
                op1 = 0;
                op2 = 0;
                input.setText(" ");
                output.setText("");
            }
        });
        eq.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent ae) {
                op2=Double.parseDouble(input.getText());
                output.setText("\n"+op2);                
                switch(op){
                    case 0: input.setText(""+(op1+op2)); break;
                    case 1: input.setText(""+(op1-op2)); break;
                    case 2: input.setText(""+(op1*op2)); break;
                    case 3: input.setText(""+(op1/op2)); break;
                    default: break;
                }
                output.setText(input.getText());
            }
        });
        del.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent ae) {
                if(input.getText().length()!=0)
                input.setText(input.getText().substring(0,((input.getText()).length()-1)));
                else input.setText("");
            }
        });
        

        Command exitCommand = new Command("Exit") {

            public void actionPerformed(ActionEvent evt) {
                notifyDestroyed();
            }
        };

        mainForm.addCommand(exitCommand);

        mainForm.addComponent(output);

        mainForm.addComponent(input);
        mainForm.addComponent(c1);
        mainForm.addComponent(c2);
        c1.addComponent(add);
        c1.addComponent(sub);
        c1.addComponent(mul);
        c1.addComponent(div);
        c2.addComponent(clr);
        c2.addComponent(dot);
        c2.addComponent(neg);
        c2.addComponent(eq);
        c2.addComponent(del);

        mainForm.addCommand(exitCommand);
        // last thing you need to shoe all this on the screen

        mainForm.show();
    }

    public void pauseApp() {
    }

    public void destroyApp(boolean unconditional) {
    }
}
