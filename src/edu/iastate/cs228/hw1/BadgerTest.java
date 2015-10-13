/**
 * @Author Gregory Steenhagen
 */

package edu.iastate.cs228.hw1;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.io.FileNotFoundException;

import org.junit.Test;

public class BadgerTest {
	@Test
	public void badgerTester() throws FileNotFoundException{
		//public1 layout
		// G  B0 F0 
		// F0 F0 R0 
		// F0 E  G 
		
		World w = new World("public1.txt");
		World wNew = new World(w.grid.length);
		assertTrue(w.grid[0][1].who() == State.BADGER); //who() Should return BADGER
		wNew.grid[0][1] = w.grid[0][1].next(wNew);
		assertTrue(wNew.grid[0][1].who() == State.FOX);	//should return Fox after update
		
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
		assertTrue(w.grid[5][3].who() == State.BADGER);	//Checks that the Living at [5][3] is a BADGER
		assertNull(wNew.grid[5][3]);	//Checks that the new grid is null originally
		wNew.grid[5][3] = w.grid[5][3].next(wNew);	//Updates the grid 
		assertTrue(wNew.grid[5][3].who() == State.BADGER);	//checks that the new Living is STILL a BADGER
	}
}
