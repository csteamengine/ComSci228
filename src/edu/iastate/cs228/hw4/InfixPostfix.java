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

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;

import edu.iastate.cs228.hw2.InsertionSorter;
import edu.iastate.cs228.hw2.MergeSorter;
import edu.iastate.cs228.hw2.QuickSorter;
import edu.iastate.cs228.hw2.SelectionSorter;

public class InfixPostfix 
{

	/**
	 * Repeatedly evaluates input infix and postfix expressions.  See the project description
	 * for the input description. It constructs a HashMap object for each expression and passes it 
	 * to the created InfixExpression or PostfixExpression object. 
	 *  
	 * @param args
	 * @throws UnassignedVariableException 
	 * @throws ExpressionFormatException 
	 * @throws FileNotFoundException 
	 **/
	public static void main(String[] args) throws ExpressionFormatException, UnassignedVariableException, FileNotFoundException 
	{
		HashMap map = new HashMap();
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
				char inputType = expression.charAt(0);
				expression = expression.substring(1);
				if(inputType == 'I'){
					Scanner exprScan = new Scanner(expression);
					InfixExpression IFP = new InfixExpression(expression);
					System.out.println("Infix Form: " + expression);
					System.out.println("Postfix Form: " + IFP.postfixString());
					while(exprScan.hasNext()){
						char c = exprScan.next().charAt(0);
						if((int) c >= 97 && (int) c <= 122){
							System.out.print(c + " = ");
							char key = c;
							map.put(c, scan.nextInt());
						}
					}
					InfixExpression IFP2 = new InfixExpression(expression,map);
					System.out.println("Expression Value: " + IFP2.evaluate());
					System.out.println();
				}else if(inputType == 'P'){
					Scanner exprScan = new Scanner(expression);
					PostfixExpression PFE = new PostfixExpression(expression);
					System.out.println("Postfix Form: " + PFE.toString());
					System.out.println("Where: ");
					while(exprScan.hasNext()){
						char c = exprScan.next().charAt(0);
						if((int) c >= 97 && (int) c <= 122){
							System.out.print(c + " = ");
							Scanner in = new Scanner(System.in);
							char key = c;
							map.put(c, scan.nextInt());
						}
					}
					PostfixExpression PFE2 = new PostfixExpression(expression, map);
					System.out.println("Expression Value: " + PFE2.evaluate());
					System.out.println();
				}else{
					throw new InputMismatchException("Incorrect Input");
				}
			}else if(inputChoice == 2){
				System.out.println("Input from a file");
				System.out.print("Enter File Name: ");
				String fileName = scan.next();	
				System.out.println();
				File file = new File(fileName);
				System.out.println(file);
				Scanner scan2 = new Scanner(file);
				while(scan2.hasNextLine()){
					String expression = scan2.nextLine();
					char inputType = expression.charAt(0);
					expression = expression.substring(1);
					if(inputType == 'I'){
						Scanner exprScan = new Scanner(expression);
						InfixExpression IFE = new InfixExpression(expression);
						System.out.println("Infix form: " + IFE.toString());
						System.out.println("Where: ");
						while(exprScan.hasNext()){
							char c = exprScan.next().charAt(0);
							if((int) c >= 97 && (int) c <= 122){
								System.out.print(c + " = ");
								Scanner in = new Scanner(System.in);
								char key = c;
								map.put(c, scan.nextInt());
							}
						}
						InfixExpression IFE2 = new InfixExpression(expression, map);
						System.out.println("Expression Value: " + IFE2.evaluate());
						System.out.println();
					}else if(inputType == 'P'){
						Scanner exprScan = new Scanner(expression);
						InfixExpression IFE = new InfixExpression(expression);
						System.out.println("Postfix form: " + IFE.toString());
						System.out.println("Where: ");
						while(exprScan.hasNext()){
							char c = exprScan.next().charAt(0);
							if((int) c >= 97 && (int) c <= 122){
								System.out.print(c + " = ");
								Scanner in = new Scanner(System.in);
								char key = c;
								map.put(c, scan.nextInt());
							}
						}
						PostfixExpression PFE2 = new PostfixExpression(expression, map);
						System.out.println("Expression Value: " + PFE2.evaluate());
						System.out.println();
					}else{
						throw new InputMismatchException("File is formatted incorrectly");
					}
				}
			}else{
				break;
			}
		}
		
	}
	
	// helper methods if needed

}
