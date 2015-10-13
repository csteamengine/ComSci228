package edu.iastate.cs228.hw2;

import java.io.FileNotFoundException;
import java.lang.NumberFormatException; 
import java.lang.IllegalArgumentException; 
import java.util.InputMismatchException;


/**
 *  
 * @authorGregory Steenhagen
 *
 */

/**
 * 
 * This class implements the version of the quicksort algorithm presented in the lecture.   
 *
 */

public class QuickSorter extends AbstractSorter
{
	
	// Other private instance variables if you need ... 
		
	/**
	 * The two constructors below invoke their corresponding superclass constructors. They
	 * also set the instance variables algorithm and outputFileName in the superclass.
	 */

	/** 
	 * Constructor accepts an input array of points. 
	 *   
	 * @param pts   input array of integers
	 */
	public QuickSorter(Point[] pts)
	{
		super(pts);
		super.algorithm = "quicksort        ";
		super.outputFileName ="quick.txt";
	}
		

	/**
	 * Constructor reads points from a file. 
	 * 
	 * @param inputFileName  name of the input file
	 * @throws FileNotFoundException 
	 * @throws InputMismatchException 
	 */
	public QuickSorter(String inputFileName) throws InputMismatchException, FileNotFoundException 
	{
		super(inputFileName);
		super.algorithm = "quicksort        ";
		super.outputFileName ="quick.txt";
	}


	/**
	 * Carry out quicksort on the array points[] of the AbstractSorter class.  
	 * 
	 * @param order  1   by x-coordinate 
	 * 			     2   by polar angle 
	 *
	 */
	@Override 
	public void sort(int order)
	{
		if(order >2 || order <1){
			throw new IllegalArgumentException();
		}
		long timer = System.nanoTime();
		setComparator(order);
		quickSortRec(0, points.length-1);
		sortingTime = System.nanoTime() - timer;
	}
	
	
	/**
	 * Operates on the subarray of points[] with indices between first and last. 
	 * 
	 * @param first  starting index of the subarray
	 * @param last   ending index of the subarray
	 */
	private void quickSortRec(int first, int last)
	{
		if (first >= last) return;
	    int p = partition(first, last);
	    quickSortRec(first, p - 1);
	    quickSortRec(p + 1, last);	}
	
	
	/**
	 * Operates on the subarray of points[] with indices between first and last.
	 * 
	 * @param first
	 * @param last
	 * @return
	 */
	private int partition(int first, int last)
	{
		// Use the last element as the pivot.
	    Point pivot = points[last];
	    int index = first - 1;

	    for (int j=first; j<last;j++){
	    	if(pointComparator.compare(points[j],pivot) != 1){
	    		index++;
	    		swap(index,j);
    		    		
	    	}

	    }
	    swap(index+1, last);
	    return index + 1;
	}	
		


	
	// Other private methods in case you need ...
}
