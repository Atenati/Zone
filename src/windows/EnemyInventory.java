/**
 * Author: Atenati Weber-Morrison
 * Date: April 7, 2020
 */

package windows;

import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;
import javax.swing.*;
import items.Equipment;

public class EnemyInventory extends JFrame {

	//declare buttons
	private JButton one;
	private JButton two;
	private JButton three;
	private JPanel pane;
	
	/**
	 * create enemy inventory
	 */
	public EnemyInventory() {
		setTitle("Enemy Inventory");
		/* initialize buttons and panel */
		pane = new JPanel();
		one = new JButton();
		two = new JButton();
		three = new JButton();

		/* position components */
		one.setBounds(90, 70, 100, 80);
		two.setBounds(224, 70, 100, 80);
		three.setBounds(358, 70, 100, 80);
		
		/* add components */
		pane.add(one);
		pane.add(two);
		pane.add(three);
		
		//JFrame settings
		pane.setLayout(null);
		pane.setBackground(Color.GRAY);
		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		this.setMinimumSize(new Dimension(695, 353));
		this.add(pane);
		setVisible(false); //starts invisible until opened
		
	}
	
	/**
	 * set button names to enemies inventory item names
	 * @param a
	 */
	public void resetInventory(ArrayList<Equipment> a) {
		one.setText(a.get(0).getName());
		two.setText(a.get(1).getName());
		three.setText(a.get(2).getName());
	}
	
	/**
	 * return button one
	 * @return
	 */
	public JButton getOne() {
		return one;
	}
	
	/**
	 * return button two
	 * @return
	 */
	public JButton getTwo() {
		return two;
	}
	
	/**
	 * return button three
	 * @return
	 */
	public JButton getThree() {
		return three;
	}
}
