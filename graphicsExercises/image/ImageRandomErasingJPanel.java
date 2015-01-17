package deitel.graphicsExercises.image;

import deitel.maths.Calculating;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 * Renders an image and can be erased with different funny randomized ways.
 * <p/>
 * @author	Jonathan Alexander Soto Montoya (jonathansoto.an@gmail.com)
 * @version	1, 17/01/15
 */
public class ImageRandomErasingJPanel extends JPanel {
	public enum ErasingWay {
		NONE,
		PIXEL,
		LINE,
		RECT,
		OVAL,
		RANDOM
	} 

	private BufferedImage image;
	private ErasingWay erasingWay;
	private Random randomGenerator;
	private Timer erasingTimer;
	private int pixelsErased;
	private boolean repaintImage = true;

	// Erasers
	private ArrayList<Point> pixels;
	private ArrayList<Rectangle> lines;// the width and height are treated as the second point
	private ArrayList<Rectangle> rects;
	private ArrayList<Rectangle> ovals;

	// Constants
	private int ANIMATION_DELAY = 10;// milliseconds
	private float PERCENTAGE_FULL_ERASE = 0.7f;// when the percentage erased reachs this one, it is fully erased
	
	/**
	 * Creates an ImageRandomErasingJPanel from the specified URL.
	 * <p/>
	 * @param	imgUrl	the URL pointing to the image
	 */
	public ImageRandomErasingJPanel(URL imgUrl) throws IOException {
		super();
		image = ImageIO.read(imgUrl);
		randomGenerator = new Random();
		pixels = new ArrayList<Point>();
		lines = new ArrayList<Rectangle>();
		rects = new ArrayList<Rectangle>();
		ovals = new ArrayList<Rectangle>();

		erasingTimer = new Timer(ANIMATION_DELAY, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				repaint();
			}
		});
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		// the percentage is not the actual. it is just an aproximation, for pixelsErased is not exact
		if((float) pixelsErased / (image.getWidth() * image.getHeight()) > PERCENTAGE_FULL_ERASE)
			repaintImage = false;

		if(repaintImage) {
			if(erasingWay != null) {
				Point originPoint = getRandomPoint();
				boolean random = false;

				if(erasingWay == ErasingWay.RANDOM) {
					erasingWay = ErasingWay.values()[randomGenerator.nextInt(ErasingWay.values().length)];
					random = true;
				}

				switch(erasingWay) {
					case PIXEL:
						pixels.add(originPoint);
						++pixelsErased;
						break;

					case LINE:
						Point endPoint = getRandomPoint();

						lines.add(new Rectangle(originPoint.x, originPoint.y, endPoint.x, endPoint.y));
						pixelsErased += Calculating.getHypotenuse(
							Math.abs(originPoint.x - endPoint.x),
							Math.abs(originPoint.y - originPoint.y)
						);
						break;

					case RECT:
						Dimension rectSize = getRandomSize();

						rects.add(new Rectangle(originPoint.x, originPoint.y, rectSize.width, rectSize.height));
						pixelsErased += 2 * (rectSize.width + rectSize.height);
						break;

					case OVAL:
						Dimension ovalSize = getRandomSize();

						ovals.add(new Rectangle(originPoint.x, originPoint.y, ovalSize.width, ovalSize.height));
						pixelsErased += Calculating.getEllipsePerimeter(ovalSize.width / 2, ovalSize.height / 2);
						break;
				}

				if(random)
					erasingWay = ErasingWay.RANDOM;
			}

			g.setColor(Color.WHITE);
			g.drawImage(image, 0, 0, this);
			drawErasers(g);
		} else
			erasingTimer.stop();
	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension(image.getWidth(), image.getHeight());
	}

	/**
	 * Erase the image with the specified method.
	 * <p/>
	 * @param	erasingWay	the way or method the image erasing process must use
	 */
	public void eraseBy(ErasingWay erasingWay) {
		this.erasingWay = erasingWay;
		erasingTimer.start();
	}

	/**
	 * Draws the eraser shapes that have been calculated.
	 * <p/>
	 * @param	g	the Graphics context
	 */
	private void drawErasers(Graphics g) {
		for(Point pixel : pixels)
			g.drawLine(pixel.x, pixel.y, pixel.x, pixel.y);

		for(Rectangle line : lines)
			g.drawLine(line.x, line.y, line.width, line.height);

		for(Rectangle rect : rects)
			g.drawRect(rect.x, rect.y, rect.width, rect.height);

		for(Rectangle oval : ovals)
			g.drawOval(oval.x, oval.y, oval.width, oval.height);
	}

	/**
	 * Returns a random point over the image.
	 * <p/>
	 * @return				the random coordinates that are inside the image's bounds
	 */
	private Point getRandomPoint() {
		return new Point(
			randomGenerator.nextInt(image.getWidth()),
			randomGenerator.nextInt(image.getHeight())
		);
	}

	/**
	 * Returns a random size. Used for ovals and rectangles.
	 * <p/>
	 * @return				a random size between 0 (inclusive) and the half of the image size (exclusive, according to the axis)
	 */
	private Dimension getRandomSize() {
		return new Dimension(
			randomGenerator.nextInt(image.getWidth() / 2),
			randomGenerator.nextInt(image.getHeight() / 2)
		);
	}

	public static void main(String[] args) throws MalformedURLException, IOException {
		ImageRandomErasingJPanel irejp = new ImageRandomErasingJPanel(new URL("file:///D:/Entertainment/Images/others/space_0.jpg"));
		JFrame wndw = new JFrame("ImageRandomErasingJPanel testing");
		wndw.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		wndw.add(irejp);
		wndw.pack();
		wndw.setVisible(true);
		irejp.eraseBy(ErasingWay.RANDOM);
	}
}