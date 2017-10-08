package cs;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
public class MainFile 
{
		//----------------------------------------------------------------
		 // Constants
		 //----------------------------------------------------------------
			
		// File constants
		 public static final String FILE_NAME = "CandyInventoryIn.txt";
		 public static final String FILE_NAME_OUT = "CandyInventoryOut.txt";
		 public static final int COL1_START = 0;
		 public static final int COL1_END = 2;
		 public static final int COL2_START = 3;
		 public static final int COL2_END = 22;
		 public static final int COL3_START = 23;
		 public static final int COL3_END = 36;
		 public static final int COL4_START =37;
		 public static final int COL4_END = 45;
		 public static final int NUM_OF_CANDY_TYPES = 7;
			
		// Formatting constants
		 public static final String COLFMT1 = "%" + 
					(COL1_END - COL1_START + 1) + "d";
		 public static final String COLFMT2 = "%-" + COL2_END + "s";
		 public static final String COLFMT3 = "%" + 
				(COL3_END - COL3_START + 1) + ".1f";

		 public static final String COLFMT4 = "%" + 
				(COL4_END - COL4_START + 1) + "d";
		 
		
		 //----------------------------------------------------------------
		 // readTextFile
		 //----------------------------------------------------------------
		 public static void readTextFileCandy(
				 ArrayList<Integer> code, ArrayList<String> name, 
				ArrayList<Double> cost, 
				ArrayList<Integer> candyCount, ArrayList<CandyStore> candies)
		 {
		     // Declare variables
		     Scanner fileIn = null;
		     
		     String line;
		     int dataCount;
		     int codeHolder, candyCountHolder;
		     String nameHolder;
		     double costHolder;
		 
		     // Attempt to open input file
		     try
		     {
		     	// Assign external file to file handle
		         fileIn = new Scanner(new FileInputStream(FILE_NAME));
		     
		         // Loop to read data
		         dataCount = 0;
		         System.out.println(); 
		         while (fileIn.hasNextLine())
		         {
		             line = fileIn.nextLine();
		             
		                // Parse input line and store tokens
		         		code.add(Integer.parseInt(
			     				line.substring(COL1_START, COL1_END).trim()));
		         		codeHolder = code.get(dataCount);
		         		name.add(line.substring(COL2_START, COL2_END));
		         		nameHolder = name.get(dataCount);
		         		cost.add(Double.parseDouble(
		     				line.substring(COL3_START, COL3_END).trim()));
		         		costHolder = cost.get(dataCount);
		         		candyCount.add(Integer.parseInt(
		     				line.substring(COL4_START, COL4_END).trim()));
		         		candyCountHolder = candyCount.get(dataCount);
		         		 
		         	candies.add(new CandyStore(codeHolder, nameHolder, costHolder, candyCountHolder));
		              
		                 // Increment line counter
		                 dataCount = dataCount + 1; 
		         }
		         
		         // Close input file
		         fileIn.close();   
		     }
		     // Handle file error
		     catch (FileNotFoundException e)
		     {
		         System.out.println("Error: file '" + FILE_NAME +
		             "' not found.");
		         System.out.println("Error message:\n" + e.getMessage());
		     }
		     catch(Exception e)
			{
					System.out.println("An exception occurred");
			}
		 }//end of read file to candies method
		
		 //----------------------------------------------------------------
		 // writeTextFile
		 //----------------------------------------------------------------
		 public static void writeTextFile(ArrayList<CandyStore> candies)
		 {
		     // Declare variables
		     PrintWriter fileOut = null;
		     int dataCount;

		     // Attempt to open output file
		     try
		     {	
		     	// Assign external file to file handle
		         fileOut = new PrintWriter(new FileOutputStream(FILE_NAME_OUT));
		         
		         // Loop to write data
		         dataCount = 0;
		  
		         	for(CandyStore str: candies)
		        	      {
		        	        fileOut.printf(
		        	             COLFMT1 + COLFMT2 + COLFMT3 + COLFMT4 + "%n", 
		        	             str.getCode(), 
		        	             str.getName(), 
		        	             str.getCost(), 
		        	             str.getCandyCount());
		        	             
		        	          // Increment line counter
		        	         dataCount = dataCount + 1;
		        	      }     
		         
		         // Show number of lines written
		         System.out.println("\n" + dataCount + " data line(s) " +
		             "written to file '" + FILE_NAME + "'.");
		             
		         // Close output file
		         fileOut.close();
		     }    
		     // Handle file error
		     catch (FileNotFoundException e)
		     {
		         System.out.println("Error: file '" + FILE_NAME +
		             "' cannot be created or opened.");
		         System.out.println("Error message: " + e.getMessage());
		     } 
		 }
		 
	//============================================
	//Methods for the main menu in main function
	//============================================
		 
		 
		 //----------------------------------------------------------------
		 // List/Print Candy Inventory
		 //----------------------------------------------------------------
		public static void listCandyInventory( ArrayList<CandyStore> candies)
		{
			 //Print header
			 System.out.printf(
					"\n" + "%-10s" + "%-25s" + "%-8s" + "%-13s" + "%n", 
			    		"Code", "Name", "Cost", "Candy Count");
			 for(CandyStore str: candies)
		        {
		             System.out.printf(
		             		COLFMT1 + COLFMT2 + COLFMT3 + COLFMT4 + "%n", 
		             		str.getCode(), 
		             		str.getName(), 
		             		str.getCost(), 
		             		str.getCandyCount());
		         } 
	 		
	 		System.out.printf("\nTotal Inventory Value: %.2f ", CandyStore.getInventoryValue());
	 		System.out.printf("\nTotal Inventory Count: %d", CandyStore.getInventoryCount());
		}
		 
		 //----------------------------------------------------------------
		 // Sell Candy to user
		 //----------------------------------------------------------------
		public static void sellCandy(ArrayList<CandyStore> candies)
		{
			int code;
			int quantity;
			int index; //variable to keep track of the array index I need
			
			Scanner keyboard = new Scanner(System.in);
			
			try
			{	
				System.out.println("\nEnter the code of the item you'd like to buy");
				code = keyboard.nextInt();
			
				//Validation loop for the code entered
				while (code < 30 || code > 36)
				{
					System.out.println("Invalid Code. Please Re-enter code");
					code = keyboard.nextInt();
				}
			
				index = code - 30;
			
				System.out.println("Enter the quality you'd wish to buy");
				quantity = keyboard.nextInt();
				keyboard.nextLine();
			
				//Validation loop to make sure user does not enter invalid quantity
				//For the particular item they want
				while(quantity < 0 || quantity > candies.get(index).getCandyCount())
				{
					System.out.println("Invalid amount. Please re-enter");
					quantity = keyboard.nextInt();
				}
			
				//updates the candy count for the type the user purchases
				candies.get(index).setCandyCount(
									candies.get(index).getCandyCount() - quantity );
			
				CandyStore.setInventoryCount(-quantity);
				CandyStore.setInventoryValue(candies.get(index));
				CandyStore.setProductCount();
			
				//Purchase Confirmation Message
				System.out.printf("Purchase confirmation");
				System.out.printf("\nItem %d. Quantity: %d", code, quantity);
			}
			catch (InputMismatchException e)
			{
				System.out.println("Input mismatch. Returning to menu");
				keyboard.nextLine(); //clear the buffer
			}
			catch(Exception e)
			{
				System.out.println("An exception occurred");
			}
		}
		
		
		 //----------------------------------------------------------------
		 // Order candy from user/ allow user to sell candy to store
		 //----------------------------------------------------------------
	public static void orderCandy(ArrayList<CandyStore> candies)
		{
			Scanner keyboard = new Scanner(System.in);
			int code1, quantity1;
			int index; // to keep track of candy object we need
			
			try
			{
				
			System.out.println("\nEnter the code for the item you wish to sell");
			code1 = keyboard.nextInt();

			//Validation loop for the code entered
			while (code1 < 30 || code1 > 36)
			{
				System.out.println("Invalid Code. Please Re-enter code");
				code1 = keyboard.nextInt();
			}
			index = code1 - 30;
			System.out.println("Please enter the quantity you wish to sell");
			quantity1 = keyboard.nextInt();
			
			//Validation loop for the quantity the user wishes to sell
			while (quantity1 < 1)
			{
				System.out.println("Please enter a quantity greater than zero");
				quantity1 = keyboard.nextInt();
			}
			
			candies.get(index).setCandyCount(
					candies.get(index).getCandyCount() + quantity1);
			CandyStore.setInventoryValue(candies.get(index));
			CandyStore.setInventoryCount(quantity1);
			
			System.out.println("Purchase confirmation");
			System.out.printf("\nSold Item #%d, Quantity:%d", code1, quantity1);
			}
			catch(InputMismatchException e)
			{
				System.out.println("Input mismatch. Returning to menu");
				keyboard.nextLine();
			}
		}

	//----------------------------------------------------------------
	// Main
	//----------------------------------------------------------------
	public static void main(String[] args)
	{
			 // Declare variables
			 Scanner keyboard = new Scanner(System.in);
			 int choice;
			 ArrayList<Integer> code = new ArrayList<Integer>();
		     ArrayList<String> name = new ArrayList<String>();
		     ArrayList<Double> cost = new ArrayList<Double>();
		     ArrayList<Integer> candyCount = new ArrayList<Integer>();
		     
		     ArrayList<CandyStore> candies = new ArrayList<CandyStore>();
		     
		     readTextFileCandy(code, name, cost, candyCount, candies);
		     
		     System.out.println("Welcome to Candy Cupboards online store!");
		     listCandyInventory(candies);
		  
		     //Loop that prints the menu options for the user
		     do
		     {
		    	 System.out.println("\nCandy Cupboard Menu");
		  	     System.out.print("1 - Buy candy from store \n2 - Sell candy to store"
		  	     		+ "\n3 - List candy inventory"
		  	     		+ "\n4 - Exit\nEnter an option: ");
		  	     
		    	 System.out.println("Please enter the number that corresponds "
		    	 		+ "with your selection");
		    	 choice = keyboard.nextInt();
		    	 switch (choice)
		    	 {
		    		 case 1:
		    			  sellCandy(candies);
		    			 break;
		    		 case 2:
		    			 orderCandy(candies);
		    			 break;
		    		 case 3:
		    			 listCandyInventory(candies);
		    			 break;
		    		 case 4:
		    			 System.out.println("\nYou have choosen to exit. Goodbye");
		    			 writeTextFile(candies);
		    			 candies.clear(); //clears list
		    			 break;
		    		 default:
		    				 System.out.println("\nInvalid choice");
		    	 }
		     } while (choice != 4);
		   
		}//end of main

}
