/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hello;

/**
 *
 * @author christine
 */
import java.io.IOException;
 
public class InToPost {
  private Stack theStack;
 
  private String input;
 
  private String output = "";
 
  public InToPost(String in) {
    input = in;
    int stackSize = input.length();
    theStack = new Stack(stackSize);
  }
 
  public String doTrans() {
    for (int j = 0; j < input.length(); j++) {
      char ch = input.charAt(j);
      switch (ch) {
      case '+': 
           output=output+" ";
        gotOper(ch, 1); 
        output=output+" ";
        break;
      case '-':
          if(j==0) {
            output = output + ch;
          }
          else if(j>1&&(input.charAt(j-1)=='*' || input.charAt(j-1)=='/' || input.charAt(j-1)=='(')) {
              output = output + ch;
          } else {
                output=output+" ";
                  gotOper(ch, 1); 
                 output=output+" ";
          }
          break;
          //   (precedence 1)
      case '*': // it's * or /
      case '/':
        output=output+" ";

        gotOper(ch, 2); 
        output=output+" ";// go pop operators
        break; //   (precedence 2)
      case '(': // it's a left paren
        theStack.push(ch); // push it
        break;
      case ')': // it's a right paren
        gotParen(ch); // go pop operators
        break;
      default: // must be an operand
        output = output + ch; // write it to output
        break;
      }
    }
    while (!theStack.isEmpty()) {
       output= output+" ";
      output = output + theStack.pop();
      output=output+" ";
      
 
    }
    System.out.println(output);
    return output; // return postfix
  }
 
  public void gotOper(char opThis, int prec1) {
    while (!theStack.isEmpty()) {
      char opTop = theStack.pop();
      if (opTop == '(') {
        theStack.push(opTop);
        break;
      }// it's an operator
      else {// precedence of new op
        int prec2;
        if (opTop == '+' || opTop == '-')
          prec2 = 1;
        else
          prec2 = 2;        if (prec2 < prec1) // if prec of new op less
        { //    than prec of old
          theStack.push(opTop); // save newly-popped op
          break;
        } else
          // prec of new not less
          output= output+" ";
          output = output + opTop; 
          output=output+" ";// than prec of old
      }
    }
    theStack.push(opThis);
  }
 
  public void gotParen(char ch){ 
    while (!theStack.isEmpty()) {
      char chx = theStack.pop();
      if (chx == '(') 
        break; 
      else
      {
          output= output+" ";
        output = output + chx; 
        output =output+" ";
        
    }
  }
  }
 
  public static void main(String[] args) throws IOException {
    String input = "1+2*4/5-7+3/6";
    String output;
    InToPost theTrans = new InToPost(input);
    output = theTrans.doTrans(); 
    System.out.println("Postfix is " + output + '\n');
 
  }
  class Stack {
    private int maxSize;
    private char[] stackArray;
    private int top;
    public Stack(int max) {
      maxSize = max;
      stackArray = new char[maxSize];
      top = -1;
    }
    public void push(char j) {
      stackArray[++top] = j;
    }
    public char pop() {
      return stackArray[top--];
    }
    public char peek() {
      return stackArray[top];
    }
    public boolean isEmpty() {
      return (top == -1);
    }
  }
 
}
