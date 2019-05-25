
public class Tester {

	public static void main(String[] args) {
		
		
		LinkedList<Integer> list = new LinkedList<Integer>();
		
		
		// adds 25-0 to list
		for(int i=0; i<26;i++) {
			
			list.addFirst(i);
			
		}
		// adds -5,-7,-2 to list
			list.addFirst(-7);
			list.addFirst(-2);
			list.addFirst(-5);
			
		for(Integer obj: list) {
			// Print -5, -2, -7, 25-0
			System.out.print(" " + obj);
			
		}
		
		//Returns Minimum Value in List
		System.out.println("\n"+list.returnMinValue()+"\n");
		
		//Returns True if List Contains Object
		System.out.println(list.contains(20)+"\n");
		
		
		System.out.println();
		
		
		
		
		
		
		
		
	}

}
