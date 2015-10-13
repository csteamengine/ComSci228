/**
 * @Author Gregory Steenhagen
 */

package edu.iastate.cs228.hw1;

import static org.junit.Assert.*;

import org.junit.Test;

public class PredatorPreyTest {
	
	@Test
	public void updateWorldTest() {
		
		//Created the two worlds required to updateWorld()
		World w = new World(3);
		World w2 = new World(3);
		w.grid[0][0] = new Grass(w,0,0);
		w.grid[0][1] = new Badger(w,0,0,0);
		w.grid[0][2] = new Fox(w,0,0,0);
		w.grid[1][0] = new Fox(w,0,0,0);
		w.grid[1][1] = new Fox(w,0,0,0);
		w.grid[1][2] = new Rabbit(w,0,0,0);
		w.grid[2][0] = new Fox(w,0,0,0);
		w.grid[2][1] = new Empty(w,0,0);
		w.grid[2][2] = new Grass(w,0,0);
		
		//Updates World w
		PredatorPrey.updateWorld(w,w2);
		
		//Created expected world for comparison
		World compare = new World(3);
		compare.grid[0][0] = new Grass(w,0,0);
		compare.grid[0][1] = new Fox(w,0,0,0);
		compare.grid[0][2] = new Empty(w,0,0);
		compare.grid[1][0] = new Empty(w,0,0);
		compare.grid[1][1] = new Empty(w,0,0);
		compare.grid[1][2] = new Fox(w,0,0,0);
		compare.grid[2][0] = new Empty(w,0,0);
		compare.grid[2][1] = new Fox(w,0,0,0);
		compare.grid[2][2] = new Grass(w,0,0);

		//First makes sure w2 matches the expected world
		assertTrue(w2.toString().equals(compare.toString()));
		
		//Makes sure w (original world) doesn't match the result world.
		//In order to make sure both worlds arent being updated with updateWorld()
		assertFalse(w.toString().equals(compare.toString()));
		
	  }
}
