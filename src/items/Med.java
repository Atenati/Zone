/**
 * Author: Atenati Weber-Morrison
 * Date: April 7, 2020
 */

package items;

public class Med extends Equipment {

	private int capacity;
	private String type;
	
	/**
	 * create meds with name, how much they heal and how well
	 * @param name
	 * @param capacity
	 * @param type
	 */
	public Med(String name, int capacity, String type) {
		super(name);
		this.capacity = capacity;
		this.type = type;
	}
	
	/**
	 * return how well meds heal
	 * @return
	 */
	public int getCapacity() {
		return capacity;
	}
	
	/**
	 * return type of meds
	 * to be used later to display how well they work in inventory
	 * @return
	 */
	public String getType() {
		return type;
	}
	
	/* Set type of meds */
	public void setType(String toset) {
		this.type = toset;
	}
	
	/**
	 * reduce capacity of meds
	 * @param toset
	 */
	public void reduceCapacity(int toset) {
		this.capacity -= toset;
	}
	
	/**
	 * display debugging information about meds
	 */
	public String toString() {
		return "DEBUG: Med - " + this.getName() + " " + this.getCapacity() + " Heals";
	}

}
