package edu.smith.cs.csc212.p4;

/**
 * This is a class for keeping track of time in our game.
 * @author alexiskilayko
 *
 */
public class GameTime {
	/**
	 * This is the value for the current time of game "clock."
	 */
	int currentHour;
	/**
	 * This is the total amount of "hours" a player has played in the game.
	 */
	int totalHour;
	
	public GameTime() {
		// Initialize current and total hours at 0.
		this.currentHour = 0;	
		this.totalHour = 0;
	}
	
	/**
	 * Print the current time in-game.
	 */
	public void printCurrentHour() {
		System.out.println("TIME: " + this.currentHour + ":00");
	}
	
	/**
	 * Print total hours player has played.
	 */
	public void printTotalHours() {
		System.out.println("TOTAL HOURS: " + this.totalHour);
	}
	
	/**
	 * Method for increasing current and total hours.
	 */
	public void increaseHour() {
		// Modular clock system; increase hour until 23 then restart at 0.
		if (this.currentHour < 24) {
			this.currentHour += 1;
		} else if (this.currentHour == 24) {
			this.currentHour = 0;
		}
		// Increase total hours.
		this.totalHour += 1;
	}

}
