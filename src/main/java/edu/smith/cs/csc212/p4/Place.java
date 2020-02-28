package edu.smith.cs.csc212.p4;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * This represents a place in our text adventure.
 * @author jfoley
 *
 */
public class Place {
	/**
	 * This is a list of places we can get to from this place.
	 */
	public List<Exit> exits;
	/**
	 * This is a list of exits the user can always see.
	 */
	private List<Exit> visibleExits;
	/**
	 * This is a list of exits that are hidden to the user at first.
	 */
	private List<Exit> secretExits;
	/**
	 * This is a list of items contained in a place.
	 */
	public List<String> items;
	/**
	 * This is a boolean that asks whether or not a user has taken the place's items.
	 */
	public boolean itemsTaken;
	/**
	 * This is the identifier of the place.
	 */
	private String id;
	/**
	 * What to tell the user about this place.
	 */
	private String description;
	/**
	 * Whether reaching this place ends the game.
	 */
	private boolean terminal;
		
	/**
	 * Internal only constructor for Place. Use {@link #create(String, String)} or {@link #terminal(String, String)} instead.
	 * @param id - the internal id of this place.
	 * @param description - the user-facing description of the place.
	 * @param terminal - whether this place ends the game.
	 */
	private Place(String id, String description, List items, boolean terminal) {
		this.id = id;
		this.description = description;
		this.exits = new ArrayList<>();
		this.visibleExits = new ArrayList<>();
		this.secretExits = new ArrayList<>();
		this.items = items;
		this.itemsTaken = false;
		this.terminal = terminal;
	}
	
	/**
	 * Create an exit for the user to navigate to another Place.
	 * @param exit - the description and target of the other Place.
	 */
	public void addExit(Exit exit) {
		this.exits.add(exit);
		if (exit.isSecret() == false) {
			this.visibleExits.add(exit);
		} else if (exit.isSecret() == true) {
			this.secretExits.add(exit);
		}
	}
	
	/**
	 * For gameplay, whether this place ends the game.
	 * @return true if this is the end.
	 */
	public boolean isTerminalState() {
		return this.terminal;
	}
	
	/**
	 * The internal id of this place, for referring to it in {@link Exit} objects.
	 * @return the id.
	 */
	public String getId() {
		return this.id;
	}
	
	/**
	 * The narrative description of this place.
	 * @return what we show to a player about this place.
	 */
	public String getDescription() {
		return this.description;
	}
	
	/**
	 * Print the place description as well as the place items.
	 */
	public void printDescription() {
		System.out.println(this.description);
		// A place does not have any items.
		if (this.items == null) {
			System.out.println("ITEMS: There are no items.");
		}  // A place has items and they have not been taken yet.
		if ((this.items != null) && (this.itemsTaken == false)) {
			System.out.println("ITEMS: "+this.items);
		}
	}

	/**
	 * Allow the player to take the items in a place. Add items to their inventory.
	 */
	public void takeItems() {
		if (this.items == null) {
			System.out.println("There are no items to take.");
		} else {
			this.itemsTaken = true;
			for (String item : this.items) {
				Player.inventory.add(item);
			}
		}
	}
	
	/**
	 * Get a view of the visible exits from this Place, for navigation.
	 * @return all the visible exits from this place.
	 */
	public List<Exit> getVisibleExits() {
		return this.visibleExits;
	}
	
	/**
	 * @return all the secret exits from this place.
	 */
	public List<Exit> getSecretExits() {
		return this.secretExits;
	}
	
	/**
	 * This is a terminal location (good or bad).
	 * @param id - this is the id of the place (for creating {@link Exit} objects that go here).
	 * @param description - this is the description of the place.
	 * @return the Place object.
	 */
	public static Place terminal(String id, String description, List items) {
		return new Place(id, description, items, true);
	}
	
	/**
	 * Create a place with an id and description.
	 * @param id - this is the id of the place (for creating {@link Exit} objects that go here).
	 * @param description - this is what we show to the user.
	 * @return the new Place object (add exits to it).
	 */
	public static Place create(String id, String description, List items) {
		return new Place(id, description, items, false);
	}
		
	/**
	 * Implements what we need to put Place in a HashSet or HashMap.
	 */
	public int hashCode() {
		return this.id.hashCode();
	}
	
	/**
	 * Give a string for debugging what place is what.
	 */
	public String toString() {
		return "Place("+this.id+" with "+this.exits.size()+" exits.)";
	}
	
	/**
	 * Whether this is the same place as another.
	 */
	public boolean equals(Object other) {
		if (other instanceof Place) {
			return this.id.equals(((Place) other).id);
		}
		return false;
	}
		
}
