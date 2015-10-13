/**
 * @Author Gregory Steenhagen
 */

package edu.iastate.cs228.hw1;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.io.FileNotFoundException;

import org.junit.Test;

public class EmptyTest {
	//public1 layout
	// G  B0 F0 
	// F0 F0 R0 
	// F0 E  G 
	
	@Test
	public void LivingTester() throws FileNotFoundException{
		World w = new World("public1.txt");
		World wNew = new World(w.grid.length);
		
		assertTrue(w.grid[2][1].who() == State.EMPTY); //Rabbit who() should return EMPTY	
		wNew.grid[2][1] = w.grid[2][1].next(wNew);
		assertTrue(wNew.grid[2][1].who() == State.FOX); //EMPTY should have updated to Fox
		
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
		assertTrue(w.grid[3][2].who() == State.EMPTY);	//Checks that the Living at [3][2] is EMPTY	
		assertNull(wNew.grid[3][2]);	//Checks that the new grid is null originally
		wNew.grid[3][2] = w.grid[3][2].next(wNew);	//Updates the grid 
		assertTrue(wNew.grid[3][2].who() == State.RABBIT);	//checks that the new Living is a RABBIT
	}
}
