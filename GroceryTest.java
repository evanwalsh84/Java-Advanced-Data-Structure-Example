/* 
Evan Walsh
3200
Section 1
This program takes in an inventory text file. After taking in the text file it requires
an input from a user and updates the quantity of the item requested then returns the total
price of every product requested.
*/
import java.io.*;
import java.util.*;

public class GroceryTest
{
	public static final int SIZE = 100;
	public static final double FOODTAX = .02;
	public static final double NONFOODTAX = .08;
	// declare constants for tax rates and maximum array size here

  public static void main(String[] args)
  {
		int usedsize;
		//usedsize records the amount of items input from the text file.
		usedsize = 0;
		GroceryItem[] inventory = new GroceryItem[SIZE];
    // declare array holding inventory at this point
    Scanner fileInput;  // inventory file

		// ***************************************************************
		// Code template for reading and initializing inventory
		//inFile takes in an input txt file.
    File inFile = new File("inventory.dat");
    try {
    				fileInput = new Scanner(inFile);  // open inventory file and read
    				while (fileInput.hasNext())
    				{
    					//Declares a new GroceryItem array using the lines read from the text file.
    					inventory[usedsize] = new GroceryItem();
    					inventory[usedsize].readItem(fileInput);
    					//Checks for duplicate items within the text file.
    					if(inventory[usedsize].itemSearch(inventory, usedsize-1, inventory[usedsize].getNumber()) != -1 && usedsize != 0)
    					{
    						System.out.println("** ERROR: duplicate item " + inventory[usedsize].getNumber() + inventory[usedsize].getName() + " ignored.");
    					}
    				
    					else
    					{
    						usedsize++;
    					}
    				}
      // call to readItem and other logic necessary to fill array
      // with grocery items from inventory.dat goes here
    		}
    catch(FileNotFoundException e){System.out.println(e); }
		int[][] input = new int[SIZE][2];
 
		// End of code template for reading and initializing inventory
		// ***************************************************************
    
		//  initial call to your debug routine printInventory here

		//  call helper function which completes testing here.
		//  BE SURE TO DECLARE Scanner for System.in in the helper routine

		//  final call to your debug routine printInventory here
		input(input, usedsize, inventory);
		System.out.println("INITIAL INVENTORY \n");
		inventory[usedsize].printInventory(inventory, usedsize);
		updatequant(input, usedsize, inventory);
		quantityupdate(input, usedsize,inventory);
		System.out.println("\n\nFINAL INVENTORY \n");
		inventory[usedsize].printInventory(inventory, usedsize);
  }
  public static void input(int[][] input, int usedsize, GroceryItem[] inventory)
	{
 		int product, quant, inputs;
 		inputs = 0;
 		//quant stores the quantity of the product entered by the user.
 		quant = 1;
 		//product stores the product number entered by the user.
 		product = 1;
 		Scanner kbd = new Scanner(System.in);
 		//System.out.println("Enter the inventory.");
 		//product = kbd.nextInt();
 		input[0][0] = product;
 		//System.out.println("Enter the inventory.");
 		//product = kbd.nextInt();
 		input[0][1] = product;
 		//The following loop creates an array and fills it with inputs from the user.
 		for(int x = 0; !((product == 0) && (quant == 0)); x++)
	 	{
	 		//Breaks the loop if the array becomes to large.
			if(x == usedsize+1)
			{
 				break;
 			}
 			else
 			{
 				product = kbd.nextInt();
 				input[x][0] = product;
 				for(int u = 0; u < inputs; u++)
 				
 				inputs++;
 				quant = kbd.nextInt();
 				input[x][1] = quant;
 			}
 
		}
 }
 //updatequant updates the price and quantity entered in by the user and returns the total cost and quantity of all products ordered.
 public static void updatequant(int[][] input, int usedsize, GroceryItem[] inventory)
 {
		double total, productcost, tax, temptotal;
		//tax keeps a running total of the tax based upon the tax code.
		tax = 0;
		//total keeps a running total of the price of every item before tax.
		total = 0;
		//productcost stores the price of an item.
		productcost = 0;
		System.out.println("\nPURCHASE SIMULATION\n");
		//The following loop continues until two zeros have been reached and prints the item and price of every item matching within the inventory.
		for(int x = 0; !((input[x][0] == 0) && (input[x][1] == 0)); x++)
		{
			int productnum = input[x][0];
			int quantity = input[x][1];
			//for(int y = 0; y < usedsize; y++)
			//{
			int location = inventory[usedsize-1].itemSearch(inventory, usedsize, productnum);
			//The following if statement checks if the item was found in the inventory.
			if( location != -1)
			{
				productcost = inventory[location].getPrice()*quantity;
				System.out.printf("%s %d @ %.2f, Cost  = %.2f %s \n", inventory[location].getName(), 
				quantity, inventory[location].getPrice(), (inventory[location].getPrice() * quantity), inventory[location].getTaxCode());
				//The following if statement checks to see which tax code is reached nad applies the appropriate tax cost.
				if(inventory[location].getTaxCode().equals("N"))
				{
					//tax += ((quantity*inventory[location].getPrice())*.02);
					temptotal = (quantity*inventory[location].getPrice());
					total += ((quantity*inventory[location].getPrice()));
					tax += (temptotal*.02);
				}
				else
				{
					temptotal = (quantity*inventory[location].getPrice());
					//tax += ((quantity*inventory[location].getPrice())*.08);
					total += ((quantity*inventory[location].getPrice()));
					tax += (temptotal * .08);
				}
			}
			else
			{
				System.out.println("*** item " + input[x][0] + " not in inventory ***");
			}
	}
//}
	System.out.printf("\nTOTAL COST = $ %.2f \nTOTAL TAX = $ %.2f \nGRAND TOTAL = $ %.2f", (total), tax, (total + tax));
 }
	public static void printSimulation(int[][] input, int usedsize, GroceryItem[] inventory)
	{
		int productNumber = 0;
		int amount = 0;
		int search = 0;
		for (int x = 0;  !((input[x][0] == 0) && (input[x][1] == 0)); x++)
		{
			productNumber = input[x][0];
			amount = input[x][1]; 
			search = inventory[usedsize-1].itemSearch(inventory, usedsize, productNumber);
			if(search != -1)
			{
				inventory[search].setQuantity(amount);
			}
		}
			System.out.print(amount);
	}
	public static void quantityupdate(int[][] input, int usedsize, GroceryItem[] inventory)
	{
		
		for(int i = 0; i < usedsize; i++)
		{
		int productnum = input[i][0];
		  for(int q = 0; q < usedsize; q++)
		  if( productnum == inventory[q].getNumber() )
			{
		  		inventory[q].setQuantity(input[i][1]);
				
			}
		}
			
	}
}
