package edu.iastate.cs228.hw5;

import java.util.AbstractSet;
import java.util.Iterator;


/**
 * 
 * @author 
 *
 */


/**
 * 
 * This class implements a splay tree.  Add any helper methods or implementation details 
 * you'd like to include.
 *
 */


public class SplayTree<E extends Comparable<? super E>> extends AbstractSet<E>
{
	protected Node root; 
	protected int size; 

	private class Node 
	{
		public E data;
		public Node left;
		public Node parent;
		public Node right;

		public Node(E data) {
			this.data = data;
		}

		@Override
		public Node clone() {
			return new Node(data);
		}	
	}

	
	/**
	 * Default constructor constructs an empty tree. 
	 */
	public SplayTree() 
	{
		size = 0;
	}
	
	
	/**
	 * Needs to call addBST() later on to complete tree construction. 
	 */
	public SplayTree(E data) 
	{
		// TODO 
	}

	
	/**
	 * Copies over an existing splay tree. The entire tree structure must be copied.  
	 * No splaying. 
	 * 
	 * @param tree
	 */
	public SplayTree(SplayTree tree)
	{
		// TODO 
	}

	
	/**
	 * This function is here for grading purpose. It is not a good programming practice.
	 * This method is fully implemented and should not be modified.
	 * 
	 * @return root of the splay tree
	 */
	public E getRoot()
	{
		// TODO 
		return null; 
	}
	
	
	@Override 
	public int size()
	{
		// TODO
		return 0; 
	}
	
	
	/**
	 * Clear the splay tree. 
	 */
	@Override
	public void clear() 
	{
		// TODO 
	}
	
	
	// ----------
	// BST method
	// ----------
	
	/**
	 * Adds an element to the tree without splaying.  The method carries out a binary search tree
	 * addition.  It is used for initializing a splay tree. 
	 * 
	 * @param data
	 * @return true  if addition takes place  
	 *         false otherwise 
	 */
	public boolean addBST(E data)
	{
		// TODO 
		return false; 
	}
	
	
	// ------------------
	// Splay tree methods 
	// ------------------
	
	/**
	 * Inserts an element into the splay tree. In case the element was not contained, this  
	 * creates a new node and splays the tree at the new node. If the element exists in the 
	 * tree already, it splays at the node containing the element. 
	 * 
	 * @param  data  element to be inserted
	 * @return true  if addition takes place 
	 *         false otherwise (i.e., data is in the tree already)
	 */
	@Override 
	public boolean add(E data)
	{
		// TODO 
		return false; 
	}
	
	
	/**
	 * Determines whether the tree contains an element.  Splays at the node that stores the 
	 * element.  If the element is not found, splays at the last node on the search path.
	 * 
	 * @param  data  element to be determined whether to exist in the tree
	 * @return true  if the element is contained in the tree 
	 *         false otherwise
	 */
	public boolean contains(E data)
	{
		// TODO 
		return false; 
	}
	
	
	/**
	 * Splays at a node containing data.  Exists for coding convenience, code readability, and
	 * testing purpose.
	 * 
	 * @param data
	 */
	public void splay(E data) 
	{
		contains(data);
	}

	
	/**
	 * Removes the node that stores an element.  Splays at its parent node after removal
	 * (No splay if the removed node was the root.) If the node was not found, the last node 
	 * encountered on the search path is splayed to the root.
	 * 
	 * @param  data  element to be removed from the tree
	 * @return true  if the object is removed 
	 *         false if it was not contained in the tree 
	 */
	public boolean remove(E data)
	{
		// TODO 
		return false; 
	}


	/**
	 * This method finds an element stored in the splay tree that is equal to data as decided 
	 * by the compareTo() method of the class E.  This is useful for retrieving the value of 
	 * a pair <key, value> stored at some node knowing the key, via a call with a pair 
	 * <key, ?> where ? can be any object of E.   
	 * 
	 * Splays at the node containing the element or the last node on the search path. 
	 * 
	 * @param  data
	 * @return element such that element.compareTo(data) == 0
	 */
	public E findElement(E data) 
	{
		// TODO 
		return null; 
	}

	
	/**
	 * Finds the node that stores an element. It is called by several methods including contains(), 
	 * add(), remove(), and findElement(). 
	 * 
	 * No splay at the found node. 
	 *
	 * @param  data  element to be searched for 
	 * @return node  if found or the last node on the search path otherwise
	 *         null  if size == 0. 
	 */
	protected Node findEntry(E data)
	{
		// TODO 
		return null; 
	}
	
	
	/** 
	 * Join the two subtrees T1 and T2 rooted at root1 and root2 into one.  It is 
	 * called by remove(). 
	 * 
	 * Precondition: All elements in T1 are less than those in T2. 
	 * 
	 * Access the largest element in T1, and splay at the node to make it the root of T1.  
	 * Make T2 the right subtree of T1.  The method is called by remove(). 
	 * 
	 * @param root1  root of the subtree T1 
	 * @param root2  root of the subtree T2 
	 * @return the root of the joined subtree
	 */
	protected Node join(Node root1, Node root2)
	{
		// TODO
		return null; 
	}

	
	/**
	 * Splay at the current node.  This consists of a sequence of zig, zigZig, or zigZag 
	 * operations until the current node is moved to the root of the tree.
	 * 
	 * @param current  node to splay
	 */
	protected void splay(Node current)
	{
		// TODO
	}
	

	/**
	 * This method performs the zig operation on a node. Calls leftRotate() 
	 * or rightRotate().
	 * 
	 * @param current  node to perform the zig operation on
	 */
	protected void zig(Node current)
    {
		// TODO
	}

	
	/**
	 * This method performs the zig-zig operation on a node. Calls leftRotate() 
	 * or rightRotate().
	 * 
	 * @param current  node to perform the zig-zig operation on
	 */
	protected void zigZig(Node current)
	{
		// TODO
	}

	
    /**
	 * This method performs the zig-zag operation on a node. Calls leftRotate() 
	 * or rightRotate() or both.
	 * 
	 * @param current  node to perform the zig-zag operation on
	 */
	protected void zigZag(Node current)
	{
		// TODO
	}	
	
	
	/**
	 * Carries out a left rotation at a node such that after the rotation 
	 * its former parent becomes its left child. 
	 * 
	 * @param current
	 */
	private void leftRotate(Node current)
	{
		
	}

	
	/**
	 * Carries out a right rotation at a node such that after the rotation 
	 * its former parent becomes its right child. 
	 * 
	 * @param current
	 */
	private void rightRotate(Node current)
	{
		
	}	
	
	
	@Override
	public Iterator<E> iterator()
	{
	    return new SplayTreeIterator();
	}

	
	/**
	 * Write the splay tree according to the format specified in Section 2.2 of the project 
	 * description.
	 * 	
	 * Calls toStringRec(). 
	 *
	 */
	@Override 
	public String toString()
	{
		// TODO 
		return null; 
	}

	
	private String toStringRec(Node n, int depth)
	{
		// TODO 
		return null; 
	}
	
	
	/**
	   *
	   * Iterator implementation for this splay tree.  The elements are returned in 
	   * ascending order according to their natural ordering.  All iterator methods 
	   * are exactly the same as those for a binary search tree --- no splaying at 
	   * any node as the cursor moves. 
	   *
	   */
	private class SplayTreeIterator implements Iterator<E>
	{
		Node cursor;
		Node pending; 

	    public SplayTreeIterator()
	    {
	    	// TODO
	    }
	    
	    @Override
	    public boolean hasNext()
	    {
	    	// TODO
	    	return true; 
	    }

	    @Override
	    public E next()
	    {
	    	// TODO
	    	return null; 
	    }

	    @Override
	    public void remove()
	    {
	      // TODO
	    }
	}
}
