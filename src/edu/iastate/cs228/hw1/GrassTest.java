/**
 * @Author Gregory Steenhagen
 */

package edu.iastate.cs228.hw1;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.io.FileNotFoundException;

import org.junit.Test;

public class GrassTest {
	@Test
	public void grassTester() throws FileNotFoundException{
		//public1 layout
		// G  B0 F0 
		// F0 F0 R0 
		// F0 E  G 
		
		World w = new World("public1.txt");
		World wNew = new World(w.grid.length);
		
		
		assertTrue(w.grid[0][0].who() == State.GRASS); //who() Should return BADGER
		wNew.grid[0][0] = w.grid[0][0].next(wNew);	//updates the world 
		assertTrue(wNew.grid[0][0].who() == State.GRASS);	//Who should now return Grass
		wNew.grid[2][2] = w.grid[2][2].next(wNew);
		assertTrue(wNew.grid[2][2].who() == State.GRASS);  //Who should now return Grass again
	
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
		assertTrue(w.grid[1][4].who() == State.GRASS);	//Checks that the Living at [1][4] is a GRASS
		assertNull(wNew.grid[1][4]);	//Checks that the new grid is null originally
		wNew.grid[1][4] = w.grid[1][4].next(wNew);	//Updates the grid 
		assertTrue(wNew.grid[1][4].who() == State.GRASS);	//checks that the new Living is still a GRASS
	}
}
