package edu.iastate.cs228.hw2;

import java.io.FileNotFoundException;
import java.lang.NumberFormatException; 
import java.lang.IllegalArgumentException; 
import java.util.InputMismatchException;


/**
 *  
 * @author Gregory Steenhagen
 *
 */

/**
 * 
 * This class implements insertion sort.   
 *
 */

public class InsertionSorter extends AbstractSorter 
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
	public InsertionSorter(Point[] pts) 
	{
		//feeds algorithm and filename into super constructor
		super(pts);
		super.algorithm = "insertion sort   ";
		super.outputFileName ="insert.txt";
	}	

	
	/**
	 * Constructor reads points from a file. 
	 * 
	 * @param inputFileName  name of the input file
	 * @throws FileNotFoundException 
	 * @throws InputMismatchException 
	 */
	public InsertionSorter(String inputFileName) throws InputMismatchException, FileNotFoundException 
	{
		//feeds algorithm and filename into super constructor
		super(inputFileName);
		super.algorithm = "insertion sort   ";
		super.outputFileName ="insert.txt";
		}
	
	
	/** 
	 * Perform insertion sort on the array points[] of the parent class AbstractSorter.  
	 * 
	 * @param order  1   by x-coordinate 
	 * 			     2   by polar angle 
	 */
	@Override 
	public void sort(int order)
	{
		//validates the input
		if(order >2 || order <1){
			throw new IllegalArgumentException();
		}
		//sets the correct comparator
		//according to class and order
		setComparator(order);
		//initial value for sorting order.
		long timer1=System.nanoTime();
		//main for loop
		for(int i=1;i<points.length;i++){
			//sets key = points[i]
			Point key = points[i];
			//j is equal to the preceding index #
			int j=i-1;
			//keeps going back a slot until key<points[j]
			while(j>=0 && (pointComparator.compare(points[j],key)==1)){
				points[j+1]=points[j];
				j--;
			}
			points[j+1]=key;
		}
		//calculates final sorting time.
		sortingTime = System.nanoTime() - timer1;
		
	}		
}
