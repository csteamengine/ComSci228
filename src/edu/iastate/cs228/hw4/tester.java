package edu.iastate.cs228.hw4;

import java.util.HashMap;
import java.util.Scanner;

public class tester {
	public static void main(String[] args) throws ExpressionFormatException{
		String s = "1234";
		String n = "12ws2";
		System.out.println(Expression.isInt(s));
		System.out.println(Expression.isInt(n));
		char c = 'C';
		char b = 'b';
		System.out.println(Expression.isVariable(c));
		System.out.println(Expression.isVariable(b));
		String a = "(22+3) * 5";
		InfixExpression IE = new InfixExpression(a);
		IE.postfix();
		System.out.println(IE.postfixString());
	}
}
