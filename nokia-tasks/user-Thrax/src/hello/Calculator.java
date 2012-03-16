/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hello;

/**
 *
 * @author Mostafa Mahmoud
 */
import com.sun.lwuit.*;
import com.sun.lwuit.events.ActionEvent;
import com.sun.lwuit.events.ActionListener;
import com.sun.lwuit.layouts.BoxLayout;
import com.sun.lwuit.plaf.UIManager;
import com.sun.lwuit.util.Resources;
import java.io.IOException;
import javax.microedition.midlet.MIDlet;

public class Calculator extends MIDlet {
    
    
    public void startApp() {
        Display.init(this);
        try {
            Resources r = Resources.open("/Theme1.res");
            UIManager.getInstance().setThemeProps(r.getTheme(r.getThemeResourceNames()[0]));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Form form = new Form();
        Button add = new Button("+");
        Button mult = new Button("*");
        Button subt = new Button("-");
        Button div = new Button("/");
        Button eval = new Button("=");
        Button clear = new Button("Clear");
        
        final TextField number = new TextField();
        final TextField res = new TextField();
        
        /*
        final TextField number1 = new TextField();
        final TextField number2 = new TextField();
        number1.setConstraint(TextField.NUMERIC);
        number2.setConstraint(TextField.NUMERIC);
        */
        
        number.setConstraint(TextField.NUMERIC);
        res.setEditable(false);
        
        form.setTitle("Smart's Calc");
        form.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
        Container buttons = new Container(new BoxLayout(BoxLayout.X_AXIS));
        
        
        add.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent ae) {
                evaluate(1, res, number);
            }
        });
        subt.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent ae) {
                evaluate(2, res, number);
            }
        });
        
        mult.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent ae) {
                evaluate(3, res, number);
            }
        });
        div.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent ae) {
                evaluate(4, res, number);
            }
        });
        eval.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent ae) {
                evaluate(0, res, number);
            }
        });
        
        clear.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent ae) {
                accumulator = 0;
                operator = 0;
                res.setText("");
                number.setText("");
            }
        });
        
        buttons.addComponent(0, add);
        buttons.addComponent(1, subt);
        buttons.addComponent(2, mult);
        buttons.addComponent(3, div);
        buttons.addComponent(4, eval);
        
        form.addComponent(0, number);
        form.addComponent(1, buttons);
        form.addComponent(2, res);
        form.addComponent(3, clear);
        
        form.show();

    }
    public void destroyApp(boolean unconditional) {
    }
    public void pauseApp() {
    }
 
    private int accumulator, operator;
    
    private boolean evaluate(int op, TextField res, TextField number) {
        int r = 0;
        boolean evaluated = false, divisionByZero = false;
        String num = number.getText();
        if (num.length() > 0) {
            int inp = Integer.parseInt(num);
            switch (operator) {
                case 1: r = accumulator + inp; evaluated = true; break;
                case 2: r = accumulator - inp; evaluated = true; break;
                case 3: r = accumulator * inp; evaluated = true; break;
                case 4: if (inp != 0) r = accumulator / inp; else divisionByZero = true; evaluated = true; break;
            }
        }
        setOperator(op, number);
        if (evaluated) {
            if (!divisionByZero) {
                accumulator = r;
                res.setText(r + "");
                number.setText("");
                return true;
            } else {
                res.setText("Divison by Zero");
                number.setText("");
            }
        }
        return false;
    }
    private boolean setOperator(int op, TextField number) {
        if (0 <= op && op <= 4) {
            operator = op;
            String num = number.getText();
            if (num.length() > 0)
                accumulator = Integer.parseInt(num);
            number.setText("");
            return true;
        }
        return false;
    }
    
}
