package edu.iastate.cs228.hw2;
import java.io.FileNotFoundException;
import java.lang.NumberFormatException; 
import java.lang.IllegalArgumentException; 
import java.util.InputMismatchException;


/**
 *  
 * @author Gregory Steenhagen
 * 
 *
 */

/**
 * 
 * This class implements selection sort.   
 *
 */

public class SelectionSorter extends AbstractSorter
{
	// Other private instance variables if you need ... 
	
	/**
	 * The two constructors below invoke their corresponding superclass constructors. They
	 * also set the instance variables algorithm and outputFileName in the superclass.
	 */

	/**
	 * Constructor takes an array of points.
	 *  
	 * @param pts  
	 */
	public SelectionSorter(Point[] pts)  
	{
		//feeds file and algorithm name into super constructor 
		super(pts);
		super.algorithm = "selection sort   ";
		super.outputFileName ="select.txt";
	}	

	
	/**
	 * Constructor reads points from a file. 
	 * 
	 * @param inputFileName  name of the input file
	 * @throws FileNotFoundException 
	 * @throws InputMismatchException 
	 */
	public SelectionSorter(String inputFileName) throws InputMismatchException, FileNotFoundException 
	{
		//feeds file and algorithm name into super constructor
		super(inputFileName);
		super.algorithm = "selection sort   ";
		super.outputFileName ="select.txt";
	}
	
	
	/** 
	 * Apply selection sort on the array points[] of the parent class AbstractSorter.  
	 *
	 * @param order  1   by x-coordinate 
	 * 			     2   by polar angle 
	 *
	 */
	@Override 
	public void sort(int order)
	{
		//makes sure input is valid
		if(order >2 || order <1){
			throw new IllegalArgumentException();
		}
		//timer for the method.
		long timer = System.nanoTime();
		//sets the comparator according to the class and order number.
		setComparator(order);
		
		//main for loop of the selection sorter
		for (int i = 0; i < points.length - 1; i++)
        {
			
            int index = i;
            //goes through the entire array and selects the next non-decreasing number.
            for (int j = i + 1; j < points.length; j++)
            	//if points[index] is smaller is sets it equal to j
                if (pointComparator.compare(points[j],points[index])==-1){
                	index=j;
                }
            //creates new point at points[index]
            Point smallerNumber = points[index];
            //points it towards the current i 
            points[index] = points[i];
            //points i towards smaller index
            points[i] = smallerNumber;

        }
		
		//calculates the final sorting time.
		sortingTime = System.nanoTime() -timer;
		
	}	
}
