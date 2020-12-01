/**
 * Author: Atenati Weber-Morrison
 * Date: April 7, 2020
 */

package people;

import java.util.ArrayList;
import java.util.Random;
import javax.swing.ImageIcon;
import items.*;
import windows.Creation;

public abstract class Enemy extends Character {

	private Random rand = new Random(); // used for choosing random items for inventory
	private ImageIcon image; // image
	private ArrayList<Equipment> loot; // enemy loot (inventory)
	private ArrayList<Equipment> weaponpool; // loot pool of possible weapons
	private ArrayList<Equipment> helmetpool; // loot pool of possible helmets
	private ArrayList<Equipment> medpool; // loot pool of possible meds
	private String type; // type of enemy
	
	/**
	 * create enemy with name and type
	 * @param name
	 * @param type
	 */
	public Enemy(String name, String type) {
		super(name);
		this.type = type;
	}
	
	/**
	 * setup enemies loot pools and inventory and image
	 */
	public void setupEnemy() {
		// initialize inventory, and loot pools
		loot = new ArrayList<Equipment>();
		weaponpool = new ArrayList<Equipment>();
		helmetpool = new ArrayList<Equipment>();
		medpool = new ArrayList<Equipment>();
		generatePools(); // fill with items
		addToLoot(getWeapon()); // add weapon to inventory
		addToLoot(getHelmet()); // add helmet to inventory
		addToLoot(getMed()); // add meds to inventory
		if(type.equals("Scav")) { // if is scav
			image = new ImageIcon(Creation.class.getResource("/scav.png")); // use scav image
		}else if(type.equals("Raider")) { // if is raider
			image = new ImageIcon(Creation.class.getResource("/raider.png")); // use raider image
		}
	}
	
	/**
	 * create all possible items in game, add to arraylists to contain them
	 */
	public void generatePools() {
		weaponpool.add(new Weapon("DVL", 10));
		weaponpool.add(new Weapon("ASVAL", 6));
		weaponpool.add(new Weapon("VSS", 4));
		weaponpool.add(new Weapon("RSASS", 8));
		
		helmetpool.add(new Helmet("FASTMT", 25, false));
		helmetpool.add(new Helmet("ALTYN", 35, true));
		helmetpool.add(new Helmet("KIVER", 20, true));
		helmetpool.add(new Helmet("RATNIK", 10, false));

		medpool.add(new Med("Bandage", 10, "Low"));
		medpool.add(new Med("IFAK", 20, "Medium"));
		medpool.add(new Med("Salewa", 30, "High"));
		medpool.add(new Med("Grizzly", 40, "Extreme"));
	}
	
	/**
	 * get image of enemy
	 * @return
	 */
	public ImageIcon getImage() {
		return image;
	}
	
	/**
	 * get random weapon
	 * @return
	 */
	public Weapon getWeapon() {
		int index = rand.nextInt(4);
		return (Weapon) weaponpool.get(index);
	}
	
	/**
	 * return random helmet
	 * @return
	 */
	public Helmet getHelmet() {
		int index = rand.nextInt(4);
		return (Helmet) helmetpool.get(index);
	}
	
	/**
	 * return random med
	 * @return
	 */
	public Med getMed() {
		int index = rand.nextInt(4);
		return (Med) medpool.get(index);
	}
	
	/**
	 * remove item from enemy
	 * @param e
	 */
	public void removeFromLoot(Equipment e) {
		loot.remove(e);
	}
	
	/**
	 * add item to enemies inventory
	 * @param e
	 */
	public void addToLoot(Equipment e) {
		loot.add(e);
	}
	
	/**
	 * get enemies entire arraylist of items
	 * @return
	 */
	public ArrayList<Equipment> getLoot() {
		return loot;
	}

}
