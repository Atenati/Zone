/**
 * Author: Atenati Weber-Morrison
 * Date: April 7, 2020
 */

package items;

public class Armour extends Equipment {

	private int armourDefence;
	private int covers;
	
	public Armour(String name, int armourDefence, int covers) {
		super(name);
		this.armourDefence = armourDefence;
		this.covers = covers;
	}
	
	/**
	 * return armour value
	 * @return
	 */
	public int getArmourDefence() {
		return armourDefence;
	}
	
	/**
	 * set armour value
	 * @param toset
	 */
	public void setArmourDefence(int toset) {
		this.armourDefence = toset;
	}
	
	/**
	 * return how well armour works
	 * @return
	 */
	public int covers() {
		return covers;
	}
	
	/**
	 * set how well armour works (covers)
	 * @param toset
	 */
	public void setCovers(int toset) {
		this.covers = toset;
	}
	
}
