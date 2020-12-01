/**
 * Author: Atenati Weber-Morrison
 * Date: April 7, 2020
 */

package engine;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import items.*;
import people.*;
import windows.*;

public class Game extends JFrame {

	/* Declare variables to use for entire game */
	private Player player;
	private Scav scav;
	private Raider raid;
	private Creation create;
	private End end;
	private int selected = 1; // which button is selected in inventory
	private static int phase = 1; // represents which stage of the zone you are in
	
	/**
	 * Main method for program
	 * Also the JFrame that contains all JPanels of prorgam
	 * handles switching JPanels, game objects and all game logic
	 */
	public Game() {
		end = new End(); // initialize ending
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // exit program on close
		this.setVisible(true); // show frame (entire object)
		this.setMinimumSize(new Dimension(700, 700)); // set frame size
		this.setLocationRelativeTo(null); // center to screen
		this.setResizable(false); // cannot shrink
		setTitle("The Zone"); // set frame title

		// initialize jpanel objects
		SplashScreen splash = new SplashScreen(); // initialize splash screen first
		create = new Creation(); // initialize creation screen

		// setup cards (layouts)
		setLayout(new CardLayout()); // set to card layout to allow switching between JPanels easily
		add(splash); // add splash screen first
		add(create); // add creation screen

		// handle first button to exit splashscreen
		JButton splashPlay = splash.getPlayButton(); // spash screen button to start game
		splashPlay.addActionListener(new ActionListener() { // when clicked
			public void actionPerformed(ActionEvent e) {
				splash.setVisible(false); // hide splash screen jpanel
				create.setVisible(true); // replace with creation jpanel
			}
		});

		// handle entering zone button from create jpanel
		JButton createEnter = create.getEnterButton(); // when the button is clicked to enter the zone
		createEnter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				create.setVisible(false); // hide creation screen
				/* Create players */
				player = new Player(create.getPlayerName(), create.getHelmet(), create.getWeapon(), create.getArmour(), create.getFaction(), create.getAttackMod(), create.getArmourMod(), create.getSpeedMod());
				
				/* Create enemies */
				scav = new Scav("Scavmosisjones", "Scav", 1);
				raid = new Raider("Gluhar", "Raider", 10);
				
				/*
				 * Divide gameplay between this (creating player) and rest of game
				 * Also contain rest of program in second main method to control flow and prevent conflicts between instantiations
				 */
				enterZone(); // Move to another method to handle game logic
			}
		});
	}
	
	/**
	 * return current game phase
	 * @return
	 */
	public static int getPhase() {
		return phase;
	}
	
	/**
	 * When the player dies or wins
	 */
	public void theEnd() {
		add(end); // add previously instantiated end jpanel to frame
		end.setVisible(true); // set to visible
	}
	
	/**
	 * Second half of program
	 * Contains main screen "zone" and all instantions/objects for this screen
	 * handles all buttons inside zone (including player/enemy inventories)
	 */
	public void enterZone() {
		Zone zone = new Zone(); // intialize zone
		CharacterInventory charinv = new CharacterInventory(); // initialize player inventory
		
		/* upon instantiation inventories are filled with random loot */
		EnemyInventory scavinv = new EnemyInventory(); // initialize first enemy inventory
		EnemyInventory raidinv = new EnemyInventory(); // initialize second enemy inventory
		charinv.createButtons(); // create buttons in player inventory (add to array to handle which is clicked and what it references
		
		/* reset inventory re-displays all objects in player inventory as buttons (like a refresh) */
		charinv.resetInventory(player.getInventory());
		scavinv.resetInventory(scav.getLoot()); // sets buttons to item names in enemy inventory
		raidinv.resetInventory(raid.getLoot());
		zone.setPlayerStats(player); // fill labels with players stats
		zone.setEnemyStats(scav); // fill labels with enemies stats
		add(zone); // add zone to frame
		zone.setVisible(true); // show zone (everything setup, ready for playing)

		/* 
		 * Next button, leads to next enemy and end screen
		 * can only be clicked once first enemy is destroyed
		 * only relates to second enemy and end screen 
		 */
		JButton next = zone.getNext();
		next.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(phase < 2) { // if not second enemy
					next.setEnabled(false); // disable button
					zone.getEngage().setEnabled(true); // allow player to engage enemy
					zone.getScavInv().setEnabled(false); // disable enemy inventory (not dead yet)
					scav = null; // delete first enemy
					phase++; // increase phase (to 2)
					zone.setEnemyStats(raid); // set zone label stats to next enemy
				} else { // else 2 enemies have been defeated
					zone.setVisible(false); // hide the zone
					end.setMessage("You Win!"); // set message to win!
					theEnd(); // handle winning jpanel
				}
			}
		});
		
		/* First item button in first enemy inventory */
		JButton scavone = scavinv.getOne(); // get button from enemy inventory frame
		scavone.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { // when clicked
				if(player.getFaction().equals("USEC")) { // if play is usec
					if(player.getInventory().size() <= 4) { // if invventory has open slots
						scavone.setEnabled(false); // disable item clicked 
						Equipment eqf = scav.getLoot().get(0); // get which item it was from enemies inventory
						player.getInventory().add(eqf); // add that item to players inventory
						charinv.resetInventory(player.getInventory()); // refresh player inventory (displaying updated inventory)
					}
				} else { // else player is not usec (limited to 5 inventory slots)
					scavone.setEnabled(false); // disable button when clicked
					Equipment eqf = scav.getLoot().get(0); // get item from enemy inventory
					player.getInventory().add(eqf); // add to player inventory
					charinv.resetInventory(player.getInventory()); // update player inventory
				}
			}
		});
		
		/* Second item button in first enemy inventory */
		JButton scavtwo = scavinv.getTwo();
		scavtwo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(player.getFaction().equals("USEC")) {
					if(player.getInventory().size() <= 4) {
						scavtwo.setEnabled(false);
						Equipment eqf = scav.getLoot().get(1);
						player.getInventory().add(eqf);
						charinv.resetInventory(player.getInventory());
					}
				} else {
					scavtwo.setEnabled(false);
					Equipment eqf = scav.getLoot().get(1);
					player.getInventory().add(eqf);
					charinv.resetInventory(player.getInventory());
				}
			}
		});
		
		/* third item button in first enemy inventory */
		JButton scavthree = scavinv.getThree();
		scavthree.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(player.getFaction().equals("USEC")) {
					if(player.getInventory().size() <= 4) {
						scavthree.setEnabled(false);
						Equipment eqf = scav.getLoot().get(2);
						player.getInventory().add(eqf);
						charinv.resetInventory(player.getInventory());
					}
				} else {
						scavthree.setEnabled(false);
						Equipment eqf = scav.getLoot().get(2);
						player.getInventory().add(eqf);
						charinv.resetInventory(player.getInventory());
					}
			}
		});
		
		/* first item button in second enemy inventory */
		JButton raidone = raidinv.getOne(); // get button from enemy inventory jframe
		raidone.addActionListener(new ActionListener() { // listen for click
			public void actionPerformed(ActionEvent e) { // when clicked
				if(player.getFaction().equals("USEC")) { // if player is usec (5 inventory slots)
					if(player.getInventory().size() <= 4) { // if inventory not full
						raidone.setEnabled(false); // disable button
						Equipment eqf = raid.getLoot().get(0); // get which item was clicked
						player.getInventory().add(eqf); // add that item to player inventory
						charinv.resetInventory(player.getInventory()); // update player inventory
					}
				} else { // else player is Bear, can have 10 items
						raidone.setEnabled(false); // disable button
						Equipment eqf = raid.getLoot().get(0); // get item clicked
						player.getInventory().add(eqf); // add to player inventory
						charinv.resetInventory(player.getInventory()); // update player inventory
					}
			}
		});
		
		/* second item button in second enemy inventory */
		JButton raidtwo = raidinv.getTwo();
		raidtwo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(player.getFaction().equals("USEC")) {
					if(player.getInventory().size() <= 4) {
						raidtwo.setEnabled(false);
						Equipment eqf = raid.getLoot().get(1);
						player.getInventory().add(eqf);
						charinv.resetInventory(player.getInventory());
					}
				} else {
						raidtwo.setEnabled(false);
						Equipment eqf = raid.getLoot().get(1);
						player.getInventory().add(eqf);
						charinv.resetInventory(player.getInventory());
					}
			}
		});
		
		/* third item button in second enemy inventory */
		JButton raidthree = raidinv.getThree();
		raidthree.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(player.getFaction().equals("USEC")) {
					if(player.getInventory().size() <= 4) {
						raidthree.setEnabled(false);
						Equipment eqf = raid.getLoot().get(2);
						player.getInventory().add(eqf);
						charinv.resetInventory(player.getInventory());
					}
				} else {
						raidthree.setEnabled(false);
						Equipment eqf = raid.getLoot().get(2);
						player.getInventory().add(eqf);
						charinv.resetInventory(player.getInventory());
					}
			}
		});
		
		/* Below are handling player inventory buttons */
		/* first item button in player inventory */
		JButton one = charinv.getOne(); // get button from player inventory jframe
		one.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { // when is clicked
				selected = 1; // set currently selected (relates to what is clicked and what is in inventory)
				charinv.setSelectedText(one.getText()); // set label text so user knows its selected
			}
		});
		JButton two = charinv.getTwo();
		two.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selected = 2; // this is to link the button order to inventory item order
				charinv.setSelectedText(two.getText());
			}
		});
		JButton three = charinv.getThree();
		three.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selected = 3; // button 3 matches third item in player inventory
				charinv.setSelectedText(three.getText());
			}
		});
		JButton four = charinv.getFour();
		four.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selected = 4; // so if fourth button is clicked we know to handle fourth item in inventory
				charinv.setSelectedText(four.getText());
			}
		});
		JButton five = charinv.getFive();
		five.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selected = 5;
				charinv.setSelectedText(five.getText());
			}
		});
		JButton six = charinv.getSix();
		six.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selected = 6;
				charinv.setSelectedText(six.getText());
			}
		});
		JButton seven = charinv.getSeven();
		seven.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selected = 7;
				charinv.setSelectedText(seven.getText());
			}
		});
		JButton eight = charinv.getEight();
		eight.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selected = 8;
				charinv.setSelectedText(eight.getText());
			}
		});
		JButton nine = charinv.getNine();
		nine.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selected = 9;
				charinv.setSelectedText(nine.getText());
			}
		});
		JButton ten = charinv.getTen();
		ten.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selected = 10;
				charinv.setSelectedText(ten.getText());
			}
		});
		
		/* Use Button in character inventory */
		JButton use = charinv.getUse(); // get use button from player inventory frame
		use.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { // when clicked
				String item = (charinv.getButtonName(selected - 1)); // initialize string getting name of button (offset because inventory is array indexed at 0)
				if(item.equals("DVL") || item.equals("ASVAL") || item.equals("VSS") || item.equals("RSASS")) { // if selected item is a weapon
					for(int i = 0; i < player.getInventory().size(); i++) { // search through all items in player inventory
						if(player.getInventory().get(i).getName().equals(item)) { // if item in inventory matches what we have selected
							Weapon w = (Weapon) player.getInventory().get(i); // create temporary weapon (must cast equipment to weapon type)
							player.setWeapon(w); // set players current weapon to that one that was selected
							player.resetFactionStatsWithEquipment(); // reset player statistics to account for different weapon stats
							zone.updatePlayerStats(player); // update labels in zone to reflect updated player stats
						}
					}
				} else if(item.equals("FASTMT") || item.equals("ALTYN") || item.equals("KIVER") || item.equals("RATNIK")) { // if helmet is selected
					for(int i = 0; i < player.getInventory().size(); i++) { // search player inventory
						if(player.getInventory().get(i).getName().equals(item)) { // if item found that matches what was selected in inventory (done to get index)
							Helmet h = (Helmet) player.getInventory().get(i); // create temp helmet to set to players current one
							player.setHelmet(h); // set players current helmet to temp one made from the selection
							player.resetFactionStatsWithEquipment(); // reset statistics to reflect new helmet
							zone.updatePlayerStats(player); // update labels
						}
					}
				} else if(item.equals("Bandage") || item.equals("IFAK") || item.equals("Salewa") || item.equals("Grizzly")) { // if medical item
					for(int i = 0; i < player.getInventory().size(); i++) { // search players inventory for item
						if(player.getInventory().get(i).getName().equals(item)) { // if found item
							Med m = (Med) player.getInventory().get(i); // create temp item to use
							player.addHealth(m.getCapacity()); // add players health by that temp item's healing ability
							player.getInventory().remove(selected - 1); // remove that used medical item  from players inventory
							player.resetFactionStatsWithEquipment(); // refresh player statistics
							charinv.resetInventory(player.getInventory()); // must refresh inventory because item was removed
							zone.updatePlayerStats(player); // update labels to reflect updated health from statistics
						}
					}
				}
			}
		});
		
		/* Drop Button */
		JButton drop = charinv.getDrop(); // get button from player inventory frame
		drop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { // if clicked
				if(player.getInventory().size() > 0) { // if there are items in players inventory
					player.getInventory().remove(selected - 1); // remove the selected item from players inventory array
					player.resetFactionStatsWithEquipment(); // reset player statistics
					charinv.resetInventory(player.getInventory()); // refresh player inventory because item was removed)
					zone.updatePlayerStats(player); // update zone labels
				} else { // else inventory is empty
					charinv.setSelectedText("Empty"); // set selected label to empty
				}
			}
		});
		
		
		// open character inventory
		JButton openCharInv = zone.getCharInv();
		openCharInv.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { // if players inventory is clicked
				charinv.setVisible(true); // make inventory frame visible
			}
		});
		
		JButton openScavInv = zone.getScavInv();
		openScavInv.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { // if enemies inventory is clicked
				if(phase == 1) { // if first enemy
					scavinv.setVisible(true); // show first enemy inventory
				} else if(phase == 2) { // if second enemy
					raidinv.setVisible(true); // show second enemies inventory
				}
			}
		});
		
		/* engage button */
		JButton engage = zone.getEngage();
		engage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { // when clicked
				if(phase == 1) { // if battleing first enemy
					doBattle(player, scav, zone); // player fights first enemy
					if(scav.getHealth() <= 0) { // if enemies health falls below 0
						zone.getEngage().setEnabled(false); // disable engage button (death handled in method)
					}
				} else if(phase == 2) { // if in second enemies phase
					doBattle(player, raid, zone); // have player battle second enemy
					if(raid.getHealth() <= 0) { // if player wins
						zone.getEngage().setEnabled(false); // disable engage button (no further enemies)
					}
				}
			}
		});
		
	}
	
	/**
	 * Accepts player and an enemy and a zone to conduct battle
	 * @param p
	 * @param s
	 * @param z
	 */
	public void doBattle(Player p, Enemy s, Zone z) {
		if(phase == 1) { // if in first enemies zone
			p.damage(((Scav) s).getScavAttack()); // call player damage method to allow player to be hurt by enemy
			((Scav) s).damage(p.getAttackStat()); // damage enemy by players attack statistic
		} else if(phase == 2) { // if in second enemy attack
			p.damage(((Raider) s).getRaidAttack()); // damage player by second enemy
			((Raider) s).damage(p.getAttackStat()); // damage enemy by player
		}
		
		z.updatePlayerStats(p); // update player statistics in zone labels
		z.updateEnemyStats(s); // update enemy labels in zone
		
		if(s.getHealth() <= 0) { // if enemy health below 0
			z.enemyDeath(); // enable the next button, disable engage button
		}
		if(p.getHealth() <= 0 ) { // if player dies
			end.setMessage("You Died..."); // set message
			z.setVisible(false); /// hide zone
			theEnd(); // go to end screen
		}
	}
	

}
