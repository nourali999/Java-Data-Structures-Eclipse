
import java.util.Iterator;

/**
 * The Hash data structure has O(1) time complexity (best case) for add, remove, and find
 * for an object in the data structure. The methods in the Hash data structure are defined
 * by the HashI interface. The Hash consists of an array of Linked Lists,
 * the Linked Lists are defined by the HashListI interface.
 * 
 * @author
 *
 * @param <K> The key for entries in the hash
 * @param <V> The value for entries in the hash
 */
public class Hash<K, V> implements HashI<K, V> {
	@SuppressWarnings("hiding")
	 class HashElement<K,V> implements Comparable<HashElement<K,V>>{
		K key;
		V value;
		public HashElement (K key, V value) {
			this.key=key;
			this.value=value;
		}
		@SuppressWarnings("unchecked")
		@Override
		//We will be comparing keys not IPAddresses
		public int compareTo(HashElement<K, V> o) {
			return (((Comparable<K>) this.key).compareTo(o.key));
		}
	}
    LinkedList<HashElement<K,V>>[] harray;
	int tableSize;
	int numElements;
	double maxLoadFactor;
	
	@SuppressWarnings("unchecked")
	public Hash(int size) {
		this.tableSize=size;
		maxLoadFactor=0.75;
		numElements=0;
		// Creates and array of LinkedLists that are initialized with HashElement objects
		harray =(LinkedList<HashElement<K,V>> []) new LinkedList[tableSize];
			for(int i =0; i<tableSize;i++) {
				harray[i]= new LinkedList<HashElement<K,V>>();
			}
	}

	@Override
	public boolean add(K key, V value) {
		//do we need to resize
		if(loadFactor()>maxLoadFactor) 
			resize(tableSize*2);
		//Create a new hashElmement with key and value
		HashElement<K,V> he = new HashElement<K,V>(key, value);
		// Hash the key, make it positive and mod on tableSize
		int hashval = key.hashCode();
		hashval = hashval & 0x7FFFFFFF;
		hashval = hashval % tableSize;
		// Adds the new hashElement to a LinkedList
		harray[hashval].add(he);
		numElements++;
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean remove(K key) {
		// Hash the key, make it positive and mod on tableSize
		int hashval = key.hashCode();
		hashval = hashval & 0x7FFFFFFF;
		hashval = hashval % tableSize;
		//Use the our iterator in our LinkedList
		for(HashElement<K,V> he: harray[hashval]) { 
			if(((Comparable<K>) he.key).compareTo(key)==0) {
				// Once found, use the remove method in our LinkedList
			harray[hashval].remove(he);
			return true;
			}
			}
			return false;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean changeValue(K key, V value) {
		// Hash the key, make it positive and mod on tableSize
		int hashval = key.hashCode();
		hashval = hashval & 0x7FFFFFFF;
		hashval = hashval % tableSize;
		
		for(HashElement<K,V> he: harray[hashval]) { 
			if(((Comparable<K>) he.key).compareTo(key)==0) {
				// Once found, set the previous value to point to our value
			he.value=value;
			return true;
			}
			}
			return false;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean contains(K key) {
		// Hash the key, make it positive and mod on tableSize
		int hashval = key.hashCode();
		hashval = hashval & 0x7FFFFFFF;
		hashval = hashval % tableSize;
		for(HashElement<K,V> he: harray[hashval]) { 
			if(((Comparable<K>) he.key).compareTo(key)==0) {
				// Once found, return true
			return true;
			}
			}
		//Otherwise if not found return false
			return false;
	}

	@SuppressWarnings("unchecked")
	@Override
	public V getValue(K key) {
		// Hash the key, make it positive and mod on tableSize
		int hashval = key.hashCode();
		hashval = hashval & 0x7FFFFFFF;
		hashval = hashval % tableSize;
	for(HashElement<K,V> he: harray[hashval]) { 
		if(((Comparable<K>) he.key).compareTo(key)==0) {
			//Once found, return value
		return he.value;
		}
		}
		return null;
	}

	@Override
	public int size() {
		return numElements;
	}

	@Override
	public boolean isEmpty() {
		return numElements==0;
	}

	@Override
	public void makeEmpty() {
		for(int i=0;i<harray.length;i++) {
			harray[i].makeEmpty();
		}
		numElements=0;
		
	}

	@Override
	public double loadFactor() {
		return (numElements/tableSize);
	}

	@Override
	public double getMaxLoadFactor() {
		return maxLoadFactor;
	}

	@Override
	public void setMaxLoadFActor(double loadfactor) {
		this.maxLoadFactor=loadfactor;	
	}

	@SuppressWarnings("unchecked")
	@Override
	public void resize(int newSize) {
		// Doubles the size of the array
	LinkedList<HashElement<K,V>>[] new_array = (LinkedList<HashElement<K,V>>[]) new LinkedList[newSize];
	for(int i=0; i<newSize;i++) {
		new_array[i] = new LinkedList<HashElement<K,V>>();
	}
	for(K key: this) {
		// gets every key and rehashes on the new tableSize
		V value = getValue(key);
		HashElement<K,V> he = new HashElement<K,V>(key,value);
		int hashval = (key.hashCode() & 0x7FFFFFFF ) % newSize;
		new_array[hashval].add(he);	
		}
		harray=new_array;
		tableSize=newSize;
	}

	class IteratorHelper<T> implements Iterator<T>{
		T[] keys;
		int posn;
		@SuppressWarnings({ "unchecked" })
		public IteratorHelper() {
			keys = (T[]) new Object[numElements];
			int counter=0;

			for(int i=0;i<tableSize;i++) {
				for(HashElement<K,V> he: harray[i]) 
					keys[counter++]= (T) he.key;
			}
			//TODO sort array of keys here
			posn=0;

		}
		@Override
		public boolean hasNext() {
			return posn<keys.length;
		}

		@Override
		public T next() {
			if(!hasNext()) return null;
			return keys[posn++];
		}
	}
	

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public Iterator<K> iterator() {
		return new IteratorHelper();
	}
	 
}

