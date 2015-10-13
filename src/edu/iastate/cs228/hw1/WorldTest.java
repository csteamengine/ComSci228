/**
 * @Author Gregory Steenhagen
 */

package edu.iastate.cs228.hw1;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;

import org.junit.Test;

public class WorldTest {
	
	@Test
	public void worldTester() throws FileNotFoundException{
		
		World w = new World(3);
		int width;
		
		/**
		 * This Makes sure that the world grid is null before it is 
		 * initialized. This only applies to the World(int) constructor.
		 * Next, it checks that the grid in not null after initialization
		 */
		assertNull(w.grid[0][0]);
		w.randomInit();
		assertNotNull(w.grid[0][0]);
		
		/*
		 * Points the world to a new grid, created by the given file.
		 * Makes sure the World grid is not null after construction
		 */
		w = new World("public1.txt");
		assertNotNull(w.grid[0][0]);
		
		//Makes sure getWidth returns the correct value
		width = w.getWidth();
		assertEquals(width,3);
		
		//Makes sure the toString method returns the correct String
		String str1 = "G  B0 F0 \nF0 F0 R0 \nF0 E  G  \n";
		String wStr = w.toString();
		assertEquals(str1,wStr);
			
	}
}
