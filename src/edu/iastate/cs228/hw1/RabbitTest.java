/**
 * @Author Gregory Steenhagen
 */

package edu.iastate.cs228.hw1;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;

import org.junit.Test;

public class RabbitTest {
	@Test
	public void rabbitTester() throws FileNotFoundException{
		//public1 layout
		// G  B0 F0 
		// F0 F0 R0 
		// F0 E  G 
		
		World w = new World("public1.txt");
		World wNew = new World(w.grid.length);
		assertTrue(w.grid[1][2].who() == State.RABBIT); //Rabbit who() should return RABBIT
		wNew.grid[1][2] = w.grid[1][2].next(wNew);
		assertTrue(wNew.grid[1][2].who() == State.FOX); //Only testing the next() operation for Rabbit
		
		//Public2.txt layout
		//F0 E  E  F0 E  E  
		//B0 F0 B0 R0 G  R0 
		//R0 E  R0 B0 B0 G  
		//B0 E  E  R0 F0 E  
		//B0 E  E  G  E  R0 
		//G  G  E  B0 R0 E  
		
		//To check if this works with bigger grids
		w = new World("public2.txt");
		wNew = new World(w.grid.length);
		assertTrue(w.grid[1][3].who() == State.RABBIT);	//Checks that the Living at [1][3] is a rabbit
		assertNull(wNew.grid[1][3]);	//Checks that the new grid is null originally
		wNew.grid[1][3] = w.grid[1][3].next(wNew);	//Updates the grid 
		assertTrue(wNew.grid[1][3].who() == State.BADGER);	//checks that the new Living is a BADGER
		
		
	}
}
