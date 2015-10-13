/**
 * @Author Gregory Steenhagen
 */

package edu.iastate.cs228.hw1;


import java.io.FileNotFoundException;
import java.util.Scanner; 

/**
 * 
 * The PredatorPrey class performs the predator-prey simulation over a grid world 
 * with squares occupied by badgers, foxes, rabbits, grass, or none. 
 *
 */
public class PredatorPrey 
{
	/**
	 * Update the new world from the old world in one cycle. 
	 * @param wOld  old world
	 * @param wNew  new world 
	 */
	public static void updateWorld(World wOld, World wNew)
	{
		// TODO 
		// 
		// For every life form (i.e., a Living object) in the grid wOld, generate  
		// a Living object in the grid wNew at the corresponding location such that 
		// the former life form changes into the latter life form. 
		// 
		// Employ the method next() of the Living class. 
		
		//width of the old world to be used to make the new world
		int width = wOld.getWidth();
		
		for(int m=0; m<width;m++){			//scans through old world and assigns the next Living to the current spot.
			for(int n=0;n< width;n++){
				wNew.grid[m][n] = wOld.grid[m][n].next(wNew);		//calls the next() method in each class(badger,empty, etc...)
			}
		}
	}
	
	/**
	 * Repeatedly generates worlds either randomly or from reading files. 
	 * Over each world, carries out an input number of cycles of evolution. 
	 * @param args
	 * @throws FileNotFoundException
	 */
	public static void main(String[] args) throws FileNotFoundException
	{	
		// TODO 
		// 
		// Generate predator-prey simulations repeatedly like shown in the 
		// sample run in the project description. 
		// 
		// 1. Enter 1 to generate a random world, 2 to read a world from an input
		//    file, and 3 to end the simulation. (An input file always ends with 
		//    the suffix .txt.)
		// 
		// 2. Print out standard messages as given in the project description. 
		// 
		// 3. For convenience, you may define two worlds even and odd as below. 
		//    In an even numbered cycle (starting at zero), generate the world 
		//    odd from the world even; in an odd numbered cycle, generate even 
		//    from odd. 
		
		//World even = new World(1);   // the world after an even number of cycles 
		//World odd;                   // the world after an odd number of cycles
		
		// 4. Print out initial and final worlds only.  No intermediate worlds should
		//    appear in the standard output.  (When debugging your program, you can 
		//    print intermediate worlds.)
		// 
		// 5. You may save some randomly generated worlds as your own test cases. 
		// 
		// 6. It is not necessary to handle file input & output exceptions for this 
		//    project. Assume data in an input file to be correctly formated. 
		
		//used to initialize the while loop
		int worldGen = 0;
		
		//used to create a world with 
		int gridWidth;
		
		//Number of cycles the user wants to perform
		int numCycles;
		
		//Number of times the simulation has been run
		//Ex: Trial 1: ...Trial 2:
		int trialNum =1;
		
		//Files Name as a string
		String file;
		
		//World to be used on odd cycle numbers.
		World odd;
		
		//World to be used on even cycle numbers.
		World even;
		
		//Scanner to take user input
		Scanner scan = new Scanner(System.in);
		
		//Beginning of simulation
		System.out.println("The Predator-Prey Simulator \nKeys: 1 (Random World)  2 (Input File)  3 (Exit)");
		System.out.println();
		
		//Main While loop to continue performing until the user enters a number other than 3.
		while(worldGen <3){
			
			//User chooses random, file, or quit
			System.out.print("Trial " + trialNum + ": ");
			worldGen = scan.nextInt();
			
			//Selects which kind of world the user wants to create
			if(worldGen ==1){
				trialNum++;
				System.out.println("Random World");
				System.out.print("Enter grid width: ");
				
				//This while loop ensures that the input is valid
				//width input must be greater than 0 to break the loop
				while(true){
					gridWidth = scan.nextInt();
			    	if(gridWidth>0){
			    		break;
			    	}
			    }
				
				//Generates the two worlds required to update the world
				odd = new World(gridWidth);
				even = new World(gridWidth);
				even.randomInit();
				
			}else if(worldGen ==2){
				trialNum++;
				System.out.println("World input from a file");
				System.out.print("File name: ");
				file = scan.next();
				odd = new World(file);
				even = new World(file);
				
				//if the user inputs 3, it ends the cycle
			}else{
				break;
			}
			
			System.out.print("Enter the number of cycles: ");
			
			//only breaks the loop when user inputs a valid cycle number
		    while(true){
		    	numCycles =scan.nextInt();
		    	if(numCycles>0){
		    		break;
		    	}
		    }
			System.out.println();
			System.out.println("Initial World:");
			System.out.println();
			System.out.println(even.toString());
			
			//This is the update cycle. Updates the world the number of times the user selected
			for(int i = 0;i<numCycles;i++){
				//if i is odd, the even world will be next so it gets cleared
				//and the new values are put into it.
				if(i%2==1){
					even = new World(odd.grid.length);
					updateWorld(odd,even);
					
				//if the update cycle is even, odd will be next, so it gets cleared and 
				//the new values get added to it.
				}else{
					odd = new World(even.grid.length);
					updateWorld(even,odd);
					
				}
				
			}
			
			System.out.println("Final World:");
			System.out.println();
			
			//if the cycles end on an even, it prints out the even world
			//if the cycles end on an odd, it prints out the odd world
			if(numCycles%2 ==0){
				System.out.println(even.toString());
			}else{
				System.out.println(odd.toString());
			}
			
		}
		scan.close();
	}
}
