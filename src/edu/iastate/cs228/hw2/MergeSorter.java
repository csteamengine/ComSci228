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
 * This class implements the mergesort algorithm.   
 *
 */

public class MergeSorter extends AbstractSorter
{
	// Other private instance variables if you need ... 
	
	/**
	 * The two constructors below invoke their corresponding superclass constructors. They
	 * also set the instance variables algorithm and outputFileName in the superclass.
	 */

	/** 
	 * Constructor accepts an input array of points. 
	 * in the array. 
	 *  
	 * @param pts   input array of integers
	 */
	public MergeSorter(Point[] pts) 
	{
		super(pts);
		super.algorithm = "mergesort        ";
		super.outputFileName ="merge.txt";
	}
	
	
	/**
	 * Constructor reads points from a file. 
	 * 
	 * @param inputFileName  name of the input file
	 * @throws FileNotFoundException 
	 * @throws InputMismatchException 
	 */
	public MergeSorter(String inputFileName) throws InputMismatchException, FileNotFoundException 
	{
		super(inputFileName);
		super.algorithm = "mergesort        ";
		super.outputFileName ="merge.txt";
	}


	/**
	 * Perform mergesort on the array points[] of the parent class AbstractSorter. 
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
		mergeSortRec(points);
		sortingTime = System.nanoTime() - timer;
		}

	
	/**
	 * This is a recursive method that carries out mergesort on an array pts[] of points. One 
	 * way is to make copies of the two halves of pts[], recursively call mergeSort on them, 
	 * and merge the two sorted subarrays into pts[].   
	 * 
	 * @param pts	point array 
	 */
	private void mergeSortRec(Point[] pts)
	{
		if(pts.length > 1) {
            // split the array into two pieces, as close to the same
            // size as possible.
            Point[] first = extract(pts, 0, pts.length / 2);
            Point[] last = extract(pts, pts.length / 2, pts.length);

            // sort each of the two halves recursively
            mergeSortRec(first);
            mergeSortRec(last);

            // merge the two sorted halves together
            merge(pts, first, last);
        }
	}
	private static Point[] extract(Point[] pts, int start, int last) {
        Point[] ret = new Point[last - start];
        for(int i = 0; i < ret.length; i++) ret[i] = pts[start + i];
        return ret;
    }
	private void merge(Point[] dest, Point[] a, Point[] b) {
        int i = 0;
        int j = 0;
        while(i < a.length && j < b.length) {
            if(pointComparator.compare(a[i],b[j])!=1) {
                dest[i + j] = a[i];
                ++i;
            } else {
                dest[i + j] = b[j];
                ++j;
            }
        }
        for(; i < a.length; i++) dest[i + j] = a[i];
        for(; j < b.length; j++) dest[i + j] = b[j];
    }

	
	// Other private methods in case you need ...

}
