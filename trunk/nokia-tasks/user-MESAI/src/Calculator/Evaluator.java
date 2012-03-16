package Calculator;



import java.util.Vector;

class Node  {
	public String data;
	public Node next;
	public Node () {
	   data =" "; 
           next = null; 
        }
	public Node (String val) {
	   data = val;  next = null; }
}

public class Evaluator {
	private Node top;
	/*  Creates a new instance of LinkStack */
	public Evaluator() {
		top = null; }
	public boolean empty(){
		return top == null; }
   
	
	public boolean full(){
		return false;
	}
public void push(String e){
		Node tmp = new Node(e);
		tmp.next = top;
		top = tmp;
	}
public String pop(){

	String e =  top.data;
	top = top.next;
	return e;
}
public String peek(){

	String e =  top.data;
   
	return e;
}


public void matching(String x)
{	
	Evaluator S=new Evaluator();

	for(int i=0;i<x.length();i++)
	{
		char c=x.charAt(i);
		if(c=='(')
			S.push(c+"");
                        
		else
		{
			if(c==')')
			if(S.empty())
				System.out.println("NOT MATCHING !!!");
			else
				S.pop();
		}
	}
	if(!S.empty())
		System.out.println("NOT MATCHING !!!");
	else
		System.out.println("MATCHING !!!");
}
public String Evaluation(String x)
{
	
	Evaluator S=new Evaluator();
        String [] input = split(x," ");
	for(int i=0;i<input.length;i++)
	{
		String c = input[i];
		String s= ""+c;
		
		if(c.equals("+")){
		
			double z=Double.parseDouble(S.pop()+"")+Double.parseDouble(S.pop()+"");
			S.push(z+"");
		}
		else if(c.equals("*"))
		{
			double z=Double.parseDouble((String)S.pop())*Double.parseDouble((String)S.pop());
			S.push(z+"");
			
		}
		else if(c.equals("/"))
		{	double u=Double.parseDouble((String)S.pop());
			
			double z= (Double.parseDouble((String)S.pop())/u);
			S.push(z+"");
			
		}
		else if(c.equals("-"))
		{	double u=Double.parseDouble((String)S.pop());
			double z=Double.parseDouble((String)S.pop())-u;
			S.push(z+"");
		}
		else
		S.push(s);
	}
        String result = S.pop();
	System.out.println("THE POSTFIX = "+x);
	System.out.println("THE RESULT = "+result);
        return result;
}
public String postfix(String x) throws Exception
{
           
	String output="";
        String[] input = split(x," ");
	Evaluator S=new Evaluator();
        if(x.equals("( ")||x.equals(" )"))
              throw new Exception("");
        
        
        if(input.length==1){
             try{
                 Double.parseDouble(x);
             }
             catch(Exception e){
              throw new Exception("");
             }
             return x;
        }
       
	for(int i=0;i<input.length;i++)
	{
		String c=input[i];
		System.out.println(input[i]);
		if(c.equals("+")||c.equals("*")||c.equals("-")||c.equals("/"))
			{while(!S.empty() && priority(S.peek())>= priority(c+""))
				output+=S.pop()+" ";
			S.push(c+"");
			}
		else if(c.equals("("))
		{
			S.push(c+"");
		}
		else if(c.equals(")"))
		{
			while(!S.peek().equals("("))
					output+=S.pop()+" ";
			S.pop();
		}
		else
			output+=c+" ";
	}
                if(!S.empty()){
		output+=S.pop()+" ";
                }
                System.out.println(">>>"+output);
         if(output.equals("( ")||output.equals(" )")||output.equals(" "))
              throw new Exception("");
        
         if(!S.empty()){
             throw new Exception("");
         }
	System.out.println("THE INFIX = "+x);
	System.out.println("THE POSTFIX = "+output);
        return output.substring(0,output.length()-1);
}
public int priority(Object x)
{
	if(x.equals("+")||x.equals("-"))
		return 1;
	else if(x.equals("*")||x.equals("/"))
		return 2;
	else
		return 0;
}

 private String[] split(String original, String separator) {
        Vector nodes = new Vector();
        // Parse nodes into vector
        int index = original.indexOf(separator);
        while (index >= 0) {
            nodes.addElement(original.substring(0, index));
            original = original.substring(index + separator.length());
            index = original.indexOf(separator);
        }
        // Get the last node
        nodes.addElement(original);

        // Create splitted string array
        String[] result = new String[nodes.size()];
        if (nodes.size() > 0) {
            for (int loop = 0; loop < nodes.size(); loop++) {
                result[loop] = (String) nodes.elementAt(loop);
            }

        }

        return result;
    }


}






