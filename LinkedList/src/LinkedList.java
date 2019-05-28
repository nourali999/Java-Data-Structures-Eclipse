


// the only import statement you are allowed.
import java.util.Iterator;
/* Got permission from Doctor Rob to import this Utility
 * Simply throws the exception
 */
import java.util.NoSuchElementException;
// do not import anything else.



public class LinkedList<E> implements ListI<E>, Comparable<E>{
	
	

	/* For our Linked List, we will
	 * need a head and a tail pointer. We 
	 * will also be keeping track of our node elements
	 * with a currentSize counter.
	 */

	private Node<E> head, tail;
	private int currentSize;

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

	public LinkedList() {  // Our class constructor sets head and tail to null and currentSize to 0.
		head=null;
		tail=null;
		currentSize=0;
	}


	@Override
	public void addFirst(E obj) {		// Adds a new element to the beginning of the list
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

	@Override
	public void addLast(E obj) {			// Adds a new element to the end of the list
		Node<E> newNode = new Node<E>(obj);
		if(head==null) {
			head=tail=newNode;
			currentSize++;
			return;
		}
		tail.next=newNode;
		tail=newNode;
		currentSize++;
	}
	@Override
	public E removeFirst() {	//Removes the first element in the list
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

	@Override
	public E removeLast() {      //Removes the last element in the list
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

	
	@SuppressWarnings("unchecked")
	@Override
	public boolean contains(E obj) {        // checks if the element is part of the list.
		Node<E> temp = head;
		while(temp!=null) {
			if(((Comparable<E>) temp.data).compareTo(obj)==0) 
				return true;
			temp=temp.next;
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	public E returnMinValue() {				// Returns Minimum Value in List
		if(head==null) return null;
		Node<E> current = head;
		E temp = current.data;
		while(current!=null) {
			if(((Comparable <E>)current.data).compareTo(temp)<0) {
				temp = current.data;
			}
			current=current.next;
		}
		return temp;
	}
	
	@SuppressWarnings("unchecked")
	public E removeLastDuplicate(E obj) { // Removes last duplicate in the list
		Node<E> current=head;
		Node<E> previous=null,pwhere=null,cwhere=null;
		while (current!=null) {
			if(((Comparable<E>) current.data).compareTo(obj)==0) {
				cwhere=current;
				pwhere=previous;	
			}
			previous=current;
			current=current.next;
		}
		if(pwhere==null&&cwhere==null)
			return null;
		if(cwhere==null)
			return removeLast();
		if(pwhere==null) 
			return removeFirst();
		E temp = cwhere.data;
		pwhere.next=cwhere.next;
		currentSize--;
		return temp;
	}


	@Override
	public E peekFirst() {				//peeks at the fist element in our list
		if(head==null) 	return null;   
		return head.data;
	}

	@Override
	public E peekLast() {				// peeks at the last element in our list
		if(head==null) return null;
		return tail.data;
	}

	@Override
	public void makeEmpty() {  // makes the list empty by setting head and tail to null and currentSize to 0.
		head=tail=null;
		currentSize=0;
	}

	@Override
	public boolean isEmpty() {  //checks to see if the list has no elements
		return head==null;
	}

	@Override
	public boolean isFull() {	// A linked list can never be full. Its will always return false.
		return false;
	}

	@Override
	public int size() {			// returns the number of elements in the list
		return currentSize;
	}

	@Override
	public Iterator<E> iterator() {        // Lets us write nice compact for loops
		return new IteratorHelper();
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


	@SuppressWarnings("unchecked")
	@Override
	public int compareTo(E obj) {
		int result = ((Comparable <E>) head.data).compareTo(obj);
		return result;
	}
}
