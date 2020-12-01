/**
 * Author: Atenati Weber-Morrison
 * Date: April 7, 2020
 */

package items;

public class Helmet extends Equipment {

	private int helmetDefence;
	private boolean faceShield;
	
	/**
	 * Create helmet with name, value and if it has a faceshield
	 * @param name
	 * @param helmetDefence
	 * @param faceShield
	 */
	public Helmet(String name, int helmetDefence, boolean faceShield) {
		super(name);
		this.helmetDefence = helmetDefence;
		this.faceShield = faceShield;
	}
	
	/**
	 * return helmet value
	 * @return
	 */
	public int getHelmetDefence() {
		return helmetDefence;
	}
	
	/**
	 * set helmet value
	 * @param toset
	 */
	public void setHelmetDefence(int toset) {
		this.helmetDefence = toset;
	}
	
	/**
	 * get if helmet has faceshield
	 * @return
	 */
	public boolean getFaceShield() {
		return faceShield;
	}
	
	/**
	 * set helmet to have faceshield (or if it breaks)
	 * @param toset
	 */
	public void setFaceShield(boolean toset) {
		this.faceShield = toset;
	}
	
	/**
	 * Display debugging information about helmet
	 */
	public String toString() {
		return "DEBUG: Helmet - " + this.getName() + " " + this.getHelmetDefence() + " Defence";
	}
}
