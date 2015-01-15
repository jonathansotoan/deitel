//This class required that be executed these instruction in a bash terminal (linux)
//export CLASSPATH=$CLASSPATH:/home/jonathan/Documentos/Dropbox/Proyectos/Java/Notepad/
package deitel.graphicsExercises.drawing;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.GradientPaint;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JColorChooser;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class DrawingFrame implements ActionListener, ItemListener {
	private DrawingPanel dw;
	private JButton clearBtn;
	private JButton undoBtn;
	private JButton firstColor;
	private JButton secondColor;
	private JButton randomShapes;
	private JCheckBox filled;
	private JCheckBox gradient;
	private JCheckBox dashes;
	private JComboBox shapesList;
	private JFrame wndw;
	private JTextField widthLine;
	private JTextField longDash;
	private Color color1;
	private Color color2;
	private MenuItem save;
	private MenuItem load;
	
	public DrawingFrame () {
		JLabel stateLabel = new JLabel ();
		JPanel controlPanel = new JPanel ();
		controlPanel.setLayout (new GridLayout (2, 1, 5, 5));
		JPanel top = new JPanel ();
		JPanel bottom = new JPanel ();
		wndw = new JFrame ("Drawing app");
		clearBtn = new JButton ("Clear");
		clearBtn.addActionListener (this);
		undoBtn = new JButton ("Undo");
		undoBtn.addActionListener (this);
		color1 = Color.BLACK;
		dw = new DrawingPanel (stateLabel);
		
		MenuBar bar = new MenuBar ();
		Menu file = new Menu ("File");
		save = new MenuItem ("Save as...");
		load = new MenuItem ("load file");
		save.addActionListener (this);
		load.addActionListener (this);
		file.add (save);
		file.addSeparator ();
		file.add (load);
		bar.add (file);
		wndw.setMenuBar (bar);
	
		String[] shapesNames = {"Circle", "Square", "Triangle", "Line"};
		shapesList = new JComboBox (shapesNames);
		shapesList.setMaximumRowCount (4);
		shapesList.addItemListener (this);
		
		filled = new JCheckBox ("Filled");
		filled.addItemListener (this);

		top.add (shapesList);
		top.add (filled);
		top.add (undoBtn);
		top.add (clearBtn);
		
		gradient = new JCheckBox ("Use gradiant");
		gradient.addItemListener (this);
		
		firstColor = new JButton ("1st Color");
		firstColor.addActionListener (this);
		secondColor = new JButton ("2nd Color");
		secondColor.addActionListener (this);
		randomShapes = new JButton ("Random shapes");
		randomShapes.addActionListener (this);
		widthLine = new JTextField (3);
		widthLine.addActionListener (this);
		longDash = new JTextField (3);
		longDash.addActionListener (this);
		dashes = new JCheckBox ("Dashes");
		dashes.addItemListener (this);
		
		bottom.add (gradient);
		bottom.add (firstColor);
		bottom.add (secondColor);
		bottom.add (randomShapes);
		bottom.add (new JLabel ("Width line"));
		bottom.add (widthLine);
		bottom.add (new JLabel ("Long dash"));
		bottom.add (longDash);
		bottom.add (dashes);
		
		controlPanel.add (top);
		controlPanel.add (bottom);
		wndw.add (controlPanel, BorderLayout.NORTH);
		wndw.add (dw);
		wndw.add (stateLabel, BorderLayout.SOUTH);
		wndw.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
		wndw.setSize (800, 500);
		wndw.setVisible (true);
	}

	public static void main (String args[]) {
		new DrawingFrame ();
	}
	
	public void actionPerformed (ActionEvent ae) {
		if (ae.getSource () == undoBtn)
			dw.undo ();
		else if (ae.getSource () == clearBtn)
			dw.clear ();
		else if (ae.getSource () == widthLine)
			if (dashes.isSelected ()) {
				float[] dashesSize = {Float.parseFloat (longDash.getText ())};
			
				dw.setCurrentStroke (new BasicStroke (Float.parseFloat (widthLine.getText ()),
																	BasicStroke.CAP_ROUND,
																	BasicStroke.JOIN_ROUND,
																	10,
																	dashesSize,
																	0));
			} else
				dw.setCurrentStroke (new BasicStroke (Float.parseFloat (widthLine.getText ()),
																	BasicStroke.CAP_ROUND,
																	BasicStroke.JOIN_ROUND));
		else if (ae.getSource () == longDash && dashes.isSelected ()) {
			float[] dashesSize = {Float.parseFloat (longDash.getText ())};
			
			dw.setCurrentStroke (new BasicStroke (Float.parseFloat (widthLine.getText ()),
																BasicStroke.CAP_ROUND,
																BasicStroke.JOIN_ROUND,
																10,
																dashesSize,
																0));
		} else if (ae.getSource () == firstColor) {
			color1 = JColorChooser.showDialog (wndw,
															"Choose a color",
															color1 == null ? Color.BLACK : color1);
			
			if (gradient.isSelected () && color2 != null && color1 != null)
				dw.setCurrentPaint (new GradientPaint (0, 0, color1, 50, 50, color2, true));
			else
				dw.setCurrentPaint (color1);
		} else if (ae.getSource () == secondColor) {
			color2 = JColorChooser.showDialog (wndw,
															"Choose a color",
															color2 == null ? Color.BLACK : color2);

			if (gradient.isSelected () && color1 != null && color2 != null)
				dw.setCurrentPaint (new GradientPaint (0, 0, color1, 50, 50, color2, true));
		} else if (ae.getSource () == randomShapes)
			dw.randomShapes ();
		else if (ae.getSource () == save || ae.getSource () == load) {
			JFileChooser selector = new JFileChooser ();
			selector.setFileSelectionMode (JFileChooser.FILES_ONLY);

			if (selector.showOpenDialog (wndw) != JFileChooser.CANCEL_OPTION)
				if (ae.getSource () == save)
					dw.save (selector.getSelectedFile ().getAbsolutePath ());
				else
					dw.load (selector.getSelectedFile ().getAbsolutePath ());
		}
	}
	
	public void itemStateChanged (ItemEvent ie) {
		if (ie.getSource () == filled)
			dw.setFillable (filled.isSelected ());
		else if (ie.getSource () == dashes)
			if (dashes.isSelected ()) {
				float[] dashesSize = {Float.parseFloat (longDash.getText ())};
			
				dw.setCurrentStroke (new BasicStroke (Float.parseFloat (widthLine.getText ()),
																	BasicStroke.CAP_ROUND,
																	BasicStroke.JOIN_ROUND,
																	10,
																	dashesSize,
																	0));
			} else
				dw.setCurrentStroke (new BasicStroke (Float.parseFloat (widthLine.getText ()),
																	BasicStroke.CAP_ROUND,
																	BasicStroke.JOIN_ROUND));
		else if (ie.getSource () == gradient)
			if (gradient.isSelected () && color1 != null && color2 != null)
				dw.setCurrentPaint (new GradientPaint (0, 0, color1, 50, 50, color2, true));
			else
				dw.setCurrentPaint (color1);
		else if (ie.getStateChange () == ItemEvent.SELECTED)
			dw.setTypeShape ((byte) shapesList.getSelectedIndex ());
	}
}
