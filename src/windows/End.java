/**
 * Author: Atenati Weber-Morrison
 * Date: April 7, 2020
 */

package windows;

import java.awt.Color;
import javax.swing.*;

public class End extends JPanel {

	// initialize JComponents
	private String determine;
	private JLabel message;
	private JPanel pane;
	
	/**
	 * Create end JPanel
	 */
	public End() {
		JLabel logo = new JLabel(new ImageIcon(SplashScreen.class.getResource("/splashlogo.png"))); // put main logo
		
		message = new JLabel(determine); // message is predetermined if player died or won
		message.setBounds(300, 450, 100, 30); // position message
		logo.setBounds(80, 75, 518, 230);
		setLayout(null); // no flowlayout by default
		add(message); // add contents to JPanel
		add(logo);
		setBackground(Color.GRAY);
	}
	
	/**
	 * allow message to be set from outside class
	 * @param toset
	 */
	public void setMessage(String toset) {
		message.setText(toset);
	}
}
