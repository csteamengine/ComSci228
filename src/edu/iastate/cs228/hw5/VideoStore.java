package edu.iastate.cs228.hw5;

import java.io.FileNotFoundException;
import java.util.Scanner; 

/**
 * 
 * @author 
 *
 */

public class VideoStore 
{
	protected SplayTree<Video> inventory;     // all the videos at the store
	
	// ------------
	// Constructors 
	// ------------
	
    /**
     * Default constructor sets inventory to an empty tree. 
     */
    VideoStore()
    {
    	// TODO 
    }
    
    
	/**
	 * Constructor accepts a video file to create its inventory.  Refer to Section 3.2 of  
	 * the project description for details regarding the format of a video file. 
	 * 
	 * The construtor works in two steps: 
	 * 
	 *     1. Calls the default constructor. 
	 *     2. Has the splay tree inventory call its method addBST() to add videos to the tree.
	 * 
	 * @param videoFile  no format checking on the file
	 * @throws FileNotFoundException
	 */
    VideoStore(String videoFile) throws FileNotFoundException 
    {
    	// TODO 
    }
    
    
   /**
     * Accepts a video file to initialize the splay tree inventory.  To be efficient, 
     * add videos to the inventory by calling the addBST() method, which does not splay. 
     * 
     * Refer to Section 3.2 for the format of video file. 
     * 
     * @param  videoFile  correctly formated if exists
     * @throws FileNotFoundException 
     */
    public void setUpInventory(String videoFile) throws FileNotFoundException
    {
    	
    }
	
    
    // ------------------
    // Inventory Addition
    // ------------------
    
    /**
     * Find a Video object by film title. 
     * 
     * @param film
     * @return
     */
	public Video findVideo(String film) 
	{
		// TODO 
		return null; 
	}


	/**
	 * Updates the splay tree inventory by adding a given number of video copies of the film.  
	 * (Splaying is justified as new videos are more likely to be rented.) 
	 * 
	 * Calls the add() method of SplayTree to add the video object.  If true is returned, the 
	 * film was not on the inventory before, and has been added.  If false is returned, the 
	 * film is already on the inventory. The root of the splay tree must store the corresponding
	 * Video object for the film. Calls findElement() of the SplayTree class to get this Video 
	 * object, which then calls getNumCopies() and addNumCopies() of the Video class to increase
	 * the number of copies of the corresponding film by n 
	 * 
	 * @param film  title of the film
	 * @param n     number of video copies 
	 */
	public void addVideo(String film, int n)
	{
		
	}
	

	/**
	 * Add one video copy of the film. 
	 * 
	 * @param film  title of the film
	 */
	public void addVideo(String film)
	{
		// TODO 
	}
	

	/**
     * Update the splay trees inventory.  
     * 
     * The videoFile format is given in Section 3.2 of the project description. 
     * 
     * @param videoFile  correctly formated if exists 
     * @throws FileNotFoundException
     */
    public void bulkImport(String videoFile) throws FileNotFoundException 
    {
    	
    }

    
    // ----------------------------
    // Video Query, Rental & Return 
    // ----------------------------
    
	/**
	 * Search the splay tree inventory to determine if a video is available. 
	 * 
	 * @param  film
	 * @return true if available
	 */
	public boolean available(String film)
	{
		// TODO 
		return false; 
	}

	
	
	/**
     * Update inventory. 
     * 
     * Search if the film is in inventory by calling findElement(new Video(film, 1)). 
     * 
     * If the film is not in inventory, prints the message "Film <film> is not 
     * in inventory", where <film> shall be replaced with the string that is the value 
     * of the parameter film.  If the film is in inventory with no copy left, prints
     * the message "Film <film> has been rented out".
     * 
     * If there is at least one available copy but n is greater than the number of 
     * such copies, rent all available copies. In this case, no AllCopiesRentedOutException
     * is thrown.  
     * 
     * @param film   
     * @param n 
	 * @throws FilmNotInInventoryException
	 * @throws AllCopiesRentedOutException   if there is zero copy of the film.
	 */
	public void videoRent(String film, int n) throws FilmNotInInventoryException, AllCopiesRentedOutException 
	{
		// TODO 
	}

	
	/**
	 * Update inventory.
	 * 
	 *    1. Calls videoRent() repeatedly for every video listed in the file.  
	 *    2. For each requested video, do the following: 
	 *       a) If it is not in inventory or is rented out, an exception will be 
	 *          thrown from rent().  Based on the exception, prints out the following 
	 *          message: "Film <film> is not in inventory" or "Film <film> 
	 *          has been rented out." In the message, <film> shall be replaced with 
	 *          the name of the video. 
	 *       b) Otherwise, update the video record in the inventory.
	 * 
	 * @param videoFile  correctly formatted if exists
	 * @throws FileNotFoundException
	 * @throws FilmNotInInventoryException
	 * @throws AllCopiesRentedOutException  if there is zero copy of some film in videoFile.
	 */
	public void bulkRent(String videoFile) throws FileNotFoundException, 
										FilmNotInInventoryException, AllCopiesRentedOutException 
	{
		// TODO 
	}

	
	/**
	 * Update inventory.
	 * 
	 * If n exceeds the number of rented video copies, accepts up to that number of rented copies
	 * while ignoring the extra copies. 
	 * 
	 * @param film
	 * @param n
	 */
	public void videoReturn(String film, int n)
	{
		// TODO
	}
	
	
	/**
	 * Update inventory. 
	 * 
	 * Handles excessive returned copies of a film in the same way as videoReturn() does.  
	 * 
	 * @param videoFile
	 * throws FileNotFoundException
	 */
	public void bulkReturn(String videoFile) throws FileNotFoundException 
	{
		// TODO 
	}
		
	

	// ------------------------
	// Methods with No Splaying
	// ------------------------
		
	/**
	 * Performs inorder traveral on the splay tree inventory to list all the videos by film 
	 * title, whether rented or not.  Below is a sample string if printed out: 
	 * 
	 * 
	 * Films in inventory: 
	 * 
	 * A Streetcar Named Desire (1) 
	 * Brokeback Mountain (1) 
	 * Forrest Gump (1)
	 * Psycho (1) 
	 * Singin' in the Rain (2)
	 * Slumdog Millionaire (5) 
	 * Taxi Driver (1) 
	 * The Godfather (1) 
	 * 
	 * 
	 * @return
	 */
	public String inventoryList()
	{
		// TODO 
		return null; 
	}

	
	/**
	 * Calls rentedVideosList() and unrentedVideosList() sequentially.  For the string format, 
	 * see Transaction 5 in the sample simulation in Section 4 of the project description. 
	 *   
	 * @return 
	 */
	public String transactionsSummary()
	{
		// TODO 
		return null; 
	}	
	
	/**
	 * Performs inorder traversal on the splay tree inventory.  Use a splay tree iterator.
	 * 
	 * Below is a sample return string when printed out:
	 * 
	 * 
	 * Rented films: 
	 * 
	 * Brokeback Mountain (1) 
	 * Singin' in the Rain (2)
	 * Slumdog Millionaire (1) 
	 * The Godfather (1) 
	 * 
	 * 
	 * @return
	 */
	private String rentedVideosList()
	{
		// TODO 
		return null; 
	}

	/**
	 * Performs inorder traversal on the splay tree inventory.  Use a splay tree iterator.
	 * Prints only the films that have unrented copies. 
	 * 
	 * Below is a sample return string when printed out:
	 * 
	 * 
	 * Films remaining in inventory:
	 * 
	 * A Streetcar Named Desire (1) 
	 * Forrest Gump (1)
	 * Psycho (1) 
	 * Slumdog Millionaire (4) 
	 * Taxi Driver (1) 
	 * 
	 * 
	 * @return
	 */
	private String unrentedVideosList()
	{
		// TODO 
		return null; 
	}	

	
	/**
	 * Parse the film name from an input line. 
	 * 
	 * @param line
	 * @return
	 */
	public static String parseFilmName(String line) 
	{
		// TODO 
		return null; 
	}
	
	
	/**
	 * Parse the number of copies from an input line. 
	 * 
	 * @param line
	 * @return
	 */
	public static String parseNumCopies(String line) 
	{
		// TODO 
		return null; 
	}
}
