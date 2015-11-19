package edu.iastate.cs228.hw4;

import static org.junit.Assert.*;

import org.junit.Test;

public class hw4_JUnitTests {

	@Test
	public void testOperators() {
		Operator plus = new Operator('+');	// input: 1   stack: 1	rank: -1
		Operator minus = new Operator('-'); // input: 1   stack: 1	rank: -1
		Operator mult = new Operator('*');  // input: 2   stack: 2	rank: -1
		Operator divide = new Operator('/');// input: 2   stack: 2	rank: -1
		Operator mod = new Operator('%');	// input: 2   stack: 2	rank: -1
		Operator carrot = new Operator('^');// input: 4   stack: 3	rank: -1
		Operator leftPar = new Operator('(');// input: 5   stack: -1	rank: 0
		Operator rightPar = new Operator(')');// input: 0   stack: 0	rank: 0
		
		//Making sure it returns the correct char
		assertEquals(plus.getOp(), '+');
		assertEquals(minus.getOp(), '-');
		assertEquals(mult.getOp(), '*');
		assertEquals(divide.getOp(), '/');
		assertEquals(mod.getOp(), '%');
		assertEquals(carrot.getOp(), '^');
		assertEquals(leftPar.getOp(), '(');
		assertEquals(rightPar.getOp(), ')');
		
		
		//Just testing random combinations... Didn't want to test 64 different combos
		assertEquals(plus.compareTo(minus), 0);
		assertEquals(plus.compareTo(mult), -1);
		assertEquals(plus.compareTo(carrot), -1);
		assertEquals(plus.compareTo(leftPar), 1);
		assertEquals(mult.compareTo(divide), 0);
		assertEquals(mult.compareTo(leftPar), 1);
		assertEquals(mult.compareTo(plus), 1);
		assertEquals(mult.compareTo(mod), 0);
		assertEquals(leftPar.compareTo(plus), -1);
		assertEquals(leftPar.compareTo(mult), -1);
		assertEquals(leftPar.compareTo(divide), -1);
		assertEquals(leftPar.compareTo(carrot), -1);
		assertEquals(leftPar.compareTo(rightPar), -1);
		
	}
	public void testInfixExpression() throws ExpressionFormatException{
		//Testing toPostfix
		InfixExpression IE = new InfixExpression("(2+3) * 5");
		IE.postfix();
		assertEquals(IE.postfixExpression, "2 3 + 5 *");
	}
	public void testPostfixExpression(){
		
	}
	public void testMainProgram(){
		
	}

}
