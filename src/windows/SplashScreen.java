/**
 * Author: Atenati Weber-Morrison
 * Date: April 7, 2020
 */

package windows;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class SplashScreen extends JPanel implements ActionListener {
	
	private JButton play;
	
	/**
	 * create splash screen
	 */
	public SplashScreen() {
		//initialize buttons
		play = new JButton("Play");
		JButton exit = new JButton("Exit");
		exit.addActionListener(this); // add action listener
		JLabel logo = new JLabel(new ImageIcon(SplashScreen.class.getResource("/splashlogo.png"))); // create imageicon logo
		logo.setBounds(80, 75, 518, 230); // position components
		play.setBounds(400, 450, 120, 60);
		exit.setBounds(200, 450, 120, 60);
		setLayout(null); // disable flowlayout
		add(play); // add to jpanel
		add(exit);
		add(logo);
		setBackground(Color.GRAY);
	}
	
	/**
	 * listen for button click
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("Exit")) { // if click exit
			System.exit(1); // program exit
		}
	}
	
	/**
	 * return play button
	 * @return
	 */
	public JButton getPlayButton() {
		return play;
	}
}
