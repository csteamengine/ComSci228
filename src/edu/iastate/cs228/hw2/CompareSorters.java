package edu.iastate.cs228.hw2;

/**
 *  
 * @author Gregory Steenhagen
 *
 */

/**
 * 
 * This class executes four sorting algorithms: selection sort, insertion sort, mergesort, and
 * quicksort, over randomly generated integers as well integers from a file input. It compares the 
 * execution times of these algorithms on the same input. 
 *
 */

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner; 
import java.util.Random; 


public class CompareSorters 
{
	
	/**
	 * Repeatedly take integer sequences either randomly generated or read from files. 
	 * Perform the four sorting algorithms over each sequence of integers, comparing 
	 * points by x-coordinate or by polar angle with respect to the lowest point.  
	 * 
	 * @param args
	 * @throws InputMismatchException 
	 * @throws IOException 
	 **/
	public static void main(String[] args) throws InputMismatchException, IOException 
	{		

		// 
		// Conducts multiple sorting rounds. In each round, performs the following: 
		// 
		//    a) If asked to sort random points, calls generateRandomPoints() to initialize an array 
		//       of random points. 
		//    b) Reassigns to elements in the array sorters[] (declared below) the references to the 
		//       four newly created objects of SelectionSort, InsertionSort, MergeSort and QuickSort. 
		//    c) Based on the input point order, carries out the four sorting algorithms in a for 
		//       loop that iterates over the array sorters[], to sort the randomly generated points
		//       or points from an input file.  
		//    d) Meanwhile, prints out the table of runtime statistics.
		// 
		// A sample scenario is given in Section 2 of the project description. 
		// 	
		AbstractSorter[] sorters = new AbstractSorter[4]; 
		// Within a sorting round, every sorter object write its output to the file 
		// "select.txt", "insert.txt", "merge.txt", or "quick.txt" if it is an object of 
		// SelectionSort, InsertionSort, MergeSort, or QuickSort, respectively. 
		Scanner scan = new Scanner(System.in);
		Random rand = new Random();
		Point[] randomPoints;
		int inputStyle;
		int order;
		int count=1;
		int numPoints;
		
		//prints out the beginning information.
		System.out.println("Comparison of Four Sorting Algorithms\n");
		System.out.println("keys: 1 (random integers)  2 (file input)  3 (exit)\n");
		System.out.println("Order:  1 (by x-coordinate)  2 (by polar angle)\n");
		
		//main while loop.
		while(true){
			System.out.print("Trial "+count + ": ");
			inputStyle=scan.nextInt();
			while(inputStyle!= 1 && inputStyle!=2 && inputStyle!=3){
				System.out.println("Please enter a valid input style.");
				inputStyle=scan.nextInt();
			}
			if(inputStyle==1){
				System.out.print("Enter number of random points: ");
				numPoints=scan.nextInt();
				randomPoints = generateRandomPoints(numPoints, rand);
				sorters[0] = new SelectionSorter(randomPoints);
				sorters[1] = new InsertionSorter(randomPoints);
				sorters[2] = new MergeSorter(randomPoints);		//fills the array for the sorters.
				sorters[3] = new QuickSorter(randomPoints);
				System.out.print("Order used in sorting: ");
				order=scan.nextInt();
				
			}else if(inputStyle==2){
				numPoints=0;
				System.out.println("Points from a file");
				System.out.print("File Name: ");
				String fileName = scan.next();
				sorters[0] = new SelectionSorter(fileName);
				sorters[1] = new InsertionSorter(fileName);
				sorters[2] = new MergeSorter(fileName);			//fills the array for the sorters
				sorters[3] = new QuickSorter(fileName);		
				System.out.print("Order used in sorting: ");
				order=scan.nextInt();
				
			}else{
				break;
			}
			
			//goes through the sorters array and sorts using each sorter
			//and writes each to their respective files.
			for(int j=0;j<sorters.length;j++){
				sorters[j].sort(order);
				sorters[j].writePointsToFile();
			}
			
			System.out.println();
			System.out.println();	//Just prints out each of the algorithms stats
			System.out.println("algorithm        size     time (ns)");
			System.out.println("------------------------------------");
			for(int i=0;i<sorters.length;i++){
				System.out.println(sorters[i].stats());
			}
			System.out.println("------------------------------------");
			System.out.println();
			System.out.println();
			count++;	//increments the count
			
		}
		
	}
	
	
	/**
	 * This method generates a given number of random points to initialize randomPoints[].
	 * The coordinates of these points are pseudo-random numbers within the range 
	 * [-50,50] � [-50,50]. Please refer to Section 3 on how such points can be generated.
	 * 
	 * Ought to be private. Made public for testing. 
	 * 
	 * @param numPts  	number of points
	 * @param rand      Random object to allow seeding of the random number generator
	 * @throws IllegalArgumentException if numPts < 1
	 */
	public static Point[] generateRandomPoints(int numPts, Random rand) throws IllegalArgumentException
	{ 
		//fills the array with random points to be copied into "points" the array
		Point[] temp = new Point[numPts];
		Point tempPoint;
		for(int i=0;i<numPts;i++){
			tempPoint = new Point(rand.nextInt(101)-50,rand.nextInt(101)-50);
			temp[i]= tempPoint;
		}
		
		return temp; 
	}
}
