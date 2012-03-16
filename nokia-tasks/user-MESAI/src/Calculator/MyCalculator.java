package Calculator;



/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import com.sun.lwuit.*;
import com.sun.lwuit.events.ActionEvent;
import com.sun.lwuit.events.ActionListener;
import com.sun.lwuit.layouts.BoxLayout;
import com.sun.lwuit.layouts.FlowLayout;
import com.sun.lwuit.layouts.GridLayout;
import com.sun.lwuit.layouts.Layout;
import com.sun.lwuit.plaf.UIManager;
import com.sun.lwuit.table.TableLayout;
import com.sun.lwuit.util.Resources;
import java.io.IOException;
import javax.microedition.midlet.*;



/**
 * @author MESAI
 */
public class MyCalculator extends MIDlet {

    private Form mainForm;
    double result = 0;
    public void startApp() {
        Display.init(this);
        CreateMainUI();
        installTheme();
       
    }

    public void CreateMainUI() {
        Image img =null;
        try {
            img = Image.createImage("/title.png");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        Label l = new Label(img);
        l.setAlignment(Label.CENTER);
        mainForm = new Form();
        mainForm.setTitleComponent(l);
        mainForm.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
        Container cOutput = new Container();
        TableLayout cOutputLayout;
        cOutput.setLayout(cOutputLayout = new TableLayout(1, 3));
        final TextArea output = new TextArea();
        output.setEditable(false);
        output.setPreferredH(40);
        final Container controls = new Container();
 

        
        final Container main = new Container();
        TableLayout layout;
        main.setLayout(layout = new TableLayout(1, 2));
        Container numbers = new Container();
        numbers.setLayout(new GridLayout(4, 3));
        for (int i = 0; i < 12; i++) {

            Button number = new Button();
            numbers.addComponent(number);

        }
        for (int i = 0; i < 12; i++) {
            if (i == 10) {
                ((Button) numbers.getComponentAt(10)).setText(".");
                ((Button) numbers.getComponentAt(10)).addActionListener(new ActionListener(){

                    public void actionPerformed(ActionEvent ae) {
                        output.setText(output.getText()+((Button)ae.getComponent()).getText());
                    }
                    
                });
                continue;
            }
            if (i == 11) {
                ((Button) numbers.getComponentAt(11)).setText("ANS");
                ((Button) numbers.getComponentAt(11)).addActionListener(new ActionListener(){

                    public void actionPerformed(ActionEvent ae) {
                        if(output.getText().length()!=0){
                        try{
                       Evaluator eval = new Evaluator();
                   
                       output.setText(eval.Evaluation(eval.postfix(output.getText())));
                        }
                        catch(Exception e){
                            output.setText("Error !! Press Clear");
                            main.setEnabled(false);
                            controls.setEnabled(false);
                        }
                        } 
                    }
                    
                });
                continue;
            }
            if (i == 9) {
                ((Button) numbers.getComponentAt(9)).setText("0");
                ((Button) numbers.getComponentAt(9)).addActionListener(new ActionListener(){

                    public void actionPerformed(ActionEvent ae) {
                        output.setText(output.getText()+"0");
                    }
                    
                });
                continue;
            }
            if (i < 3) {
                ((Button) numbers.getComponentAt(i)).setText(9 - (2 - (i % 3)) + "");
                 
            }
            if (i < 6 && i >= 3) {
                ((Button) numbers.getComponentAt(i)).setText(6 - (2 - (i % 3)) + "");
            }
            if (i < 9 && i >= 6) {
                ((Button) numbers.getComponentAt(i)).setText(3 - (2 - (i % 3)) + "");
            }
            ((Button) numbers.getComponentAt(i)).addActionListener(new ActionListener(){

                    public void actionPerformed(ActionEvent ae) {
                        output.setText(output.getText()+((Button)ae.getComponent()).getText());
                    }
                    
                });
        }
        Container operators = new Container();
        operators.setLayout(new GridLayout(4, 1));
        
        Button operatorAdd = new Button(" + ");
        operatorAdd.addActionListener(new ActionListener(){

                    public void actionPerformed(ActionEvent ae) {
                         if(output.getText().length()==0||output.getText().endsWith("( ")){
                             output.setText(output.getText()+"+");
                        }else{
                        output.setText(output.getText()+((Button)ae.getComponent()).getText());
                        }
                    }
                    
                });
        operators.addComponent(operatorAdd);

        Button operatorSub = new Button(" - ");
        operatorSub.addActionListener(new ActionListener(){

                    public void actionPerformed(ActionEvent ae) {
                        if(output.getText().length()==0||output.getText().endsWith("( ")){
                             output.setText(output.getText()+"-");
                        }else{
                        output.setText(output.getText()+((Button)ae.getComponent()).getText());
                        }
                    }
                    
                });
        operators.addComponent(operatorSub);

        Button operatorMult = new Button(" * ");
        operatorMult.addActionListener(new ActionListener(){

                    public void actionPerformed(ActionEvent ae) {
                        output.setText(output.getText()+((Button)ae.getComponent()).getText());
                    }
                    
                });
        operators.addComponent(operatorMult);

        Button operatorDiv = new Button(" / ");
        operatorDiv.addActionListener(new ActionListener(){

                    public void actionPerformed(ActionEvent ae) {
                        output.setText(output.getText()+((Button)ae.getComponent()).getText());
                    }
                    
                });
        operators.addComponent(operatorDiv);

        TableLayout.Constraint constraint1 = layout.createConstraint();
        constraint1.setWidthPercentage(75);
        constraint1.setHeightPercentage(0);
        TableLayout.Constraint constraint2 = layout.createConstraint();
        constraint2.setWidthPercentage(25);
        constraint2.setHeightPercentage(0);
        main.addComponent(constraint1,numbers);
        main.addComponent(constraint2,operators);
        
        controls.setLayout(new TableLayout(1,4));
        Button dummyb = new Button();
        dummyb.setVisible(false);
        TableLayout.Constraint dummy = layout.createConstraint();
        dummy.setWidthPercentage(25);
        controls.addComponent(dummy,dummyb);

        Button Bracket1 = new Button("( ");
        Bracket1.addActionListener(new ActionListener(){

                    public void actionPerformed(ActionEvent ae) {
                        output.setText(output.getText()+((Button)ae.getComponent()).getText());
                    }
                    
                });
        TableLayout.Constraint constraint3 = layout.createConstraint();
        constraint3.setWidthPercentage(25);
        controls.addComponent(constraint3,Bracket1);

        Button Bracket2 = new Button(" )");
        Bracket2.addActionListener(new ActionListener(){

                    public void actionPerformed(ActionEvent ae) {
                        output.setText(output.getText()+((Button)ae.getComponent()).getText());
                    }
                    
                });
        TableLayout.Constraint constraint4 = layout.createConstraint();
        constraint4.setWidthPercentage(25);
        controls.addComponent(constraint4,Bracket2);
        Button del = new Button("DEL");
        TableLayout.Constraint delc = layout.createConstraint();
        delc.setWidthPercentage(25);
        controls.addComponent(delc,del);
        del.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent ae) {
                if(output.getText().length()!=0){
                if(output.getText().endsWith(" + ")||output.getText().endsWith(" - ")||output.getText().endsWith(" * ")||output.getText().endsWith(" / ")){
                output.setText(output.getText().substring(0, output.getText().length()-3));
                }else{
                    if(output.getText().endsWith("( ")||output.getText().endsWith(" )")){
                      output.setText(output.getText().substring(0, output.getText().length()-2));
                    }else{
                         output.setText(output.getText().substring(0, output.getText().length()-1));
                    }
                }
               
                }
            }
            
        });
        mainForm.addComponent(output);
        mainForm.addComponent(controls);
        mainForm.addComponent(main);
        Command exitCommand = new Command("Exit"){

            public void actionPerformed(ActionEvent evt) {
                notifyDestroyed();
            }
            
        };
        mainForm.addCommand(exitCommand);
        Command Clear = new Command("Clear"){

            public void actionPerformed(ActionEvent evt) {
                 output.setText("");
                main.setEnabled(true);
                 controls.setEnabled(true);
            }
            
        };
        mainForm.addCommand(Clear);
        
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

            r = Resources.open("/calculator.res");

           
            UIManager.getInstance().setThemeProps(r.getTheme("Theme 1"));
            // UIManager.getInstance().setResourceBundle(r.getL10N("localize", "en"));
            //  UIManager.getInstance().setResourceBundle(r.getL10N("localize", "en"));

           // mHomeForm.refreshTheme();
           mainForm.refreshTheme();
           mainForm .show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void pauseApp() {
    }

    public void destroyApp(boolean unconditional) {
    }
}
