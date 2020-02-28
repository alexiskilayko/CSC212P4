package edu.smith.cs.csc212.p4;

/**
 * This class is an extension of Exit that is locked at first.
 * @author alexiskilayko
 *
 */
public class LockedExit extends Exit {
	
	/**
	 * Item that is needed to unlock the locked exit.
	 */
	String unlockable;
	
	public LockedExit(String target, String description, String unlockable) {
		super(target, description);
		this.unlockable = unlockable;
		this.locked = true; // Locked exit is initially locked from user.

	}
	
	/**
	 * Allow user to unlock a locked exit if they have the proper item to unlock.
	 */
	public void Unlock() {
		if (Player.inventory.contains(this.unlockable)) {
			this.locked = false;
		}
	}

}