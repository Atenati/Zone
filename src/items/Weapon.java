/**
 * Author: Atenati Weber-Morrison
 * Date: April 7, 2020
 */

package items;

public class Weapon extends Equipment {

	private int damage;
	private String type;
	
	/**
	 * create weapon with name and attack power
	 * @param name
	 * @param damage
	 */
	public Weapon(String name, int damage) {
		super(name);
		this.damage = damage;
	}
	
	/**
	 * get weapons damage value
	 * @return
	 */
	public int getDamage() {
		return damage;
	}
	
	/**
	 * set weapons damage value
	 * @param toset
	 */
	public void setDamage(int toset) {
		this.damage = toset;
	}
	
	/**
	 * set weapons type
	 * @param toset
	 */
	public void setType(String toset) {
		this.type = toset;
	}
	
	/**
	 * weapons debugging string
	 */
	public String toString() {
		return "DEBUG: Weapon - " + this.getName() + " " + damage + " Damage";
	}

}
