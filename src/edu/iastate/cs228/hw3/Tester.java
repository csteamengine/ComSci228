package edu.iastate.cs228.hw3;

import java.util.Random;

public class Tester {
	public static void main(String[] args){
		//This is my tester class for Project 3. Here I will run unofficial tests.
		//No JUnit tests will be in this class. This is just for my personal use.
		PrimeFactorization pf = new PrimeFactorization(1000);
		PrimeFactorization pf1 = new PrimeFactorization(pf);
		System.out.println(pf.toString());
		System.out.println(pf1.toString());
		
		PrimeFactor[] pflist = new PrimeFactor[5];
		for(int i = 0; i< 5; i++){
			pflist[i] = new PrimeFactor(i,i+1);
		}
		PrimeFactorization pf2 = new PrimeFactorization(pflist);
		System.out.println(pf2.toString());
	}
}
