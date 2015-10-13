package edu.iastate.cs228.hw2;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.NumberFormatException; 
import java.lang.IllegalArgumentException; 
import java.util.InputMismatchException;


/**
 *  
 * @author Gregory Steenhagen
 *
 */

import java.util.Comparator;
import java.util.Scanner;

/**
 * 
 * This abstract class is extended by SelectionSort, InsertionSort, MergeSort, and QuickSort.
 * It stores the input (later on the sorted) sequence and records the employed sorting algorithm, 
 * the comparison method, and the time spent on sorting. 
 *
 */


public abstract class AbstractSorter
{
	
	protected Point[] points;    // Array of points operated on by a sorting algorithm. 
	                             // The number of points is given by points.length.
	
	protected String algorithm = null; // "selection sort", "insertion sort",  
	                                   // "mergesort", or "quicksort". Initialized by a subclass 
									   // constructor.
	protected boolean sortByAngle;     // true if last sort was done by polar angle and false 
									   // if by x-coordinate 
	
	protected String outputFileName;   // "select.txt", "insert.txt", "merge.txt", or "quick.txt"
	
	protected long sortingTime; 	   // execution time in nanoseconds. 
	 
	protected Comparator<Point> pointComparator;  // comparator which compares polar angle if 
												  // sortByAngle == true and x-coordinate if 
												  // sortByAngle == false 
	
	private Point lowestPoint; 	    // lowest point in the array, or in case of a tie, the
									// leftmost of the lowest points. This point is used 
									// as the reference point for polar angle based comparison.

	
	// Add other protected or private instance variables you may need. 

	/**
	 * This constructor accepts an array of points as input. Copy the points into the array points[]. 
	 * Sets the instance variable lowestPoint.
	 * 
	 * @param  pts  input array of points 
	 * @throws IllegalArgumentException if pts == null or pts.length == 0.
	 */
	protected AbstractSorter(Point[] pts) throws IllegalArgumentException
	{
		if(pts == null || pts.length==0){
			throw new IllegalArgumentException();
		}
		
		points=new Point[pts.length];	//instantiates the private points array with the same length as pts.
		
		//holds the current lowest point.
		Point tempLow = pts[0];
		
		//goes through pts and both copies it and finds the lowest point.
		for(int i=0;i<points.length;i++){
			
			points[i] = new Point(pts[i].getX(),pts[i].getY());	//deep copy of pts
			//checks every index if its lower then tempLow. Y coordinate first then X if necessary.
			if(points[i].getY()<tempLow.getY() || (points[i].getY() == tempLow.getY() && points[i].getX() < tempLow.getX())){
				tempLow=points[i];		
			}
		}
		
		lowestPoint=tempLow;	//Finally sets the lowestPoint
	}

	
	/**
	 * This constructor reads points from a file. Sets the instance variables lowestPoint and 
	 * outputFileName.
	 * 
	 * @param  inputFileName
	 * @throws FileNotFoundException 
	 * @throws InputMismatchException   when the input file contains an odd number of integers
	 */
	protected AbstractSorter(String inputFileName) throws FileNotFoundException, InputMismatchException
	{
		//creates a file from the passed String.
		File file = new File(inputFileName);
		int totalNum=0; //keeps track of array length
		Scanner scan=new Scanner(file);	//creates the scanner with the given filename
		while(scan.hasNext()){
			scan.next();	//counts the total number of elements in the file
			totalNum++;		//used to make array length
		}
		scan.close();		//closes the scanner 
		if(totalNum%2 !=0){		
			throw new InputMismatchException();	//throws input mismatch if the numLength is odd
		}
		points = new Point[totalNum/2];	//sets the array to 1/2 the numLength
		scan = new Scanner(file);	//recreates the same scanner 
		points[0] = new Point(scan.nextInt(),scan.nextInt()); //sets the first point so it can be set as the tempLow
		Point tempLow = points[0];
		
		//scans through file and sets points X and Y coordinates
		//also checks for the lowest point.
		for(int i=1; i<totalNum/2;i++){
			points[i] = new Point(scan.nextInt(), scan.nextInt());
			if(points[i].getY()<tempLow.getY() || (points[i].getY() == tempLow.getY() && points[i].getX() < tempLow.getX()) || tempLow ==null){
				tempLow=points[i];
			}
		}
		
		lowestPoint = tempLow;	//sets lowestPoint equal to tempLow, found earlier.
		scan.close();
	}
	

	/**
	 * Sorts the elements in points[]. 
	 * 
	 *     a) in the non-decreasing order of x-coordinate if order == 1
	 *     b) in the non-decreasing order of polar angle w.r.t. lowestPoint if order == 2 
	 *        (lowestPoint will be at index 0 after sorting)
	 * 
	 * Sets the instance variable sortByAngle based on the value of order. Calls the method 
	 * setComparator() to set the variable pointComparator and use it in sorting.    
	 * Records the sorting time (in nanoseconds) using the System.nanoTime() method. 
	 * (Assign the time to the variable sortingTime.)  
	 * 
	 * @param order  1   by x-coordinate 
	 * 			     2   by polar angle w.r.t lowestPoint 
	 *
	 * @throws IllegalArgumentException if order is less than 1 or greater than 2
	 */
	public abstract void sort(int order) throws IllegalArgumentException; 
	
	
	/**
	 * Outputs performance statistics in the format: 
	 * 
	 * <sorting algorithm> <size>  <time>
	 * 
	 * For instance, 
	 * 
	 * selection sort   1000	  9200867
	 * 
	 * Use the spacing in the sample run in Section 2 of the project description. 
	 */
	public String stats()
	{
		String stats="";
		stats += algorithm;
		stats += "   ";				//simply creates a string with proper spacing
		stats += points.length;
		stats += "   ";
		stats += sortingTime;
		return stats; 
		
	}
	
	
	/**
	 * Write points[] to a string.  When printed, the points will appear in order of increasing
	 * index with every point occupying a separate line.  The x and y coordinates of the point are 
	 * displayed on the same line with exactly one blank space in between. 
	 */
	@Override
	public String toString()
	{
		String pointString="";
		
		//scans through the array and creates a correctly formatted string.
		for(int i=0;i<points.length;i++){
			pointString += points[i].getX() +" " + points[i].getY() + "\n";
		}
		return pointString; 
	}

	
	/**
	 *  
	 * This method, called after sorting, writes point data into a file by outputFileName. It will 
	 * be used for Mathematica plotting to verify the sorting result.  The data format depends on 
	 * sortByAngle.  It is detailed in Section 4.1 of the projection description proj2.pdf. 
	 * @throws IOException 
	 */
	public void writePointsToFile() throws IOException
	{
		String pointString="";
		
		//first checks if sortByAngle is false
		if(!sortByAngle){
			//writes normal points to the file
			//one point per line with one space
			for(int i=0;i<points.length;i++){	
				pointString += points[i].getX() +" " + points[i].getY() + "\n";
			}
		}else{
			
			//writes points to file 
			//in the polar format.
			//points[i] points[0] points[i]
			pointString += lowestPoint.getX() +" "+lowestPoint.getY() +"\n";
			for(int i=1;i<points.length;i++){
				pointString += points[i].getX() + " " + points[i].getY() + " " + lowestPoint.getX() +" "+lowestPoint.getY() + " "
						+ points[i].getX() + " " + points[i].getY() + "\n";
			}

		}
		File file = new File(outputFileName);
		FileWriter fwriter = new FileWriter(file.getAbsoluteFile());
		BufferedWriter bwriter = new BufferedWriter(fwriter);
		bwriter.write(pointString);
		bwriter.close();

		
	}	

	
	/**
	 * Generates a comparator on the fly that compares by polar angle if sortByAngle == true
	 * and by x-coordinate if sortByAngle == false. Set the protected variable pointComparator
	 * to it. Need to create an object of the PolarAngleComparator class and call the compareTo() 
	 * method in the Point class, respectively for the two possible values of sortByAngle.  
	 * 
	 * @param order
	 * @return
	 */
	protected void setComparator(int order) 
	{
		sortByAngle = order == 2;
		//if order ==1 it overrides the normal compare method and implements it as a compareTo method.
		//This allows the points to be compared via the X and possibly Y coordinates.
		if(order ==1){
			pointComparator = new Comparator<Point>() {
				@Override
				public int compare(Point p, Point other) {
					return p.compareTo(other);
				}
			};
		//Otherwise it just creates the PolarAngleComparator with the earlier discovered lowest point as the reference point.
		}else if(order ==2){
			pointComparator = new PolarAngleComparator(lowestPoint);
		}
	}

	
	/**
	 * Swap the two elements indexed at i and j respectively in the array points[]. 
	 * 
	 * @param i
	 * @param j
	 */
	protected void swap(int i, int j)
	{
		Point temp = points[i];
		points[i] = points[j];		//simple swap
		points[j] = temp;
	}	
}
