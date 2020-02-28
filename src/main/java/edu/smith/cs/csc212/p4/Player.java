package edu.smith.cs.csc212.p4;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * This is a class for our player.
 * @author alexiskilayko
 *
 */
public class Player {

	/**
	 * This is a list of the items that a user is currently carrying.
	 */
	public static List<String> inventory;
	
	public Player() {
		this.inventory = new ArrayList<>();
	}
	
	/**
	 * Print the user's items if they have anything, or let them know if they have nothing.
	 */
	public void printInventory() {
		if (this.inventory.isEmpty() == true) {
			System.out.println("INVENTORY: You have nothing.");
		} else {
			System.out.println("INVENTORY: " + this.inventory);
		}
	}
}
