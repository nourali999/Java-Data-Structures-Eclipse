/**
 * 
 */


import java.util.Iterator;
import java.util.NoSuchElementException;


 
/**
 * The linked list for our hash will only implement the
 * methods in the HashListI interface, a reduced set of
 * methods compared to the linked list from Assignment 1.
 * 
 * @author
 *
 */
public class LinkList<E> implements HashListI<E> {
	
	/* Our Node<E> class will be an inner class.
	 * It has two components. A pointer that points
	 * to our data and a pointer to the next Node.
	 */
	
	@SuppressWarnings("hiding")
	
	class Node<E>{
		E data; 
		Node<E> next;
		public Node(E obj) {  // Our Node<E> class constructor sets data to object and next to null.
			data = obj;       
			next = null;
		}	
		}
	
	/*
	 * Our iterator helper class helps us iterate 
	 * through the elements in our data structures 
	 */
	
     class IteratorHelper implements Iterator<E>{    
		Node<E> index;
		
		public IteratorHelper() {
			index = head;
			}

		public boolean hasNext() {
			return (index!=null);
		}
		
		public E next() {
			if(!hasNext())
				throw new NoSuchElementException();
			E val = index.data;
			index=index.next;
			return val;
			}	
		}

		/* For our Linked List, we will
		 * need a head and a tail pointer. We 
		 * will also be keeping track of our node elements
		 * with a currentSize counter.
		 */
	
		private Node<E> head, tail;
		private int currentSize;

	

		public LinkList() {  // Our class constructor sets head and tail to null and currentSize to 0.
			head=null;
			tail=null;
			currentSize=0;
		}
		
	
	
		/*
		 *Adds an Element to the LinkedList
		 */
	@Override
	public void add(E obj) {
		Node<E> newNode = new Node<E>(obj);
		if(head==null) {
		head=tail=newNode;
		currentSize++;
		return;
	}
		newNode.next=head;
		head=newNode;
		currentSize++;
		
	}
	/*
	 * Removes  an Element anywhere from the list.
	 * So long as the Element exists in out list.
	 */
	@SuppressWarnings("unchecked")
	@Override
	public E remove(E obj) {
		Node<E> current=head,previous=null;
		while(current!=null) {
			if(((Comparable<E>)obj).compareTo(current.data)==0) {
				if(current==head) return removeFirst();
				if(current==tail) return removeLast();
				currentSize--;
				previous.next=current.next;
				return current.data;
				
			}
			previous=current;
			current=current.next;
		}
			return null;
		}
	

	@Override
	public void makeEmpty() {      // makes the list empty by setting head and tail to null and currentSize to 0.
		head=tail=null;
		currentSize=0;
		
	}

	@Override
	public boolean isEmpty() {     // return true if head is null
		return head==null;
	}

	@Override
	public int size() {          // returns the number of elements in the list
		return currentSize;
	}

	
	@SuppressWarnings("unchecked")
	@Override
	public boolean contains(E obj) {// checks if the element is part of the list.
		Node<E> current = head;
		while(current!=null) {
		if(((Comparable<E>) current.data).compareTo(obj)==0) {
		return true;
		}
		current=current.next;
		}
		return false;
		}
	

	@Override
	public Iterator<E> iterator() {
		return new IteratorHelper();
	}
	// Helps our removes method
	private E removeFirst() {	//Removes the first element in the list
		if(head==null) return null;
		E temp = head.data;
		if(head==tail) {
		head=tail=null;
		currentSize--;
		return temp;
		}
		head=head.next;
		currentSize--;
		return temp;
	}
	//Helps our remove method
	private E removeLast() {      //Removes the last element in the list
		if(head==null) return null;
		Node<E> current = head;
		if(head==tail) { 
		head=tail=null;
		currentSize--;
		return current.data;
		}
		Node<E> previous = null;
		while(current.next!=null) {
		previous=current;
		current=current.next;
		}
		E temp = current.data;
		previous.next=null;
		tail=previous;
		currentSize--;
		return temp;
		}
	
	

}
