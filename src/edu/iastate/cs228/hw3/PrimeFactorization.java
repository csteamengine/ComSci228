package edu.iastate.cs228.hw3;

/**
 * @author Justin Wheeler
 */

import java.util.ListIterator;
import java.util.NoSuchElementException;

public class PrimeFactorization implements Iterable<PrimeFactor>
{
	/**
	 * Overflow value
	 */
	private static final long OVERFLOW = -1;
	
	/**
	 * The factored integer
	 * It is set to OVERFLOW when the number is greater than 2^63-1
	 * (The largest number representable by the type long)
	 */
	private long value;
	
	/**
	 * Reference to dummy node at the head.
	 */
	private Node head;
	  
	/**
	 * Reference to dummy node at the tail.
	 */
	private Node tail;
	
	/**
	 * Size of the list (Number of distinct prime factors)
	 */
	private int size;

	
    /**
	 *  Default constructor constructs an empty list to represent the number 1.
	 *  
	 *  Combined with the add() method, it can be used to create a prime factorization.  
	 */
	public PrimeFactorization() 
	{	
		//Sets the value and size, creates the dummy nodes, and links head and tail.
		value = 1;
		size = 0;
		head = new Node();
		tail = new Node();
		head.next = tail;
		tail.previous = head;
		
	}

	
	/** 
	 * Obtains the prime factorization of n and creates a doubly linked list to store the result.   
	 * Follows the algorithm in Section 1.2 of the project description. 
	 * 
	 * @param n
	 * @throws IllegalArgumentException if n < 1
	 */
	public PrimeFactorization(long n) throws IllegalArgumentException 
	{	
		//Calls the default constructor and checks if N is less than 1
		this();
		if (n < 1){
			throw new IllegalArgumentException("N cannot be less than 1");
		}

		//Performs the actual factorization on the long.
		for (int i = 2; i * i <= n; i++){
			while ( isPrime(i) && n % i == 0)
			{
				//adds the primeFactor into the PrimeFactorization
				add(i, 1);
				//Divides the number by the factor 
				n /= i;
			}
		}
		//Once the loop is done, it checks the remainder.
		//The remainder will either be equal to 1 or to a prime number.
		if (n != 1){
			add((int) n, 1);
		}
	}
	
	
	/**
	 * Copy constructor.  It is unnecessary to verify the primality of the numbers in the list.
	 * 
	 * @param pf
	 */
	public PrimeFactorization(PrimeFactorization pf)
	{
		//Calls Default constructor.
		this();
		//Creates an iterator
		PrimeFactorizationIterator pfIter = pf.iterator();
		
		//Goes through the given PrimeFactorization, and copies it into this.prime and this.multiplicity
		while (pfIter.hasNext()){
			PrimeFactor pfTemp = pfIter.cursor.pFactor;
			add(pfTemp.prime, pfTemp.multiplicity);
    		pfTemp = pfIter.next();
		}
	}
	
	
	/**
	 * Constructs a factorization from an array of prime factors.  Useful when the number is 
	 * too large to be represented even as a long integer. 
	 * 
	 * @param pflist
	 */
	public PrimeFactorization (PrimeFactor[] pfList)
	{
		//Default constructor
		this();
		//Goes through the array and adds the prime and multiplicity to the PrimeFactorization.
		for (int i = 0; i < pfList.length; i++){
			//copies the array object's prime and multiplicity 
			add(pfList[i].prime, pfList[i].multiplicity);
		}
	}

	
    /**
	 * Test if a number is a prime or not.  Check iteratively from 2 to the largest 
	 * integer not exceeding the square root of n to see if it divides n. 
	 * 
	 *@param n
	 *@return true if n is a prime 
	 * 		  false otherwise 
	 */
    public static boolean isPrime(long n) 
	{
    	//if number is even(non prime) or less than two(also non prime)
    	//Also accounts for two being prime.
    	if (n != 2 && (n%2 == 0 || n < 2)){
    		return false;
    	}
    	
    	//Goes through every odd number besides 1 up to sqrt(n) to see if it is prime.
    	for (int i = 2; i * i <= n; i++){
    		if (n % i == 0){
    			return false;
    		}
    	}
    	return true;
	}   

    
	/**
	 * Multiplies this.value with another number n.  You can do this in one loop: Factor n and 
	 * traverse the doubly linked list in the same time. For details refer to Section 3.1 in the 
	 * project description. Store the prime factorization of the product. Update value and size. 
	 * 
	 * @param n
	 * @throws IllegalArgumentException if n < 1
	 */
	public void multiply(long n) throws IllegalArgumentException 
	{
		//Throws exception if n is less than 1
		if (n < 1){
			throw new IllegalArgumentException("N cannot be less than 1.");
		}
		
		//Scans through all the prime numbers
		//if it divides n then it is added to the PrimeFactorization
		for (int i = 2; i * i < n; i++){
			
			
			while (isPrime(i) && n % i == 0)	//While i is prime and n is divisible by i
			{
				add(i, 1);		//Adds i and the multiplicity 1 to the PrimeFactorization
				n /= i;
			}
		}
		
		if (n != 1){
			add((int) n, 1);		//Adds any remainder other than 1. as long as it is a prime.
		}
	}
	
	
	/**
	 * Multiplies this.value with another number in the factorization form.  Traverse both linked 
	 * lists and store the result in this list object.  See Section 3.1 in the project description 
	 * for details of algorithm. 
	 * 
	 * @param pf 
	 */
	public void multiply(PrimeFactorization pf)
	{
		//Creates the iterator
		PrimeFactorizationIterator pfIter = pf.iterator();
		
		//Adds the two arrays together essentially.
		while (pfIter.hasNext()){
			PrimeFactor pfTemp = pfIter.cursor.pFactor;
			add(pfTemp.prime, pfTemp.multiplicity);
    		pfTemp = pfIter.next();
		}
	}
	
	
	/**
	 * Divides this.value by n.  Make updates to the list, value, size if divisible.  No update 
	 * otherwise. Refer to Section 3.2 in the project description for details. 
	 *  
	 * @param n
	 * @return  true if divisible 
	 *          false if not divisible 
	 * @throws IllegalArgumentException if n <= 0
	 */
	public boolean dividedBy(long n) throws IllegalArgumentException
	{
		//if n is negative or 0, it throws an illegal argument exception.
		if (n <= 0){
			throw new IllegalArgumentException("N cannot be less than or equal to 0");
		}
		
		
		if (value != -1 && value < n){		//returns false if the value is less than n and if the value is not equal to -1(overflow)
			return false;	
		}
		//Creates the primeFactorization of n then divides this by n
		PrimeFactorization pfTemp = new PrimeFactorization(n);
		dividedBy(pfTemp);
		return true; 
	}
	
	
	/**
	 * Division where the divisor is represented in the factorization form.  Update the linked 
	 * list of this object accordingly by removing those nodes housing prime factors that disappear  
	 * after the division.  No update if this number is not divisible by pf. Algorithm details are 
	 * given in Section 3.2. 
	 * 
	 * @param pf
	 * @return	true if divisible by pf
	 * 			false otherwise
	 */
	public boolean dividedBy(PrimeFactorization pf)
	{
		//Returns false if any of the following are true
		if (value != -1 && pf.value != -1 && value < pf.value){
			return false;
		}
		
		//clears the list if the two pf's are equal.
		if (this.equals(pf)){
			clearList();
		}
		
		//Creates the iterator
		PrimeFactorizationIterator pfIter = pf.iterator();
		
		//Goes through the pf
		while (pfIter.hasNext()){
			PrimeFactor pfTemp = pfIter.cursor.pFactor;
			
			//if it doesn't contain the PrimeFactor then it returns false.
			if (!containsPrimeFactor(pfTemp.prime)){
				return false;
			}
			
			pfTemp = pfIter.next();
		}
		
		//Sets the iterator to pf now, reuses the iterator
		pfIter = pf.iterator();
		
		//Removes the node.
		while (pfIter.hasNext()){
			PrimeFactor tempPF =pfIter.next();
			remove(tempPF.prime, tempPF.multiplicity);	
		}
		return true; 
	}

	
	/**
	 * Computes the greatest common divisor (gcd) of this.value and an integer n, and return the 
	 * result as a PrimeFactors object.  Calls the method Euclidean() if this.value != OVERFLOW.
	 *     
	 * It is more efficient to factorize the gcd than n, which is often much greater. 
	 *     
	 * @param n
	 * @return prime factorization of gcd
	 * @throws IllegalArgumentException if n < 1
	 */
	public PrimeFactorization gcd(long n) throws IllegalArgumentException 
	{
		if(n< 1){
			throw new IllegalArgumentException("N cannot be less than 1");
		}
		long gcd =1;
		if(this.value != OVERFLOW){
			gcd = Euclidean(this.value, n);
		}
		PrimeFactorization pfGCD = new PrimeFactorization(gcd);
		return pfGCD; 
	}
	
	
	/**
	  * Implements the Euclidean algorithm to compute the gcd of two natural numbers m and n. 
	  * The algorithm is described in Section 4.1 of the project description. 
	  * 
	  * @param m
	  * @param n
	  * @return gcd of m and n. 
	  * @throws IllegalArgumentException if m < 1 or n < 1
	  */
 	public static long Euclidean(long m, long n) throws IllegalArgumentException
	{
 		if(m < 1 || n < 1){
 			throw new IllegalArgumentException("M and N cannot be less than 1");
 		}
 		long gcd =m;
 		while(m%n!=0){
 			gcd = m%n;
 			m=n;
 			n=gcd;
 		}
 		
 		return gcd; 
	}

 	
	/**
	 * Computes the gcd of this.value and pf.value by traversing the two lists.  No direct 
	 * computation involving value and pf.value. Refer to Section 4.2 in the project description 
	 * on how to proceed.  
	 * 
	 * @param  pf
	 * @return prime factorization of the gcd
	 */
	public PrimeFactorization gcd(PrimeFactorization pf)
	{
		
		PrimeFactorization GCD = new PrimeFactorization();
		PrimeFactorizationIterator thisIter = this.iterator();
		PrimeFactorizationIterator pfIter = pf.iterator();
		while(thisIter.hasNext() && pfIter.hasNext()){
			PrimeFactor pfThis = thisIter.cursor.pFactor;
			PrimeFactor pfOther = pfIter.cursor.pFactor;
			if(pfThis.prime == pfOther.prime){
				if(pfThis.multiplicity < pfOther.multiplicity){
					GCD.add(pfThis.prime,pfThis.multiplicity);
				}else{
					GCD.add(pfOther.prime,pfOther.multiplicity);
				}
				pfThis = thisIter.next();
				pfOther = pfIter.next();
			}else if(pfThis.prime > pfOther.prime){
				pfOther = pfIter.next();
			}else{
				pfThis = thisIter.next();
			}
		}
		if(this.value == -1 || pf.value == -1){
			GCD.updateValue();
		}
		return GCD; 
	}
	
	
	/**
	 * Computes the least common multiple (lcm) of this.value and the number represented by pf. 
	 * The list-based algorithm is given in Section 4.3 in the project description. 
	 * 
	 * @param pf  
	 * @return factored least common multiple  
	 */
	public PrimeFactorization lcm(PrimeFactorization pf)
	{
		PrimeFactorization LCM = new PrimeFactorization();
		PrimeFactorizationIterator thisIter = this.iterator();
		PrimeFactorizationIterator pfIter = pf.iterator();
		while(thisIter.hasNext() && pfIter.hasNext()){
			PrimeFactor pfThis = thisIter.cursor.pFactor;
			PrimeFactor pfOther = pfIter.cursor.pFactor;
			if(pfThis.prime == pfOther.prime){
				if(pfThis.multiplicity < pfOther.multiplicity){
					LCM.add(pfOther.prime, pfOther.multiplicity);
				}else{
					LCM.add(pfThis.prime,pfThis.multiplicity);
				}
				pfThis = thisIter.next();
				pfOther = pfIter.next();
			}else if(pfThis.prime < pfOther.prime ){
				LCM.add(pfThis.prime, pfThis.multiplicity);
				pfThis = thisIter.next();
			}else{
				LCM.add(pfOther.prime, pfOther.multiplicity);
				pfOther = pfIter.next();
			}
		}
		PrimeFactor pfThis = thisIter.cursor.pFactor;
		PrimeFactor pfOther = pfIter.cursor.pFactor;
		while(thisIter.hasNext()){
			LCM.add(pfThis.prime, pfThis.multiplicity);
			thisIter.next();
		}
		while(pfIter.hasNext()){
			LCM.add(pfOther.prime, pfOther.multiplicity);
			pfIter.next();
		}
		if(this.value == -1 && pf.value== -1){
			LCM.value=OVERFLOW;
		}else{
			LCM.updateValue();
		}
		return LCM; 
	}

	
	/**
	 * Computes the least common multiple of this.value and an integer n. Construct a PrimeFactors 
	 * object using n and then call the lcm() method above.  Calls the first lcm() method. 
	 * 
	 * @param n
	 * @return factored least common multiple 
	 * @throws IllegalArgumentException if n < 1
	 */
	public PrimeFactorization lcm(long n) throws IllegalArgumentException 
	{
		PrimeFactorization pfN = new PrimeFactorization(n);
		PrimeFactorization LCM = this.lcm(pfN);
		return LCM; 
	}

	
	/**
	 * Traverses the list to determine if p is a prime factor. 
	 * 
	 * Precondition: p is a prime. 
	 * 
	 * @param p  
	 * @return true if p is a prime factor of the number represented by this linked list
	 *         false otherwise 
	 * @throws IllegalArgumentException if p is not a prime
	 */
	public boolean containsPrimeFactor(int p) throws IllegalArgumentException
	{
		//Creates the iterator
		PrimeFactorizationIterator pfIter = iterator();
		
		//Main loop. 
		while (pfIter.hasNext()){
			PrimeFactor pfTemp = pfIter.cursor.pFactor;
			
			//Checks if the pf contains the given prime Factor p
			if (pfTemp.prime == p){
    			return true;
    		}else if (pfTemp.prime > p){		//If it passes p then it returns false because it doesn't contain p.
    			return false;
    		}
			//advances the cursor
    		pfTemp = pfIter.next();
		}
		return false;
	}
	
	/*
	 * // TODO
	 * Make next two private
	 */
	
	
	/**
	 * Adds a prime factor p of multiplicity m.  Search for p in the linked list.  If p is found at 
	 * a node N, add m to N.multiplicity.  Otherwise, create a new node to store p and m. 
	 *  
	 * Precondition: p is a prime. 
	 * 
	 * @param p  prime 
	 * @param m  multiplicity
	 * @return   true  if m >= 1
	 *           false if m < 1   
	 */
    public boolean add(int p, int m) 
    {
    	//Creates the iterator
		PrimeFactorizationIterator pfIter = iterator();
	
		//Main loop
		while (pfIter.hasNext()){
			PrimeFactor pfTemp = pfIter.cursor.pFactor;
    		
			//increments the multiplicity if p is already present.
			if (pfTemp.prime == p){
    			pfTemp.multiplicity += m;
    			updateValue();
    			return true;
    		}
    		//otherwise, if it passes p, it breaks the loop and then goes on to add the PrimeFactor 
    		if (pfTemp.prime > p){
    			break;
    		}
    		pfTemp = pfIter.next();
    	}
		//This is if p does not already exist.
    	pfIter.add(new PrimeFactor(p, m));
    	return true;
    }
 
    
    /**
     * Removes m from the multiplicity of a prime p on the linked list. <br>
     * It starts by searching for p. <br>
     * Returns false if p is not found, and true if p is found. <br>
     * In the latter case, let N be the node that stores p. <br>
     * If N.multiplicity > m, subtracts m from N.multiplicity. <br>
     * If N.multiplicity <= m, removes the node N.
     * 
     * Precondition: p is a prime.
     * 
     * @param p
     * @param m
     * @return true when p is found. false when p is not found.
     * @throws IllegalArgumentException
     *             if m < 1
     */

    public boolean remove(int p, int m) throws IllegalArgumentException
    {
    	//if m is less than 1, throws IllegalArguementException
    	if (m < 1){
    		throw new IllegalArgumentException("M cannot be negative.");
    	}

    	//if p cannot be found, returns false because the removal failed.
		if (!containsPrimeFactor(p)){
			return false;
		}
		
		//Creates the iterator
		PrimeFactorizationIterator pfIter = iterator();
		
		//Main loop
		while (pfIter.hasNext()){
			PrimeFactor pfTemp = pfIter.next();
			
			if (pfTemp.prime == p){
				if (pfTemp.multiplicity > m){	//if p is found and the multiplicity is greater than m, it is decreased by the given amount.
					pfTemp.multiplicity -= m;
					updateValue();
				}
				else{
					pfIter.remove();		//if the given amount is greater than pfTemp.multiplicity, pfTemp gets removed.
				}
				return true;
			}
		}
		return false;
    }

    
    /**
     * 
     * @return size of the list
     */
	public int size() 
	{
		return size;
	}
	
	
	/**
	 * Writes out the list as a factorization in the form of a product. Represents exponentiation 
	 * by a caret.  For example, if the number is 5814, the returned string would be printed out 
	 * as "2 * 3^2 * 17 * 19". 
	 */
	@Override 
	public String toString()
	{
		if (size > 0){		//if the PF is not empty, it does the following
			PrimeFactorizationIterator pfIter = iterator();		//Iterator
			String tempString = "";			//String to be returned
			
			while (pfIter.hasNext()){
				tempString += pfIter.next().toString();
				
				if (pfIter.hasNext()){
					tempString += " * ";	//Adds the multiplication symbol into the string.
				}
			}
			return tempString; 
		}
		return "1";		//Only for empty strings
	}

	
	/**
	 * @return true if this PrimeFactorization is representing a value that is too large to be within long's range. e.g. 999^999. false otherwise.
	 */
	public boolean valueOverflow()
	{
		return value == OVERFLOW;
	}

	/**
	 * @return value represented by this PrimeFactorization, or -1 if valueOverflow()
	 */
	public long value()
	{
		return value;
	}

	public PrimeFactor[] toArray()
	{
		PrimeFactor[] pfArr = new PrimeFactor[size];
		int i = 0;
		for (PrimeFactor pf : this){
			pfArr[i++] = pf;
		}
		return pfArr;
	}

	@Override
	public PrimeFactorizationIterator iterator()
	{
	    return new PrimeFactorizationIterator();
	}

		
	/**
	 * Doubly-linked node type for this class.
	 */
    private class Node 
    {
	   /**
	    * The prime factor 
	    * Data of the Node.
	    */
		public PrimeFactor pFactor;
		
		/**
		 * neighbor to the current node in the next position
		 */
		public Node next;
		
		/**
		 * Neighbor to the current node in the previous position
		 */
		public Node previous;
		
		
		/**
		 * Default constructor
		 * creates a dummy node with null data and no previous or next
		 */
		public Node()
		{
			pFactor = null;
			next = null;
			previous = null;
		}
	  
		
		/**
		 * Precondition: p is a prime
		 * 
		 * @param p	 prime number 
		 * @param m  multiplicity 
		 * @throws IllegalArgumentException if m < 1 
		 */
		public Node(int p, int m) throws IllegalArgumentException 
		{	
			//Thrown if m < 1
			if (m < 1){
				throw new IllegalArgumentException("M cannot be less than 1");
			}
			
			pFactor = new PrimeFactor(p, m); //Creates a primeFactor object with the given prime and multiplicity
			next = null;
			previous = null;
		}   
		
		
		/**
		 * Constructs a node over a provided PrimeFactor object. 
		 * 
		 * @param pf
		 * @throws IllegalArgumentException
		 */
		public Node(PrimeFactor pf)  
		{
			//Creates the node over the given PF... May need to use the reference, rather than the copy.
			pFactor = new PrimeFactor(pf.prime, pf.multiplicity);
			next = null;
			previous = null;
		}

		
		/**
		 * Printed out in the form: prime + "^" + multiplicity.  For instance "2^3". 
		 */
		@Override
		public String toString() 
		{
			return pFactor.toString();
		}
    }

    
    private class PrimeFactorizationIterator implements ListIterator<PrimeFactor>
    {  	
        // Class invariants: 
        // 1) logical cursor position is always between cursor.previous and cursor
        // 2) after a call to next(), cursor.previous refers to the node just returned 
        // 3) after a call to previous() cursor refers to the node just returned 
        // 4) index is always the logical index of node pointed to by cursor

        private Node cursor;
        private Node pending;    // node pending for removal
        private int index;      
  	  
        
        /**
    	 * Default constructor positions the cursor before the smallest prime factor.
    	 */
    	public PrimeFactorizationIterator()
    	{
    		cursor = head.next;
    		pending = null;
    		index = 0;
    	}

    	
    	@Override
    	public boolean hasNext()
    	{
    		return index < size;	//returns whether the end has been reached or not.
    	}

    	
    	@Override
    	public boolean hasPrevious()	//returns whether there is a previous node or not.
    	{
    		return index > 0;
    	}

    	
    	@Override 
    	public PrimeFactor next() 
    	{
    		//check if you can proceed forward again or not.
    		if (!hasNext()){
    			throw new NoSuchElementException("Can't go forward any more, you are at tail.");
    		}
    		
    		PrimeFactor pfTemp = cursor.pFactor;	//saves the cursors pFactor to be returned
    		pending = cursor;
    		cursor = cursor.next;
    		index++;
    		
    		return pfTemp; 
    	}

 
    	@Override 
    	public PrimeFactor previous() 
    	{
    		//if you are at head, you can't move back any further.
    		if (!hasPrevious()){
    			throw new NoSuchElementException("Can't move back any more, you are at head.");
    		}
    		
    		cursor = cursor.previous;
    		pending = cursor;
    		index--;	
    		
    		return cursor.pFactor; 
    	}

   
    	/**
    	 *  Removes the prime factor returned by next() or previous()
    	 *  
    	 *  @throws IllegalStateException if pending == null 
    	 */
    	@Override
    	public void remove() throws IllegalStateException
    	{
    		//Checks if pending is null, if so, it cannot be removed
    		if (pending == null){
    			throw new IllegalStateException("Pending node is null, Cannot be removed");
    		}
    		
    		//unlinks pending if it is non-null
    		unlink(pending);

            if (cursor == pending){
            	cursor = pending.next;	
            }
            else{
            	index--;
            }
            
            size--;  
            pending = null;
            updateValue();
    	}
 
 
    	/**
    	 * Adds a prime factor at the cursor position.  The cursor is at a wrong position 
    	 * in either of the two situations below: 
    	 * 
    	 *    a) pf.prime < cursor.previous.pFactor.prime if cursor.previous != null. 
    	 *    b) pf.prime > cursor.pFactor.prime if cursor != null. 
    	 * 
    	 * Take into account the possibility that pf.prime == cursor.pFactor.prime. 
    	 * 
    	 * Precondition: pf.prime is a prime. 
    	 * 
    	 * @param pf  
    	 * @throws IllegalArgumentException if the cursor is at a wrong position. 
    	 */
    	@Override
        public void add(PrimeFactor pf) throws IllegalArgumentException 
        {	
    		//Makes sure the cursor is in the right position.
			if ((cursor.previous != head && cursor.previous.pFactor.prime >= pf.prime) || (cursor != tail && cursor.pFactor.prime <= pf.prime)){
				throw new IllegalArgumentException("Cannot add a node in the current cursor position.");
			}
    		
			//Links the cursor and the given pf
			link(cursor, new Node(pf));
			//updates the size, index, pending, and value
            size++;
            index++;
            pending = null;
            updateValue();
        }


    	@Override
		public int nextIndex() 
		{
			return index;
		}


    	@Override
		public int previousIndex() 
		{
			return index - 1;
		}

    	
		@Deprecated
		@Override
		public void set(PrimeFactor pf) 
		{
			throw new UnsupportedOperationException(getClass().getSimpleName() + " does not support set method");
		}
    }
    

    /**
     * Inserts toAdd into the list after current without updating size.
     * 
     * Precondition: current != null, toAdd != null
     */
    private void link(Node current, Node toAdd)
    {
    	//links the toAdd node in front of the current node.
    	//Changes all the references.
    	toAdd.next = current;
    	toAdd.previous = current.previous;
    	current.previous.next = toAdd;
    	current.previous = toAdd;
    }
	 
    
    /**
     * Removes toRemove from the list without updating size.
     */
    private void unlink(Node toRemove)
    {
    	//Unlinks the node in question. 
    	//Connects the nodes on either side of toRemove, together.
        toRemove.previous.next = toRemove.next;
        toRemove.next.previous = toRemove.previous;
    }

    
    /**
	  * Remove all the nodes in the linked list except the two dummy nodes. 
	  * 
	  * Made public for testing purpose.  Ought to be private otherwise. 
	  */
	public void clearList()
	{
		//Links the head and the tail and updates value and size.
		value = 1;
		size = 0;
		tail.previous = head;
		head.next = tail;
	}	
	
	
	/**
	 * Multiply the prime factors (with multiplicities) out to obtain the represented integer.  
	 * Use Math.multiply(). If an exception is throw, assign OVERFLOW to the instance variable value.  
	 * Otherwise, assign the multiplication result to the variable. 
	 * 
	 */
	private void updateValue()
	{
		//Tries to update the value
		try{	
			if (size == 0){
				value = 1;		//Default value for an empty pf
			}
			else{
				PrimeFactorizationIterator pfIter = iterator();	//Creates an iterator 
				long temp = 1;
				
				//Goes through the PrimeFactorization, and multiplies it out to get the value.
				while (pfIter.hasNext()){	
					PrimeFactor pfTemp = pfIter.cursor.pFactor;
					for(int i = 0;i< pfTemp.multiplicity;i++){
						temp = multiplyExact(temp, pfTemp.prime);	//uses the multiplyExact as assigned
					}
		    		pfTemp = pfIter.next(); 	//Advances the cursor
				}
				value = temp;
			}
		} 	
		catch (ArithmeticException e) {		//If the value overflows, value is set to OVERFLOW, which is -1;
			value = OVERFLOW;
		}
	}
	private long multiplyExact(long x, long y) {
	     if (Long.MAX_VALUE / x < y)
	         throw new ArithmeticException();
	     else
	         return x * y;
	 }
}