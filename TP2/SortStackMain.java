import java.util.Random;
import java.util.Stack;


public class SortStackMain 
{
	static final int COUNT = 30;
	static final int MAX_VALUE = 1000;
	
	public static void main(String[] args) 
	{
		boolean sortIsGood = true;
		
		Random generator = new Random( System.nanoTime() );
		Stack<Integer> stack = new Stack<Integer>();
		
		for(int i = 0; i < COUNT; i++)
			stack.push(generator.nextInt(MAX_VALUE));
		
		stack = sortStack(stack);
		
		boolean countIsGood = size(stack) == COUNT;
			
		int tmp = stack.pop();
		while(!stack.isEmpty())
		{
			System.out.print(tmp + ", ");
			
			if(tmp > stack.peek())
				sortIsGood = false;
			
			tmp = stack.pop();
		}
		System.out.println(tmp);
		
		if(!countIsGood)
			System.out.println("Erreur: il manque des elements dans la pile");
		else if(!sortIsGood)
			System.out.println("Erreur: le trie a echoue");
		else
			System.out.println("It's all good");
	}
    
	private static int size(Stack<Integer> stack) {
		//A completer
		
		//enlever éléments en comptant.
		int qt = 0;
		Stack<Integer> tempStack = new Stack<Integer>();
		while(!stack.empty())
		{
			qt++;
			tempStack.push(stack.pop());
		}
		
		//remettre les éléments
		for(int i = 0; i < qt; i++) { stack.push(tempStack.pop());}
		
		return qt;
		
    }
    
	static Stack<Integer> sortStack(Stack<Integer> stack)
	{
		//A completer
		
		//nous allons procéder à un merge sort.
		//
		//comme nous ne pouvons utiliser que peek, pop, push et empty, 
		//nous compterons le nombre d'éléments lors de la séparation.
		int qt = 0;
		Stack<Integer> left = new Stack<Integer>();
		Stack<Integer> right = new Stack<Integer>();
		
		while(!stack.empty())
		{
			if(qt%2 == 0)
				left.push(stack.pop());
			else
				right.push(stack.pop());
			qt++;
		}
		
		if(qt == 1) {return left;} //déjà trié; seulement un élément dans left.
		
		
		//sort chaque partie 
		left = sortStack(left);
		right = sortStack(right);
		
		//merge les deux stack:
		Stack<Integer> inverted = new Stack<Integer>();
		for(int i = 0; i < qt; i++)
		{
			if(left.empty())
				inverted.push(right.pop());
			
			else if(right.empty())
				inverted.push(left.pop());
			
			else if(left.peek() <= right.peek())
				inverted.push(left.pop());
			
			else
				inverted.push(right.pop());
		}
		//inversion de l'ordre pour avoir le plus petit sur le dessus
		
		for(int i = 0; i < qt; i++)
			stack.push(inverted.pop());
		return stack;
	}
}
