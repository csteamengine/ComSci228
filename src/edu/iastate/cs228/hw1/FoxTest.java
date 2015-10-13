/**
 * @Author Gregory Steenhagen
 */

package edu.iastate.cs228.hw1;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.io.FileNotFoundException;

import org.junit.Test;

public class FoxTest {
	@Test
	public void foxTester() throws FileNotFoundException{
		//public1 layout
		// G  B0 F0 
		// F0 F0 R0 
		// F0 E  G 
		
		//Worlds to be used for testing updateWorld
		World w = new World("public1.txt");
		World wNew = new World(w.grid.length);
		
		
		assertTrue(w.grid[0][2].who() == State.FOX); //who() Should return FOX
		wNew.grid[0][2] = w.grid[0][2].next(wNew);	//updating just one spot
		assertTrue(wNew.grid[0][2].who() == State.EMPTY);	//Makes sure the Fox rules for survival are working correctly
	
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
		assertTrue(w.grid[3][4].who() == State.FOX);	//Checks that the Living at [3][4] is a FOX
		assertNull(wNew.grid[3][4]);	//Checks that the new grid is null originally
		wNew.grid[3][4] = w.grid[3][4].next(wNew);	//Updates the grid 
		assertTrue(wNew.grid[3][4].who() == State.BADGER);	//checks that the new Living is a BADGER
	}
		
}
