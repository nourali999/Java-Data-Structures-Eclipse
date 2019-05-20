

// This is where you will write the calculator functions
//
// This class only has one public method:



public class Calculator{

	/*
	 * Create three BigNumbers
	 * num1, num2 and sum.
	 */

	private BigNumber num1 = new BigNumber();
	private BigNumber num2 = new BigNumber();
	private BigNumber sum = new BigNumber();
	 
	 public String calculate(String number1, String operation, String number2) {
	
		/*If the number1 is negative, 
		*set the boolean setNegative
		*to true. 
		*Remove "-" from String number1.
		 */	 
			 
		 	char negative = '-';
		 	
		 	if(number1.charAt(0)==negative) {
		 		num1.setNegative(true);
		 		StringBuilder temp = new StringBuilder(number1);
		 		temp.deleteCharAt(0);
		 		number1 = temp.toString();
		 		}else {
		 			num1.setNegative(false);
		 		}
		/*
		 *If the number2 is negative, 
		 *use StringBuilder to remove the
		 *character at position 0. 
		 *The"-" will be removed String number2.
		 */

		if(number2.charAt(0)==negative) {
			num2.setNegative(true);
			StringBuilder temp = new StringBuilder(number2);
			temp.deleteCharAt(0);
			number2 = temp.toString();
		}else {
			num2.setNegative(false);
		}


		/*
		 * Add leading 0's to have the numbers be the same length
		 * 
		 */

		StringBuilder temp1 = new StringBuilder(number1);

		while(temp1.length()<number2.length()) {
			temp1.insert(0, 0);
			number1 = temp1.toString();	
			}
		
		StringBuilder temp2 = new StringBuilder(number2);
		
		while(temp2.length()<number1.length()) {
			temp2.insert(0, 0);
			number2 = temp2.toString();	
			}


		// stores the number1 value as an integer to our linkedList
		num1.stringtoBigNumber(number1);

		// stores the number2 value as an integer to our linkedList
		num2.stringtoBigNumber(number2);




		// We will use switch to choose which block to execute



		switch(operation) {


		// This case execute if the operation is used is addition.


		case "+":

			/*
			 * The if statement checks if the two integers are both negative.
			 */

			if(num1.getNegative()&&num2.getNegative()) {

				/*
				 * Just like in middle school. If the first two numbers we are adding
				 * are bigger than ten, we carry a one other wise we do not.
				 * 
				 */
				int carry = 0;
				while(num1.length()!=0) {
					int tempsum = num1.getLastDigit() + num2.getLastDigit() + carry;
					int leftOver = (tempsum ) % 10;
					sum.addFirstDigit(leftOver);
					carry=0;
					if(tempsum >=10) {
						carry = 1;
					}
				}		

				//if we have a one left over at the end then bring it down to the front.
				if(carry==1) {
					sum.addFirstDigit(1);
				}

				/* Since we are adding two negative number, our big number need to be negative.
				 *We can accomplish this by removing the first digit making it negative and 
				 *putting it back. We will utilize a temporary variable to accomplish this.
				 */

				int temp = sum.getFirstDigit()*(-1);
				sum.addFirstDigit(temp);
				break;

			}
			
			// This if statement handles the case where the first number is negative and the second is positive.
			if(num1.getNegative()==true && num2.getNegative()==false){ 
				
				while(num1.length()!=0) {
					int digit1 = num1.peekFirstDigit();
					int digit2 = num2.peekFirstDigit();
					if(digit1<digit2) {	// if the first digit of the first number is smaller, switch the numbers and subtract.
						BigNumber temp = new BigNumber();
						temp =num1;  
						num1=num2;				// switches num2 with num1 and vice versa.
						num2=temp;
						int borrow = 0;		
						while(num1.length()!=0){
						int storedNum1 = num1.getLastDigit() + borrow;
						int storedNum2 = num2.getLastDigit();
						int storedSubtraction = storedNum1 - storedNum2;
						if(storedSubtraction<0) {
							storedNum1+=10;
							int leftover = storedNum1-storedNum2;
							sum.addFirstDigit(leftover);
							borrow = -1;
							}else {
							int leftover= storedNum1-storedNum2;
							sum.addFirstDigit(leftover);
							borrow=0;
						}
						}
						}
				
					if(digit2<digit1) { //If the second digit is bigger than we can subtract and return that number as a negative
						int borrow = 0;		
						while(num1.length()!=0){
						int storedNum1 = num1.getLastDigit() + borrow;
						int storedNum2 = num2.getLastDigit();
						int storedSubtraction = storedNum1 - storedNum2;
						if(storedSubtraction<0) {
							storedNum1+=10;
							int leftover = storedNum1-storedNum2;
							sum.addFirstDigit(leftover);
							borrow = -1;
							}else {
							int leftover= storedNum1-storedNum2;
							sum.addFirstDigit(leftover);
							borrow=0;
						}
						}
						
						int temp = sum.getFirstDigit()*(-1);
						sum.addFirstDigit(temp);
						break;
					}
					if(digit1==digit2) {// if the first two digits are the same. Remove them IE. -94 + 95 --> -4 + 5
						num1.getLastDigit();
						num2.getLastDigit();
						
					}
					break;
					
				}
			}
			
			
			if(num1.getNegative()==false&&num2.getNegative()==true) {
				while(num1.length()!=0) {
					int digit1 = num1.peekFirstDigit();
					int digit2 = num2.peekFirstDigit();
					if(digit1>digit2) {	// if the first digit of the first number is bigger, switch the numbers and subtract.
						 
						int borrow = 0;		
						while(num1.length()!=0){
						int storedNum1 = num1.getLastDigit() + borrow;
						int storedNum2 = num2.getLastDigit();
						int storedSubtraction = storedNum1 - storedNum2;
						if(storedSubtraction<0) {
							storedNum1+=10;
							int leftover = storedNum1-storedNum2;
							sum.addFirstDigit(leftover);
							borrow = -1;
							}else {
							int leftover= storedNum1-storedNum2;
							sum.addFirstDigit(leftover);
							borrow=0;
						}
						}
						}
				
					if(digit2>digit1) { //If the second digit is bigger than we can subtract and return that number as a negative
						BigNumber temp = new BigNumber();
						temp =num1;  
						num1=num2;				// switches num2 with num1 and vice versa.
						num2=temp;
						int borrow = 0;		
						while(num1.length()!=0){
						int storedNum1 = num1.getLastDigit() + borrow;
						int storedNum2 = num2.getLastDigit();
						int storedSubtraction = storedNum1 - storedNum2;
						if(storedSubtraction<0) {
							storedNum1+=10;
							int leftover = storedNum1-storedNum2;
							sum.addFirstDigit(leftover);
							borrow = -1;
							}else {
							int leftover= storedNum1-storedNum2;
							sum.addFirstDigit(leftover);
							borrow=0;
						}
						}
						
						int tempneg = sum.getFirstDigit()*(-1);
						sum.addFirstDigit(tempneg);
						break;
					}
					if(digit1==digit2) {// if the first two digits are the same. Remove them IE. -94 + 95 --> -4 + 5
						num1.getLastDigit();
						num2.getLastDigit();
						
					}
					break;
					
				}
			}
			/*Last case is if we have two positive integers
			  Just like in middle school. If the first two numbers we are adding
			  are bigger than ten, we carry a one other wise we do not.
			 */
			
			int carry = 0;
			while(num1.length()!=0) {

				int tempSum = num1.getLastDigit() + num2.getLastDigit() + carry;
				int leftOver = (tempSum ) % 10;
				sum.addFirstDigit(leftOver);
				carry=0;
				if(tempSum >=10) {
					carry = 1;
				}
				}		
			
			if(carry==1) {		//if we have a one left over at the end then bring it down to the front.
				sum.addFirstDigit(1);
				}
			
				break;
				
			case "-":	// This case will handle subtraction
				
			if(num1.getNegative()==true&&num2.getNegative()==true) { //negative - negative is the same as negative + a positive
				while(num1.length()!=0) {
					int digit1 = num1.peekFirstDigit();
					int digit2 = num2.peekFirstDigit();
					if(digit1<digit2) {	// if the first digit of the first number is smaller, switch the numbers and subtract.
						BigNumber temp = new BigNumber();
						temp =num1;  
						num1=num2;				// switches num2 with num1 and vice versa.
						num2=temp;
						int borrow = 0;		
						while(num1.length()!=0){
						int storedNum1 = num1.getLastDigit() + borrow;
						int storedNum2 = num2.getLastDigit();
						int storedSubtraction = storedNum1 - storedNum2;
						if(storedSubtraction<0) {
							storedNum1+=10;
							int leftover = storedNum1-storedNum2;
							sum.addFirstDigit(leftover);
							borrow = -1;
							}else {
							int leftover= storedNum1-storedNum2;
							sum.addFirstDigit(leftover);
							borrow=0;
						}
						}
						}
				
					if(digit2<digit1) { //If the second digit is bigger than we can subtract and return that number as a negative
						int borrow = 0;		
						while(num1.length()!=0){
						int storedNum1 = num1.getLastDigit() + borrow;
						int storedNum2 = num2.getLastDigit();
						int storedSubtraction = storedNum1 - storedNum2;
						if(storedSubtraction<0) {
							storedNum1+=10;
							int leftover = storedNum1-storedNum2;
							sum.addFirstDigit(leftover);
							borrow = -1;
							}else {
							int leftover= storedNum1-storedNum2;
							sum.addFirstDigit(leftover);
							borrow=0;
						}
						}
						
						int temp = sum.getFirstDigit()*(-1);
						sum.addFirstDigit(temp);
						break;
						}
						if(digit1==digit2) {// if the first two digits are the same. Remove them IE. -94 + 95 --> -4 + 5
						num1.getLastDigit();
						num2.getLastDigit();
						
						}
						break;
					
						}
				
						}
			
			if(num1.getNegative()==true&&num2.getNegative()==false) {
				int carryNum = 0;
				while(num1.length()!=0) {

					int tempSum = num1.getLastDigit() + num2.getLastDigit() + carryNum;
					int leftOver = (tempSum ) % 10;
					sum.addFirstDigit(leftOver);
					carryNum=0;
					if(tempSum >=10) {
						carryNum = 1;
					}
					}		
				if(carryNum==1) {		//if we have a one left over at the end then bring it down to the front.
					sum.addFirstDigit(1);
					}
				int tempneg = sum.getFirstDigit()*(-1);
				sum.addFirstDigit(tempneg);
				break;
				
			}
			
			
			if(num1.getNegative()==false&&num2.getNegative()==true) { // since positive - negative is same + plus +, we will add.
				int carrynum = 0;
				while(num1.length()!=0) {

					int tempSum = num1.getLastDigit() + num2.getLastDigit() + carrynum;
					int leftOver = (tempSum ) % 10;
					sum.addFirstDigit(leftOver);
					carrynum=0;
					if(tempSum >=10) {
						carrynum = 1;
					}
					}		
				if(carrynum==1) {		//if we have a one left over at the end then bring it down to the front.
					sum.addFirstDigit(1);
					}
				
					break;
					}
			
			/*
			 * Subtract the last two digits of the numbers.
			 * if the two digits are negative then borrow one.
			 * Otherwise just subtract the two numbers.				 
			 */
			if (num1.getNegative()==false&&num2.getNegative()==false) {
				 while(num1.length()!=0) {
				 int digit1 = num1.peekFirstDigit();
				 int digit2 = num2.peekFirstDigit();
				 if(digit2>digit1) { //If the second digit is bigger than we can subtract and return that number as a negative
				 BigNumber temp = new BigNumber();
				 temp =num1;  
				 num1=num2;				// switches num2 with num1 and vice versa.
				 num2=temp;
				 int borrow = 0;		
				 while(num1.length()!=0){
				 int storedNum1 = num1.getLastDigit() + borrow;
				 int storedNum2 = num2.getLastDigit();
				 int storedSubtraction = storedNum1 - storedNum2;
				 if(storedSubtraction<0) {
				 storedNum1+=10;
							int leftover = storedNum1-storedNum2;
							sum.addFirstDigit(leftover);
							borrow = -1;
							}else {
							int leftover= storedNum1-storedNum2;
							sum.addFirstDigit(leftover);
							borrow=0;
						}
						}
						
						int tempneg = sum.getFirstDigit()*(-1);
						sum.addFirstDigit(tempneg);
						break;
					}
				int borrow = 0;		
				while(num1.length()!=0){
				int storedNum1 = num1.getLastDigit() + borrow;
				int storedNum2 = num2.getLastDigit();
				int storedSubtraction = storedNum1 - storedNum2;
				if(storedSubtraction<0) {
					storedNum1+=10;
					int leftover = storedNum1-storedNum2;
					sum.addFirstDigit(leftover);
					borrow = -1;
					}else {
					int leftover= storedNum1-storedNum2;
					sum.addFirstDigit(leftover);
					borrow=0;
				}
				}
			 	}
			 	}

			break;
		case "*":

				sum.addFirstDigit(0);
				
		case "/":
		
				sum.addFirstDigit(0);
		
				}
			return sum.toString();

	 			}
				}
				




