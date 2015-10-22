package edu.iastate.cs228.hw3;

import java.util.Random;

public class Tester {
	public static void main(String[] args){
		//This is my tester class for Project 3. Here I will run unofficial tests.
		//No JUnit tests will be in this class. This is just for my personal use.
		PrimeFactor pf = new PrimeFactor(5,1);
		PrimeFactor pf2 = pf.clone();
		pf.prime = 7;
		pf.multiplicity = 4;
		System.out.println(pf.toString());
		System.out.println(pf2.toString());
	}
}
