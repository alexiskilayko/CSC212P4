package edu.smith.cs.csc212.p4;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * A Pokemon text adventure game!
 * @author alexiskilayko
 *
 */
public class PokemonAdventure implements GameWorld {
	
	private Map<String, Place> places = new HashMap<>();
	
	// Place where game starts.
	public String getStart() {
		return "palletTown";
	}
	
	/**
	 * This constructor builds our Pokemon adventure game.
	 */
	public PokemonAdventure() {
		
		System.out.println("Hello there! Welcome to the world of Pokemon! \n"
				+ "Your very own Pokemon legend is about to unfold. A world of dreams and adventures with Pokemon awaits. \n"
				+ "Let's go!");
		
		Place palletTown = insert(Place.create("palletTown", "You stand in front of your house in Pallet Town, soaking in your new surroundings. It's small and quiet, but pretty.",
				null));
		palletTown.addExit(new Exit("playerHouse", "Enter your house."));
		palletTown.addExit(new Exit("oaksLab", "Enter the curious laboratory across from you."));
		palletTown.addExit(new LockedExit("route1", "Take the path ahead.", "Pikachu"));
		
		Place playerHouse = insert(Place.create("playerHouse", "As you enter your living room, your mom calls out to you from the kitchen, asking if you've gone to see Professor Oak.",
				Arrays.asList("Potion")));
		playerHouse.addExit(new Exit("palletTown", "Exit the door behind you."));
		
		Place oaksLab = insert(Place.create("oaksLab", "PokeBalls line the shelves behind Professor Oak, and fancy lab technology is in every corner of the room.",
				Arrays.asList("Pikachu")));
		oaksLab.addExit(new Exit("palletTown", "Exit the door behind you."));
		
		Place route1 = insert(Place.create("route1", "The grass ahead of you on Route 1 rustles. You can hear faint sounds - perhaps some wild Pokemon?", 
				null));
		route1.addExit(new Exit("palletTown", "Go back."));
		route1.addExit(new Exit("viridianCity", "Go forward."));
		
		Place viridianCity = insert(Place.create("viridianCity", "You are amazed by the evergreen trees that you've heard Viridian City is famous for.", 
				Arrays.asList("Gym Badge")));
		viridianCity.addExit(new Exit("route1", "Go back."));
		viridianCity.addExit(new Exit("route2", "Go forward."));
		viridianCity.addExit(new Exit("route22", "Take a left."));
		
		Place route2 = insert(Place.create("route2", "More trees and more wild Pokemon abound on Route 2.", null));
		route2.addExit(new Exit("viridianCity", "Return to the city."));
		route2.addExit(new SecretExit("diglettCave", "Investigate the cave."));
		
		Place diglettCave = insert(Place.create("diglettCave", "The cave is dark. Every now and then you run into wild Diglett.", null));
		diglettCave.addExit(new Exit("route2", "Return to the route."));
		
		Place route22 = insert(Place.create("route22", "Several hills lie before you. It looks like a long but important trek ahead.", null));
		route22.addExit(new Exit("route2", "Go back."));
		route22.addExit(new LockedExit("pkmnLeague", "Climb up the hills.", "Gym Badge"));
		
		Place pkmnLeague = insert(Place.create("pkmnLeague", "After several hills, you find yourself in front of the Pokemon League.", null));
		pkmnLeague.addExit(new Exit("route22", "Go back."));
		pkmnLeague.addExit(new Exit("eliteLorelei", "Enter the Pokemon League."));
		
		Place eliteLorelei = insert(Place.create("eliteLorelei", "The first of the Elite Four, Lorelei, nearly freezes you in place you with Ice-type Pokemon.", null));
		eliteLorelei.addExit(new Exit("eliteBruno", "Advance forward."));
		
		Place eliteBruno = insert(Place.create("eliteBruno", "The second of the Elite Four, Bruno, puts up a fierce fight with Fighting-type Pokemon.", null));
		eliteBruno.addExit(new Exit("eliteAgatha", "Advance forward."));
		
		Place eliteAgatha = insert(Place.create("eliteAgatha", "The third of the Elite Four, Agatha, sends chills down your spine with Ghost-type Pokemon.", null));
		eliteAgatha.addExit(new Exit("eliteLance", "Advance forward."));
		
		Place eliteLance = insert(Place.create("eliteLance", "The final of the Elite Four, Lance, proved a valiant oponent with Dragon-type Pokemon.", null));
		eliteLance.addExit(new Exit("championBlue", "Advance foward."));
		
		// Place where game terminates.
		Place championBlue = insert(Place.terminal("championBlue", "Finally you defeat the Champion Blue. You're the new Pokemon Champion!", null));
		
	}
	
	/**
	 *  Insert method taken from Professor Foley.
	 */
	private Place insert(Place p) {
		places.put(p.getId(), p);
		return p;
	}
	
	/**
	 * Get a Place object by name.
	 */
	public Place getPlace(String id) {
		return this.places.get(id);
	}

}
