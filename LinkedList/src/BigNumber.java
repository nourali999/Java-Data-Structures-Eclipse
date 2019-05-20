
// This is where you will write your code for manipulating the big number
// as described in the assignment. 
//
// This class will include the getFirstDigit(), getLastDigit(), addFirstDigit(), addLastDigit()
// methods, as well as the setNegative(), getNegative(), length() methods.
//
// You will also need to write stringToBigNumber() and toString() methods.

	
	public class BigNumber {
		
		//
	
		LinkedList<Integer> numberList = new LinkedList<Integer>();
		
		
		
		//This is a wrapper class for out BigNumbers.

		
		
		public int length() {				// Returns the size of the linked list
			return numberList.size();
			}
		
		public void addFirstDigit(int obj) {  
			numberList.addFirst(obj);
			}
		
		public void addLastDigit(int obj) {
			numberList.addLast(obj);
		}
		
		public int peekFirstDigit() {
			return numberList.peekFirst();
			}
		
		public int peekLastDigit() {
			return numberList.peekLast();
			}
		
		
		public int getFirstDigit() {
			return numberList.removeFirst();
		}
		
		public int getLastDigit() {
			return numberList.removeLast();
		}
		
		
		/*
		 *The for loop Converts each character in the 
		 *string named "number1" to an integer. Then it adds each integer to 
		 *the LinkedList data structures. The addLastDigit method will be 
		 *utilized to accomplish this. 
		 */
		
		public void stringtoBigNumber(String s) {
				for (int i=0;i<s.length();i++) {
				int number = Integer.valueOf(String.valueOf(s.charAt(i)));
				numberList.addLast(number);
				}
				}
			
			private boolean isNegative = false;
			
			public void setNegative(boolean n) {
			this.isNegative = n;			
			}
		
			public boolean getNegative() {
			return this.isNegative;
			}
		
		
		/*
		 * Main Purpose: Prints our BigNumber
		 * on the screen the way we want it too.
		 * Simply print out each value in our Big number
		 * until the is no more items left.
		 */
	
			
		@Override
		public String toString() {
			String r = "";
			while(!numberList.isEmpty()) {
			r = r + numberList.removeFirst();
			}
			return r;
			}	
		
		
}
