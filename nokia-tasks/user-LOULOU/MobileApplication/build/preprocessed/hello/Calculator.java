/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hello;

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
import com.sun.lwuit.layouts.GridLayout;
import com.sun.lwuit.plaf.UIManager;
import com.sun.lwuit.util.Resources;
import javax.microedition.midlet.*;

public class Calculator extends MIDlet {

    Form mainForm;
    Container buttons;
    double result;
    double operand;
    char operation;
    Label input;

    public void startApp() {
        Display.init(this);
        createMainUI();//this method is a way to organize your code/i can write whats inside createMainUI()here
        installTheme();
    }

    public static int getNumberOfDecimalPlace(String value) {
        final int index = value.indexOf('.');
        if (index < 0) {
            return 0;
        }
        return value.length() - 1 - index;
    }

    public void createMainUI() {

        mainForm = new Form("Calculator");
        mainForm.setLayout(new BoxLayout(BoxLayout.Y_AXIS));

        buttons = new Container();
        buttons.setLayout(new GridLayout(5, 4));

        input = new Label();
        input.setText("0");

        final Label output = new Label("");
        output.setText("0");

        final Label outputLabel = new Label("Result:");

        final Label error = new Label(" ");

        //Button c
        //when I press answer beyzawed 7aga fel input 3al input adim
        //Dot
        //Negative
        //AC

        //// Abutton to insert
        Button one = new Button("1");
        Button two = new Button("2");
        Button three = new Button("3");
        Button four = new Button("4");
        Button five = new Button("5");
        Button six = new Button("6");
        Button seven = new Button("7");
        Button eight = new Button("8");
        Button nine = new Button("9");
        Button zero = new Button("0");
        Button answer = new Button("Ans");

        Button equals = new Button("=");
        Button plus = new Button("+");
        Button minus = new Button("-");
        Button multiply = new Button("*");
        Button divide = new Button("/");

        Button c = new Button("C");
        Button ac = new Button("AC");
        Button point = new Button(".");
        Button negative = new Button("+/-");

        c.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent ae) {
                if (input.getText().equals("")) {
                    error.setText("Please press the 'Ans' button");
                } else {
                    int op = (int) operand / 10;
                    operand = op;
                    input.setText(operand + "");
                }
            }
        });
        ac.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent ae) {
                input.setText("");
                output.setText("");
                operand = 0;
                result = 0;
            }
        });
        negative.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent ae) {
                if (input.getText().equals("")) {
                    error.setText("Please press the 'Ans' button");
                } else {
                    operand = (-1) * operand;
                    input.setText(operand + "");
                }
            }
        });

        one.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent ae) {
                operand = (operand * 10) + 1;
                input.setText(operand + "");
                System.out.println("ONEEEE   " + operand);
            }
        });
        two.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent ae) {
                operand = (operand * 10) + 2;
                input.setText(operand + "");
                System.out.println("TWOOOO   " + operand);
            }
        });
        three.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent ae) {
                operand = (operand * 10) + 3;
                input.setText(operand + "");
                System.out.println("THREEEE   " + operand);
            }
        });
        four.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent ae) {
                operand = (operand * 10) + 4;
                input.setText(operand + "");
            }
        });
        five.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent ae) {
                operand = (operand * 10) + 5;
                input.setText(operand + "");
            }
        });
        six.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent ae) {
                operand = (operand * 10) + 6;
                input.setText(operand + "");
            }
        });
        seven.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent ae) {
                operand = (operand * 10) + 7;
                input.setText(operand + "");
            }
        });
        eight.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent ae) {
                operand = (operand * 10) + 8;
                input.setText(operand + "");
            }
        });
        nine.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent ae) {
                operand = (operand * 10) + 9;
                input.setText(operand + "");
            }
        });
        zero.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent ae) {
                operand = operand * 10;
                input.setText(operand + "");
            }
        });

        plus.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent ae) {
                if (input.getText().equals("")) {
                    error.setText("Please press the 'Ans' button");
                } else {
                    result = operand;
                    operand = 0;
                    operation = '+';
                    input.setText("");
                }

            }
        });
        minus.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent ae) {
                if (input.getText().equals("")) {
                    error.setText("Please press the 'Ans' button");
                } else {
                    result = operand;
                    operand = 0;
                    operation = '-';
                    input.setText("");
                }
            }
        });
        multiply.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent ae) {
                if (input.getText().equals("")) {
                    error.setText("Please press the 'Ans' button");
                } else {
                    result = operand;
                    operand = 0;
                    operation = '*';
                    input.setText("");
                }
            }
        });
        divide.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent ae) {
                if (input.getText().equals("")) {
                    error.setText("Please press the 'Ans' button");
                } else {
                    result = operand;
                    operand = 0;
                    operation = '/';
                    input.setText("");
                }
            }
        });
        equals.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent ae) {
                switch (operation) {
                    case '+':
                        result = result + operand;
                        break;
                    case '-':
                        result = result - operand;
                        break;
                    case '*':
                        result = result * operand;
                        break;
                    case '/':
                        result = result / operand;
                        break;
                }
                input.setText("");
                output.setText(result + "");
                operand = 0;
            }
        });
        answer.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent ae) {
                error.setText("");
                input.setText(result + "");
                operand = result;
            }
        });


        // a command to exit
        Command exitCommand = new Command("Exit") {

            public void actionPerformed(ActionEvent evt) {
                notifyDestroyed();
            }
        };

        mainForm.addCommand(exitCommand);

        mainForm.addComponent(input);

        mainForm.addComponent(buttons);
        buttons.addComponent(c);
        buttons.addComponent(ac);
        buttons.addComponent(point);
        buttons.addComponent(negative);
        buttons.addComponent(one);
        buttons.addComponent(two);
        buttons.addComponent(three);
        buttons.addComponent(plus);
        buttons.addComponent(four);
        buttons.addComponent(five);
        buttons.addComponent(six);
        buttons.addComponent(minus);
        buttons.addComponent(seven);
        buttons.addComponent(eight);
        buttons.addComponent(nine);
        buttons.addComponent(multiply);
        buttons.addComponent(zero);
        buttons.addComponent(answer);
        buttons.addComponent(equals);
        buttons.addComponent(divide);

        mainForm.addComponent(outputLabel);
        mainForm.addComponent(output);
        mainForm.addComponent(error);

        mainForm.addCommand(exitCommand);
        // last thing you need to shoe all this on the screen
        mainForm.show();

    }

    private void installTheme() {
// This is not the normal way to do this.
// Usually you load a theme from a file.
// UIManager uim = UIManager.getInstance();
// Hashtable ht = new Hashtable();
// ht.put("sel#" + Style.BG_COLOR, "ffffff");
// ht.put(Style.BG_COLOR, "ffffff");
// ht.put(Style.FG_COLOR, "000000");
// uim.setThemeProps(ht);
        try {
            Resources r;

            r = Resources.open("/calculatorTheme.res");


            UIManager.getInstance().setThemeProps(r.getTheme("Calculator"));
// UIManager.getInstance().setResourceBundle(r.getL10N("localize", "en"));
// UIManager.getInstance().setResourceBundle(r.getL10N("localize", "en"));

// mHomeForm.refreshTheme();
            mainForm.refreshTheme();
            //mainForm.show();
        } catch (Exception e) {
            System.out.println("Error");
        }
    }

    public void pauseApp() {
    }

    public void destroyApp(boolean unconditional) {
    }
}
