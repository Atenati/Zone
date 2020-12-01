/**
 * Author: Atenati Weber-Morrison
 * Date: April 7, 2020
 */

package engine;

public class Main {
	
	/* First method to run, runs program */
	public static void main(String args[])
	{
		javax.swing.SwingUtilities.invokeLater(new Runnable()
				{
					public void run()
					{
						Game newgame = new Game(); // creates new instance of game
					}
				});
	}
}
