package edu.iastate.cs228.hw3;

/**
 *  
 * @author Gregory Steenhagen
 *
 */

import java.util.ListIterator;
import java.util.NoSuchElementException;

public class PrimeFactorization implements Iterable<PrimeFactor>
{
	private static final long OVERFLOW = -1;
	private long value; 	// the factored integer 
							// it is set to OVERFLOW when the number is greater than 2^63-1, 
						    // the largest number representable by the type long. 
	
	/**
	 * Reference to dummy node at the head.
	 */
	private Node head;
	  
	/**
	 * Reference to dummy node at the tail.
	 */
	private Node tail;
	
	private int size;     			// number of distinct prime factors


	// ------------
	// Constructors 
	// ------------
	
    /**
	 *  Default constructor constructs an empty list to represent the number 1.
	 *  
	 *  Combined with the add() method, it can be used to create a prime factorization.  
	 */
	public PrimeFactorization() 
	{	 
		head = new Node();
	    tail = new Node();
	    head.next = tail;
	    tail.previous = head;
	    size = 0;
	    value=1;
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
		if(n<1){
			throw new IllegalArgumentException("N is less than 1");
		}
		head=new Node();
		tail=new Node();
		head.next=tail;
		tail.previous=head;
		size=0;
		value = 1000;
		for(int i =2;i*i<n;i++){
			while(isPrime(i) && n%i==0){
				this.add(i, 1);
				n=n/i;
			}
		}
	}
	
	
	/**
	 * Copy constructor.  It is unnecessary to verify the primality of the numbers in the list.
	 * 
	 * @param pf
	 */
	public PrimeFactorization(PrimeFactorization pf)
	{
		head = pf.head;
	    tail = pf.tail;
	    head.next = pf.head.next;
	    tail.previous = pf.tail.previous;
	    size = pf.size;
	    
	}
	
	/**
	 * Constructs a factorization from an array of prime factors.  Useful when the number is 
	 * too large to be represented even as a long integer. 
	 * 
	 * @param pflist
	 */
	public PrimeFactorization (PrimeFactor[] pfList)
	{
		size = pfList.length;
		head = new Node();
	    tail = new Node();
	    head.next =new Node(pfList[0].prime,pfList[0].multiplicity);
	    tail.previous = new Node(pfList[size-1].prime,pfList[size-1].multiplicity);
	    for(int i =0;i<size;i++ ){
	    	this.add(pfList[i].prime,pfList[i].multiplicity);
	    }
	}
	

	// --------------
	// Primality Test
	// --------------
	
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
    	if(n==2){
    		return true;
    	}else if (n%2==0 || n<2){
    		return false;
    	}
        for(int i=2;i*i<=n;i+=1) {
            if(n%i==0){
                return false;
            }
        }
		return true; 
	}   

   
	// ---------------------------
	// Multiplication and Division 
	// ---------------------------
	
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
		if(n<1){
			throw new IllegalArgumentException();
		}
		PrimeFactorization pf = new PrimeFactorization(n);
		this.multiply(pf);
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
		// TODO
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
		if(n<=0){
			throw new IllegalArgumentException();
		}
		if(this.value!=-1 && this.value < n){
			return false;
		}
		if(this.value%n==0){
			PrimeFactorization pf = new PrimeFactorization(n);
			this.dividedBy(pf);
			return true;
		}
		
		return false; 
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
		if(this.value !=-1 && pf.value!=-1 && this.value<pf.value){
			return false;
		}else if(this.value != -1 && pf.value ==-1){
			return false;
		}
		
		return false; 
	}

	
	// -------------------------------------------------
	// Greatest Common Divisor and Least Common Multiple 
	// -------------------------------------------------

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
		// TODO 
		return null; 
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
 		// TODO 
 		return 0; 
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
		// TODO 
		return null; 
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
		// TODO 
		return null; 
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
		// TODO 
		return null; 
	}

	
	// ------------
	// List Methods
	// ------------
	
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
		// TODO 
		return false; 
	}
	
	// The next two methods ought to be private but are made public for testing purpose. 
	
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

    	if(m>=1){
    		PrimeFactorizationIterator PFI = iterator();
    		System.out.println(PFI.cursor == tail);
    		System.out.println(PFI.cursor.previous == head);
    		if(PFI.hasNext()){
    			PrimeFactor pf = PFI.next();
        		while(PFI.hasNext() && pf.prime <= p){
        			pf = PFI.next();
            	}
        		if(pf.prime ==p){
        			pf.multiplicity +=m;
        		}else{
        			PFI.add(new PrimeFactor(p,m));
        		}
    		}else{
    			PFI.add(new PrimeFactor(p,m));
    		}
    		return true;
    	}else{
    		return false;
    	}
    	
    }

	    
    /**
     * Removes a prime p of multiplicity m from the list.  It starts by searching for p in the 
     * linked list.  Return if p is not found.  Otherwise, let N be the node that stores p. If 
     * N.multiplicity > m, substract m from N.multiplicity.  Otherwise, remove the node N. 
     * 
     * Precondition: p is a prime. 
     * 
     * @param p
     * @param m
     * @return true on success
     *         false when p is either not found or found at a node N but m > N.multiplicity
     * @throws IllegalArgumentException if m < 1
     */
    public boolean remove(int p, int m) throws IllegalArgumentException
    {
    	if(m<1){
    		throw new IllegalArgumentException();
    	}
    	PrimeFactorizationIterator PFI = iterator();
		
    	while(PFI.hasNext()){
    		PrimeFactor pf = PFI.next();
			if(pf.prime ==p){
				if(pf.multiplicity >m){
					pf.multiplicity -=m;
					return true;
				}else if(pf.multiplicity ==m){
					PFI.remove();
				}
			}
    		PFI.cursor = PFI.cursor.next;
    		pf = PFI.next();
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
		String output ="";
		PrimeFactorizationIterator iterator = iterator();
		while(iterator.hasNext()){
			output += iterator.next().toString();
		}
		return output; 
	}

	
	// The next three methods are for testing, but you may use them as you like.  

	/**
	 * @return true if this PrimeFactorization is representing a value that is too large to be within long's range. e.g. 999^999. false otherwise.
	 */
	public boolean valueOverflow() {
		return value == OVERFLOW;
	}

	/**
	 * @return value represented by this PrimeFactorization, or -1 if valueOverflow()
	 */
	public long value() {
		return value;
	}

	public PrimeFactor[] toArray() {
		PrimeFactor[] arr = new PrimeFactor[size];
		int i = 0;
		for (PrimeFactor pf : this)
			arr[i++] = pf;
		return arr;
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
		public PrimeFactor pFactor;			// prime factor 
		public Node next;
		public Node previous;
		
		/**
		 * Default constructor for creating a dummy node.
		 */
		public Node()
		{
			pFactor = null;
			next=null;
			previous=null;
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
			if(m<1){
				throw new IllegalArgumentException();
			}
			pFactor = new PrimeFactor(p,m);
		}   
		
		/**
		 * Constructs a node over a provided PrimeFactor object. 
		 * 
		 * @param pf
		 * @throws IllegalArgumentException
		 */
		public Node(PrimeFactor pf)  
		{
			pFactor=new PrimeFactor(pf.prime, pf.multiplicity);
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
  	  
    	// other instance variables ... 
    	  
      
        /**
    	 * Default constructor positions the cursor before the smallest prime factor.
    	 */
    	public PrimeFactorizationIterator()
    	{
    		cursor = head.next;
    		cursor.previous = head;
    		pending = head;
    		index = 0;
    	}

    	@Override
    	public boolean hasNext()
    	{
    		
    		return cursor != tail; 
    	}

    	
    	@Override
    	public boolean hasPrevious()
    	{
    		return cursor != head; 
    	}

 
    	@Override 
    	public PrimeFactor next() 
    	{
    		if (!hasNext()){
    			throw new NoSuchElementException();
    		}
    		index++;
    		pending =cursor;
    		cursor = cursor.next;
    		return pending.pFactor; 
    	}

 
    	@Override 
    	public PrimeFactor previous() 
    	{
    		cursor = cursor.previous;
            index--;
            pending = cursor;
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
    		if(pending == null){
    			throw new IllegalStateException("Pending is null");
    		}
    		unlink(pending);
    		pending = null;
    		size--;
    		index--;
    		
    		
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
    		 if (cursor.previous != head && cursor.previous.pFactor.prime >= pf.prime)
    		     throw new IllegalArgumentException("Illegal add: previous prime >= given prime");
    		 if (cursor != tail && cursor.pFactor.prime <= pf.prime)
    		     throw new IllegalArgumentException("Illegal add: next prime <= given prime");
        	index++;
        	size++;
        	Node temp = new Node(pf);
        	link(cursor, temp);
        	pending = null;
        	
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
        
    	// Other methods you may want to add or override that could possibly facilitate 
    	// other operations, for instance, addition, access to the previous element, etc.
    	// 
    	// ...
    	// 
    }

    
    // --------------
    // Helper methods 
    // -------------- 
    
    /**
     * Inserts toAdd into the list after current without updating size.
     * 
     * Precondition: current != null, toAdd != null
     */
    private void link(Node current, Node toAdd)
    {	
    	toAdd.next =current.next;
    	toAdd.previous =current;
    	current.next = toAdd;
    }
	 
    /**
     * Removes toRemove from the list without updating size.
     */
    private void unlink(Node toRemove)
    {
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
		PrimeFactorizationIterator pf = iterator();
		while(pf.cursor !=tail){
			pf.next();
			pf.remove();
		}
		head.next = tail;
		tail.previous = head;
	}	
	
	/**
	 * Multiply the prime factors (with multiplicities) out to obtain the represented integer.  
	 * Use Math.multiply(). If an exception is throw, assign OVERFLOW to the instance variable value.  
	 * Otherwise, assign the multiplication result to the variable. 
	 * 
	 */
	private void updateValue()
	{
		try {		
			// TODO		
		} 
			
		catch (ArithmeticException e) 
		{
			value = OVERFLOW;
		}
		
	}
}
