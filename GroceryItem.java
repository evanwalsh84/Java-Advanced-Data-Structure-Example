/* 
Evan Walsh
3200
Section 1
*/
import java.io.*;
import java.util.*;

public class GroceryItem {
	private int productNum;
	private String name;
	private float price;
	private String tax;
	private int quantity;

	public GroceryItem() 
	{

	}

	// Returns the product number.
	public int getNumber() {
		return productNum;
	}

	// Returns the name of an item.
	public java.lang.String getName() {
		return name;
	}

	// Returns the price of an item.
	public double getPrice() {
		return price;
	}

	// Returns the tax code of the item.
	public String getTaxCode() {
		return tax;
	}
	//Reads in values from the connected text file.
	public void readItem(Scanner readFile) {
		productNum = readFile.nextInt();
		name = readFile.next();
		quantity = readFile.nextInt();
		price = readFile.nextFloat();
		tax = readFile.next();
		readFile.nextLine();
	}
	//Searches for the index of the input  product number.
	public int itemSearch(GroceryItem[] items, int used, int productNum) {
		for (int i = 0; i < used; i++) {
			if (productNum == items[i].getNumber()) {
				return i;
			}
		}
		return -1;
	}
	//Returns the quantity of the input product.
	public int getQuantity() {
		return quantity;
	}
	//Sets the new quantity for the input product.
	public void setQuantity(int value) {
		quantity = quantity - value;
	}
	//prints the inventory of the associated text file.
	public static void printInventory(GroceryItem[] items, int used) {
		for (int i = 0; i < used; i++) {
			System.out.print(items[i].getNumber());
			System.out.printf(" %s %d %.2f", items[i].getName(),
					items[i].getQuantity(), items[i].getPrice());
			System.out.print(items[i].getTaxCode() + "\n");
		}
	}
}
