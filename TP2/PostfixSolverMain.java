/**
 * programme principale sur les équations postfix
 * @author : Jacob Dorais(1879536) et Francois-Xavier Legault(1876882)
 * @date : 17-02-2018
 */

import java.io.*;
import java.util.Stack;

public class PostfixSolverMain 
{
	public static void main(String[] args) throws IOException 
	{
		Stack<Double> stack = new Stack<Double>();
		
		String s = "25 5 2 * + 15 3 / 5 - +";
		
		//L'expression est separee en tokens selon les espaces
		for(String token : s.split("\\s")) 
		{
			//A completer
			
			//si token est une opération
			if(token.equals("+") || token.equals("-") || token.equals("*") || token.equals("/"))
			{
				double first = stack.pop();
				double second = stack.pop();
				double result;
				switch(token)
				{
				case "+": result = first+second;break;
				
				case "-": result = second-first;break;
				
				case "*": result = first*second;break;
				
				case "/": result = second/first;break;
				
				default: result = 0;
				
				}
				
				stack.push(result);
			}
			else
			{
				//si token est un nombre
				
				stack.push(Double.parseDouble(token));
			}
		}
     
		System.out.println("25 + 5*2 + 15/3 - 5 = "+stack.peek());
		if(stack.peek() == 35)
			System.out.println("It's all good");
		else
			System.out.println("Erreur: mauvais resultat");
     }
}
