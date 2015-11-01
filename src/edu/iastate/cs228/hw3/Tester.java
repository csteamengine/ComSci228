package edu.iastate.cs228.hw3;

import java.util.Random;

public class Tester {
	public static void main(String[] args){
		//This is my tester class for Project 3. Here I will run unofficial tests.
		//No JUnit tests will be in this class. This is just for my personal use.
		PrimeFactorization pf= new PrimeFactorization();
		int n = 1000;
		for(int i =2;i*i<n;i++){
			while(pf.isPrime(i) && n%i==0){
				System.out.println(n + "/" + i);
				n=n/i;
				System.out.println(n);
			}
		}
	}
}
