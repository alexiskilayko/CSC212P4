package edu.smith.cs.csc212.p4;

import java.util.List;

/**
 * This is our main class for P4. It interacts with a GameWorld and handles user-input.
 * @author jfoley
 *
 */
public class InteractiveFiction {

	/**
	 * This is where we play the game.
	 * @param args
	 */
	
	public static void main(String[] args) {
		// This is a text input source (provides getUserWords() and confirm()).
		TextInput input = TextInput.fromArgs(args);

		// This is the game we're playing.
		GameWorld game = new PokemonAdventure();
		
		// This is the current location of the player (initialize as start).
		// Maybe we'll expand this to a Player object.
		String place = game.getStart();
		
		// This is the time for our game.
		GameTime time = new GameTime();
		
		// This is our player.
		Player player = new Player();

		// Play the game until quitting.
		// This is too hard to express here, so we just use an infinite loop with breaks.
		while (true) {
			// Print the description of where you are.
			Place here = game.getPlace(place);
			here.printDescription();
			// Print items player currently has.
			player.printInventory();
			// Print the time.
			time.printCurrentHour();
				
			// Game over after print!
			if (here.isTerminalState()) {
				break;
			}
			
			// Hidden ways out of this place.
			List<Exit> secretExits = here.getSecretExits();
			
			// Show a user the ways out of this place.
			List<Exit> visibleExits = here.getVisibleExits();			
			
			for (int i=0; i<visibleExits.size(); i++) {
			    Exit e = visibleExits.get(i);
				System.out.println(" ["+i+"] " + e.getDescription());
			}

			// Figure out what the user wants to do, for now, only "quit" is special.
			List<String> words = input.getUserWords(">");
			if (words.size() == 0) {
				System.out.println("Must type something!");
				continue;
			} else if (words.size() > 1) {
				System.out.println("Only give me 1 word at a time!");
				continue;
			}
			
			// Get the word they typed as lowercase, and no spaces.
			String action = words.get(0).toLowerCase().trim();
			
			if ((action.equals("quit")) || (action.equals("q")) || (action.equals("escape"))) { // User wants to quit.
				if (input.confirm("Are you sure you want to quit?")) {
					break;
				} else {
					continue;
				}
			}  else if (action.equals("search")) { // User wants secret exits to be revealed.
				for (Exit e : secretExits) {
					visibleExits.add(e); // Make the place's secret exits visible.
				} continue;
			}  else if (action.equals("take")) { // User wants to take place items.
				here.takeItems();
				continue;
			}
			
			// From here on out, what they typed better be a number!
			Integer exitNum = null;
			try {
				exitNum = Integer.parseInt(action);
			} catch (NumberFormatException nfe) {
				System.out.println("That's not something I understand! Try a number!");
				continue;
			}
			
			if (exitNum < 0 || exitNum > visibleExits.size()) {
				System.out.println("I don't know what to do with that number!");
				continue;
			}

			// Move to the room they indicated.
			Exit destination = visibleExits.get(exitNum);
			// If user has unlockable, unlock lockedExit; otherwise, do nothing.
			destination.unlock();
			if (destination.locked == false) { // Allow user to enter place if door has no lock or is unlocked.
				place = destination.getTarget();
			} else { // Forbid user from entering if they do not have unlockable.
				System.out.println("This area is not yet accessible. Maybe you need an item?");
			}
			
			// Increment hour with every move by player.
			time.increaseHour();
		}

		// You get here by "quit" or by reaching a Terminal Place.
		System.out.println(">>> GAME OVER <<<");
		// Print total hours spent in game.
		time.printTotalHours();
	}

}
