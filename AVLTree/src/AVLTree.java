
import java.util.Iterator;

public class AVLTree<K, V> implements AVLTreeI<K, V> {

	@SuppressWarnings("hiding")
	class Node<K, V>{
		V value;
		K key;
		// height is the height from node to leaf, longest path
		int height;
		Node<K,V> left;
		Node<K,V> right;
		Node<K,V> parent;

		public Node(K key, V value) {
			this.key=key;
			this.value=value;
			left=right=null;
			this.height=0;
		}
	}
	private Node<K,V> root;
	// keeps track of total number of nodes in our tree
	private int currentSize;
	public AVLTree() {
		root=null;
		currentSize=0;
	}

	@Override
	public void add(K key, V value) {
		Node<K, V> newNode =new Node<K, V>(key,value);
		this.root= add(root,newNode);
	}

	@SuppressWarnings("unchecked")
	public Node<K,V> add(Node<K,V> node, Node<K, V> newNode) {
		if(node==null) {
			currentSize++;
			return newNode;
		}
		if(((Comparable<K>) newNode.key ).compareTo(node.key)>0) {
			//if the key is bigger than root's key, go right
			node.right = add(node.right,newNode);
		}else {
			//if the key is less than root's key, go left
			node.left = add(node.left, newNode);
		}

		// Updates height of this ancestor node 

		if(height(node.left)>height(node.right)) {
			node.height = height(node.left) +1;
		}
		else
			node.height = height(node.right) +1;

		//  node.height = (Math.max(height(node.left), height(node.right))) + 1;


		// checks if the node needs to be re-balanced
		return rotate(node);

	}

	private Node<K,V> rotate(Node<K,V> node){

		/* if the balance of the node is less than 1
		 and its right child's balance is bigger than 0 do a rightLeftRotate */
		if (getBalance(node) < -1 && getBalance(node.right) > 0) 
			node = rightLeftRotate(node);

		/* if the balance of the node is bigger than 1
			 and its left child's balance is less than 0 do a leftRightRotate */

		if (getBalance(node) > 1 && getBalance(node.left) < 0) 

			node= leftRightRotate(node);
		/* if the node's balance is bigger than 1 
		   and its left child's is bigger than 0 do a rightRotation */

		if (getBalance(node) > 1 && getBalance(node.left) > 0) 
			node = rightRotate(node);

		/* if the balance of the node is less than 1
			 and its right child's balance is less than 0 do a leftRotate */

		if (getBalance(node) < -1 && getBalance(node.right)<0) 
			node = leftRotate(node);  

		//  return the node after rotation or if there is no violation returns the node as is.
		return node;
	}

	// Gets Balance factor of our node by subtracting left and right subtree's height
	private int getBalance(Node<K, V> node) {
		if (node == null)
			return 0;
		return height(node.left) - height(node.right);
	}

	@Override
	//return true of key is found otherwise it returns false
	public boolean contains(K key) {
		return contains(key,root);
	}
	@SuppressWarnings("unchecked")
	private boolean contains(K key, Node<K, V> node) {
		// if the key does not exist say false
		if(node==null) return false;
		//if you found the key return true
		if(((Comparable<K>)key).compareTo(node.key)==0)
			return true;
		// if the key is bigger go right
		if(((Comparable<K>)key).compareTo(node.key)>0)
			return contains(key, node.right);
		// Otherwise go left
		else
			return contains(key, node.left);

	}



	@Override
	public V getValue(K key) {
		return getValue(key,root);
	}
	@SuppressWarnings("unchecked")
	private V getValue(K key, Node<K,V> node) {
		// if the value does not exist return null
		if(node==null) return null;
		// if found return node's value
		if(((Comparable<K>)key).compareTo(node.key)==0)
			return node.value;
		//if the key happens to be bigger go right
		if(((Comparable<K>)key).compareTo(node.key)>0)
			return getValue(key, node.right);
		//Otherwise go left
		else
			return getValue(key, node.left);
	}


	@Override
	// Gets the number of elements in the tree
	public int size() {
		return currentSize;
	}

	@Override
	// return true if empty otherwise false
	public boolean isEmpty() {
		return root==null;
	}

	@Override
	// Return the height of the tree
	public int height() {
		if(root==null) return 0;
		return root.height;
	}
	// Return the height of the largest subtree under the node

	public int height(Node<K,V> node) {
		if(node==null) return -1;
		return node.height;

	}

	@SuppressWarnings("unchecked")

	// returns the height from root to the specified node as dots

	public String getHeight (Node<K,V> node) {
		if(root==null) return "";   // Empty Tree
		if(root==node) return "(root)";    // return 0 if root is the node
		Node<K, V> current = root;
		int height = 0;
		while( current != null )
		{
			// compare the keys
			int compareResult = ((Comparable<K>) node.key).compareTo( current.key);
			// if key is less than root key  and go left and add 1 to height
			if( compareResult < 0 ) {
				height++;
				current = current.left;
			}
			// if key is less than root key and  go right and add 1 to height
			else if( compareResult > 0 ) {
				height++;
				current = current.right;
			}
			else {
				String r ="";
				for(int i =0; i<height; i++) {
					r+= ".";
				}
				return r; // Match return dots
			}
		}
		return "";   //  Node not in list therefore height DNE
	}




	private Node<K,V> rightRotate(Node<K,V> node) {
		Node<K,V> newParentNode = node.left;
		// Perform rotation
		node.left = newParentNode.right;;
		newParentNode.right = node;
		// // update the height of the nodes left if its bigger
		if(height(node.left)>height(node.right)) {
			node.height = height(node.left) +1;
		}
		else
			//Otherwise updates the nodes right's height
			node.height = height(node.right) +1;

		// update the height of the nodes left if its bigger
		if(height(newParentNode.left)>height(newParentNode.right)) {
			newParentNode.height = height(newParentNode.left) +1;
		}
		else
			//Otherwise updates the nodes right's height
			newParentNode.height = height(newParentNode.right) +1;

		// Return new root
		return newParentNode;
	}

	private Node<K,V> leftRotate(Node<K,V> node) {
		Node<K,V> newParentNode = node.right;
		// Perform rotation
		node.right = newParentNode.left;;
		newParentNode.left = node;
		// Update heights
		if(height(node.left)>height(node.right)) {
			node.height = height(node.left) +1;
		}
		else
			node.height = height(node.right) +1;
		if(height(newParentNode.left)>height(newParentNode.right)) {
			newParentNode.height = height(newParentNode.left) +1;
		}
		else
			newParentNode.height = height(newParentNode.right) +1;
		// Return new root
		return newParentNode;
	}
	// Performs a rightLeftRotation
	private Node<K, V> rightLeftRotate(Node<K,V> node){
		node.right= rightRotate(node.right);
		return leftRotate(node);
	}
	// Performs a leftRightRotation
	private Node<K, V> leftRightRotate(Node<K, V> node){
		node.left= leftRotate(node.left);
		return rightRotate(node);
	}


	class IteratorHelper<T> implements Iterator<T>{
		// We need a stack to store the nodes
		class Stack<E>{
			private E[] nodes = null;
			private int size;
			private int front;
			@SuppressWarnings("unchecked")
			public Stack(int max) {
				nodes=(E[]) new Object[max];
				size=0; front =-1;
			}
			// removes from stack
			public E pop() {
				if(this.size == 0){
					return null;
				}
				this.size--;
				E result = this.nodes[front];
				this.nodes[front] = null;
				this.front--;
				return result;
			}
			// adds to stack
			public void push(E node) {
				if (size==currentSize)
					return;
				this.size++;
				this.nodes[++front] = node;
			}
			// checks if stack is empty
			public boolean isEmpty(){
				return size==0;
			}
			// gets the size of the stack
			public int getSize() {
				return size;
			}
		}
		// create a stack of nodes
		Stack<Node<K,V>> stack = new Stack<Node<K,V>>(currentSize);
		@SuppressWarnings("unchecked")
		// Generic array that stores keys.
		T[] inorder = (T[]) new Object[currentSize];
		int counter, position;
		Node<K, V> current;
		@SuppressWarnings("unchecked")
		public IteratorHelper() {
			current=root;
			position=0; counter=0;

			while(true) {
				if(root==null) break;
				//while the trees left is not null
				if(current!=null) {
					//push to the stack
					stack.push(current);
					current=current.left;
				}else {
					// Get out if the stack is empty
					if(stack.isEmpty()) break;
					//remove from the stack
					current= stack.pop();
					// add key to out generic array
					inorder[counter++] = (T) current.key;
					current=current.right;
				}
			}	
		}
		@Override
		public boolean hasNext() {
			return position<counter;
		}

		@Override
		public T next() {
			if(!hasNext()) return null;
			return inorder[position++];
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public Iterator<K> iterator() {
		// returns an instance of an iterator
		return new IteratorHelper();
	}



	// Recursively outputs height key and value of tree
	public void inorder(Node<K,V> node) {
		if(node==null) return;
		inorder(node.left);
		System.out.println(getHeight(node) + "			" + node.key+ "			" + node.value);
		inorder(node.right);
	}

	@Override
	public void print() {
		inorder(root);

	}

}
