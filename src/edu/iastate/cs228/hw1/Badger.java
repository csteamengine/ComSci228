/**
 * @Author Gregory Steenhagen
 */

package edu.iastate.cs228.hw1;

/**
 * A badger eats a rabbit and competes against a fox. 
 */
public class Badger extends Animal
{
	/**
	 * Constructor 
	 * @param w: world
	 * @param r: row position 
	 * @param c: column position
	 * @param a: age 
	 */
	public Badger (World w, int r, int c, int a) 
	{
		age = a;
		world = w;
		row = r;
		column = c;
		
	}
	
	/**
	 * A badger occupies the square. 	 
	 */
	public State who()
	{
		return State.BADGER; 		//Returns the life form badger.
	}
	
	/**
	 * A badger dies of old age or hunger, from isolation and attack by a group of foxes. 
	 * @param wNew     world of the next cycle
	 * @return Living  life form occupying the square in the next cycle. 
	 */
	public Living next(World wNew)
	{
		Living newLiving;			//new living object
		int population[] = new int[NUM_LIFE_FORMS];		//counts all life forms in 3x3 
		census(population);								//neighborhood.
		if(age>=BADGER_MAX_AGE){	
			newLiving = new Empty(wNew, row, column);	//Chooses which living to put in the spot
		}else if(population[BADGER] == 1 && population[FOX] > 1){
			newLiving = new Fox(wNew,row,column,0);
		}else if(population[FOX] + population[BADGER] > population[RABBIT]){
			newLiving = new Empty(wNew, row, column);
		}else{
			age +=1;
			newLiving = new Badger(wNew, row, column,age);
		}
		
		return newLiving; 
	}
}
