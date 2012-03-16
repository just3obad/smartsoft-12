/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hello;

import com.sun.lwuit.*;
import com.sun.lwuit.events.ActionEvent;
import com.sun.lwuit.events.ActionListener;
import com.sun.lwuit.layouts.BoxLayout;
import com.sun.lwuit.plaf.UIManager;
import com.sun.lwuit.util.Resources;
import javax.microedition.midlet.*;

/**
 * @author MESAI
 */
public class Midlet extends MIDlet {

    double input1;
    double input2;
    int operator;
    Form mainForm; //this is your main Form

    public void startApp() {
        Display.init(this);
        createMainUI();
        installTheme();//this method is a way to organize your code ican write whats inside createMainUI()here

    }

    private void installTheme() {
        try {
            Resources r;
            r = Resources.open("/julie.rs");//you open the.res file
            UIManager.getInstance().setThemeProps(r.getTheme("Theme 1"));// referencing the theme
            mainForm.refreshTheme();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void createMainUI() {
        mainForm = new Form("Calculator");

        mainForm.setLayout(new BoxLayout(BoxLayout.Y_AXIS));




        final TextField out = new TextField();
        out.setHint("Enter Operand");

        Button plus = new Button(" + ");
        Button times = new Button(" x ");
        Button minus = new Button(" - ");
        Button divide = new Button(" / ");
        Button percentile = new Button(" % ");
        Button equals = new Button("  =  ");
        Button dot = new Button(" . ");
        Button cancel = new Button("  CE  ");
        Button oneover = new Button(" 1/x");
        Button back = new Button(" Del ");

        Button one = new Button(" 1 ");
        Button two = new Button(" 2 ");
        Button three = new Button(" 3 ");
        Button four = new Button(" 4 ");
        Button five = new Button(" 5 ");
        Button six = new Button(" 6 ");
        Button seven = new Button(" 7 ");
        Button eight = new Button(" 8 ");
        Button nine = new Button(" 9 ");
        Button zero = new Button(" 0 ");

        plus.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent ae) {

                try {
                    if (out.getText().length() != 0) {



                        input1 = Double.parseDouble(out.getText());
                        out.setText("0");
                        operator = 1;


                    } else {

                        out.setText("Write Something Please");




                    }
                } catch (NumberFormatException e) {
                    out.setText("Invalid Argument");
                }
            }
        });

        minus.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent ae) {

                try {
                    if (out.getText().length() != 0) {



                        input1 = Double.parseDouble(out.getText());
                        out.setText("0");
                        operator = 2;


                    } else {

                        out.setText("Write Something Please");




                    }
                } catch (NumberFormatException e) {
                    out.setText("Invalid Argument");
                }
            }
        });


        times.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent ae) {
                try {
                    if (out.getText().length() != 0) {



                        input1 = Double.parseDouble(out.getText());
                        out.setText("0");
                        operator = 3;


                    } else {

                        out.setText("Write Something Please");




                    }
                } catch (NumberFormatException e) {
                    out.setText("Invalid Argument");
                }
            }
        });

        divide.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent ae) {
                try {
                    if (out.getText().length() != 0) {
                        input1 = Double.parseDouble(out.getText());
                        out.setText("0");
                        operator = 4;
                    } else {

                        out.setText("Write Something Please");
                    }
                } catch (NumberFormatException e) {
                    out.setText("Invalid Argument");
                }
            }
        });

        percentile.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent ae) {
                try {
                    if (out.getText().length() != 0) {
                        input1 = Double.parseDouble(out.getText());
                        out.setText("0");
                        operator = 5;
                    } else {

                        out.setText("Write Something Please");
                    }
                } catch (NumberFormatException e) {
                    out.setText("Invalid Argument");
                }
            }
        });




        oneover.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent ae) {

                try {
                    if (out.getText().equals("0")) {
                        out.setText("Math error");
                    } else {
                        out.setText(Double.toString(1.0 / Double.parseDouble(out.getText())));
                    }
                } catch (NumberFormatException e) {
                    out.setText("Invalid Argument");
                }


            }
        });




        dot.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent ae) {
                if (out.getText().equals("0")) {
                    out.setText("0.");
                } else if (out.getText().indexOf('.') == -1) {
                    out.setText(out.getText() + ".");
                } else {
                }
            }
        });





        back.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent ae) {



                if (!(out.getText().equals("0") || out.getText().equals("0.0"))) {

                    if (out.getText().length() > 1) {

                        out.setText("0");
                    } else {
                        out.setText(out.getText().substring(0, out.getText().length() - 1));
                    }


                }



            }
        });





        equals.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent ae) {
                try {
                    input2 = Double.parseDouble(out.getText());
                    switch (operator) {
                        case 1:
                            out.setText(Double.toString(input1 + input2));
                            input1 = 0.0;
                            input2 = 0.0;
                            break;
                        case 2:
                            out.setText(Double.toString(input1 - input2));
                            input1 = 0.0;
                            input2 = 0.0;
                            break;
                        case 3:
                            out.setText(Double.toString(input1 * input2));
                            input1 = 0.0;
                            input2 = 0.0;
                            break;
                        case 4:
                            if (input2 != 0.0) {
                                out.setText(Double.toString(input1 / input2));
                                input1 = 0.0;
                                input2 = 0.0;
                            } else {
                                out.setText("Math Error");
                            }
                            break;
                        case 5:
                            out.setText(Double.toString(input1 % input2));
                            input1 = 0.0;
                            input2 = 0.0;
                    }


                } catch (NumberFormatException e) {
                    out.setText("Sorry,Invalid Arguments");
                }
            }
        });









        // DIGITS LISTENERS !! 

        one.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent ae) {
                if (out.getText().equals("0")) {
                    out.setText("1");
                } else {
                    out.setText(out.getText() + "1");
                }
            }
        });


        two.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent ae) {
                if (out.getText().equals("0")) {
                    out.setText("2");
                } else {
                    out.setText(out.getText() + "2");
                }
            }
        });



        three.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent ae) {
                if (out.getText().equals("0")) {
                    out.setText("3");
                } else {
                    out.setText(out.getText() + "3");
                }
            }
        });




        four.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent ae) {
                if (out.getText().equals("0")) {
                    out.setText("4");
                } else {
                    out.setText(out.getText() + "4");
                }
            }
        });




        five.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent ae) {
                if (out.getText().equals("0")) {
                    out.setText("5");
                } else {
                    out.setText(out.getText() + "5");
                }
            }
        });



        six.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent ae) {
                if (out.getText().equals("0")) {
                    out.setText("6");
                } else {
                    out.setText(out.getText() + "6");
                }
            }
        });



        seven.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent ae) {
                if (out.getText().equals("0")) {
                    out.setText("7");
                } else {
                    out.setText(out.getText() + "7");
                }
            }
        });


        eight.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent ae) {
                if (out.getText().equals("0")) {
                    out.setText("8");
                } else {
                    out.setText(out.getText() + "8");
                }
            }
        });

        nine.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent ae) {
                if (out.getText().equals("0")) {
                    out.setText("9");
                } else {
                    out.setText(out.getText() + "9");
                }
            }
        });



        zero.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent ae) {
                if (out.getText().equals("0")) {
                    out.setText("0");
                } else {
                    out.setText(out.getText() + "0");
                }
            }
        });




        Command exitCommand = new Command("Exit") {

            public void actionPerformed(ActionEvent evt) {
                notifyDestroyed();
            }
        };


        mainForm.addCommand(exitCommand);

        // mainForm.addComponent(input1);
        // mainForm.addComponent(input2);
        Container c1 = new Container();
        Container c2 = new Container();
        Container c3 = new Container();
        Container c4 = new Container();
        Container c5 = new Container();
        Container c6 = new Container();
        Container c7 = new Container();


        mainForm.addComponent(out);


        mainForm.addComponent(c1);
        mainForm.addComponent(c5);
        mainForm.addComponent(c2);
        mainForm.addComponent(c6);
        mainForm.addComponent(c3);
        mainForm.addComponent(c7);
        mainForm.addComponent(c4);


        final Label s1 = new Label("               ");
        final Label s2 = new Label("               ");
        final Label s3 = new Label("               ");


        c5.addComponent(s1);
        c6.addComponent(s2);
        c7.addComponent(s3);



        c1.addComponent(seven);
        c1.addComponent(eight);
        c1.addComponent(nine);
        c1.addComponent(divide);
        c1.addComponent(cancel);


        c2.addComponent(four);
        c2.addComponent(five);
        c2.addComponent(six);
        c2.addComponent(times);
        c2.addComponent(back);

        c3.addComponent(one);
        c3.addComponent(two);
        c3.addComponent(three);
        c3.addComponent(minus);
        c3.addComponent(oneover);

        c4.addComponent(zero);
        c4.addComponent(dot);
        c4.addComponent(percentile);
        c4.addComponent(plus);
        c4.addComponent(equals);

        mainForm.addCommand(exitCommand);
        // last thing you need to shoe all this on the screen
        mainForm.show();
        installTheme();



    }

    public void pauseApp() {
    }

    public void destroyApp(boolean unconditional) {
    }
}
