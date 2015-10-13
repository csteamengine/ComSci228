package edu.iastate.cs228.hw2;

/**
 *  
 * @author Gregory Steenhagen
 * 
 * 
 */

public class Point implements Comparable<Point>
{
	private int x; 
	private int y;
	
	public Point()  // default constructor
	{
		// x and y get default value 0
	}
	
	public Point(int x, int y)
	{
		//sets the x and y values to those that are passed in.
		this.x = x;  
		this.y = y;   
	}
	
	//returns the x coord
	public int getX()   
	{
		return x;
	}
	
	//returns Y coord
	public int getY()
	{
		return y;
	}
	
	//checks if the point is equal according to the X and Y coord.
	@Override
	public boolean equals(Object obj)
	{
		//checks if its null.
		if (obj == null || obj.getClass() != this.getClass())
		{
			return false;
		}
		//returns x and y coordinates compared to this.x and this.y
		Point other = (Point) obj;
		return x == other.x && y == other.y;   
	}

	/**
	 * Compare this point with a second point q in the left-to-right order. 
	 * @param 	q 
	 * @return  -1  if this.x < q.x || (this.x == q.x && this.y < q.y)
	 * 		    0   if this.x == q.x && this.y == q.y 
	 * 			1	otherwise 
	 */
	public int compareTo(Point q)
	{
		//compares the points returning the correct value.
		if(this.x < q.x || (this.x == q.x && this.y<q.y)){
			return -1;
		}else if(this.x == q.x && this.y == q.y){
			return 0;
		}else{
			return 1;
		}
		 
	}
	
	
	/**
	 * Output a point in the standard form (x, y). 
	 */
	@Override
    public String toString() 
	{ 
		//creates a simple string with correct format.
		return "(" + this.getX() +", " + this.getY()+")"; 
	}
}
