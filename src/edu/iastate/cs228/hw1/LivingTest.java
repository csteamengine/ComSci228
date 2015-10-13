/**
 * @Author Gregory Steenhagen
 */

package edu.iastate.cs228.hw1;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;

import org.junit.Test;

public class LivingTest {
	@Test
	public void LivingTester() throws FileNotFoundException{
		
		//int array to store census value.
		int[] population = new int[5];
	
		//comparative arrays
		int[] expected = {1,0,2,1,0};
		int[] expected2 = {1,1,4,2,1};
		int[] expected3 = {0,1,1,1,1};
		
		//World Generated from file public1.txt
		//public1 layout
		// G  B0 F0 
		// F0 F0 R0 
		// F0 E  G 
		World w = new World("public1.txt");
		
		//Test top left corner
		w.grid[0][0].census(population);
		assertArrayEquals(expected, population);
		
		//Test Middle
		population = new int[5];
		w.grid[1][1].census(population);
		assertArrayEquals(expected2,population);
		
		//Test Bottom Right Corner
		population = new int[5];
		w.grid[2][2].census(population);
		assertArrayEquals(expected3,population);
	}
}
