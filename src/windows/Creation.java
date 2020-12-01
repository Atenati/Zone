/**
 * Author: Atenati Weber-Morrison
 * Date: April 7, 2020
 */

package windows;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import items.*;

public class Creation extends JPanel implements ActionListener {

	/* radio titles */
	private JLabel weapon;
	private JLabel armour;
	private JLabel helmet;
	
	/* stat titles */
	private JLabel attack;
	private JLabel armourstat;
	private JLabel speed;
	private JLabel invsize;
	
	/* stat stats */
	private JTextField attackTF;
	private JTextField armourstatTF;
	private JTextField speedTF;
	private JTextField invsizeTF;
	
	/* stat mod stats */
	private JLabel attackmodLabel;
	private JLabel armourmodLabel;
	private JLabel speedmodLabel;
	
	/* radio buttons */
	private JRadioButton bear;
	private JRadioButton usec;
	private JRadioButton dvl;
	private JRadioButton asval;
	private JRadioButton vss;
	private JRadioButton rsass;
	private JRadioButton tactec;
	private JRadioButton paca;
	private JRadioButton unitar;
	private JRadioButton iotv;
	private JRadioButton fastmt;
	private JRadioButton altyn;
	private JRadioButton kiver;
	private JRadioButton ratnik;
	
	/* Image and name */
	private JLabel character;
	private ImageIcon usecImage;
	private ImageIcon bearImage;
	private JLabel nameprompt;
	private JTextField name;
	private JButton enter;

	/* base usec stats */
	private int usecattackmod = 70;
	private int usecarmourmod = 50;
	private int usecspeedmod = 40;
	private int usecinvmod = 5;

	/* base bear stats */
	private int bearattackmod = 40;
	private int beararmourmod = 20;
	private int bearspeedmod = 70;
	private int bearinvmod = 10;
	
	/* radio button checks */
	private boolean nameFilled = false;
	private boolean usecchosen;
	private boolean bearchosen;
	private boolean weaponFilled = false;
	private boolean armourFilled = false;
	private boolean helmetFilled = false;

	/* temp weapons to set to create player when finished */
	private Weapon Cweapon;
	private Armour Carmour;
	private Helmet Chelmet;
	
	/**
	 * create creation window
	 * sets all values needed to create player
	 */
	public Creation() {
		/* initialize pane */
		this.setBackground(Color.gray);
		setLayout(null);
		
		/* name textfield */
		nameprompt = new JLabel("Name");
		nameprompt.setBounds(364, 13, 100, 30);
		name = new JTextField();
		name.getDocument().addDocumentListener(new DocumentListener() { // listens to changes on document
			@Override
			public void changedUpdate(DocumentEvent e) { // if text is changed
				textModified();
			}

			@Override
			public void insertUpdate(DocumentEvent e) { // if text is inserted
				textModified();
			}

			@Override
			public void removeUpdate(DocumentEvent e) { // if text is removed
				textModified();
			}
			
			/**
			 * check wether there is text in jtextfield after modifications to it
			 */
			public void textModified() {
				if(name.getText().isEmpty()) { // if is empty, name was removed or not entered yet
					nameFilled = false; // name is not entered
				} else { // else jtextfield contains text
					nameFilled = true; // name is entered
				}
				checkEnabled(); // check wether all other requirments are ment to continue (name could be entered last)
			}
			
		});
		name.setBounds(364, 43, 150, 30); // set name location
		
		/* Character icons */
		usecImage = new ImageIcon(Creation.class.getResource("/usec.png"));
		bearImage = new ImageIcon(Creation.class.getResource("/bear.png"));
		
		/* Character icon & initial image */
		character = new JLabel(new ImageIcon(Creation.class.getResource("/nocharacter.png")));
		character.setBounds(30, 60, 213, 560);

		/* Radio Buttons */
		bear = new JRadioButton("Bear");
		bear.setBackground(Color.gray);
		usec = new JRadioButton("USEC");
		usec.setBackground(Color.gray);
		bear.addActionListener(this);
		usec.addActionListener(this); // add actionlistener to faction selection
		bear.setBounds(367, 97, 100, 30);
		usec.setBounds(367, 130, 100 ,30);
		ButtonGroup cs = new ButtonGroup(); // create button group to hold factions
		cs.add(bear); // add factions to buttongroup
		cs.add(usec);
		
		/* Equipment Labels */
		weapon = new JLabel("Weapon"); // initialize
		armour = new JLabel("Armour");
		helmet = new JLabel("Helmet");
		weapon.setBounds(311, 214, 100, 30); // set positions
		armour.setBounds(440, 214, 100, 30);
		helmet.setBounds(568, 214, 100, 30);
		
		/* Equipment Selection */
		ButtonGroup weaponradios = new ButtonGroup(); // create buttongroup to handle weapon radios
		dvl = new JRadioButton("DVL"); // initialize and set background colour
		dvl.setBackground(Color.gray);
		asval = new JRadioButton("ASVAL");
		asval.setBackground(Color.gray);
		vss = new JRadioButton("VSS");
		vss.setBackground(Color.gray);
		rsass = new JRadioButton("RSASS");
		rsass.setBackground(Color.gray);
		dvl.addActionListener(this); // add action listeners to radios
		asval.addActionListener(this);
		vss.addActionListener(this);
		rsass.addActionListener(this);
		weaponradios.add(dvl); // add radios to buttongroup
		weaponradios.add(asval);
		weaponradios.add(vss);
		weaponradios.add(rsass);
		dvl.setBounds(310, 244, 100, 30); // set locations
		asval.setBounds(310, 274, 100, 30);
		vss.setBounds(310, 304, 100, 30);
		rsass.setBounds(310, 334, 100, 30);

		ButtonGroup armourradios = new ButtonGroup(); // create radiogroup for armour
		tactec = new JRadioButton("TACTEC"); // initialize
		tactec.setBackground(Color.gray);
		paca = new JRadioButton("PACA");
		paca.setBackground(Color.gray);
		unitar = new JRadioButton("UNITAR");
		unitar.setBackground(Color.gray);
		iotv = new JRadioButton("IOTV");
		iotv.setBackground(Color.gray);
		tactec.addActionListener(this); // add action listeners
		paca.addActionListener(this);
		unitar.addActionListener(this);
		iotv.addActionListener(this);
		armourradios.add(tactec); // add to buttongroup
		armourradios.add(paca);
		armourradios.add(unitar);
		armourradios.add(iotv);
		tactec.setBounds(440, 244, 100, 30); // set locations
		paca.setBounds(440, 274, 100, 30);
		unitar.setBounds(440, 304, 100, 30);
		iotv.setBounds(440, 334, 100, 30);
		
		ButtonGroup helmetradios = new ButtonGroup(); // create buttongroup for helmet radios
		fastmt = new JRadioButton("FASTMT"); // initialize
		fastmt.setBackground(Color.gray);
		altyn = new JRadioButton("ALTYN");
		altyn.setBackground(Color.gray);
		kiver = new JRadioButton("KIVER");
		kiver.setBackground(Color.gray);
		ratnik = new JRadioButton("RATNIK");
		ratnik.setBackground(Color.gray);
		fastmt.addActionListener(this); // add action listeners
		altyn.addActionListener(this);
		kiver.addActionListener(this);
		ratnik.addActionListener(this);
		helmetradios.add(fastmt); // add to button group
		helmetradios.add(altyn);
		helmetradios.add(kiver);
		helmetradios.add(ratnik);
		fastmt.setBounds(568, 244, 100, 30); // position
		altyn.setBounds(568, 274, 100, 30);
		kiver.setBounds(568, 304, 100, 30);
		ratnik.setBounds(568, 334, 100, 30);

		/* stat label titles */
		attack = new JLabel("Attack"); // initialize labels
		armourstat = new JLabel("Armour");
		speed = new JLabel("Speed");
		invsize = new JLabel("Inv Size");
		attack.setBounds(341, 411, 100, 30); // position labels
		armourstat.setBounds(341, 441, 100, 30);
		speed.setBounds(341, 471, 100, 30);
		invsize.setBounds(341, 501, 100, 30);
		
		/* stat textfields */
		attackTF = new JTextField(); // initialize text fields
		armourstatTF = new JTextField();
		speedTF = new JTextField();
		invsizeTF = new JTextField();
		invsizeTF.setEditable(false); // set editable to false
		attackTF.setEditable(false);
		speedTF.setEditable(false);
		armourstatTF.setEditable(false);
		attackTF.setBounds(466, 416, 100, 20); // position
		armourstatTF.setBounds(466, 446, 100, 20);
		speedTF.setBounds(466, 476, 100, 20);
		invsizeTF.setBounds(466, 506, 100, 20);
		
		/* stat modifier textfields */
		attackmodLabel = new JLabel(); // initialize modifier labels
		armourmodLabel = new JLabel();
		speedmodLabel = new JLabel();
		attackmodLabel.setBounds(580, 416, 100, 20); // position
		armourmodLabel.setBounds(580, 446, 100, 20);
		speedmodLabel.setBounds(580, 476, 100, 20);
		
		/* Final Button */
		enter = new JButton("Enter Zone"); // initialize button
		enter.setEnabled(false); // set enabled to false default
		enter.addActionListener(this); // add action listener
		enter.setBounds(500, 600, 120, 30); // set position
		
		/* Add items to pane */
		add(attackTF);
		add(armourstatTF);
		add(speedTF);
		add(invsizeTF);
		add(attackmodLabel);
		add(armourmodLabel);
		add(speedmodLabel);
		add(attack);
		add(armourstat);
		add(speed);
		add(invsize);
		add(weapon);
		add(armour);
		add(helmet);
		add(dvl);
		add(asval);
		add(vss);
		add(rsass);
		add(tactec);
		add(paca);
		add(unitar);
		add(iotv);
		add(fastmt);
		add(altyn);
		add(kiver);
		add(ratnik);
		add(nameprompt);
		add(name);
		add(character);
		add(bear);
		add(usec);
		add(enter);
	}
	
	/**
	 * check wether there is
	 * a name entered
	 * a faction chosen
	 * a weapon chosen
	 * a armour chosen
	 * a helmet chosen
	 * 
	 * when all are true the button unlocks
	 * but if name is removed it locks again because of document listener
	 */
	public void checkEnabled() {
		if(nameFilled && (bearchosen || usecchosen) && weaponFilled && armourFilled && helmetFilled) {
			enter.setEnabled(true);
		} else {
			enter.setEnabled(false);
		}
	}
	
	/**
	 * return enter button
	 * @return
	 */
	public JButton getEnterButton() {
		return enter;
	}
	
	/**
	 * listen for radios
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		/* if faction is selected */
		if(bear.isSelected()) {
			bearchosen = true;
			usecchosen = false;
			character.setIcon(bearImage);
			attackTF.setText(String.valueOf(bearattackmod));
			armourstatTF.setText(String.valueOf(beararmourmod));
			speedTF.setText(String.valueOf(bearspeedmod));
			invsizeTF.setText(String.valueOf(bearinvmod));
			checkEnabled();
		}
		if(usec.isSelected()) {
			usecchosen = true;
			bearchosen = false;
			character.setIcon(usecImage);
			attackTF.setText(String.valueOf(usecattackmod));
			armourstatTF.setText(String.valueOf(usecarmourmod));
			speedTF.setText(String.valueOf(usecspeedmod));
			invsizeTF.setText(String.valueOf(usecinvmod));
			checkEnabled();
		}
		
		/* check weapon selection */
		if(dvl.isSelected()) {
			attackmodLabel.setText("+10"); // set label
			weaponFilled = true; // set weapon is selected
			checkEnabled(); // check wether everything else is selected
			Cweapon = new Weapon("DVL", 10); // create temporary weapon, this is to hand off to player when players needs to be created, this is players weapon
		}
		if(asval.isSelected()) {
			attackmodLabel.setText("+6");
			weaponFilled = true;
			checkEnabled();
			Cweapon = new Weapon("ASVAL", 6);
		}
		if(vss.isSelected()) {
			Cweapon = new Weapon("VSS", 4);
			attackmodLabel.setText("+4");
			weaponFilled = true;
			checkEnabled();
		}
		if(rsass.isSelected()) {
			Cweapon = new Weapon("RSASS", 8);
			attackmodLabel.setText("+8");
			weaponFilled = true;
			checkEnabled();
		}
		
		/* check armour selection */
		if(tactec.isSelected()) {
			Carmour = new Armour("TACTEC", 20, 3);
			speedmodLabel.setText("-20");
			armourFilled = true;
			checkEnabled();
		}
		if(paca.isSelected()) {
			Carmour = new Armour("PACA", 5, 1);
			speedmodLabel.setText("-5");
			armourFilled = true;
			checkEnabled();
		}
		if(unitar.isSelected()) {
			Carmour = new Armour("UNITAR", 10, 2);
			speedmodLabel.setText("-10");
			armourFilled = true;
			checkEnabled();
		}
		if(iotv.isSelected()) {
			Carmour = new Armour("IOTV", 15, 4);
			speedmodLabel.setText("-15");
			armourFilled = true;
			checkEnabled();
		}
		
		/* Check helmet selection */
		if(fastmt.isSelected()) {
			Chelmet = new Helmet("FASTMT", 25, false); // fastmt does not have faceshield
			armourmodLabel.setText("+25");
			helmetFilled = true;
			checkEnabled();
		}
		if(altyn.isSelected()) {
			Chelmet = new Helmet("ALTYN", 35, true); // altyn has faceshield
			armourmodLabel.setText("+35");
			helmetFilled = true;
			checkEnabled();
		}
		if(kiver.isSelected()) {
			Chelmet = new Helmet("KIVER", 20, true); // kiver has faceshield
			armourmodLabel.setText("+20");
			helmetFilled = true;
			checkEnabled();
		}
		if(ratnik.isSelected()) {
			Chelmet = new Helmet("RATNIK", 10, false); // ratnik does not have faceshield
			armourmodLabel.setText("+10");
			helmetFilled = true;
			checkEnabled();
		}
	}
	
	/* The below are the functions used to create the player */
	
	/**
	 * gets players entered name
	 * @return
	 */
	public String getPlayerName() {
		return name.getText();
	}
	
	/**
	 * gets player chosen faction
	 * @return
	 */
	public String getFaction() {
		if(usecchosen) {
			return "USEC";
		} else {
			return "Bear";
		}
	}
	
	/**
	 * gets players chosen helmet
	 * @return
	 */
	public Helmet getHelmet() {
		return Chelmet;
	}
	
	/**
	 * gets players chosen weapon
	 * @return
	 */
	public Weapon getWeapon() {
		return Cweapon;
	}
	
	/**
	 * gets players chosen armour
	 * @return
	 */
	public Armour getArmour() {
		return Carmour;
	}
	
	/**
	 * gets players attack mod
	 * @return
	 */
	public int getAttackMod() {
		return Integer.parseInt(attackmodLabel.getText());
	}
	
	/**
	 * gets players armour mod
	 * @return
	 */
	public int getArmourMod() {
		return Integer.parseInt(armourmodLabel.getText());
	}
	
	/**
	 * gets players speed mod
	 * @return
	 */
	public int getSpeedMod() {
		return Integer.parseInt(speedmodLabel.getText().substring(1));
	}

}
