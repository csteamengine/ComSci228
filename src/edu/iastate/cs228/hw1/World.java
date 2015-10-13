/**
 * @Author Gregory Steenhagen
 */

package edu.iastate.cs228.hw1;

import java.io.BufferedWriter;
import java.io.File; 
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner; 
import java.util.Random; 

/**
 * 
 * The world is represented as a square grid of size width X width. 
 *
 */
public class World 
{
	private int width; // grid size: width X width 
	
	//Actual grid
	public Living[][] grid; 
	
	/**
	 *  Default constructor reads from a file 
	 */
	public World(String inputFileName) throws FileNotFoundException
	{		
        // TODO 
		// 
		// Assumption: The input file is in correct format. 
		// 
		// You may create the grid world in the following steps: 
		// 
		// 1) Reads the first line to determine the width of the grid.
		// 
		// 2) Creates a grid object. 
		// 
		// 3) Fills in the grid according to the input file. 
		// 
		// Be sure to close the input file when you are done.
		
		//Age of the animal from the file
		int age;
		
		//Used to parse the next Living from the file
		//Ex: B0
		String thing;
		
		//parsed the first letter out so the type of animal can be found
		String subThing;
		
		//File that is being read
		File file = new File(inputFileName);
		
		//scanner to read the file
		Scanner scan = new Scanner(file);
		
		//scans the first row of the file to find the width of the World
		width =0;
		String[] length = scan.nextLine().trim().split("\\s+");
		  for (int i = 0; i < length.length; i++) {
		    width++;
		  }
		  
		grid = new Living[width][width];
		scan.close();
		scan = new Scanner(file);
		
		//Actual Scanning of the file
		while(scan.hasNext()){
			  for(int i=0;i<width;i++){
				  for(int j=0;j<width;j++){
					  thing =scan.next();
					  if(thing.contains("B")){
						  
						  	//parses out the age, same for Fox and Rabbit
							subThing = thing.charAt(1) + "";
							age = Integer.parseInt(subThing);
							grid[i][j] = new Badger(this,i,j,age);		//Creates the badger Living with the given age
						}else if(thing.contains("F")){
							subThing = thing.charAt(1) + "";
							age = Integer.parseInt(subThing);
							grid[i][j] = new Fox(this,i,j,age);		//Creates the Fox Living with the given age
						}else if(thing.contains("R")){
							subThing = thing.charAt(1) + "";
							age = Integer.parseInt(subThing);
							grid[i][j] = new Rabbit(this,i,j,age);		//Creates the Rabbit Living with the given age
						}else if(thing.contains("G")){
							grid[i][j] = new Grass(this,i,j);		//Creates a new Grass
						}else if(thing.contains("E")){
							grid[i][j] = new Empty(this,i,j);		//Creates a new Empty
						}
				  }

			  }
			  
		  }
		scan.close();		//Closes the Scanner
	}
	
	/**
	 * Constructor that builds a w X w grid without initializing it. 
	 * @param width  the grid 
	 */
	public World(int w)
	{
		width = w;
		grid = new Living[width][width];	//Constructs a new World with the given width
	}
	
	
	public int getWidth()
	{
		return width;		//Returns the width of the grid
	}
	
	/**
	 * Initialize the world by randomly assigning to every square of the grid  
	 * one of BADGER, FOX, RABBIT, GRASS, or EMPTY.  
	 * 
	 * Every animal starts at age 0.
	 */
	public void randomInit()
	{
		int randNum;
		Random generator = new Random(); 
		for(int i = 0; i < width;i++){
			for(int j = 0; j< width;j++){
				randNum = generator.nextInt(5);		//Random Generator for selecting the animal
				
				//Each Living has equal opportunity to get selected
				//They are all given a number between 0 and 5
				//The random generates a number between 0 and 5 and matches it to a Living
				if(randNum ==0){
					grid[i][j] = new Badger(this,i,j,0);
				}else if(randNum == 1){
					grid[i][j] = new Empty(this,i,j);
				}else if(randNum == 2){
					grid[i][j] = new Fox(this,i,j,0);
				}else if(randNum == 3){
					grid[i][j] = new Grass(this,i,j);
				}else if(randNum == 4){
					grid[i][j] = new Rabbit(this,i,j,0);
				}
			}
		}
	}
	
	
	/**
	 * Output the world grid. For each square, output the first letter of the living form
	 * occupying the square. If the living form is an animal, then output the age of the animal 
	 * followed by a blank space; otherwise, output two blanks.  
	 */
	public String toString()
	{
		//Starts an empty string to add to
		String worldPrint = "";
		
		//Goes the every space in the grid and if checks its STATE and then formats it accordingly
		for(int l = 0; l <width;l++){
			for(int k = 0; k<width;k++){
				
				if(grid[l][k].who().equals(State.BADGER)){
					Animal temp = (Animal) grid[l][k];
					worldPrint += "B" + temp.myAge() + " ";
				}else if(grid[l][k].who().equals(State.EMPTY)){
					worldPrint += "E  ";
				}else if(grid[l][k].who().equals(State.FOX)){
					Animal temp = (Animal) grid[l][k];
					worldPrint += "F" + temp.myAge() + " ";
				}else if(grid[l][k].who().equals(State.GRASS)){
					worldPrint += "G  ";
				}else if(grid[l][k].who().equals(State.RABBIT)){
					Animal temp = (Animal) grid[l][k];
					worldPrint += "R" + temp.myAge() + " ";
				}
				
			}
			worldPrint += "\n";
		}
		return worldPrint; 
	}
	

	/**
	 * Write the world grid to an output file.  Also useful for saving a randomly 
	 * generated world for debugging purpose. 
	 * @throws IOException 
	 */
	public void write(String outputFileName) throws IOException
	{
		// TODO 
		// 
		// 1. Open the file. 
		// 
		// 2. Write to the file. The five life forms are represented by characters 
		//    B, E, F, G, R. Leave one blank space in between. Examples are given in
		//    the project description. 
		// 
		// 3. Close the file.
		
		//Simply uses the toString method to create a string with
		//the correct spacing and line breaks
		BufferedWriter fw = new BufferedWriter(new FileWriter(outputFileName));
		String WorldtoFile = this.toString();
		fw.write(WorldtoFile);
		fw.close();	//closes the file
	}			
}
