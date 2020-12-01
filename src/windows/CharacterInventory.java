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

public class CharacterInventory extends JFrame {

	// initialize buttons
	private JButton one;
	private JButton two;
	private JButton three;
	private JButton four;
	private JButton five;
	private JButton six;
	private JButton seven;
	private JButton eight;
	private JButton nine;
	private JButton ten;
	
	// initialize labels
	private JLabel selected;
	private JLabel info;
	
	//initialize buttons, array and panel
	private JButton use;
	private JButton drop;
	private JButton[] items;
	private JPanel pane;
	
	/**
	 * create character inventory
	 */
	public CharacterInventory() {
		//initialize objects
		setTitle("Character Inventory");
		//initialize buttons
		pane = new JPanel();
		use = new JButton("Use");
		drop = new JButton("Drop");
		
		one = new JButton();
		two = new JButton();
		three = new JButton();
		four = new JButton();
		five = new JButton();
		six = new JButton();
		seven = new JButton();
		eight = new JButton();
		nine = new JButton();
		ten = new JButton();
		
		//create and set location of info and selected label
		info = new JLabel("Selected: ");
		selected = new JLabel();
		info.setBounds(30, 250, 100, 30);
		selected.setBounds(100, 250, 100, 30);
		
		//set positions
		use.setBounds(190, 240, 150, 57);
		drop.setBounds(355, 240, 150, 57);
		
		//add to panel
		pane.add(use);
		pane.add(drop);
		pane.add(selected);
		pane.add(info);
		
		//frame settings
		pane.setLayout(null);
		pane.setBackground(Color.GRAY);
		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		this.setMinimumSize(new Dimension(695, 353));
		this.add(pane);
		setVisible(false); //starts invisible until opened
	}
	
	/**
	 * allow text to change from outside
	 * @param toset
	 */
	public void setSelectedText(String toset) {
		selected.setText(toset);
	}
	
	/**
	 * create buttons in array to make and place them automatically
	 */
	public void createButtons() {
		items = new JButton[10]; // initialize array
		// initialize buttons
		items[9] = ten;
		items[8] = nine;
		items[7] = eight;
		items[6] = seven;
		items[5] = six;
		items[4] = five;
		items[3] = four;
		items[2] = three;
		items[1] = two;
		items[0] = one;
		//stating x and y of buttons
		int x = 17;
		int y = 15;
		for(int i = 0; i < items.length; i++) {
			items[i].setBounds(x, y, 100, 80); // set arrays index button location
			pane.add(items[i]); // add each item of array (buttons) to jpanel
			if(i == 4) { // if reach end of row
				x= -117; // backset x
				y= 135; // change y to second row
			}
			x += 134; // increase x each button place
		}
	}
	
	/**
	 * return array of buttons
	 * @return
	 */
	public JButton[] getInvButtons() {
		return items;
	}
	
	/**
	 * get button name
	 * this is what relates the text on button, to its position in the array, thus the item position in character inventory
	 * @param index
	 * @return
	 */
	public String getButtonName(int index) {
		return items[index].getText();
	}
	
	/**
	 * disable all buttons that aren't holding anything (empty spaces in character inventory)
	 * set text on buttons to corrosponding item in player inventory
	 * @param a
	 */
	public void resetInventory(ArrayList<Equipment> a) {
		for(int i = 0; i < a.size(); i++) {
			items[i].setText(a.get(i).getName());
			items[i].setEnabled(true);
		}
		for(int i = a.size(); i < items.length; i++) {
			items[i].setEnabled(false);
			items[i].setText("");
		}
	}

	/* getters for inventory buttons */
	public JButton getOne() {
		return one;
	}
	public JButton getTwo() {
		return two;
	}
	public JButton getThree() {
		return three;
	}
	public JButton getFour() {
		return four;
	}
	public JButton getFive() {
		return five;
	}
	public JButton getSix() {
		return six;
	}
	public JButton getSeven() {
		return seven;
	}
	public JButton getEight() {
		return eight;
	}
	public JButton getNine() {
		return nine;
	}
	public JButton getTen() {
		return ten;
	}
	
	/**
	 * get use button
	 * @return
	 */
	public JButton getUse() {
		return use;
	}
	
	/**
	 * get drop button
	 * @return
	 */
	public JButton getDrop() {
		return drop;
	}
	
}