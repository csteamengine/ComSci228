/**
 * @Author Gregory Steenhagen
 */

package edu.iastate.cs228.hw1;

/** 
 * Empty squares are competed by various forms of life.
 */
public class Empty extends Living 
{
	public Empty (World w, int r, int c) 
	{
		world = w;
		row = r;
		column = c;
	}
	
	public State who()
	{
		
		return State.EMPTY; 
	}
	
	/**
	 * An empty square will be occupied by a neighboring Badger, Fox, Rabbit, or Grass, or 
	 * remain empty. 
	 * @param wNew     world of the next life cycle.
	 * @return Living  life form in the next cycle.   
	 */
	public Living next(World wNew)
	{
		Living newLiving;
		int population[] = new int[NUM_LIFE_FORMS];
		census(population);		//Calls census on the Empty 
		
		//Applies the survival rules according to the census 
		if(population[RABBIT]>1){
			newLiving = new Rabbit(wNew, row, column,0);
		}else if(population[FOX]>1){
			newLiving = new Fox(wNew,row,column,0);
		}else if(population[BADGER]>1){
			newLiving = new Badger(wNew, row, column,0);
		}else if(population[GRASS]>=1){
			newLiving = new Grass(wNew, row, column);
		}else{
			newLiving = new Empty(wNew, row, column);
		}
		return newLiving; 
	}
}
