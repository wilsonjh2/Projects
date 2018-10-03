/*
 *James Hunter Wilson
 *10/7/17
 *MidTerm Programing Section Part 1
 */
public class PrintStack {


	/*
		Code to print Stack S in reverse, i.e., from the bottom to top.
	        You are only allowed to use the push, pop and top operations defined in the Stack class.
		Your solution should be recursive. 
                So please use this as a "wrapper" function to wrap other recursive functions that prints the stack.
	*/
	public static void printStack(Stack S, Stack T, int count) {
	
		

		
		T  = add(S, T, 0);
	
		
		
		count++;

		//if the Stack S is empty there is nothing else to push onto stack T
		if(S.top() == -1){
		//function to print the Stack T	
		T = printT(T, 0);
		
		
		if(T.top() == -1){
		return;
		}
			
				 }
		
		//recursive call to the print stack function
		//this function is only called ~1000 times	
		printStack(S, T, count);
		return;
		
	
	}
	//This Method pushed the top element from Stack S onto the top of Stack T
	//This process is repeated 1000 times
	public static Stack add(Stack S, Stack T, int count){
	if(S.top() == -1){
	return T;
	}
	if(count == 1000){
	return T;
	}
	
	T.push(S.top());
	S.pop();
	
	count++;
	//recursively calls itself only 1000 times
	add(S, T, count);
	return T;
	}
	
	//Method to print the Stack T which is Stack S in reverse
	//This method only recurses 1000 for each call from print stack
	//Very similar to add
	public static Stack printT(Stack T, int count){
	if(T.top() == -1){
	
	return T;
	}
	else if(count == 1000){
	return T;
	}
	
	System.out.println(T.top());
	T.pop();
	count++;

	//recursive call
	printT(T, count);
	return T;
	}

	
	

	

	public static void main(String [] args) {
		Stack S = new Stack(false);	// Creates a Stack loaded with elements.
		Stack T = new Stack(true);

		 printStack(S, T, 0);
		
		
		
	
		
	}
	
}
