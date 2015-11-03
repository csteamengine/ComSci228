package edu.iastate.cs228.hw4;

/**
 *  
 * @author
 *
 */

/**
 * 
 * This class evaluates input infix and postfix expressions. 
 *
 */

import java.util.HashMap;
import java.util.Scanner;

public class InfixPostfix 
{

	/**
	 * Repeatedly evaluates input infix and postfix expressions.  See the project description
	 * for the input description. It constructs a HashMap object for each expression and passes it 
	 * to the created InfixExpression or PostfixExpression object. 
	 *  
	 * @param args
	 **/
	public static void main(String[] args) 
	{
		Scanner scan = new Scanner(System.in);
		System.out.println("Evaluation of Infix and Postfix Expressions\n"
				+ "keys: 1 (standard input) 2 (file input) 3 (exit)\n"
				+ "(Enter 'I' before an infix expression, 'P' before a postfix expression)");
		int cycles =0;
		System.out.println();
		while(true){
			cycles +=1;
			System.out.print("Trial " + cycles + ": ");
			int inputChoice = scan.nextInt();
			scan.nextLine();
			if(inputChoice ==1){
				System.out.print("Expression: ");
				String expression = scan.nextLine();
				if(expression.charAt(0) == 'I'){
					System.out.println("Infix Expression");
				}else if(expression.charAt(0) == 'P'){
					System.out.println("Postfix Expression");
				}
			}else if(inputChoice == 2){
				System.out.println("File Stuff");
			}else{
				break;
			}
		}
		
	}
	
	// helper methods if needed
}
