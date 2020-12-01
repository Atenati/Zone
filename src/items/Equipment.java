/**
 * Author: Atenati Weber-Morrison
 * Date: April 7, 2020
 */

package items;

public abstract class Equipment {

	private String name;
	
	/**
	 * create equipment with param name
	 * @param name
	 */
	public Equipment(String name) {
		this.name = name;
	}
	
	/**
	 * get equipment name
	 * @return
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * set equipment name
	 * @param toset
	 */
	public void setName(String toset) {
		this.name = toset;
	}
}
