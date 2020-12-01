/**
 * Author: Atenati Weber-Morrison
 * Date: April 7, 2020
 */

package people;

import java.util.ArrayList;
import javax.swing.ImageIcon;
import items.*;
import windows.Creation;

public class Player extends Character {

	private String faction; // player has faction
	private ImageIcon image; // player has image
	private Helmet helmet; // player has helmet
	private Weapon weapon; // player has weapon
	private Armour armour; // player has armour
	private ArrayList<Equipment> inventory; // player has an inventory
	private int invSize; // player has inventory size
	
	// player statistics
	private int attackstat;
	private int armourstat;
	private int speedstat;
	
	// player statistic modifiers
	private int attackmod;
	private int armourmod;
	private int speedmod;
	
	/**
	 * create player
	 * @param name
	 * @param helmet
	 * @param weapon
	 * @param armour
	 * @param faction
	 * @param attackmod
	 * @param armourmod
	 * @param speedmod
	 */
	public Player(String name, Helmet helmet, Weapon weapon, Armour armour, String faction, int attackmod, int armourmod, int speedmod) {
		super(name);
		this.helmet = helmet;
		this.weapon = weapon;
		this.armour = armour;
		this.faction = faction;
		this.attackmod = attackmod;
		this.armourmod = armourmod;
		this.speedmod = speedmod;
		inventory = new ArrayList<Equipment>(); // initialize inventory
		inventory.add(new Med("Salewa", 30, "High")); // give player starting meds
		inventory.add(weapon); // add players weapon to inventory
		inventory.add(helmet); // add players helmet to inventory
		if(faction.equals("USEC")) { // if player is usec
			image = new ImageIcon(Creation.class.getResource("/usecsmall.png")); // set usec image
			invSize = 5; // set small inventory
			attackstat = 70 + attackmod; // set high attack
			armourstat = 50 + armourmod; // set medium armour
			speedstat = 40 - speedmod; // set low speed
		} else if(faction.equals("Bear")) { // if player is bear
			image = new ImageIcon(Creation.class.getResource("/bearsmall.png")); // get bear image
			invSize = 10; // set large inventory
			attackstat = 40 + attackmod; // set low attack
			armourstat = 20 + armourmod; // set low armour
			speedstat = 70 - speedmod; // set high speed
		}
	}

	/*
	 * GAMELOGIC CHECK
	 * if attack with 30 (now becomes 60)
	 * our armour is 30 (now becomes 15)
	 * 60 - 15 = 45
	 * = we take 45 damage
	 *  = must take med
	 */
	/**
	 * player takes damage from external source (enemy)
	 * @param attack
	 */
	public void damage(int attack) {
		if(this.getHelmet().getFaceShield()) { // if has faceshield
			this.reduceHealth((attack * 2) - ((helmet.getHelmetDefence() / 2) + 5)); // reduce health by attack - half armour + 5
			this.getHelmet().setFaceShield(false); // faceshield breaks
		} else { // else doesn't have faceshield
			this.reduceHealth((attack * 2) - (helmet.getHelmetDefence() / 2)); // reduce health by attack - half armour
		}
		if(this.getHealth() <= 0) { // if player dies
			this.setHealth(0); // set to 0 (prevents negative numbers)
		}
	}
	
	/**
	 * if player chooses different weapon it will just add that ones stats to current stats
	 * this resets all stats back to the faction stats
	 * then adds the items modifiers
	 */
	public void resetFactionStatsWithEquipment() {
		if(this.faction.equals("USEC")) { // if usec
			this.attackstat = 70 + weapon.getDamage(); // use usec stats
			this.armourstat = 50 + helmet.getHelmetDefence();
			this.speedstat = 40 - this.speedmod;
		} else if(this.faction.equals("Bear")) { // if bear
			this.attackstat = 40 + weapon.getDamage(); // use bear stats
			this.armourstat = 20 + helmet.getHelmetDefence();
			this.speedstat = 70 - this.speedmod;;
		}
	}
	
	/**
	 * return player faction/type
	 * @return
	 */
	public String getFaction() {
		return faction;
	}
	
	/**
	 * return player image
	 * @return
	 */
	public ImageIcon getImage() {
		return image;
	}
	
	/**
	 * return player helmet
	 * @return
	 */
	public Helmet getHelmet() {
		return helmet;
	}
	
	/**
	 * set player helmet
	 * @param toset
	 */
	public void setHelmet(Helmet toset) {
		this.helmet = toset;
	}
	
	/**
	 * get player weapon
	 * @return
	 */
	public Weapon getWeapon() {
		return weapon;
	}
	
	/**
	 * set player weapon
	 * @param toset
	 */
	public void setWeapon(Weapon toset) {
		this.weapon = toset;
	}
	
	/**
	 * get player amour
	 * @return
	 */
	public Armour getArmour() {
		return armour;
	}
	
	/**
	 * set player armour
	 * @param toset
	 */
	public void setArmour(Armour toset) {
		this.armour = toset;
	}
	
	/**
	 * get players attack stat
	 * @return
	 */
	public int getAttackStat() {
		return attackstat;
	}
	
	/**
	 * get players armour stat
	 * @return
	 */
	public int getArmourStat() {
		return armourstat;
	}
	
	/**
	 * get players speed stat
	 * @return
	 */
	public int getSpeed() {
		return speedstat;
	}
	
	/**
	 * return players entire arraylist inventory
	 * @return
	 */
	public ArrayList<Equipment> getInventory() {
		return inventory;
	}
}
