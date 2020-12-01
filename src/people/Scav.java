/**
 * Author: Atenati Weber-Morrison
 * Date: April 7, 2020
 */

package people;

public class Scav extends Enemy {

	//initialize scav stats
	private int level;
	private int scavAttack = 20;
	private int scavArmour = 10;
	
	/**
	 * create scav
	 * @param name
	 * @param type
	 * @param level
	 */
	public Scav(String name, String type, int level) {
		super(name, type);
		this.level = level;
		if(level == 1) { // if scav is level 1
			scavAttack += 2; // set to easy stats
			scavArmour += 2;
		} else if(level == 2) { // if scav level 2
			scavAttack += 5; // set to hard stats
			scavArmour += 5;
		}
		this.setupEnemy(); // generate inventory from random loot pools
	}
	
	/**
	 * handle scav taking damage
	 * @param attack
	 */
	public void damage(int attack) {
		this.reduceHealth(attack - (getScavArmour() / 3)); // scav takes damage - 1 third of armour
		if(this.getHealth() <= 0) { // if scav dies
			this.setHealth(0); // reset to 0 to prevent negative numbers
		}
	}
	
	/**
	 * get scavs attack power
	 * @return
	 */
	public int getScavAttack() {
		return scavAttack;
	}
	
	/**
	 * get scavs armour defence
	 * @return
	 */
	public int getScavArmour() {
		return scavArmour;
	}
	
	/**
	 * get scavs level
	 * @return
	 */
	public int getLevel() {
		return level;
	}
}
