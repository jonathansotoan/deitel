//This class required that be executed these instruction in a bash terminal (linux)
//export CLASSPATH=$CLASSPATH:/home/jonathan/Documentos/Dropbox/Proyectos/Java/Notepad/
package deitel.graphicsExercises.drawing;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GradientPaint;
import java.awt.Paint;
import java.awt.Stroke;
import java.awt.BasicStroke;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseMotionListener;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.EOFException;
import java.io.IOException;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.util.Random;

public class DrawingPanel extends JPanel {
	public static final byte CIRCLE = 0;
	public static final byte SQUARE = 1;
	public static final byte TRIANGLE = 2;
	public static final byte LINE = 3;

	private Shape[] shapes;
	private Shape currentShape;
	private Stroke currentStroke;
	private short numberOfShapes;
	private byte typeOfShape;
	private Paint currentPaint;
	private boolean filled = false;
	private JLabel stateLabel;
	
	public DrawingPanel (JLabel state) {
		stateLabel = state;
		shapes = new Shape[100];
		numberOfShapes = 0;
		typeOfShape = CIRCLE;
		currentPaint = Color.BLACK;
		setBackground (Color.WHITE);
		MouseHandler mh = new MouseHandler ();
		addMouseListener (mh);
		addMouseMotionListener (mh);
	}
	
	@Override
	public void paintComponent (Graphics g) {
		super.paintComponent (g);
		Graphics2D g2d = (Graphics2D) g;

		if (currentShape != null)
			currentShape.draw (g2d);

		for (byte j = 0; shapes[j] != null && j < shapes.length; ++j)
			shapes[j].draw (g2d);
	}
	
	public void setTypeShape (byte newTypeShape) {
		typeOfShape = newTypeShape >= 0 && newTypeShape < 4 ? newTypeShape : 0;
	}
	
	public void setCurrentPaint (Paint newPaint) {
		currentPaint = newPaint;
	}
	
	public void setCurrentStroke (Stroke newStroke) {
		currentStroke = newStroke;
	}
	
	public void setFillable (boolean filledValue) {
		filled = filledValue;
	}
	
	public void undo () {
		if (--numberOfShapes < 0)
			numberOfShapes = 0;
		
		shapes[numberOfShapes] = null;
		
		repaint ();
	}
	
	public void clear () {
		numberOfShapes = 0;
		currentShape = null;
		
		for (short j = 0; shapes[j] != null && j < shapes.length; ++j)
			shapes[j] = null;
		
		repaint ();
	}
		
	public void save (String fileName) {
		try {
			ObjectOutputStream oos = new ObjectOutputStream (new FileOutputStream (fileName));
			
			for (short j = 0; j < numberOfShapes; ++j) {
				if (shapes[j] instanceof Line)
					oos.writeByte (LINE);
				else if (shapes[j] instanceof Square)
					oos.writeByte (SQUARE);
				else if (shapes[j] instanceof Triangle)
					oos.writeByte (TRIANGLE);
				else if (shapes[j] instanceof Circle)
					oos.writeByte (CIRCLE);
				
				shapes[j].writeExternal (oos);
			}
			
			oos.close ();
		} catch (IOException ioEx) {
			System.err.println ("Error: We can not save.");
			ioEx.printStackTrace ();
		}
	}

	public void load (String fileName) {
		try {
			ObjectInputStream ois = new ObjectInputStream (new FileInputStream (fileName));
			clear ();
			for (short j = 0; j < shapes.length; ++j) {
				byte type = ois.readByte ();
			
				switch (type) {
					case LINE:
						shapes[j] = new Line ();
						break;
					
					case SQUARE:
						shapes[j] = new Square ();
						break;
					
					case TRIANGLE:
						shapes[j] = new Triangle ();
						break;
					
					case CIRCLE:
						shapes[j] = new Circle ();
				}
			
				shapes[j].readExternal (ois);
			}
			ois.close ();

			repaint ();
		} catch (EOFException endOfFileEx) {
			//When the reading ends, the control will come here and it will continue.
		} catch (ClassNotFoundException classNotFoundEx) {
			System.err.println ("Error: The class does not exist.");
			System.exit (1);
		} catch (IOException ioEx) {
			System.err.println ("Error: We can not load.");
		}
	}

	public void randomShapes () {
		Random ran = new Random ();
		byte randomShapes = (byte) ran.nextInt (15);

		for (byte j = 0; j <= randomShapes; ++j) {
			float[] dashesSize = {ran.nextFloat () * 30};
			BasicStroke strokeTemp = new BasicStroke (ran.nextFloat () * 10,
																	BasicStroke.CAP_ROUND,
																	BasicStroke.JOIN_ROUND,
																	10,
																	dashesSize,
																	0);

			Paint paintTemp = new Color (ran.nextInt (256), ran.nextInt (256), ran.nextInt (256));
			
			if (ran.nextBoolean ())
				paintTemp = new GradientPaint (0,
														0,
														(Color) paintTemp,
														ran.nextInt (81) + 20,
														ran.nextInt (81) + 20,
														new Color (ran.nextInt (256), ran.nextInt (256), ran.nextInt (256)),
														true);

			switch (ran.nextInt (4)) {
				case CIRCLE:
					shapes[numberOfShapes++] = new Circle (ran.nextInt (getWidth () + 1),
																		ran.nextInt (getHeight () + 1),
																		ran.nextInt (getWidth () + 1),
																		ran.nextInt (getHeight () + 1),
																		ran.nextBoolean (),
																		paintTemp,
																		strokeTemp);
					break;

				case SQUARE:
					shapes[numberOfShapes++] = new Square (ran.nextInt (getWidth () + 1),
																		ran.nextInt (getHeight () + 1),
																		ran.nextInt (getWidth () + 1),
																		ran.nextInt (getHeight () + 1),
																		ran.nextBoolean (),
																		paintTemp,
																		strokeTemp);
					break;

				case TRIANGLE:
					shapes[numberOfShapes++] = new Triangle (ran.nextInt (getWidth () + 1),
																			ran.nextInt (getHeight () + 1),
																			ran.nextInt (getWidth () + 1),
																			ran.nextInt (getHeight () + 1),
																			ran.nextBoolean (),
																			paintTemp,
																			strokeTemp);
					break;

				case LINE:
					shapes[numberOfShapes++] = new Line (ran.nextInt (getWidth () + 1),
																			ran.nextInt (getHeight () + 1),
																			ran.nextInt (getWidth () + 1),
																			ran.nextInt (getHeight () + 1),
																			paintTemp,
																			strokeTemp);
			}
		}

		repaint ();
	}
	
	private class MouseHandler extends MouseAdapter implements MouseMotionListener {

		public void mousePressed (MouseEvent me) {
			switch (typeOfShape) {
				case CIRCLE:
					currentShape = new Circle (me.getX (), me.getY (), filled, currentPaint, currentStroke);
					break;
					
				case SQUARE:
					currentShape = new Square (me.getX (), me.getY (), filled, currentPaint, currentStroke);
					break;
					
				case TRIANGLE:
					currentShape = new Triangle (me.getX(), me.getY(), filled, currentPaint, currentStroke);
					break;
				
				case LINE:
					currentShape = new Line (me.getX(), me.getY(), currentPaint, currentStroke);
			}
		}
		
		public void mouseReleased (MouseEvent me) {
			refreshCurrentShape (me.getX (), me.getY ());
			shapes[numberOfShapes++] = currentShape;
			currentShape = null;
			repaint ();
		}
		
		public void mouseMoved (MouseEvent me) {
			refreshStateLabel (me.getX (), me.getY ());
		}
		
		public void mouseDragged (MouseEvent me) {
			refreshStateLabel (me.getX (), me.getY ());
			refreshCurrentShape (me.getX (), me.getY ());
			repaint ();
		}
		
		private void refreshStateLabel (int x, int y) {
			stateLabel.setText (String.format ("(%d, %d)", x, y));
		}
		
		private void refreshCurrentShape (int x, int y) {
			currentShape.setFinalPoint (x, y);
		}
	}
}
