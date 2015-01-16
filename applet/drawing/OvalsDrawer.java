import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JColorChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 * Draws ovals over an applet according to the user-defined parameters.
 * <p/>
 * @author	Jonathan Alexander Soto Montoya (jonathansoto.an@gmail.com)
 * @version	1, 16/01/15
 */
/* <applet code="OvalsDrawer.class" width="800" height="600"></applet> */
public class OvalsDrawer extends JApplet {
	private JTextField x;
	private JTextField y;
	private JTextField width;
	private JTextField height;
	private JCheckBox filled;
	private Rectangle currentOval;// the delimiter of the oval
	private int horizontalLimit;
	private Color currentColor;
	
	@Override
	public void init() {
		x = new JTextField(3);
		y = new JTextField(3);
		width = new JTextField(5);
		height = new JTextField(5);
		filled = new JCheckBox("Filled");
		currentColor = Color.BLACK;

		JButton btn_draw = new JButton("Draw");
		btn_draw.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent ae) {
				try {
					if(Integer.parseInt(x.getText()) > x.getRootPane().getBounds().width ||
						Integer.parseInt(y.getText()) + horizontalLimit > x.getRootPane().getBounds().height)
						JOptionPane.showMessageDialog(x.getRootPane(), "Your coordinates are out of the bounds. You are not going to be able to see anything");

					currentOval = new Rectangle(
						Integer.parseInt(x.getText()),
						Integer.parseInt(y.getText()),
						Integer.parseInt(width.getText()),
						Integer.parseInt(height.getText())
					);
					repaint();
				} catch(NumberFormatException nfe) {
					JOptionPane.showMessageDialog(x.getRootPane(), "Any of the numbers you entered is not valid\nPlease, try again");
				}
			}
		});

		final JButton btn_color = new JButton();
		btn_color.setBackground(currentColor);
		btn_color.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent ae) {
				currentColor = JColorChooser.showDialog(x.getRootPane(), "Choose a color", currentColor);
				btn_color.setBackground(currentColor);
			}
		});

		JPanel toolBar = new JPanel(new GridLayout(2, 6, 10, 10));
		toolBar.add(new JLabel("X", SwingConstants.RIGHT));
		toolBar.add(x);
		toolBar.add(new JLabel("Width", SwingConstants.RIGHT));
		toolBar.add(width);
		toolBar.add(filled);
		toolBar.add(new JLabel());
		toolBar.add(new JLabel("Y", SwingConstants.RIGHT));
		toolBar.add(y);
		toolBar.add(new JLabel("Height", SwingConstants.RIGHT));
		toolBar.add(height);
		toolBar.add(btn_color);
		toolBar.add(btn_draw);

		JPanel flowed = new JPanel(new FlowLayout());
		flowed.add(toolBar);

		add(flowed, BorderLayout.NORTH);
	}

	@Override
	public void start() {
		horizontalLimit = x.getParent().getBounds().height + 10;
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);

		g.setColor(Color.BLACK);
		g.drawLine(0, horizontalLimit, x.getRootPane().getBounds().width, horizontalLimit);
		g.setColor(currentColor);

		if(currentOval != null)
			if(filled.isSelected())
				g.fillOval(currentOval.x, currentOval.y + horizontalLimit, currentOval.width, currentOval.height);
			else
				g.drawOval(currentOval.x, currentOval.y + horizontalLimit, currentOval.width, currentOval.height);
	}
}