/**
 * @Author Gregory Steenhagen
 */

package edu.iastate.cs228.hw1;

import static org.junit.Assert.*;

import org.junit.Test;

public class AnimalTest {
	@Test
	public void myAgeTest(){
		World w = new World(3);
		w.grid[0][0] = new Badger(w,0,0,0);  //Badger with age 3
		w.grid[0][1] = new Fox(w,0,1,0);
		w.grid[0][2] = new Rabbit(w,0,2,3);  //Fox with age 3
		w.grid[1][0] = new Empty(w,1,0);
		w.grid[1][1] = new Grass(w,1,1);
		w.grid[1][2] = new Empty(w,1,2);
		w.grid[2][0] = new Badger(w,2,0,0);
		w.grid[2][1] = new Fox(w,2,1,0);
		w.grid[2][2] = new Rabbit(w,2,2,0);
		
		Animal an = (Animal) w.grid[0][0];	//Downcasting Badger to ensure its an animal
		assertTrue(an.myAge() == 0);		//Badger's age should be 0
		an = (Animal) w.grid[0][2];			//now refers to the Fox
		assertFalse(an.myAge() ==0);		//Fox's Age should NOT be 0
		assertTrue(an.myAge() == 3);		//Fox's Age SHOULD be 3
		
	}
}
