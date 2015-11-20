package edu.iastate.cs228.hw4;

import java.util.HashMap;
import java.util.Scanner;

public class tester {
	public static void main(String[] args) throws ExpressionFormatException, UnassignedVariableException{
		String a = "  2 ^ 3   ^ 2 % 100";
		String b = "1 - 2 - 1";
		InfixExpression IE = new InfixExpression(a);

		System.out.println(IE.evaluate());
		System.out.println(IE.postfixString());
	}
}
