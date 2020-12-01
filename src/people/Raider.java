/**
 * Author: Atenati Weber-Morrison
 * Date: April 7, 2020
 */

package people;

import java.util.Random;

public class Raider extends Enemy {

	// initialize raider stats
	private int bonus;
	private Random rand = new Random();
	private int raiderAttack = 20;
	private int raiderArmour = 30;
	
	/**
	 * create a raider object
	 * @param name
	 * @param type
	 * @param bonus
	 */
	public Raider(String name, String type, int bonus) {
		super(name, type);
		this.bonus = bonus;
		this.setupEnemy(); // setup inventory with random items from loot pools
		
		int chance = rand.nextInt(2); // 1 or 0
		if(chance == 1) { // 50% chance
			raiderArmour += bonus; // raider takes less damage
		}
	}
	
	/**
	 * damage raider
	 */
	public void damage(int attack) {
		this.reduceHealth(attack - (getRaidArmour() / 3)); // raider damages by attack - 1 third of armour (more than player to make it easy)
		if(this.getHealth() <= 0) { // if raider dies
			this.setHealth(0); // reset to 0 (prevent negative numbers)
		}
	}
	
	/**
	 * get raiders attack power
	 * @return
	 */
	public int getRaidAttack() {
		return raiderAttack;
	}
	
	/**
	 * get raideres armour defence
	 * @return
	 */
	public int getRaidArmour() {
		return raiderArmour;
	}
}
