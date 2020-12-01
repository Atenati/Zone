/**
 * Author: Atenati Weber-Morrison
 * Date: April 7, 2020
 */

package windows;

import java.awt.Color;
import javax.swing.*;
import engine.*;
import people.*;

public class Zone extends JPanel {

	/* declare title label */
	private JLabel title;
	
	/* declare player and enemy image labels */
	private JLabel playerImage;
	private JLabel enemyImage;
	
	/* declare player and enemy name labels */
	private JLabel playerName;
	private JLabel enemyName;

	/* declare player inv and enemy inv buttons */
	private JButton ci;
	private JButton ei;
	
	/* declare textfields */
	private JTextField playerAttack;
	private JTextField playerHealth;
	private JTextField playerArmour;
	private JTextField playerSpeed;
	
	/* declare textfiels labels */
	private JLabel playerAttackLabel;
	private JLabel playerHealthLabel;
	private JLabel playerArmourLabel;
	private JLabel playerSpeedLabel;

	/* declare enemy attack stat */
	private JTextField enemyAttack;
	private JTextField enemyHealth;
	private JTextField enemyArmour;

	/* declare enemy attack stat labels */
	private JLabel enemyAttackLabel;
	private JLabel enemyHealthLabel;
	private JLabel enemyArmourLabel;
	
	/* decare engage and next buttons */
	private JButton engage;
	private JButton next;
	
	public Zone() {
		/* JPanel settings */
		this.setBackground(Color.GRAY);
		setLayout(null);
		
		/* Setup and position title */
		title = new JLabel("The Zone");
		title.setBounds(325, 30, 100, 30);
		
		/* Setup and position character inventory button */
		ci = new JButton("Inventory");
		ci.setBounds(80, 424, 133, 50);
		
		/* Setup and position Enemy inventory button */
		ei = new JButton("Loot");
		ei.setBounds(454, 424, 133, 50);
		ei.setEnabled(false);
		
		/* Setup and position Player stats */
		playerAttackLabel = new JLabel("Attack");
		playerHealthLabel = new JLabel("Health");
		playerArmourLabel = new JLabel("Armour");
		playerSpeedLabel = new JLabel("Speed");
		playerAttackLabel.setBounds(75, 485, 100, 30);
		playerHealthLabel.setBounds(75, 510, 100, 30);
		playerArmourLabel.setBounds(75, 535, 100, 30);
		playerSpeedLabel.setBounds(75, 560, 100, 30);

		/* setup and position textfields */
		playerAttack = new JTextField();
		playerHealth = new JTextField();
		playerArmour = new JTextField();
		playerSpeed = new JTextField();
		playerAttack.setEditable(false);
		playerHealth.setEditable(false);
		playerArmour.setEditable(false);
		playerSpeed.setEditable(false);
		playerAttack.setBounds(131, 490, 100, 20);
		playerHealth.setBounds(131, 515, 100, 20);
		playerArmour.setBounds(131, 540, 100, 20);
		playerSpeed.setBounds(131, 565, 100, 20);
		
		/* Setup and position Enemy stats */
		enemyAttackLabel = new JLabel("Attack");
		enemyHealthLabel = new JLabel("Health");
		enemyArmourLabel = new JLabel("Armour");
		enemyAttackLabel.setBounds(420, 485, 100, 30);
		enemyHealthLabel.setBounds(420, 510, 100, 30);
		enemyArmourLabel.setBounds(420, 535, 100, 30);
		
		/* setup and position enemy textfields */
		enemyAttack = new JTextField();
		enemyHealth = new JTextField();
		enemyArmour = new JTextField();
		enemyAttack.setEditable(false);
		enemyHealth.setEditable(false);
		enemyArmour.setEditable(false);
		enemyAttack.setBounds(476, 485, 100, 20);
		enemyHealth.setBounds(476, 510, 100, 20);
		enemyArmour.setBounds(476, 535, 100, 20);
		
		/* Setup and position Engage button */
		engage = new JButton("Engage");
		engage.setBounds(163, 600, 133, 50);
		
		/* Setup and position Next button */
		next = new JButton("Next");
		next.setBounds(335, 600, 133, 50);
		next.setEnabled(false);
		
		/* Add to JPanel */
		add(title);
		add(ci);
		add(ei);
		add(engage);
		add(next);
		
		add(playerAttackLabel);
		add(playerHealthLabel);
		add(playerArmourLabel);
		add(playerSpeedLabel);
		
		add(enemyAttackLabel);
		add(enemyHealthLabel);
		add(enemyArmourLabel);

		add(playerAttack);	
		add(playerHealth);	
		add(playerArmour);	
		add(playerSpeed);
		
		add(enemyAttack);	
		add(enemyHealth);	
		add(enemyArmour);
	}
	
	/**
	 * update labels in zone with current player stats
	 * @param p
	 */
	public void updatePlayerStats(Player p) {
		p.resetFactionStatsWithEquipment();
		playerAttack.setText(String.valueOf(p.getAttackStat()));
		playerHealth.setText(String.valueOf(p.getHealth()));
		playerArmour.setText(String.valueOf(p.getArmourStat()));
		playerSpeed.setText(String.valueOf(p.getSpeed()));
	}
	
	/* update labels on zone with current enemy stats */
	public void updateEnemyStats(Enemy e) {
		if(Game.getPhase() == 1) { // if phase 1
			enemyAttack.setText(String.valueOf(((Scav) e).getScavAttack())); // do scavs
			enemyHealth.setText(String.valueOf(e.getHealth()));
			enemyArmour.setText(String.valueOf(((Scav) e).getScavArmour()));
		} else if(Game.getPhase() == 2) { // if phase 2
			enemyAttack.setText(String.valueOf(((Raider) e).getRaidAttack())); // do raiders
			enemyHealth.setText(String.valueOf(e.getHealth()));
			enemyArmour.setText(String.valueOf(((Raider) e).getRaidArmour()));
		}
		
	}
	
	/* Set player statistics and initialize and position them on jpanel */
	public void setPlayerStats(Player p) {
		playerAttack.setText(String.valueOf(p.getAttackStat()));
		playerHealth.setText(String.valueOf(p.getHealth()));
		playerArmour.setText(String.valueOf(p.getArmourStat()));
		playerSpeed.setText(String.valueOf(p.getSpeed()));
		
		playerName = new JLabel(p.getName());
		playerImage = new JLabel(p.getImage());
		playerImage.setBounds(95, 105, 106, 280);
		playerName.setBounds(95, 395, 100, 30);
		add(playerImage);
		add(playerName);
	}
	
	/* if enemy dies, allow inventory to be opened, allow next to be clicked */
	public void enemyDeath() {
		ei.setEnabled(true);
		next.setEnabled(true);
	}
	
	/* initialize, add and setup enemy labels on jpanel */
	public void setEnemyStats(Enemy e) {
		if(Game.getPhase() == 1) { // if phase 1
			enemyAttack.setText(String.valueOf(((Scav) e).getScavAttack())); // set scav stats
			enemyHealth.setText(String.valueOf(e.getHealth()));
			enemyArmour.setText(String.valueOf(((Scav) e).getScavArmour()));
		} else if(Game.getPhase() == 2) { // if phase 2
			enemyAttack.setText(String.valueOf(((Raider) e).getRaidAttack())); // add radier stats
			enemyHealth.setText(String.valueOf(e.getHealth()));
			enemyArmour.setText(String.valueOf(((Raider) e).getRaidArmour()));
			enemyName.setText(e.getName());
			enemyImage.setIcon(e.getImage());
		}
		
		/* initialize enemy name and image */
		enemyName = new JLabel(e.getName());
		enemyImage = new JLabel(e.getImage());
		enemyImage.setBounds(440, 108, 106, 280);
		enemyName.setBounds(440, 395, 100, 30);
		add(enemyName);
		add(enemyImage);
	}
	
	/**
	 * get engage button
	 * @return
	 */
	public JButton getEngage() {
		return engage;
	}
	
	/* get next button */
	public JButton getNext() {
		return next;
	}

	/* get character inventory button */
	public JButton getCharInv() {
		return ci;
	}
	
	/* get enemy inventory button */
	public JButton getScavInv() {
		return ei;
	}
}
