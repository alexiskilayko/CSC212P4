package edu.smith.cs.csc212.p4;

/**
 * This class is an extension of Exit that is secret.
 * @author alexiskilayko
 *
 */

public class SecretExit extends Exit {
	
	public SecretExit(String target, String description) {
		super(target, description);
		this.hidden = true; // Secret exit is initially hidden to user.
	}
	
	/**
	 * Ask if exit is hidden.
	 * Secret exits should return true at first.
	 */
	/*boolean isSecret() {
		return this.hidden;
	}*/

	@Override
	/**
	 * Allow user to search for secret exits.
	 * Once searched, secret exits become visible.
	 */
	void search() {
		this.hidden = false;
	}
}
