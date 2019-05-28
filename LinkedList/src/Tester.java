
public class Tester {

	public static void main(String[] args) {
		
		
		LinkedList<Integer> list = new LinkedList<Integer>();
		
		
		// adds 25-0 to list
		for(int i=0; i<26;i++) {
			
			list.addFirst(i);
			
		}
	   // adds -5,-7,-2 to the beginning of our list
			list.addFirst(-7);
			list.addFirst(-2);
			list.addFirst(-5);
			
		for(Integer obj: list) {
			// Print -5, -2, -7, 25-0
			System.out.print(" " + obj);
			
		}
		System.out.print("\n");
		//Returns Minimum Value in List
		System.out.println("The minimum value is: "+list.returnMinValue());
		
		//Returns True if List Contains Object
		System.out.println("Does the list contain 20: "+list.contains(20));
		
		//Adds 9 to end of list
		list.addLast(9);
		
		
		//prints list with the added 9 at end
		for(Integer obj: list)
			System.out.print(" "+obj);
		
		//Makes new line
		System.out.println();
		
		//Removes Last duplicate
		System.out.println("Remove the last duplicate/copy of "
		   +list.removeLastDuplicate(9)+":");
		
		for(Integer obj: list)
			System.out.print(" "+obj);
	
		
		
		
		
		
		
	}

}
