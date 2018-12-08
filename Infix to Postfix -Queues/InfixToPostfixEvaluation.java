package lmu.cmsi281.assignments;

import java.util.ArrayList;
import java.util.Stack;

public class InfixToPostfixEvaluation {

	public static int getPrecedence(String operator) {
		// Addition and subtraction holds least precedence
		if (operator.equals("+") || operator.equals("-")) {
			return 1;
		}
		// Multiplication and division hold higher precedence
		if (operator.equals("*") || operator.equals("/")) {
			return 2;
		}
		// Exponent holds highest precedence
		if (operator.equals("^")) {
			return 3;
		}
		// Invalid operand
        return -1;
	}
	
	public static boolean isDigit(String s) {
		try  {  
		    toDigit(s);
		} catch (NumberFormatException e) {  
		    return false;  
		}  
		  
		return true;  
	}
	
	public static int toDigit(String s) throws NumberFormatException {
		return Integer.parseInt(s);  
	}
	
	public static boolean isValidSymbol(String s) {
		if (isDigit(s)) {
			return true;
		}
		
		switch (s) {
			case "+":
			case "-":
			case "*":
			case "/":
			case "^":
			case "(":
			case ")":
				return true;
		}
		return false;
	}
	
	public static ArrayList<String> infixToPostfix(String infix) throws RuntimeException {
		
		/** Infix to Postfix Algorithm
		 * 	Given a list of tokens:
		 * 
		 * 	If the token is not a numeric nor a valid symbol 
		 * 		then we will throw a RuntimeException
		 * 	If the token is a numeric (operand)
		 * 		then we add it to the result
		 * 	If the token is a "("
		 * 		then we push it onto the stack
		 * 	If the token is a ")"
		 *		then we pop each operator off the stack and add to our result until we reach "("
		 *		which we also pop off
		 *	If the token is an operator: "+", "-", "*", "/", "^"
		 *		then check the precedence of the operator on the stack, and 
		 *		while the operators on the stack has more than or equal precedence to the one at hand, 
		 *		we pop them off the stack and add them to our result
		 *		until we reach one that has less precedence than the one we are processing
		 *		we then push our operator onto the stack
		 *	Once we are done with processing our tokens, pop every element off the stack and add
		 *  it to our result
		 */
		
		ArrayList<String> result = new ArrayList<String>();
		Stack<String> stack = new Stack<String>();
		
		// Splits the infix based on spaces: "1 + 2" --> [ 1, +, 2 ]
		String[] tokens = infix.split(" ");
		String operators = "*^/-+" ;
			
		for (int i = 0; i < tokens.length; i++) {
			
			String curr = tokens[i];
			
			if (isValidSymbol(tokens[i]) == false) {
				throw new RuntimeException("Invalid Symbol");
			}
			
	        if (isDigit(curr)) {
                result.add(curr);                
            } 
	        else if (curr.equals("(")) {   // encountered parenthesis 
                stack.push(curr);
            }

            else if (curr.equals(")")) {  //found the end of parenthesis, go back to note operands
                while (!stack.isEmpty() && !stack.peek().equals("(")) {
                    result.add(stack.pop());
                }
                if(!stack.isEmpty())
                    stack.pop();
            }
	        
            else if (operators.contains(curr)) // operator encountered
            {
            	while (!stack.isEmpty() && (getPrecedence(curr) <= getPrecedence(stack.peek()))) {
            		result.add(stack.pop());
            	}
                stack.push(curr);
                }
    
		}

		while(!stack.isEmpty()) {
			result.add(stack.pop());
		}
			
		return result;

	}
	
	public static Integer evaluatePostfix(ArrayList<String> postfix) throws RuntimeException {
		
		/** Postfix Evaluation Algorithm
		 * 	Given a list of tokens of a postfix expression:
		 * 
		 * 	If the token is not a numeric nor a valid symbol 
		 * 		then we will throw a RuntimeException
		 * 	If the token is a numeric (operand)
		 * 		then we add it to the operands stack
		 *	If the token is an operator: "+", "-", "*", "/", "^"
		 *		then we evaluate the first two elements in operands using the operator
		 *		we then push result back onto the operands stack
		 *	Once we are done with processing our tokens, we return the result 
		 *
		 *	If the postfix expression is invalid, we should throw a RuntimeException
		 */
		
		Stack<String> operands = new Stack<String>();
		
		for (int i=0; i < postfix.size(); i++) {
			String curr = postfix.get(i);
		
			if (isValidSymbol(curr) == false) {
				throw new RuntimeException("Invalid Symbol");
			}
			
			if (isDigit(curr)) {
				operands.push(curr);
			}
			else {
				if (operands.size() < 2) {
					throw new RuntimeException("Invalid Postfix Expression");
				}
				else {
					int val1 = toDigit(operands.pop());
					int val2 = toDigit(operands.pop());
				
					operands.push(Integer.toString(evaluate(curr, val2, val1)));			
			
				}
			}
		}
	
		
		return Integer.parseInt(operands.pop());
	}
	
	public static Integer evaluate(String operator, int a, int b) throws RuntimeException {
		
		if (operator.equals("+")) {
			return a + b;
		}
		
		if (operator.equals("-")) {
			return a - b;
		}
		
		if (operator.equals("*")) {
			return a * b;
		}
		
		if (operator.equals("/")) {
			return a / b;
		}
		
		if (operator.equals("^")) {
			return (int)Math.pow(a, b);
		}
		// Invalid operator
		throw new RuntimeException();
	}
	
	public static void main(String[] args) {
		String infix0 = "1 * ( 2 + 3 ) / 4 + 5";
		String infix1 = "1 + 2 * 3 / 4 - 5 + 6 / 7 ";
		String infix2 = "1 + 2 * ( 3 ^ 4 - 5 ) + ( 6 + 7 * 8 ) - 9";
		
		ArrayList<String> postfix0 = infixToPostfix(infix0);
		ArrayList<String> postfix1 = infixToPostfix(infix1);
		ArrayList<String> postfix2 = infixToPostfix(infix2);
		
		System.out.println(postfix0);	// [1, 2, 3, +, *, 4, /, 5, +]
		System.out.println(postfix1);	// [1, 2, 3, *, 4, /, +, 5, -, 6, 7, /, +]
		System.out.println(postfix2);	// [1, 2, 3, 4, ^, 5, -, *, +, 6, 7, 8, *, +, +, 9, -]
	
		System.out.println(evaluatePostfix(postfix0));	// 6
		System.out.println(evaluatePostfix(postfix1));	// -3
		System.out.println(evaluatePostfix(postfix2));	// 206
	}
}
