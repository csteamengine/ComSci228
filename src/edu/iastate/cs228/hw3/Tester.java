package edu.iastate.cs228.hw3;

import java.util.Random;

public class Tester {
	public static void main(String[] args){
		int[] nums = new int[10];
		Random rand = new Random();
		for(int i=0;i<nums.length;i++){
			nums[i] = rand.nextInt(9)+1;
			for(int j =0;j<i;j++){
				System.out.print(" ");
			}
			System.out.println(nums[i] + " ");
		}
		
	}
}
