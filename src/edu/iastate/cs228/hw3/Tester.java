package edu.iastate.cs228.hw3;

import java.util.Random;

public class Tester {
	public static void main(String[] args){
		//This is my tester class for Project 3. Here I will run unofficial tests.
		//No JUnit tests will be in this class. This is just for my personal use.
		
		PrimeFactorization pf1 = new PrimeFactorization();
		pf1.add(11,3);
		pf1.add(5, 1);
		pf1.add(7, 1);
		System.out.println(pf1.value());
		System.out.println(pf1.containsPrimeFactor(11));
	}
}
