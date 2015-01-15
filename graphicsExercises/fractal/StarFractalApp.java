//Exercise 15.19
package deitel.graphicsExercises.fractal;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class StarFractalApp {
	private static StarFractalPanel sfp;
	private static JLabel stateLevel;

	public static void main (String args[]) {
		sfp = new StarFractalPanel (0);
		
		JButton increase = new JButton ("Increase");
		IncreaseHandler ih = new IncreaseHandler ();
		increase.addActionListener (ih);
		
		JButton decrease = new JButton ("Decrease");
		DecreaseHandler dh = new DecreaseHandler ();
		decrease.addActionListener (dh);
		
		stateLevel = new JLabel ("Level: " + sfp.getLevel ());
		
		JPanel controlPanel = new JPanel ();
		controlPanel.add (decrease);
		controlPanel.add (increase);
		controlPanel.add (stateLevel);
		
		JFrame wndw = new JFrame ("Star fractal application");
		wndw.add (controlPanel, BorderLayout.NORTH);
		wndw.add (sfp);
		wndw.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
		wndw.setSize (700, 700);
		wndw.setVisible (true);
	}
	
	//I use 2 classes to learn a different way handling events
	private static class IncreaseHandler implements ActionListener {

		public void actionPerformed (ActionEvent ae) {
			sfp.setLevel (sfp.getLevel () + 1);
			stateLevel.setText ("Level: " + sfp.getLevel ());
		}
	}
	
	private static class DecreaseHandler implements ActionListener {

		public void actionPerformed (ActionEvent ae) {
			sfp.setLevel (sfp.getLevel () - 1);
			stateLevel.setText ("Level: " + sfp.getLevel ());
		}
	}
}
