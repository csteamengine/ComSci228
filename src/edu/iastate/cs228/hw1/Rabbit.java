/**
 * @Author Gregory Steenhagen
 */

package edu.iastate.cs228.hw1;

/*
 * A rabbit eats grass and lives no more than three years.
 */
public class Rabbit extends Animal 
{	
	/**
	 * Creates a Rabbit object.
	 * @param w: world  
	 * @param r: row position 
	 * @param c: column position
	 * @param a: age 
	 */
	public Rabbit (World w, int r, int c, int a) 
	{
		world = w;
		row = r;
		column = c;
		age = a;
	}
		
	// Rabbit occupies the square.
	public State who()
	{
		return State.RABBIT; 
	}
	
	/**
	 * A rabbit dies of old age or hunger, or it is eaten if there are as many 
	 * foxes and badgers in the neighborhood.  
	 * @param wNew     world of the next cycle 
	 * @return Living  new life form occupying the same square
	 */
	public Living next(World wNew)
	{
		Living newLiving;
		int population[] = new int[NUM_LIFE_FORMS];
		
		census(population);	//calls census on the 3x3 neighborhood
		
		//Applies the survival rules to the rabbit
		if(age>=RABBIT_MAX_AGE){
			newLiving = new Empty(wNew, row, column);
		}else if(population[GRASS] == 0){
			newLiving = new Empty(wNew,row,column);
		}else if(population[FOX] + population[BADGER] >= population[RABBIT] && population[FOX]> population[BADGER]){
			newLiving = new Fox(wNew, row, column,0);
		}else if(population[BADGER]>population[RABBIT]){
			newLiving = new Badger(wNew, row, column,0);
		}else{
			age +=1;
			newLiving = new Rabbit(wNew, row, column, age);
		}
		return newLiving; 
	}
}
