/**
 * Author: Atenati Weber-Morrison
 * Date: April 7, 2020
 */

package people;

public abstract class Character {

	// default character values, has name and 100 health
	private int health = 100; // every character starts with 100 health
	private String name;
	
	/**
	 * all characters have name
	 * @param name
	 */
	public Character(String name) {
		this.name = name;
	}
	
	/**
	 * get health (check if dead)
	 * @return
	 */
	public int getHealth() {
		return health;
	}
	
	/**
	 * add health (use meds)
	 * @param toadd
	 */
	public void addHealth(int toadd) {
		this.health += toadd;
	}
	
	/**
	 * set health (if falls below 0, reset to 0)
	 * @param toset
	 */
	public void setHealth(int toset) {
		this.health = toset;
	}
	
	/**
	 * reduce health (from damage)
	 * @param toremove
	 * @return
	 */
	public int reduceHealth(int toremove) {
		this.health -= toremove;
		return health;
	}
	
	/**
	 * get character name
	 * @return
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * set character name
	 * @param toset
	 */
	public void setName(String toset) {
		this.name = toset;
	}
}
