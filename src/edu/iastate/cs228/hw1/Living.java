/**
 * @Author Gregory Steenhagen
 */

package edu.iastate.cs228.hw1;

/**
 * 
 * Living refers to the life form occupying a square in a world grid. It is a 
 * superclass of Empty, Grass, and Animal, the latter of which is in turn a superclass
 * of Badger, Fox, and Rabbit. Living has two abstract methods awaiting implementation. 
 *
 */
public abstract class Living 
{
	protected World world; // the world in which the life form resides
	protected int row;     // location of the square on which 
	protected int column;  // the life form resides
	
	// constants to be used as indices. 
	protected static final int BADGER = 0; 
	protected static final int EMPTY = 1; 
	protected static final int FOX = 2; 
	protected static final int GRASS = 3; 
	protected static final int RABBIT = 4; 
	
	public static final int NUM_LIFE_FORMS = 5; 
	
	// life expectancies 
	public static final int BADGER_MAX_AGE = 4; 
	public static final int FOX_MAX_AGE = 6; 
	public static final int RABBIT_MAX_AGE = 3; 
	
	
	/**
	 * Censuses all life forms in the 3 X 3 neighborhood in a world. 
	 * @param population  counts of all life forms
	 */
	protected void census(int population[])
	{		
		int m;
		int n;
		int l;
		int k;
		if(row == 0){
			if(column == 0){
				m=row;
				n=column;		//index 0,0 must scan only down and right
				l=row+1;
				k=column+1;
			}else if(column != world.grid.length-1){
				m=row;
				n=column -1;  //anywhere on top row except corners
				l=row+1;			//scan left to right and down
				k=column+1;
			}else{			//column == world.grid.length
				m=row;
				n=column-1;		//index 0,length-1 must scan only left and down
				l =row+1;
				k=column;
			}
			
		}else if(row == world.grid.length-1){
			if(column == 0){
				m=row-1;
				n=column;		//index length,0 must scan up and right
				l=row;
				k=column+1;
			}else if(column != world.grid.length-1){
				m=row-1;
				n=column -1;		//somewhere on bottom row
				l=row;				//scan left to right and up
				k=column+1;
			}else{			//column == world.grid.length
				m=row-1;
				n=column-1;		// indext length,length must scan up and left
				l =row;
				k=column;
			}
		}else if(column == 0){
			m=row-1;
			n=column;		//left column not in corner
			l =row+1;		//must scan up,down, and right
			k=column+1;
		}else if(column == world.grid.length-1){
			m=row-1;
			n=column-1;
			l =row+1;		//far right column,
			k=column;		//must scan left,up, and down
		}else{
			m=row-1;
			n=column-1;		//any non-border, scan entire 3x3
			l=row+1;
			k=column+1;
		}
		for(int p = m; p <=l;p++){					//These lines just scan through the world
			for(int q = n; q <=k;q++){
				if(world.grid[p][q].who() == State.BADGER){			//If Badger, adds one to the 0th index of population.
					population[BADGER] +=1;
				}else if(world.grid[p][q].who() == State.EMPTY){		//Same for other types.
					population[EMPTY] +=1;
				}else if(world.grid[p][q].who() == State.FOX){
					population[FOX] +=1;
				}else if(world.grid[p][q].who() == State.GRASS){
					population[GRASS] +=1;
				}else if(world.grid[p][q].who() == State.RABBIT){
					population[RABBIT] +=1;
				}
			}
			
		}
		
	}

	/**
	 * Gets the identity of the life form on the square.
	 * @return State
	 */
	public abstract State who();
	// To be implemented in each class of Badger, Empty, Fox, Grass, and Rabbit. 
	// 
	// There are five states given in State.java. Include the prefix State in   
	// the return value, e.g., return State.Fox instead of Fox.  
	
	/**
	 * Determines the life form on the square in the next cycle.
	 * @param  wNew  world of the next cycle
	 * @return Living 
	 */
	public abstract Living next(World wNew); 
	// To be implemented in the classes Badger, Empty, Fox, Grass, and Rabbit. 
	// 
	// For each class (life form), carry out the following: 
	// 
	// 1. Obtains counts of life forms in the 3X3 neighborhood of the class object. 

	// 2. Applies the survival rules for the life form to determine the life form  
	//    (on the same square) in the next cycle.  These rules are given in the  
	//    project description. 
	// 
	// 3. Generate this new life form at the same location in the world wNew.      

}
